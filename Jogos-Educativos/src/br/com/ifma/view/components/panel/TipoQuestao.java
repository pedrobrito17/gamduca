package br.com.ifma.view.components.panel;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Pedro Brito
 */
public class TipoQuestao extends JPanel{

    private JPanel multiplaEscolha, perguntaDireta, verdadeiroOuFalso;
    
    public TipoQuestao() {
    }
    
    public void configPaineis(){
        multiplaEscolha = new JPanel();
        multiplaEscolha.setBackground(Color.red);
        
        perguntaDireta = new JPanel();
        perguntaDireta.setBackground(Color.BLUE);
        
        verdadeiroOuFalso = new JPanel();
        verdadeiroOuFalso.setBackground(Color.GREEN);
        
        this.setLayout(new CardLayout());
        this.setBorder(new EmptyBorder(0,10,10,10));
        this.add(multiplaEscolha, "Múltipla escolha");
        this.add(perguntaDireta, "Pergunta direta");
        this.add(verdadeiroOuFalso, "Verdadeiro ou falso");
    }
    
    
}
