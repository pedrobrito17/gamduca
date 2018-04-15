package br.com.ifma.view.components.jpanel;

import br.com.ifma.view.components.utils.Fonte;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Pedro Brito
 */
public class JpRespostaMultiplaEscolha extends JPanel{
    
    private JPanel jpRespostaA, jpRespostaB, jpRespostaC, jpRespostaD ;
    private JLabel a, b, c, d;
    private JTextArea respA, respB, respC, respD;
    private JScrollPane jsRespA, jsRespB, jsRespC, jsRespD;
    private JRadioButton rbRespA, rbRespB, rbRespC, rbRespD;
    private ButtonGroup group;
    
    public JpRespostaMultiplaEscolha() {
        configRespostas();
    }
    
    private void configRespostas(){
        this.setBorder(BorderFactory.createTitledBorder("Respostas"));
        this.setLayout(new GridLayout(4,1));
        configRespostaA();
        configRespostaB();
        configRespostaC();
        configRespostaD();
        configButtonGroup();
    }

    private void configRespostaA(){
        jpRespostaA = new JPanel(new BorderLayout());
        jpRespostaA.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(jpRespostaA);
        
        a = new JLabel("(A)");
        a.setFont(Fonte.retornarFontePadrao());
        a.setBorder(new EmptyBorder(2,2,2,8));
        jpRespostaA.add(a, BorderLayout.WEST);
        
        respA = new JTextArea();
        respA.setBorder(new EmptyBorder(5, 5, 5, 5));
        respA.setLineWrap(true);
        respA.setWrapStyleWord(true);
        jsRespA = new JScrollPane(respA);
        jsRespA.setPreferredSize(new Dimension(300, 100));
        jsRespA.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jpRespostaA.add(jsRespA, BorderLayout.CENTER);
        
        rbRespA = new JRadioButton("Correta");
        rbRespA.setName("A");
        rbRespA.setFont(Fonte.retornarFontePadrao());
        rbRespA.setFocusPainted(false);
        rbRespA.setSelected(true);
        jpRespostaA.add(rbRespA, BorderLayout.EAST);
    }
    
    private void configRespostaB(){
        jpRespostaB = new JPanel(new BorderLayout());
        jpRespostaB.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(jpRespostaB);
        
        b = new JLabel("(B)");
        b.setFont(Fonte.retornarFontePadrao());
        b.setBorder(new EmptyBorder(2,2,2,8));
        jpRespostaB.add(b, BorderLayout.WEST);
        
        respB = new JTextArea();
        respB.setBorder(new EmptyBorder(5, 5, 5, 5));
        respB.setLineWrap(true);
        respB.setWrapStyleWord(true);
        jsRespB = new JScrollPane(respB);
        jsRespB.setPreferredSize(new Dimension(300, 100));
        jsRespB.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jpRespostaB.add(jsRespB, BorderLayout.CENTER);
        
        rbRespB = new JRadioButton("Correta");
        rbRespB.setName("B");
        rbRespB.setFont(Fonte.retornarFontePadrao());
        rbRespB.setFocusPainted(false);
        jpRespostaB.add(rbRespB, BorderLayout.EAST);
    }
    
    private void configRespostaC(){
        jpRespostaC = new JPanel(new BorderLayout());
        jpRespostaC.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(jpRespostaC);
        
        c = new JLabel("(C)");
        c.setFont(Fonte.retornarFontePadrao());
        c.setBorder(new EmptyBorder(2,2,2,8));
        jpRespostaC.add(c, BorderLayout.WEST);
        
        respC = new JTextArea();
        respC.setBorder(new EmptyBorder(5, 5, 5, 5));
        respC.setLineWrap(true);
        respC.setWrapStyleWord(true);
        jsRespC = new JScrollPane(respC);
        jsRespC.setPreferredSize(new Dimension(300, 100));
        jsRespC.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jpRespostaC.add(jsRespC, BorderLayout.CENTER);
        
        rbRespC = new JRadioButton("Correta");
        rbRespC.setName("C");
        rbRespC.setFont(Fonte.retornarFontePadrao());
        rbRespC.setFocusPainted(false);
        jpRespostaC.add(rbRespC, BorderLayout.EAST);
    }
    
    private void configRespostaD(){
        jpRespostaD = new JPanel(new BorderLayout());
        jpRespostaD.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(jpRespostaD);
        
        d = new JLabel("(D)");
        d.setFont(Fonte.retornarFontePadrao());
        d.setBorder(new EmptyBorder(2,2,2,8));
        jpRespostaD.add(d, BorderLayout.WEST);
        
        respD = new JTextArea();
        respD.setBorder(new EmptyBorder(5, 5, 5, 5));
        respD.setLineWrap(true);
        respD.setWrapStyleWord(true);
        jsRespD = new JScrollPane(respD);
        jsRespD.setPreferredSize(new Dimension(300, 100));
        jsRespD.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        jpRespostaD.add(jsRespD, BorderLayout.CENTER);
        
        rbRespD = new JRadioButton("Correta");
        rbRespD.setName("D");
        rbRespD.setFont(Fonte.retornarFontePadrao());
        rbRespD.setFocusPainted(false);
        jpRespostaD.add(rbRespD, BorderLayout.EAST);
    }
    
    private void configButtonGroup(){
        group = new ButtonGroup();
        group.add(rbRespA);
        group.add(rbRespB);
        group.add(rbRespC);
        group.add(rbRespD);
    }
    
    public void limparRespostas(){
        this.respA.setText("");
        this.respB.setText("");
        this.respC.setText("");
        this.respD.setText("");
    }
    
    public boolean todasAsRespostasEstaoLimpas(){
        return respA.getText().isEmpty() &&
                respB.getText().isEmpty() &&
                respC.getText().isEmpty() &&
                respD.getText().isEmpty();
    }
    
    public boolean todasAsRespostasForamPreenchidas(){
        return !respA.getText().isEmpty() &&
                !respB.getText().isEmpty() &&
                !respC.getText().isEmpty() &&
                !respD.getText().isEmpty();
    }
    
    public String getRespA(){
        return respA.getText();
    }
    
    public void setRespA(String respA){
        this.respA.setText(respA);
    }
    
    public String getRespB(){
        return respB.getText();
    }
    
    public void setRespB(String respB){
        this.respB.setText(respB);
    }
    
    public String getRespC(){
        return respC.getText();
    }
    
    public void setRespC(String respC){
        this.respC.setText(respC);
    }
    
    public String getRespD(){
        return respD.getText();
    }
    
    public void setRespD(String respD){
        this.respD.setText(respD);
    }
    
    public String getRespCorreta(){
        if( rbRespA.isSelected() ){ return "A"; }
        else if( rbRespB.isSelected() ){ return "B"; }
        else if( rbRespC.isSelected() ){ return "C"; }
        else if( rbRespD.isSelected() ){ return "D"; }
        else return null;
    }
    
    public void setRespCorreta(String resp){
        switch(resp){
            case "A":
                rbRespA.setSelected(true);
                break;
            case "B":
                rbRespB.setSelected(true);
                break;
            case "C":
                rbRespC.setSelected(true);
                break;
            case "D":
                rbRespD.setSelected(true);
                break;
            default:
                break;
        }
    }

}

