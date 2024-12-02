-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 02, 2024 at 04:53 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `bdnewclothes`
    CREATE DATABASE `bdnewclothes`;
    USE `bdnewclothes`;
--

-- --------------------------------------------------------

--
-- Table structure for table `administrador`
--

CREATE TABLE `administrador` (
                                 `email` varchar(100) NOT NULL,
                                 `senha` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `administrador`
--

INSERT INTO `administrador` (`email`, `senha`) VALUES
    ('adm@adm', '123');

-- --------------------------------------------------------

--
-- Table structure for table `categoria`
--

CREATE TABLE `categoria` (
                             `cod` int(11) NOT NULL,
                             `nome` varchar(80) NOT NULL,
                             `descricao` varchar(300) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `categoria`
--

INSERT INTO `categoria` (`cod`, `nome`, `descricao`) VALUES
                                                         (1, '', 'Camiseta'),
                                                         (2, '', 'Blusa'),
                                                         (3, '', 'Camisa'),
                                                         (4, '', 'Calça'),
                                                         (5, '', 'Short'),
                                                         (6, '', 'Saia'),
                                                         (7, '', 'Vestido'),
                                                         (8, '', 'Jaqueta'),
                                                         (9, '', 'Suéter'),
                                                         (10, '', 'Moletom'),
                                                         (11, '', 'Bermuda'),
                                                         (12, '', 'Pijama'),
                                                         (13, '', 'Meias'),
                                                         (14, '', 'Tênis'),
                                                         (15, '', 'Sandália'),
                                                         (16, '', 'Bota');

-- --------------------------------------------------------

--
-- Table structure for table `doacao`
--

CREATE TABLE `doacao` (
                          `ID_doacao` int(11) NOT NULL,
                          `dataDoacao` date DEFAULT NULL,
                          `ID_doador` int(11) NOT NULL,
                          `ID_ong` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `doacao`
--

INSERT INTO `doacao` (`ID_doacao`, `dataDoacao`, `ID_doador`, `ID_ong`) VALUES
                                                                            (1, '2025-11-12', 1, 1),
                                                                            (2, '2024-12-02', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `doador`
--

CREATE TABLE `doador` (
                          `ID_doador` int(11) NOT NULL,
                          `nome` varchar(80) NOT NULL,
                          `email` varchar(100) NOT NULL,
                          `CPF` varchar(30) NOT NULL,
                          `senha` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `doador`
--

INSERT INTO `doador` (`ID_doador`, `nome`, `email`, `CPF`, `senha`) VALUES
    (1, 'Gustavo', 'gu@gu', '123.456.789-88', '123');

-- --------------------------------------------------------

--
-- Table structure for table `itemdoacao`
--

CREATE TABLE `itemdoacao` (
                              `ID_item` int(11) NOT NULL,
                              `qtd` int(11) NOT NULL,
                              `ID_doacao` int(11) NOT NULL,
                              `cod` int(11) NOT NULL,
                              `ID_tamanho` int(11) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `itemdoacao`
--

INSERT INTO `itemdoacao` (`ID_item`, `qtd`, `ID_doacao`, `cod`, `ID_tamanho`) VALUES
    (1, 12, 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `ong`
--

CREATE TABLE `ong` (
                       `ID_ong` int(11) NOT NULL,
                       `nome` varchar(80) NOT NULL,
                       `email` varchar(100) NOT NULL,
                       `CNPJ` varchar(30) NOT NULL,
                       `endereco` varchar(100) NOT NULL,
                       `telefone` varchar(20) NOT NULL,
                       `senha` varchar(100) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `ong`
--

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
                                                                                           (20, 'Centro de Inclusão Digital', 'inclusao@centro.org', '98.765.432/0001-28', 'Rua Digital, 987, Vila da Tecnologia', '(11) 79012-7654', 'senhaSegura20'),
                                                                                           (21, 'doeRoupas', 'ong@ong', '24.143.432/4343-24', 'Rua da costa', '(12) 31231-2321', '123');

-- --------------------------------------------------------

--
-- Table structure for table `tamanho`
--

CREATE TABLE `tamanho` (
                           `ID_tamanho` int(11) NOT NULL,
                           `descricao` varchar(90) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `tamanho`
--

INSERT INTO `tamanho` (`ID_tamanho`, `descricao`) VALUES
                                                      (1, 'PP'),
                                                      (2, 'P'),
                                                      (3, 'M'),
                                                      (4, 'G'),
                                                      (5, 'GG'),
                                                      (6, 'XG'),
                                                      (7, 'XXG');

-- --------------------------------------------------------

--
-- Table structure for table `telefone`
--

CREATE TABLE `telefone` (
                            `telefone` varchar(30) NOT NULL,
                            `ID_doador` int(11) NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `categoria`
--
ALTER TABLE `categoria`
    ADD PRIMARY KEY (`cod`);

--
-- Indexes for table `doacao`
--
ALTER TABLE `doacao`
    ADD PRIMARY KEY (`ID_doacao`),
  ADD KEY `ID_doador` (`ID_doador`),
  ADD KEY `ID_ong` (`ID_ong`);

--
-- Indexes for table `doador`
--
ALTER TABLE `doador`
    ADD PRIMARY KEY (`ID_doador`);

--
-- Indexes for table `itemdoacao`
--
ALTER TABLE `itemdoacao`
    ADD PRIMARY KEY (`ID_item`),
  ADD KEY `ID_doacao` (`ID_doacao`),
  ADD KEY `cod` (`cod`),
  ADD KEY `ID_tamanho` (`ID_tamanho`);

--
-- Indexes for table `ong`
--
ALTER TABLE `ong`
    ADD PRIMARY KEY (`ID_ong`);

--
-- Indexes for table `tamanho`
--
ALTER TABLE `tamanho`
    ADD PRIMARY KEY (`ID_tamanho`);

--
-- Indexes for table `telefone`
--
ALTER TABLE `telefone`
    ADD KEY `ID_doador` (`ID_doador`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `categoria`
--
ALTER TABLE `categoria`
    MODIFY `cod` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `doacao`
--
ALTER TABLE `doacao`
    MODIFY `ID_doacao` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `doador`
--
ALTER TABLE `doador`
    MODIFY `ID_doador` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `itemdoacao`
--
ALTER TABLE `itemdoacao`
    MODIFY `ID_item` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `ong`
--
ALTER TABLE `ong`
    MODIFY `ID_ong` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=22;

--
-- AUTO_INCREMENT for table `tamanho`
--
ALTER TABLE `tamanho`
    MODIFY `ID_tamanho` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
