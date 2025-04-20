CREATE TABLE depoimentos (
    id bigint not null auto_increment,
    foto VARCHAR(255),
    depoimento VARCHAR(510),
    nome VARCHAR(255),

    PRIMARY KEY(id)
);