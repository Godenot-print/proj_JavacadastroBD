CREATE DATABASE CadCli_DataBase;

USE CadCli_DataBase;

DROP TABLE IF EXISTS cliente;

CREATE TABLE cliente (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    sobrenome VARCHAR(20),
    nomeM VARCHAR(20),
    nomeP VARCHAR(20) NOT NULL,
    cpf VARCHAR(20),
    data_nascimento VARCHAR(10),
    endereco VARCHAR(50),
    cep VARCHAR(15)
);