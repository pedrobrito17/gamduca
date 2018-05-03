package br.com.ifma.controller;

import br.com.ifma.model.Fase;
import br.com.ifma.model.Pergunta;
import br.com.ifma.model.Questao;
import br.com.ifma.model.Quiz;
import br.com.ifma.model.RespostaPerguntaDireta;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JFrame;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 *
 * @author Pedro Brito
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class JogoControllerTest {
    
    static JogoController jogoController;
    static Quiz quiz;
    
    @BeforeClass
    public static void configClass() {     
        jogoController = new JogoController();
        
        quiz = new Quiz();
        quiz.setTituloQuiz("Teste");

        Questao questao = new Questao();
        Pergunta pergunta = new Pergunta();
        pergunta.setUrlMultimidia("/home/pedro/Imagens/469439.jpg");
        pergunta.setTipoMultimidia("imagem");
        questao.setPergunta(pergunta);
        questao.setTituloQuestao("Questão 1");
        questao.setRespPerguntaDir(new RespostaPerguntaDireta("Teste"));
        HashMap<String, Questao> map = new HashMap<>();
        map.put("Questão 1", questao);

        Fase fase = new Fase(map);
        ArrayList<Fase> list = new ArrayList<>();
        list.add(fase);
        quiz.setFases(list);
        
    }
    
    @AfterClass
    public static void finalyClass() {
        deletarDiretorioExistente(new File(jogoController.pathJogo));
    }
    
    public static void deletarDiretorioExistente(File arquivo) {
        if (arquivo.exists()) {
            File[] arquivosDoDiretorio = arquivo.listFiles();
            if (arquivosDoDiretorio != null) {
                for (File file : arquivosDoDiretorio) {
                    deletarDiretorioExistente(file);
                }
            }
            arquivo.delete();
        }
    }
    
    @Test
    public void testAObterCaminhoParaSalvarJogo() {
        JFrame frame = new JFrame();
        
        jogoController.obterCaminhoParaSalvarJogo(frame);
    }

    @Test
    public void testBCriarTodosOsDiretorios() {
        jogoController.criarTodosOsDiretorios();
        
        String path = jogoController.pathJogo;
        Assert.assertTrue(new File(path).exists());
    }

    @Test
    public void testCriarArquivosDoJogo() {
        
        String path = jogoController.pathJogo;
        String newPathImagem = path+"/multimidia/imagem/Questão 1";
        jogoController.criarArquivosDoJogo(quiz);
        
        Assert.assertTrue(new File(newPathImagem).exists());
    }
    
}
