package com.pi.estoquemadeireira.dto;

import com.pi.estoquemadeireira.entity.Produto;

public final class ProdutoMapper {

    private ProdutoMapper() {
    }

    public static Produto toEntity(ProdutoRequestDTO dto) {
        return new Produto(
                dto.nome(),
                dto.unidadeMedida(),
                dto.quantidadeEstoque(),
                dto.precoUnitario(),
                dto.estoqueMinimo()
        );
    }

    public static ProdutoResponseDTO toResponse(Produto produto) {
        return new ProdutoResponseDTO(
                produto.getId(),
                produto.getNome(),
                produto.getUnidadeMedida(),
                produto.getQuantidadeEstoque(),
                produto.getPrecoUnitario(),
                produto.getEstoqueMinimo()
        );
    }

    public static void updateEntity(Produto produto, ProdutoRequestDTO dto) {
        produto.setNome(dto.nome());
        produto.setUnidadeMedida(dto.unidadeMedida());
        produto.setQuantidadeEstoque(dto.quantidadeEstoque());
        produto.setPrecoUnitario(dto.precoUnitario());
        produto.setEstoqueMinimo(dto.estoqueMinimo());
    }
}
