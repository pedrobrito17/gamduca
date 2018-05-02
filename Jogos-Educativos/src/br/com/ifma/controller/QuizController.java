package br.com.ifma.controller;

import br.com.ifma.model.Customizacao;
import br.com.ifma.model.Fase;
import br.com.ifma.model.Questao;
import br.com.ifma.model.Quiz;
import br.com.ifma.view.FrameQuiz;
import br.com.ifma.view.components.filter.FiltroFileChooserQuiz;
import br.com.ifma.view.components.jpanel.JpFase;
import br.com.ifma.view.components.jpanel.JpQuestao;
import br.com.ifma.view.components.utils.FilterUtils;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Brito
 */
public class QuizController {

    private final Quiz quiz;
    private Customizacao customizacao;
    private String path;

    public QuizController() {
        quiz = new Quiz();
    }

    public Quiz getQuiz() {
        return quiz;
    }

    public void setTituloDoQuiz(String titulo) {
        quiz.setTituloQuiz(titulo);
    }

    public void setCustomizacao(Customizacao customizacao) {
        this.customizacao = customizacao;
    }

    public void obterDiretorio(JFrame frame) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.addChoosableFileFilter(new FiltroFileChooserQuiz());
        fc.setAcceptAllFileFilterUsed(false);
        fc.setDialogTitle("Salvar quiz");
        fc.setApproveButtonText("Salvar");
        fc.setSelectedFile(new File("quiz"));

        int retorno = fc.showOpenDialog(frame);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();
            path = file.getPath() + "." + FilterUtils.JQZ;
        }
    }

    public void criarFasesDoQuiz(ArrayList<JpFase> jpFases) {
        FaseController faseController = new FaseController();
        ArrayList<Fase> fases = faseController.recuperarFases(jpFases);
        quiz.setFases(fases);
    }

    public void salvarQuiz(ArrayList<JpFase> jpFases) {

        criarFasesDoQuiz(jpFases);

        try {
            FileOutputStream savarArquivo = new FileOutputStream(path);
            ObjectOutputStream stream = new ObjectOutputStream(savarArquivo);

            stream.writeObject(quiz);
            stream.writeObject(customizacao);
            stream.close();

        } catch (IOException ex) {
            System.out.println(ex.toString());
            JOptionPane.showMessageDialog(null, "Não foi possível salvar o "
                    + "arquivo", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void abrirArquivoQuiz(String pathFile, FrameQuiz frame, Customizacao customizador) {
        try {
            FileInputStream abrirArquivo = new FileInputStream(pathFile);
            ObjectInputStream stream = new ObjectInputStream(abrirArquivo);

            Quiz abrirQuiz = (Quiz) stream.readObject();
            customizacao = (Customizacao) stream.readObject();
            stream.close();

            inserirDadosNoQuiz(abrirQuiz, frame);
            inserirCustomizacao(customizador);

        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Arquivo não encontrado",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IOException | ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Erro inesperado",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void inserirCustomizacao(Customizacao customizador) {
        customizador.setAtivarTempo(customizacao.isAtivarTempo());
        customizador.setMsgAcerto(customizacao.getMsgAcerto());
        customizador.setMsgAcertoParcial(customizacao.getMsgAcertoParcial());
        customizador.setMsgErro(customizacao.getMsgErro());
        customizador.setMsgTempoAcabou(customizacao.getMsgTempoAcabou());
        customizador.setTaxaAcerto(customizacao.getTaxaAcerto());
        customizador.setTempo(customizacao.getTempo());
    }

    private void inserirDadosNoQuiz(Quiz fileQuiz, FrameQuiz frame) {

        ArrayList<Fase> fases = fileQuiz.getFases();
        int indexFase = 1;

        for (Fase fase : fases) {
            ArrayList<JpQuestao> jpQuestoes = new ArrayList<>();
            HashMap<String, Questao> questoes = fase.getQuestoes();

            for (int i = 0; i < questoes.size(); i++) {

                int numQuestao = i + 1;
                Questao questao = questoes.get("Questão " + numQuestao);
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

    private ArrayList<JpQuestao> adicionaQuestaoCasoNãoTenha3(ArrayList<JpQuestao> jpQuestoes) {
        if (jpQuestoes.size() == 1) {
            jpQuestoes.add(new JpQuestao(2));
            jpQuestoes.add(new JpQuestao(3));
        } else if (jpQuestoes.size() == 2) {
            jpQuestoes.add(new JpQuestao(3));
        }
        return jpQuestoes;
    }
}
