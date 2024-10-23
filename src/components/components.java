package components;

import javax.swing.*;

import newclothes.fazerDoacao;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent; // Certifique-se de que este é o correto


public class components extends JFrame{
        public JLabel criarLabel(String texto, String tagHtml, String fonte, int tamanho, int estilo, int x, int y, int largura, int altura) {
        // Adiciona a tag HTML ao texto
        String textoFormatado = "<html>" + tagHtml + texto + tagHtml.replace("<", "</") + "</html>";
        
        // Cria o JLabel com o texto formatado
        JLabel label = new JLabel(textoFormatado);
        
        // Configura a fonte
        label.setFont(new Font(fonte, estilo, tamanho));
        
        // Configura a posição e o tamanho do JLabel
        label.setBounds(x, y, largura, altura);
        
        return label;
    }

    // Função para criar um JButton personalizado
    public JButton criarBotao(String texto, String tagHtml, String corFundoHex, String fonte, int estilo, int tamanhoFonte, Color corTexto, int x, int y, int largura, int altura) {
        // Formata o texto com a tag HTML
        String textoFormatado = "<html>" + tagHtml + texto + tagHtml.replace("<", "</") + "</html>";
        
        // Cria o botão com o texto formatado
        JButton botao = new JButton(textoFormatado);
        
        // Configura a cor de fundo usando hexadecimal
        botao.setBackground(Color.decode(corFundoHex));
        
        // Configura a fonte
        botao.setFont(new Font(fonte, estilo, tamanhoFonte));
        
        // Configura a cor do texto
        botao.setForeground(corTexto);
        
        // Define a posição e o tamanho do botão
        botao.setBounds(x, y, largura, altura);

        // Remover efeitos de foco e borda
        botao.setFocusPainted(false); // Remove a borda quando o botão tem foco
        botao.setBorderPainted(false); // Remove a borda do botão

        // Adiciona o MouseListener para alterar o cursor
        botao.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                // Muda o cursor para a mão quando o mouse está sobre o botão
                botao.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // Retorna ao cursor padrão quando o mouse sai do botão
                botao.setCursor(Cursor.getDefaultCursor());
            }
        });

        return botao;
    }


    // Função que cria o painel da tabela
    public static JPanel criarTabelaPanel(Object[][] dados, int[] larguras) {
        JPanel tabela = new JPanel();
        tabela.setLayout(null); // Usando layout absoluto (null)
        int numLinhas = dados.length;
        int numColunas = 6; // ID, Item, Tamanho, Quantidade, Alterar, Excluir

        int alturaCelula = 40; // Altura padrão das células
        int posX = 0; // Posição X inicial das células
        int posY = 0; // Posição Y inicial das células

        // Criando cabeçalhos manualmente (pode ser adaptado conforme as colunas)
        String[] colunas = {"ID", "ITEM", "TAMANHO", "QUANTIDADE", "ALT", "EXC"};

        // Criando cabeçalho
        for (int i = 0; i < numColunas; i++) {
            JLabel header = new JLabel(colunas[i], SwingConstants.CENTER);
            header.setOpaque(true);
            header.setBackground(Color.WHITE);
            header.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            header.setBounds(posX, posY, larguras[i], alturaCelula); // Definindo posição e largura da célula
            header.setFont(new Font("Arial", Font.BOLD, 13)); // Aumentando a fonte e definindo negrito
            header.setText(header.getText().toUpperCase()); // Definindo texto em maiúsculas
            tabela.add(header);
            posX += larguras[i]; // Ajustando a posição X para a próxima célula
        }

        posY += alturaCelula; // Movendo para a linha seguinte (conteúdo)

        // Criando as células de conteúdo
        for (int i = 0; i < numLinhas; i++) {
            posX = 0; // Reiniciando a posição X para cada nova linha
            for (int j = 0; j < numColunas; j++) {
                if (j == 4 || j == 5) {
                    // Adicionando botões com imagens nas últimas colunas (Alterar e Excluir)
                    ImageIcon icon = new ImageIcon(fazerDoacao.class.getResource("/newclothes/imagens/" + (j == 4 ? "alterar.png" : "excluir.png")));
                    JLabel imagemLabel = new JLabel(icon);
                    imagemLabel.setBounds(posX, posY, larguras[j], alturaCelula);
                    imagemLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    imagemLabel.setOpaque(true);
                    imagemLabel.setBackground(Color.WHITE);

                    // Adicionando o cursor de mão para o JLabel da imagem
                    imagemLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                    final int linha = i; // Armazena a linha atual
                    final int coluna = j; // Armazena a coluna atual
                    final int idItem = (int) dados[i][0]; // Obtendo o ID do item da linha atual

                    // Adicionando o MouseListener para capturar o clique
                    imagemLabel.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (coluna == 4) {
                                System.out.println("Alterar clicado na linha " + linha + " com ID: " + idItem);
                                // Aqui você pode chamar a função para alterar, passando o idItem
                            } else if (coluna == 5) {
                                System.out.println("Excluir clicado na linha " + linha + " com ID: " + idItem);
                                // Aqui você pode chamar a função para excluir, passando o idItem
                            }
                        }
                    });

                    tabela.add(imagemLabel);
                } else {
                    // Adicionando os dados (ID, Item, Tamanho, Quantidade)
                    JLabel celula = new JLabel(dados[i][j].toString().toUpperCase(), SwingConstants.CENTER);
                    celula.setOpaque(true);
                    celula.setBackground(Color.WHITE);
                    celula.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    celula.setBounds(posX, posY, larguras[j], alturaCelula); // Definindo posição e largura da célula
                    celula.setFont(new Font("Arial", Font.BOLD, 15)); // Aumentando a fonte e definindo negrito
                    tabela.add(celula);
                }
                posX += larguras[j]; // Ajustando a posição X para a próxima célula
            }
            posY += alturaCelula; // Movendo para a próxima linha
        }

        // Definindo o tamanho do painel com base no conteúdo
        tabela.setPreferredSize(new Dimension(posX, posY));
        tabela.setBounds(0, 0, posX, posY); // Ajustando o tamanho do painel

        return tabela;
    }

}
