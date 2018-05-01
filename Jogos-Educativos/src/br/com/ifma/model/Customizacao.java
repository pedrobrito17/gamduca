package br.com.ifma.model;

import java.io.Serializable;

/**
 *
 * @author Pedro Brito
 */
public class Customizacao implements Serializable{
    
    private String msgAcerto, msgErro, msgAcertoParcial, msgTempoAcabou;
    private int tempo, taxaAcerto;
    private boolean ativarTempo;

    public Customizacao() {
        this.msgAcerto = "Parabéns! Você acertou";
        this.msgErro = "Infelizmente você errou";
        this.msgAcertoParcial = "Desculpe! Você não acertou todas as alternativas";
        this.msgTempoAcabou = "Seu tempo acabou";
        this.tempo = 0;
        this.taxaAcerto = 70;
        this.ativarTempo = false;
    }

    public boolean isAtivarTempo() {
        return ativarTempo;
    }

    public void setAtivarTempo(boolean ativarTempo) {
        this.ativarTempo = ativarTempo;
    }
    
    public String getMsgAcerto() {
        return msgAcerto;
    }

    public void setMsgAcerto(String msgAcerto) {
        this.msgAcerto = msgAcerto;
    }

    public String getMsgErro() {
        return msgErro;
    }

    public void setMsgErro(String msgErro) {
        this.msgErro = msgErro;
    }

    public String getMsgAcertoParcial() {
        return msgAcertoParcial;
    }

    public void setMsgAcertoParcial(String msgAcertoParcial) {
        this.msgAcertoParcial = msgAcertoParcial;
    }

    public String getMsgTempoAcabou() {
        return msgTempoAcabou;
    }

    public void setMsgTempoAcabou(String msgTempoAcabou) {
        this.msgTempoAcabou = msgTempoAcabou;
    }

    public int getTempo() {
        return tempo;
    }

    public void setTempo(int tempo) {
        this.tempo = tempo;
    }

    public int getTaxaAcerto() {
        return taxaAcerto;
    }

    public void setTaxaAcerto(int taxaAcerto) {
        this.taxaAcerto = taxaAcerto;
    }
    
    
    
}
