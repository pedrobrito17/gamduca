package br.com.ifma.gerador;

import br.com.ifma.model.Quiz;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Pedro Brito
 */
public class GeradorJs {

    public static void gerarJavaScript(Quiz quiz, String path) throws IOException, URISyntaxException {
        String json = new Gson().toJson(quiz);

        criarArquivoJS(path);
        escreverJsonNoArquivoJS(json, path);
        escreverJsNoArquivoJS(path);
        
        exportarJs("jquery.min.js", path);
        exportarJs("bootstrap.min.js", path);
        exportarJs("jspdf.min.js", path);
    }

    private static void escreverJsonNoArquivoJS(String json, String path) throws URISyntaxException, IOException {
        File file = new File(path + "/quiz.js");
        FileWriter fileWriter = new FileWriter(file, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter print = new PrintWriter(bufferedWriter);
        print.printf("var quiz = " + json);

        if (print != null) {
            print.close();
        }
    }

    private static void escreverJsNoArquivoJS(String path) throws URISyntaxException, FileNotFoundException, IOException {
        InputStream quizJs = GeradorJs.class.getClassLoader().getResourceAsStream("frontend/quiz.js");

        String linhaAuxiliar = "";
        Reader reader = new InputStreamReader(quizJs, "UTF-8");
        BufferedReader bf = new BufferedReader(reader);
        String linha = null;
        while ((linha = bf.readLine()) != null) {
            linhaAuxiliar = linhaAuxiliar + linha + "\n";
        }
        if (reader != null) {
            reader.close();
        }
        if (bf != null) {
            bf.close();
        }

        File fileDestination = new File(path + "/quiz.js");
        FileWriter fileWriter = new FileWriter(fileDestination, true);
        BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
        PrintWriter print = new PrintWriter(bufferedWriter);
        print.append(linhaAuxiliar);

        if (print != null) {
            print.flush();
            print.close();
        }

    }

    private static void criarArquivoJS(String path) throws IOException {
        File file = new File(path + "/quiz.js");
        boolean verificador = file.createNewFile();
        if (!verificador) {
            throw new IOException("Não foi possível criar o arquivo js");
        }
    }
    
    private static void exportarJs(String nameJs, String path) throws URISyntaxException, FileNotFoundException, IOException{
        URL urlfonts = GeradorJs.class.getClassLoader().getResource("frontend/"+nameJs);
        File destination = new File(path + "/"+nameJs);
        FileUtils.copyURLToFile(urlfonts, destination);
    }

}
