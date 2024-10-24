package newclothes;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import newclothes.*;
import newclothes.menuDoador.variavelGlobal;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class TelaDoacao extends JFrame {
    private JTable tabela;
    private ModeloTabelaNaoEditavel modeloTabela;
    private JTextField campoDia;
    private JTextField campoMes;
    private JTextField campoAno;

    public TelaDoacao() {
        setTitle("Tela de Doação");
        setSize(750, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        int idDoador = variavelGlobal.idDoador;
        System.out.println("ID do Doador: " + idDoador);

        JLabel labelDoacao = new JLabel("Sua doação contém os itens:");
        labelDoacao.setBounds(20, 20, 250, 25);
        add(labelDoacao);

        // Modelo da tabela
        modeloTabela = new ModeloTabelaNaoEditavel(new String[]{"Item", "Tamanho", "Quantidade"}, 0);
        tabela = new JTable(modeloTabela);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(20, 50, 550, 200);
        add(scrollPane);

        JButton botaoAdicionar = new JButton("Adicionar Novo Item");
        botaoAdicionar.setBounds(20, 270, 180, 30);
        botaoAdicionar.addActionListener(e -> abrirModalAdicionarItem());
        add(botaoAdicionar);

        JButton botaoFazerDoacao = new JButton("Fazer Doação");
        botaoFazerDoacao.setBounds(210, 270, 150, 30);
        add(botaoFazerDoacao);
        botaoFazerDoacao.addActionListener(e ->{
            
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
            @Override
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
            @Override
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
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isDigit(e.getKeyChar()) || campoAno.getText().length() >= 4) {
                    e.consume();
                }
            }
        });
        add(campoAno);

        JLabel labelOng = new JLabel("ONG:");
        labelOng.setBounds(400, 320, 150, 25);
        add(labelOng);

        JComboBox<String> comboOng = new JComboBox<>(getOngsDoBanco());
        comboOng.setBounds(440, 320, 200, 25);
        add(comboOng);
    }

    private void abrirModalAdicionarItem() {
        JDialog modal = new JDialog(this, "Adicionar Item", true);
        modal.setSize(300, 250);
        modal.setLayout(null);

        JLabel labelQuantidade = new JLabel("Quantidade do Item:");
        labelQuantidade.setBounds(20, 20, 150, 25);
        modal.add(labelQuantidade);

        JTextField campoQuantidade = new JTextField();
        campoQuantidade.setBounds(160, 20, 100, 25);
        campoQuantidade.addKeyListener(new KeyAdapter() {
            @Override
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
        comboTipo.setBounds(160, 60, 100, 25);
        modal.add(comboTipo);

        JLabel labelTamanho = new JLabel("Tamanho:");
        labelTamanho.setBounds(20, 100, 150, 25);
        modal.add(labelTamanho);

        JComboBox<String> comboTamanho = new JComboBox<>(getTamanhosDoBanco());
        comboTamanho.setBounds(160, 100, 100, 25);
        modal.add(comboTamanho);

        JButton botaoAdicionarItem = new JButton("Adicionar");
        botaoAdicionarItem.setBounds(100, 140, 100, 30);
        botaoAdicionarItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String quantidade = campoQuantidade.getText();
                String tipo = (String) comboTipo.getSelectedItem();
                String tamanho = (String) comboTamanho.getSelectedItem();

                if (!quantidade.isEmpty()) {
                    modeloTabela.addRow(new Object[]{tipo, tamanho, quantidade});
                    modal.dispose();
                } else {
                    JOptionPane.showMessageDialog(modal, "Por favor, insira uma quantidade.");
                }
            }
        });
        modal.add(botaoAdicionarItem);

        modal.setLocationRelativeTo(this);
        modal.setVisible(true);
    }

    // Método para buscar tipos do banco de dados
    private String[] getTiposDoBanco() {
        ArrayList<String> tipos = new ArrayList<>();
        String url = "jdbc:mysql://localhost/bdnewclothes"; // URL do seu banco de dados
        String usuario = "root"; // Usuário do banco de dados
        String senha = ""; // Senha do banco de dados

        try (Connection connection = DriverManager.getConnection(url, usuario, senha);
             Statement statement = connection.createStatement()) {

            String query = "SELECT descricao FROM categoria"; // Consulta SQL
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                tipos.add(resultSet.getString("descricao")); // Adiciona a descrição à lista
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tipos.toArray(new String[0]); // Retorna o array de tipos
    }

    private void excluirItem() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            modeloTabela.removeRow(selectedRow);
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um item para excluir.");
        }
    }

    private void alterarItem() {
        int selectedRow = tabela.getSelectedRow();
        if (selectedRow != -1) {
            // Recuperar os dados da linha selecionada
            String tipo = (String) modeloTabela.getValueAt(selectedRow, 0);
            String tamanho = (String) modeloTabela.getValueAt(selectedRow, 1);
            String quantidade = (String) modeloTabela.getValueAt(selectedRow, 2);
    
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
                @Override
                public void actionPerformed(ActionEvent e) {
                    String novaQuantidade = campoQuantidade.getText();
                    String novoTipo = (String) comboTipo.getSelectedItem();
                    String novoTamanho = (String) comboTamanho.getSelectedItem();
    
                    if (!novaQuantidade.isEmpty()) {
                        modeloTabela.setValueAt(novoTipo, selectedRow, 0);
                        modeloTabela.setValueAt(novoTamanho, selectedRow, 1);
                        modeloTabela.setValueAt(novaQuantidade, selectedRow, 2);
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
        String url = "jdbc:mysql://localhost/bdnewclothes"; // URL do seu banco de dados
        String usuario = "root"; // Usuário do banco de dados
        String senha = ""; // Senha do banco de dados

        try (Connection connection = DriverManager.getConnection(url, usuario, senha);
             Statement statement = connection.createStatement()) {

            String query = "SELECT nome FROM ong"; // Consulta SQL
            ResultSet resultSet = statement.executeQuery(query);

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
        String url = "jdbc:mysql://localhost/bdnewclothes"; // URL do seu banco de dados
        String usuario = "root"; // Usuário do banco de dados
        String senha = ""; // Senha do banco de dados

        try (Connection connection = DriverManager.getConnection(url, usuario, senha);
             Statement statement = connection.createStatement()) {

            String query = "SELECT descricao FROM tamanho"; // Consulta SQL
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                tamanhos.add(resultSet.getString("descricao")); // Adiciona a descrição à lista
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tamanhos.toArray(new String[0]); // Retorna o array de tamanhos
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TelaDoacao().setVisible(true));
    }

    // Classe ModeloTabelaNaoEditavel
    class ModeloTabelaNaoEditavel extends DefaultTableModel {
        public ModeloTabelaNaoEditavel(Object[] columnNames, int rowCount) {
            super(columnNames, rowCount);
        }

        @Override
        public boolean isCellEditable(int row, int column) {
            return false; // Todas as células são não editáveis
        }
    }
}
