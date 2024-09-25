package controle;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import conexao.Conexao;
import java.text.ParseException;

/**
 *
 * @author FATEC ZONA LESTE
 */
public class TelaPrincipal extends JFrame {
    Conexao con_cliente;

    public TelaPrincipal() {
        super("Conexão Java MySQL");
        Container tela = getContentPane();
        tela.setLayout(null);

        con_cliente = new Conexao(); // inicialização do objeto
        con_cliente.conecta(); // chama o método que conecta


        setSize(715, 520);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}