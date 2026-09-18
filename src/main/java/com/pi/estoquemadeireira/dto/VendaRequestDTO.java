package com.pi.estoquemadeireira.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record VendaRequestDTO(

        @NotNull(message = "clienteId e obrigatorio")
        Long clienteId,

        @NotEmpty(message = "a venda deve ter ao menos um item")
        List<@Valid ItemVendaRequestDTO> itens
) {
}
