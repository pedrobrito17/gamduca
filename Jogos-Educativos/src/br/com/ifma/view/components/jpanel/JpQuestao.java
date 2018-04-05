package br.com.ifma.view.components.jpanel;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Pedro Brito
 */
public final class JpQuestao extends JPanel {
    
    private JpPergunta jpPergunta;
    private JpTiposRespostas jpTipoQuestao;

    public JpQuestao(int posicao) {
        configPanelResposta();
        configPanelPergunta();
        configPanelQuestao();
        setTituloQuestao(posicao);
    }
    
    public void configPanelPergunta(){
        jpPergunta = new JpPergunta(jpTipoQuestao);
        jpPergunta.configPergunta();
    }
    
    public void configPanelResposta(){
        jpTipoQuestao = new JpTiposRespostas();
        jpTipoQuestao.configTipoQuestao();
    }
    
    public void configPanelQuestao(){
        this.setLayout(new BorderLayout());
        this.add(jpPergunta, BorderLayout.NORTH);
        this.add(jpTipoQuestao, BorderLayout.CENTER);
    }
    
    public void setTituloQuestao(int posicao){
        jpPergunta.setTituloQuestao("Questão "+posicao);
    }
        
    
}
