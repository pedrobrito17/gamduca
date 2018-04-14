package br.com.ifma.controller;

import br.com.ifma.model.Fase;
import br.com.ifma.model.Questao;
import br.com.ifma.model.Quiz;
import br.com.ifma.view.FrameQuiz;
import br.com.ifma.view.components.jpanel.JpFase;
import br.com.ifma.view.components.jpanel.JpQuestao;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Brito
 */
public class QuizController {

    private final Quiz quiz;

    public QuizController() {
        this.quiz = new Quiz();
    }

    public Quiz getQuiz() {
        return this.quiz;
    }

    public void setTituloDoQuiz(String titulo) {
        this.quiz.setTituloQuiz(titulo);
    }

    public void criarFasesDoQuiz(ArrayList<JpFase> jpFases) {
        FaseController faseController = new FaseController();
        ArrayList<Fase> fases = faseController.recuperarFases(jpFases);
        this.quiz.setFases(fases);
    }

    public void setConfiguracoesDoQuiz() {

    }

    public void salvarQuiz(String path, ArrayList<JpFase> jpFases) {

        criarFasesDoQuiz(jpFases);

        try {
            FileOutputStream savarArquivo = new FileOutputStream(path);
            ObjectOutputStream stream = new ObjectOutputStream(savarArquivo);

            stream.writeObject(this.quiz);
            stream.close();

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Não foi possível salvar o "
                    + "arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
        }

    }

    public void abrirQuiz(String pathFile, FrameQuiz frame) {
        try {
            FileInputStream abrirArquivo = new FileInputStream(pathFile);
            ObjectInputStream stream = new ObjectInputStream(abrirArquivo);

            Quiz abrirQuiz = (Quiz) stream.readObject();
            stream.close();

            inserirDadosNoQuiz(abrirQuiz, frame);

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro inesperado",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void inserirDadosNoQuiz(Quiz fileQuiz, FrameQuiz frame) {

        ArrayList<Fase> fases = fileQuiz.getFases();
        int indexFase = 1;
        
        for (Fase fase : fases) {
            ArrayList<JpQuestao> jpQuestoes = new ArrayList<>();
            HashMap<String, Questao> questaoes = fase.getQuestoes();

            for (Map.Entry<String, Questao> entry : questaoes.entrySet()) {

                String key = entry.getKey();
                String num = key.substring(key.length() - 1);
                int numQuestao = Integer.parseInt(num);

                Questao questao = entry.getValue();
                JpQuestao jpQuestao = new JpQuestao(numQuestao);
                jpQuestao.getJpPergunta().setTituloQuestao(
                        "Questão " + numQuestao);
                jpQuestao.getJpPergunta().setTipoResposta(
                        questao.getTipoQuestao());
                jpQuestao.getJpPergunta().setTxtPergunta(
                        questao.getPergunta().getTxtPergunta());
                jpQuestao.getJpPergunta().setUrlMultimidia(
                        questao.getPergunta().getUrlMultimidia());

                switch (questao.getTipoQuestao()) {
                    case "Múltipla escolha":
                        jpQuestao.getJpRespostaMultiplaEscolha().setRespA(
                                questao.getRespMulti().getRespA());
                        jpQuestao.getJpRespostaMultiplaEscolha().setRespB(
                                questao.getRespMulti().getRespB());
                        jpQuestao.getJpRespostaMultiplaEscolha().setRespC(
                                questao.getRespMulti().getRespC());
                        jpQuestao.getJpRespostaMultiplaEscolha().setRespD(
                                questao.getRespMulti().getRespD());
                        jpQuestao.getJpRespostaMultiplaEscolha().setRespCorreta(
                                questao.getRespMulti().getRespostaCorreta());
                        break;
                    case "Pergunta direta":
                        jpQuestao.getJpRespostaPerguntaDireta().setResposta(
                                questao.getRespPerguntaDir().getResposta());
                        break;
                    case "Verdadeiro ou falso":
                        jpQuestao.getJpRespostaVerdadeiroOuFalso().setRespA(
                                questao.getRespVerd().getRespA());
                        jpQuestao.getJpRespostaVerdadeiroOuFalso().setRespB(
                                questao.getRespVerd().getRespB());
                        jpQuestao.getJpRespostaVerdadeiroOuFalso().setRespC(
                                questao.getRespVerd().getRespC());
                        jpQuestao.getJpRespostaVerdadeiroOuFalso().setRespD(
                                questao.getRespVerd().getRespD());
                        break;
                    default:
                        break;
                }
                jpQuestoes.add(jpQuestao);

            }
            frame.inserirTituloNoQuiz(fileQuiz.getTituloQuiz());
            
            JpFase jpFase = frame.retornarJpFase(indexFase);
            jpFase.setJpQuestoes(adicionaQuestaoCasoNãoTenha3(jpQuestoes));
            jpFase.recriarCardLayoutComQuestoesDoQuizSalvo();
            if (indexFase == 2 || indexFase == 3) {
                frame.adicionarFase();
            }
            indexFase++;
        }
    }

    public ArrayList<JpQuestao> adicionaQuestaoCasoNãoTenha3(ArrayList<JpQuestao> jpQuestoes) {
        if(jpQuestoes.size()==1){
            jpQuestoes.add(new JpQuestao(2));
            jpQuestoes.add(new JpQuestao(3));
        }
        else if(jpQuestoes.size()==2){
            jpQuestoes.add(new JpQuestao(3));
        }
        return jpQuestoes;
    }
}
