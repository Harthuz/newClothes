/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controle;

import conexao.Conexao;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
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
        entrarButton.setBounds(100, 290, 200, 35); // Ajuste o tamanho se necessário
        
        entrarButton.setFont(new Font("Arial", Font.BOLD, 16));
        entrarButton.setForeground(Color.WHITE);
        
        entrarButton.setBackground(new Color(140, 58, 28)); // Cor #8C3A1C
        entrarButton.setOpaque(true);
        painelDireito.add(entrarButton);

        JButton criarContaJLabel = new JButton("Criar conta");
        criarContaJLabel.setBounds(135, 400, 130, 28);

        criarContaJLabel.setFont(new Font("Arial", Font.BOLD, 14));
        criarContaJLabel.setForeground(Color.WHITE);
        
        criarContaJLabel.setBackground(new Color(140, 58, 28)); // Cor #db6e46
        criarContaJLabel.setOpaque(true);
        painelDireito.add(criarContaJLabel);

        criarContaJLabel.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                TelaCadastro cadastro = new TelaCadastro();
            }
        });
        

        entrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                try {
                    char[] senhaArray = senhaField.getPassword();
                    String senha = new String(senhaArray);

                    String pesquisa = "select * from usuario where username like'" +usuarioField.getText() +"' && senha = "+ senha + "";
                    con_cliente.executaSQL(pesquisa);

                    if(con_cliente.resultset.first()){
                        TelaPrincipal mostra = new TelaPrincipal();
                        mostra.setVisible(true);
                        dispose();
                    }
                    else{
                        JOptionPane.showMessageDialog(null, "\n Usuário não cadastrado!!!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                        con_cliente.desconecta();
                        System.exit(0);
                    }
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "\n Os dados digitados não foram encontrados!! \n", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        setSize(800, 500);
        setVisible(true);
        setLocationRelativeTo(null);
    }

public class TelaCadastro extends JFrame {
    public TelaCadastro() {
        super("Cadastro");
        
        Container tela = getContentPane();
        tela.setLayout(null);
        
        // Título "Cadastro"
        JLabel cadastroLabel = new JLabel("Cadastro");
        cadastroLabel.setFont(new Font("Arial", Font.BOLD, 36));
        cadastroLabel.setBounds(127, 20, 200, 50);
        tela.add(cadastroLabel);
        
        // Campo Usuário
        JLabel usuarioLabel = new JLabel("Usuário:");
        usuarioLabel.setBounds(50, 100, 80, 20);
        tela.add(usuarioLabel);

        JTextField usuarioField = new JTextField();
        usuarioField.setBounds(50, 130, 300, 30);
        tela.add(usuarioField);

        // Campo E-mail
        JLabel emailLabel = new JLabel("E-mail:");
        emailLabel.setBounds(50, 170, 80, 20);
        tela.add(emailLabel);

        JTextField emailField = new JTextField();
        emailField.setBounds(50, 200, 300, 30);
        tela.add(emailField);

        // Campo Senha
        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setBounds(50, 240, 80, 20);
        tela.add(senhaLabel);

        JPasswordField senhaField = new JPasswordField();
        senhaField.setBounds(50, 270, 300, 30);
        tela.add(senhaField);

        // Botão Cadastrar
        JButton cadastrarButton = new JButton("Cadastrar");
        cadastrarButton.setBounds(100, 340, 200, 35);
        cadastrarButton.setFont(new Font("Arial", Font.BOLD, 14));
        cadastrarButton.setForeground(Color.WHITE);
        
        cadastrarButton.setBackground(new Color(140, 58, 28)); // Cor #db6e46
        tela.add(cadastrarButton);

        // Ação do Botão Cadastrar (ainda não implementada)
        cadastrarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String username = usuarioField.getText(); // Obter o username
                String email = emailField.getText(); // Obter o e-mail
                char[] senhaArray = senhaField.getPassword(); // Obter a senha
                String senha = new String(senhaArray);
        
                // Verificar se todos os campos foram preenchidos
                if (username.isEmpty() || email.isEmpty() || senha.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Por favor, preencha todos os campos!", "Erro", JOptionPane.ERROR_MESSAGE);
                    return;
                }
        
                // Consulta para inserir o novo usuário na tabela
                String insercao = "INSERT INTO usuario (username, email, senha) VALUES ('" + username + "', '" + email + "', '" + senha + "')";
                
                try {
                    con_cliente.executaUpdate(insercao); // Executa a inserção no banco de dados
                    JOptionPane.showMessageDialog(null, "Usuário cadastrado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    dispose(); // Fecha a tela de cadastro após cadastrar
                } catch (Exception erro) {
                    JOptionPane.showMessageDialog(null, "Erro ao cadastrar usuário: " + erro.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        

        setSize(408, 450);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}
    

    public static void main(String[] args) {
        TelaLogin app = new TelaLogin();
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}