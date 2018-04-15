package br.com.ifma.controller;

import br.com.ifma.model.Fase;
import br.com.ifma.model.Pergunta;
import br.com.ifma.model.Questao;
import br.com.ifma.model.RespostaMultiplaEscolha;
import br.com.ifma.model.RespostaPerguntaDireta;
import br.com.ifma.model.RespostaVerdadeiroOuFalso;
import br.com.ifma.view.components.jpanel.JpFase;
import br.com.ifma.view.components.jpanel.JpQuestao;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author Pedro Brito
 */
public class FaseController {

    private ArrayList<Fase> fases;

    public FaseController() {
    }

    public ArrayList<Fase> recuperarFases(ArrayList<JpFase> jpFases) {

        fases = new ArrayList();
        for (JpFase jpFase : jpFases) {
            fases.add(recuperarFaseSemVerificarSeQuestaoEstaCompleta(jpFase));
        }
        return fases;
    }

    private Fase recuperarFaseSemVerificarSeQuestaoEstaCompleta(JpFase jpFase) {
        ArrayList<JpQuestao> jpQuestoes = jpFase.getJpQuestoes();
        
        HashMap<String, Questao> map = new HashMap<>();
        for (JpQuestao jpQuestao : jpQuestoes) {
            
            Questao questao;
            Pergunta pergunta;

            String tituloQuestao = jpQuestao.getTituloQuestao();
            String txtPergunta = jpQuestao.getJpPergunta().getTxtPergunta();
            String urlMultimidia = jpQuestao.getJpPergunta().getUrlMultimidia();
            String tipoQuestao = jpQuestao.getJpPergunta().getTipoResposta();

            pergunta = getPerguntaDaQuestao(tituloQuestao, tipoQuestao,
                    txtPergunta, urlMultimidia);

            questao = new Questao();
            questao.setTituloQuestao(tituloQuestao);
            questao.setPergunta(pergunta);
            questao.setTipoQuestao(tipoQuestao);

            switch (tipoQuestao) {
                case "Múltipla escolha": {
                    String respA = jpQuestao.getJpRespostaMultiplaEscolha().getRespA();
                    String respB = jpQuestao.getJpRespostaMultiplaEscolha().getRespB();
                    String respC = jpQuestao.getJpRespostaMultiplaEscolha().getRespC();
                    String respD = jpQuestao.getJpRespostaMultiplaEscolha().getRespD();
                    String respCorreta = jpQuestao.getJpRespostaMultiplaEscolha().getRespCorreta();

                    RespostaMultiplaEscolha respMulti
                            = new RespostaMultiplaEscolha(respA, respB,
                                    respC, respD, respCorreta);

                    questao.setRespMulti(respMulti);

                    break;
                }
                case "Pergunta direta":
                    String resp = jpQuestao.getJpRespostaPerguntaDireta().getResposta();
                    RespostaPerguntaDireta respPerg
                            = new RespostaPerguntaDireta(resp);

                    questao.setRespPerguntaDir(respPerg);

                    break;
                case "Verdadeiro ou falso": {
                    String[] respA = jpQuestao.getJpRespostaVerdadeiroOuFalso().getRespA();
                    String[] respB = jpQuestao.getJpRespostaVerdadeiroOuFalso().getRespB();
                    String[] respC = jpQuestao.getJpRespostaVerdadeiroOuFalso().getRespC();
                    String[] respD = jpQuestao.getJpRespostaVerdadeiroOuFalso().getRespD();

                    RespostaVerdadeiroOuFalso respVF
                            = new RespostaVerdadeiroOuFalso(respA, respB, respC, respD);

                    questao.setRespVerd(respVF);

                    break;
                }
                default:
                    break;
            }
            map.put(questao.getTituloQuestao(), questao);
        }
        return new Fase(map);
    }

    private Fase recuperarFaseVerificandoSeQuestaoEstaCompleta(JpFase jpFase) {
        ArrayList<JpQuestao> jpQuestoes = jpFase.getJpQuestoes();
        HashMap<String, Questao> map = new HashMap<>();
        for (JpQuestao jpQuestao : jpQuestoes) {

            Questao questao;
            Pergunta pergunta;

            if (jpQuestao.questaoCompleta()) {
                String tituloQuestao = jpQuestao.getTituloQuestao();
                String txtPergunta = jpQuestao.getJpPergunta().getTxtPergunta();
                String urlMultimidia = jpQuestao.getJpPergunta().getUrlMultimidia();
                String tipoQuestao = jpQuestao.getJpPergunta().getTipoResposta();

                pergunta = getPerguntaDaQuestao(tituloQuestao, tipoQuestao,
                        txtPergunta, urlMultimidia);

                questao = new Questao();
                questao.setTituloQuestao(tituloQuestao);
                questao.setPergunta(pergunta);
                questao.setTipoQuestao(tipoQuestao);

                switch (tipoQuestao) {
                    case "Múltipla escolha": {
                        String respA = jpQuestao.getJpRespostaMultiplaEscolha().getRespA();
                        String respB = jpQuestao.getJpRespostaMultiplaEscolha().getRespB();
                        String respC = jpQuestao.getJpRespostaMultiplaEscolha().getRespC();
                        String respD = jpQuestao.getJpRespostaMultiplaEscolha().getRespD();
                        String respCorreta = jpQuestao.getJpRespostaMultiplaEscolha().getRespCorreta();

                        RespostaMultiplaEscolha respMulti
                                = new RespostaMultiplaEscolha(respA, respB,
                                        respC, respD, respCorreta);

                        questao.setRespMulti(respMulti);

                        break;
                    }
                    case "Pergunta direta":
                        String resp = jpQuestao.getJpRespostaPerguntaDireta().getResposta();
                        RespostaPerguntaDireta respPerg
                                = new RespostaPerguntaDireta(resp);

                        questao.setRespPerguntaDir(respPerg);

                        break;
                    case "Verdadeiro ou falso": {
                        String[] respA = jpQuestao.getJpRespostaVerdadeiroOuFalso().getRespA();
                        String[] respB = jpQuestao.getJpRespostaVerdadeiroOuFalso().getRespB();
                        String[] respC = jpQuestao.getJpRespostaVerdadeiroOuFalso().getRespC();
                        String[] respD = jpQuestao.getJpRespostaVerdadeiroOuFalso().getRespD();

                        RespostaVerdadeiroOuFalso respVF
                                = new RespostaVerdadeiroOuFalso(respA, respB, respC, respD);

                        questao.setRespVerd(respVF);

                        break;
                    }
                    default:
                        break;
                }
                map.put(questao.getTituloQuestao(), questao);
            }
        }
        return new Fase(map);
    }

    private Pergunta getPerguntaDaQuestao(String tituloQuestao, String tipoQuestao, String txtPergunta, String urlMultimidia) {

        Pergunta pergunta = new Pergunta();
        pergunta.setTxtPergunta(txtPergunta);
        pergunta.setUrlMultimidia(urlMultimidia);
        pergunta.setTipoMultimidia(pergunta.descobrirTipoMultimidia(urlMultimidia));
        return pergunta;
    }

}
