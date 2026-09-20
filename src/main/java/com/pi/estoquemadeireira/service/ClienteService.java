package com.pi.estoquemadeireira.service;

import com.pi.estoquemadeireira.dto.ClienteMapper;
import com.pi.estoquemadeireira.dto.ClienteRequestDTO;
import com.pi.estoquemadeireira.dto.ClienteResponseDTO;
import com.pi.estoquemadeireira.entity.Cliente;
import com.pi.estoquemadeireira.repository.ClienteRepository;
import jakarta.persistence.EntityNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClienteService {

    private static final Logger log = LoggerFactory.getLogger(ClienteService.class);

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Transactional(readOnly = true)
    public List<ClienteResponseDTO> listar(String nome) {
        List<Cliente> clientes = (nome == null || nome.isBlank())
                ? clienteRepository.findAll()
                : clienteRepository.findByNomeContainingIgnoreCase(nome);
        return clientes.stream()
                .map(ClienteMapper::toResponse)
                .toList();
    }

    @Transactional(readOnly = true)
    public ClienteResponseDTO buscarPorId(Long id) {
        return ClienteMapper.toResponse(buscarEntidade(id));
    }

    @Transactional
    public ClienteResponseDTO criar(ClienteRequestDTO dto) {
        Cliente salvo = clienteRepository.save(ClienteMapper.toEntity(dto));
        log.info("Cliente criado: id={}, nome='{}'", salvo.getId(), salvo.getNome());
        return ClienteMapper.toResponse(salvo);
    }

    @Transactional
    public ClienteResponseDTO atualizar(Long id, ClienteRequestDTO dto) {
        Cliente cliente = buscarEntidade(id);
        ClienteMapper.updateEntity(cliente, dto);
        Cliente atualizado = clienteRepository.save(cliente);
        log.info("Cliente atualizado: id={}", atualizado.getId());
        return ClienteMapper.toResponse(atualizado);
    }

    @Transactional
    public void excluir(Long id) {
        Cliente cliente = buscarEntidade(id);
        clienteRepository.delete(cliente);
        log.info("Cliente excluido: id={}", id);
    }

    private Cliente buscarEntidade(Long id) {
        return clienteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cliente nao encontrado: id " + id));
    }
}
