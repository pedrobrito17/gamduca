package br.com.ifma.controller;

import br.com.ifma.gerador.GeradorMultimidia;
import br.com.ifma.model.Quiz;
import java.io.File;
import java.io.IOException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Brito
 */
public class JogoController {

    String pathJogo;
    private String pathCss, pathJs, pathMultimidia, pathVideo, pathAudio, pathImagem;

    public static void main(String[] args) {
        JogoController jogo = new JogoController();
        jogo.obterCaminhoParaSalvarJogo(new JFrame());
        jogo.criarTodosOsDiretorios();

//        jogo.criarArquivosDoJogo(quiz);
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
        deletarDiretorioExistente(diretorio);

        boolean verificador = diretorio.mkdir();
        if (!verificador) {
            JOptionPane.showMessageDialog(null, "O diretório escolhido não "
                    + "pode ser usado", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deletarDiretorioExistente(File arquivo) {
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

    public void criarArquivosDoJogo(Quiz quiz) {
        GeradorMultimidia geradorMultimidia = new GeradorMultimidia();
        try {
            geradorMultimidia.gerarArquivosMultimidiaDoQuiz(quiz, pathMultimidia);
        } catch (IOException ex) {
            System.out.println(ex.toString());
            JOptionPane.showMessageDialog(null, "Houve um erro na geração do "
                    + "jogo. Por favor tente novamente", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
