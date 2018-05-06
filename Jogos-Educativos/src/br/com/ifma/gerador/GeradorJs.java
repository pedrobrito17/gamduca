package br.com.ifma.gerador;

import br.com.ifma.model.Quiz;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.URISyntaxException;
import java.net.URL;

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
        URL url = GeradorJs.class.getClassLoader().getResource("front-end/quiz.js");
        File fileSource = new File(url.toURI());
        FileInputStream source = new FileInputStream(fileSource);

        String linhaAuxiliar = "";
        Reader reader = new InputStreamReader(source, "UTF-8");
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

}
