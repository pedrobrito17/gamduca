package br.com.ifma;


import br.com.ifma.view.FramePrincipal;
import javax.swing.SwingUtilities;

/**
 *
 * @author Pedro Brito
 */
public class JogosEducativos {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FramePrincipal janelaPrincipal = new FramePrincipal();
        });
    }

}