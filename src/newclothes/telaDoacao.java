package newclothes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import components.components;
import conexao.conexao;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class telaDoacao extends JFrame {
    private JTable tabela;
    private ModeloTabelaNaoEditavel modeloTabela;
    private JTextField campoDia;
    private JTextField campoMes;
    private JTextField campoAno;
    conexao con_cliente;


    public telaDoacao(int idDoacao, int idDoador) {

        setTitle("Tela de Doação");
        setSize(750, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        System.out.println(idDoacao);

        con_cliente = new conexao();
        con_cliente.conecta();

        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                try {
                    String sqlDeletaItemDoacao = "DELETE FROM itemdoacao WHERE ID_doacao = "+idDoacao;
                    int rowsAffectedsqlDeletaItemDoacao = con_cliente.statement.executeUpdate(sqlDeletaItemDoacao);

                    if (rowsAffectedsqlDeletaItemDoacao>0) {
                        System.out.println("itemdoacao referentes a doação ID:"+idDoacao+" excluidos");
                    }

                    String sqlDeletaDoacao = "DELETE FROM doacao WHERE ID_doacao = "+idDoacao;
                    int rowsAffectedsqlDeletaDoacao = con_cliente.statement.executeUpdate(sqlDeletaDoacao);

                    if (rowsAffectedsqlDeletaDoacao>0) {
                        System.out.println("Doação excluida");
                    }
                } catch (SQLException exp) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir doação: Fechar janela"+exp, "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        

        // Label TITULO
        JLabel fazerDoacaoLabel = components.criarLabel("SUA DOAÇÃO CONTÉM OS ITENS:", "<u>", "Arial", 14, Font.BOLD, 20, 20, 400, 25);
        add(fazerDoacaoLabel);

        // Modelo da tabela
        modeloTabela = new ModeloTabelaNaoEditavel(new String[]{"ID","Item", "Tamanho", "Quantidade"}, 0);
        tabela = new JTable(modeloTabela);
        tabela.getColumnModel().getColumn(0).setMinWidth(0);
        tabela.getColumnModel().getColumn(0).setMaxWidth(0);
        tabela.getColumnModel().getColumn(0).setWidth(0);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(20, 50, 550, 200);
        add(scrollPane);

        JButton botaoAdicionar = new JButton("Adicionar Novo Item");
        botaoAdicionar.setBounds(20, 270, 180, 30);
        add(botaoAdicionar);
        botaoAdicionar.addActionListener(e -> {

            JDialog modal = new JDialog(this, "Adicionar Item", true);
            modal.setSize(500, 250);
            modal.setLayout(null);

            JLabel labelQuantidade = new JLabel("Quantidade do Item:");
            labelQuantidade.setBounds(20, 20, 150, 25);
            modal.add(labelQuantidade);

            JTextField campoQuantidade = new JTextField();
            campoQuantidade.setBounds(160, 20, 100, 25);
            campoQuantidade.addKeyListener(new KeyAdapter() {
                public void keyTyped(KeyEvent e) {
                    if (!Character.isDigit(e.getKeyChar())) {
                        e.consume();
                    }
                }
            });
            modal.add(campoQuantidade);

            JLabel labelTipo = new JLabel("Tipo do Item:");
            labelTipo.setBounds(20, 60, 150, 25);
            modal.add(labelTipo);

            JComboBox<String> comboTipo = new JComboBox<>(getTiposDoBanco());
            comboTipo.setBounds(160, 60, 300, 25);
            modal.add(comboTipo);

            JLabel labelTamanho = new JLabel("Tamanho:");
            labelTamanho.setBounds(20, 100, 150, 25);
            modal.add(labelTamanho);

            JComboBox<String> comboTamanho = new JComboBox<>(getTamanhosDoBanco());
            comboTamanho.setBounds(160, 100, 100, 25);
            modal.add(comboTamanho);

            JButton botaoAdicionarItem = new JButton("Adicionar");
            botaoAdicionarItem.setBounds(100, 140, 100, 30);
            add(botaoAdicionar);
            botaoAdicionarItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                        String quantidade = campoQuantidade.getText();
                        String tipo = (String) comboTipo.getSelectedItem();
                        String tamanho = (String) comboTamanho.getSelectedItem();
                        if (!quantidade.isEmpty()) {
                            
                            try {
                                String sqlAdicionaItemDoacao = "INSERT INTO itemdoacao (qtd , ID_doacao, cod, ID_tamanho) VALUES ('"+quantidade+"', "
                                + "'"+idDoacao+"', "
                                + "(SELECT cod FROM categoria WHERE descricao = '"+tipo+"'), "
                                + "(SELECT ID_tamanho FROM tamanho WHERE descricao = '"+tamanho+"') )";
                                
                                int rowsAffectedsqlAdicionaItemDoacao = con_cliente.statement.executeUpdate(sqlAdicionaItemDoacao);
                                
                                if (rowsAffectedsqlAdicionaItemDoacao>0) {
                                    String sqlSelecionarUltimoItemDoacao = "SELECT ID_item FROM itemdoacao ORDER BY ID_item DESC LIMIT 1";
                                    ResultSet ResultSetsqlSelecionarUltimoItemDoacao = con_cliente.statement.executeQuery(sqlSelecionarUltimoItemDoacao);

                                    if(ResultSetsqlSelecionarUltimoItemDoacao.next()){
                                        int ultimoItemDoacao = ResultSetsqlSelecionarUltimoItemDoacao.getInt("ID_item");

                                        modeloTabela.addRow(new Object[]{ultimoItemDoacao,tipo, tamanho, quantidade});
                                        modal.dispose();
                                    }
                                    else{
                                        JOptionPane.showMessageDialog(null, "Erro ao consultar último itemdoacao", "Erro", JOptionPane.ERROR_MESSAGE);
                                    }
                                } else {
                                    
                                }
                            } catch (SQLException exp) {
                                JOptionPane.showMessageDialog(null, "Erro ao criar novo item doação: "+exp, "Erro", JOptionPane.ERROR_MESSAGE);

                            }


                        } else {
                            JOptionPane.showMessageDialog(modal, "Por favor, insira uma quantidade.");
                        }
                }
            });
                modal.add(botaoAdicionarItem);
                modal.setLocationRelativeTo(this);
                modal.setVisible(true);

        });

        
        JLabel labelOng = new JLabel("ONG:");
        labelOng.setBounds(400, 320, 150, 25);
        add(labelOng);

        JComboBox<String> comboOng = new JComboBox<>(getOngsDoBanco());
        comboOng.setBounds(440, 320, 200, 25);
        add(comboOng);

        JButton botaoFazerDoacao = new JButton("Fazer Doação");
        botaoFazerDoacao.setBounds(210, 270, 150, 30);
        add(botaoFazerDoacao);
        botaoFazerDoacao.addActionListener(e->{

            try {
                String sqlVerificaItensTabela = "SELECT * FROM itemdoacao WHERE ID_doacao = "+idDoacao;
                ResultSet resultSetsqlVerificaItensTabela = con_cliente.statement.executeQuery(sqlVerificaItensTabela);

                if(resultSetsqlVerificaItensTabela.next()){
                    try {
                        int campoDiaint = Integer.parseInt(campoDia.getText());
                        int campoMesint = Integer.parseInt(campoMes.getText());
                        int campoAnoint = Integer.parseInt(campoAno.getText());
        
                        if (campoDiaint < 1 || campoDiaint > 31 || campoMesint < 1 || campoMesint > 12 || campoAnoint < 1000 || campoAnoint > 9999) {
                            // Condição para datas inválidas em geral
                            JOptionPane.showMessageDialog(null, "Data inválida.");
                        } else {
                            // Verificações adicionais para meses com menos de 31 dias
                            if ((campoMesint == 4 || campoMesint == 6 || campoMesint == 9 || campoMesint == 11) && campoDiaint > 30) {
                                JOptionPane.showMessageDialog(null, "Esse mês tem apenas 30 dias.");
                            } else if (campoMesint == 2) {
                                // Verificar ano bissexto para fevereiro
                                boolean anoBissexto = (campoAnoint % 4 == 0 && (campoAnoint % 100 != 0 || campoAnoint % 400 == 0));
                                if (anoBissexto && campoDiaint > 29) {
                                    JOptionPane.showMessageDialog(null, "Fevereiro em ano bissexto tem apenas 29 dias.");
                                } else if (!anoBissexto && campoDiaint > 28) {
                                    JOptionPane.showMessageDialog(null, "Fevereiro tem apenas 28 dias.");
                                }
                            }
                            else{
                                try {
                                    String ongSelecionada = (String) comboOng.getSelectedItem();
                                    System.out.println(ongSelecionada);


                                    String dataInput = campoAnoint+"-"+campoMesint+"-"+campoDiaint;

                                    // Formatar o input da data e a data de hoje
                                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
                                    LocalDate dataInputDate = LocalDate.parse(dataInput, formatter);
                                    LocalDate dataHoje = LocalDate.now();


                                    // Comparar as datas
                                    if (dataInputDate.isBefore(dataHoje)) {
                                        JOptionPane.showMessageDialog(null, "A data da doação não pode ser anterior a hoje", "Erro", JOptionPane.ERROR_MESSAGE);
                                    } 
                                    else{
                                        String sqlAtualizaDoacao = "UPDATE doacao SET dataDoacao = '"+dataInput+"', ID_ong = (SELECT ID_ong FROM ong WHERE nome like '"+ongSelecionada+"') WHERE ID_doacao ="+idDoacao;
                                        int rowsAffectedsqlAtualizaDoacao = con_cliente.statement.executeUpdate(sqlAtualizaDoacao);
                                        if(rowsAffectedsqlAtualizaDoacao>0){
                                            JOptionPane.showMessageDialog(null, "Sua doação foi feita com sucesso", "Doação Feita", JOptionPane.INFORMATION_MESSAGE);
                                            new menuDoador(idDoador);
                                            dispose();
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(null, "Erro ao fazer nova doação: Update não enviado", "Erro", JOptionPane.ERROR_MESSAGE);
                                        }
                                    }
                                } catch (SQLException exp) {
                                    JOptionPane.showMessageDialog(null, "Erro ao fazer nova doação: "+exp, "Erro", JOptionPane.ERROR_MESSAGE);
                                }                                
                            }
                        }
                    } catch (Exception exp) {
                        JOptionPane.showMessageDialog(null, "Campo data não pode ser vazio", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null, "Adicione pelo menos um item na tabela antes de fazer doação", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                
            } catch (SQLException exp) {
                JOptionPane.showMessageDialog(null, "Erro ao verificar itens da tabela: "+exp, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Criar botão de sair
        JButton sairButton = new JButton("Sair");
        sairButton.setBounds(370, 270, 150, 30); // Definindo posição e tamanho do botão
        add(sairButton);

        // Adicionar evento para o botão de sair
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuDoador menuDoador = new menuDoador(idDoador);
                menuDoador.setVisible(true);
                dispose();
            }
        });

        // Botão para Excluir Item
        JButton botaoExcluir = new JButton("Excluir Item");
        botaoExcluir.setBounds(590, 50, 130, 30);
        botaoExcluir.addActionListener(e -> excluirItem());
        add(botaoExcluir);

        // Botão para Alterar Item
        JButton botaoAlterar = new JButton("Alterar Item");
        botaoAlterar.setBounds(590, 100, 130, 30);
        botaoAlterar.addActionListener(e -> alterarItem());
        add(botaoAlterar);

        // Labels e campos para data
        JLabel labelData = new JLabel("Data:");
        labelData.setBounds(20, 320, 50, 25);
        add(labelData);

        JLabel labelDia = new JLabel("Dia:");
        labelDia.setBounds(70, 320, 30, 25);
        add(labelDia);

        campoDia = new JTextField();
        campoDia.setBounds(100, 320, 40, 25);
        campoDia.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || campoDia.getText().length() >= 2) {
                    e.consume();
                }
            }
        });
        add(campoDia);

        JLabel labelMes = new JLabel("Mês:");
        labelMes.setBounds(150, 320, 30, 25);
        add(labelMes);

        campoMes = new JTextField();
        campoMes.setBounds(180, 320, 40, 25);
        campoMes.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || campoMes.getText().length() >= 2) {
                    e.consume();
                }
            }
        });
        add(campoMes);

        JLabel labelAno = new JLabel("Ano:");
        labelAno.setBounds(230, 320, 30, 25);
        add(labelAno);

        campoAno = new JTextField();
        campoAno.setBounds(260, 320, 60, 25);
        campoAno.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || campoAno.getText().length() >= 4) {
                    e.consume();
                }
            }
        });
        add(campoAno);
    }

    // Método para buscar tipos do banco de dados
    private String[] getTiposDoBanco() {
        ArrayList<String> tipos = new ArrayList<>();

        try{
            String query = "SELECT descricao FROM categoria"; // Consulta SQL
            ResultSet resultSet = con_cliente.statement.executeQuery(query);

            while (resultSet.next()) {
                tipos.add(resultSet.getString("descricao")); // Adiciona a descrição à lista
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tipos.toArray(new String[0]); // Retorna o array de tipos
    }

    // Função que retorna apenas a data atual
    public static LocalDate getDataAtual() {
        LocalDateTime agora = LocalDateTime.now(); // Obtém a data e hora atuais
        return agora.toLocalDate(); // Extrai apenas a data
    }

    private void excluirItem() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            // Mensagem de confirmação
            int confirm = JOptionPane.showConfirmDialog(this, 
                    "Você deseja excluir esse item?", 
                    "Confirmação de Exclusão", 
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
    
            // Verifica se o usuário clicou em "Sim"
            if (confirm == JOptionPane.YES_OPTION) {
                // Acessar o valor da coluna ID na linha selecionada
                int idItemSelecionado = (int) modeloTabela.getValueAt(selectedRow, 0); // Supondo que a coluna ID seja a primeira (índice 0)
                
                try {
                    String sqlDeletarItemDoacao = "DELETE FROM itemdoacao WHERE ID_item = "+idItemSelecionado;
                    int rowsAffectedsqlDeletarItemDoacao = con_cliente.statement.executeUpdate(sqlDeletarItemDoacao);

                    if(rowsAffectedsqlDeletarItemDoacao>0){
                        // Remover a linha do modelo da tabela
                        modeloTabela.removeRow(selectedRow);
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "Erro ao excluir itemdoacao: Exclusão não realizada", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (SQLException exp) {
                    JOptionPane.showMessageDialog(null, "Erro ao excluir itemdoacao: "+exp, "Erro", JOptionPane.ERROR_MESSAGE);
                }
                
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item para excluir.");
        }
    }
    

    private void alterarItem() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            // Recuperar os dados da linha selecionada
            int idItemDoacao = (int) modeloTabela.getValueAt(selectedRow, 0);
            String tipo = (String) modeloTabela.getValueAt(selectedRow, 1);
            String tamanho = (String) modeloTabela.getValueAt(selectedRow, 2);
            String quantidade = (String) modeloTabela.getValueAt(selectedRow, 3);
    
            // Abrir modal para alterar o item
            JDialog modal = new JDialog(this, "Alterar Item", true);
            modal.setSize(300, 250);
            modal.setLayout(null);
    
            JLabel labelQuantidade = new JLabel("Quantidade do Item:");
            labelQuantidade.setBounds(20, 20, 150, 25);
            modal.add(labelQuantidade);
    
            JTextField campoQuantidade = new JTextField(quantidade);
            campoQuantidade.setBounds(160, 20, 100, 25);
            modal.add(campoQuantidade);
    
            JLabel labelTipo = new JLabel("Tipo do Item:");
            labelTipo.setBounds(20, 60, 150, 25);
            modal.add(labelTipo);
    
            JComboBox<String> comboTipo = new JComboBox<>(getTiposDoBanco());
            comboTipo.setSelectedItem(tipo);
            comboTipo.setBounds(160, 60, 100, 25);
            modal.add(comboTipo);
    
            JLabel labelTamanho = new JLabel("Tamanho:");
            labelTamanho.setBounds(20, 100, 150, 25);
            modal.add(labelTamanho);
    
            JComboBox<String> comboTamanho = new JComboBox<>(getTamanhosDoBanco());
            comboTamanho.setSelectedItem(tamanho);
            comboTamanho.setBounds(160, 100, 100, 25);
            modal.add(comboTamanho);
    
            JButton botaoAlterarItem = new JButton("Alterar");
            botaoAlterarItem.setBounds(100, 140, 100, 30);
            botaoAlterarItem.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String novaQuantidade = campoQuantidade.getText();
                    String novoTipo = (String) comboTipo.getSelectedItem();
                    String novoTamanho = (String) comboTamanho.getSelectedItem();
    
                    if (!novaQuantidade.isEmpty()) {
                        try {
                            String sqlAlterarItemDoacao = "UPDATE itemdoacao SET "
                            + "qtd = '" + novaQuantidade + "', "
                            + "cod = (SELECT cod FROM categoria WHERE descricao = '" + novoTipo + "'), "
                            + "ID_tamanho = (SELECT ID_tamanho FROM tamanho WHERE descricao = '" + novoTamanho + "') "
                            + "WHERE ID_item = " + idItemDoacao; // Supondo que você tenha essa variável                    

                            int rowsAffectedsqlAlterarItemDoacao = con_cliente.statement.executeUpdate(sqlAlterarItemDoacao);
                            if(rowsAffectedsqlAlterarItemDoacao>0){
                            }
                            else{
                                JOptionPane.showMessageDialog(null, "Erro ao alterar itemdoacao: Nenhum item alterado", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        } catch (SQLException exp) {
                            JOptionPane.showMessageDialog(null, "Erro ao alterar itemdoacao: "+exp, "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        modeloTabela.setValueAt(novoTipo, selectedRow, 1);
                        modeloTabela.setValueAt(novoTamanho, selectedRow, 2);
                        modeloTabela.setValueAt(novaQuantidade, selectedRow, 3);
                        modal.dispose();

                    } else {
                        JOptionPane.showMessageDialog(modal, "Por favor, insira uma quantidade.");
                    }
                }
            });
            modal.add(botaoAlterarItem);
    
            modal.setLocationRelativeTo(this);
            modal.setVisible(true);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item para alterar.");
        }
    }
  
    

    // Método para buscar ONGs do banco de dados
    private String[] getOngsDoBanco() {
        ArrayList<String> ongs = new ArrayList<>();

        try{
            String query = "SELECT nome FROM ong"; // Consulta SQL
            ResultSet resultSet = con_cliente.statement.executeQuery(query);

            while (resultSet.next()) {
                ongs.add(resultSet.getString("nome")); // Adiciona o nome da ONG à lista
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return ongs.toArray(new String[0]); // Retorna o array de ONGs
    }

    // Método para buscar tamanhos do banco de dados
    private String[] getTamanhosDoBanco() {
        ArrayList<String> tamanhos = new ArrayList<>();

        try {
            String query = "SELECT descricao FROM tamanho"; // Consulta SQL
            ResultSet resultSet = con_cliente.statement.executeQuery(query);

            while (resultSet.next()) {
                tamanhos.add(resultSet.getString("descricao")); // Adiciona a descrição à lista
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tamanhos.toArray(new String[0]); // Retorna o array de tamanhos
    }

    // Classe ModeloTabelaNaoEditavel
    class ModeloTabelaNaoEditavel extends DefaultTableModel {
        public ModeloTabelaNaoEditavel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        public boolean isCellEditable(int row, int column) {
            return false; // Todas as células são não editáveis
        }
    }
}
