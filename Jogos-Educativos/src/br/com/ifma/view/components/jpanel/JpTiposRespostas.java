package br.com.ifma.view.components.jpanel;

import java.awt.CardLayout;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Pedro Brito
 */
public class JpTiposRespostas extends JPanel{

    private JpRespostaMultiplaEscolha jpRespMultiplaEscolha;
    private JpRespostaPerguntaDireta jpRespPerguntaDireta;
    private JpRespostaVerdadeiroOuFaso jpRespVerdadeiroOuFalso;
    
    public JpTiposRespostas() {
        configTipoQuestao();
    }
    
    private void configTipoQuestao(){
        configMultiplaEscolha();
        configPerguntaDireta();
        configVerdadeiroOuFalso();
        
        this.setLayout(new CardLayout());
        this.setBorder(new EmptyBorder(0,10,10,10));
        this.add(jpRespMultiplaEscolha, "Múltipla escolha");
        this.add(jpRespPerguntaDireta, "Pergunta direta");
        this.add(jpRespVerdadeiroOuFalso, "Verdadeiro ou falso");
    }
    
    private void configMultiplaEscolha(){
        jpRespMultiplaEscolha = new JpRespostaMultiplaEscolha();
    }
    
    private void configPerguntaDireta(){
        jpRespPerguntaDireta = new JpRespostaPerguntaDireta();
    }
    
    private void configVerdadeiroOuFalso(){
        jpRespVerdadeiroOuFalso = new JpRespostaVerdadeiroOuFaso();
    }
    
    
}
