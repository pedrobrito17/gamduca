package br.com.ifma.view.components.jpanel;

import br.com.ifma.view.components.Botao;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
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
    private int cont = 1;

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

            if (cont == 2) {
                proxima.setEnabled(true);
                anterior.setEnabled(false);
                cont--;
            } else if (cont > 1 || cont < (jpQuestoes.size())) {
                proxima.setEnabled(true);
                anterior.setEnabled(true);
                cont--;
            }
        });

        proxima.addActionListener((ActionEvent e) -> {
            CardLayout cls = (CardLayout) jpCardQuestoes.getLayout();
            cls.show(jpCardQuestoes, cont < 10 ? "Questão " + (cont + 1) : "Questão");

            if (cont == (jpQuestoes.size() - 1)) {
                proxima.setEnabled(false);
                anterior.setEnabled(true);
                cont++;
            } else if (cont >= 1 || cont < (jpQuestoes.size())) {
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
        for (int i = 0; i < 3; i++) {
            jpQuestoes.add(new JpQuestao(i + 1));
        }

        jpCardQuestoes = new JPanel(new CardLayout());
        for (int i = 0; i < jpQuestoes.size(); i++) {
            jpCardQuestoes.add(jpQuestoes.get(i), "Questão " + (i + 1));
        }
    }

    public void adicionarQuestao() {
        int tam = jpQuestoes.size();

        if (tam < 10) {
            jpQuestoes.add(new JpQuestao(tam + 1));

            jpCardQuestoes.add(jpQuestoes.get(tam), "Questão " + (tam + 1));
            jpCardQuestoes.revalidate();
            jpCardQuestoes.repaint();

            proxima.setEnabled(true);
        } else {
            JOptionPane.showMessageDialog(this, "10 questões é o limite por fase.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void deletarQuestao(int numeroQuestao) {
        int size = jpCardQuestoes.getComponentCount();

        if (size != 3 && (numeroQuestao <= size)) {
            jpCardQuestoes.remove(jpQuestoes.get(numeroQuestao - 1));
            jpQuestoes.remove(numeroQuestao - 1);

            int j = 1;
            for (JpQuestao jpQuestao : jpQuestoes) {
                jpQuestao.getJpPergunta().setTituloQuestao("Questão " + j);
                j++;
            }
            jpCardQuestoes.removeAll();
            jpCardQuestoes.revalidate();
            jpCardQuestoes.repaint();

            for (int i = 0; i < jpQuestoes.size(); i++) {
                jpCardQuestoes.add(jpQuestoes.get(i), "Questão " + (i + 1));
            }

            CardLayout cls = (CardLayout) jpCardQuestoes.getLayout();
            cls.show(jpCardQuestoes, "Questão 1");
            cont = 1;
            anterior.setEnabled(false);
            proxima.setEnabled(true);
        }
    }

    public void moverQuestao(int numeroQuestao, int paraPosicao) {
        JpQuestao jpQuestao = jpQuestoes.get(numeroQuestao - 1);
        jpQuestao.setTituloQuestao(paraPosicao);

        int mudarPosicao = paraPosicao - 1;
        while (mudarPosicao < (numeroQuestao - 1)) {
            jpQuestoes.get(mudarPosicao).setTituloQuestao((mudarPosicao + 2));
            mudarPosicao++;
        }
        Collections.sort(jpQuestoes);

        jpCardQuestoes.removeAll();
        jpCardQuestoes.revalidate();
        jpCardQuestoes.repaint();

        for (int i = 0; i < jpQuestoes.size(); i++) {
            jpCardQuestoes.add(jpQuestoes.get(i), "Questão " + (i + 1));
        }

        CardLayout cls = (CardLayout) jpCardQuestoes.getLayout();
        cls.show(jpCardQuestoes, "Questão 1");
        cont = 1;
        anterior.setEnabled(false);
        proxima.setEnabled(true);
    }

    private void configPanelFase() {
        this.setLayout(new BorderLayout());
        this.add(jpCardQuestoes, BorderLayout.CENTER);
        this.add(jpBotoes, BorderLayout.SOUTH);
    }

    public ArrayList<JpQuestao> getJpQuestoes() {
        return jpQuestoes;
    }

}
