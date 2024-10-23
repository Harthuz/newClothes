package newclothes;

import javax.swing.*;
import java.awt.*;

import components.components;
import conexao.conexao;

public class fazerDoacao extends JFrame{
    conexao con_cliente;
    components components;
    public fazerDoacao(){
        components = new components();

        con_cliente = new conexao();
        con_cliente.conecta();

        // Configuração da janela
        setTitle("Fazer Doação");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // Usando layout nulo para setBounds

        // Label Fazer Doação
        JLabel doacaoLabel = components.criarLabel("SUA DOAÇÃO CONTÉM OS ITENS:", "<u>", "Arial", 14, Font.BOLD, 20, 20, 300, 30);
        add(doacaoLabel);

        // Dados da tabela (campos id, item, tamanho, quantidade e botões de alterar e excluir)
        Object[][] dados = {
            {1, "Camiseta", "M", 10},
            {2, "Calça", "G", 5},
            {3, "Jaqueta", "P", 3},
            {3, "Jaqueta", "P", 3},
            {3, "Jaqueta", "P", 3},
            {3, "Jaqueta", "P", 3},
            {3, "Jaqueta", "P", 3},
            {3, "Jaqueta", "P", 3},
            {3, "Jaqueta", "P", 3},
            {4, "Boné", "Único", 7}
        };

        // Largura individual de cada coluna
        int[] larguras = {50, 150, 100, 100, 50, 50};

        // Criando a tabela customizada
        JPanel tabela = components.criarTabelaPanel(dados, larguras);

        // Criando um JScrollPane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(20, 70, 518, 250); // Definindo a posição e o tamanho do JScrollPane

        // Adicionando o JScrollPane à janela principal
        add(scrollPane);

        // Tornar a janela visível
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(600,450);        
    }

    public static void main(String[] args) {
        new fazerDoacao();
    }
}
