package com.pi.estoquemadeireira.dto;

import com.pi.estoquemadeireira.entity.Cliente;

public final class ClienteMapper {

    private ClienteMapper() {
    }

    public static Cliente toEntity(ClienteRequestDTO dto) {
        return new Cliente(
                dto.nome(),
                dto.cpfCnpj(),
                dto.telefone(),
                dto.email(),
                dto.cep(),
                dto.logradouro(),
                dto.bairro(),
                dto.cidade(),
                dto.uf()
        );
    }

    public static ClienteResponseDTO toResponse(Cliente cliente) {
        return new ClienteResponseDTO(
                cliente.getId(),
                cliente.getNome(),
                cliente.getCpfCnpj(),
                cliente.getTelefone(),
                cliente.getEmail(),
                cliente.getCep(),
                cliente.getLogradouro(),
                cliente.getBairro(),
                cliente.getCidade(),
                cliente.getUf()
        );
    }

    public static void updateEntity(Cliente cliente, ClienteRequestDTO dto) {
        cliente.setNome(dto.nome());
        cliente.setCpfCnpj(dto.cpfCnpj());
        cliente.setTelefone(dto.telefone());
        cliente.setEmail(dto.email());
        cliente.setCep(dto.cep());
        cliente.setLogradouro(dto.logradouro());
        cliente.setBairro(dto.bairro());
        cliente.setCidade(dto.cidade());
        cliente.setUf(dto.uf());
    }
}
