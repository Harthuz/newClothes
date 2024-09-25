package controle;

import javax.swing.*;

import conexao.Conexao;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
        JMenu menuAjuda = new JMenu("Ajuda");
        JMenuItem menuItemSobre = new JMenuItem("Sobre");

        // Adiciona o item "Sobre" ao menu "Ajuda"
        menuItemSobre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Mostra um diálogo simples quando "Sobre" for clicado
                JOptionPane.showMessageDialog(null, "New Clothes - Versão 1.0");
            }
        });
        menuAjuda.add(menuItemSobre);
        menuBar.add(menuAjuda);
        setJMenuBar(menuBar); // Adiciona a barra de menu à janela

        // Cria a barra de ferramentas (JToolBar)
        JToolBar toolBar = new JToolBar();
        JButton botaoVoltar = new JButton("Voltar");

        // Ação para o botão "voltar"
        botaoVoltar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Lógica de atualização (pode ser adaptada para sua necessidade)
                TelaLogin telaLogin = new TelaLogin();
                dispose();
            }
        });

        toolBar.add(botaoVoltar);
        toolBar.setBounds(0, 0, 300, 30); // Define posição e tamanho da barra de ferramentas
        tela.add(toolBar); // Adiciona a barra de ferramentas à tela

        // Cria o botão "Fazer Doação"
        JButton botaoFazerDoacao = new JButton("Fazer Doação");
        botaoFazerDoacao.setBackground(new Color(140, 58, 28)); // Marrom
        botaoFazerDoacao.setForeground(Color.WHITE);
        botaoFazerDoacao.setBounds(50, 50, 200, 30); // Posição e tamanho do botão
        botaoFazerDoacao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre uma nova janela vazia
                JFrame novaJanela = new JFrame("Fazer Doação");
                novaJanela.setSize(300, 200);
                novaJanela.setVisible(true);
                novaJanela.setLocationRelativeTo(null);;
            }
        });

        // Cria o botão "Ver Doações"
        JButton botaoVerDoacoes = new JButton("Ver Doações");
        botaoVerDoacoes.setBackground(new Color(140, 58, 28)); // Marrom
        botaoVerDoacoes.setForeground(Color.WHITE);
        botaoVerDoacoes.setBounds(50, 100, 200, 30); // Posição e tamanho do botão
        botaoVerDoacoes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Abre uma nova janela vazia
                JFrame novaJanela = new JFrame("Ver Doações");
                novaJanela.setSize(300, 200);
                novaJanela.setVisible(true);
                novaJanela.setLocationRelativeTo(null);
            }
        });

        // Adiciona os botões ao painel
        tela.add(botaoFazerDoacao);
        tela.add(botaoVerDoacoes);

        // Configura a janela principal
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
