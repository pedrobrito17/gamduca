package br.com.ifma.view.components.utils;

import java.awt.Font;

/**
 *
 * @author Pedro Brito
 */
public class Fonte {
    
    public static Font retornarFontePadrao(){
        Font font = new Font("Verdana", Font.PLAIN, 12);
        return font;
    }
    
    public static Font retornarFontePadraoNegrito(){
        Font font = new Font("Verdana", Font.BOLD, 12);
        return font;
    }

}
