package br.com.ifma.view;

import br.com.ifma.view.components.config.Fonte;
import br.com.ifma.view.components.menu.Ajuda;
import br.com.ifma.view.components.menu.ArquivoQuiz;
import br.com.ifma.view.components.menu.EditarQuiz;
import br.com.ifma.view.components.panel.Questao;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Pedro Brito
 */
public class Quiz extends JFrame{
    
    private JPanel jpanelTitulo, jpanelTabbed;
    private final JMenuBar menuBar;
    private final ArquivoQuiz arquivo;
    private final EditarQuiz editar;
    private final Ajuda ajuda;
    JTextField textTitulo;
    JLabel labelTitulo;
    
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                Quiz quiz = new Quiz("Quiz");
            }
        });
    }
    
    public Quiz(String title) throws HeadlessException {
        super(title);
        menuBar = new JMenuBar();
        arquivo = new ArquivoQuiz("Arquivo");
        arquivo.configurarMenu();
        editar = new EditarQuiz("Editar");
        editar.configurarMenu();
        ajuda = new Ajuda("Ajuda");
        ajuda.configurarMenu();
        
        configuracaoDoMenu();
        configuracaoDoJPanel();
        inicializarFrame();
    }
    
    private void configuracaoDoMenu(){
        menuBar.add(arquivo);
        menuBar.add(editar);
        menuBar.add(ajuda);
    }
    
    private void configuracaoDoJPanel(){
        jpanelTitulo = new JPanel(new GridLayout(2,1));
        jpanelTitulo.setBorder(new EmptyBorder(10, 10, 10, 10));
        labelTitulo = new JLabel("Título do Quiz");
        labelTitulo.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        jpanelTitulo.add(labelTitulo);
        textTitulo = new JTextField(100);
        textTitulo.setPreferredSize(new Dimension(100, 25));
        jpanelTitulo.add(textTitulo);
        
        jpanelTabbed = new JPanel(new BorderLayout());
        JTabbedPane tabbed = new JTabbedPane();
        tabbed.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        
        JPanel p = new JPanel(new BorderLayout());
        p.add(new JLabel("Testando"), BorderLayout.NORTH);
        p.setBackground(Color.red);
        
        
        tabbed.addTab("1ª Fase", new Questao());
        tabbed.addTab("2ª Fase", new JPanel());
        tabbed.addTab("3ª Fase", new JPanel());
        jpanelTabbed.add(tabbed);
    }
    
    private void inicializarFrame(){
        this.setSize(800, 680);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(true);
        
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setJMenuBar(menuBar);
        this.add(jpanelTitulo, BorderLayout.NORTH);
        this.add(jpanelTabbed, BorderLayout.CENTER);
        
        this.setVisible(true);
    }   
    
    
}
