package com.pi.estoquemadeireira.dto;

import java.math.BigDecimal;

public record ItemVendaResponseDTO(
        Long id,
        Long produtoId,
        String nomeProduto,
        BigDecimal quantidade,
        BigDecimal precoUnitarioNoMomento,
        BigDecimal subtotal
) {
}
