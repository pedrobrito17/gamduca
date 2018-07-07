package br.com.ifma.gerador;

import br.com.ifma.model.Fase;
import br.com.ifma.model.Questao;
import br.com.ifma.model.Quiz;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Pedro Brito
 */
public class GeradorMultimidia {

    public static void gerarArquivosMultimidiaDoQuiz(Quiz quiz, String path) throws FileNotFoundException, IOException, NullPointerException {
        ArrayList<Fase> fases = quiz.getFases();

        for (Fase fase : fases) {
            HashMap<String, Questao> map = fase.getQuestoes();

            for (Map.Entry<String, Questao> entry : map.entrySet()) {
                String key = entry.getKey();
                Questao questao = entry.getValue();

                if (questao.getPergunta().getUrlMultimidia() != null && !questao.getPergunta().getTipoMultimidia().equals("link")) {
                    String source = questao.getPergunta().getUrlMultimidia();
                    String destination = null;
                    String urlArquivo = null;
                    switch (questao.getPergunta().getTipoMultimidia()) {
                        case "imagem":
                            destination = path + "/imagem/" + key;
                            urlArquivo = "recursos/multimidia/imagem/" + key;
                            break;
                        case "audio":
                            destination = path + "/audio/" + key;
                            urlArquivo = "recursos/multimidia/audio/" + key;
                            break;
                        case "video":
                            destination = path + "/video/" + key;
                            urlArquivo = "recursos/multimidia/video/" + key;
                            break;
                    }

                    FileChannel sourceChannel = new FileInputStream(source).getChannel();
                    FileChannel destinationChannel = new FileOutputStream(destination).getChannel();
                    sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);

                    if (sourceChannel.isOpen()) {
                        sourceChannel.close();
                    }
                    if (destinationChannel != null && destinationChannel.isOpen()) {
                        destinationChannel.close();
                    }

                    questao.getPergunta().setUrlMultimidia(urlArquivo);
                }
            }
        }
        
        exportarImagemAprovado(path);
    }

    public static void exportarImagemAprovado(String path) throws FileNotFoundException, IOException {
        URL url = GeradorMultimidia.class.getClassLoader().getResource("icones/aprovado.jpg");
        File file = new File(path + "/imagem/aprovado.jpg");
        FileUtils.copyURLToFile(url, file);     
    }
}
