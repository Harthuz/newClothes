-- Criação do banco de dados
CREATE DATABASE `BD_NEWCLOTHES`;

-- Seleciona o banco de dados para uso
USE `BD_NEWCLOTHES`;

-- Criação da tabela 'categoria'
CREATE TABLE `categoria` (
                             `Cod` INT AUTO_INCREMENT PRIMARY KEY, -- Código da categoria, auto-incremento e chave primária
                             `Nome` VARCHAR(80) NOT NULL, -- Nome da categoria
                             `Descricao` VARCHAR(300) NOT NULL -- Descrição da categoria
);

-- Criação da tabela 'doador'
CREATE TABLE `doador` (
                          `ID_Doador` INT AUTO_INCREMENT PRIMARY KEY, -- ID do doador, auto-incremento e chave primária
                          `Nome` VARCHAR(80) NOT NULL, -- Nome do doador
                          `Email` VARCHAR(100) NOT NULL, -- Email do doador
                          `CPF` VARCHAR(30) NOT NULL, -- CPF do doador
                          `Senha` VARCHAR(255) NOT NULL -- Senha do doador
);

-- Criação da tabela 'ong'
CREATE TABLE `ong` (
                       `ID_ONG` INT AUTO_INCREMENT PRIMARY KEY, -- ID da ONG, auto-incremento e chave primária
                       `Nome` VARCHAR(80) NOT NULL, -- Nome da ONG
                       `Email` VARCHAR(100) NOT NULL, -- Email da ONG
                       `CNPJ` VARCHAR(30) NOT NULL, -- CNPJ da ONG
                       `Endereco` VARCHAR(100) NOT NULL, -- Endereço da ONG
                       `Telefone` VARCHAR(20) NOT NULL, -- Telefone da ONG
                       `Senha` VARCHAR(255) NOT NULL -- Senha da ONG
);

-- Criação da tabela 'vestimenta'
CREATE TABLE `vestimenta` (
                              `ID_Doacao` INT AUTO_INCREMENT PRIMARY KEY, -- ID da doação, auto-incremento e chave primária
                              `Qtd` INT NOT NULL, -- Quantidade de vestimentas
                              `Categoria` INT NOT NULL, -- Código da categoria (referência à tabela 'categoria')
                              `DataDoacao` DATE, -- Data da doação
                              `ID_Doador` INT NOT NULL, -- ID do doador (referência à tabela 'doador')
                              FOREIGN KEY (`ID_Doador`) REFERENCES `doador`(`ID_Doador`), -- Chave estrangeira para a tabela 'doador'
                              FOREIGN KEY (`Categoria`) REFERENCES `categoria`(`Cod`) -- Chave estrangeira para a tabela 'categoria'
);

-- Criação da tabela 'telefone'
CREATE TABLE `telefone` (
                            `Telefone` VARCHAR(30) NOT NULL, -- Número de telefone
                            `ID_Doador` INT NOT NULL, -- ID do doador (referência à tabela 'doador')
                            FOREIGN KEY (`ID_Doador`) REFERENCES `doador`(`ID_Doador`) -- Chave estrangeira para a tabela 'doador'
);

-- Criação da tabela 'Ong_Doacao'
CREATE TABLE `Ong_Doacao` (
                              `ID_ONG` INT NOT NULL, -- ID da ONG (referência à tabela 'ong')
                              `ID_Doacao` INT NOT NULL, -- ID da doação (referência à tabela 'vestimenta')
                              `DataRecebimento` DATE, -- Data de recebimento da doação pela ONG
                              FOREIGN KEY (`ID_ONG`) REFERENCES `ong`(`ID_ONG`), -- Chave estrangeira para a tabela 'ong'
                              FOREIGN KEY (`ID_Doacao`) REFERENCES `vestimenta`(`ID_Doacao`) -- Chave estrangeira para a tabela 'vestimenta'
);
