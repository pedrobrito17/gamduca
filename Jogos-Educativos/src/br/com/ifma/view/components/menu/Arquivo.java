package br.com.ifma.view.components.menu;

import br.com.ifma.view.components.config.Fonte;
import com.sun.glass.events.KeyEvent;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Pedro Brito
 */
public class Arquivo extends JMenu{
    
    private JMenuItem forca, palavrasCruzadas, quiz, sair;

    public Arquivo(String s) {
        super(s);
    }
    
    public void configurarMenu(){
        configurarItemMenu();
        this.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        this.add(forca);
        this.add(palavrasCruzadas);
        this.add(quiz);
        this.add(sair);
    }
    
    public void configurarItemMenu(){
        forca = new JMenuItem("Forca", KeyEvent.VK_F);
        KeyStroke ctrlVKeyStroke = KeyStroke.getKeyStroke("control F");
        forca.setAccelerator(ctrlVKeyStroke);
        forca.addActionListener(new MenuItemActionListener(forca));
        forca.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        forca.setToolTipText("Inicia a configuração do jogo da forca");
        
        palavrasCruzadas = new JMenuItem("Palavras Cruzadas", KeyEvent.VK_P);
        KeyStroke ctrlVKeyStrok = KeyStroke.getKeyStroke("control P");
        palavrasCruzadas.setAccelerator(ctrlVKeyStrok);
        palavrasCruzadas.addActionListener(new MenuItemActionListener(palavrasCruzadas));
        palavrasCruzadas.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        palavrasCruzadas.setToolTipText("Inicia a configuração do jogo palavras cruzadas");
        
        quiz = new JMenuItem("Quiz", KeyEvent.VK_Q);
        KeyStroke ctrlVKeyStro = KeyStroke.getKeyStroke("control Q");
        quiz.setAccelerator(ctrlVKeyStro);
        quiz.addActionListener(new MenuItemActionListener(quiz));
        quiz.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        quiz.setToolTipText("Inicia a configuração do jogo quiz");
        
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
                case "Forca":
                    JOptionPane.showMessageDialog(parent, item.getActionCommand() + " foi selecionado.");
                    break;
                case "Palavras Cruzadas":
                    JOptionPane.showMessageDialog(parent, item.getActionCommand() + " foi selecionado.");
                    break;
                case "Quiz":
                    JOptionPane.showMessageDialog(parent, item.getActionCommand() + " foi selecionado.");
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
