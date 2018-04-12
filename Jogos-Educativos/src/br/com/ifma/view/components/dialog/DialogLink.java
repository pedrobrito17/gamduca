package br.com.ifma.view.components.dialog;

import br.com.ifma.view.components.Botao;
import br.com.ifma.view.components.config.Fonte;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Pedro Brito
 */
public class DialogLink extends JDialog {

    private Botao btnCancelar, btnOk;
    private JPanel jpBotoes, jpTextField;
    private TextField textField;
    private final ArrayList<String> lista;
    
    public DialogLink(ArrayList<String> lista) throws HeadlessException {
        this.lista = lista;
        configTextField();
        configBotoes();
        inicializarDialog();
    }

    private void configTextField() {
        textField = new TextField();
        textField.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));

        jpTextField = new JPanel(new GridLayout(1, 1));
        jpTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        jpTextField.add(textField);
        jpTextField.setPreferredSize(new Dimension(100, 25));
    }

    private void configBotoes() {
        btnCancelar = new Botao("Cancelar");
        btnCancelar.configurarBotao();
        btnCancelar.addActionListener((ActionEvent e) -> {
            this.dispose();
        });

        btnOk = new Botao("Inserir");
        btnOk.configurarBotao();
        btnOk.addActionListener((ActionEvent e) -> {
            lista.add(textField.getText());
            this.dispose();
        });

        jpBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        jpBotoes.add(btnCancelar);
        jpBotoes.add(btnOk);
    }

    private void inicializarDialog() {
        this.setSize(400, 90);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(jpTextField, BorderLayout.CENTER);
        this.add(jpBotoes, BorderLayout.SOUTH);
        this.setTitle("Inserir url");
        this.setVisible(true);
    }

}
