package br.com.ifma.view.components.menu;

import br.com.ifma.view.components.config.Fonte;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Pedro Brito
 */
public class Opcoes extends JMenu{
    
    private JMenuItem configuracao;

    public Opcoes(String s) {
        super(s);
        configurarMenu();
    }
    
    private void configurarMenu(){
        configurarItemMenu();
        this.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        this.add(configuracao);
    }
    
    private void configurarItemMenu(){
        configuracao = new JMenuItem("Configuração", KeyEvent.VK_N);
        KeyStroke ctrlVKeyStroke = KeyStroke.getKeyStroke("control N");
        configuracao.setAccelerator(ctrlVKeyStroke);
        configuracao.addActionListener(new MenuItemActionListener(configuracao));
        configuracao.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        configuracao.setToolTipText("Configuração geral da aplicação");
    }
    
    private class MenuItemActionListener implements ActionListener {

        Component parent;

        private MenuItemActionListener(Component parent) {
            this.parent = parent;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            JMenuItem item = (JMenuItem) e.getSource();
            switch(item.getActionCommand()){
                case "Configuração":
                    JOptionPane.showMessageDialog(parent, item.getActionCommand() + " foi selecionado.");
                    break;
                default:
                    break;
            }
        }
    }
    
}

