package newclothes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import conexao.conexao;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class menuONG extends JFrame {
    // Instânciando o banco de dados
    conexao con_cliente;

    public menuONG(int id) {
        // Configurações do JFrame
        setTitle("ONG");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Abrindo o banco
        con_cliente = new conexao();
        con_cliente.conecta();

        // Criando painel principal com layout nulo
        JPanel panel = new JPanel();
        panel.setBackground(Color.decode("#F2F2F2")); // Cor de fundo
        panel.setLayout(null);  // Desabilitando o layout manager


        // Header com layout nulo para centralizar manualmente
        JPanel header = new JPanel();
        header.setBackground(Color.decode("#553428"));
        header.setBounds(0, 0, 500, 50);  // Definindo tamanho e posição do header
        header.setLayout(null);  // Layout nulo para o header também

        JLabel headerTitle = new JLabel("DOAÇÕES");
        headerTitle.setFont(new Font("Arial", Font.BOLD, 25));
        headerTitle.setForeground(Color.WHITE);
        headerTitle.setBounds(175, 10, 150, 30);  // Definindo posição e tamanho do título manualmente

        header.add(headerTitle);  // Adicionando o título ao header
        panel.add(header);


        // Criando a barra de ferramentas (JToolBar)
        JToolBar toolBar = new JToolBar();
        toolBar.setBackground(new Color(0x391F15)); // Usando a cor hexadecimal
        toolBar.setBounds(0, 50, 500, 30);

        // Criando o menu e seus itens
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        // Item "Desenvolvedores"
        JMenuItem devItem = new JMenuItem("Desenvolvedores");
        devItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                Desenvolvedores desenvolvedores = new Desenvolvedores(null);
                desenvolvedores.setVisible(true);
            }
        });
        menu.add(devItem);

        // Item "Sair"
        JMenuItem exitItem = new JMenuItem("Sair");
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                login login = new login();
                login.setVisible(true);
                dispose();
            }
        });
        menu.add(exitItem);

        menuBar.add(menu);
        toolBar.add(menuBar);
        panel.add(toolBar);


        // Criando Titulo "Doações" pra tabela
        JLabel tabelaJlabel = new JLabel("<html><u>DOAÇÕES</u></html>");
        tabelaJlabel.setBounds(30,70,100,100);
        panel.add(tabelaJlabel);
        tabelaJlabel.setFont(new Font("Arial", Font.BOLD, 15));
        tabelaJlabel.setForeground(Color.BLACK);

        // Pegando os dados do banco de dados, para usar na tabela de doações
        try {
            String query = "SELECT * FROM doacao WHERE ID_ong = "  + id;
            con_cliente.executaSQL(query);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao puxar os dados de doações: " + e.getMessage());
        }

        // Montando a tabela com os dados do banco
        String[] colunas = {"ID", "Doador", "Data de Envio"};
        DefaultTableModel modelo = new DefaultTableModel(null, colunas);
        JTable tabela = new JTable(modelo);

        try{
            while (con_cliente.resultset.next()) {
                Object[] linha = {
                        con_cliente.resultset.getInt("ID_doacao"),
                        con_cliente.resultset.getString("ID_doador"),
                        con_cliente.resultset.getDate("dataDoacao") // Você pode formatar a data como necessário
                };
                modelo.addRow(linha);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro ao passar os dados do banco de dados para a tabela: " + e.getMessage());
        }

        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(20, 160, 440, 200); // Definindo a posição e tamanho do JScrollPane
        panel.add(scrollPane);

        // Exibindo na tela
        add(panel);  // Adicionando o painel ao JFrame
        setVisible(true);  // Tornando o JFrame visível
    }
}
