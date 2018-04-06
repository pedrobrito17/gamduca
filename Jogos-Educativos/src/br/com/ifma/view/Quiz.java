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
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import br.com.ifma.view.components.utils.OpcoesQuizInterface;
import java.awt.EventQueue;

/**
 *
 * @author Pedro Brito
 */
public class Quiz extends JFrame implements OpcoesQuizInterface{
    
    private JPanel jpanelTitulo, jpanelTabbed, panelAux;
    private final JMenuBar menuBar;
    private final ArquivoQuiz arquivo;
    private final EditarQuiz editar;
    private final OpcoesQuiz opcoesQuiz;
    private final GerenciadorQuiz gerenciadorQuiz;
    private final Ajuda ajuda;
    private final Toolbar toolbar;
    private JTextField textTitulo;
    private JLabel labelTitulo;
    private JTabbedPane tabbed;
    
    public Quiz() {
        menuBar = new JMenuBar();
        arquivo = new ArquivoQuiz("Arquivo");
        arquivo.configurarMenu();
        editar = new EditarQuiz("Editar");
        editar.configurarMenu();
        ajuda = new Ajuda("Ajuda");
        ajuda.configurarMenu();
        opcoesQuiz = new OpcoesQuiz(this);
        opcoesQuiz.setText("Opções");
        opcoesQuiz.configurarMenu();
        gerenciadorQuiz = new GerenciadorQuiz("Gerenciador");
        gerenciadorQuiz.configurarMenu();
        toolbar = new Toolbar(this);

        configuracaoDoMenu();
        configuracaoDoJPanel();
        inicializarFrame();
    }
    
    private void configuracaoDoMenu(){
        menuBar.add(arquivo);
        menuBar.add(editar);
        menuBar.add(opcoesQuiz);
        menuBar.add(gerenciadorQuiz);
        menuBar.add(ajuda);
    }
    
    private void configuracaoDoJPanel(){
        jpanelTitulo = new JPanel(new GridLayout(2,1));
        jpanelTitulo.setBorder(new EmptyBorder(10, 10, 10, 10));
        labelTitulo = new JLabel("Título do Quiz");
        labelTitulo.setFont(new Font(Fonte.FONTE.getFonte(), Font.BOLD, Fonte.TAMANHO.getTamanhoDaFonte()));
        jpanelTitulo.add(labelTitulo);
        textTitulo = new JTextField(100);
        textTitulo.setPreferredSize(new Dimension(100, 25));
        jpanelTitulo.add(textTitulo);
        
        jpanelTabbed = new JPanel(new BorderLayout());
        tabbed = new JTabbedPane();
        tabbed.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        
        tabbed.addTab("1ª Fase", new JpFase());
        jpanelTabbed.add(tabbed);
    }
    
    private void inicializarFrame(){
        this.setSize(700, 800);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        this.setJMenuBar(menuBar);
        this.add(toolbar, BorderLayout.NORTH);
        
        panelAux = new JPanel(new BorderLayout());
        panelAux.add(jpanelTitulo, BorderLayout.NORTH);
        panelAux.add(jpanelTabbed, BorderLayout.CENTER);
        
        this.add(panelAux, BorderLayout.CENTER);
        this.setTitle("Quiz");
        this.setVisible(true);
    }   

    @Override
    public void adicionarFase() {
        switch (tabbed.getComponentCount()) {
            case 1:
                tabbed.add("2ª Fase", new JpFase());
                break;
            case 2:
                tabbed.add("3ª Fase", new JpFase());
                break;
            default:
                JOptionPane.showMessageDialog(this, "Não é possível adicionar mais de 3 fases ao quiz.", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                break;
        }
    }

    @Override
    public void personalizarQuiz() {
        EventQueue.invokeLater(() -> {
            PersonalizarQuiz personalizarQuiz = new PersonalizarQuiz();
        });
    }

}
