package br.com.ifma.view.components.dialog;

import br.com.ifma.view.components.config.Fonte;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Pedro Brito
 */
public final class PersonalizarQuiz extends JFrame{
    
    private JTabbedPane tabbed;
    private JPanel jpFonte, jpCor, jpMensagem, jpTempo;

    public PersonalizarQuiz() throws HeadlessException {
        configJpFonte();
        configJpCor();
        configJpMensagem();
        configTabbed();
        inicializarFrame();
    }
    
    public void configJpFonte(){
        jpFonte = new JPanel(new BorderLayout());
        jpFonte.setBackground(Color.red);
        jpFonte.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
    
    public void configJpCor(){
        jpCor = new JPanel(new BorderLayout());
        jpCor.setBackground(Color.black);
        jpCor.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
    
    public void configJpMensagem(){
        jpMensagem = new JPanel(new BorderLayout());
        jpMensagem.setBackground(Color.green);
        jpMensagem.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
    
    public void configJpTempo(){
        jpTempo = new JPanel(new BorderLayout());
        jpTempo.setBackground(Color.PINK);
        jpTempo.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
    
    public void configTabbed(){
        tabbed = new JTabbedPane();
        tabbed.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        tabbed.add("Fonte", jpFonte);
        tabbed.add("Cor", jpCor);
        tabbed.add("Mensagem", jpMensagem);
        tabbed.add("Tempo", jpTempo);
    }

    public void inicializarFrame() {
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Personalizar quiz");
        this.add(tabbed, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
