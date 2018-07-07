package br.com.ifma.model;

import java.io.Serializable;

/**
 *
 * @author Pedro Brito
 */
public class Pergunta implements Serializable{
    
    private String txtPergunta;
    private String urlMultimidia;
    private String tipoMultimidia;

    public String getTxtPergunta() {
        return txtPergunta;
    }

    public void setTxtPergunta(String txtPergunta) {
        this.txtPergunta = txtPergunta;
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
        return !urlMultimidia.isEmpty();
    }

    public String descobrirTipoMultimidia(String url) {
        if(url == null || url.isEmpty()){
            return null;
        }
        else if(url.endsWith(".flv") || url.endsWith(".mkv") || url.endsWith(".mov")
                || url.endsWith(".wav") || url.endsWith(".mp4")){
            return "video";
        }
        else if( url.endsWith(".gif") || url.endsWith(".jpeg") || url.endsWith(".jpg")
                || url.endsWith(".png") ){
            return "imagem";
        }
        else if(url.endsWith(".aac") || url.endsWith(".ogc") || url.endsWith(".wav")
                || url.endsWith(".wma") || url.endsWith(".mp3")){
            return "audio";
        }
        else{
            return "link";
        }
    }
    
    
}
