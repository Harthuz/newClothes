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
            JOptionPane.showMessageDialog(null, "Conexão estabelecida", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
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
}
