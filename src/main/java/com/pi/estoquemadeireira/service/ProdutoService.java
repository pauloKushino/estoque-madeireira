package com.pi.estoquemadeireira.service;

import com.pi.estoquemadeireira.dto.EstoqueResponseDTO;
import com.pi.estoquemadeireira.dto.ProdutoMapper;
import com.pi.estoquemadeireira.dto.ProdutoRequestDTO;
import com.pi.estoquemadeireira.dto.ProdutoResponseDTO;
import com.pi.estoquemadeireira.entity.Produto;
import com.pi.estoquemadeireira.repository.ProdutoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProdutoService {

    private static final Logger log = LoggerFactory.getLogger(ProdutoService.class);

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Transactional(readOnly = true)
    public List<ProdutoResponseDTO> listar() {
        return produtoRepository.findAll().stream()
                .map(ProdutoMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ProdutoResponseDTO buscarPorId(Long id) {
        return ProdutoMapper.toResponse(buscarEntidade(id));
    }

    @Transactional(readOnly = true)
    public EstoqueResponseDTO consultarEstoque(Long id) {
        Produto produto = buscarEntidade(id);
        return new EstoqueResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getQuantidadeEstoque(),
                produto.getUnidadeMedida()
        );
    }

    @Transactional
    public ProdutoResponseDTO criar(ProdutoRequestDTO dto) {
        Produto salvo = produtoRepository.save(ProdutoMapper.toEntity(dto));
        log.info("Produto criado: id={}, nome='{}'", salvo.getId(), salvo.getNome());
        return ProdutoMapper.toResponse(salvo);
    }

    @Transactional
    public ProdutoResponseDTO atualizar(Long id, ProdutoRequestDTO dto) {
        Produto produto = buscarEntidade(id);
        ProdutoMapper.updateEntity(produto, dto);
        Produto atualizado = produtoRepository.save(produto);
        log.info("Produto atualizado: id={}", atualizado.getId());
        return ProdutoMapper.toResponse(atualizado);
    }

    @Transactional
    public void excluir(Long id) {
        Produto produto = buscarEntidade(id);
        produtoRepository.delete(produto);
        log.info("Produto excluido: id={}", id);
    }

    private Produto buscarEntidade(Long id) {
        return produtoRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Produto nao encontrado: id " + id));
    }
}
