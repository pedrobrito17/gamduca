package br.com.ifma.view.components.filter;

import br.com.ifma.view.components.utils.FilterUtils;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Pedro Brito
 */
public class FiltroFileChooserAudio extends FileFilter {

    public FiltroFileChooserAudio() {

    }

    @Override
    public boolean accept(File pathname) {
        if (pathname.isDirectory()) {
            return true;
        }

        String extension = FilterUtils.getExtension(pathname);
        if (extension != null) {
            return extension.equals(FilterUtils.MP3)
                    || extension.equals(FilterUtils.AAC)
                    || extension.equals(FilterUtils.OGC)
                    || extension.equals(FilterUtils.WAV)
                    || extension.equals(FilterUtils.WMA);
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Arquivos de áudio (.mp3, .aac, .ogc, .wav, .wma)";
    }
    
}


