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

public class CrudDoadores extends JFrame { 
    conexao con_cliente;
    JTable clienteles; // Defina clienteles como um campo da classe
    DefaultTableModel tableModel; // Adicione um modelo de tabela
    
    JTextField codigoJTextField;
    JTextField nomeJTextField;
    JTextField SenhaJTextField;
    JFormattedTextField CPFJFormattedTextField;
    JTextField emailJTextField;
    JButton primeiro, anterior, proximo, ultimo, gravar, novo, excluir, alterar;

    public CrudDoadores() { 
        super("CRUD Cliente");
        con_cliente = new conexao();
        con_cliente.conecta();
   
        
    
    Container tela = getContentPane();
    tela.setLayout(null);
    tela.setBackground(new Color(242, 242, 242));  // Cor de fundo (Lavender);

        // Fonte customizada
        Font fontePadrao = new Font("Verdana", Font.BOLD, 14);
        Font fonteNegrito = new Font("Verdana", Font.BOLD, 14);

        // Labels
        JLabel codigoJLabel = new JLabel("Código:");
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

        JLabel CPFJLabel = new JLabel("CPF:");
        CPFJLabel.setBounds(20, 80, 100, 20);
        CPFJLabel.setFont(fonteNegrito);
        CPFJLabel.setForeground(new Color(0, 51, 102));
        tela.add(CPFJLabel);

        JLabel emailJLabel = new JLabel("Email:");
        emailJLabel.setBounds(20, 110, 100, 20);
        emailJLabel.setFont(fonteNegrito);
        emailJLabel.setForeground(new Color(0, 51, 102));
        tela.add(emailJLabel);
        
          JLabel SenhaJLabel = new JLabel("Senha:");
        SenhaJLabel.setBounds(20, 140, 100, 20);
        SenhaJLabel.setFont(fonteNegrito);
        SenhaJLabel.setForeground(new Color(0, 51, 102));
        tela.add(SenhaJLabel);

        SenhaJTextField = new JTextField();
        SenhaJTextField.setBounds(90, 140, 200, 25);
        SenhaJTextField.setFont(fontePadrao);
        tela.add(SenhaJTextField);

        // Botões de navegação
        primeiro = new JButton("Primeiro");
        anterior = new JButton("Anterior");
        proximo = new JButton("Próximo");
        ultimo = new JButton("Último");

        primeiro.setBounds(20, 170, 100, 20);
        anterior.setBounds(120, 170, 100, 20);
        proximo.setBounds(220, 170, 100, 20);
        ultimo.setBounds(320, 170, 100, 20);

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

        novo.setBounds(20, 420, 150, 20);
        gravar.setBounds(170, 420, 100, 20);
        excluir.setBounds(280, 420, 100, 20);
        alterar.setBounds(380, 420, 100, 20);
        
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
                CPFJFormattedTextField.setText("");
                emailJTextField.setText("");
                 SenhaJTextField.setText("");

                
                codigoJTextField.requestFocus();

            }});

       gravar.addActionListener(new ActionListener() {
    public void actionPerformed(ActionEvent e) {
        String nome = nomeJTextField.getText();
        String email = emailJTextField.getText(); 
        String CPF = CPFJFormattedTextField.getText();
        String Senha = SenhaJTextField.getText();

        try {
            // Corrigido para incluir a coluna Senha e remover o '+' desnecessário
            String insert_sql = "INSERT INTO doador (nome, email, CPF, Senha) VALUES ('"
                    + nome + "', '" + email + "', '" + CPF + "', '" + Senha + "')";
                    
            // Execute o SQL para inserção
            con_cliente.statement.executeUpdate(insert_sql);
            JOptionPane.showMessageDialog(null, "Gravação realizada com sucesso!!!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);

            con_cliente.executaSQL("SELECT * FROM doador ORDER BY ID_doador");
            preencherTabela(); // Atualiza a tabela após a inserção
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "\n Erro na gravação: \n" + erro, "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);
        }
    }
});


        alterar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String nome = nomeJTextField.getText();
                String email = emailJTextField.getText(); // Correção para usar o campo correto]
                 String CPF = CPFJFormattedTextField.getText();
                  String Senha = SenhaJTextField.getText();
                String sql;
                String msg;

                try {
                    if (codigoJTextField.getText().equals("")) {
                        sql = "INSERT INTO doador (nome,email,CPF) VALUES ('" + nome + "','" +email+  "','"+CPF+ "', +'"+Senha + "')";
                        msg = "Gravação de um novo registro";
                    } else {
                        // Corrigido para usar as colunas corretas e SQL válido
                        sql = "UPDATE doador SET nome='" + nome + "', email='" +email+  "', CPF='" + CPF + "',Senha='" + Senha + "' WHERE ID_doador = " + codigoJTextField.getText();
                        msg = "Alteração de registro";
                    }

                    con_cliente.statement.executeUpdate(sql);
                    JOptionPane.showMessageDialog(null, msg + " realizada com sucesso!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);

                    con_cliente.executaSQL("SELECT * FROM doador ORDER BY ID_doador");
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
                        sql = "DELETE FROM doador WHERE ID_doador = " + codigoJTextField.getText(); // Use o campo de código adaptado
                        int excluiu = con_cliente.statement.executeUpdate(sql);

                        if (excluiu == 1) {
                            JOptionPane.showMessageDialog(null, "Exclusão realizada com sucesso!", "Mensagem do Programa", JOptionPane.INFORMATION_MESSAGE);

                            con_cliente.executaSQL("SELECT * FROM doador ORDER BY ID_doador");
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
        searchLabel.setBounds(20, 470, 100, 20);  // Ajuste a posição conforme necessário
        tela.add(searchLabel);

        JTextField searchTextField = new JTextField();
        searchTextField.setBounds(90, 470, 200, 20);  // Ajuste a posição conforme necessário
        tela.add(searchTextField);

        JButton searchButton = new JButton("Pesquisar");
        searchButton.setBounds(290, 470, 100, 20);  // Ajuste a posição conforme necessário
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
        sair.setBounds(600, 470, 100, 20);
        sair.setFont(new Font("Verdana", Font.PLAIN, 14));
        sair.setBackground(new Color(255, 102, 102));  // Cor do fundo (Vermelho claro)
        sair.setForeground(Color.WHITE);  // Cor do texto (Branco)
        tela.add(sair);
        
        // Adiciona um ActionListener para o botão "Sair"
        sair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fecha a janela
                dispose();
            }
        });


        // Máscaras de formatação
        MaskFormatter CPFMask;
     

      // Data
            
            try {
                CPFMask = new MaskFormatter("###.###.###-##");
                CPFJFormattedTextField = new JFormattedTextField(CPFMask);
                CPFJFormattedTextField.setBounds(90, 80, 100, 25);
                tela.add(CPFJFormattedTextField);
            } catch (ParseException e) {
                e.printStackTrace();
            }
 
            // Telefone
            
          

        emailJTextField = new JTextField(); // Use o campo da classe
        emailJTextField.setBounds(90, 110, 200, 25);
        tela.add(emailJTextField);

        clienteles = new JTable(); // Inicialize clienteles
        clienteles.setBounds(20, 200, 700, 200);

        JScrollPane clienteScrollPane = new JScrollPane();
        clienteScrollPane.setBounds(20, 200, 700, 200);
        tela.add(clienteScrollPane);

        clienteles.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        clienteles.setFont(new Font("Arial", Font.BOLD, 12));

        // Inicialize o modelo de tabela e atribua ao JTable
        tableModel = new DefaultTableModel(
            new Object[][] {},
            new String[] {"Código", "Nome", "Email" , "CPF" , "Senha"}
        ) {
            boolean[] canEdit = new boolean[] { false, false, false, false };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        };

        clienteles.setModel(tableModel);
        clienteScrollPane.setViewportView(clienteles);

        clienteles.setAutoCreateRowSorter(true);

        // Executa a consulta SQL
        con_cliente.executaSQL("select * from doador order by ID_doador");

        preencherTabela();
        posicionarRegistro();

        setSize(750, 550);
        setVisible(true);
        setLocationRelativeTo(null);
    }


    public void preencherTabela() {
        // Configuração das larguras das colunas
        clienteles.getColumnModel().getColumn(0).setPreferredWidth(4);
        clienteles.getColumnModel().getColumn(1).setPreferredWidth(150);
        clienteles.getColumnModel().getColumn(2).setPreferredWidth(11);
          clienteles.getColumnModel().getColumn(3).setPreferredWidth(30);
          
   

        // Limpe o modelo antes de adicionar novas linhas
        tableModel.setRowCount(0);

        try {
            con_cliente.resultset.beforeFirst();
            while (con_cliente.resultset.next()) {
                tableModel.addRow(new Object[]{
                    con_cliente.resultset.getString("ID_doador"),
                    con_cliente.resultset.getString("nome"),
                    con_cliente.resultset.getString("email"),
                         con_cliente.resultset.getString("CPF"),
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
            codigoJTextField.setText(con_cliente.resultset.getString("ID_doador"));
            nomeJTextField.setText(con_cliente.resultset.getString("nome"));
            emailJTextField.setText(con_cliente.resultset.getString("email"));
             CPFJFormattedTextField.setText(con_cliente.resultset.getString("CPF"));
             SenhaJTextField.setText(con_cliente.resultset.getString("Senha"));
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "Não localizou dados: " + erro, "Mensagem do Programa", JOptionPane.ERROR_MESSAGE);
        }
    }

}
