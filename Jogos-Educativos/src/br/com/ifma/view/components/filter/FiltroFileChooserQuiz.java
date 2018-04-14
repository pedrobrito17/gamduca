package br.com.ifma.view.components.filter;

import br.com.ifma.view.components.utils.FilterUtils;
import java.io.File;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Pedro Brito
 */
public class FiltroFileChooserQuiz extends FileFilter {

    public FiltroFileChooserQuiz() {

    }

    @Override
    public boolean accept(File pathname) {
        if (pathname.isDirectory()) {
            return true;
        }

        String extension = FilterUtils.getExtension(pathname);
        if (extension != null) {
            return extension.equals(FilterUtils.JQZ);
        }

        return false;
    }

    @Override
    public String getDescription() {
        return "Arquivos do quiz (.jqz)";
    }
}
