package br.com.ifma.model;

import java.io.Serializable;

/**
 *
 * @author Pedro Brito
 */
public class Questao implements Serializable{
    
    private String tituloQuestao;
    private Pergunta pergunta;
    private String tipoQuestao;
    private RespostaMultiplaEscolha respMulti;
    private RespostaPerguntaDireta respPerguntaDir;
    private RespostaVerdadeiroOuFalso respVerd;

    public Pergunta getPergunta() {
        return pergunta;
    }

    public void setPergunta(Pergunta pergunta) {
        this.pergunta = pergunta;
    }

    public RespostaMultiplaEscolha getRespMulti() {
        return respMulti;
    }

    public void setRespMulti(RespostaMultiplaEscolha respMulti) {
        this.respMulti = respMulti;
    }

    public RespostaPerguntaDireta getRespPerguntaDir() {
        return respPerguntaDir;
    }

    public void setRespPerguntaDir(RespostaPerguntaDireta respPerguntaDir) {
        this.respPerguntaDir = respPerguntaDir;
    }

    public RespostaVerdadeiroOuFalso getRespVerd() {
        return respVerd;
    }

    public void setRespVerd(RespostaVerdadeiroOuFalso respVerd) {
        this.respVerd = respVerd;
    }

    public String getTituloQuestao() {
        return tituloQuestao;
    }

    public void setTituloQuestao(String tituloQuestao) {
        this.tituloQuestao = tituloQuestao;
    }

    public String getTipoQuestao() {
        return tipoQuestao;
    }

    public void setTipoQuestao(String tipoQuestao) {
        this.tipoQuestao = tipoQuestao;
    }

}
