package br.com.ifma.view.components;

import br.com.ifma.view.components.utils.Fonte;
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
        this.setFont(Fonte.retornarFontePadrao());
        this.setFocusPainted(false);
        this.setOpaque(false);
        this.setContentAreaFilled(false);
    }

}
