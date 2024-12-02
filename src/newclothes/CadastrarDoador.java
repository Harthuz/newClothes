package newclothes;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import conexao.conexao;

public class CadastrarDoador extends JFrame {
    // Instanciando o banco de dados
    conexao con_cliente;

    // Campos
    private JFormattedTextField cpfField;
    private JTextField nomeField;
    private JTextField emailField;

    public CadastrarDoador() {
        // Configurações do JFrame
        setTitle("Cadastrar Doador");
        setSize(450, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setIconImage(new javax.swing.ImageIcon(getClass().getResource("/newclothes/newclothesicon.png")).getImage());


        // Abrindo o banco
        con_cliente = new conexao();
        con_cliente.conecta();

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#F2F2F2"));

        // Titulo
        JLabel title = new JLabel("Cadastrar Doador");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setBounds(125, 20, 200, 40);
        panel.add(title);

        // Campo de nome
        JLabel nomeLabel = new JLabel("Digite seu nome");
        nomeLabel.setBounds(50, 75, 200, 40);
        panel.add(nomeLabel);

        nomeField = new JTextField("");
        nomeField.setBounds(50, 110, 250, 35);
        panel.add(nomeField);

        // Campo de CPF
        JLabel cpfLabel = new JLabel("Digite seu CPF");
        cpfLabel.setBounds(50, 145, 200, 40);
        panel.add(cpfLabel);

        try {
            MaskFormatter maskFormatter = new MaskFormatter("###.###.###-##");
            maskFormatter.setPlaceholderCharacter('_');

            cpfField = new JFormattedTextField(maskFormatter);
            cpfField.setBounds(50, 180, 250, 35);
            panel.add(cpfField);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar máscara para CPF: " + e.getMessage());
        }

        // Campo de email
        JLabel emailLabel = new JLabel("Digite seu email");
        emailLabel.setBounds(50, 215, 200, 40);
        panel.add(emailLabel);

        emailField = new JTextField("");
        emailField.setBounds(50, 250, 250, 35);
        panel.add(emailField);

        // Campo de senha
        JLabel senhaLabel = new JLabel("Digite sua senha");
        senhaLabel.setBounds(50, 285, 200, 40);
        panel.add(senhaLabel);

        JPasswordField passwordField = new JPasswordField("");
        passwordField.setBounds(50, 320, 250, 35);
        passwordField.setEchoChar('\u2022'); // Caractere para esconder a senha
        panel.add(passwordField);

        // Botão "Mostrar" ao lado do campo de senha
        JButton showPasswordButton = new JButton("Mostrar");
        showPasswordButton.setBounds(305, 320, 110, 35);
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
        enterButton.setBounds(160, 370, 100, 40);
        enterButton.setBackground(Color.decode("#8C3A1C")); // Cor marrom
        enterButton.setForeground(Color.WHITE);
        panel.add(enterButton);
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarCampos(nomeField, cpfField, emailField, passwordField)) {
                    // Lógica para cadastrar o doador
                    String nome = nomeField.getText().trim();
                    String cpf = cpfField.getText().trim();
                    String email = emailField.getText().trim();
                    String senha = new String(passwordField.getPassword()).trim();

                    // Verificação se esse email já existe no sistema
                     try {
                         Boolean emailExiste = con_cliente.emailExiste(email);

                         if(emailExiste) {
                             JOptionPane.showMessageDialog(null, "O email que você esta tentando cadastrar ja existe no sistema ");
                         } else {
                            // Criando Query
                             String query = "INSERT INTO doador (nome, email, cpf, senha) VALUES ('"
                                     + nome + "', '"
                                     + email + "', '"
                                     + cpf + "', '"
                                     + senha + "')";

                             // Criando novo doador
                             try {
                                 Boolean result = con_cliente.atualizaSQL(query);

                                 if (result) {
                                     JOptionPane.showMessageDialog(null, "Doador cadastrado com sucesso");
                                     dispose();
                                     login login = new login();
                                     login.setVisible(true);
                                 }
                             } catch (Exception ex) {
                                 JOptionPane.showMessageDialog(null, "Erro ao cadastrar o doador: " + ex.getMessage());
                             }
                         }
                     } catch (Exception ex) {
                         JOptionPane.showMessageDialog(null, "Erro ao Verificar se o email já existe no sistema: " + ex.getMessage());
                     }
                }
            }
        });

        // Botão de sair
        JButton sairButton = new JButton("Sair");
        sairButton.setBounds(160, 420, 100, 20);
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

    private boolean validarCampos(JTextField nomeField, JFormattedTextField cpfField,
                                  JTextField emailField, JPasswordField passwordField) {
        // Verifica se os campos estão preenchidos
        if (nomeField.getText().trim().isEmpty() || cpfField.getText().trim().isEmpty() ||
                emailField.getText().trim().isEmpty() || passwordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "Todos os campos devem ser preenchidos!");
            return false;
        }

        // Verifica se o CPF está preenchido corretamente
        if (cpfField.getText().trim().equals("___.___.___-__")) {
            JOptionPane.showMessageDialog(this, "CPF deve ser preenchido corretamente!");
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
