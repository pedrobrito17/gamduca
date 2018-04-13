package br.com.ifma.view;

import br.com.ifma.controller.QuizController;
import br.com.ifma.view.components.config.Fonte;
import br.com.ifma.view.components.dialog.DialogMoverQuestao;
import br.com.ifma.view.components.dialog.PersonalizarQuiz;
import br.com.ifma.view.components.jpanel.JpFase;
import br.com.ifma.view.components.menu.Ajuda;
import br.com.ifma.view.components.menu.ArquivoQuiz;
import br.com.ifma.view.components.menu.OpcoesQuiz;
import br.com.ifma.view.components.menu.EditarQuiz;
import br.com.ifma.view.components.menu.GerenciadorQuiz;
import br.com.ifma.view.components.menu.Toolbar;
import br.com.ifma.view.components.utils.ArquivoQuizInterface;
import br.com.ifma.view.components.utils.GerenciadorQuizInterface;
import br.com.ifma.view.components.utils.OpcoesQuizInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Pedro Brito
 */
public class FrameQuiz extends JFrame implements OpcoesQuizInterface, ArquivoQuizInterface, GerenciadorQuizInterface {

    private JPanel jpanelTitulo, jpanelTabbed, panelAux;
    private JMenuBar menuBar;
    private ArquivoQuiz arquivo;
    private EditarQuiz editar;
    private OpcoesQuiz opcoesQuiz;
    private GerenciadorQuiz gerenciadorQuiz;
    private Ajuda ajuda;
    private final Toolbar toolbar;
    private JTextField textTitulo;
    private JLabel labelTitulo;
    private JTabbedPane tabbed;
    private JpFase jpFase1, jpFase2, jpFase3;

    public FrameQuiz() {
        this.toolbar = new Toolbar(this);
        configuracaoDoMenu();
        configuracaoDoJPanel();
        inicializarFrame();
    }

    private void configuracaoDoMenu() {
        menuBar = new JMenuBar();
        arquivo = new ArquivoQuiz(this);
        arquivo.setText("Arquivo");
        menuBar.add(arquivo);
        editar = new EditarQuiz("Editar");
        menuBar.add(editar);
        opcoesQuiz = new OpcoesQuiz(this);
        opcoesQuiz.setText("Opções");
        menuBar.add(opcoesQuiz);
        gerenciadorQuiz = new GerenciadorQuiz(this);
        gerenciadorQuiz.setText("Gerenciador");
        menuBar.add(gerenciadorQuiz);
        ajuda = new Ajuda("Ajuda");
        menuBar.add(ajuda);
    }

    private void configuracaoDoJPanel() {
        jpanelTitulo = new JPanel(new GridLayout(2, 1));
        jpanelTitulo.setFocusable(true);
        jpanelTitulo.setBorder(new EmptyBorder(10, 10, 10, 10));
        labelTitulo = new JLabel("Título do Quiz");
        labelTitulo.setFont(new Font(Fonte.FONTE.getFonte(), Font.BOLD,
                Fonte.TAMANHO.getTamanhoDaFonte()));
        jpanelTitulo.add(labelTitulo);
        textTitulo = new JTextField(100);
        textTitulo.setPreferredSize(new Dimension(100, 25));
        jpanelTitulo.add(textTitulo);

        jpanelTabbed = new JPanel(new BorderLayout());
        tabbed = new JTabbedPane();
        tabbed.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN,
                Fonte.TAMANHO.getTamanhoDaFonte()));

        jpFase1 = new JpFase();
        tabbed.addTab("1ª Fase", jpFase1);
        jpanelTabbed.add(tabbed);
    }

    private void inicializarFrame() {
        this.setSize(700, 800);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setJMenuBar(menuBar);
        this.add(toolbar, BorderLayout.NORTH);

        panelAux = new JPanel(new BorderLayout());
        panelAux.add(jpanelTitulo, BorderLayout.NORTH);
        panelAux.add(jpanelTabbed, BorderLayout.CENTER);

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentShown(ComponentEvent e) {
                textTitulo.requestFocusInWindow();
            }

        });

        this.add(panelAux, BorderLayout.CENTER);
        this.setTitle("Quiz");
        this.setVisible(true);
    }

    @Override
    public void adicionarFase() {
        switch (tabbed.getComponentCount()) {
            case 1:
                jpFase2 = new JpFase();
                tabbed.add("2ª Fase", jpFase2);
                tabbed.revalidate();
                tabbed.repaint();
                break;
            case 2:
                jpFase3 = new JpFase();
                tabbed.add("3ª Fase", jpFase3);
                tabbed.revalidate();
                tabbed.repaint();
                break;
            default:
                JOptionPane.showMessageDialog(tabbed,
                        "Não é possível adicionar mais de "
                        + "3 fases ao quiz.", "Aviso",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }

    @Override
    public void deletarFase() {
        switch (tabbed.getComponentCount()) {
            case 2:
                tabbed.remove(1);
                tabbed.revalidate();
                tabbed.repaint();
                jpFase2 = null;
                break;
            case 3:
                tabbed.remove(2);
                tabbed.revalidate();
                tabbed.repaint();
                jpFase3 = null;
                break;
            default:
                JOptionPane.showMessageDialog(tabbed,
                        "Não é possível deletar a 1ª fase.", "Aviso",
                        JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }

    @Override
    public void personalizarQuiz() {
        PersonalizarQuiz personalizarQuiz = new PersonalizarQuiz();
    }

    @Override
    public void exportarJogo() {
        ArrayList<JpFase> jpFases = new ArrayList();
        if (jpFase1 != null) {
            jpFases.add(jpFase1);
        }
        if (jpFase2 != null) {
            jpFases.add(jpFase2);
        }
        if (jpFase3 != null) {
            jpFases.add(jpFase3);
        }
        QuizController quiz = new QuizController();
        quiz.setTituloDoQuiz(labelTitulo.getText());
        quiz.criarFasesDoQuiz(jpFases);
    }

    @Override
    public void exportarScorm() {
    }

    @Override
    public void adicionarQuestao() {
        JpFase jpFase = (JpFase) tabbed.getSelectedComponent();
        jpFase.adicionarQuestao();
    }

    @Override
    public void moverQuestao() {
        JpFase jpFase = (JpFase) tabbed.getSelectedComponent();
        DialogMoverQuestao dialog = new DialogMoverQuestao(this, jpFase);

    }

    @Override
    public void deletarQuestao() {
        String acumulador;
        int numeroQuestao = 0;

        while ((numeroQuestao < 1) || (numeroQuestao > 10)) {
            try {
                acumulador = JOptionPane.showInputDialog(this, "Informe o "
                        + "número da questão", "Deletar questão",
                        JOptionPane.QUESTION_MESSAGE);

                numeroQuestao = Integer.parseInt(acumulador);
                
                JpFase jpFase = (JpFase) tabbed.getSelectedComponent();
                if(jpFase.getJpQuestoes().size()==3){
                    JOptionPane.showMessageDialog(this, "Cada fase deve ter no mínimo 3 questões", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Informe apenas número de"
                        + " 1 a 10");
            }
        }

        JpFase jpFase = (JpFase) tabbed.getSelectedComponent();
        jpFase.deletarQuestao(numeroQuestao);
    }

}
