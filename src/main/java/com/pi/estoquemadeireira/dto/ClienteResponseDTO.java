package com.pi.estoquemadeireira.dto;

public record ClienteResponseDTO(
        Long id,
        String nome,
        String cpfCnpj,
        String telefone,
        String email,
        String cep,
        String logradouro,
        String bairro,
        String cidade,
        String uf
) {
}
