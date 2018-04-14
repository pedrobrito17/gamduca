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

    public JpQuestao(int numeroQuestao) {
        configPanelResposta();
        configPanelPergunta();
        configPanelQuestao();
        configTituloQuestao(numeroQuestao);
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
    
    private void configTituloQuestao(int numeroQuestao){
        this.tituloQuestao = "Questão "+numeroQuestao;
        jpPergunta.setTituloQuestao("Questão "+numeroQuestao);
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
                        .todasAsRespostasForamPreenchidas() &&
                        jpPergunta.perguntaCompleta();
            case "Pergunta direta":
                return jpTipoQuestao.getJpRespostaPerguntaDireta()
                        .respostaPreenchida() &&
                        jpPergunta.perguntaCompleta();
            case "Verdadeiro ou falso":
                return jpTipoQuestao.getJpRespostaVerdadeiroOuFaso()
                        .todasAsRespostasForamPreenchidas() &&
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
