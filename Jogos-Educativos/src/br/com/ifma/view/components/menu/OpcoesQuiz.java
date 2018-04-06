package br.com.ifma.view.components.menu;

import br.com.ifma.view.components.config.Fonte;
import br.com.ifma.view.components.utils.TabbedInterface;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Brito
 */
public class OpcoesQuiz extends JMenu{
    
    private JMenuItem adicionarFase, configurarQuiz;
    private TabbedInterface tabbedInterface;
    
    public OpcoesQuiz(String s) {
        super(s);
    }
    
    public OpcoesQuiz(JFrame quiz){
        if(quiz instanceof TabbedInterface){
            tabbedInterface = (TabbedInterface) quiz;
        }else{
            throw new RuntimeException(quiz.toString()
                    + " deve implementar onLoginCompletedListener");
        }
    }
    
    public void configurarMenu() {
        configurarItemMenu();
        this.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        this.add(adicionarFase);
        this.add(configurarQuiz);
    }

    private void configurarItemMenu() {
        adicionarFase = new JMenuItem("Adicionar fase");
        adicionarFase.addActionListener(new OpcoesQuiz.MenuItemActionListener(adicionarFase));
        adicionarFase.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        
        configurarQuiz = new JMenuItem("Configurar quiz");
        configurarQuiz.addActionListener(new OpcoesQuiz.MenuItemActionListener(configurarQuiz));
        configurarQuiz.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
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
                case "Adicionar fase":
                    tabbedInterface.adicionarFase();
                    break;
                case "Configuração do quiz":
                    JOptionPane.showMessageDialog(parent, item.getActionCommand() + " foi selecionado.");
                    break;
                default:
                    break;
            }
        }
    }

}
