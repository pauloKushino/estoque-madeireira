package com.pi.estoquemadeireira.dto;

import com.pi.estoquemadeireira.entity.UnidadeMedida;

import java.math.BigDecimal;

public record EstoqueResponseDTO(
        Long produtoId,
        String nomeProduto,
        BigDecimal quantidadeDisponivel,
        UnidadeMedida unidadeMedida
) {
}
