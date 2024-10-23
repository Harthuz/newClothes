package newclothes;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import conexao.conexao;


public class Cadastrar_ong extends JFrame {
    // Instanciando o banco de dados
    conexao con_cliente;

    // Campos
    private JFormattedTextField cnpjField;
    private JFormattedTextField telefoneField;
    private JTextField emailField;

    public Cadastrar_ong() {
        // Configurações do JFrame
        setTitle("Cadastrar ONG");
        setSize(450, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        // Abrindo o banco
        con_cliente = new conexao();
        con_cliente.conecta();

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#F2F2F2"));

        // Titulo
        JLabel title = new JLabel("Cadastrar ONG");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setBounds(125, 20, 200, 40);
        panel.add(title);

        // Campo de nome
        JLabel nomeLabel = new JLabel("Digite o nome da sua ONG");
        nomeLabel.setBounds(50, 75, 200, 40);
        panel.add(nomeLabel);

        JTextField nomeField = new JTextField("");
        nomeField.setBounds(50, 110, 250, 35);
        panel.add(nomeField);

        // Campo de CNPJ
        JLabel cnpjLabel = new JLabel("Digite o CNPJ da sua ONG");
        cnpjLabel.setBounds(50, 145, 200, 40);
        panel.add(cnpjLabel);

        try {
            MaskFormatter maskFormatter = new MaskFormatter("##.###.###/####-##");
            maskFormatter.setPlaceholderCharacter('_');

            cnpjField = new JFormattedTextField(maskFormatter);
            cnpjField.setBounds(50, 180, 250, 35);
            panel.add(cnpjField);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar máscara para CNPJ: " + e.getMessage());
        }

        // Campo de endereço
        JLabel enderecoLabel = new JLabel("Digite o endereço da sua ONG");
        enderecoLabel.setBounds(50, 215, 200, 40);
        panel.add(enderecoLabel);

        JTextField enderecoField = new JTextField("");
        enderecoField.setBounds(50, 250, 250, 35);
        panel.add(enderecoField);

        // Campo de Telefone
        JLabel telefoneLabel = new JLabel("Digite o Telefone da sua ONG");
        telefoneLabel.setBounds(50, 285, 200, 40);
        panel.add(telefoneLabel);

        try {
            MaskFormatter maskFormatter = new MaskFormatter("(##) #####-####");
            maskFormatter.setPlaceholderCharacter('_');

            telefoneField = new JFormattedTextField(maskFormatter);
            telefoneField.setBounds(50, 320, 250, 35);
            panel.add(telefoneField);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar máscara para telefone: " + e.getMessage());
        }

        // Campo de email
        JLabel emailLabel = new JLabel("Digite o email da sua ONG");
        emailLabel.setBounds(50, 355, 200, 40);
        panel.add(emailLabel);

        emailField = new JTextField("");
        emailField.setBounds(50, 390, 250, 35);
        panel.add(emailField);

        // Campo de senha
        JLabel senhaLabel = new JLabel("Digite a senha da sua ONG");
        senhaLabel.setBounds(50, 425, 200, 40);
        panel.add(senhaLabel);

        JPasswordField passwordField = new JPasswordField("");
        passwordField.setBounds(50, 460, 250, 35);
        passwordField.setEchoChar('\u2022'); // Caractere para esconder a senha
        panel.add(passwordField);

        // Botão "Mostrar" ao lado do campo de senha
        JButton showPasswordButton = new JButton("Mostrar");
        showPasswordButton.setBounds(305, 460, 110, 35);
        showPasswordButton.setBorderPainted(false);
        showPasswordButton.setFocusPainted(false);
        showPasswordButton.setBackground(Color.WHITE);
        showPasswordButton.setForeground(Color.BLUE);
        Border bnt_border = BorderFactory.createLineBorder(Color.BLACK);
        showPasswordButton.setBorder(bnt_border);
        panel.add(showPasswordButton);
        showPasswordButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (passwordField.getEchoChar() == '\u2022') {
                    passwordField.setEchoChar((char) 0); // Mostra senha
                    showPasswordButton.setText("Esconder");
                } else {
                    passwordField.setEchoChar('\u2022'); // Oculta senha
                    showPasswordButton.setText("Mostrar");
                }
            }
        });

        // Botão de Cadastrar
        JButton enterButton = new JButton("Cadastrar");
        enterButton.setBounds(160, 530, 100, 40);
        enterButton.setBackground(Color.decode("#8C3A1C")); // Cor marrom
        enterButton.setForeground(Color.WHITE);
        panel.add(enterButton);
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos(nomeField, cnpjField, enderecoField, telefoneField, emailField, passwordField)) {
                    // Lógica para cadastrar a ONG
                    String nome = nomeField.getText().trim();
                    String cnpj = cnpjField.getText().trim();
                    String endereco = enderecoField.getText().trim();
                    String telefone = telefoneField.getText().trim();
                    String email = emailField.getText().trim();
                    String senha = new String(passwordField.getPassword()).trim();

                    // Verificação para evitar SQL Injection
                    String query = "INSERT INTO ong (nome, email, CNPJ, endereco, telefone, senha) VALUES ('"
                            + nome + "', '"
                            + email + "', '"
                            + cnpj + "', '"
                            + endereco + "', '"
                            + telefone + "', '"
                            + senha + "')";
                    try {
                        Boolean result = con_cliente.atualizaSQL(query);

                        if (result == true) {
                            JOptionPane.showMessageDialog(null, "ONG adicionada com sucesso");
                            dispose();
                            login login = new login();
                            login.setVisible(true);
                        }
                    }catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar a ONG: " + ex.getMessage());
                    }
                }
            }
        });

        // Botão de sair
        JButton sairButton = new JButton("Sair");
        sairButton.setBounds(160, 580, 100, 20);
        sairButton.setBackground(Color.red);
        sairButton.setForeground(Color.WHITE);
        panel.add(sairButton);
        sairButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                login login = new login();
                login.setVisible(true);
            }
        });


        // Adiciona o painel ao JFrame
        add(panel);
        setVisible(true);
    }

    private boolean validarCampos(JTextField nomeField, JFormattedTextField cnpjField,
                                  JTextField enderecoField, JFormattedTextField telefoneField,
                                  JTextField emailField, JPasswordField passwordField) {
        // Verifica se os campos estão preenchidos
        if (nomeField.getText().trim().isEmpty() || cnpjField.getText().trim().isEmpty() ||
                enderecoField.getText().trim().isEmpty() || telefoneField.getText().trim().isEmpty() ||
                emailField.getText().trim().isEmpty() || passwordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!");
            return false;
        }

        // Verifica se o CNPJ está preenchido corretamente
        if (cnpjField.getText().trim().equals("__/______/____-__")) {
            JOptionPane.showMessageDialog(this, "CNPJ deve ser preenchido corretamente!");
            return false;
        }

        // Verifica se o telefone está preenchido corretamente
        if (telefoneField.getText().trim().equals("(  )     -    ")) {
            JOptionPane.showMessageDialog(this, "Telefone deve ser preenchido corretamente!");
            return false;
        }

        // Verifica se o e-mail possui algo antes e depois do "@"
        String email = emailField.getText().trim();
        if (!email.contains("@") || email.indexOf("@") == 0 || email.indexOf("@") == email.length() - 1) {
            JOptionPane.showMessageDialog(this, "E-mail deve ser preenchido corretamente!");
            return false;
        }

        return true; // Todos os campos estão válidos
    }
}
