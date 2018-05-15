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
public class JpRespostaVerdadeiroOuFaso extends JPanel {

    private JPanel jpRespostaA, jpRespostaB, jpRespostaC, jpRespostaD;
    private JLabel a, b, c, d;
    private JTextArea respA, respB, respC, respD;
    private JScrollPane jsRespA, jsRespB, jsRespC, jsRespD;
    private JRadioButton rbVerdadeiroA, rbFalsoA, rbVerdadeiroB, rbFalsoB, rbVerdadeiroC, rbFalsoC, rbVerdadeiroD, rbFalsoD;
    private ButtonGroup groupA, groupB, groupC, groupD;

    public JpRespostaVerdadeiroOuFaso() {
        configRespostas();
    }

    private void configRespostas() {
        this.setBorder(BorderFactory.createTitledBorder("Respostas"));
        this.setLayout(new GridLayout(4, 1));
        configRespostaA();
        configRespostaB();
        configRespostaC();
        configRespostaD();
    }

    private void configRespostaA() {
        jpRespostaA = new JPanel(new BorderLayout());
        jpRespostaA.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(jpRespostaA);

        a = new JLabel("(A)");
        a.setFont(Fonte.retornarFontePadrao());
        a.setBorder(new EmptyBorder(2, 2, 2, 8));
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
        rbVerdadeiroA.setFont(Fonte.retornarFontePadrao());
        rbVerdadeiroA.setFocusPainted(false);
        rbFalsoA = new JRadioButton("Falso");
        rbFalsoA.setFont(Fonte.retornarFontePadrao());
        rbFalsoA.setFocusPainted(false);
        rbFalsoA.setSelected(true);
        groupA = new ButtonGroup();
        groupA.add(rbVerdadeiroA);
        groupA.add(rbFalsoA);
        JPanel jp = new JPanel(new BorderLayout());
        jp.add(rbVerdadeiroA, BorderLayout.NORTH);
        jp.add(rbFalsoA, BorderLayout.CENTER);
        jpRespostaA.add(jp, BorderLayout.EAST);
    }

    private void configRespostaB() {
        jpRespostaB = new JPanel(new BorderLayout());
        jpRespostaB.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(jpRespostaB);

        b = new JLabel("(B)");
        b.setFont(Fonte.retornarFontePadrao());
        b.setBorder(new EmptyBorder(2, 2, 2, 8));
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
        rbVerdadeiroB.setFont(Fonte.retornarFontePadrao());
        rbVerdadeiroB.setFocusPainted(false);
        rbFalsoB = new JRadioButton("Falso");
        rbFalsoB.setFont(Fonte.retornarFontePadrao());
        rbFalsoB.setFocusPainted(false);
        rbFalsoB.setSelected(true);
        groupB = new ButtonGroup();
        groupB.add(rbVerdadeiroB);
        groupB.add(rbFalsoB);
        JPanel jp = new JPanel(new BorderLayout());
        jp.add(rbVerdadeiroB, BorderLayout.NORTH);
        jp.add(rbFalsoB, BorderLayout.CENTER);
        jpRespostaB.add(jp, BorderLayout.EAST);
    }

    private void configRespostaC() {
        jpRespostaC = new JPanel(new BorderLayout());
        jpRespostaC.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(jpRespostaC);

        c = new JLabel("(C)");
        c.setFont(Fonte.retornarFontePadrao());
        c.setBorder(new EmptyBorder(2, 2, 2, 8));
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
        rbVerdadeiroC.setFont(Fonte.retornarFontePadrao());
        rbVerdadeiroC.setFocusPainted(false);
        rbFalsoC = new JRadioButton("Falso");
        rbFalsoC.setFont(Fonte.retornarFontePadrao());
        rbFalsoC.setFocusPainted(false);
        rbFalsoC.setSelected(true);
        groupC = new ButtonGroup();
        groupC.add(rbVerdadeiroC);
        groupC.add(rbFalsoC);
        JPanel jp = new JPanel(new BorderLayout());
        jp.add(rbVerdadeiroC, BorderLayout.NORTH);
        jp.add(rbFalsoC, BorderLayout.CENTER);
        jpRespostaC.add(jp, BorderLayout.EAST);
    }

    private void configRespostaD() {
        jpRespostaD = new JPanel(new BorderLayout());
        jpRespostaD.setBorder(new EmptyBorder(5, 5, 5, 5));
        this.add(jpRespostaD);

        d = new JLabel("(D)");
        d.setFont(Fonte.retornarFontePadrao());
        d.setBorder(new EmptyBorder(2, 2, 2, 8));
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
        rbVerdadeiroD.setFont(Fonte.retornarFontePadrao());
        rbVerdadeiroD.setFocusPainted(false);
        rbFalsoD = new JRadioButton("Falso");
        rbFalsoD.setFont(Fonte.retornarFontePadrao());
        rbFalsoD.setFocusPainted(false);
        rbFalsoD.setSelected(true);
        groupD = new ButtonGroup();
        groupD.add(rbVerdadeiroD);
        groupD.add(rbFalsoD);
        JPanel jp = new JPanel(new BorderLayout());
        jp.add(rbVerdadeiroD, BorderLayout.NORTH);
        jp.add(rbFalsoD, BorderLayout.CENTER);
        jpRespostaD.add(jp, BorderLayout.EAST);
    }

    public void limparRespostas() {
        this.respA.setText("");
        this.respB.setText("");
        this.respC.setText("");
        this.respD.setText("");
    }

    public boolean todasAsRespostasEstaoLimpas() {
        return respA.getText().isEmpty()
                && respB.getText().isEmpty()
                && respC.getText().isEmpty()
                && respD.getText().isEmpty();
    }

    public boolean todasAsRespostasForamPreenchidas() {
        return !respA.getText().isEmpty()
                && !respB.getText().isEmpty()
                && !respC.getText().isEmpty()
                && !respD.getText().isEmpty();
    }

    public String[] getRespA() {
        String[] respostaA = new String[2];
        respostaA[0] = respA.getText();
        respostaA[1] = rbVerdadeiroA.isSelected() ? "true" : "false";
        return respostaA;
    }

    public String[] getRespB() {
        String[] respostaB = new String[2];
        respostaB[0] = respB.getText();
        respostaB[1] = rbVerdadeiroB.isSelected() ? "true" : "false";
        return respostaB;
    }

    public String[] getRespC() {
        String[] respostaC = new String[2];
        respostaC[0] = respC.getText();
        respostaC[1] = rbVerdadeiroC.isSelected() ? "true" : "false";
        return respostaC;
    }

    public String[] getRespD() {
        String[] respostaD = new String[2];
        respostaD[0] = respD.getText();
        respostaD[1] = rbVerdadeiroD.isSelected() ? "true" : "false";
        return respostaD;
    }

    public void setRespA(String[] resp) {
        respA.setText(resp[0]);
        if (Boolean.valueOf(resp[1])) {
            rbVerdadeiroA.setSelected(true);
        } else {
            rbFalsoA.setSelected(true);
        }
    }

    public void setRespB(String[] resp) {
        respB.setText(resp[0]);
        if(resp[1]==null){
        }
        else if (Boolean.valueOf(resp[1])) {
            rbVerdadeiroB.setSelected(true);
        } else {
            rbFalsoB.setSelected(true);
        }
    }

    public void setRespC(String[] resp) {
        respC.setText(resp[0]);
        if (Boolean.valueOf(resp[1])) {
            rbVerdadeiroC.setSelected(true);
        } else {
            rbFalsoC.setSelected(true);
        }
    }

    public void setRespD(String[] resp) {
        respD.setText(resp[0]);
        if (Boolean.valueOf(resp[1])) {
            rbVerdadeiroD.setSelected(true);
        } else {
            rbFalsoD.setSelected(true);
        }
    }
}
