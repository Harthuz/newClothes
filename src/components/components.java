package components;

import javax.swing.*;
import java.awt.*;


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
        
        return botao;
    }    
}
