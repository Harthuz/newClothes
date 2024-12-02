package newclothes;

import conexao.conexao;
import components.components;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class menuDoador extends JFrame {
    conexao con_cliente;
    components components;


    public menuDoador(int id){
        // Instanciando classe components
        components = new components();

        con_cliente = new conexao();
        con_cliente.conecta();

        int idDoadorMenuDoador = id;

        // Configuração da janela
        setTitle("Menu Doador");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // Usando layout nulo para setBounds
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/newclothes/newclothesicon.png")).getImage());


        // Header com layout nulo para centralizar manualmente
        JPanel header = new JPanel();
        header.setBackground(Color.decode("#553428"));
        header.setBounds(0, 0, 500, 50);  // Definindo tamanho e posição do header
        header.setLayout(null);  // Layout nulo para o header também

        JLabel headerTitle = new JLabel("DOADOR");
        headerTitle.setFont(new Font("Arial", Font.BOLD, 25));
        headerTitle.setForeground(Color.WHITE);
        headerTitle.setBounds(175, 10, 150, 30);  // Definindo posição e tamanho do título manualmente

        header.add(headerTitle);  // Adicionando o título ao header
        add(header);

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

        // Botão "Fazer doação"
        JButton btnFazerDoacao = new JButton("Fazer Doação");
        btnFazerDoacao.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LocalDate dataAtual = getDataAtual();

                try {
                    String sqlCriarDoacao = "INSERT INTO doacao (dataDoacao, ID_doador, ID_ong) VALUES ('"+dataAtual+"','"+idDoadorMenuDoador+"','"+1+"')";
                    int rowsAffectedsqlCriarDoacao = con_cliente.statement.executeUpdate(sqlCriarDoacao);

                    // Verifica se a inserção foi bem-sucedida
                    if (rowsAffectedsqlCriarDoacao > 0) {

                        String sqlUltimaDoacao = "SELECT ID_doacao FROM doacao ORDER BY dataDoacao DESC, ID_doacao DESC LIMIT 1";
                        ResultSet ResultSetsqlUltimaDoacao = con_cliente.statement.executeQuery(sqlUltimaDoacao);

                        if (ResultSetsqlUltimaDoacao.next()) {
                            int idUltimaDoacao = ResultSetsqlUltimaDoacao.getInt("ID_doacao");
                            SwingUtilities.invokeLater(() -> new telaDoacao(idUltimaDoacao, idDoadorMenuDoador).setVisible(true));
                            dispose();
                            System.out.println("Doação criada com sucesso!");
                        } else {

                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Falha ao criar a doação. Nenhuma linha afetada.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException exp) {
                    JOptionPane.showMessageDialog(null, "Erro ao criar nova doação: "+exp, "Erro", JOptionPane.ERROR_MESSAGE);
                }            }
        });
        toolBar.add(btnFazerDoacao);

        // Botão "Doações"
        JButton btnDoacoes = new JButton("Doações");
        btnDoacoes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new listarDoacao(idDoadorMenuDoador);
                dispose();
            }
        });
        toolBar.add(btnDoacoes);

        add(toolBar);

        // Label Fazer Doação
        JLabel fazerDoacaoLabel = components.criarLabel("FAZER DOAÇÃO", "<u>", "Arial", 14, Font.BOLD, 20, 90, 150, 30);
        add(fazerDoacaoLabel);

        // Botão Fazer Doação
        JButton novaDoacaoButton = components.criarBotao("Nova Doação","<b>","#8C3A1C","Arial",Font.PLAIN,16,Color.WHITE,20,120,150,30);
        add(novaDoacaoButton);

        // Adiciona ActionListener ao botão
        novaDoacaoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LocalDate dataAtual = getDataAtual();

                try {
                    String sqlCriarDoacao = "INSERT INTO doacao (dataDoacao, ID_doador, ID_ong) VALUES ('"+dataAtual+"','"+idDoadorMenuDoador+"','"+1+"')";
                    int rowsAffectedsqlCriarDoacao = con_cliente.statement.executeUpdate(sqlCriarDoacao);

                    // Verifica se a inserção foi bem-sucedida
                    if (rowsAffectedsqlCriarDoacao > 0) {

                        String sqlUltimaDoacao = "SELECT ID_doacao FROM doacao ORDER BY dataDoacao DESC, ID_doacao DESC LIMIT 1";
                        ResultSet ResultSetsqlUltimaDoacao = con_cliente.statement.executeQuery(sqlUltimaDoacao);

                        if (ResultSetsqlUltimaDoacao.next()) {
                            int idUltimaDoacao = ResultSetsqlUltimaDoacao.getInt("ID_doacao");
                            SwingUtilities.invokeLater(() -> new telaDoacao(idUltimaDoacao, idDoadorMenuDoador).setVisible(true));
                            dispose();
                            System.out.println("Doação criada com sucesso!");
                        } else {
                            
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Falha ao criar a doação. Nenhuma linha afetada.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException exp) {
                    JOptionPane.showMessageDialog(null, "Erro ao criar nova doação: "+exp, "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Label Doações
        JLabel doacoesLabel = components.criarLabel("DOAÇÃO", "<u>", "Arial", 14, Font.BOLD, 20, 160, 150, 30);
        add(doacoesLabel);

        // Botão Doações
        JButton doacoesButton = components.criarBotao("Doações","<b>","#8C3A1C","Arial",Font.PLAIN,16,Color.WHITE,20,190,150,30);
        add(doacoesButton);
        doacoesButton.addActionListener(e ->{
            new listarDoacao(idDoadorMenuDoador);
            dispose();
        });


        // Tornar a janela visível
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(500,500 );
    }

    // Função que retorna apenas a data atual
    public static LocalDate getDataAtual() {
        LocalDateTime agora = LocalDateTime.now(); // Obtém a data e hora atuais
        return agora.toLocalDate(); // Extrai apenas a data
    }
}
