package br.com.ifma.gerador;

import br.com.ifma.model.Fase;
import br.com.ifma.model.Questao;
import br.com.ifma.model.Quiz;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Pedro Brito
 */
public class GeradorMultimidia {

    public void gerarArquivosMultimidiaDoQuiz(Quiz quiz, String path) throws FileNotFoundException, IOException {
        ArrayList<Fase> fases = quiz.getFases();

        for (Fase fase : fases) {
            HashMap<String, Questao> map = fase.getQuestoes();

            for (Map.Entry<String, Questao> entry : map.entrySet()) {
                String key = entry.getKey();
                Questao questao = entry.getValue();

                if (!questao.getPergunta().getUrlMultimidia().isEmpty()) {
                    String source = questao.getPergunta().getUrlMultimidia();
                    String destination = null;
                    switch (questao.getPergunta().getTipoMultimidia()) {
                        case "imagem":
                            destination = path + "/imagem/"+key;
                            break;
                        case "audio":
                            destination = path + "/audio/"+key;
                            break;
                        case "video":
                            destination = path + "/video/"+key;
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
                    
                    questao.getPergunta().setUrlMultimidia(destination);
                }
            }
        }

    }
}
