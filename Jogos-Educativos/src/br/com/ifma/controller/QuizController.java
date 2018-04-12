package br.com.ifma.controller;

import br.com.ifma.model.Fase;
import br.com.ifma.model.Quiz;
import br.com.ifma.view.components.jpanel.JpFase;
import java.util.ArrayList;

/**
 *
 * @author Pedro Brito
 */
public class QuizController {
    
    private Quiz quiz;
    
    public QuizController(){
        this.quiz = new Quiz();
    }
    
    public void setTituloDoQuiz(String titulo){
        quiz.setTituloQuiz(titulo);
    }
    
    public void criarFasesDoQuiz(ArrayList<JpFase> jpFases){
        FaseController faseController = new FaseController();
        ArrayList<Fase> fases = faseController.recuperarFases(jpFases);
        quiz.setFases(fases);
    }
    
    public void setConfiguracoesDoQuiz(){
        
    }
    
    public Quiz getQuiz(){
        return this.quiz;
    }

}
