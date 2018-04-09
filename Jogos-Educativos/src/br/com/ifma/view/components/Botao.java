package br.com.ifma.view.components;

import br.com.ifma.view.components.config.Fonte;
import java.awt.Font;
import javax.swing.Icon;
import javax.swing.JButton;

/**
 *
 * @author Pedro Brito
 */
public class Botao extends JButton{

    public Botao(String text) {
        super(text);
    }

    public Botao() {
    }

    public Botao(Icon icon) {
        super(icon);
    }
    
    public void configurarBotao(){
        this.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
    }

}
