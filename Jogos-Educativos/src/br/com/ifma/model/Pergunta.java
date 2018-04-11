package br.com.ifma.model;

/**
 *
 * @author Pedro Brito
 */
public class Pergunta {
    
    private String pergunta;
    private String urlMultimidia;
    private String tipoMultimidia;

    public String getPergunta() {
        return pergunta;
    }

    public void setPergunta(String pergunta) {
        this.pergunta = pergunta;
    }

    public String getUrlMultimidia() {
        return urlMultimidia;
    }

    public void setUrlMultimidia(String urlMultimidia) {
        this.urlMultimidia = urlMultimidia;
    }

    public String getTipoMultimidia() {
        return tipoMultimidia;
    }

    public void setTipoMultimidia(String tipoMultimidia) {
        this.tipoMultimidia = tipoMultimidia;
    }
    
    public boolean possuiMultimidia(){
        if(!urlMultimidia.isEmpty()){
            return true;
        }
        return false;
    }
}
