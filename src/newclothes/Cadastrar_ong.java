package newclothes;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;

public class Cadastrar_ong extends JFrame {
    public Cadastrar_ong() {
        // Configurações do JFrame
        setTitle("Cadastrar ONG");
        setSize(450,650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);


        //Abrindo o banco



        //Painel principal
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.decode("#F2F2F2"));


        // Titulo
        JLabel title = new JLabel("Cadastrar ONG");
        title.setFont(new Font("Arial", Font.BOLD, 25));
        title.setBounds(125,20,200,40);
        panel.add(title);


        //Campo de nome
        JLabel nomeLabel = new JLabel("Digite o nome da sua ONG");
        nomeLabel.setBounds(50, 75, 200,40);
        panel.add(nomeLabel);

        JTextField nomeField = new JTextField("");
        nomeField.setBounds(50,110,250,35);
        panel.add(nomeField);


        //Campo de CNPJ
        JLabel cnpjLabel = new JLabel("Digite o CNPJ da sua ONG");
        cnpjLabel.setBounds(50, 145, 200,40);
        panel.add(cnpjLabel);

        try {
            MaskFormatter maskFormatter = new MaskFormatter("##.###.###/####-##");
            maskFormatter.setPlaceholderCharacter('_');

            JFormattedTextField cnpjField = new JFormattedTextField(maskFormatter);
            cnpjField.setBounds(50,180,250,35);
            panel.add(cnpjField);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar mascara para CNPJ: " + e.getMessage());
        }


        //Campo de endereço
        JLabel enderecoLabel = new JLabel("Digite o endereço da sua ONG");
        enderecoLabel.setBounds(50, 215, 200,40);
        panel.add(enderecoLabel);

        JTextField enderecoField = new JTextField("");
        enderecoField.setBounds(50,250,250,35);
        panel.add(enderecoField);


        //Campo de Telefone
        JLabel telefoneLabel = new JLabel("Digite o Telefone da sua ONG");
        telefoneLabel.setBounds(50, 285, 200,40);
        panel.add(telefoneLabel);

        try {
            MaskFormatter maskFormatter = new MaskFormatter("(##) #####-####");
            maskFormatter.setPlaceholderCharacter('_');

            JFormattedTextField telefoneField = new JFormattedTextField(maskFormatter);
            telefoneField.setBounds(50,320,250,35);
            panel.add(telefoneField);
        } catch (ParseException e) {
            JOptionPane.showMessageDialog(null, "Erro ao criar mascara para telefone: " + e.getMessage());
        }


        //Campo de email
        JLabel emailLabel = new JLabel("Digite o email da sua ONG");
        emailLabel.setBounds(50, 355, 200,40);
        panel.add(emailLabel);

        JTextField emailField = new JTextField("");
        emailField.setBounds(50,390,250,35);
        panel.add(emailField);


        //Campo de senha
        JLabel senhaLabel = new JLabel("Digite a senha da sua ONG");
        senhaLabel.setBounds(50, 425, 200,40);
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


        // Botão de Entrar
        JButton enterButton = new JButton("Cadastrar");
        enterButton.setBounds(160, 530, 100, 40);
        enterButton.setBackground(Color.decode("#8C3A1C")); // Cor marrom
        enterButton.setForeground(Color.WHITE);
        panel.add(enterButton);

        // Adiciona o painel ao JFrame
        add(panel);
        setVisible(true);
    }

    public static void main(String[] args) {
        new Cadastrar_ong();
    }
}
