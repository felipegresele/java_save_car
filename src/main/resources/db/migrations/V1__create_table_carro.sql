-- V1: Migration criando tabela carro no banco de dados

CREATE TABLE carro (
    id SERIAL PRIMARY KEY,
    marca VARCHAR(255) NOT NULL,
    modelo VARCHAR(255) NOT NULL,
    ano INT NOT NULL,
    novo BOOLEAN DEFAULT false,
)