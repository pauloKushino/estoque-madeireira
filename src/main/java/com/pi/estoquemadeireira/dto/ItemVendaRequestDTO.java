package com.pi.estoquemadeireira.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record ItemVendaRequestDTO(

        @NotNull(message = "produtoId e obrigatorio")
        Long produtoId,

        @NotNull(message = "quantidade e obrigatoria")
        @Positive(message = "quantidade deve ser maior que zero")
        BigDecimal quantidade
) {
}
