package br.com.ifma.view.components.jpanel;

import br.com.ifma.view.components.config.Fonte;
import br.com.ifma.view.components.dialog.DialogMidia;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Pedro Brito
 */
public class JpPergunta extends JPanel implements ItemListener {

    private JPanel panelResposta, panelQuestao, panelTextArea, jpOpcoes;
    private JPanel jpgridOpcoes;
    private JLabel labelQuestao;
    private JTextArea textAreaPergunta;
    private JScrollPane sp;
    private JComboBox cbtipoQuestao;
    private JButton midia;
    private final String[] tipos = {"Múltipla escolha", "Pergunta direta", "Verdadeiro ou falso"};
    private final JPanel cardTipoResposta;
    private int indexCorrente = 0;
    private final ArrayList<String> listaUrlMultimida = new ArrayList<>();
    
    public JpPergunta(JPanel cardTipoResposta) {
        this.cardTipoResposta = cardTipoResposta;
        configPergunta();
    }

    private void configPergunta() {
        configPanelOpcoes();
        configPanelTextAreaPergunta();
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(jpgridOpcoes, BorderLayout.NORTH);
        this.add(panelTextArea, BorderLayout.CENTER);
    }

    private void configPanelOpcoes() {
        labelQuestao = new JLabel("Questão");
        labelQuestao.setFont(new Font(Fonte.FONTE.getFonte(), Font.BOLD, Fonte.TAMANHO.getTamanhoDaFonte()));
        labelQuestao.setBorder(new EmptyBorder(0, 0, 10, 0));
        panelQuestao = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelQuestao.add(labelQuestao);

        cbtipoQuestao = new JComboBox(tipos);
        cbtipoQuestao.setEditable(false);
        cbtipoQuestao.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        cbtipoQuestao.addItemListener(this);

        ImageIcon imgIcon = createImageIcon("icones/multimedia.png");
        midia = new JButton(imgIcon);
        midia.setBorderPainted(false);
        midia.setContentAreaFilled(false);
        midia.setFocusPainted(false);
        midia.setOpaque(false);
        midia.setToolTipText("Selecionar multimídia");
        midia.addActionListener((ActionEvent e) -> {
            DialogMidia dialog = new DialogMidia(listaUrlMultimida);
        });

        jpOpcoes = new JPanel(new FlowLayout(5, 15, 5));
        jpOpcoes.add(midia);
        jpOpcoes.add(cbtipoQuestao);

        jpgridOpcoes = new JPanel(new BorderLayout());
        jpgridOpcoes.add(panelQuestao, BorderLayout.WEST);
        jpgridOpcoes.add(jpOpcoes, BorderLayout.EAST);
    }

    private void configPanelTextAreaPergunta() {
        panelTextArea = new JPanel(new GridLayout(1, 1));
        textAreaPergunta = new JTextArea();
        textAreaPergunta.setBorder(new EmptyBorder(5, 5, 5, 5));
        textAreaPergunta.setLineWrap(true);
        textAreaPergunta.setWrapStyleWord(true);
        sp = new JScrollPane(textAreaPergunta);
        sp.setPreferredSize(new Dimension(300, 80));
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelTextArea.add(sp);
    }

    private static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = JpPergunta.class.getClassLoader().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Arquivo não encontrado: " + path);
            return null;
        }
    }

    @Override
    public void itemStateChanged(ItemEvent evt) {
        String linhaComboBox = (String) evt.getItem();
        
        //verifica se o item selecionado no combobox é igual ao item do evento
        if(cbtipoQuestao.getSelectedItem().equals(linhaComboBox)){

            CardLayout cl = (CardLayout) cardTipoResposta.getLayout();
            cl.show(cardTipoResposta, (String) evt.getItem());

            //limpa os textarea das resposta quando muda o combobox
            switch (indexCorrente) {
                case 0:
                    JpRespostaMultiplaEscolha panel0 = (JpRespostaMultiplaEscolha) cardTipoResposta.getComponent(indexCorrente);
                    panel0.clear();
                    break;
                case 1:
                    JpRespostaPerguntaDireta panel1 = (JpRespostaPerguntaDireta) cardTipoResposta.getComponent(indexCorrente);
                    panel1.clear();
                    break;
                case 2:
                    JpRespostaVerdadeiroOuFaso panel2 = (JpRespostaVerdadeiroOuFaso) cardTipoResposta.getComponent(indexCorrente);
                    panel2.clear();
                    break;
                default:
                    break;
            }
            indexCorrente = cbtipoQuestao.getSelectedIndex();
        }

    }

    public void setTituloQuestao(String titulo) {
        labelQuestao.setText(titulo);
    }
    
    public String getTituloQuestao(){
        return labelQuestao.getText();
    }
    
    public void setTxtPergunta(String pergunta){
        textAreaPergunta.setText(pergunta);
    }
    
    public String getTxtPergunta(){
        return textAreaPergunta.getText();
    }
    
    public void setTipoResposta(String tipoResposta){
        switch(tipoResposta){
            case "Múltipla escolha":
                cbtipoQuestao.setSelectedIndex(0);
                break;
            case "Pergunta direta":
                cbtipoQuestao.setSelectedIndex(1);
                break;
            case "Verdadeiro ou falso":
                cbtipoQuestao.setSelectedIndex(2);
                break;
            default:
                break;
        }
    }
    
    public String getTipoResposta(){
        return (String) cbtipoQuestao.getSelectedItem();
    }
    
    public void setUrlMultimidia(String url){
        listaUrlMultimida.add(url);
    }
    
    public String getUrlMultimidia(){
        int tam = listaUrlMultimida.size();
        if(tam > 0)
            return listaUrlMultimida.get(tam-1);
        else
            return null;
    }
    
    public boolean perguntaLimpa(){
        return textAreaPergunta.getText().isEmpty();
    }
    
    public boolean perguntaCompleta(){
        return !textAreaPergunta.getText().isEmpty();
    }

}
