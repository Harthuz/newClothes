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

-- Inserir categoria de camiseta
INSERT INTO categoria (descricao)
VALUES ('Camiseta');

-- Inserir categoria de blusa
INSERT INTO categoria (descricao)
VALUES ('Blusa');

-- Inserir categoria de camisa
INSERT INTO categoria (descricao)
VALUES ('Camisa');

-- Inserir categoria de calça
INSERT INTO categoria (descricao)
VALUES ('Calça');

-- Inserir categoria de short
INSERT INTO categoria (descricao)
VALUES ('Short');

-- Inserir categoria de saia
INSERT INTO categoria (descricao)
VALUES ('Saia');

-- Inserir categoria de vestido
INSERT INTO categoria (descricao)
VALUES ('Vestido');

-- Inserir categoria de jaqueta
INSERT INTO categoria (descricao)
VALUES ('Jaqueta');

-- Inserir categoria de suéter
INSERT INTO categoria (descricao)
VALUES ('Suéter');

-- Inserir categoria de moletom
INSERT INTO categoria (descricao)
VALUES ('Moletom');

-- Inserir categoria de bermuda
INSERT INTO categoria (descricao)
VALUES ('Bermuda');

-- Inserir categoria de pijama
INSERT INTO categoria (descricao)
VALUES ('Pijama');

-- Inserir categoria de meias
INSERT INTO categoria (descricao)
VALUES ('Meias');

-- Inserir categoria de tênis
INSERT INTO categoria (descricao)
VALUES ('Tênis');

-- Inserir categoria de sandália
INSERT INTO categoria (descricao)
VALUES ('Sandália');

-- Inserir categoria de bota
INSERT INTO categoria (descricao)
VALUES ('Bota');

INSERT INTO tamanho (descricao) VALUES ('PP');
INSERT INTO tamanho (descricao) VALUES ('P');
INSERT INTO tamanho (descricao) VALUES ('M');
INSERT INTO tamanho (descricao) VALUES ('G');
INSERT INTO tamanho (descricao) VALUES ('GG');
INSERT INTO tamanho (descricao) VALUES ('XG');
INSERT INTO tamanho (descricao) VALUES ('XXG');

INSERT INTO `ong` (`ID_ong`, `nome`, `email`, `CNPJ`, `endereco`, `telefone`, `senha`) VALUES
                                                                                           (1, 'Instituto da Esperança', 'contato@institutodaesperanca.com.br', '12.345.678/0001-90', 'Rua da Paz, 123, Centro', '(11) 98765-4321', 'senhaSegura1'),
                                                                                           (2, 'Associação Vida Nova', 'info@vidanova.com.br', '98.765.432/0001-10', 'Avenida do Futuro, 456, Bairro Novo', '(11) 99876-5432', 'senhaSegura2'),
                                                                                           (3, 'Projeto Cidadão', 'projeto@cidadao.org.br', '11.222.333/0001-11', 'Rua da Liberdade, 789, Cidade Velha', '(11) 97765-4321', 'senhaSegura3'),
                                                                                           (4, 'Movimento Sem Fronteiras', 'contato@seamovimento.com', '45.678.901/0001-12', 'Praça da Paz, 321, Centro Histórico', '(11) 96654-3210', 'senhaSegura4'),
                                                                                           (5, 'Fundação Amigos da Criança', 'amigos@fundacao.org', '21.321.123/0001-13', 'Rua da Amizade, 555, Vila das Flores', '(11) 95543-2109', 'senhaSegura5'),
                                                                                           (6, 'Centro de Apoio à Mulher', 'apoio@centro.org', '33.444.555/0001-14', 'Avenida das Flores, 234, Jardim das Rosas', '(11) 94432-1098', 'senhaSegura6'),
                                                                                           (7, 'Associação de Deficientes', 'associacao@deficientes.org', '55.666.777/0001-15', 'Rua da Inclusão, 789, Centro', '(11) 93321-0987', 'senhaSegura7'),
                                                                                           (8, 'Grupo de Voluntários', 'voluntarios@grupo.org', '77.888.999/0001-16', 'Avenida do Trabalho, 456, Bairro da Solidariedade', '(11) 92210-9876', 'senhaSegura8'),
                                                                                           (9, 'Associação de Saúde e Bem-Estar', 'saude@associacao.org', '99.000.111/0001-17', 'Praça do Bem, 654, Vila das Oliveiras', '(11) 91101-8765', 'senhaSegura9'),
                                                                                           (10, 'Instituto Verde e Azul', 'contato@verdeazul.org', '12.345.678/0001-18', 'Rua da Sustentabilidade, 321, Centro Ecológico', '(11) 90123-7654', 'senhaSegura10'),
                                                                                           (11, 'Fundação de Ação Social', 'acao@fundacao.org', '98.765.432/0001-19', 'Avenida da Esperança, 987, Vila Solidária', '(11) 89012-6543', 'senhaSegura11'),
                                                                                           (12, 'Movimento pela Paz', 'paz@movimento.org', '11.222.333/0001-20', 'Rua da Paz, 888, Centro da Esperança', '(11) 87901-5432', 'senhaSegura12'),
                                                                                           (13, 'Centro de Estudos e Pesquisas', 'estudos@centro.org', '45.678.901/0001-21', 'Praça do Conhecimento, 234, Bairro da Sabedoria', '(11) 86790-4321', 'senhaSegura13'),
                                                                                           (14, 'Instituto de Educação e Cultura', 'educacao@instituto.org', '21.321.123/0001-22', 'Rua da Cultura, 111, Centro Educacional', '(11) 85678-3210', 'senhaSegura14'),
                                                                                           (15, 'Grupo de Ajuda Mútua', 'ajuda@grupo.org', '33.444.555/0001-23', 'Avenida da Comunidade, 765, Vila de Ajuda', '(11) 84567-2109', 'senhaSegura15'),
                                                                                           (16, 'Associação de Jovens Empreendedores', 'jovens@associacao.org', '55.666.777/0001-24', 'Rua do Empreendedorismo, 456, Centro de Inovação', '(11) 83456-1098', 'senhaSegura16'),
                                                                                           (17, 'Instituto de Direitos Humanos', 'direitos@instituto.org', '77.888.999/0001-25', 'Praça dos Direitos, 987, Centro de Justiça', '(11) 82345-0987', 'senhaSegura17'),
                                                                                           (18, 'Movimento Ecológico', 'ecologico@movimento.org', '99.000.111/0001-26', 'Rua Verde, 654, Vila Ecológica', '(11) 81234-9876', 'senhaSegura18'),
                                                                                           (19, 'Fundação Arte e Cultura', 'arte@fundacao.org', '12.345.678/0001-27', 'Avenida da Arte, 321, Centro Cultural', '(11) 80123-8765', 'senhaSegura19'),
                                                                                           (20, 'Centro de Inclusão Digital', 'inclusao@centro.org', '98.765.432/0001-28', 'Rua Digital, 987, Vila da Tecnologia', '(11) 79012-7654', 'senhaSegura20');
COMMIT;