package br.com.ifma.view.components.utils;

import br.com.ifma.view.FramePrincipal;
import javax.swing.ImageIcon;

/**
 *
 * @author Pedro Brito
 */
public class Icone {

    public static ImageIcon retornarImageIcon(String path) {
        java.net.URL imgURL = FramePrincipal.class.getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Arquivo não encontrado: " + path);
            return null;
        }
    }

}
