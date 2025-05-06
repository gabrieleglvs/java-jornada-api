CREATE TABLE destinos (
    id bigint not null auto_increment,
    foto VARCHAR(255),
    nome VARCHAR(255),
    preco DECIMAL(10, 2),

    PRIMARY KEY(id)
);