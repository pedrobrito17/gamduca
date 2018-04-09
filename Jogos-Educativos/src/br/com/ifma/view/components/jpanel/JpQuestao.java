package br.com.ifma.view.components.jpanel;

import java.awt.BorderLayout;
import javax.swing.JPanel;

/**
 *
 * @author Pedro Brito
 */
public class JpQuestao extends JPanel {
    
    private JpPergunta jpPergunta;
    private JpTiposRespostas jpTipoQuestao;

    public JpQuestao(int posicao) {
        configPanelResposta();
        configPanelPergunta();
        configPanelQuestao();
        setTituloQuestao(posicao);
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
    
    private void setTituloQuestao(int posicao){
        jpPergunta.setTituloQuestao("Questão "+posicao);
    }
        
    
}
