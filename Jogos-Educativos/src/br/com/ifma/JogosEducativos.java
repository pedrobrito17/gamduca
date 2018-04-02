package br.com.ifma;

import br.com.ifma.view.JanelaPrincipal;
import java.awt.EventQueue;

/**
 *
 * @author Pedro Brito
 */
public class JogosEducativos {

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                JanelaPrincipal janelaPrincipal = new JanelaPrincipal();
            }
        });
    }
    
}
