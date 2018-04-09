package br.com.ifma.view.components.menu;

import br.com.ifma.view.components.config.Fonte;
import com.sun.glass.events.KeyEvent;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;

/**
 *
 * @author Pedro Brito
 */
public class ArquivoQuiz extends JMenu{
    
    private JMenuItem sair, exportarPacote, exportarJogo;

    public ArquivoQuiz(String s) {
        super(s);
        configurarMenu();
    }
    
    private void configurarMenu(){
        configurarItemMenu();
        this.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        this.add(exportarJogo);
        this.add(exportarPacote);
        this.add(new JSeparator());
        this.add(sair);
    }
    
    private void configurarItemMenu(){        
        sair = new JMenuItem("Sair");
        sair.addActionListener(new MenuItemActionListener(this));
        sair.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
    
        exportarPacote = new JMenuItem("Exportar pacote scorm", KeyEvent.VK_P);
        KeyStroke ctrlVKeyStroke = KeyStroke.getKeyStroke("control P");
        exportarPacote.setAccelerator(ctrlVKeyStroke);
        exportarPacote.addActionListener(new MenuItemActionListener(this));
        exportarPacote.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));

        exportarJogo = new JMenuItem("Exportar Jogo", KeyEvent.VK_J);
        KeyStroke ctrlVKeyStrok = KeyStroke.getKeyStroke("control J");
        exportarJogo.setAccelerator(ctrlVKeyStrok);
        exportarJogo.addActionListener(new MenuItemActionListener(this));
        exportarJogo.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
    
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
                case "Exportar pacote scorm":
                    break;
                case "Exportar jogo":
                    break;
                case "Sair":
                    break;
                default:
                    break;
            }
        }
    }

}
