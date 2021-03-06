package br.com.ifma.view;

import br.com.ifma.controller.JogoController;
import br.com.ifma.controller.QuizController;
import br.com.ifma.controller.ScormController;
import br.com.ifma.model.Customizacao;
import br.com.ifma.model.Quiz;
import br.com.ifma.view.components.dialog.DialogMoverQuestao;
import br.com.ifma.view.components.dialog.DialogCustomizarQuiz;
import br.com.ifma.view.components.filter.FiltroFileChooserQuiz;
import br.com.ifma.view.components.jpanel.JpFase;
import br.com.ifma.view.components.menu.Ajuda;
import br.com.ifma.view.components.menu.ArquivoQuiz;
import br.com.ifma.view.components.menu.OpcoesQuiz;
import br.com.ifma.view.components.menu.EditarQuiz;
import br.com.ifma.view.components.menu.GerenciadorQuiz;
import br.com.ifma.view.components.menu.Toolbar;
import br.com.ifma.view.components.utils.ArquivoQuizInterface;
import br.com.ifma.view.components.utils.Fonte;
import br.com.ifma.view.components.utils.GerenciadorQuizInterface;
import br.com.ifma.view.components.utils.OpcoesQuizInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Pedro Brito
 */
public class FrameQuiz extends JFrame implements OpcoesQuizInterface,
        ArquivoQuizInterface, GerenciadorQuizInterface {

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
    private Customizacao customizacao = new Customizacao();

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
        labelTitulo.setFont(Fonte.retornarFontePadraoNegrito());
        jpanelTitulo.add(labelTitulo);
        textTitulo = new JTextField(100);
        textTitulo.setPreferredSize(new Dimension(100, 25));
        jpanelTitulo.add(textTitulo);

        jpanelTabbed = new JPanel(new BorderLayout());
        tabbed = new JTabbedPane();
        tabbed.setFont(Fonte.retornarFontePadrao());

        jpFase1 = new JpFase();
        tabbed.addTab("1ª Fase", jpFase1);
        jpanelTabbed.add(tabbed);
    }

    private void inicializarFrame() {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        int height = (int) (dimension.height * 0.9);
        int width = (int) (dimension.width * 0.5);

        this.setSize(width, height);
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

    private ArrayList<JpFase> obterArrayListDeJpFasesCriados() {
        ArrayList<JpFase> jpFases = new ArrayList();
        if (tabbed.getComponentCount() >= 1) {
            jpFases.add(jpFase1);
        }
        if (tabbed.getComponentCount() >= 2) {
            jpFases.add(jpFase2);
        }
        if (tabbed.getComponentCount() == 3) {
            jpFases.add(jpFase3);
        }
        return jpFases;
    }

    public JpFase retornarJpFase(int index) {
        switch (index) {
            case 1:
                if (jpFase1 != null) {
                    return jpFase1;
                } else {
                    jpFase1 = new JpFase();
                    return jpFase1;
                }
            case 2:
                if (jpFase2 != null) {
                    return jpFase2;
                } else {
                    jpFase2 = new JpFase();
                    return jpFase2;
                }
            case 3:
                if (jpFase3 != null) {
                    return jpFase3;
                } else {
                    jpFase3 = new JpFase();
                    return jpFase3;
                }
        }
        return null;
    }

    public void inserirTituloNoQuiz(String titulo) {
        textTitulo.setText(titulo);
    }

    /*
     * MÉTODOS DO MENUBAR E TOOLBAR
     */

 /* MÉTODOS DO MENU OPÇÕES */
    @Override
    public void adicionarFase() {
        switch (tabbed.getComponentCount()) {
            case 1:
                if (jpFase2 == null) {
                    jpFase2 = new JpFase();
                }
                tabbed.add("2ª Fase", jpFase2);
                tabbed.revalidate();
                tabbed.repaint();
                break;
            case 2:
                if (jpFase3 == null) {
                    jpFase3 = new JpFase();
                }
                tabbed.add("3ª Fase", jpFase3);
                tabbed.revalidate();
                tabbed.repaint();
                break;
            default:
                JOptionPane.showMessageDialog(tabbed,
                        "Não é possível adicionar mais de "
                        + "3 fases ao quiz", "Aviso",
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
        DialogCustomizarQuiz personalizarQuiz = new DialogCustomizarQuiz(customizacao);
    }

    /* MÉTODOS DO MENU GERENCIADOR */
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
            acumulador = JOptionPane.showInputDialog(this, "Informe o "
                    + "número da questão", "Deletar questão",
                    JOptionPane.QUESTION_MESSAGE);

            if(acumulador == null){
                break;
            }

            try{
                numeroQuestao = Integer.parseInt(acumulador);
            }catch(NumberFormatException e){
                break;
            }

            JpFase jpFase = (JpFase) tabbed.getSelectedComponent();
            if (jpFase.getJpQuestoes().size() == 3) {
                JOptionPane.showMessageDialog(this, "Cada fase deve ter "
                        + "no mínimo 3 questões",
                        "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
        
        if((numeroQuestao > 0) && (numeroQuestao < 11)){
            JpFase jpFase = (JpFase) tabbed.getSelectedComponent();
            jpFase.deletarQuestao(numeroQuestao);
        }
    }

    /* MÉTODOS DO MENU ARQUIVO */
    @Override
    public void abrirQuiz() {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fc.addChoosableFileFilter(new FiltroFileChooserQuiz());
        fc.setAcceptAllFileFilterUsed(false);
        fc.setDialogTitle("Abrir quiz");

        int retorno = fc.showOpenDialog(this);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            File file = fc.getSelectedFile();

            QuizController quizController = new QuizController();
            quizController.abrirArquivoQuiz(file.getPath(), this, customizacao);
        }
    }

    @Override
    public void salvarQuiz() {
        ArrayList<JpFase> jpFases = obterArrayListDeJpFasesCriados();

        QuizController quizController = new QuizController();
        quizController.obterDiretorio(this);
        quizController.setTituloDoQuiz(textTitulo.getText());
        quizController.inserirCustomizacaoNoObjetoQuiz(customizacao);
        quizController.salvarQuiz(jpFases);
    }

    @Override
    public void novoQuiz() {
        SwingUtilities.invokeLater(() -> {
            FrameQuiz frameQuiz = new FrameQuiz();
        });
    }

    @Override
    public void exportarJogo() {
        ArrayList<JpFase> jpFases = obterArrayListDeJpFasesCriados();
        
        QuizController quizController = new QuizController();
        quizController.setTituloDoQuiz(textTitulo.getText());
        quizController.criarFasesDoQuiz(jpFases);
        quizController.inserirCustomizacaoNoObjetoQuiz(customizacao);
        Quiz quiz = quizController.getQuiz();
        
        JogoController jogoController = new JogoController();
        boolean verificador = jogoController.obterCaminhoParaSalvarJogo(this, "jogo");
        if(verificador){
            jogoController.criarTodosOsDiretorios();
            jogoController.criarArquivos(quiz);
        }
    }

    @Override
    public void exportarScorm() {
        ArrayList<JpFase> jpFases = obterArrayListDeJpFasesCriados();
        
        QuizController quizController = new QuizController();
        quizController.setTituloDoQuiz(textTitulo.getText());
        quizController.criarFasesDoQuiz(jpFases);
        quizController.inserirCustomizacaoNoObjetoQuiz(customizacao);
        Quiz quiz = quizController.getQuiz();
        
        ScormController scormController = new ScormController();
        boolean verificador = scormController.obterCaminhoParaSalvarJogo(this, "scorm");
        if(verificador){
            scormController.criarTodosOsDiretorios();
            scormController.criarArquivos(quiz);
            scormController.criarArquivosScorm(quiz);
            scormController.criarPacote();
        }
    }

}
