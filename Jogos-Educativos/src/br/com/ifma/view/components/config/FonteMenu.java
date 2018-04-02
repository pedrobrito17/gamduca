package br.com.ifma.view.components.config;

/**
 *
 * @author Pedro Brito
 */
public enum FonteMenu {
    
    FONTE("Verdana"),
    TAMANHO(12);
    
    private String fonte;
    private int tamanhoDaFonte;
    
    FonteMenu(String fonte){
        this.fonte = fonte;
    }
    
    FonteMenu(int tamanho){
        this.tamanhoDaFonte = tamanho;
    }

    public String getFonte() {
        return fonte;
    }

    public int getTamanhoDaFonte() {
        return tamanhoDaFonte;
    }    
            
}
