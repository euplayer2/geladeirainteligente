CREATE TABLE food_item (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(255),
    categoria VARCHAR(255),
    quantidade INT,
    validade DATE
);