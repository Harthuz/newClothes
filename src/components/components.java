package components;

import javax.swing.*;
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

}
