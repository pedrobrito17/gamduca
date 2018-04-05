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
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Pedro Brito
 */
public final class DialogLink extends JFrame {

    private Botao btnCancelar, btnOk;
    private JPanel jpBotoes, jpTextField;
    private TextField textField;

    public DialogLink() throws HeadlessException {
        configTextField();
        configBotoes();
        inicializarFrame();
    }

    public void configTextField() {
        textField = new TextField();
        textField.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));

        jpTextField = new JPanel(new GridLayout(1, 1));
        jpTextField.setBorder(new EmptyBorder(10, 10, 10, 10));
        jpTextField.add(textField);
        jpTextField.setPreferredSize(new Dimension(100, 25));
    }

    public void configBotoes() {
        btnCancelar = new Botao("Cancelar");
        btnCancelar.configurarBotao();
        btnCancelar.addActionListener((ActionEvent e) -> {
            this.dispose();
        });

        btnOk = new Botao("Inserir");
        btnOk.configurarBotao();
        btnOk.addActionListener((ActionEvent e) -> {
            System.out.println(textField.getText());
            this.dispose();
        });

        jpBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        jpBotoes.add(btnCancelar);
        jpBotoes.add(btnOk);
    }

    public void inicializarFrame() {
        this.setSize(400, 90);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.add(jpTextField, BorderLayout.CENTER);
        this.add(jpBotoes, BorderLayout.SOUTH);
        this.setVisible(true);
    }

}