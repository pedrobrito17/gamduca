package br.com.ifma.gerador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.channels.FileChannel;

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
        URL url = GeradorJs.class.getClassLoader().getResource("front-end/"+name);
        File file = new File(url.toURI());
        FileInputStream source = new FileInputStream(file);

        FileChannel sourceChannel = source.getChannel();
        FileChannel destinationChannel = new FileOutputStream(path + "/" +name).getChannel();
        sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);

        if (sourceChannel.isOpen()) {
            sourceChannel.close();
        }
        if (destinationChannel != null && destinationChannel.isOpen()) {
            destinationChannel.close();
        }
    }
    
    public static void exportarFonts(String nameFont, String path) throws URISyntaxException, FileNotFoundException, IOException{
        URL urlfonts = GeradorJs.class.getClassLoader().getResource("front-end/"+nameFont);
        File fileFonts = new File(urlfonts.toURI());
        FileInputStream sourceFonts = new FileInputStream(fileFonts);

        FileChannel sourceFontsChannel = sourceFonts.getChannel();
        FileChannel destinationFontsChannel = new FileOutputStream(path + "/"+nameFont).getChannel();
        sourceFontsChannel.transferTo(0, sourceFontsChannel.size(), destinationFontsChannel);

        if (sourceFontsChannel.isOpen()) {
            sourceFontsChannel.close();
        }
        if (destinationFontsChannel != null && destinationFontsChannel.isOpen()) {
            destinationFontsChannel.close();
        }
    }

}
