package br.com.ifma.view.components.dialog;

import br.com.ifma.view.components.Botao;
import br.com.ifma.view.components.utils.Fonte;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JDialog;
import javax.swing.JEditorPane;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Pedro Brito
 */
public final class DialogSobre extends JDialog {

    private JEditorPane textoIntrodutorio;
    private JScrollPane sp;
    private JPanel jpTextArea, jpJLabels, jpBotao;
    private JLabel lbDev1, lbForca, lbDev2, lbQuiz;
    private Botao botao;

    public DialogSobre() {
        configJEditorPane();
        configJLabels();
        configBotao();
        inicializarDialog();
    }

    public void configJEditorPane() {
        textoIntrodutorio = createEditorPane();
        textoIntrodutorio.setBorder(new EmptyBorder(5, 5, 5, 5));
        textoIntrodutorio.setFocusable(false);
        sp = new JScrollPane(textoIntrodutorio);
        sp.setPreferredSize(new Dimension(250, 110));
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        
        jpTextArea = new JPanel(new BorderLayout());
        jpTextArea.add(sp, BorderLayout.CENTER);
        jpTextArea.setBorder(new EmptyBorder(5, 5, 5, 5));
    }
    
    private JEditorPane createEditorPane() {
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        java.net.URL helpURL = DialogSobre.class.getClassLoader().getResource(
                "arquivos/sobre.html");
        
        if (helpURL != null) {
            try {
                editorPane.setPage(helpURL);
            } catch (IOException e) {
                System.err.println("Attempted to read a bad URL: " + helpURL);
            }
        } else {
            System.err.println("Couldn't find file: TextSampleDemoHelp.html");
        }
 
        return editorPane;
    }
    
    private void configJLabels(){
        lbDev1 = new JLabel("Desenvolvedor do Forca:");
        lbDev1.setFont(Fonte.retornarFontePadraoNegrito());
        lbDev1.setBorder(new EmptyBorder(10, 0, 10, 0));
        lbForca = new JLabel("Luiz Aristóteles Santos Silva");
        lbForca.setFont(Fonte.retornarFontePadrao());
        
        lbDev2 = new JLabel("Desenvolvedor do Quiz:");
        lbDev2.setFont(Fonte.retornarFontePadraoNegrito());
        lbDev2.setBorder(new EmptyBorder(10, 0, 10, 0));
        lbQuiz = new JLabel("Pedro Ítalo Aragão Brito");
        lbQuiz.setFont(Fonte.retornarFontePadrao());
        
        jpJLabels = new JPanel(new GridLayout(7, 1));
        jpJLabels.setBorder(new EmptyBorder(5, 5, 5, 5));
        jpJLabels.add(lbDev1);
        jpJLabels.add(lbForca);
        jpJLabels.add(lbDev2);
        jpJLabels.add(lbQuiz);
    }
    
    private void configBotao(){
        botao = new Botao("Fechar");
        botao.configurarBotao();
        botao.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        
        jpBotao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jpBotao.add(new JSeparator(SwingConstants.HORIZONTAL));
        jpBotao.add(botao);
    }

    private void inicializarDialog() {
        this.setSize(320, 300);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(jpTextArea, BorderLayout.NORTH);
        this.add(jpJLabels, BorderLayout.CENTER);
        this.add(jpBotao, BorderLayout.SOUTH);
        this.setTitle("Sobre");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

}
