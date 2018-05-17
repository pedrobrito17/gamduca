package br.com.ifma.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author Pedro Brito
 */
public class ScormController extends JogoController {

    public void gerarArquivosScorm() {

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

        //método para compactar arquivo
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

        public void adicionarArquivo(ZipOutputStream zout, File diretorioComArquivos) throws FileNotFoundException, IOException {

            File arquivos[] = diretorioComArquivos.listFiles();

            for (File arquivo : arquivos) {

                if (arquivo.isDirectory()) {
                    adicionarArquivo(zout, arquivo);
                } else {
                    
                    String pathArquivoZip = arquivo.getAbsolutePath().substring(tamStringPathRaiz + 1);

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
