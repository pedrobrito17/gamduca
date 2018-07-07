package br.com.ifma.controller;

import br.com.ifma.builder.JpFaseBuilder;
import br.com.ifma.view.components.jpanel.JpFase;
import java.util.ArrayList;
import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Pedro Brito
 */
public class QuizControllerTest {

    @Test
    public void criarFasesDoQuiz() {
        //cenario
        JpFase jpFase = new JpFaseBuilder().obterJpFaseBuilder().getJpFase();
        ArrayList<JpFase> array = new ArrayList<>();
        array.add(jpFase);
        
        //ação
        QuizController quizController = new QuizController();
        quizController.setTituloDoQuiz("Meu quiz");
        quizController.criarFasesDoQuiz(array);
        
        //verificação dos paineis
        Assert.assertEquals(3, jpFase.getJpQuestoes().size());
        Assert.assertNotNull( jpFase.getJpQuestoes().get(2).getJpPergunta() );

        //verificação das fases
        Assert.assertEquals(1, quizController.getQuiz().getFases().size());
        
        //verificações da 1ª questão
        Assert.assertNotNull(quizController.getQuiz().getFases().get(0).getQuestoes().get("Questão 1") );
        Assert.assertEquals("Campeão da Copa do Mundo de 2002?", quizController.getQuiz().getFases().get(0).getQuestoes().get("Questão 1").getPergunta().getTxtPergunta() );
        Assert.assertEquals("/home/pedro/Imagens/carro.png", quizController.getQuiz().getFases().get(0).getQuestoes().get("Questão 1").getPergunta().getUrlMultimidia() );
        Assert.assertEquals("A", quizController.getQuiz().getFases().get(0).getQuestoes().get("Questão 1").getRespMulti().getRespostaCorreta() );
        
        //verifica a quantidade de questões
        Assert.assertEquals(3, quizController.getQuiz().getFases().get(0).getQuestoes().size() );
        
        //verificação da 4ª questão
        Assert.assertNull( quizController.getQuiz().getFases().get(0).getQuestoes().get("Questão 4") );
    }

    
}
