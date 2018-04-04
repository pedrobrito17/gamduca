package br.com.ifma.view.components.config;

/**
 *
 * @author Pedro Brito
 */
public enum Fonte {
    
    FONTE("Verdana"),
    TAMANHO(12);
    
    private String fonte;
    private int tamanhoDaFonte;
    
    Fonte(String fonte){
        this.fonte = fonte;
    }
    
    Fonte(int tamanho){
        this.tamanhoDaFonte = tamanho;
    }

    public String getFonte() {
        return fonte;
    }

    public int getTamanhoDaFonte() {
        return tamanhoDaFonte;
    }    
            
}
