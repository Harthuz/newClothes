package newclothes;

import conexao.conexao;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;

public class login extends JFrame {
    // Instânciando o banco de dados
    conexao con_cliente;

    public login() {
        // Configurações do JFrame
        setTitle("Entrar");
        setSize(450, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a tela
        setResizable(false);

        // Abrindo o banco
        con_cliente = new conexao();
        con_cliente.conecta();

        // Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(null); // Layout absoluto para controlar posições exatas
        panel.setBackground(Color.decode("#F2F2F2")); // Cor de fundo (preto)

        // Título "Entrar"
        JLabel titleLabel = new JLabel("Entrar");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
        titleLabel.setForeground(Color.BLACK);
        titleLabel.setBounds(175, 30, 150, 40);
        panel.add(titleLabel);

        // Label do campo email
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setBounds(100,35,100,100);
        panel.add(emailLabel);

        // Campo de email
        JTextField emailField = new JTextField("");
        emailField.setBounds(100, 100, 250, 35);
        panel.add(emailField);

        // Label do campo email
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(100,105,100,100);
        panel.add(senhaLabel);

        // Campo de senha
        JPasswordField passwordField = new JPasswordField("");
        passwordField.setBounds(100, 170, 200, 35);
        passwordField.setEchoChar('\u2022'); // Caractere para esconder a senha
        panel.add(passwordField);

        // Botão "Mostrar" ao lado do campo de senha
        JButton showPasswordButton = new JButton("Mostrar");
        showPasswordButton.setBounds(310, 170, 110, 35);
        showPasswordButton.setBorderPainted(false);
        showPasswordButton.setFocusPainted(false);
        showPasswordButton.setBackground(Color.WHITE);
        showPasswordButton.setForeground(Color.BLUE);
        Border bnt_border = BorderFactory.createLineBorder(Color.BLACK);
        showPasswordButton.setBorder(bnt_border);
        panel.add(showPasswordButton);

        // Ação do botão "Mostrar"
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

        // Botão de Entrar
        JButton enterButton = new JButton("Entrar");
        enterButton.setBounds(175, 230, 100, 40);
        enterButton.setBackground(new Color(139, 69, 19)); // Cor marrom
        enterButton.setForeground(Color.WHITE);
        panel.add(enterButton);

        enterButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    // Pegando os dados
                    char[] passwordArray = passwordField.getPassword();
                    String password = new String(passwordArray); // Converte char[] para string
                    String email = emailField.getText(); // Obtém o e-mail digitado

                    // Consulta SQL com concatenação de strings
                    String loginQuery = "SELECT * FROM %s WHERE email = '%s' AND senha = '%s'";
                    String[] tables = {"doador", "ong", "administrador"};
                    boolean loginSuccess = false;
                    String userType = ""; // Variável para armazenar o tipo de usuário

                    // Loop através das tabelas
                    for (String table : tables) {
                        String formattedQuery = String.format(loginQuery, table, email, password);

                        con_cliente.executaSQL(formattedQuery); // Executa a consulta

                        if (con_cliente.resultset.first()) {
                            loginSuccess = true;
                            userType = table; // Armazena a tabela em que o login foi bem-sucedido.
                            break;
                        }
                    }

                    if (loginSuccess) {
                        JOptionPane.showMessageDialog(null, "Login efetuado com sucesso! - " + userType.toUpperCase());

                        // Redirecionar para a página correspondente
                        switch (userType) {
                            case "doador":
                                // Redirecionar para a página do doador
                                break;
                            case "ong":
                                // Redirecionar para a página da ONG
                                break;
                            case "administrador":
                                // Redirecionar para a página do administrador
                                break;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Login falhou. Verifique suas credenciais.");
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Erro: " + ex.getMessage());
                }
            }
        });

        // Botão Cadastrar Doador
        JButton registerDonorButton = new JButton("Cadastrar doador");
        registerDonorButton.setBounds(50, 300, 150, 40);
        registerDonorButton.setBackground(new Color(139, 69, 19)); // Cor marrom
        registerDonorButton.setForeground(Color.WHITE);
        panel.add(registerDonorButton);

        // Botão Cadastrar ONG
        JButton registerOrgButton = new JButton("Cadastrar ONG");
        registerOrgButton.setBounds(250, 300, 150, 40);
        registerOrgButton.setBackground(new Color(139, 69, 19)); // Cor marrom
        registerOrgButton.setForeground(Color.WHITE);
        panel.add(registerOrgButton);

        // Adiciona o painel ao JFrame
        add(panel);
    }

    public static void main(String[] args) {
        // Cria e exibe a tela de login
        login frame = new login();
        frame.setVisible(true);
    }
}

