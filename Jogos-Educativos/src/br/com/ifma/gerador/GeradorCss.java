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
        URL url = GeradorJs.class.getClassLoader().getResource("front-end/quiz.css");
        File file = new File(url.toURI());
        FileInputStream source = new FileInputStream(file);

        FileChannel sourceChannel = source.getChannel();
        FileChannel destinationChannel = new FileOutputStream(path + "/quiz.css").getChannel();
        sourceChannel.transferTo(0, sourceChannel.size(), destinationChannel);

        if (sourceChannel.isOpen()) {
            sourceChannel.close();
        }
        if (destinationChannel != null && destinationChannel.isOpen()) {
            destinationChannel.close();
        }
    }

}
