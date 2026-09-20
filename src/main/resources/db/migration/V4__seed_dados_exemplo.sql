-- Dados de exemplo para demonstracao (pitch): produtos tipicos de madeireira e clientes.
-- O produto "Dormente de Acacia" fica abaixo do estoque minimo de proposito,
-- para evidenciar o indicador de estoque baixo na interface.

INSERT INTO produto (nome, unidade_medida, quantidade_estoque, preco_unitario, estoque_minimo) VALUES
('Tabua de Pinus 25cm', 'M3', 45.500, 850.00, 10.000),
('Viga de Eucalipto 3m', 'METRO_LINEAR', 120.000, 45.90, 30.000),
('Ripao de Cedro', 'UNIDADE', 200, 12.50, 50),
('Compensado Naval 18mm', 'UNIDADE', 60, 185.00, 15),
('Dormente de Acacia 2.4m', 'UNIDADE', 8, 95.00, 10);

INSERT INTO cliente (nome, cpf_cnpj, telefone, email, cep, logradouro, bairro, cidade, uf) VALUES
('Construtora Horizonte LTDA', '11.222.333/0001-81', '1133445566', 'obras@horizonte.com', '01311-000', 'Avenida Paulista', 'Bela Vista', 'Sao Paulo', 'SP'),
('Marcenaria Arte em Madeira', '07.526.557/0001-00', '4133224455', 'contato@artemadeira.com', '80010-000', 'Rua XV de Novembro', 'Centro', 'Curitiba', 'PR'),
('Joao Pereira dos Santos', '529.982.247-25', '11987654321', 'joao.pereira@email.com', '01001-000', 'Praca da Se', 'Se', 'Sao Paulo', 'SP');
