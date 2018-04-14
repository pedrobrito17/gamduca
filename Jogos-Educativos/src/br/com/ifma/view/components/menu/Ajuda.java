package br.com.ifma.view.components.menu;

import br.com.ifma.view.components.utils.Fonte;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Brito
 */
public class Ajuda extends JMenu{
    
    private JMenuItem tutorial;
    private JMenuItem sobre;

    public Ajuda(String s) {
        super(s);
        configurarMenu();
    }
    
    private void configurarMenu(){
        configurarItemMenu();
        this.setFont(Fonte.retornarFontePadrao());
        this.add(tutorial);
        this.add(sobre);
    }
    
    private void configurarItemMenu(){
        tutorial = new JMenuItem("Tutorial");
        tutorial.addActionListener(new MenuItemActionListener(tutorial));
        tutorial.setFont(Fonte.retornarFontePadrao());
        tutorial.setToolTipText("Instruções para o uso");
        
        sobre = new JMenuItem("Sobre");
        sobre.addActionListener(new MenuItemActionListener(tutorial));
        sobre.setFont(Fonte.retornarFontePadrao());
        sobre.setToolTipText("Selecione para visualizar");
    }
    
    private class MenuItemActionListener implements ActionListener {

        Component parent;

        public MenuItemActionListener(Component parent) {
            this.parent = parent;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            JMenuItem item = (JMenuItem) e.getSource();
            switch(item.getActionCommand()){
                case "Tutorial":
                    JOptionPane.showMessageDialog(parent, item.getActionCommand() + " foi selecionado.");
                    break;
                case "Sobre":
                    JOptionPane.showMessageDialog(parent, item.getActionCommand() + " foi selecionado.");
                    break;
                default:
                    break;
            }
        }
    }
    
}
