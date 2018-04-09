package br.com.ifma.view.components.jpanel;

import br.com.ifma.view.components.Botao;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Pedro Brito
 */
public class JpFase extends JPanel {

    private JPanel jpBotoes, jpCardQuestoes;
    private ArrayList<JpQuestao> jpQuestoes;
    private Botao anterior, proxima;
    private int cont = 0;

    public JpFase() {
        configPanelBotoes();
        configQuestoes();
        configPanelFase();
    }

    private void configPanelBotoes() {
        anterior = new Botao("Anterior");
        ImageIcon imgIconAnt = createImageIcon("icones/back.png");
        anterior.setIcon(imgIconAnt);
        anterior.setToolTipText("Questão anterior");
        anterior.configurarBotao();
        anterior.setEnabled(false);
        proxima = new Botao("Próxima");
        ImageIcon imgIconProx = createImageIcon("icones/next.png");
        proxima.setIcon(imgIconProx);
        proxima.setToolTipText("Próxima questão");
        proxima.setVerticalTextPosition(SwingConstants.CENTER);
        proxima.setHorizontalTextPosition(SwingConstants.LEFT);
        proxima.configurarBotao();

        jpBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jpBotoes.setBorder(new EmptyBorder(0, 10, 5, 10));
        jpBotoes.add(anterior);
        jpBotoes.add(proxima);

        anterior.addActionListener((ActionEvent e) -> {
            CardLayout cl = (CardLayout) jpCardQuestoes.getLayout();
            cl.show(jpCardQuestoes, cont > 0 ? "Questão " + (cont - 1) : "Questão");
            
            if(cont==1){
                proxima.setEnabled(true);
                anterior.setEnabled(false);
                cont--;
            }
            else if(cont>0 || cont<9){
                proxima.setEnabled(true);
                anterior.setEnabled(true);
                cont--;
            }
        });

        proxima.addActionListener((ActionEvent e) -> {
            CardLayout cls = (CardLayout) jpCardQuestoes.getLayout();
            cls.show(jpCardQuestoes, cont < 10 ? "Questão " + (cont + 1) : "Questão");
            
            if(cont==8){
                proxima.setEnabled(false);
                anterior.setEnabled(true);
                cont++;
            }
            else if(cont>=0 || cont<9){
                proxima.setEnabled(true);
                anterior.setEnabled(true);
                cont++;
            }
        });
    }
    
    private static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = JpFase.class.getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Arquivo não encontrado: " + path);
            return null;
        }
    }

    private void configQuestoes() {
        jpQuestoes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            jpQuestoes.add(new JpQuestao(i + 1));
        }

        jpCardQuestoes = new JPanel(new CardLayout());
        for (int i = 0; i < 10; i++) {
            jpCardQuestoes.add(jpQuestoes.get(i), "Questão " + i);
        }
    }

    private void configPanelFase() {
        this.setLayout(new BorderLayout());
        this.add(jpCardQuestoes, BorderLayout.CENTER);
        this.add(jpBotoes, BorderLayout.SOUTH);
    }

}
