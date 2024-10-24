package newclothes;

import conexao.conexao;
import components.components;
import javax.swing.*;
import java.util.Date;
import java.awt.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class menuDoador extends JFrame {
    conexao con_cliente;
    components components;

    public class variavelGlobal {
        // Variável global estática
        public static int idDoador = 23;
    }

    public menuDoador(){
        // Instanciando classe components
        components = new components();

        con_cliente = new conexao();
        con_cliente.conecta();

        // Configuração da janela
        setTitle("Menu Doador");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // Usando layout nulo para setBounds

        // Label Fazer Doação
        JLabel fazerDoacaoLabel = components.criarLabel("FAZER DOAÇÃO", "<u>", "Arial", 14, Font.BOLD, 20, 20, 150, 30);
        add(fazerDoacaoLabel);

        // Botão Fazer Doação
        JButton novaDoacaoButton = components.criarBotao("Nova Doação","<b>","#8C3A1C","Arial",Font.PLAIN,16,Color.WHITE,20,50,150,30);
        add(novaDoacaoButton);

        // Adiciona ActionListener ao botão
        novaDoacaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {                
                new TelaDoacao();
                dispose();
            }
        });



        // Label Doações
        JLabel doacoesLabel = components.criarLabel("DOAÇÃO", "<u>", "Arial", 14, Font.BOLD, 20, 90, 150, 30);
        add(doacoesLabel);

        // Botão Doações
        JButton doacoesButton = components.criarBotao("Doações","<b>","#8C3A1C","Arial",Font.PLAIN,16,Color.WHITE,20,120,150,30);
        add(doacoesButton);
        doacoesButton.addActionListener(e ->{
            new listarDoacao();
            dispose();
        });


        // Tornar a janela visível
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(420,450);
    }

    public static void main(String[] args) {
        new menuDoador();
    }
}
