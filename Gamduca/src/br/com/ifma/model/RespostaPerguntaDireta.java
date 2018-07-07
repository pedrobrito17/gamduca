package br.com.ifma.model;

import java.io.Serializable;

/**
 *
 * @author Pedro Brito
 */
public class RespostaPerguntaDireta implements Serializable{
    
    private String resposta;

    public RespostaPerguntaDireta(String resp) {
        this.resposta = resp;
    }

    public String getResposta() {
        return resposta;
    }

    public void setResposta(String resposta) {
        this.resposta = resposta;
    }
    
    
}
