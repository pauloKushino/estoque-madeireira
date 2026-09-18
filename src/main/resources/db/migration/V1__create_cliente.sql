-- PLACEHOLDER: a tabela cliente pertence ao módulo de outro desenvolvedor.
-- Esta migration existe apenas para destravar a FK de venda.cliente_id (V3).
-- Substitua este arquivo pela migration oficial do modulo de Cliente (mesmo nome/versao).
CREATE TABLE cliente (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    cpf_cnpj VARCHAR(18),
    telefone VARCHAR(20),
    email VARCHAR(120),
    cep VARCHAR(9),
    logradouro VARCHAR(150),
    bairro VARCHAR(100),
    cidade VARCHAR(100),
    uf CHAR(2)
);
