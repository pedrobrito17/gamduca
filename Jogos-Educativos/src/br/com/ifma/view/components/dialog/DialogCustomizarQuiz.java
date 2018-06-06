package br.com.ifma.view.components.dialog;

import br.com.ifma.model.Customizacao;
import br.com.ifma.view.components.Botao;
import br.com.ifma.view.components.utils.Fonte;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Pedro Brito
 */
public class DialogCustomizarQuiz extends JDialog{
    
    private JTabbedPane tabbed;
    private JPanel jpMensagem, jpTempo, jpAvaliacao, jpButtons;
    private JLabel msgErro, msgAcerto, msgTempo, msgSegundos, msgTaxa, msgInstrucao;
    private JTextField txtErro, txtAcerto, txtTempo;
    private Botao btnSalvar, btnCancelar;
    private JCheckBox chkAdicionarTempo;
    private JComboBox<Integer> cbTempoSegundos;
    private JComboBox<Integer> cbTaxa;
    
    private final int width = 550;
    private final int heigth = 25;
    
    private final Customizacao customizacao;

    public DialogCustomizarQuiz(Customizacao customizacao) throws HeadlessException {
        this.customizacao = customizacao;
        configJpMensagem();
        configJpTempo();
        configJpAvaliacao();
        configButtons();
        configTabbed();
        inicializarDialog();
    }
    
    private void configJpMensagem(){
        jpMensagem = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpMensagem.setBorder(new EmptyBorder(10, 10, 10, 10));
        msgAcerto = new JLabel("Messagem de acerto");
        msgAcerto.setFont(Fonte.retornarFontePadrao());
        txtAcerto = new JTextField(customizacao.getMsgAcerto());
        txtAcerto.setPreferredSize(new Dimension(width, heigth));
        jpMensagem.add(msgAcerto);
        jpMensagem.add(txtAcerto);
        
        msgErro = new JLabel("Menssagem de erro");
        msgErro.setFont(Fonte.retornarFontePadrao());
        txtErro = new JTextField(customizacao.getMsgErro());
        txtErro.setPreferredSize(new Dimension(width, heigth));
        jpMensagem.add(msgErro);
        jpMensagem.add(txtErro);
    }
    
    private void configJpTempo(){
        jpTempo = new JPanel();
        jpTempo.setLayout(new FlowLayout(FlowLayout.LEFT));
        jpTempo.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        msgInstrucao = new JLabel("O cronômetro será ativado em questões que "
                + "não possuirem arquivo multimídia");
        msgInstrucao.setFont(Fonte.retornarFontePadrao());
        jpTempo.add(msgInstrucao);
        
        chkAdicionarTempo = new JCheckBox("Adicionar cronômetro ao quiz            "
                + "                                                           "
                + "         ");
        chkAdicionarTempo.addItemListener((ItemEvent e) -> {
            if(chkAdicionarTempo.isSelected()){
                txtTempo.setEnabled(true);
                cbTempoSegundos.setEnabled(true);
                customizacao.setAtivarTempo(true);
            }
            else{
                txtTempo.setEnabled(false);
                cbTempoSegundos.setEnabled(false);
                customizacao.setAtivarTempo(false);
            }
        });
        chkAdicionarTempo.setFont(Fonte.retornarFontePadraoNegrito());
        chkAdicionarTempo.setFocusPainted(false);
        jpTempo.add(chkAdicionarTempo);
                
        msgTempo = new JLabel("Mensagem de tempo expirado");
        msgTempo.setFont(Fonte.retornarFontePadrao());
        txtTempo = new JTextField(customizacao.getMsgTempoAcabou());
        txtTempo.setPreferredSize(new Dimension(width, heigth));
        jpTempo.add(msgTempo);
        jpTempo.add(txtTempo);
        
        msgSegundos = new JLabel("segundos");
        msgSegundos.setFont(Fonte.retornarFontePadrao());
        Integer[] numeros = new Integer[61];
        int j = 0;
        for(int i = 30 ; i < 91 ; i++){
            numeros[j++]=i;
        }
        cbTempoSegundos = new JComboBox<>(numeros);
        cbTempoSegundos.setFont(Fonte.retornarFontePadrao());
        jpTempo.add(cbTempoSegundos);
        jpTempo.add(msgSegundos);
        
        if(customizacao.isAtivarTempo()){
            chkAdicionarTempo.setSelected(true);
            txtTempo.setEnabled(true);
            cbTempoSegundos.setEnabled(true);
            cbTempoSegundos.setSelectedItem(customizacao.getTempo());
        }
        else{
            chkAdicionarTempo.setSelected(false);
            txtTempo.setEnabled(false);
            cbTempoSegundos.setEnabled(false);
        }
    }
    
    private void configJpAvaliacao(){
        jpAvaliacao = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jpAvaliacao.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        msgTaxa = new JLabel("Taxa de acerto para avançar de fase              "
                + "                                                            "
                + "                              ");
        msgTaxa.setFont(Fonte.retornarFontePadrao());
        Integer [] numeros = new Integer[11];
        int j = 0;
        for(int i = 50 ; i < 105 ; i=i+5){
            numeros[j++]=i;
        }
        cbTaxa = new JComboBox<>(numeros);
        cbTaxa.setFont(Fonte.retornarFontePadrao());
        cbTaxa.setSelectedItem(customizacao.getTaxaAcerto());
        jpAvaliacao.add(msgTaxa);
        jpAvaliacao.add(cbTaxa);
        jpAvaliacao.add(new JLabel("%"));
    }
    
    private void configTabbed(){
        tabbed = new JTabbedPane();
        tabbed.setFont(Fonte.retornarFontePadrao());
        tabbed.add("Mensagem", jpMensagem);
        tabbed.add("Tempo", jpTempo);
        tabbed.add("Avaliação", jpAvaliacao);
    }
    
    private void configButtons(){
        jpButtons = new JPanel(new FlowLayout(FlowLayout.CENTER));
        btnCancelar = new Botao("Cancelar");
        btnCancelar.configurarBotao();
        btnSalvar = new Botao("Salvar");
        btnSalvar.configurarBotao();
        jpButtons.add(btnCancelar);
        jpButtons.add(btnSalvar);
        
        btnCancelar.addActionListener((ActionEvent e) -> {
            this.dispose();
        });
        
        btnSalvar.addActionListener((ActionEvent e) -> {
            customizacao.setMsgAcerto(txtAcerto.getText());
            customizacao.setMsgErro(txtErro.getText());
            customizacao.setMsgTempoAcabou(txtTempo.getText());
            customizacao.setTempo((int)cbTempoSegundos.getSelectedItem());
            customizacao.setTaxaAcerto((int)cbTaxa.getSelectedItem());
            this.dispose();
        });
    }

    private void inicializarDialog() {
        this.setSize(600, 300);
        this.setLayout(new BorderLayout());
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setTitle("Customizar quiz");
        this.add(tabbed, BorderLayout.CENTER);
        this.add(jpButtons, BorderLayout.SOUTH);
        this.setVisible(true);
    }
}
