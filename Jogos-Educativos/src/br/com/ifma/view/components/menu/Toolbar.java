package br.com.ifma.view.components.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;
import br.com.ifma.view.components.utils.OpcoesQuizInterface;

/**
 *
 * @author Pedro Brito
 */
public final class Toolbar extends JToolBar{
    
    private JButton exportJogo, exportApp;
    private JButton copy, cut, paste, selectAll;
    private JButton adicionarFase, personalizarQuiz;
    private JButton addQuestao, moverQuestao, deletarQuestao;
    private JButton tutorial;
    private OpcoesQuizInterface tabbedInterface;
    
    public Toolbar(JFrame frame) {
        if(frame instanceof OpcoesQuizInterface){
            tabbedInterface = (OpcoesQuizInterface) frame;
        }else{
            throw new RuntimeException(frame.getClass() + "não instancionou "+tabbedInterface.getClass());
        }
        configBotoes();
        configToolBar();
    }
    
    public void configBotoes(){
        exportJogo = new JButton(new ImageIcon(getClass().getClassLoader().getResource("icones/export-jogo.png")));
        exportJogo.setToolTipText("Exportar jogo");
        exportJogo.setFocusPainted(false);
        exportApp = new JButton(new ImageIcon(getClass().getClassLoader().getResource("icones/export-app.png")));
        exportApp.setToolTipText("Exportar pacote scorm");
        exportApp.setFocusPainted(false);
        
        copy = new JButton(new ImageIcon(getClass().getClassLoader().getResource("icones/copy.png")));
        copy.setToolTipText("Copiar");
        copy.setFocusPainted(false);
        cut = new JButton(new ImageIcon(getClass().getClassLoader().getResource("icones/cut.png")));
        cut.setToolTipText("Recortar");
        cut.setFocusPainted(false);
        paste = new JButton(new ImageIcon(getClass().getClassLoader().getResource("icones/paste.png")));
        paste.setToolTipText("Colar");
        paste.setFocusPainted(false);
        selectAll = new JButton(new ImageIcon(getClass().getClassLoader().getResource("icones/select-all.png")));
        selectAll.setToolTipText("Selecionar tudo");
        selectAll.setFocusPainted(false);
        
        adicionarFase = new JButton(new ImageIcon(getClass().getClassLoader().getResource("icones/adicionar-fase.png")));
        adicionarFase.setToolTipText("Adicionar fase");
        adicionarFase.setFocusPainted(false);
        adicionarFase.addActionListener((ActionEvent e) -> {
            tabbedInterface.adicionarFase();
        });
        personalizarQuiz = new JButton(new ImageIcon(getClass().getClassLoader().getResource("icones/personalizar-quiz.png")));
        personalizarQuiz.setToolTipText("Personalizar quiz");
        personalizarQuiz.setFocusPainted(false);
        personalizarQuiz.addActionListener((ActionEvent e) -> {
            tabbedInterface.personalizarQuiz();
        });
        
        addQuestao = new JButton(new ImageIcon(getClass().getClassLoader().getResource("icones/add-questao.png")));
        addQuestao.setToolTipText("Adicionar questão");
        addQuestao.setFocusPainted(false);
        deletarQuestao = new JButton(new ImageIcon(getClass().getClassLoader().getResource("icones/deletar-questao.png")));
        deletarQuestao.setToolTipText("Deletar questão");
        deletarQuestao.setFocusPainted(false);
        moverQuestao = new JButton(new ImageIcon(getClass().getClassLoader().getResource("icones/mover-questao.png")));
        moverQuestao.setToolTipText("Mover questão");
        moverQuestao.setFocusPainted(false);
        tutorial = new JButton(new ImageIcon(getClass().getClassLoader().getResource("icones/tutorial.png")));
        tutorial.setToolTipText("Tutorial");
    }
    
    public void configToolBar(){
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
        this.add(personalizarQuiz);
        this.addSeparator();
        this.add(addQuestao);
        this.add(moverQuestao);
        this.add(deletarQuestao);
        this.addSeparator();
        this.add(tutorial);
        this.setFloatable(false);
    }
    
}
