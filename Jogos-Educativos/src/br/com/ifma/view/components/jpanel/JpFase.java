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
public final class JpFase extends JPanel {

    private JPanel jpBotoes, jpCardQuestoes;
    private ArrayList<JpQuestao> jpQuestoes;
    private Botao anterior, proxima;
    private int cont = 0;

    public JpFase() {
        configPanelBotoes();
        configQuestoes();
        configPanelFase();
    }

    public void configPanelBotoes() {
        anterior = new Botao("Anterior");
        anterior.setIcon(new ImageIcon("img/back.png"));
        anterior.setToolTipText("Questão anterior");
        anterior.configurarBotao();
        proxima = new Botao("Próxima");
        proxima.setIcon(new ImageIcon("img/next.png"));
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
            cont--;
        });

        proxima.addActionListener((ActionEvent e) -> {
            CardLayout cls = (CardLayout) jpCardQuestoes.getLayout();
            cls.show(jpCardQuestoes, cont < 10 ? "Questão " + (cont + 1) : "Questão");
            cont++;
        });
    }

    public void configQuestoes() {
        jpQuestoes = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            jpQuestoes.add(new JpQuestao(i + 1));
        }

        jpCardQuestoes = new JPanel(new CardLayout());
        for (int i = 0; i < 10; i++) {
            jpCardQuestoes.add(jpQuestoes.get(i), "Questão " + i);
        }
    }

    public void configPanelFase() {
        this.setLayout(new BorderLayout());
        this.add(jpCardQuestoes, BorderLayout.CENTER);
        this.add(jpBotoes, BorderLayout.SOUTH);
    }

}
