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
public class GeradorHtml {
    
    public static void exportarHtml(String path) throws URISyntaxException, FileNotFoundException, IOException{
        URL url = GeradorJs.class.getClassLoader().getResource("frontend/index.html");
        File file = new File(path + "/index.html");
        FileUtils.copyURLToFile(url, file);
    }

}
