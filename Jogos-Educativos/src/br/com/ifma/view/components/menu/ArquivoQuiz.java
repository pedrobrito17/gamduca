package br.com.ifma.view.components.menu;

import br.com.ifma.view.components.config.Fonte;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author Pedro Brito
 */
public class ArquivoQuiz extends JMenu{
    
    private JMenuItem sair;

    public ArquivoQuiz(String s) {
        super(s);
    }
    
    public void configurarMenu(){
        configurarItemMenu();
        this.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        this.add(sair);
    }
    
    public void configurarItemMenu(){        
        sair = new JMenuItem("Sair");
        sair.addActionListener(new MenuItemActionListener(this));
        sair.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
    }
    
    public class MenuItemActionListener implements ActionListener {

        Component parent;

        public MenuItemActionListener(Component parent) {
            this.parent = parent;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            JMenuItem item = (JMenuItem) e.getSource();
            switch(item.getActionCommand()){
                case "Sair":
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

}
