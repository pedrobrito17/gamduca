package br.com.ifma.model;

import java.util.ArrayList;

/**
 *
 * @author Pedro Brito
 */
public class Quiz {
    
    private String tituloQuiz;
    private ArrayList<Fase> fases;
    //private Configurações config;

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
    
    
    

}
