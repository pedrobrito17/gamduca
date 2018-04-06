package br.com.ifma.view.components.filter;

import br.com.ifma.view.components.utils.FilterUtils;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Pedro Brito
 */
public class FiltroFileChooserVideo extends FileFilter {

    public FiltroFileChooserVideo() {

    }

    @Override
    public boolean accept(File pathname) {
        if (pathname.isDirectory()) {
            return true;
        }

        String extension = FilterUtils.getExtension(pathname);
        if (extension != null) {
            return extension.equals(FilterUtils.AVI)
                    || extension.equals(FilterUtils.FLV)
                    || extension.equals(FilterUtils.MKV)
                    || extension.equals(FilterUtils.MOV)
                    || extension.equals(FilterUtils.WAV)
                    || extension.equals(FilterUtils.MP4);
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Arquivos de vídeo (.avi, .flv, .mkv, .mov, .wav, .mp4)";
    }
    
}


