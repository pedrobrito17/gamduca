package br.com.ifma.controller;

import br.com.ifma.gerador.GeradorScorm;
import br.com.ifma.model.Quiz;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Brito
 */
public class ScormController extends JogoController {
    
    /* Nome da pasta a ser criada foi alterada pois ela será apagada para
     * criação do arquivo .zip
     * Caso contrário poderia ter uma pasta quiz, criada pelo exportar jogo
     * e ela seria apagada após a criação do arquivo .zip
    */
    @Override
    public boolean obterCaminhoParaSalvarJogo(JFrame frame, String tituloDialog) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        fc.setDialogTitle("Exportar "+tituloDialog);
        fc.setApproveButtonText("Exportar");

        int retorno = fc.showOpenDialog(frame);
        if (retorno == JFileChooser.APPROVE_OPTION) {
            path = fc.getSelectedFile().getPath();
            pathRoot =  path + "/QuizZip";
            pathRecursos = pathRoot + "/recursos";
            pathCss = pathRoot + "/recursos/css";
            pathFonts = pathCss + "/fonts";
            pathJs = pathRoot + "/recursos/js";
            pathMultimidia = pathRoot + "/recursos/multimidia";
            pathVideo = pathMultimidia + "/video";
            pathAudio = pathMultimidia + "/audio";
            pathImagem = pathMultimidia + "/imagem";
            return true;
        }
        return false;
    }

    public void criarArquivosScorm(Quiz quiz) {
        try {
            GeradorScorm.exportarArquivosScorm(pathRoot, quiz);
        } catch (URISyntaxException | IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve erro na criação do "
                    + "pacote scorm.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public void criarPacote() {
        Compactador compactador = new Compactador();
        try {
            compactador.compactarParaZip(path + "/Quiz.zip", pathRoot);
            deletarDiretorioExistente(new File(pathRoot));
        } catch (IOException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Houve erro na criação do "
                    + "pacote scorm.", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    public class Compactador {

        //Constantes
        static final int TAMANHO_BUFFER = 4096; // 4kb
        private int tamStringPathRaiz;

        public void compactarParaZip(String pathArqZip, String pathDiretorioQuiz)
                throws IOException {

            int cont;
            byte[] dados = new byte[TAMANHO_BUFFER];
            tamStringPathRaiz = pathDiretorioQuiz.length();

            FileOutputStream destino = new FileOutputStream(new File(pathArqZip));
            ZipOutputStream zout = new ZipOutputStream(new BufferedOutputStream(destino));
            File file = new File(pathDiretorioQuiz);

            adicionarArquivo(zout, file);

            zout.close();

        }

        public void adicionarArquivo(ZipOutputStream zout, File diretorioComArquivos) 
                throws FileNotFoundException, IOException {

            File arquivos[] = diretorioComArquivos.listFiles();

            for (File arquivo : arquivos) {

                if (arquivo.isDirectory()) {
                    adicionarArquivo(zout, arquivo);
                } else {
                    
                    String pathArquivoZip = arquivo.getAbsolutePath().
                            substring(tamStringPathRaiz + 1);

                    FileInputStream streamDeEntrada = new FileInputStream(arquivo);
                    BufferedInputStream origem = new BufferedInputStream(streamDeEntrada, TAMANHO_BUFFER);
                    ZipEntry entry = new ZipEntry(pathArquivoZip);
                    zout.putNextEntry(entry);

                    int cont;
                    byte[] dados = new byte[TAMANHO_BUFFER];
                    while ((cont = origem.read(dados, 0, TAMANHO_BUFFER)) != -1) {
                        zout.write(dados, 0, cont);
                    }

                    zout.closeEntry();
                    origem.close();
                    streamDeEntrada.close();
                }
            }
        }

    }

}
