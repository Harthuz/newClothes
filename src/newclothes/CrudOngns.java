package newclothes;

import conexao.conexao;
import javax.swing.JOptionPane;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.sql.*;

public class CrudOngns extends JFrame {
    conexao con_cliente;
    JTable clienteles; // Defina clienteles como um campo da classe
    DefaultTableModel tableModel; // Adicione um modelo de tabela
    
    JTextField codigoJTextField;
    JTextField nomeJTextField;
    JTextField SenhaJTextField;
    JTextField EnderecoJTextField;
    JFormattedTextField CNPJJFormattedTextField,TelefoneJFormattedTextField ;
    JTextField emailJTextField;
    JButton primeiro, anterior, proximo, ultimo, gravar, novo, excluir, alterar;

    public CrudOngns() {
        super("CRUD Ongs Administrador");
        con_cliente = new conexao();
        con_cliente.conecta();
   
        
    
    Container tela = getContentPane();
    tela.setLayout(null);
    tela.setBackground(new Color(242, 242, 242));  // Cor de fundo (Lavender);

        // Fonte customizada
        Font fontePadrao = new Font("Verdana", Font.BOLD, 14);
        Font fonteNegrito = new Font("Verdana", Font.BOLD, 14);

        // Labels
        JLabel codigoJLabel = new JLabel("ID ONG:");
        codigoJLabel.setBounds(20, 20, 100, 25);
        codigoJLabel.setFont(fonteNegrito);  // Fonte em negrito
        codigoJLabel.setForeground(new Color(0, 51, 102));  // Cor do texto (Azul escuro)
        tela.add(codigoJLabel);

        codigoJTextField = new JTextField();
        codigoJTextField.setBounds(90, 20, 100, 20);
        codigoJTextField.setFont(fontePadrao);
        tela.add(codigoJTextField);

        JLabel nomeJLabel = new JLabel("Nome:");
        nomeJLabel.setBounds(20, 50, 100, 20);
        nomeJLabel.setFont(fonteNegrito);
        nomeJLabel.setForeground(new Color(0, 51, 102));
        tela.add(nomeJLabel);

        nomeJTextField = new JTextField();
        nomeJTextField.setBounds(90, 50, 200, 25);
        nomeJTextField.setFont(fontePadrao);
        tela.add(nomeJTextField);

        JLabel CNPJJLabel = new JLabel("CNPJ:");
        CNPJJLabel.setBounds(20, 80, 100, 20);
        CNPJJLabel.setFont(fonteNegrito);
        CNPJJLabel.setForeground(new Color(0, 51, 102));
        tela.add(CNPJJLabel);

        JLabel emailJLabel = new JLabel("Email:");
        emailJLabel.setBounds(20, 110, 100, 20);
        emailJLabel.setFont(fonteNegrito);
        emailJLabel.setForeground(new Color(0, 51, 102));
        tela.add(emailJLabel);
        
           JLabel EnderecoJLabel = new JLabel("Endereço:");
        EnderecoJLabel.setBounds(20, 140, 100, 20);
        EnderecoJLabel.setFont(fonteNegrito);
        EnderecoJLabel.setForeground(new Color(0, 51, 102));
        tela.add(EnderecoJLabel);

        EnderecoJTextField = new JTextField();
        EnderecoJTextField.setBounds(110, 140, 200, 20);
        tela.add(EnderecoJTextField);


        JLabel TelefoneJLabel = new JLabel("Telefone:");
        TelefoneJLabel.setBounds(20, 170, 100, 20);
        TelefoneJLabel.setFont(fonteNegrito);
        TelefoneJLabel.setForeground(new Color(0, 51, 102));
        tela.add(TelefoneJLabel);
        
          JLabel SenhaJLabel = new JLabel("Senha:");
        SenhaJLabel.setBounds(20, 200, 100, 20);
        SenhaJLabel.setFont(fonteNegrito);
        SenhaJLabel.setForeground(new Color(0, 51, 102));
        tela.add(SenhaJLabel);

        SenhaJTextField = new JTextField();
        SenhaJTextField.setBounds(90, 200, 200, 25);
        SenhaJTextField.setFont(fontePadrao);
        tela.add(SenhaJTextField);

        // Botões de navegação
        primeiro = new JButton("Primeiro");
        anterior = new JButton("Anterior");
        proximo = new JButton("Próximo");
        ultimo = new JButton("Último");

        primeiro.setBounds(20, 250, 100, 20);
        anterior.setBounds(120, 250, 100, 20);
        proximo.setBounds(220, 250, 100, 20);
        ultimo.setBounds(320, 250, 100, 20);

       primeiro.setFont(fontePadrao);
primeiro.setForeground(Color.WHITE);

anterior.setFont(fontePadrao);
anterior.setForeground(Color.WHITE);

proximo.setFont(fontePadrao);
proximo.setForeground(Color.WHITE);

ultimo.setFont(fontePadrao);
ultimo.setForeground(Color.WHITE);


        primeiro.setBackground(new Color(57, 31, 21));  // Cor do fundo dos botões (Azul claro)
        anterior.setBackground(new Color(57, 31, 21));
        proximo.setBackground(new Color(57, 31, 21));
        ultimo.setBackground(new Color(57, 31, 21));
        
        
        tela.add(primeiro);
        tela.add(anterior);
        tela.add(proximo);
        tela.add(ultimo);


        novo = new JButton("Novo Registro");
        gravar = new JButton("Gravar");
        excluir = new JButton("Excluir");
        alterar = new JButton("Alterar");

        novo.setBounds(20, 520, 150, 20);
        gravar.setBounds(180, 520, 100, 20);
        excluir.setBounds(290, 520, 100, 20);
        alterar.setBounds(400, 520, 100, 20);
        
        novo.setFont(fontePadrao);
novo.setForeground(Color.WHITE);

gravar.setFont(fontePadrao);
gravar.setForeground(Color.WHITE);

excluir.setFont(fontePadrao);
excluir.setForeground(Color.WHITE);

alterar.setFont(fontePadrao);
alterar.setForeground(Color.WHITE);


        novo.setBackground(new Color(57, 31, 21));  // Cor do fundo dos botões (Azul claro)
        gravar.setBackground(new Color(57, 31, 21));
        excluir.setBackground(new Color(57, 31, 21));
        alterar.setBackground(new Color(57, 31, 21));

        tela.add(novo);
        tela.add(gravar);
        tela.add(excluir);
        tela.add(alterar);
        
        primeiro.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
        try{
        con_cliente.resultset.first();
        mostrar_dados();
        }
        catch(SQLException erro){
        JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro");
        }
        }});
        
        anterior.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
        try{
        con_cliente.resultset.previous();
        mostrar_dados();
        }
        catch(SQLException erro){
        JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro");
        }
        }});
        
        proximo.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
        try{
        con_cliente.resultset.next();
        mostrar_dados();
        }
        catch(SQLException erro){
        JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro");
        }
        }});
        
        ultimo.addActionListener( new ActionListener(){
        public void actionPerformed(ActionEvent e){
        try{
        con_cliente.resultset.last();
        mostrar_dados();
        }
        catch(SQLException erro){
        JOptionPane.showMessageDialog(null, "Não foi possivel acessar o primeiro registro");
        }
        }});

        novo.addActionListener( new ActionListener(){
            public void actionPerformed(ActionEvent e){
                codigoJTextField.setText("");
                nomeJTextField.setText("");
                CNPJJFormattedTextField.setText("");
                emailJTextField.setText("");
                EnderecoJTextField.setText("");
                TelefoneJFormattedTextField.setText("");
                 SenhaJTextField.setText("");

                
                codigoJTextField.requestFocus();

            }});

        gravar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeJTextField.getText();
                String email = emailJTextField.getText();
                String CNPJ = CNPJJFormattedTextField.getText();
                String Endereco = EnderecoJTextField.getText();
                String Telefone = TelefoneJFormattedTextField.getText();
                String Senha = SenhaJTextField.getText();

                try {
                    // Corrigido para remover a vírgula extra
                    String insert_sql = "INSERT INTO ong (nome, email, CNPJ, Endereco, Telefone, Senha) VALUES ('"
                            + nome + "', '" + email + "', '" + CNPJ + "', '" + Endereco + "', '" + Telefone + "', '" + Senha + "')";

                    // Execute o SQL para inserção
                    con_cliente.statement.executeUpdate(insert_sql);
                    JOptionPane.showMessageDialog(null, "Gravação realizada com sucesso!!!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);

                    con_cliente.executaSQL("SELECT * FROM ong ORDER BY ID_ong");
                    preencherTabela(); // Atualiza a tabela após a inserção
                } catch (SQLException erro) {
                    JOptionPane.showMessageDialog(null, "\n Erro na gravação: \n" + erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        alterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeJTextField.getText();
                String email = emailJTextField.getText();
                String CNPJ = CNPJJFormattedTextField.getText();
                String Endereco = EnderecoJTextField.getText();
                String Telefone = TelefoneJFormattedTextField.getText();
                String Senha = SenhaJTextField.getText();
                String sql;
                String msg;

                try {
                    if (codigoJTextField.getText().equals("")) {
                        sql = "INSERT INTO ong (nome, email, CNPJ, Endereco, Telefone, Senha) VALUES ('"
                                + nome + "', '" + email + "', '" + CNPJ + "', '" + Endereco + "', '" + Telefone + "', '" + Senha + "')";
                        msg = "Gravação de um novo registro";
                    } else {
                        // Corrigido para usar as colunas corretas e SQL válido
                        sql = "UPDATE ong SET nome='" + nome + "', email='" + email + "', CNPJ='" + CNPJ + "', Endereco='" + Endereco + "', Telefone='" + Telefone + "', Senha='" + Senha + "' WHERE ID_ong = " + codigoJTextField.getText();
                        msg = "Alteração de registro";
                    }

                    con_cliente.statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, msg + " realizada com sucesso!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);

                    con_cliente.executaSQL("SELECT * FROM ong ORDER BY ID_ong");
                    preencherTabela(); // Atualiza a tabela após a alteração

                } catch (SQLException errosql) {
                    JOptionPane.showMessageDialog(null, "\nErro na gravação: \n" + errosql, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        excluir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String sql = "";
                try {
                    int resposta = JOptionPane.showConfirmDialog(null, "Deseja excluir o registro?", "Confirmar Exclusão", JOptionPane.YES_NO_OPTION);
                    if (resposta == JOptionPane.YES_OPTION) {
                        sql = "DELETE FROM ong WHERE ID_ong = " + codigoJTextField.getText(); // Use o campo de código adaptado
                        int excluiu = con_cliente.statement.executeUpdate(sql);

                        if (excluiu == 1) {
                            JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);

                            con_cliente.executaSQL("SELECT * FROM ong ORDER BY ID_ong");
                            preencherTabela();
                            con_cliente.resultset.first();
                            posicionarRegistro();
                        } else {
                            JOptionPane.showMessageDialog(null, "Operação cancelada pelo usuário!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                        }
                    }
                } catch (SQLException excecao) {
                    JOptionPane.showMessageDialog(null, "Erro na exclusão: " + excecao, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
                }

            }
        });


// Barra de pesquisa
        JLabel searchLabel = new JLabel("Pesquisar:");
        searchLabel.setBounds(20, 600, 100, 20);  // Ajuste a posição conforme necessário
        tela.add(searchLabel);

        JTextField searchTextField = new JTextField();
        searchTextField.setBounds(90, 600, 200, 20);  // Ajuste a posição conforme necessário
        tela.add(searchTextField);

        JButton searchButton = new JButton("Pesquisar");
        searchButton.setBounds(340, 600, 100, 20);  // Ajuste a posição conforme necessário
        tela.add(searchButton);

// Adiciona um ActionListener para filtrar a tabela à medida que o usuário digita
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
              try {
                  String pesquisa = "select * from doador where nome like '" + searchTextField.getText() + "%'";
                  con_cliente.executaSQL(pesquisa);

                  if (con_cliente.resultset.first()){
                      preencherTabela();
                  } else {
                      JOptionPane.showMessageDialog(null,"\n Não existe dados com este paramêtro!!","Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
                  }
              } catch (SQLException errosql){
                  JOptionPane.showMessageDialog(null,"\n Os dados digitados não foram localizados!! :\n "+errosql,"Mensagem do Programa",JOptionPane.INFORMATION_MESSAGE);
              }
            }
        });

 JButton sair = new JButton("Sair");
        sair.setBounds(750, 600, 100, 20);
        sair.setFont(new Font("Verdana", Font.PLAIN, 14));
        sair.setBackground(new Color(255, 102, 102));  // Cor do fundo (Vermelho claro)
        sair.setForeground(Color.WHITE);  // Cor do texto (Branco)
        tela.add(sair);
        
        // Adiciona um ActionListener para o botão "Sair"
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    // Fecha a janela
                MenuAdmin MenuAdmin = new MenuAdmin();
        MenuAdmin.setVisible(true); // Mostra a janela modal;  
        dispose();// TODO add your handling code here:
                dispose();
            }
        });


        // Máscaras de formatação
        MaskFormatter CNPJMask;
        MaskFormatter TelefoneMask;

      // CNPJ
            
            try {
                CNPJMask = new MaskFormatter("##.###.###/####-##");
                CNPJJFormattedTextField = new JFormattedTextField(CNPJMask);
                CNPJJFormattedTextField.setBounds(90, 80, 150, 25);
                tela.add(CNPJJFormattedTextField);
            } catch (ParseException e) {
                e.printStackTrace();
            }
 
            // Telefone
            try {
                TelefoneMask = new MaskFormatter("(##) ####-####");
                TelefoneJFormattedTextField = new JFormattedTextField(TelefoneMask);
                TelefoneJFormattedTextField.setBounds(100, 170, 100, 25);
                tela.add(TelefoneJFormattedTextField);
            } catch (ParseException e) {
                e.printStackTrace();
            }
          

        emailJTextField = new JTextField(); // Use o campo da classe
        emailJTextField.setBounds(90, 110, 200, 25);
        tela.add(emailJTextField);

        clienteles = new JTable(); // Inicialize clienteles
        clienteles.setBounds(20, 300, 800, 200);

        JScrollPane clienteScrollPane = new JScrollPane();
        clienteScrollPane.setBounds(20, 300, 800, 200);
        tela.add(clienteScrollPane);

        clienteles.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        clienteles.setFont(new Font("Arial", Font.BOLD, 12));

        // Inicialize o modelo de tabela e atribua ao JTable
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"ID_ONG", "Nome", "Email" , "CNPJ" , "Endereco","Telefone" ,"Senha" ,}
        ) {
            boolean[] canEdit = new boolean[] { false, false, false, false, false, false, false };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        clienteles.setModel(tableModel);
        clienteScrollPane.setViewportView(clienteles);

        clienteles.setAutoCreateRowSorter(true);

        // Executa a consulta SQL
        con_cliente.executaSQL("select * from ong order by ID_ong");

        preencherTabela();
        posicionarRegistro();

        setSize(900, 700);
        setVisible(true);
        setLocationRelativeTo(null);
    }


    public void preencherTabela() {
        // Configuração das larguras das colunas
        clienteles.getColumnModel().getColumn(0).setPreferredWidth(50);
        clienteles.getColumnModel().getColumn(1).setPreferredWidth(150);
        clienteles.getColumnModel().getColumn(2).setPreferredWidth(150);
          clienteles.getColumnModel().getColumn(3).setPreferredWidth(150);
        clienteles.getColumnModel().getColumn(4).setPreferredWidth(150);
        clienteles.getColumnModel().getColumn(5).setPreferredWidth(100);
        clienteles.getColumnModel().getColumn(6).setPreferredWidth(90);
          
   

        // Limpe o modelo antes de adicionar novas linhas
        tableModel.setRowCount(0);

        try {
            con_cliente.resultset.beforeFirst();
            while (con_cliente.resultset.next()) {
                tableModel.addRow(new Object[]{
                    con_cliente.resultset.getString("ID_ong"),
                    con_cliente.resultset.getString("nome"),
                    con_cliente.resultset.getString("email"),
                         con_cliente.resultset.getString("CNPJ"),
                        con_cliente.resultset.getString("endereco"),
                        con_cliente.resultset.getString("telefone"),
                         con_cliente.resultset.getString("Senha")
                         
                });
            }
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Erro ao listar dados da tabela: " + erro, "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void posicionarRegistro() {
        try {
            con_cliente.resultset.first();
            mostrar_dados();
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Não foi possível posicionar no primeiro registro: " + erro, "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void mostrar_dados() {
        try {
            codigoJTextField.setText(con_cliente.resultset.getString("ID_ong"));
            nomeJTextField.setText(con_cliente.resultset.getString("nome"));
            emailJTextField.setText(con_cliente.resultset.getString("email"));
             CNPJJFormattedTextField.setText(con_cliente.resultset.getString("CNPJ"));
            EnderecoJTextField.setText(con_cliente.resultset.getString("Endereco"));
            TelefoneJFormattedTextField.setText(con_cliente.resultset.getString("Telefone"));
             SenhaJTextField.setText(con_cliente.resultset.getString("Senha"));
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Não localizou dados: " + erro, "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        CrudOngns app = new CrudOngns();
        app.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }
}
