package br.com.ifma.view.components.menu;

import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import br.com.ifma.view.components.utils.OpcoesQuizInterface;
import java.awt.Component;
import java.util.ArrayList;
import javax.swing.JFrame;

/**
 *
 * @author Pedro Brito
 */
public final class Toolbar extends JToolBar{
    
    private JButton exportJogo, exportApp;
    private JButton copy, cut, paste, selectAll;
    private JButton adicionarFase, deletarFase, personalizarQuiz;
    private JButton addQuestao, moverQuestao, deletarQuestao;
    private JButton tutorial;
    private OpcoesQuizInterface tabbedInterface;
    
    public Toolbar(JFrame quiz) {
        if(quiz instanceof OpcoesQuizInterface){
            tabbedInterface = (OpcoesQuizInterface) quiz;
        }else{
            throw new RuntimeException(quiz.toString()
                    + " deve implementar onLoginCompletedListener");
        }
        configBotoes();
        configToolBar();
    }
    
    private void configBotoes(){
        exportJogo = getJButton("icones/export-jogo.png", "Exportar jogo", "exportar jogo");
        exportApp = getJButton("icones/export-app.png", "Exportar pacote scorm", "exportar scorm");
        
        copy = getJButton("icones/copy.png", "Copiar", "copiar");
        cut = getJButton("icones/cut.png", "Recortar", "recortar");
        paste = getJButton("icones/paste.png", "Colar", "colar");
        selectAll = getJButton("icones/select-all.png", "Selecionar tudo", "selecionar tudo");
        
        adicionarFase = getJButton("icones/adicionar-fase.png", "Adicionar fase", "adcionar fase");
        adicionarFase.addActionListener((ActionEvent e) -> {
            tabbedInterface.adicionarFase();
        });
        deletarFase = getJButton("icones/deletar-fase.png", "Deletar fase", "deletar fase");
        deletarFase.addActionListener((ActionEvent e) -> {
            tabbedInterface.deletarFase();
        });
        personalizarQuiz = getJButton("icones/personalizar-quiz.png", "Personalizar quiz", "personalizar quiz");
        personalizarQuiz.addActionListener((ActionEvent e) -> {
            tabbedInterface.personalizarQuiz();
        });
        
        addQuestao = getJButton("icones/add-questao.png", "Adicionar questão", "adicionar questão");
        deletarQuestao = getJButton("icones/deletar-questao.png", "Deletar questão", "deletar questão");
        moverQuestao = getJButton("icones/mover-questao.png", "Mover questão", "mover questão");
        
        tutorial = getJButton("icones/tutorial.png", "Tutorial", "tutorial");
    }
    
    private JButton getJButton(String path, String tooltip, String name){
        JButton button = new JButton( createImageIcon(path) );
        button.setToolTipText(tooltip);
        button.setName(name);
        button.setFocusPainted(false);
        return button;
    }

    private static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = Toolbar.class.getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Arquivo não encontrado: " + path);
            return null;
        }
    }
    
    private void configToolBar(){
        this.addSeparator();
        this.add(exportJogo);
        this.add(exportApp);
        this.addSeparator();
        this.add(cut);
        this.add(copy);
        this.add(paste);
        this.add(selectAll);
        this.addSeparator();
        this.add(adicionarFase);
        this.add(deletarFase);
        this.add(personalizarQuiz);
        this.addSeparator();
        this.add(addQuestao);
        this.add(moverQuestao);
        this.add(deletarQuestao);
        this.addSeparator();
        this.add(tutorial);
        this.setFloatable(false);
    }
    
    private ArrayList<JButton> getTodosBotoes(){
        Component[] components = this.getComponents();
        ArrayList<JButton> botoesToolbar = new ArrayList<>();
        for (Component component : components) {
            if (component.getName() != null) {
                botoesToolbar.add((JButton) component);
            }
        }
        return botoesToolbar;
    }
    
}
