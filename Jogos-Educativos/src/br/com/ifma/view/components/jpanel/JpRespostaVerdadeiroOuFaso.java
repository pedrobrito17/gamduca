package br.com.ifma.view.components.jpanel;

import br.com.ifma.view.components.config.Fonte;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
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
public class JpRespostaVerdadeiroOuFaso extends JPanel{

    private JPanel jpRespostaA, jpRespostaB, jpRespostaC, jpRespostaD ;
    private JLabel a, b, c, d;
    private JTextArea respA, respB, respC, respD;
    private JScrollPane jsRespA, jsRespB, jsRespC, jsRespD;
    private JRadioButton rbVerdadeiroA, rbFalsoA, rbVerdadeiroB, rbFalsoB, rbVerdadeiroC, rbFalsoC, rbVerdadeiroD, rbFalsoD;
    private ButtonGroup groupA, groupB, groupC, groupD;
    
    public JpRespostaVerdadeiroOuFaso() {
        configRespostas();
    }

    private void configRespostas(){
        this.setBorder(BorderFactory.createTitledBorder("Respostas"));
        this.setLayout(new GridLayout(4,1));
        configRespostaA();
        configRespostaB();
        configRespostaC();
        configRespostaD();
    }

    private void configRespostaA(){
        jpRespostaA = new JPanel(new BorderLayout());
        jpRespostaA.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(jpRespostaA);
        
        a = new JLabel("(A)");
        a.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
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
        
        rbVerdadeiroA = new JRadioButton("Verdadeiro");
        rbVerdadeiroA.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        rbVerdadeiroA.setFocusPainted(false);
        rbFalsoA = new JRadioButton("Falso");
        rbFalsoA.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        rbFalsoA.setFocusPainted(false);
        groupA = new ButtonGroup();
        groupA.add(rbVerdadeiroA);
        groupA.add(rbFalsoA);
        JPanel jp = new JPanel(new BorderLayout());
        jp.add(rbVerdadeiroA, BorderLayout.NORTH);
        jp.add(rbFalsoA, BorderLayout.CENTER);
        jpRespostaA.add(jp, BorderLayout.EAST);
    }
    
    private void configRespostaB(){
        jpRespostaB = new JPanel(new BorderLayout());
        jpRespostaB.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(jpRespostaB);
        
        b = new JLabel("(B)");
        b.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
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
        
        rbVerdadeiroB = new JRadioButton("Verdadeiro");
        rbVerdadeiroB.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        rbVerdadeiroB.setFocusPainted(false);
        rbFalsoB = new JRadioButton("Falso");
        rbFalsoB.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        rbFalsoB.setFocusPainted(false);
        groupB = new ButtonGroup();
        groupB.add(rbVerdadeiroB);
        groupB.add(rbFalsoB);
        JPanel jp = new JPanel(new BorderLayout());
        jp.add(rbVerdadeiroB, BorderLayout.NORTH);
        jp.add(rbFalsoB, BorderLayout.CENTER);
        jpRespostaB.add(jp, BorderLayout.EAST);
        
    }
    
    private void configRespostaC(){
        jpRespostaC = new JPanel(new BorderLayout());
        jpRespostaC.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(jpRespostaC);
        
        c = new JLabel("(C)");
        c.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
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
        
        rbVerdadeiroC = new JRadioButton("Verdadeiro");
        rbVerdadeiroC.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        rbVerdadeiroC.setFocusPainted(false);
        rbFalsoC = new JRadioButton("Falso");
        rbFalsoC.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        rbFalsoC.setFocusPainted(false);
        groupC = new ButtonGroup();
        groupC.add(rbVerdadeiroC);
        groupC.add(rbFalsoC);
        JPanel jp = new JPanel(new BorderLayout());
        jp.add(rbVerdadeiroC, BorderLayout.NORTH);
        jp.add(rbFalsoC, BorderLayout.CENTER);
        jpRespostaC.add(jp, BorderLayout.EAST);
        
    }
    
    private void configRespostaD(){
        jpRespostaD = new JPanel(new BorderLayout());
        jpRespostaD.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(jpRespostaD);
        
        d = new JLabel("(D)");
        d.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
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
        
        rbVerdadeiroD = new JRadioButton("Verdadeiro");
        rbVerdadeiroD.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        rbVerdadeiroD.setFocusPainted(false);
        rbFalsoD = new JRadioButton("Falso");
        rbFalsoD.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        rbFalsoD.setFocusPainted(false);
        groupD = new ButtonGroup();
        groupD.add(rbVerdadeiroD);
        groupD.add(rbFalsoD);
        JPanel jp = new JPanel(new BorderLayout());
        jp.add(rbVerdadeiroD, BorderLayout.NORTH);
        jp.add(rbFalsoD, BorderLayout.CENTER);
        jpRespostaD.add(jp, BorderLayout.EAST);
    }
}
