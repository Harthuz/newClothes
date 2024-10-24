package newclothes;

import components.components;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import java.awt.Font;
import java.sql.*;

public class listarDoacao {
    public listarDoacao(){
        components components;
        components = new components();

        // Criar o JFrame
        JFrame frame = new JFrame("Tabela de Doações");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 360);
        frame.setLayout(null); // Usar layout nulo para definir posições manualmente

        // Label Fazer Doação
        JLabel fazerDoacaoLabel = components.criarLabel("SUAS DOAÇÕES", "<u>", "Arial", 16, Font.BOLD, 10, 10, 150, 30);
        frame.add(fazerDoacaoLabel);

        // Criar as colunas da tabela
        String[] columnNames = {"ID_doacao", "dataDoacao", "ID_doador", "ID_ong"};

        // Criar o modelo da tabela com as colunas
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Desabilitar edição
            }
        };

        // Criar a JTable com o modelo
        JTable table = new JTable(tableModel);
        
        // Adicionar a JTable a um JScrollPane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(10, 40, 560, 240); // Definindo a posição e o tamanho do JScrollPane

        frame.add(scrollPane);

        // Conectar ao banco de dados e buscar os dados
        try {
            // Definindo a conexão
            String url = "jdbc:mysql://localhost:3306/bdnewclothes";
            String user = "root";
            String password = "";

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            // Executar a consulta SQL
            ResultSet resultSet = statement.executeQuery("SELECT * FROM doacao");

            // Preencher a tabela com os dados retornados
            while (resultSet.next()) {
                int idDoacao = resultSet.getInt("ID_doacao");
                String dataDoacao = resultSet.getString("dataDoacao");
                int idDoador = resultSet.getInt("ID_doador");
                int idOng = resultSet.getInt("ID_ong");
                
                // Adicionar a linha à tabela
                tableModel.addRow(new Object[]{idDoacao, dataDoacao, idDoador, idOng});
            }

            // Fechar a conexão
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        // Definir a visibilidade do JFrame
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
    public static void main(String[] args) {
        new listarDoacao();
    }
}
