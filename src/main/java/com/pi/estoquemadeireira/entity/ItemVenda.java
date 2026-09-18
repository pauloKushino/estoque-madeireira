package com.pi.estoquemadeireira.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Entity
@Table(name = "item_venda")
public class ItemVenda {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "venda_id", nullable = false)
    private Venda venda;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(nullable = false, precision = 12, scale = 3)
    private BigDecimal quantidade;

    // Snapshot: preserva o preco praticado na data da venda,
    // mesmo que o preco do produto mude depois.
    @Column(name = "preco_unitario_no_momento", nullable = false, precision = 12, scale = 2)
    private BigDecimal precoUnitarioNoMomento;

    @Column(nullable = false, precision = 14, scale = 2)
    private BigDecimal subtotal;

    public void calcularSubtotal() {
        this.subtotal = this.quantidade.multiply(this.precoUnitarioNoMomento)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public Long getId() {
        return id;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public BigDecimal getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(BigDecimal quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getPrecoUnitarioNoMomento() {
        return precoUnitarioNoMomento;
    }

    public void setPrecoUnitarioNoMomento(BigDecimal precoUnitarioNoMomento) {
        this.precoUnitarioNoMomento = precoUnitarioNoMomento;
    }

    public BigDecimal getSubtotal() {
        return subtotal;
    }
}
