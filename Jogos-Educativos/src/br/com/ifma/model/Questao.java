package br.com.ifma.model;

/**
 *
 * @author Pedro Brito
 */
public class Questao {
    
    private String tituloQuestao;
    private Pergunta pergunta;
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
    
    
    
    public Object respostaDaQuestao(){
        if(respMulti!=null){
            return respMulti;
        }
        else if(respPerguntaDir!=null){
            return respPerguntaDir;
        }
        else if(respVerd!=null){
            return respVerd;
        }
        return null;
    }

}
