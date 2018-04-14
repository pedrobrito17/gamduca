package br.com.ifma.view.components.dialog;

import br.com.ifma.view.components.Botao;
import br.com.ifma.view.components.filter.FiltroFileChooserAudio;
import br.com.ifma.view.components.filter.FiltroFileChooserImagem;
import br.com.ifma.view.components.filter.FiltroFileChooserVideo;
import br.com.ifma.view.components.utils.Fonte;
import br.com.ifma.view.components.utils.Icone;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.io.File;
import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;

/**
 *
 * @author Pedro Brito
 */
public class DialogMidia extends JDialog {

    private JLabel selecao, caminho;
    private final String TEXTO = "Selecione o tipo de multimídia desejada para "
            + "adicionar à pergunta da questão.";
    private JRadioButton img, audio, video, link;
    private ButtonGroup group;
    private Botao btnCancelar, btnOk;
    private JPanel jpTexto, jpMidia, jpBotoes, jpTextMedia, jpCaminho;
    private JFileChooser fileChooser;
    private final ArrayList<String> listaPath;

    public DialogMidia(ArrayList<String> lista) throws HeadlessException {
        this.listaPath = lista;
        configText();
        configRadioButton();
        configJPTextMedia();
        configCaminho();
        configBotoes();
        inicializarDialog();
    }

    private void configText() {
        ImageIcon imgIcon = Icone.retornarImageIcon("icones/select.png");
        selecao = new JLabel(TEXTO, imgIcon, JLabel.LEFT);
        selecao.setFont(Fonte.retornarFontePadrao());

        jpTexto = new JPanel(new FlowLayout());
        jpTexto.setBorder(new EmptyBorder(10, 10, 5, 10));
        jpTexto.add(selecao);
    }

    private void configRadioButton() {
        img = new JRadioButton("Imagem");
        img.setFont(Fonte.retornarFontePadrao());
        img.setFocusPainted(false);
        img.setSelected(true);
        audio = new JRadioButton("Áudio");
        audio.setFont(Fonte.retornarFontePadrao());
        audio.setFocusPainted(false);
        video = new JRadioButton("Vídeo");
        video.setFont(Fonte.retornarFontePadrao());
        video.setFocusPainted(false);
        link = new JRadioButton("Link");
        link.setFont(Fonte.retornarFontePadrao());
        link.setFocusPainted(false);

        group = new ButtonGroup();
        group.add(img);
        group.add(audio);
        group.add(video);
        group.add(link);

        jpMidia = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 5));
        jpMidia.add(img);
        jpMidia.add(audio);
        jpMidia.add(video);
        jpMidia.add(link);
    }

    private void configJPTextMedia() {
        jpTextMedia = new JPanel(new BorderLayout());
        jpTextMedia.add(jpTexto, BorderLayout.NORTH);
        jpTextMedia.add(jpMidia, BorderLayout.CENTER);
    }

    private void configCaminho() {
        caminho = new JLabel();
        caminho.setFont(Fonte.retornarFontePadrao());
        int tam = listaPath.size();
        if(tam > 0){
            caminho.setText(listaPath.get(tam-1));
        }
        
        jpCaminho = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jpCaminho.setBorder(new EmptyBorder(10, 10, 5, 10));
        jpCaminho.add(caminho);
    }

    private void configBotoes() {
        btnCancelar = new Botao("Cancelar");
        btnCancelar.configurarBotao();
        btnCancelar.addActionListener((ActionEvent e) -> {
            dispose();
        });

        btnOk = new Botao("Ok");
        btnOk.configurarBotao();
        btnOk.addActionListener((ActionEvent e) -> {

            this.dispose();
            if (link.isSelected()) {
                DialogLink dialogLink = new DialogLink(listaPath);
            } else {
                fileChooser = new JFileChooser();
                fileChooser.addChoosableFileFilter(getFiltroDoRadioButtonSelected());
                fileChooser.setAcceptAllFileFilterUsed(false);
                int a = fileChooser.showOpenDialog(this);
                if (a == JFileChooser.APPROVE_OPTION) {
                    File file = fileChooser.getSelectedFile();
                    listaPath.add(file.getPath());
                }
            }

        });

        jpBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        jpBotoes.add(btnCancelar);
        jpBotoes.add(btnOk);
    }

    private void inicializarDialog() {
        this.setSize(550, 180);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(jpTextMedia, BorderLayout.NORTH);
        this.add(jpCaminho, BorderLayout.CENTER);
        this.add(jpBotoes, BorderLayout.SOUTH);
        this.setTitle("Selecionar multimídia");
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setVisible(true);
    }

    private FileFilter getFiltroDoRadioButtonSelected() {
        if (img.isSelected()) {
            return new FiltroFileChooserImagem();
        } else if (audio.isSelected()) {
            return new FiltroFileChooserAudio();
        } else {
            return new FiltroFileChooserVideo();
        }
    }

}
