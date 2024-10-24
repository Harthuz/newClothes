package newclothes.doacao;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import conexao.conexao;

public class DoacaoDAO {
    private conexao con_cliente;

    public DoacaoDAO(conexao con_cliente) {
        this.con_cliente = con_cliente;
    }

    public int getLastDonationId() {
        int idDoacao = 17; // Valor padrão
        try {
            String sql = "SELECT ID_doacao FROM doacao ORDER BY dataDoacao DESC, ID_doacao DESC LIMIT 1";
            ResultSet resultSet = con_cliente.statement.executeQuery(sql);
            if (resultSet.next()) {
                idDoacao = resultSet.getInt("ID_doacao");
            } else {
                JOptionPane.showMessageDialog(null, "Nenhuma doação encontrada.", "Mensagem do Programa", JOptionPane.WARNING_MESSAGE);
                System.exit(0);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar o ID da última doação: " + e.getMessage(), "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
        }
        return idDoacao;
    }

    public void deleteLastDonation(int idDoacao) {
        try {
            String delete_sql = "DELETE FROM doacao WHERE ID_doacao = '" + idDoacao + "'";
            con_cliente.statement.executeUpdate(delete_sql);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao deletar a última doação: " + erro.getMessage(), "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
