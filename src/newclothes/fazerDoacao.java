package newclothes;

import javax.swing.*;
import javax.swing.text.MaskFormatter;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import newclothes.doacao.*;
import components.components;
import conexao.conexao;

public class fazerDoacao extends JFrame {
    private conexao con_cliente;
    private components components;
    private DoacaoDAO doacaoDAO;
    private ItemDoacaoDAO itemDoacaoDAO;

    public fazerDoacao() {
        components = new components();
        con_cliente = new conexao();
        con_cliente.conecta();

        doacaoDAO = new DoacaoDAO(con_cliente);
        itemDoacaoDAO = new ItemDoacaoDAO(con_cliente);

        // Get the last donation ID safely
        int idDoacao = doacaoDAO.getLastDonationId();

        // Adicionando WindowListener
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                doacaoDAO.deleteLastDonation(idDoacao);
            }
        });

        // Configuração da janela
        setTitle("Fazer Doação");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);  // Usando layout nulo para setBounds

        String numeroString = String.valueOf(idDoacao);

        // Label Titulo Página
        JLabel doacaoLabel = components.criarLabel("SUA DOAÇÃO CONTÉM OS ITENS:", "<u>", "Arial", 14, Font.BOLD, 20, 20, 300, 30);
        add(doacaoLabel);

        ArrayList<Object[]> listaDados = itemDoacaoDAO.fetchDonationItems(idDoacao);

        Object[][] dados = listaDados.toArray(new Object[0][]);
        int[] larguras = {50, 150, 100, 100, 50, 50};

        // Criando a tabela customizada
        JPanel tabela = components.criarTabelaPanel(dados, larguras);

        // Criando um JScrollPane para permitir rolagem
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(20, 70, 518, 250); // Definindo a posição e o tamanho do JScrollPane

        // Adicionando o JScrollPane à janela principal
        add(scrollPane);

        // Botão adicionar doacao
        JButton adicionarDoacaoButton = components.criarBotao("+", "<b>", "#8C3A1C", "Arial", Font.BOLD, 30, Color.WHITE, 20, 319, 518, 40);
        add(adicionarDoacaoButton);
        adicionarDoacaoButton.addActionListener(e -> {
            adicionarItem dialog = new adicionarItem(this);


        });

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
        JButton fazerDoacaoButton = components.criarBotao("Fazer Doação", "<b>", "#8C3A1C", "Arial", Font.PLAIN, 18, Color.WHITE, 368, 390, 170, 40);
        add(fazerDoacaoButton);

        // Tornar a janela visível
        setVisible(true);
        setLocationRelativeTo(null);
    }

    private static MaskFormatter createMaskFormatter(String mask) {
        MaskFormatter formatter = null;
        try {
            formatter = new MaskFormatter(mask);
            formatter.setPlaceholderCharacter('_'); // Placeholder para caracteres não digitados
            formatter.setAllowsInvalid(false); // Não permite entradas inválidas
            formatter.setValueContainsLiteralCharacters(false); // Não considera caracteres literais no valor
        } catch (Exception e) {
            e.printStackTrace();
        }
        return formatter;
    }

    void atualizarTabela() {
        // Obter o ID da última doação novamente
        int idDoacao = doacaoDAO.getLastDonationId();
        
        // Buscar os novos dados da tabela
        ArrayList<Object[]> listaDados = itemDoacaoDAO.fetchDonationItems(idDoacao);
        Object[][] dados = listaDados.toArray(new Object[0][]);
        
        // Atualizar a tabela existente
        JPanel tabelaPanel = components.criarTabelaPanel(dados, new int[]{50, 150, 100, 100, 50, 50});
        
        // Remover o JScrollPane atual e adicionar um novo com os dados atualizados
        remove(0); // Remove o JScrollPane da tabela anterior
        JScrollPane scrollPane = new JScrollPane(tabelaPanel);
        scrollPane.setBounds(20, 70, 518, 250); // Redefinindo a posição e o tamanho do JScrollPane
        add(scrollPane);
        revalidate(); // Revalidar o layout para mostrar a nova tabela
        repaint(); // Repaint para garantir que a nova tabela apareça
    }
    

    public static void main(String[] args) {
        new fazerDoacao();
    }
}
