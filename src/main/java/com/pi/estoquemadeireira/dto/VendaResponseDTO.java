package com.pi.estoquemadeireira.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public record VendaResponseDTO(
        Long id,
        Long clienteId,
        LocalDateTime dataVenda,
        BigDecimal valorTotal,
        List<ItemVendaResponseDTO> itens
) {
}
