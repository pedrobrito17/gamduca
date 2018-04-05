package br.com.ifma.view.components.jpanel;

import br.com.ifma.view.components.Botao;
import br.com.ifma.view.components.config.Fonte;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
public class JpPergunta extends JPanel implements ItemListener{
    
    private JPanel panelResposta, panelQuestao, panelTextArea, jpOpcoes;
    private JPanel jpgridOpcoes;
    private JLabel labelQuestao;
    private JTextArea textAreaPergunta;
    private JScrollPane sp;
    private JComboBox cbtipoQuestao;
    private Botao img, audio, video, link;
    private final String[] tipos = {"Múltipla escolha", "Pergunta direta", "Verdadeiro ou falso"};
    private final JPanel cardTipoResposta;

    public JpPergunta(JPanel cardTipoResposta) {
        this.cardTipoResposta = cardTipoResposta;
    }
    
    public void configPergunta(){
        configPanelOpcoes();
        configPanelTextAreaPergunta();
        this.setLayout(new BorderLayout());
        this.setBorder(new EmptyBorder(10, 10, 10, 10));
        this.add(jpgridOpcoes, BorderLayout.NORTH);
        this.add(panelTextArea, BorderLayout.CENTER);
    }
    
    public void configPanelOpcoes(){
        labelQuestao = new JLabel("Questão");
        labelQuestao.setFont(new Font(Fonte.FONTE.getFonte(), Font.BOLD, Fonte.TAMANHO.getTamanhoDaFonte()));
        labelQuestao.setBorder(new EmptyBorder(0, 0, 10, 0));
        panelQuestao = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelQuestao.add(labelQuestao);
        
        cbtipoQuestao = new JComboBox(tipos);
        cbtipoQuestao.setEditable(false);
        cbtipoQuestao.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        cbtipoQuestao.addItemListener(this);
                
        img = new Botao("imagem");
        img.configurarBotao();
        audio = new Botao("audio");
        audio.configurarBotao();
        video = new Botao("video");
        video.configurarBotao();
        link = new Botao("link");
        link.configurarBotao();
        
        jpOpcoes = new JPanel(new FlowLayout(5));
        jpOpcoes.add(img);
        jpOpcoes.add(audio);
        jpOpcoes.add(video);
        jpOpcoes.add(link);
        jpOpcoes.add(cbtipoQuestao);

        jpgridOpcoes = new JPanel(new BorderLayout());
        jpgridOpcoes.add(panelQuestao, BorderLayout.WEST);
        jpgridOpcoes.add(jpOpcoes, BorderLayout.EAST);
    }
    
    public void configPanelTextAreaPergunta(){
        panelTextArea = new JPanel(new GridLayout(1,1));
        textAreaPergunta = new JTextArea();
        textAreaPergunta.setBorder(new EmptyBorder(5, 5, 5, 5));
        textAreaPergunta.setLineWrap(true);
        textAreaPergunta.setWrapStyleWord(true);
        sp = new JScrollPane(textAreaPergunta);
        sp.setPreferredSize(new Dimension(300, 80));
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelTextArea.add(sp);
    }
    
    @Override
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)cardTipoResposta.getLayout();
        cl.show(cardTipoResposta, (String)evt.getItem());
    }

    public void setTituloQuestao(String titulo) {
        labelQuestao.setText(titulo);
    }
}
