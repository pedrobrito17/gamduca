package br.com.ifma.view.components.menu;

import br.com.ifma.view.components.config.Fonte;
import br.com.ifma.view.components.utils.GerenciadorQuizInterface;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

/**
 *
 * @author Pedro Brito
 */
public class GerenciadorQuiz extends JMenu{
    
    private JMenuItem addQuestao, deletarQuestao, moverQuestao;
    private GerenciadorQuizInterface gerenciadorInterface;
    
    public GerenciadorQuiz(Component parent) {
        if(parent instanceof GerenciadorQuizInterface){
            gerenciadorInterface = (GerenciadorQuizInterface) parent;
        }else{
            throw new RuntimeException(parent.toString()
                    + " deve implementar GerenciadorQuizInterface");
        }
        
        configurarMenu();
    }
    
     private void configurarMenu() {
        configurarItemMenu();
        this.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        this.add(addQuestao);
        this.add(moverQuestao);
        this.add(deletarQuestao);
    }

    private void configurarItemMenu() {
        addQuestao = new JMenuItem("Adicionar questão");
        addQuestao.addActionListener(new GerenciadorQuiz.MenuItemActionListener(addQuestao));
        addQuestao.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        
        deletarQuestao = new JMenuItem("Deletar questão");
        deletarQuestao.addActionListener(new GerenciadorQuiz.MenuItemActionListener(deletarQuestao));
        deletarQuestao.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        
        moverQuestao = new JMenuItem("Mover questão");
        moverQuestao.addActionListener(new GerenciadorQuiz.MenuItemActionListener(moverQuestao));
        moverQuestao.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
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
                case "Adicionar questão":
                    gerenciadorInterface.adicionarQuestao();
                    break;
                case "Deletar questão":
                    gerenciadorInterface.deletarQuestao();
                    break;
                case "Mover questão":
                    gerenciadorInterface.moverQuestao();
                    break;
                default:
                    break;
            }
        }
    }

}
