package br.com.ifma.model;

import java.io.Serializable;
import java.util.HashMap;

/**
 *
 * @author Pedro Brito
 */
public class Fase implements Serializable{
    
    private HashMap<String, Questao> questoes;
    
    public Fase(HashMap<String, Questao> questoes){
        this.questoes = questoes;
    }

    public HashMap<String, Questao> getQuestoes() {
        return questoes;
    }

    public void setQuestoes(HashMap<String, Questao> questoes) {
        this.questoes = questoes;
    }
    
    
    
}
