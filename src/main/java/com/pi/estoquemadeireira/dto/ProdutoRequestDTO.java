package com.pi.estoquemadeireira.dto;

import com.pi.estoquemadeireira.entity.UnidadeMedida;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record ProdutoRequestDTO(

        @NotBlank(message = "nome e obrigatorio")
        @Size(max = 120, message = "nome deve ter no maximo 120 caracteres")
        String nome,

        @NotNull(message = "unidadeMedida e obrigatoria (M3, METRO_LINEAR ou UNIDADE)")
        UnidadeMedida unidadeMedida,

        @NotNull(message = "quantidadeEstoque e obrigatoria")
        @PositiveOrZero(message = "quantidadeEstoque nao pode ser negativa")
        BigDecimal quantidadeEstoque,

        @NotNull(message = "precoUnitario e obrigatorio")
        @Positive(message = "precoUnitario deve ser maior que zero")
        BigDecimal precoUnitario,

        @DecimalMin(value = "0.0", message = "estoqueMinimo nao pode ser negativo")
        BigDecimal estoqueMinimo
) {
}
