package br.com.ifma.controller;

import br.com.ifma.gerador.GeradorCss;
import br.com.ifma.gerador.GeradorHtml;
import br.com.ifma.gerador.GeradorJs;
import br.com.ifma.gerador.GeradorMultimidia;
import br.com.ifma.model.Quiz;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Brito
 */
public class JogoController {

    String pathRoot;
    protected String path, pathRecursos, pathCss, pathJs, pathMultimidia, pathVideo, pathAudio, 
            pathImagem, pathFonts;

    public boolean obterCaminhoParaSalvarJogo(JFrame frame, String tituloDialog) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setDialogTitle("Exportar "+tituloDialog);
        fc.setApproveButtonText("Exportar");

        int retorno = fc.showOpenDialog(frame);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            path = fc.getSelectedFile().getPath();
            pathRoot =  path + "/Quiz";
            pathRecursos = pathRoot + "/recursos";
            pathCss = pathRoot + "/recursos/css";
            pathFonts = pathCss + "/fonts";
            pathJs = pathRoot + "/recursos/js";
            pathMultimidia = pathRoot + "/recursos/multimidia";
            pathVideo = pathMultimidia + "/video";
            pathAudio = pathMultimidia + "/audio";
            pathImagem = pathMultimidia + "/imagem";
            return true;
        }
        return false;
    }

    public void criarTodosOsDiretorios() {
        criarDiretorio(pathRoot);
        criarDiretorio(pathRecursos);
        criarDiretorio(pathCss);
        criarDiretorio(pathFonts);
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

    public void deletarDiretorioExistente(File arquivo) {
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

    public void criarArquivos(Quiz quiz) {
        try {
            GeradorMultimidia.gerarArquivosMultimidiaDoQuiz(quiz, pathMultimidia);
            GeradorJs.gerarJavaScript(quiz, pathJs);
            GeradorCss.exportarCss(pathCss);
            GeradorHtml.exportarHtml(pathRoot);
        } catch (URISyntaxException ex) {
            System.out.println(ex.toString());
            JOptionPane.showMessageDialog(null, "Houve um erro na geração do "
                    + "jogo. Por favor tente novamente", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch(IOException io ){
            System.out.println(io.toString());
            JOptionPane.showMessageDialog(null, "O arquivo multimídia não foi "
                    + "encontrado.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
        }
    }

}
