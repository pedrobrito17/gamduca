package br.com.ifma.view.components.utils;

import java.io.File;
import javax.swing.ImageIcon;

/**
 *
 * @author Pedro Brito
 */
public class FilterUtils {
    /* IMAGENS */
    public final static String JPEG = "jpeg";
    public final static String JPG = "jpg";
    public final static String GIF = "gif";
    public final static String PNG = "png";
    /* ÁUDIO */
    public final static String MP3 = "mp3";
    public final static String AAC = "aac";
    public final static String OGC = "ogc";
    public final static String WMA = "wma";
    public final static String WAV = "wav";
    /* VÍDEO */
    public final static String MP4 = "mp4";
    public final static String AVI = "avi";
    public final static String MOV = "mov";
    public final static String WMV = "wmv";
    public final static String FLV = "flv";
    public final static String MKV = "mkv";
    /*  QUIZ */
    public final static String JQZ = "jqz";

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = FilterUtils.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Arquivo não encontrado: " + path);
            return null;
        }
    }
}
