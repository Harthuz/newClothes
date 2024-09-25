package controle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import conexao.Conexao;

import java.awt.*;

public class MenuDoador extends JFrame {
    Conexao con_cliente;

    public MenuDoador() {
        super("Menu Doador");

        con_cliente = new Conexao();
        con_cliente.conecta();

        Container tela = getContentPane();
        tela.setLayout(null); // Define o layout como null

        // Cria a barra de menu (JMenuBar)
        JMenuBar menuBar = new JMenuBar();
        JMenu menuAcao = new JMenu("MENU");

        JMenuItem menuItemFazerDoacao = new JMenuItem("FAZER DOAÇÃO");
        JMenuItem menuItemExcluirDoacao = new JMenuItem("EXCLUIR DOAÇÃO");
        JMenuItem menuItemAlterarDoacao = new JMenuItem("ALTERAR DOAÇÃO");
        JMenuItem menuItemSobre = new JMenuItem("DESENVOLVEDORES");
        JMenuItem menuItemSair = new JMenuItem("SAIR");

        // Ação para "Fazer Doação"
        menuItemFazerDoacao.addActionListener(e -> {
            JFrame novaJanela = new JFrame("Fazer Doação");
            novaJanela.setSize(300, 200);
            novaJanela.setVisible(true);
            novaJanela.setLocationRelativeTo(null);
        });

        // Ação para "Excluir Doação"
        menuItemExcluirDoacao.addActionListener(e -> {
            JFrame novaJanela = new JFrame("Excluir Doação");
            novaJanela.setSize(300, 200);
            novaJanela.setVisible(true);
            novaJanela.setLocationRelativeTo(null);
        });

        // Ação para "Alterar Doação"
        menuItemAlterarDoacao.addActionListener(e -> {
            JFrame novaJanela = new JFrame("Alterar Doação");
            novaJanela.setSize(300, 200);
            novaJanela.setVisible(true);
            novaJanela.setLocationRelativeTo(null);
        });

        // Ação para "Sobre"
        menuItemSobre.addActionListener(e ->
                JOptionPane.showMessageDialog(null, "New Clothes - Versão 1.0\n\nDesenvolvido por:\n\nErick Ferreira Lima\nGustavo Rodrigues\nHernandes Arthur\n\nAlunos da ETEC Zona Leste 2º DS-AMS")
        );

        // Ação para "Sair"
        menuItemSair.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(null, "Você realmente deseja sair?", "Confirmar Sair", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                dispose(); // Fecha a janela
            }
        });

        menuAcao.add(menuItemFazerDoacao);
        menuAcao.add(menuItemExcluirDoacao);
        menuAcao.add(menuItemAlterarDoacao);
        menuAcao.add(menuItemSobre);
        menuAcao.add(menuItemSair);
        menuBar.add(menuAcao);
        setJMenuBar(menuBar); // Adiciona a barra de menu à janela

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
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(50, 50, 500, 200); // Posição e tamanho do JScrollPane
        tela.add(scrollPane); // Adiciona o JScrollPane à tela

        // Configura a janela principal
        setSize(600, 400); // Aumenta o tamanho da janela
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        setVisible(true);
    }


}
