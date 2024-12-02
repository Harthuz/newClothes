package newclothes;

import conexao.conexao;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class doacoesADM extends JFrame {
    conexao con_cliente;
    JTable doacoesTable; // Defina a tabela de doações como um campo da classe
    DefaultTableModel tableModel; // Adicione um modelo de tabela

    JTextField codigoJTextField;
    JTextField dataDoacaoJTextField;
    JTextField idDoadorJTextField;
    JTextField idOngJTextField;
    JButton primeiro, anterior, proximo, ultimo, gravar, novo, excluir, alterar;

    public doacoesADM() {
        super("CRUD Doações");
        setSize(700, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a tela
        setResizable(false);

        con_cliente = new conexao();
        con_cliente.conecta();

        Container tela = getContentPane();
        tela.setLayout(null);
        tela.setBackground(new Color(242, 242, 242));  // Cor de fundo

        Font fontePadrao = new Font("Verdana", Font.BOLD, 14);
        Font fonteNegrito = new Font("Verdana", Font.BOLD, 14);

        // Labels
        JLabel codigoJLabel = new JLabel("ID Doação:");
        codigoJLabel.setBounds(20, 20, 100, 25);
        codigoJLabel.setFont(fonteNegrito);
        codigoJLabel.setForeground(new Color(0, 51, 102));
        tela.add(codigoJLabel);

        codigoJTextField = new JTextField();
        codigoJTextField.setBounds(120, 20, 100, 20);
        codigoJTextField.setFont(fontePadrao);
        tela.add(codigoJTextField);

        JLabel dataDoacaoJLabel = new JLabel("Data Doação:");
        dataDoacaoJLabel.setBounds(20, 50, 100, 20);
        dataDoacaoJLabel.setFont(fonteNegrito);
        dataDoacaoJLabel.setForeground(new Color(0, 51, 102));
        tela.add(dataDoacaoJLabel);

        dataDoacaoJTextField = new JTextField();
        dataDoacaoJTextField.setBounds(120, 50, 200, 25);
        dataDoacaoJTextField.setFont(fontePadrao);
        tela.add(dataDoacaoJTextField);

        JLabel idDoadorJLabel = new JLabel("ID Doador:");
        idDoadorJLabel.setBounds(20, 80, 100, 20);
        idDoadorJLabel.setFont(fonteNegrito);
        idDoadorJLabel.setForeground(new Color(0, 51, 102));
        tela.add(idDoadorJLabel);

        idDoadorJTextField = new JTextField();
        idDoadorJTextField.setBounds(120, 80, 200, 25);
        idDoadorJTextField.setFont(fontePadrao);
        tela.add(idDoadorJTextField);

        JLabel idOngJLabel = new JLabel("ID Ong:");
        idOngJLabel.setBounds(20, 110, 100, 20);
        idOngJLabel.setFont(fonteNegrito);
        idOngJLabel.setForeground(new Color(0, 51, 102));
        tela.add(idOngJLabel);

        idOngJTextField = new JTextField();
        idOngJTextField.setBounds(120, 110, 200, 25);
        idOngJTextField.setFont(fontePadrao);
        tela.add(idOngJTextField);

        // Botões de navegação
        primeiro = new JButton("Primeiro");
        anterior = new JButton("Anterior");
        proximo = new JButton("Próximo");
        ultimo = new JButton("Último");

        primeiro.setBounds(20, 170, 100, 20);
        anterior.setBounds(120, 170, 100, 20);
        proximo.setBounds(220, 170, 100, 20);
        ultimo.setBounds(320, 170, 100, 20);

        primeiro.setFont(fontePadrao);
        anterior.setFont(fontePadrao);
        proximo.setFont(fontePadrao);
        ultimo.setFont(fontePadrao);

        primeiro.setBackground(new Color(57, 31, 21));
        anterior.setBackground(new Color(57, 31, 21));
        proximo.setBackground(new Color(57, 31, 21));
        ultimo.setBackground(new Color(57, 31, 21));

        primeiro.setForeground(Color.WHITE);
        anterior.setForeground(Color.WHITE);
        proximo.setForeground(Color.WHITE);
        ultimo.setForeground(Color.WHITE);

        tela.add(primeiro);
        tela.add(anterior);
        tela.add(proximo);
        tela.add(ultimo);

        novo = new JButton("Novo Registro");
        gravar = new JButton("Gravar");
        excluir = new JButton("Excluir");
        alterar = new JButton("Alterar");

        novo.setBounds(20, 420, 150, 20);
        gravar.setBounds(170, 420, 100, 20);
        excluir.setBounds(280, 420, 100, 20);
        alterar.setBounds(380, 420, 100, 20);

        novo.setFont(fontePadrao);
        gravar.setFont(fontePadrao);
        excluir.setFont(fontePadrao);
        alterar.setFont(fontePadrao);

        novo.setBackground(new Color(57, 31, 21));
        gravar.setBackground(new Color(57, 31, 21));
        excluir.setBackground(new Color(57, 31, 21));
        alterar.setBackground(new Color(57, 31, 21));

        novo.setForeground(Color.WHITE);
        gravar.setForeground(Color.WHITE);
        excluir.setForeground(Color.WHITE);
        alterar.setForeground(Color.WHITE);

        JButton sair = new JButton("Sair");
        sair.setBounds(500, 420, 100, 20);
        sair.setFont(new Font("Verdana", Font.PLAIN, 14));
        sair.setBackground(new Color(255, 102, 102));  // Cor do fundo (Vermelho claro)
        sair.setForeground(Color.WHITE);  // Cor do texto (Branco)
        tela.add(sair);
        
        // Adiciona um ActionListener para o botão "Sair"
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fecha a janela
                MenuAdmin MenuAdmin = new MenuAdmin();
                MenuAdmin.setVisible(true); // Mostra a janela modal;
                dispose();// TODO add your handling code here:
            }
        });


        tela.add(novo);
        tela.add(gravar);
        tela.add(excluir);
        tela.add(alterar);

        con_cliente.executaSQL("SELECT * FROM doacao ORDER BY ID_doacao");
        preencherTabela();
        posicionarRegistro();

        // Ações para os botões de navegação
        primeiro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    con_cliente.resultset.first();
                    mostrar_dados();
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Não foi possível acessar o primeiro registro");
                }
            }
        });

        anterior.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    con_cliente.resultset.previous();
                    mostrar_dados();
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Não foi possível acessar o registro anterior");
                }
            }
        });

        proximo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    con_cliente.resultset.next();
                    mostrar_dados();
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Não foi possível acessar o próximo registro");
                }
            }
        });

        ultimo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    con_cliente.resultset.last();
                    mostrar_dados();
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Não foi possível acessar o último registro");
                }
            }
        });

        // Função para gravar novos registros de doação
        gravar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dataDoacao = dataDoacaoJTextField.getText();
                String idDoador = idDoadorJTextField.getText();
                String idOng = idOngJTextField.getText();

                try {
                    String insert_sql = "INSERT INTO doacao (dataDoacao, ID_doador, ID_ong) VALUES ('"
                            + dataDoacao + "', '" + idDoador + "', '" + idOng + "')";

                    con_cliente.statement.executeUpdate(insert_sql);
                    JOptionPane.showMessageDialog(null, "Gravação realizada com sucesso!!!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);

                    con_cliente.executaSQL("SELECT * FROM doacao ORDER BY ID_doacao");
                    preencherTabela();
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "\nErro na gravação: \n" + erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Excluir registro
        excluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o registro?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        String sql = "DELETE FROM doacao WHERE ID_doacao = " + codigoJTextField.getText();
                        int excluiu = con_cliente.statement.executeUpdate(sql);

                        if (excluiu == 1) {
                            JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                            con_cliente.executaSQL("SELECT * FROM doacao ORDER BY ID_doacao");
                            preencherTabela();
                            con_cliente.resultset.first();
                            mostrar_dados();
                        }
                    }
                } catch (SQLException excecao) {
                    JOptionPane.showMessageDialog(null, "Erro na exclusão: " + excecao, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        // Alterar registro
        alterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String dataDoacao = dataDoacaoJTextField.getText();
                String idDoador = idDoadorJTextField.getText();
                String idOng = idOngJTextField.getText();
                String sql;

                try {
                    sql = "UPDATE doacao SET dataDoacao = '" + dataDoacao
                            + "', ID_doador = '" + idDoador + "', ID_ong = '" + idOng
                            + "' WHERE ID_doacao = " + codigoJTextField.getText();

                    con_cliente.statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, "Alteração realizada com sucesso!!!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);

                    con_cliente.executaSQL("SELECT * FROM doacao ORDER BY ID_doacao");
                    preencherTabela();
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Erro na alteração: \n" + erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        novo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                codigoJTextField.setText("");
                dataDoacaoJTextField.setText("");
                idDoadorJTextField.setText("");
                idOngJTextField.setText("");

                codigoJTextField.requestFocus();
            }
        });

        preencherTabela(); // Preencher a tabela ao inicializar
        setSize(700, 500);
        setVisible(true);
    }

    // Método para preencher a tabela de doações
    public void preencherTabela() {
        try {
            String[] colunas = {"ID", "Data de Doação", "ID Doador", "ID Ong"};
            tableModel = new DefaultTableModel(colunas, 0); // Define colunas no modelo da tabela

            doacoesTable = new JTable(tableModel);
            JScrollPane scrollPane = new JScrollPane(doacoesTable);
            scrollPane.setBounds(20, 220, 500, 150);
            getContentPane().add(scrollPane);

            con_cliente.resultset.beforeFirst();
            while (con_cliente.resultset.next()) {
                String[] linha = {
                        con_cliente.resultset.getString("ID_doacao"),
                        con_cliente.resultset.getString("dataDoacao"),
                        con_cliente.resultset.getString("ID_doador"),
                        con_cliente.resultset.getString("ID_ong")
                };
                tableModel.addRow(linha);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao preencher a tabela: " + e, "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Mostrar dados no formulário
    public void mostrar_dados() {
        try {
            // Acessa os dados do resultset
            String idDoacao = con_cliente.resultset.getString("ID_doacao");
            String dataDoacao = con_cliente.resultset.getString("dataDoacao");
            String idDoador = con_cliente.resultset.getString("ID_doador");
            String idOng = con_cliente.resultset.getString("ID_ong");

            // Atualiza os campos de texto com os valores obtidos
            codigoJTextField.setText("tesrte");
            dataDoacaoJTextField.setText(dataDoacao);
            idDoadorJTextField.setText(idDoador);
            idOngJTextField.setText(idOng);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao mostrar os dados: " + erro, "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void posicionarRegistro() {
        try {
            con_cliente.resultset.first();
            mostrar_dados();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível posicionar no primeiro registro: " + erro, "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
        }
    }
}
