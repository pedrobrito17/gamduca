package br.com.ifma.view.components.dialog;

import br.com.ifma.view.components.config.Fonte;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Pedro Brito
 */
public class PersonalizarQuiz extends JDialog{
    
    private JTabbedPane tabbed;
    private JPanel jpFonte, jpCor, jpMensagem, jpTempo, jpAvaliacao;

    public PersonalizarQuiz() throws HeadlessException {
        configJpFonte();
        configJpCor();
        configJpMensagem();
        configTabbed();
        inicializarDialog();
    }
    
    private void configJpFonte(){
        jpFonte = new JPanel(new BorderLayout());
        jpFonte.setBackground(Color.red);
        jpFonte.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
    
    private void configJpCor(){
        jpCor = new JPanel(new BorderLayout());
        jpCor.setBackground(Color.black);
        jpCor.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
    
    private void configJpMensagem(){
        jpMensagem = new JPanel(new BorderLayout());
        jpMensagem.setBackground(Color.green);
        jpMensagem.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
    
    private void configJpTempo(){
        jpTempo = new JPanel(new BorderLayout());
        jpTempo.setBackground(Color.PINK);
        jpTempo.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
    
    private void configJpAvaliacao(){
        jpAvaliacao = new JPanel(new BorderLayout());
        jpAvaliacao.setBackground(Color.ORANGE);
        jpAvaliacao.setBorder(new EmptyBorder(10, 10, 10, 10));
    }
    
    private void configTabbed(){
        tabbed = new JTabbedPane();
        tabbed.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        tabbed.add("Fonte", jpFonte);
        tabbed.add("Cor", jpCor);
        tabbed.add("Mensagem", jpMensagem);
        tabbed.add("Tempo", jpTempo);
        tabbed.add("Avaliação", jpAvaliacao);
    }

    private void inicializarDialog() {
        this.setSize(800, 600);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Personalizar quiz");
        this.add(tabbed, BorderLayout.CENTER);
        this.setVisible(true);
    }
}
