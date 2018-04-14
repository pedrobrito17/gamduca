package br.com.ifma.view.components.menu;

import br.com.ifma.view.components.utils.ArquivoQuizInterface;
import br.com.ifma.view.components.utils.Fonte;
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
public class ArquivoQuiz extends JMenu {

    private JMenuItem novoQuiz, abrirQuiz, salvarQuiz, 
            sair, exportarPacote, exportarJogo;
    private ArquivoQuizInterface arquivoInterface;

    public ArquivoQuiz(Component parent) {
        if (parent instanceof ArquivoQuizInterface) {
            arquivoInterface = (ArquivoQuizInterface) parent;
        } else {
            throw new RuntimeException(parent.toString()
                    + " deve implementar ArquivoQuizInterface");
        }
        configurarMenu();
    }

    private void configurarMenu() {
        configurarItemMenu();
        this.setFont(Fonte.retornarFontePadrao());
        this.add(novoQuiz);
        this.add(abrirQuiz);
        this.add(salvarQuiz);
        this.add(new JSeparator());
        this.add(exportarJogo);
        this.add(exportarPacote);
        this.add(new JSeparator());
        this.add(sair);
    }

    private void configurarItemMenu() {
        novoQuiz = getItemMenu("Novo quiz", this, KeyEvent.VK_N, "control N");
        abrirQuiz = getItemMenu("Abrir quiz", this, KeyEvent.VK_B, "control B");
        salvarQuiz = getItemMenu("Salvar quiz", this, KeyEvent.VK_S, "control S");
        exportarJogo = getItemMenu("Exportar jogo", this, KeyEvent.VK_J, "control J");
        exportarPacote = getItemMenu("Exportar pacote scorm", this, KeyEvent.VK_P, "control P");
        sair = getItemMenu("Sair", this, 0, null);
    }

    private JMenuItem getItemMenu(String text, Component parent, int keyEvent, 
            String keyStroke) {
        
        JMenuItem menuItem = new JMenuItem(text, keyEvent);
        KeyStroke ctrlVKeyStroke = KeyStroke.getKeyStroke(keyStroke);
        menuItem.setAccelerator(ctrlVKeyStroke);
        menuItem.addActionListener(new ArquivoQuiz.MenuItemActionListener(parent));
        menuItem.setFont(Fonte.retornarFontePadrao());
        return menuItem;
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
                case "Novo quiz":
                    arquivoInterface.novoQuiz();
                    break;
                case "Abrir quiz":
                    arquivoInterface.abrirQuiz();
                    break;
                case "Salvar quiz":
                    arquivoInterface.salvarQuiz();
                    break;
                case "Exportar pacote scorm":
                    arquivoInterface.exportarScorm();
                    break;
                case "Exportar jogo":
                    arquivoInterface.exportarJogo();
                    break;
                case "Sair":
                    break;
                default:
                    break;
            }
        }
    }

}
