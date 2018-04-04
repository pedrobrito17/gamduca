package br.com.ifma.view.components.menu;

import br.com.ifma.view.components.config.Fonte;
import com.sun.glass.events.KeyEvent;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author Pedro Brito
 */
public class EditarQuiz extends JMenu{
    
    private JMenuItem recortar, copiar, colar, sair;

    public EditarQuiz(String s) {
        super(s);
    }
    
    public void configurarMenu(){
        configurarItemMenu();
        this.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        this.add(recortar);
        this.add(copiar);
        this.add(colar);
    }
    
    public void configurarItemMenu(){        
        recortar = new JMenuItem("Recortar", KeyEvent.VK_X);
        KeyStroke ctrlVKeyStroke = KeyStroke.getKeyStroke("control X");
        recortar.setAccelerator(ctrlVKeyStroke);
        recortar.addActionListener(new EditarQuiz.MenuItemActionListener(recortar));
        recortar.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        
        copiar = new JMenuItem("Copiar", KeyEvent.VK_C);
        KeyStroke ctrlVKeyStrok = KeyStroke.getKeyStroke("control C");
        copiar.setAccelerator(ctrlVKeyStrok);
        copiar.addActionListener(new EditarQuiz.MenuItemActionListener(copiar));
        copiar.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        
        colar = new JMenuItem("Colar", KeyEvent.VK_V);
        KeyStroke ctrlVKeyStro = KeyStroke.getKeyStroke("control V");
        colar.setAccelerator(ctrlVKeyStro);
        colar.addActionListener(new EditarQuiz.MenuItemActionListener(colar));
        colar.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
    }
    
    public class MenuItemActionListener implements ActionListener {

        Component parent;

        public MenuItemActionListener(Component parent) {
            this.parent = parent;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            JMenuItem item = (JMenuItem) e.getSource();
//            switch(item.getActionCommand()){
//                case "Sair":
//                    System.exit(0);
//                    break;
//                default:
//                    break;
//            }
        }
    }

}
