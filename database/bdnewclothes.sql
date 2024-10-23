-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Tempo de geração: 22/10/2024 às 01:33
-- Versão do servidor: 8.3.0
-- Versão do PHP: 8.2.18

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Banco de dados: `bdnewclothes`
--
CREATE DATABASE `bdnewclothes`;
USE `bdnewclothes`;

-- --------------------------------------------------------

--
-- Estrutura para tabela `categoria`
--

DROP TABLE IF EXISTS `categoria`;
CREATE TABLE IF NOT EXISTS `categoria` (
  `cod` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  `descricao` varchar(300) NOT NULL,
  PRIMARY KEY (`cod`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `doacao`
--

DROP TABLE IF EXISTS `doacao`;
CREATE TABLE IF NOT EXISTS `doacao` (
  `ID_doacao` int NOT NULL AUTO_INCREMENT,
  `dataDoacao` date DEFAULT NULL,
  `ID_doador` int NOT NULL,
  `ID_ong` int NOT NULL,
  PRIMARY KEY (`ID_doacao`),
  KEY `ID_doador` (`ID_doador`),
  KEY `ID_ong` (`ID_ong`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `doador`
--

DROP TABLE IF EXISTS `doador`;
CREATE TABLE IF NOT EXISTS `doador` (
  `ID_doador` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  `email` varchar(100) NOT NULL,
  `CPF` varchar(30) NOT NULL,
  `senha` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_doador`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `itemdoacao`
--

DROP TABLE IF EXISTS `itemdoacao`;
CREATE TABLE IF NOT EXISTS `itemdoacao` (
  `ID_item` int NOT NULL AUTO_INCREMENT,
  `qtd` int NOT NULL,
  `ID_doacao` int NOT NULL,
  `cod` int NOT NULL,
  `ID_tamanho` int DEFAULT NULL,
  PRIMARY KEY (`ID_item`),
  KEY `ID_doacao` (`ID_doacao`),
  KEY `cod` (`cod`),
  KEY `ID_tamanho` (`ID_tamanho`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `ong`
--

DROP TABLE IF EXISTS `ong`;
CREATE TABLE IF NOT EXISTS `ong` (
  `ID_ong` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(80) NOT NULL,
  `email` varchar(100) NOT NULL,
  `CNPJ` varchar(30) NOT NULL,
  `endereco` varchar(100) NOT NULL,
  `telefone` varchar(20) NOT NULL,
  `senha` varchar(100) NOT NULL,
  PRIMARY KEY (`ID_ong`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `tamanho`
--

DROP TABLE IF EXISTS `tamanho`;
CREATE TABLE IF NOT EXISTS `tamanho` (
  `ID_tamanho` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(90) NOT NULL,
  PRIMARY KEY (`ID_tamanho`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estrutura para tabela `telefone`
--

DROP TABLE IF EXISTS `telefone`;
CREATE TABLE IF NOT EXISTS `telefone` (
  `telefone` varchar(30) NOT NULL,
  `ID_doador` int NOT NULL,
  KEY `ID_doador` (`ID_doador`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

DROP TABLE IF EXISTS `administrador`;
CREATE TABLE IF NOT EXISTS `administrador` (
  `email` varchar(100) NOT NULL,
  `senha` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- Inserindo dados na tabela `categoria`
INSERT INTO `categoria` (`nome`, `descricao`) VALUES
                                                  ('Roupas', 'Artigos de vestuário para todas as idades'),
                                                  ('Calçados', 'Diversos tipos de calçados'),
                                                  ('Acessórios', 'Acessórios de moda e utilidade');

-- Inserindo dados na tabela `doador`
INSERT INTO `doador` (`nome`, `email`, `CPF`, `senha`) VALUES
                                                           ('João Silva', 'joao.silva@example.com', '123.456.789-00', 'senha123'),
                                                           ('Maria Oliveira', 'maria.oliveira@example.com', '987.654.321-00', 'senha456'),
                                                           ('Pedro Santos', 'pedro.santos@example.com', '123.789.456-00', 'senha789');

-- Inserindo dados na tabela `ong`
INSERT INTO `ong` (`nome`, `email`, `CNPJ`, `endereco`, `telefone`, `senha`) VALUES
                                                                                 ('ONG Esperança', 'contato@ongesperanca.org', '12.345.678/0001-99', 'Rua da Solidariedade, 100', '(11) 1234-5678', 'senha123'),
                                                                                 ('Instituto do Bem', 'contato@institutodobem.org', '98.765.432/0001-01', 'Avenida da Ajuda, 200', '(11) 9876-5432', 'senha456');

-- Inserindo dados na tabela `tamanho`
INSERT INTO `tamanho` (`descricao`) VALUES
                                        ('P'),
                                        ('M'),
                                        ('G'),
                                        ('GG');

-- Inserindo dados na tabela `doacao`
INSERT INTO `doacao` (`dataDoacao`, `ID_doador`, `ID_ong`) VALUES
                                                               ('2024-10-20', 1, 1),
                                                               ('2024-10-21', 2, 1),
                                                               ('2024-10-22', 3, 2);

-- Inserindo dados na tabela `itemdoacao`
INSERT INTO `itemdoacao` (`qtd`, `ID_doacao`, `cod`, `ID_tamanho`) VALUES
                                                                       (5, 1, 1, 2),  -- 5 peças de categoria com código 1 (Roupas) em tamanho M
                                                                       (3, 1, 2, 1),  -- 3 peças de categoria com código 2 (Calçados) em tamanho P
                                                                       (10, 2, 3, 3); -- 10 peças de categoria com código 3 (Acessórios) em tamanho G

-- Inserindo dados na tabela `telefone`
INSERT INTO `telefone` (`telefone`, `ID_doador`) VALUES
                                                     ('(11) 91234-5678', 1),
                                                     ('(11) 99876-5432', 2),
                                                     ('(11) 93456-7890', 3);
