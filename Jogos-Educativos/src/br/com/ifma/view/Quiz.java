package br.com.ifma.view;

import br.com.ifma.view.components.config.Fonte;
import br.com.ifma.view.components.dialog.PersonalizarQuiz;
import br.com.ifma.view.components.jpanel.JpFase;
import br.com.ifma.view.components.menu.Ajuda;
import br.com.ifma.view.components.menu.ArquivoQuiz;
import br.com.ifma.view.components.menu.OpcoesQuiz;
import br.com.ifma.view.components.menu.EditarQuiz;
import br.com.ifma.view.components.menu.GerenciadorQuiz;
import br.com.ifma.view.components.menu.Toolbar;
import br.com.ifma.view.components.utils.OpcoesQuizInterface;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
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
public class Quiz extends JFrame implements OpcoesQuizInterface {

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

    public Quiz() {
        this.toolbar = new Toolbar(this);
        configuracaoDoMenu();
        configuracaoDoJPanel();
        inicializarFrame();
    }

    private void configuracaoDoMenu() {
        menuBar = new JMenuBar();
        arquivo = new ArquivoQuiz("Arquivo");
        menuBar.add(arquivo);
        editar = new EditarQuiz("Editar");
        menuBar.add(editar);
        opcoesQuiz = new OpcoesQuiz(this);
        opcoesQuiz.setText("Opções");
        menuBar.add(opcoesQuiz);
        gerenciadorQuiz = new GerenciadorQuiz("Gerenciador");
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

        tabbed.addTab("1ª Fase", new JpFase());
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
                tabbed.add("2ª Fase", new JpFase());
                tabbed.revalidate();
                tabbed.repaint();
                break;
            case 2:
                tabbed.add("3ª Fase", new JpFase());
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
                break;
            case 3:
                tabbed.remove(2);
                tabbed.revalidate();
                tabbed.repaint();
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


}
