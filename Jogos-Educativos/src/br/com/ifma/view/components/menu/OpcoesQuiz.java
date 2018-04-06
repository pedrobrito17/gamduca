package br.com.ifma.view.components.menu;

import br.com.ifma.view.components.config.Fonte;
import br.com.ifma.view.components.dialog.DialogMidia;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import br.com.ifma.view.components.utils.OpcoesQuizInterface;
import java.awt.EventQueue;

/**
 *
 * @author Pedro Brito
 */
public class OpcoesQuiz extends JMenu{
    
    private JMenuItem adicionarFase, personalizarQuiz;
    private OpcoesQuizInterface tabbedInterface;
    
    public OpcoesQuiz(String s) {
        super(s);
    }
    
    public OpcoesQuiz(JFrame quiz){
        if(quiz instanceof OpcoesQuizInterface){
            tabbedInterface = (OpcoesQuizInterface) quiz;
        }else{
            throw new RuntimeException(quiz.toString()
                    + " deve implementar onLoginCompletedListener");
        }
    }
    
    public void configurarMenu() {
        configurarItemMenu();
        this.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        this.add(adicionarFase);
        this.add(personalizarQuiz);
    }

    private void configurarItemMenu() {
        adicionarFase = new JMenuItem("Adicionar fase");
        adicionarFase.addActionListener(new OpcoesQuiz.MenuItemActionListener(adicionarFase));
        adicionarFase.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        
        personalizarQuiz = new JMenuItem("Personalizar quiz");
        personalizarQuiz.addActionListener(new OpcoesQuiz.MenuItemActionListener(personalizarQuiz));
        personalizarQuiz.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
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
                case "Personalizar quiz":
                    tabbedInterface.personalizarQuiz();
                    break;
                default:
                    break;
            }
        }
    }

}
