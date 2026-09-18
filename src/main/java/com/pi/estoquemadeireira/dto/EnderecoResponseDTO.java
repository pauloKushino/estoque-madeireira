package com.pi.estoquemadeireira.dto;

public record EnderecoResponseDTO(
        String cep,
        String logradouro,
        String bairro,
        String cidade,
        String uf
) {
}
