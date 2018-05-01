package br.com.ifma.view.components.menu;

import br.com.ifma.view.components.utils.ArquivoQuizInterface;
import br.com.ifma.view.components.utils.Icone;
import br.com.ifma.view.components.utils.GerenciadorQuizInterface;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import br.com.ifma.view.components.utils.OpcoesQuizInterface;
import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 *
 * @author Pedro Brito
 */
public final class Toolbar extends JToolBar{
    
    private JButton abrirQuiz, novoQuiz, salvarQuiz, exportJogo, exportScorm;
    private JButton copy, cut, paste, selectAll;
    private JButton adicionarFase, deletarFase, customizarQuiz;
    private JButton addQuestao, moverQuestao, deletarQuestao;
    private JButton tutorial;
    private OpcoesQuizInterface opcoesInterface;
    private ArquivoQuizInterface arquivoInterface;
    private GerenciadorQuizInterface gerenciadorInterface;
    
    public Toolbar(Component parent) {
        if(parent instanceof OpcoesQuizInterface){
            opcoesInterface = (OpcoesQuizInterface) parent;
        }else{
            throw new RuntimeException(parent.toString()
                    + " deve implementar OpcoesQuizInterface");
        }
        
        if(parent instanceof ArquivoQuizInterface){
            arquivoInterface = (ArquivoQuizInterface) parent;
        }else{
            throw new RuntimeException(parent.toString()
                    + " deve implementar ArquivoQuizInterface");
        }
        
        if(parent instanceof GerenciadorQuizInterface){
            gerenciadorInterface = (GerenciadorQuizInterface) parent;
        }else{
            throw new RuntimeException(parent.toString()
                    + " deve implementar GerenciadorQuizInterface");
        }
        
        configBotoes();
        configToolBar();
    }
    
    private void configBotoes(){
        
        //ÍCONES DO ARQUIVO
        novoQuiz = getJButton("icones/new-file.png", "Novo quiz", "novo quiz");
        novoQuiz.addActionListener((ActionEvent e) -> {
            arquivoInterface.novoQuiz();
        });
        abrirQuiz = getJButton("icones/open-file.png", "Abrir quiz", "abrir quiz");
        abrirQuiz.addActionListener((ActionEvent e) -> {
            arquivoInterface.abrirQuiz();
        });
        salvarQuiz = getJButton("icones/save.png", "Salvar quiz", "salvar quiz");
        salvarQuiz.addActionListener((ActionEvent e) -> {
            arquivoInterface.salvarQuiz();
        });
        exportJogo = getJButton("icones/export-jogo.png", "Exportar jogo", "exportar jogo");
        exportJogo.addActionListener((ActionEvent e) -> {
            arquivoInterface.exportarJogo();
        });
        exportScorm = getJButton("icones/export-app.png", "Exportar pacote scorm", "exportar scorm");
        exportScorm.addActionListener((ActionEvent e) -> {
            arquivoInterface.exportarScorm();
        });
        
        //ÍCONES DO EDITAR
        copy = getJButton("icones/copy.png", "Copiar", "copiar");
        cut = getJButton("icones/cut.png", "Recortar", "recortar");
        paste = getJButton("icones/paste.png", "Colar", "colar");
        selectAll = getJButton("icones/select-all.png", "Selecionar tudo", "selecionar tudo");
        
        //ÍCONES DO OPÇÕES
        adicionarFase = getJButton("icones/adicionar-fase.png", "Adicionar fase", "adicionar fase ao Quiz");
        adicionarFase.addActionListener((ActionEvent e) -> {
            opcoesInterface.adicionarFase();
        });
        deletarFase = getJButton("icones/deletar-fase.png", "Deletar fase", "deletar fase do Quiz");
        deletarFase.addActionListener((ActionEvent e) -> {
            opcoesInterface.deletarFase();
        });
        customizarQuiz = getJButton("icones/personalizar-quiz.png", "Customizar quiz", "customizar quiz");
        customizarQuiz.addActionListener((ActionEvent e) -> {
            opcoesInterface.personalizarQuiz();
        });
        
        //ÍCONES DO GERENCIADOR
        addQuestao = getJButton("icones/add-questao.png", "Adicionar questão", "adicionar questão a fase");
        addQuestao.addActionListener((ActionEvent e) -> {
            gerenciadorInterface.adicionarQuestao();
        });
        deletarQuestao = getJButton("icones/deletar-questao.png", "Deletar questão", "deletar questão da fase");
        deletarQuestao.addActionListener((ActionEvent e) -> {
            gerenciadorInterface.deletarQuestao();
        });
        moverQuestao = getJButton("icones/mover-questao.png", "Mover questão", "mover questão da fase");
        moverQuestao.addActionListener((ActionEvent e) -> {
            gerenciadorInterface.moverQuestao();
        });
        
        //ÍCONES DO AJUDA
        tutorial = getJButton("icones/tutorial.png", "Tutorial", "tutorial");
    }
    
    private JButton getJButton(String path, String tooltip, String name){
        JButton button = new JButton( Icone.retornarImageIcon(path) );
        button.setToolTipText(tooltip);
        button.setName(name);
        button.setFocusPainted(false);
        return button;
    }
    
    private void configToolBar(){
        this.addSeparator();
        this.add(novoQuiz);
        this.add(abrirQuiz);
        this.add(salvarQuiz);
        this.add(exportJogo);
        this.add(exportScorm);
        this.addSeparator();
        this.add(cut);
        this.add(copy);
        this.add(paste);
        this.add(selectAll);
        this.addSeparator();
        this.add(adicionarFase);
        this.add(deletarFase);
        this.add(customizarQuiz);
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
