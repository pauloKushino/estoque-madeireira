package com.pi.estoquemadeireira.service;

import com.pi.estoquemadeireira.dto.ItemVendaRequestDTO;
import com.pi.estoquemadeireira.dto.VendaMapper;
import com.pi.estoquemadeireira.dto.VendaRequestDTO;
import com.pi.estoquemadeireira.dto.VendaResponseDTO;
import com.pi.estoquemadeireira.entity.ItemVenda;
import com.pi.estoquemadeireira.entity.Produto;
import com.pi.estoquemadeireira.entity.Venda;
import com.pi.estoquemadeireira.exception.EstoqueInsuficienteException;
import com.pi.estoquemadeireira.repository.ProdutoRepository;
import com.pi.estoquemadeireira.repository.VendaRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VendaService {

    private static final Logger log = LoggerFactory.getLogger(VendaService.class);

    private final VendaRepository vendaRepository;
    private final ProdutoRepository produtoRepository;

    public VendaService(VendaRepository vendaRepository, ProdutoRepository produtoRepository) {
        this.vendaRepository = vendaRepository;
        this.produtoRepository = produtoRepository;
    }

    @Transactional(readOnly = true)
    public List<VendaResponseDTO> listar() {
        return vendaRepository.findAll().stream()
                .map(VendaMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public VendaResponseDTO buscarPorId(Long id) {
        return VendaMapper.toResponse(buscarEntidade(id));
    }

    @Transactional
    public VendaResponseDTO criar(VendaRequestDTO dto) {
        log.info("Iniciando venda: clienteId={}, itens={}", dto.clienteId(), dto.itens().size());

        Venda venda = new Venda();
        venda.setClienteId(dto.clienteId());
        venda.setDataVenda(LocalDateTime.now());

        for (ItemVendaRequestDTO itemDTO : dto.itens()) {
            Produto produto = produtoRepository.findById(itemDTO.produtoId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Produto nao encontrado: id " + itemDTO.produtoId()));

            // Regra 1: rejeita a venda INTEIRA se qualquer item nao tiver estoque
            if (itemDTO.quantidade().compareTo(produto.getQuantidadeEstoque()) > 0) {
                log.warn("Venda rejeitada por estoque insuficiente: produtoId={}, disponivel={}, solicitado={}",
                        produto.getId(), produto.getQuantidadeEstoque(), itemDTO.quantidade());
                throw new EstoqueInsuficienteException(String.format(
                        "Estoque insuficiente para o produto '%s' (id %d): disponivel %s %s, solicitado %s",
                        produto.getNome(), produto.getId(),
                        produto.getQuantidadeEstoque().toPlainString(), produto.getUnidadeMedida(),
                        itemDTO.quantidade().toPlainString()));
            }

            produto.debitarEstoque(itemDTO.quantidade());

            ItemVenda item = new ItemVenda();
            item.setProduto(produto);
            item.setQuantidade(itemDTO.quantidade());
            item.setPrecoUnitarioNoMomento(produto.getPrecoUnitario());
            item.calcularSubtotal();
            venda.adicionarItem(item);
        }

        venda.calcularValorTotal();
        Venda salva = vendaRepository.save(venda);
        log.info("Venda criada: id={}, valorTotal={}", salva.getId(), salva.getValorTotal());
        return VendaMapper.toResponse(salva);
    }

    private Venda buscarEntidade(Long id) {
        return vendaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Venda nao encontrada: id " + id));
    }
}
