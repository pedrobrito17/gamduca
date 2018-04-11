package br.com.ifma;


import br.com.ifma.view.FramePrincipal;
import java.awt.EventQueue;

/**
 *
 * @author Pedro Brito
 */
public class JogosEducativos {

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            FramePrincipal janelaPrincipal = new FramePrincipal();
        });
    }
    
}
