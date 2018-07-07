package br.com.ifma.view;

import br.com.ifma.view.components.menu.Ajuda;
import br.com.ifma.view.components.menu.Arquivo;
import br.com.ifma.view.components.utils.Icone;
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
public class FramePrincipal extends JFrame{
    
    private JPanel jpanel;
    private JMenuBar menuBar;
    private Arquivo arquivo;
    private Ajuda ajuda;
    
    public FramePrincipal(){
        configuracaoDoMenu();
        configuracaoDoJPanel();
        inicializarFrame();
    }
    
    private void configuracaoDoMenu(){
        menuBar = new JMenuBar();
        arquivo = new Arquivo("Arquivo");
        ajuda = new Ajuda("Ajuda");
        menuBar.add(arquivo);
        menuBar.add(ajuda);
    }
    
    private void configuracaoDoJPanel(){
        ImageIcon imagemForca = Icone.retornarImageIcon("icones/img-main.png");
        jpanel = new JPanel();
        jpanel.setLayout(new BorderLayout());
        jpanel.add(new JLabel(null, imagemForca, JLabel.LEFT), BorderLayout.CENTER);
    }
    
    private void inicializarFrame(){
        this.setSize(508, 505);
        this.setLayout(new GridLayout(1,1));
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        this.setJMenuBar(menuBar);
        this.add(jpanel);

        this.setVisible(true);
    }  
}
