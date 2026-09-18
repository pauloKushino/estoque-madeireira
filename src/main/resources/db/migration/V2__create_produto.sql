CREATE TABLE produto (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    unidade_medida VARCHAR(20) NOT NULL,
    quantidade_estoque DECIMAL(12, 3) NOT NULL,
    preco_unitario DECIMAL(12, 2) NOT NULL,
    estoque_minimo DECIMAL(12, 3) NULL,
    CONSTRAINT chk_produto_estoque_positivo CHECK (quantidade_estoque >= 0),
    CONSTRAINT chk_produto_preco_positivo CHECK (preco_unitario > 0)
);
