package br.com.ifma.view.components.menu;

import br.com.ifma.view.components.config.FonteMenu;
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
    }
    
    public void configurarMenu(){
        configurarItemMenu();
        this.setFont(new Font(FonteMenu.FONTE.getFonte(), Font.PLAIN, FonteMenu.TAMANHO.getTamanhoDaFonte()));
        this.add(configuracao);
    }
    
    public void configurarItemMenu(){
        configuracao = new JMenuItem("Configuração", KeyEvent.VK_N);
        KeyStroke ctrlVKeyStroke = KeyStroke.getKeyStroke("control N");
        configuracao.setAccelerator(ctrlVKeyStroke);
        configuracao.addActionListener(new MenuItemActionListener(configuracao));
        configuracao.setFont(new Font(FonteMenu.FONTE.getFonte(), Font.PLAIN, FonteMenu.TAMANHO.getTamanhoDaFonte()));
        configuracao.setToolTipText("Configuração geral da aplicação");
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
                case "Configuração":
                    JOptionPane.showMessageDialog(parent, item.getActionCommand() + " foi selecionado.");
                    break;
                default:
                    break;
            }
        }
    }
    
}

