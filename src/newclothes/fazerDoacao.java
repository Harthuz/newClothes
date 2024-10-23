package newclothes;

import javax.swing.*;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;

import java.util.Date;
import java.awt.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import components.components;
import conexao.conexao;

public class fazerDoacao extends JFrame{
    conexao con_cliente;
    components components;
    public fazerDoacao(){
        components = new components();

        con_cliente = new conexao();
        con_cliente.conecta();

        // Configuração da janela
        setTitle("Fazer Doação");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // Usando layout nulo para setBounds

        // Label Titulo Página
        JLabel doacaoLabel = components.criarLabel("SUA DOAÇÃO CONTÉM OS ITENS:", "<u>", "Arial", 14, Font.BOLD, 20, 20, 300, 30);
        add(doacaoLabel);

        // Dados da tabela (campos id, item, tamanho, quantidade e botões de alterar e excluir)
        Object[][] dados = {
            {1, "Camiseta", "M", 10},
            {2, "Calça", "G", 5},
            {3, "Jaqueta", "P", 3},
            {3, "Jaqueta", "P", 3},
            {3, "Jaqueta", "P", 3},
            {3, "Jaqueta", "P", 3},
            {3, "Jaqueta", "P", 3},
            {3, "Jaqueta", "P", 3},
            {3, "Jaqueta", "P", 3},
            {4, "Boné", "Único", 7}
        };

        // Largura individual de cada coluna
        int[] larguras = {50, 150, 100, 100, 50, 50};

        // Criando a tabela customizada
        JPanel tabela = components.criarTabelaPanel(dados, larguras);

        // Criando um JScrollPane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(20, 70, 518, 250); // Definindo a posição e o tamanho do JScrollPane

        // Adicionando o JScrollPane à janela principal
        add(scrollPane);

        // Botão Fazer Doação
        JButton adicionarDoacaoButton = components.criarBotao("+","<b>","#8C3A1C","Arial",Font.BOLD,30,Color.WHITE,20,319,518,40);
        add(adicionarDoacaoButton);

        // Criar o JLabel para a data de envio
        JLabel dataEnvioLabel = new JLabel("Data de Envio: ");
        dataEnvioLabel.setFont(new Font("Arial", Font.BOLD, 14));
        dataEnvioLabel.setBounds(20, 400, 250, 30);

        // Criar o campo de texto formatado com máscara
        JFormattedTextField dataEnvioField = new JFormattedTextField(createMaskFormatter("##/##/####"));
        dataEnvioField.setBounds(126, 400, 100, 30);
        dataEnvioField.setValue(""); // Iniciar sem valor

        // Adicionar os componentes à janela
        add(dataEnvioLabel);
        add(dataEnvioField);

        // Botão Fazer Doação
        JButton fazerDoacaoButton = components.criarBotao("Fazer Doação","<b>","#8C3A1C","Arial",Font.PLAIN,18,Color.WHITE,368,390,170,40);
        add(fazerDoacaoButton);


        // Tornar a janela visível
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(600,500);        
    }

        // Método para criar a máscara de formatação
    private static MaskFormatter createMaskFormatter(String mask) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(mask);
            formatter.setPlaceholderCharacter('_'); // Placeholder para caracteres não digitados
            formatter.setAllowsInvalid(false); // Não permite entradas inválidas
            formatter.setValueContainsLiteralCharacters(false); // Não considera caracteres literais no valor
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return formatter;
    }

    public static void main(String[] args) {
        new fazerDoacao();
    }
}
