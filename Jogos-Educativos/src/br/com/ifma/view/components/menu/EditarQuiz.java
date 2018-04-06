package br.com.ifma.view.components.menu;

import br.com.ifma.view.components.config.Fonte;
import com.sun.glass.events.KeyEvent;
import java.awt.Component;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Pedro Brito
 */
public class EditarQuiz extends JMenu {

    private JMenuItem recortar, copiar, colar, deletar, selecionarTudo;
    private Clipboard clipboard = new Clipboard("clipboard");

    public EditarQuiz(String s) {
        super(s);
    }

    public void configurarMenu() {
        configurarItemMenu();
        this.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        this.add(recortar);
        this.add(copiar);
        this.add(colar);
        this.add(deletar);
        this.add(selecionarTudo);
    }

    public void configurarItemMenu() {
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

        deletar = new JMenuItem("Deletar");
        deletar.addActionListener(new EditarQuiz.MenuItemActionListener(deletar));
        deletar.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));

        selecionarTudo = new JMenuItem("Selecionar tudo", KeyEvent.VK_A);
        KeyStroke ctrlVKeyStr = KeyStroke.getKeyStroke("control A");
        selecionarTudo.setAccelerator(ctrlVKeyStr);
        selecionarTudo.addActionListener(new EditarQuiz.MenuItemActionListener(selecionarTudo));
        selecionarTudo.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
    }

    public class MenuItemActionListener implements ActionListener {

        Component parent;

        public MenuItemActionListener(Component parent) {
            this.parent = parent;
        }

        @Override
        public void actionPerformed(java.awt.event.ActionEvent e) {
            JMenuItem item = (JMenuItem) e.getSource();
            switch (item.getActionCommand()) {
                case "Recortar":
                    JOptionPane.showMessageDialog(parent, item.getActionCommand() + " foi selecionado.");
                    break;
                case "Copiar":
                    JOptionPane.showMessageDialog(parent, item.getActionCommand() + " foi selecionado.");
                    break;
                case "Colar":
                    break;
                case "Deletar":
                    JOptionPane.showMessageDialog(parent, item.getActionCommand() + " foi selecionado.");
                    break;
                case "Selecionar tudo":
                    Toolkit.getDefaultToolkit().getSystemSelection();
                    break;
                default:
                    break;
            }
        }
    }

}
