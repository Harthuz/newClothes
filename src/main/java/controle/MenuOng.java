package controle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import conexao.Conexao;

import java.awt.*;

public class MenuOng extends JFrame {
    Conexao con_cliente;

    public MenuOng() {
        super("Menu ONG");
        Container tela = getContentPane();

        con_cliente = new Conexao();
        con_cliente.conecta();

        tela.setLayout(null); // Define o layout como null

        // Cria a barra de menu (JMenuBar)
        JMenuBar menuBar = new JMenuBar();
        JMenu menuAjuda = new JMenu("MENU");

        JMenuItem menuItemSobre = new JMenuItem("DESENVOLVEDORES");
        JMenuItem menuItemSair = new JMenuItem("SAIR");

        // Adiciona o item "Sobre" ao menu "Ajuda"
        menuItemSobre.addActionListener(e ->
                JOptionPane.showMessageDialog(null, "New Clothes - Versão 1.0\n" +
                        "\n" +
                        "Desenvolvido por:\n" +
                        "\n" +
                        "Erick Ferreira Lima\n" +
                        "Gustavo Rodrigues\n" +
                        "Hernandes Arthur\n" +
                        "\nAlunos da ETEC Zona Leste 2º DS-AMS")
        );

        // Adiciona ação para o botão "Sair"
        menuItemSair.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Você realmente deseja sair?", "Confirmar Sair", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); // Fecha a janela
            }
        });

        menuAjuda.add(menuItemSobre);
        menuAjuda.add(menuItemSair);
        menuBar.add(menuAjuda);
        setJMenuBar(menuBar); // Adiciona a barra de menu à janela

        // Cria a barra de ferramentas (JToolBar)
        JToolBar toolBar = new JToolBar();
        JButton botaoVoltar = new JButton("Voltar");

        // Ação para o botão "voltar"
        botaoVoltar.addActionListener(e -> {
            // Lógica de atualização (pode ser adaptada para sua necessidade)
            TelaLogin telaLogin = new TelaLogin();
            dispose();
        });

        // Adiciona o botão à barra de ferramentas
        toolBar.add(botaoVoltar);
        toolBar.setBounds(0, 0, 600, 30); // Aumenta a posição e tamanho da barra de ferramentas
        tela.add(toolBar); // Adiciona a barra de ferramentas à tela

        // Criação da tabela
        String[] colunas = {"ID", "Nome", "Quantidade", "Data da Doação"};
        DefaultTableModel modeloTabela = new DefaultTableModel(colunas, 0);

        // Adicionando algumas linhas de exemplo
        modeloTabela.addRow(new Object[]{1, "Camiseta", 10, "2024-01-01"});
        modeloTabela.addRow(new Object[]{2, "Calça", 5, "2024-02-01"});
        modeloTabela.addRow(new Object[]{3, "Jaqueta", 2, "2024-03-01"});
        modeloTabela.addRow(new Object[]{4, "Sapatilha", 8, "2024-04-01"});
        modeloTabela.addRow(new Object[]{5, "Manto", 3, "2024-05-01"});

        JTable tabela = new JTable(modeloTabela);

        // Adiciona a tabela a um JScrollPane
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(50, 50, 500, 200); // Aumenta a posição e tamanho do JScrollPane
        tela.add(scrollPane); // Adiciona o JScrollPane à tela

        // Configura a janela principal
        setSize(600, 400); // Aumenta o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setVisible(true);
    }
}
