package br.com.ifma.view.components;

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
        this.setFont(new Font("Verdana", Font.PLAIN, 12));
        this.setFocusPainted(false);
    }

}
