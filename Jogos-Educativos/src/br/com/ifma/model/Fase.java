package br.com.ifma.model;

import java.util.ArrayList;

/**
 *
 * @author Pedro Brito
 */
public class Fase {
    
    private ArrayList<Questao> questoes;
    
    public Fase(){
        
    }

    public ArrayList<Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(ArrayList<Questao> questoes) {
        this.questoes = questoes;
    }

    
}
