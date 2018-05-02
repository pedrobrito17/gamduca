package br.com.ifma.controller;

import br.com.ifma.gerador.GeradorMultimidia;
import br.com.ifma.model.Fase;
import br.com.ifma.model.Pergunta;
import br.com.ifma.model.Questao;
import br.com.ifma.model.Quiz;
import br.com.ifma.model.RespostaPerguntaDireta;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Brito
 */
public class JogoController {

    private String pathJogo;
    private String pathCss, pathJs, pathMultimidia, pathVideo, pathAudio, pathImagem;

    public static void main(String[] args) {
        JogoController jogo = new JogoController();
        jogo.obterCaminhoParaSalvarJogo(new JFrame());
        jogo.criarTodosOsDiretorios();
        
        Quiz quiz = new Quiz();
        quiz.setTituloQuiz("Teste");
        
        Questao questao = new Questao();
        Pergunta pergunta = new Pergunta();
        pergunta.setUrlMultimidia("/home/pedro/Imagens/469439.jpg");
        questao.setPergunta(pergunta);
        questao.setTituloQuestao("Questão 1");
        questao.setRespPerguntaDir(new RespostaPerguntaDireta("Teste"));
        HashMap<String, Questao> map = new HashMap<>();
        map.put("Questão 1", questao);
        
        Fase fase = new Fase(map);
        ArrayList<Fase> list = new ArrayList<>();
        list.add(fase);
        quiz.setFases(list);
        
        
        jogo.criarArquivosDoJogo(quiz);
    }

    public void obterCaminhoParaSalvarJogo(JFrame frame) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setDialogTitle("Exportar jogo");
        fc.setApproveButtonText("Exportar");

        int retorno = fc.showOpenDialog(frame);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            pathJogo = fc.getSelectedFile().getPath() + "/Quiz";
            pathCss = pathJogo + "/css";
            pathJs = pathJogo + "/js";
            pathMultimidia = pathJogo + "/multimidia";
            pathVideo = pathMultimidia + "/video";
            pathAudio = pathMultimidia + "/audio";
            pathImagem = pathMultimidia + "/imagem";
        }
    }

    public void criarTodosOsDiretorios() {
        criarDiretorio(pathJogo);
        criarDiretorio(pathCss);
        criarDiretorio(pathJs);
        criarDiretorio(pathMultimidia);
        criarDiretorio(pathVideo);
        criarDiretorio(pathAudio);
        criarDiretorio(pathImagem);
    }

    private void criarDiretorio(String path) {
        File diretorio = new File(path);
        boolean verificador = diretorio.mkdir();
        if (!verificador) {
            JOptionPane.showMessageDialog(null, "O diretório escolhido não pode ser usado", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void criarArquivosDoJogo(Quiz quiz){
        GeradorMultimidia geradorMultimidia = new GeradorMultimidia();
        geradorMultimidia.gerarMultimidia(quiz, pathMultimidia);
    }

}
