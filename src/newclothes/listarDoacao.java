package newclothes;

import components.components;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class listarDoacao {
    components components;
    public listarDoacao(int id) {
        components = new components();

        // Criar o JFrame
        JFrame frame = new JFrame("Tabela de Doações");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); // Aumentar altura para incluir botão de sair
        frame.setLayout(null);

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
        scrollPane.setBounds(10, 40, 560, 240);
        frame.add(scrollPane);

        // Conectar ao banco de dados e buscar os dados
        try {
            String url = "jdbc:mysql://localhost:3306/bdnewclothes";
            String user = "root";
            String password = "";

            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM doacao WHERE ID_doador = '" + id + "'");

            while (resultSet.next()) {
                int idDoacao = resultSet.getInt("ID_doacao");
                String dataDoacao = resultSet.getString("dataDoacao");
                int idDoador = resultSet.getInt("ID_doador");
                int idOng = resultSet.getInt("ID_ong");

                tableModel.addRow(new Object[]{idDoacao, dataDoacao, idDoador, idOng});
            }

            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame, "Erro ao conectar ao banco de dados.", "Erro", JOptionPane.ERROR_MESSAGE);
        }

        // Criar botão de sair
        JButton sairButton = new JButton("Sair");
        sairButton.setBounds(250, 300, 100, 30); // Definindo posição e tamanho do botão
        frame.add(sairButton);

        // Adicionar evento para o botão de sair
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                menuDoador menuDoador = new menuDoador(id);
                menuDoador.setVisible(true);
                frame.dispose();
            }
        });

        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
