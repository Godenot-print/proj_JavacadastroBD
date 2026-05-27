CREATE DATABASE ProjetoJavaConnectionBD;

USE ProjetoJavaConnectionBD;

DROP TABLE IF EXISTS aluno;

CREATE TABLE aluno (
    id INT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    cpf VARCHAR(14),
    rg VARCHAR(15),
    cep VARCHAR(10),
    endereco VARCHAR(100),
    numero VARCHAR(10),
    bairro VARCHAR(50),
    cidade VARCHAR(50),
    uf VARCHAR(2),
    complemento VARCHAR(50),
    telefone VARCHAR(20),
    celular VARCHAR(20),
    email VARCHAR(100),
    data_nascimento VARCHAR(10)
);
