package br.com.ifma.view.components.menu;

import br.com.ifma.view.FrameQuiz;
import br.com.ifma.view.components.utils.Fonte;
import com.sun.glass.events.KeyEvent;
import gui.frames.FramePrincipal;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

/**
 *
 * @author Pedro Brito
 */
public class Arquivo extends JMenu {

    private JMenuItem forca, quiz, sair;

    public Arquivo(String s) {
        super(s);
        configurarMenu();
    }

    private void configurarMenu() {
        configurarItemMenu();
        this.setFont(Fonte.retornarFontePadrao());
        this.add(forca);
        this.add(quiz);
        this.add(new JSeparator());
        this.add(sair);
    }

    private void configurarItemMenu() {
        forca = getItemMenu("Forca", this, KeyEvent.VK_F, "control F",
                "Inicia a configuração do jogo da forca");

        quiz = getItemMenu("Quiz", this, KeyEvent.VK_Q, "control Q",
                "Inicia a configuração do jogo quiz");

        sair = getItemMenu("Sair", this, 0, null, null);
    }

    private JMenuItem getItemMenu(String text, Component parent, int keyEvent,
            String keyStroke, String tooltip) {

        JMenuItem menuItem = new JMenuItem(text, keyEvent);
        KeyStroke ctrlVKeyStroke = KeyStroke.getKeyStroke(keyStroke);
        menuItem.setAccelerator(ctrlVKeyStroke);
        menuItem.setToolTipText(tooltip);
        menuItem.addActionListener(new MenuItemActionListener(this));
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
                case "Forca":
                    SwingUtilities.invokeLater(() -> {
                        FramePrincipal framePrincipal = new FramePrincipal();
                    });
                    break;
                case "Quiz":
                    SwingUtilities.invokeLater(() -> {
                        FrameQuiz quiz = new FrameQuiz();
                    });
                    break;
                case "Sair":
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }

}
