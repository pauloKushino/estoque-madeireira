package com.pi.estoquemadeireira.dto;

import com.pi.estoquemadeireira.entity.ItemVenda;
import com.pi.estoquemadeireira.entity.Venda;

import java.util.List;

public final class VendaMapper {

    private VendaMapper() {
    }

    public static VendaResponseDTO toResponse(Venda venda) {
        List<ItemVendaResponseDTO> itens = venda.getItens().stream()
                .map(VendaMapper::toItemResponse)
                .toList();

        return new VendaResponseDTO(
                venda.getId(),
                venda.getClienteId(),
                venda.getDataVenda(),
                venda.getValorTotal(),
                itens
        );
    }

    private static ItemVendaResponseDTO toItemResponse(ItemVenda item) {
        return new ItemVendaResponseDTO(
                item.getId(),
                item.getProduto().getId(),
                item.getProduto().getNome(),
                item.getQuantidade(),
                item.getPrecoUnitarioNoMomento(),
                item.getSubtotal()
        );
    }
}
