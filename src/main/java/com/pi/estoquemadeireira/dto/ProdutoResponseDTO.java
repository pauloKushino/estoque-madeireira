package com.pi.estoquemadeireira.dto;

import com.pi.estoquemadeireira.entity.UnidadeMedida;

import java.math.BigDecimal;

public record ProdutoResponseDTO(
        Long id,
        String nome,
        UnidadeMedida unidadeMedida,
        BigDecimal quantidadeEstoque,
        BigDecimal precoUnitario,
        BigDecimal estoqueMinimo
) {
}
