package newclothes;

import conexao.conexao;
import components.components;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import javax.swing.text.AttributeSet;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class adicionarItem extends JDialog {
    conexao con_cliente;
    components components;

    public adicionarItem(JFrame parent) {
        super(parent, "Adicionar Item", true); // Set the dialog to be modal

        // Instanciando classe components
        components = new components();

        con_cliente = new conexao();
        con_cliente.conecta();

        // Configuração da janela
        setSize(420, 450);
        setLayout(null);  // Usando layout nulo para setBounds

        // Label adicionar item
        JLabel adicionarItemLabel = components.criarLabel("ADICIONAR ITEM", "<u>", "Arial", 16, Font.BOLD, 20, 20, 150, 30);
        add(adicionarItemLabel);

        // Label quantidade
        JLabel quantidadeLabel = components.criarLabel("Quantidade do item", "<u>", "Arial", 13, Font.PLAIN, 20, 60, 150, 30);
        add(quantidadeLabel);

        // Criando o JTextField com placeholder
        JTextField textField = new JTextField("Ex: 1") {
            @Override
            protected void processComponentKeyEvent(KeyEvent e) {
                super.processComponentKeyEvent(e);
            }
        };

        // Definindo a cor do placeholder
        textField.setForeground(Color.GRAY);
        textField.setCaretColor(Color.BLACK); // Cor do cursor

        // Aplicando DocumentFilter para permitir apenas números
        ((AbstractDocument) textField.getDocument()).setDocumentFilter(new NumberDocumentFilter());

        // Listener para lidar com o placeholder
        textField.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if (textField.getText().equals("Ex: 1")) {
                    textField.setText("");
                    textField.setForeground(Color.BLACK); // Muda a cor do texto para preto
                }
            }

            @Override
            public void focusLost(FocusEvent e) {
                if (textField.getText().isEmpty()) {
                    textField.setForeground(Color.GRAY);
                    textField.setText("Ex: 1");
                }
            }
        });

        // Adicionando o JTextField ao JFrame
        textField.setBounds(20, 90, 150, 30); // Ajuste a posição e o tamanho conforme necessário
        add(textField);

        // Criando JComboBox para categoria de item
        JComboBox<String> categoriaComboBox = new JComboBox<>(fetchCategories());
        categoriaComboBox.setBounds(20, 130, 150, 30);
        add(categoriaComboBox);        

        // Criando JComboBox para tamanho
        JComboBox<String> tamanhoComboBox = new JComboBox<>(fetchTamanhos());
        tamanhoComboBox.setBounds(20, 170, 150, 30);
        add(tamanhoComboBox);

        // Botão adicionar item
        JButton adidionarItemButton = components.criarBotao("Adicionar Item","<b>","#8C3A1C","Arial",Font.PLAIN,16,Color.WHITE,20,290,150,30);
        add(adidionarItemButton);
        adidionarItemButton.addActionListener(e -> {
            int quantidadeDigitada=0, codigo=0, idDoacao=23, tamanho=0;
            String textoDigitado = textField.getText(); // Obtém o texto do campo de texto

            try {
                quantidadeDigitada = Integer.parseInt(textoDigitado); // Converte o texto para inteiro
            } catch (NumberFormatException exp) {
                // Trata a exceção se a conversão falhar (por exemplo, se o texto não for um número válido)
                JOptionPane.showMessageDialog(null, "Por favor, digite um número válido.", "Erro de Entrada", JOptionPane.ERROR_MESSAGE);
                return; 
            }

            String tamanhoSelecionado = (String) tamanhoComboBox.getSelectedItem();
            String categoriaSelecionada = (String) categoriaComboBox.getSelectedItem();

            try {
                // Primeira consulta: buscar dados da tabela "tamanho"
                String sqlTamanho = "SELECT ID_tamanho FROM tamanho WHERE descricao LIKE '"+tamanhoSelecionado+"'";
                
                ResultSet resultSetTamanho = con_cliente.statement.executeQuery(sqlTamanho);
                
                while (resultSetTamanho.next()) {
                    tamanho = resultSetTamanho.getInt("ID_tamanho");
                }
                
                // Segunda consulta: buscar dados da tabela "categoria"
                String sqlCategoria = "SELECT cod FROM categoria WHERE descricao LIKE '"+categoriaSelecionada+"'";
                
                ResultSet resultSetCategoria = con_cliente.statement.executeQuery(sqlCategoria);
                
                while (resultSetCategoria.next()) {
                    codigo = resultSetCategoria.getInt("cod");
                }

                String sqlInput = "INSERT INTO itemdoacao (qtd, ID_doacao, cod, ID_tamanho) VALUES (" 
                + quantidadeDigitada + ", " 
                + idDoacao + ", " 
                + codigo + ", " 
                + tamanho + ")";

                try {
                    int rowsAffected = con_cliente.statement.executeUpdate(sqlInput); // Executa a atualização e captura o número de linhas afetadas
                    if (rowsAffected > 0) {
                        System.out.println("Inserção bem-sucedida.");
                        dispose();
                    } else {
                        System.out.println("Erro ao inserir.");
                    }
                } catch (SQLException exp) {
                    System.out.println("Erro ao executar a inserção: " + exp.getMessage());
                }

            } catch (SQLException exp) {
                JOptionPane.showMessageDialog(null, "Erro ao buscar categorias: " + exp.getMessage(), "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
            }            
        });

        // Tornar a janela visível
        setVisible(true);
        setLocationRelativeTo(parent);
    }
    

    // Método para buscar categorias no banco de dados
    private String[] fetchCategories() {
        ArrayList<String> categories = new ArrayList<>();
        try {
            String sql = "SELECT descricao FROM categoria"; // Modifique conforme necessário
            ResultSet resultSet = con_cliente.statement.executeQuery(sql);
            while (resultSet.next()) {
                categories.add(resultSet.getString("descricao"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar categorias: " + e.getMessage(), "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
        }
        return categories.toArray(new String[0]);
    }

    // Método para buscar tamanhos no banco de dados
    private String[] fetchTamanhos() {
        ArrayList<String> tamanhos = new ArrayList<>();
        try {
            String sql = "SELECT descricao FROM tamanho"; // Modifique conforme necessário
            ResultSet resultSet = con_cliente.statement.executeQuery(sql);
            while (resultSet.next()) {
                tamanhos.add(resultSet.getString("descricao"));
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro ao buscar tamanhos: " + e.getMessage(), "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
        }
        return tamanhos.toArray(new String[0]);
    }

    // Classe DocumentFilter para restringir a entrada a números
    static class NumberDocumentFilter extends DocumentFilter {
        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string != null && string.matches("\\d*")) { // Permite apenas dígitos
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text != null && text.matches("\\d*")) { // Permite apenas dígitos
                super.replace(fb, offset, length, text, attrs);
            }
        }

        @Override
        public void remove(FilterBypass fb, int offset, int length) throws BadLocationException {
            super.remove(fb, offset, length);
        }
    }

    public static void main(String[] args) {
        // Pass null as parent to avoid NullPointerException
        new adicionarItem(null);
    }
}
