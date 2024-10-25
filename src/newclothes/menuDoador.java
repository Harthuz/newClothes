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

    public static class variavelGlobal {
        // Variável global estática
        public static int idDoador = 25;
    }

    public menuDoador(){
        // Instanciando classe components
        components = new components();

        con_cliente = new conexao();
        con_cliente.conecta();

        int idDoadorMenuDoador = variavelGlobal.idDoador;

        // Configuração da janela
        setTitle("Menu Doador");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // Usando layout nulo para setBounds

        // Label Fazer Doação
        JLabel fazerDoacaoLabel = components.criarLabel("FAZER DOAÇÃO", "<u>", "Arial", 14, Font.BOLD, 20, 20, 150, 30);
        add(fazerDoacaoLabel);

        // Botão Fazer Doação
        JButton novaDoacaoButton = components.criarBotao("Nova Doação","<b>","#8C3A1C","Arial",Font.PLAIN,16,Color.WHITE,20,50,150,30);
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
                            SwingUtilities.invokeLater(() -> new telaDoacao(idUltimaDoacao).setVisible(true));           
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
        JLabel doacoesLabel = components.criarLabel("DOAÇÃO", "<u>", "Arial", 14, Font.BOLD, 20, 90, 150, 30);
        add(doacoesLabel);

        // Botão Doações
        JButton doacoesButton = components.criarBotao("Doações","<b>","#8C3A1C","Arial",Font.PLAIN,16,Color.WHITE,20,120,150,30);
        add(doacoesButton);
        doacoesButton.addActionListener(e ->{
            new listarDoacao();
            dispose();
        });


        // Tornar a janela visível
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(420,450);
    }

    // Função que retorna apenas a data atual
    public static LocalDate getDataAtual() {
        LocalDateTime agora = LocalDateTime.now(); // Obtém a data e hora atuais
        return agora.toLocalDate(); // Extrai apenas a data
    }

    public static void main(String[] args) {
        new menuDoador();
    }
}
