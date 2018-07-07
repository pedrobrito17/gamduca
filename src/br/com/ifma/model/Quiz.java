package br.com.ifma.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Pedro Brito
 */
public class Quiz implements Serializable{
    
    private String tituloQuiz;
    private ArrayList<Fase> fases;
    private Customizacao customizacao;

    public String getTituloQuiz() {
        return tituloQuiz;
    }

    public void setTituloQuiz(String tituloQuiz) {
        this.tituloQuiz = tituloQuiz;
    }

    public ArrayList<Fase> getFases() {
        return fases;
    }

    public void setFases(ArrayList<Fase> fases) {
        this.fases = fases;
    }

    public Customizacao getCustomizacao() {
        return customizacao;
    }

    public void setCustomizacao(Customizacao customizacao) {
        this.customizacao = customizacao;
    }
    
}
