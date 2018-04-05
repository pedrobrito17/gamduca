package br.com.ifma.view.components.filter;

import br.com.ifma.view.components.utils.FilterUtils;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Pedro Brito
 */
public class FiltroFileChooserImagem extends FileFilter {

    public FiltroFileChooserImagem() {

    }

    @Override
    public boolean accept(File pathname) {
        if (pathname.isDirectory()) {
            return true;
        }

        String extension = FilterUtils.getExtension(pathname);
        if (extension != null) {
            return extension.equals(FilterUtils.GIF)
                    || extension.equals(FilterUtils.JPEG)
                    || extension.equals(FilterUtils.JPG)
                    || extension.equals(FilterUtils.PNG);
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Imagem";
    }

}