package br.com.ifma.view.components.jpanel;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Pedro Brito
 */
public class JpQuestao extends JPanel implements Comparable<JpQuestao>{
    
    private String tituloQuestao;
    private JpPergunta jpPergunta;
    private JpTiposRespostas jpTipoQuestao;

    public JpQuestao(int posicao) {
        configPanelResposta();
        configPanelPergunta();
        configPanelQuestao();
        configTituloQuestao(posicao);
    }
    
    private void configPanelPergunta(){
        jpPergunta = new JpPergunta(jpTipoQuestao);
    }
    
    private void configPanelResposta(){
        jpTipoQuestao = new JpTiposRespostas();
    }
    
    private void configPanelQuestao(){
        this.setLayout(new BorderLayout());
        this.add(jpPergunta, BorderLayout.NORTH);
        this.add(jpTipoQuestao, BorderLayout.CENTER);
    }
    
    private void configTituloQuestao(int posicao){
        this.tituloQuestao = "Questão "+posicao;
        jpPergunta.setTituloQuestao("Questão "+posicao);
    }
    
    public void setTituloQuestao(int posicao){
        this.tituloQuestao = "Questão "+posicao;
        jpPergunta.setTituloQuestao("Questão "+posicao);
    }
    
    public String getTituloQuestao(){
        return jpPergunta.getTituloQuestao();
    }

    public JpPergunta getJpPergunta() {
        return jpPergunta;
    }
    
    public JpRespostaMultiplaEscolha getJpRespostaMultiplaEscolha(){
        return jpTipoQuestao.getJpRespostaMultiplaEscolha();
    }
    
    public JpRespostaPerguntaDireta getJpRespostaPerguntaDireta(){
        return jpTipoQuestao.getJpRespostaPerguntaDireta();
    }
    
    public JpRespostaVerdadeiroOuFaso getJpRespostaVerdadeiroOuFalso(){
        return jpTipoQuestao.getJpRespostaVerdadeiroOuFaso();
    }
    
    public boolean questaoCompleta(){
        switch(jpPergunta.getTipoResposta()){
            case "Múltipla escolha":
                return jpTipoQuestao.getJpRespostaMultiplaEscolha()
                        .respostasCompletas() &&
                        jpPergunta.perguntaCompleta();
            case "Pergunta direta":
                return jpTipoQuestao.getJpRespostaPerguntaDireta()
                        .respostaCompleta() &&
                        jpPergunta.perguntaCompleta();
            case "Verdadeiro ou falso":
                return jpTipoQuestao.getJpRespostaVerdadeiroOuFaso()
                        .respostasCompletas() &&
                        jpPergunta.perguntaCompleta();
            default:
                return false;
        }
    }

    @Override
    public int compareTo(JpQuestao jp) {
        return this.tituloQuestao.compareTo(jp.tituloQuestao);
    }
    
    
    
}
