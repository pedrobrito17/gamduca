package br.com.ifma.view.components.jpanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Pedro Brito
 */
public class JpRespostaPerguntaDireta extends JPanel{
    
    private JTextField resposta;
    private JPanel jp;
    
    public JpRespostaPerguntaDireta() {
        configResposta();
    }
    
    private void configResposta(){
        resposta = new JTextField();
        resposta.setPreferredSize(new Dimension(100,25));
        
        jp = new JPanel(new BorderLayout());
        jp.setBorder(new EmptyBorder(10, 10, 10, 10));
        jp.add(resposta, BorderLayout.PAGE_START);
        
        this.setBorder(BorderFactory.createTitledBorder("Resposta"));
        this.setLayout(new BorderLayout());
        this.add(jp, BorderLayout.PAGE_START);
    }

    public void clear() {
        this.resposta.setText("");
    }
    
    public boolean respostaLimpa(){
        return resposta.getText().isEmpty();
    }
    
    public boolean respostaCompleta(){
        return !resposta.getText().isEmpty();
    }
    
    public String getResposta(){
        return resposta.getText();
    }

}
