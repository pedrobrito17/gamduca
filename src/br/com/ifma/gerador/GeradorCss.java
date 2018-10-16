package br.com.ifma.gerador;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author Pedro Brito
 */
public class GeradorCss {
    
    public static void exportarCss(String path) throws URISyntaxException, FileNotFoundException, IOException{
        exportarArquivoCss("quiz.css", path);
        exportarArquivoCss("bootstrap.min.css", path);
        
        exportarFonts("FiraSans-Bold.ttf", path+"/fonts");
        exportarFonts("FiraSans.ttf", path+"/fonts");
        exportarFonts("montserrat.ttf", path+"/fonts");
    }
    
    public static void exportarArquivoCss(String name, String path) throws URISyntaxException, FileNotFoundException, IOException{
        URL url = GeradorJs.class.getClassLoader().getResource("frontend/"+name);
        File file = new File(path + "/" +name);
        FileUtils.copyURLToFile(url, file);
    }
    
    public static void exportarFonts(String nameFont, String path) throws URISyntaxException, FileNotFoundException, IOException{
        URL urlfonts = GeradorJs.class.getClassLoader().getResource("frontend/"+nameFont);
        File fileFonts = new File(path + "/"+nameFont);
        FileUtils.copyURLToFile(urlfonts, fileFonts);
    }

}
