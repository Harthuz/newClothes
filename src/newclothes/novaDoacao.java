package newclothes;

import conexao.conexao;
import components.components;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;

public class novaDoacao extends JFrame{
    conexao con_cliente;
    components components;

    public novaDoacao(){
        // Instanciando classe components
        components = new components();

        con_cliente = new conexao();
        con_cliente.conecta();

        // Configuração da janela
        setTitle("Menu Doador");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // Usando layout nulo para setBounds

        JLabel tituloLabel = components.criarLabel("SUA DOAÇÃO CONTÉM OS ITENS:","<u>","Arial",14,Font.BOLD,20,20,300,20);
        add(tituloLabel);

        

        // Tornar a janela visível
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(420,450);
    }

    public static void main(String[] args) {
        new novaDoacao();
    }
}
