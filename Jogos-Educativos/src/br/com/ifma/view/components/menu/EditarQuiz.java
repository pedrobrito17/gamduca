package br.com.ifma.view.components.menu;

import br.com.ifma.view.components.utils.Fonte;
import com.sun.glass.events.KeyEvent;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

/**
 *
 * @author Pedro Brito
 */
public class EditarQuiz extends JMenu {

    private JMenuItem recortar, copiar, colar, deletar, selecionarTudo;

    public EditarQuiz(String s) {
        super(s);
        configurarMenu();
    }

    private void configurarMenu() {
        configurarItemMenu();
        this.setFont(Fonte.retornarFontePadrao());
        this.add(recortar);
        this.add(copiar);
        this.add(colar);
        this.add(deletar);
        this.add(selecionarTudo);
    }

    private void configurarItemMenu() {
        recortar = new JMenuItem("Recortar", KeyEvent.VK_X);
        KeyStroke ctrlVKeyStroke = KeyStroke.getKeyStroke("control X");
        recortar.setAccelerator(ctrlVKeyStroke);
        recortar.addActionListener(new EditarQuiz.MenuItemActionListener(recortar));
        recortar.setFont(Fonte.retornarFontePadrao());

        copiar = new JMenuItem("Copiar", KeyEvent.VK_C);
        KeyStroke ctrlVKeyStrok = KeyStroke.getKeyStroke("control C");
        copiar.setAccelerator(ctrlVKeyStrok);
        copiar.addActionListener(new EditarQuiz.MenuItemActionListener(copiar));
        copiar.setFont(Fonte.retornarFontePadrao());

        colar = new JMenuItem("Colar", KeyEvent.VK_V);
        KeyStroke ctrlVKeyStro = KeyStroke.getKeyStroke("control V");
        colar.setAccelerator(ctrlVKeyStro);
        colar.addActionListener(new EditarQuiz.MenuItemActionListener(colar));
        colar.setFont(Fonte.retornarFontePadrao());

        deletar = new JMenuItem("Deletar");
        deletar.addActionListener(new EditarQuiz.MenuItemActionListener(deletar));
        deletar.setFont(Fonte.retornarFontePadrao());

        selecionarTudo = new JMenuItem("Selecionar tudo", KeyEvent.VK_A);
        KeyStroke ctrlVKeyStr = KeyStroke.getKeyStroke("control A");
        selecionarTudo.setAccelerator(ctrlVKeyStr);
        selecionarTudo.addActionListener(new EditarQuiz.MenuItemActionListener(selecionarTudo));
        selecionarTudo.setFont(Fonte.retornarFontePadrao());
    }

    private class MenuItemActionListener implements ActionListener {

        Component parent;

        private MenuItemActionListener(Component parent) {
            this.parent = parent;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            JMenuItem item = (JMenuItem) e.getSource();
            switch (item.getActionCommand()) {
                case "Recortar": 
                    break;
                case "Copiar":
                    
                    break;
                case "Colar":
                    
                    break;
                case "Deletar":
                    
                    break;
                case "Selecionar tudo":
                    
                    break;
                default:
                    break;
            }
        }
    }

}
