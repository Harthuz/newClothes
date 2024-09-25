/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import conexao.Conexao;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.concurrent.ExecutionException;
/**
 *
 * @author dti
 */
public class TelaLogin extends JFrame{
    Conexao con_cliente;
    public TelaLogin(){
        super("Login");

        con_cliente = new Conexao(); 
        con_cliente.conecta(); 

        Container tela = getContentPane();
        tela.setLayout(null);

        // Painel Esquerdo
        JPanel painelEsquerdo = new JPanel();
        painelEsquerdo.setBounds(0, 0, 400, 500);
        painelEsquerdo.setBackground(new Color(140, 58, 28));
        tela.add(painelEsquerdo);

        // Adicionar a imagem ao painelEsquerdo
        ImageIcon logoIcon = new ImageIcon(getClass().getResource("/images/logo.png")); // Substitua "logo.png" pelo nome da sua imagem
        JLabel logoLabel = new JLabel(logoIcon);
        logoLabel.setBounds(50, 100, 100, 100); // Ajuste os valores conforme necessário
        painelEsquerdo.add(logoLabel);

        // Painel Direito
        JPanel painelDireito = new JPanel();
        painelDireito.setLayout(null);
        painelDireito.setBounds(400, 0, 400, 500);
        painelDireito.setBackground(new Color(242, 242, 242));
        tela.add(painelDireito);

        // Título "Login"
        JLabel loginJLabel = new JLabel("Login");
        loginJLabel.setFont(new Font("Arial", Font.BOLD, 36));
        loginJLabel.setForeground(new Color(115, 50, 26)); // Cor #73321A
        loginJLabel.setBounds(150, 80, 100, 50); // Centralizado horizontalmente
        painelDireito.add(loginJLabel);

        // Campo Usuário
        JLabel usuarioLabel = new JLabel("Usuário:");
        usuarioLabel.setBounds(50, 150, 80, 20);
        painelDireito.add(usuarioLabel);

        JTextField usuarioField = new JTextField();
        usuarioField.setBounds(50, 175, 300, 30);
        painelDireito.add(usuarioField);

        // Campo Senha
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(50, 215, 80, 20);
        painelDireito.add(senhaLabel);

        JPasswordField senhaField = new JPasswordField();
        senhaField.setBounds(50, 240, 300, 30);
        painelDireito.add(senhaField);

        // Botão Entrar
        JButton entrarButton = new JButton("Entrar");
        entrarButton.setBounds(80, 290, 200, 35); // Ajuste o tamanho se necessário
        
        entrarButton.setFont(new Font("Arial", Font.BOLD, 16));
        entrarButton.setForeground(Color.WHITE);
        
        entrarButton.setBackground(new Color(140, 58, 28)); // Cor #8C3A1C
        entrarButton.setOpaque(true);
        painelDireito.add(entrarButton);

        // Botão de cadastrar doador
        JButton criar_conta_user_jlabel = new JButton("Criar conta - doador");
        criar_conta_user_jlabel.setBounds(70, 340, 220, 28);

        criar_conta_user_jlabel.setFont(new Font("Arial", Font.BOLD, 14));
        criar_conta_user_jlabel.setForeground(Color.WHITE);

        criar_conta_user_jlabel.setBackground(new Color(140, 58, 28)); // Cor #db6e46
        criar_conta_user_jlabel.setOpaque(true);
        painelDireito.add(criar_conta_user_jlabel);

        criar_conta_user_jlabel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                doador_tela_cadastro cadastro_doador = new doador_tela_cadastro();
            }
        });

        // Botão de cadastrar ong
        JButton criar_conta_ong_jlabel = new JButton("Criar conta - ong");
        criar_conta_ong_jlabel.setBounds(70, 380, 220, 28);

        criar_conta_ong_jlabel.setFont(new Font("Arial", Font.BOLD, 14));
        criar_conta_ong_jlabel.setForeground(Color.WHITE);

        criar_conta_ong_jlabel.setBackground(new Color(140, 58, 28)); // Cor #db6e46
        criar_conta_ong_jlabel.setOpaque(true);
        painelDireito.add(criar_conta_ong_jlabel);

        criar_conta_ong_jlabel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                ong_tela_cadastro cadastro_ong = new ong_tela_cadastro();
            }
        });


        entrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    char[] senhaArray = senhaField.getPassword();
                    String senha = new String(senhaArray);
                    String email = usuarioField.getText();

                    // Consulta para verificar se o email está nas tabelas de doador e ong
                    String pesquisaDoador = "SELECT * FROM doador WHERE Email = '" + email + "' AND Senha = '" + senha + "'";
                    String pesquisaOng = "SELECT * FROM ong WHERE Email = '" + email + "' AND Senha = '" + senha + "'";

                    // Verifica se o email está na tabela de doadores
                    con_cliente.executaSQL(pesquisaDoador);
                    boolean isDoador = con_cliente.resultset.first();

                    // Verifica se o email está na tabela de ONGs
                    con_cliente.executaSQL(pesquisaOng);
                    boolean isOng = con_cliente.resultset.first();

                    if (isDoador) {
                        // A lógica para doador
                        MenuDoador telaDoador = new MenuDoador();
                        telaDoador.setVisible(true);
                        JOptionPane.showMessageDialog(null, "Bem vindo doador!");
                        dispose();
                    } else if (isOng) {
//                         A lógica para ONG
                        MenuOng telaOng = new MenuOng();
                        telaOng.setVisible(true);
                        JOptionPane.showMessageDialog(null, "Bem vindo ONG!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Usuário não cadastrado!!!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                        con_cliente.desconecta();
                    }
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "Os dados digitados não foram encontrados!! \n", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });


        setSize(800, 500);
        setVisible(true);
        setLocationRelativeTo(null);
    }

    public class doador_tela_cadastro extends JFrame {
        public doador_tela_cadastro() {
            super("Cadastro");

            Container tela = getContentPane();
            tela.setLayout(null);

            // Título "Cadastro"
            JLabel cadastroLabel = new JLabel("Cadastro");
            cadastroLabel.setFont(new Font("Arial", Font.BOLD, 36));
            cadastroLabel.setBounds(127, 20, 200, 50);
            tela.add(cadastroLabel);

            // Campo nome
            JLabel nome_doador = new JLabel("Nome:");
            nome_doador.setBounds(50, 100, 80, 20);
            tela.add(nome_doador);

            JTextField usuarioField = new JTextField();
            usuarioField.setBounds(50, 130, 300, 30);
            tela.add(usuarioField);

            // Campo CPF
            JLabel cpf_doador_jlabel = new JLabel("CPF:");
            cpf_doador_jlabel.setBounds(50, 170, 80, 20);
            tela.add(cpf_doador_jlabel);

            JTextField cpf_doador_txtfield = new JTextField();
            cpf_doador_txtfield.setBounds(50, 200, 300, 30);
            tela.add(cpf_doador_txtfield);

            // Campo E-mail
            JLabel emailLabel = new JLabel("E-mail:");
            emailLabel.setBounds(50, 240, 80, 20);
            tela.add(emailLabel);

            JTextField emailField = new JTextField();
            emailField.setBounds(50, 270, 300, 30);
            tela.add(emailField);

            // Campo Senha
            JLabel senhaLabel = new JLabel("Senha:");
            senhaLabel.setBounds(50, 310, 80, 20);
            tela.add(senhaLabel);

            JPasswordField senhaField = new JPasswordField();
            senhaField.setBounds(50, 340, 300, 30);
            tela.add(senhaField);

            // Botão Cadastrar
            JButton cadastrarButton = new JButton("Cadastrar");
            cadastrarButton.setBounds(100, 410, 200, 35);
            cadastrarButton.setFont(new Font("Arial", Font.BOLD, 14));
            cadastrarButton.setForeground(Color.WHITE);

            cadastrarButton.setBackground(new Color(140, 58, 28)); // Cor #db6e46
            tela.add(cadastrarButton);

            // Ação do Botão Cadastrar
            cadastrarButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    String username = usuarioField.getText(); // Obter o username
                    String email = emailField.getText(); // Obter o e-mail
                    String cpf = cpf_doador_txtfield.getText();
                    char[] senhaArray = senhaField.getPassword(); // Obter a senha
                    String senha = new String(senhaArray);

                    // Verificar se todos os campos foram preenchidos
                    if (username.isEmpty() || email.isEmpty() || senha.isEmpty() || cpf.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Verificando se o CPF contém SOMENTE números e tem exatamente 11 dígitos
                    if (!cpf.matches("\\d{11}")) { // Verifica se o CPF contém apenas 11 dígitos numéricos
                        JOptionPane.showMessageDialog(null, "CPF deve ter exatamente 11 dígitos e somente números!", "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Verificar se o e-mail já existe nas tabelas doador e ong
                    String verificaEmail = "SELECT COUNT(*) FROM doador WHERE Email = '" + email + "' UNION SELECT COUNT(*) FROM ong WHERE Email = '" + email + "'";
                    try {
                        ResultSet rs = con_cliente.executaQuery(verificaEmail);
                        int count = 0;
                        while (rs.next()) {
                            count += rs.getInt(1);
                        }
                        if (count > 0) {
                            JOptionPane.showMessageDialog(null, "Este e-mail já está cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                            return;
                        }
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, "Erro ao verificar e-mail: " + erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Consulta para inserir o novo usuário na tabela
                    String insercao = "INSERT INTO doador (Nome, Email, CPF, Senha) VALUES ('" + username + "', '" + email + "', '" + cpf + "', '" + senha + "')";

                    try {
                        con_cliente.executaUpdate(insercao); // Executa a inserção no banco de dados
                        JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        dispose(); // Fecha a tela de cadastro após cadastrar
                    } catch (Exception erro) {
                        JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: " + erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });


            setSize(408, 600);
            setVisible(true);
            setLocationRelativeTo(null);
        }
    }

    public class ong_tela_cadastro extends JFrame {
            public ong_tela_cadastro() {
                super("Cadastro");

                Container tela = getContentPane();
                tela.setLayout(null);

                // Título "Cadastro"
                JLabel ong_cadastro_label = new JLabel("Cadastro");
                ong_cadastro_label.setFont(new Font("Arial", Font.BOLD, 36));
                ong_cadastro_label.setBounds(127, 20, 200, 50);
                tela.add(ong_cadastro_label);

                // Campo nome
                JLabel nome_ong_label = new JLabel("Nome:");
                nome_ong_label.setBounds(50, 100, 80, 20);
                tela.add(nome_ong_label);

                JTextField nome_ong_txtfield = new JTextField();
                nome_ong_txtfield.setBounds(50, 130, 300, 30);
                tela.add(nome_ong_txtfield);

                // Campo CNPJ
                JLabel cnpj_ong_jlabel = new JLabel("CNPJ:");
                cnpj_ong_jlabel.setBounds(50, 170, 80, 20);
                tela.add(cnpj_ong_jlabel);

                JTextField cnpj_ong_txtfield = new JTextField();
                cnpj_ong_txtfield.setBounds(50, 200, 300, 30);
                tela.add(cnpj_ong_txtfield);

                // Campo E-mail
                JLabel ong_email_label = new JLabel("E-mail:");
                ong_email_label.setBounds(50, 240, 80, 20);
                tela.add(ong_email_label);

                JTextField ong_email_txtfield = new JTextField();
                ong_email_txtfield.setBounds(50, 270, 300, 30);
                tela.add(ong_email_txtfield);

                // Campo endereço
                JLabel ong_endereco_label = new JLabel("Endereço:");
                ong_endereco_label.setBounds(50, 310, 80, 20);
                tela.add(ong_endereco_label);

                JTextField ong_endereco_txtfield = new JTextField();
                ong_endereco_txtfield.setBounds(50, 340, 300, 30);
                tela.add(ong_endereco_txtfield);

                // Campo telefone
                JLabel ong_telefone_label = new JLabel("Telefone:");
                ong_telefone_label.setBounds(50, 380, 80, 20);
                tela.add(ong_telefone_label);

                JFormattedTextField ong_telefone_txtfield = null;
                try {
                    MaskFormatter formatter = new MaskFormatter("(##) #####-####");
                    formatter.setPlaceholderCharacter('_'); // Caracter que aparece nos campos vazios
                    ong_telefone_txtfield = new JFormattedTextField(formatter);
                    ong_telefone_txtfield.setBounds(50, 410, 300, 30);
                    tela.add(ong_telefone_txtfield);
                } catch (ParseException e) {
                    e.printStackTrace(); // Tratar exceção em caso de erro na formatação
                }
                JFormattedTextField finalOng_telefone_txtfield = ong_telefone_txtfield;


                // Campo Senha
                JLabel ong_senha_label = new JLabel("Senha:");
                ong_senha_label.setBounds(50, 450, 80, 20);
                tela.add(ong_senha_label);

                JPasswordField ong_senha_txtfield = new JPasswordField();
                ong_senha_txtfield.setBounds(50, 480, 300, 30);
                tela.add(ong_senha_txtfield);

                // Botão Cadastrar
                JButton cadastrarButton = new JButton("Cadastrar");
                cadastrarButton.setBounds(100, 550, 200, 35);
                cadastrarButton.setFont(new Font("Arial", Font.BOLD, 14));
                cadastrarButton.setForeground(Color.WHITE);

                cadastrarButton.setBackground(new Color(140, 58, 28)); // Cor #db6e46
                tela.add(cadastrarButton);

                // Ação do Botão Cadastrar
                cadastrarButton.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        String nome = nome_ong_txtfield.getText();
                        String email = ong_email_txtfield.getText();
                        String cnpj = cnpj_ong_txtfield.getText();
                        String endereco = ong_endereco_txtfield.getText();
                        String telefone = finalOng_telefone_txtfield.getText();
                        char[] senhaArray = ong_senha_txtfield.getPassword();
                        String senha = new String(senhaArray);

                        // Verificar se todos os campos foram preenchidos
                        if (nome.isEmpty() || email.isEmpty() || senha.isEmpty() || cnpj.isEmpty() || endereco.isEmpty() || telefone.isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Verificar se o CNPJ é válido
                        if (!cnpj.matches("\\d{14}")) {
                            JOptionPane.showMessageDialog(null, "CNPJ deve ter exatamente 14 dígitos e somente números!", "Erro", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Verificar se o e-mail já existe nas tabelas doador e ong
                        String verificaEmail = "SELECT COUNT(*) FROM doador WHERE Email = '" + email + "' UNION SELECT COUNT(*) FROM ong WHERE Email = '" + email + "'";
                        try {
                            ResultSet rs = con_cliente.executaQuery(verificaEmail);
                            int count = 0;
                            while (rs.next()) {
                                count += rs.getInt(1);
                            }
                            if (count > 0) {
                                JOptionPane.showMessageDialog(null, "Este e-mail já está cadastrado!", "Erro", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                        } catch (Exception erro) {
                            JOptionPane.showMessageDialog(null, "Erro ao verificar e-mail: " + erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                            return;
                        }

                        // Inserir novo usuário na tabela
                        String insercao = "INSERT INTO ong (Nome, Email, CNPJ, Endereco, Telefone, Senha) VALUES ('" + nome + "', '" + email + "', '" + cnpj + "', '" + endereco + "', '" + telefone + "', '" + senha + "')";
                        try {
                            con_cliente.executaUpdate(insercao);
                            JOptionPane.showMessageDialog(null, "ONG cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                            dispose(); // Fecha a tela de cadastro após cadastrar
                        } catch (Exception erro) {
                            JOptionPane.showMessageDialog(null, "Erro ao cadastrar ONG: " + erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                });


                setSize(408, 700);
                setVisible(true);
                setLocationRelativeTo(null);
            }
        }


    public static void main(String[] args) {
        TelaLogin app = new TelaLogin();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}