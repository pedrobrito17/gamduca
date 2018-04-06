package br.com.ifma.view.components.menu;

import br.com.ifma.view.components.config.Fonte;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Brito
 */
public class GerenciadorQuiz extends JMenu{
    
    private JMenuItem inserirQuestao, deletarQuestao, moverQuestao;

    public GerenciadorQuiz(String s) {
        super(s);
    }
    
     public void configurarMenu() {
        configurarItemMenu();
        this.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        this.add(inserirQuestao);
        this.add(deletarQuestao);
        this.add(moverQuestao);
    }

    private void configurarItemMenu() {
        inserirQuestao = new JMenuItem("Inserir questão");
        inserirQuestao.addActionListener(new GerenciadorQuiz.MenuItemActionListener(inserirQuestao));
        inserirQuestao.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        
        deletarQuestao = new JMenuItem("Deletar questão");
        deletarQuestao.addActionListener(new GerenciadorQuiz.MenuItemActionListener(deletarQuestao));
        deletarQuestao.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        
        moverQuestao = new JMenuItem("Mover questão");
        moverQuestao.addActionListener(new GerenciadorQuiz.MenuItemActionListener(moverQuestao));
        moverQuestao.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
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
                case "Inserir questão":
                    JOptionPane.showMessageDialog(parent, item.getActionCommand() + " foi selecionado.");
                    break;
                case "Deletar questão":
                    JOptionPane.showMessageDialog(parent, item.getActionCommand() + " foi selecionado.");
                    break;
                case "Mover questão":
                    JOptionPane.showMessageDialog(parent, item.getActionCommand() + " foi selecionado.");
                    break;
                default:
                    break;
            }
        }
    }

}
