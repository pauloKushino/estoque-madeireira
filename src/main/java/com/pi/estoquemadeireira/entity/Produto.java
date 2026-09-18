package com.pi.estoquemadeireira.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Enumerated(EnumType.STRING)
    @Column(name = "unidade_medida", nullable = false, length = 20)
    private UnidadeMedida unidadeMedida;

    @Column(name = "quantidade_estoque", nullable = false, precision = 12, scale = 3)
    private BigDecimal quantidadeEstoque;

    @Column(name = "preco_unitario", nullable = false, precision = 12, scale = 2)
    private BigDecimal precoUnitario;

    @Column(name = "estoque_minimo", precision = 12, scale = 3)
    private BigDecimal estoqueMinimo;

    protected Produto() {
    }

    public Produto(String nome, UnidadeMedida unidadeMedida, BigDecimal quantidadeEstoque,
                   BigDecimal precoUnitario, BigDecimal estoqueMinimo) {
        this.nome = nome;
        this.unidadeMedida = unidadeMedida;
        this.quantidadeEstoque = quantidadeEstoque;
        this.precoUnitario = precoUnitario;
        this.estoqueMinimo = estoqueMinimo;
    }

    // Trava de seguranca da regra "nunca permitir estoque negativo";
    // a validacao amigavel ao usuario acontece antes, no VendaService.
    public void debitarEstoque(BigDecimal quantidade) {
        BigDecimal novoSaldo = this.quantidadeEstoque.subtract(quantidade);
        if (novoSaldo.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalStateException("Operacao deixaria o estoque do produto " + this.id + " negativo");
        }
        this.quantidadeEstoque = novoSaldo;
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public UnidadeMedida getUnidadeMedida() {
        return unidadeMedida;
    }

    public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
        this.unidadeMedida = unidadeMedida;
    }

    public BigDecimal getQuantidadeEstoque() {
        return quantidadeEstoque;
    }

    public void setQuantidadeEstoque(BigDecimal quantidadeEstoque) {
        this.quantidadeEstoque = quantidadeEstoque;
    }

    public BigDecimal getPrecoUnitario() {
        return precoUnitario;
    }

    public void setPrecoUnitario(BigDecimal precoUnitario) {
        this.precoUnitario = precoUnitario;
    }

    public BigDecimal getEstoqueMinimo() {
        return estoqueMinimo;
    }

    public void setEstoqueMinimo(BigDecimal estoqueMinimo) {
        this.estoqueMinimo = estoqueMinimo;
    }
}
