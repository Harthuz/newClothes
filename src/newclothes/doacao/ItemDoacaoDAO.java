package newclothes.doacao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import conexao.conexao;

public class ItemDoacaoDAO {
    private conexao con_cliente;

    public ItemDoacaoDAO(conexao con_cliente) {
        this.con_cliente = con_cliente;
    }

    public ArrayList<Object[]> fetchDonationItems(int idDoacao) {
        ArrayList<Object[]> listaDados = new ArrayList<>();
        ResultSet resultSet = null;
    
        try {
            // SQL com JOIN
            String sql = "SELECT " +
                         "id.ID_item, " +
                         "id.qtd, " +
                         "t.descricao AS tamanho, " +
                         "c.descricao AS categoria " +
                         "FROM itemdoacao id " +
                         "JOIN tamanho t ON id.ID_tamanho = t.ID_tamanho " +
                         "JOIN categoria c ON id.cod = c.cod " +
                         "WHERE id.ID_doacao = " + idDoacao;
            
            resultSet = con_cliente.statement.executeQuery(sql);
    
            while (resultSet.next()) {
                int quantidade = resultSet.getInt("qtd");
                int idItem = resultSet.getInt("ID_item");
                String tamanho = resultSet.getString("tamanho");
                String descricao = resultSet.getString("categoria");
    
                listaDados.add(new Object[]{idItem, descricao, tamanho, quantidade});
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar os itens da doação: " + e.getMessage(), "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close(); // Fechar o ResultSet no final
                }
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro ao fechar o ResultSet: " + e.getMessage(), "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
            }
        }
        return listaDados;
    }
}
