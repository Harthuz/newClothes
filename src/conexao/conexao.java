package conexao;

import javax.swing.JOptionPane;
import java.sql.*;

public class conexao {
    final private String driver = "com.mysql.cj.jdbc.Driver";
    final private String url = "jdbc:mysql://localhost/bdnewclothes";
    final private String usuario = "root";
    final private String senha = "";
    private Connection conexao;
    public Statement statement;
    public ResultSet resultset;

    public boolean conecta() {
        boolean result = true;
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, usuario, senha);
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver não localizado: " + driver, "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
            result = false;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao conectar ao banco de dados: " + e.getMessage(), "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
            result = false;
        }
        return result;
    }

    public void desconecta() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                JOptionPane.showMessageDialog(null, "Conexão com banco fechada", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao fechar o banco: " + e.getMessage(), "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void executaSQL(String sql) {
        try {
            if (conexao != null) {
                statement = conexao.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_READ_ONLY);
                resultset = statement.executeQuery(sql); // Executa a consulta SQL
            } else {
                JOptionPane.showMessageDialog(null, "Conexão não estabelecida!", "Mensagem do Programa", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no comando SQL! \n ERRO: " + e.getMessage(), "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
        }
    }

    public boolean atualizaSQL(String sql) {
        boolean result = false;
        try {
            if (conexao != null) {
                statement = conexao.createStatement();
                int rowsAffected = statement.executeUpdate(sql); // Executa o comando de atualização
                return  result = rowsAffected > 0; // Retorna true se alguma linha foi afetada
            } else {
                JOptionPane.showMessageDialog(null, "Conexão não estabelecida!", "Mensagem do Programa", JOptionPane.WARNING_MESSAGE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro no comando SQL! \n ERRO: " + e.getMessage(), "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
        }
        return result;
    }

    public boolean emailExiste(String email) {
        String sql = "SELECT COUNT(*) FROM ( " +
                "SELECT 1 FROM doador WHERE email = '" + email + "' " +
                "UNION ALL " +
                "SELECT 1 FROM ong WHERE email = '" + email + "' " +
                "UNION ALL " +
                "SELECT 1 FROM administrador WHERE email = '" + email + "' " +
                ") AS subquery";

        try {
            statement = conexao.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()) {
                return resultSet.getInt(1) > 0; // Retorna true se o contador for maior que 0
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao verificar email! \n ERRO: " + e.getMessage(), "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
        }

        return false; // Retorna false se nenhum email foi encontrado
    }

}
