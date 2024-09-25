package controle;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuDoador extends JFrame {

    private JPanel contentPanel;

    public MenuDoador() {
        super("Menu Doador");
        Container tela = getContentPane();
        tela.setLayout(null); // Define o layout como null

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

    public static void main(String[] args) {
        new MenuDoador();
    }
}
