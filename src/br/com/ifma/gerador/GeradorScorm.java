package br.com.ifma.gerador;

import br.com.ifma.model.Fase;
import br.com.ifma.model.Questao;
import br.com.ifma.model.Quiz;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Pedro Brito
 */
public class GeradorScorm {

    InputStream imanifest;

    public static void exportarArquivosScorm(String path, Quiz quiz) throws URISyntaxException, IOException {
        exportarArquivo(path, "adlcp_rootv1p2.xsd");
        exportarArquivo(path, "ims_xml.xsd");
        exportarArquivo(path, "imscp_rootv1p1p2.xsd");
        exportarArquivo(path, "imsmd_rootv1p2p1.xsd");

        InputStream imanifest = GeradorScorm.class.getResourceAsStream("/frontend/scorm/imsmanifest.xml");
        String stringImanifest = lerArquivo(imanifest);
        stringImanifest = stringImanifest.replace("?titulo", quiz.getTituloQuiz());
        stringImanifest = stringImanifest.replace("?MANIFEST", getIdentificador("MANIFEST"));
        stringImanifest = stringImanifest.replace("?ORG", getIdentificador("ORG"));
        stringImanifest = stringImanifest.replace("?ITEM", getIdentificador("ITEM"));
        stringImanifest = stringImanifest.replace("?RES", getIdentificador("RES"));
        stringImanifest = stringImanifest.replace("?arquivos", obterStringParaAlterarImanifest(quiz));
        salvarArquivo(stringImanifest, new File(path + "/imsmanifest.xml"));
    }

    private static void exportarArquivo(String path, String nameArquivo) throws URISyntaxException, FileNotFoundException, IOException {
        URL url = GeradorJs.class.getClassLoader().getResource("frontend/scorm/" + nameArquivo);
        File file = new File(path + "/" + nameArquivo);
        FileUtils.copyURLToFile(url, file);
    }

    private static String lerArquivo(InputStream file) throws UnsupportedEncodingException, IOException {
        String linhaAuxiliar = "";
        Reader reader = new InputStreamReader(file, "UTF-8");
        BufferedReader bf = new BufferedReader(reader);
        String linha = null;
        while ((linha = bf.readLine()) != null) {
            linhaAuxiliar = linhaAuxiliar + linha + "\n";
        }
        reader.close();
        bf.close();

        return linhaAuxiliar;
    }

    private static String obterStringParaAlterarImanifest(Quiz quiz) {
        ArrayList<Fase> fases = quiz.getFases();
        String userFiles = "";

        for (Fase fase : fases) {
            HashMap<String, Questao> questoes = fase.getQuestoes();

            for (Map.Entry<String, Questao> entry : questoes.entrySet()) {
                Questao questao = entry.getValue();
                String tipoMultimidia = questao.getPergunta().getTipoMultimidia();
                if (tipoMultimidia != null && !tipoMultimidia.equals("link")) {
                    userFiles = userFiles + "<file href=\"" + questao.getPergunta().getUrlMultimidia() + "\" />\n";
                }
            }
        }
        return userFiles;
    }

    private static void salvarArquivo(String arquivo, File destino) throws FileNotFoundException, UnsupportedEncodingException {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(destino), "UTF-8"));
        pw.append(arquivo);
        pw.flush();
        pw.close();
    }

    private static String getIdentificador(String inicio) {
        char[] caracteres = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's',
            't', 'u', 'v', 'w', 'x', 'x', 'z', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
        
        String identificador = inicio + "-";
        Random random = new Random();
        for (int i = 0; i < 32; i++) {
                identificador = identificador + caracteres[random.nextInt(35)];
        }
        return identificador.toUpperCase();
    }

}
