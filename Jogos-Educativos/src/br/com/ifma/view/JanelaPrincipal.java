package br.com.ifma.view;

import br.com.ifma.view.components.menu.Ajuda;
import br.com.ifma.view.components.menu.Arquivo;
import br.com.ifma.view.components.menu.Opcoes;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;

/**
 *
 * @author Pedro Brito
 */
public class JanelaPrincipal extends JFrame{
    
    private final JFrame jframe;
    private final JPanel jpanel;
    private final JMenuBar menuBar;
    private final Arquivo arquivo;
    private final Opcoes opcoes;
    private final Ajuda ajuda;
    
    public JanelaPrincipal(){
        jframe = new JFrame();       
        
        menuBar = new JMenuBar();
        arquivo = new Arquivo("Arquivo");
        arquivo.configurarMenu();
        opcoes = new Opcoes("Opções");
        opcoes.configurarMenu();
        ajuda = new Ajuda("Ajuda");
        ajuda.configurarMenu();
        
        jpanel = new JPanel();
        
        configuracaoDoMenu();
        configuracaoDoJPanel();
        inicializarFrame();
    }
    
    private void configuracaoDoMenu(){
        menuBar.add(arquivo);
        menuBar.add(opcoes);
        menuBar.add(ajuda);
    }
    
    private void configuracaoDoJPanel(){
        ImageIcon imagemForca = new ImageIcon("img/img-main.png");
        jpanel.setLayout(new BorderLayout());
        jpanel.add(new JLabel(null, imagemForca, JLabel.LEFT), BorderLayout.CENTER);
    }
    
    private void inicializarFrame(){
        jframe.setSize(508, 505);
        jframe.setLayout(new GridLayout(1,1));
        jframe.setLocationRelativeTo(null);
        jframe.setResizable(false);
        jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jframe.setJMenuBar(menuBar);
        jframe.add(jpanel);
        
        jframe.setVisible(true);
    }   

}
