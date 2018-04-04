package br.com.ifma.view.components.panel;

import br.com.ifma.view.components.Botao;
import br.com.ifma.view.components.config.Fonte;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderImage;
import javax.swing.Icon;
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
public final class Questao extends JPanel implements ItemListener{
    
    private JPanel panelPergunta, panelResposta, panelQuestao, panelTextArea, panelOpcoes, panelLinks;
    private JPanel p1, p2;
    private TipoQuestao jpTipoQuestao;
    private JLabel labelQuestao;
    private JTextArea textAreaPergunta;
    private JScrollPane sp;
    private JComboBox cbtipoQuestao;
    private Botao btnAnterior, btnProximo;
    private JButton img, audio, video, link;
    private final String[] tipos = {"Múltipla escolha", "Pergunta direta", "Verdadeiro ou falso"};

    public Questao() {
        configPanelOpcoes();
        configPanelPergunta();
        configPanelResposta();
        configPanelQuestao();
    }
    
    public void configPanelOpcoes(){
        panelQuestao = new JPanel(new FlowLayout(FlowLayout.CENTER));
        labelQuestao = new JLabel("Questão");
        labelQuestao.setFont(new Font(Fonte.FONTE.getFonte(), Font.BOLD, 14));
        labelQuestao.setBorder(new EmptyBorder(0, 0, 10, 0));
        panelQuestao.add(labelQuestao);
        
        panelOpcoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        cbtipoQuestao = new JComboBox(tipos);
        cbtipoQuestao.setEditable(false);
        cbtipoQuestao.setFont(new Font(Fonte.FONTE.getFonte(), Font.PLAIN, Fonte.TAMANHO.getTamanhoDaFonte()));
        cbtipoQuestao.addItemListener(this);
        btnAnterior = new Botao("Anterior");
        btnAnterior.configurarBotao();
        btnProximo = new Botao("Próxima");
        btnProximo.configurarBotao();
        panelOpcoes.add(cbtipoQuestao);
        panelOpcoes.add(btnAnterior);
        panelOpcoes.add(btnProximo);
        
                
        panelLinks = new JPanel(new FlowLayout(FlowLayout.LEFT));
        img = new JButton("imagem");
        audio = new Botao("audio");
        video = new Botao("video");
        link = new Botao("link");
        panelLinks.add(img);
        panelLinks.add(audio);
        panelLinks.add(video);
        panelLinks.add(link);
        
        p1 = new JPanel(new GridLayout(2,1));
        p2 = new JPanel(new GridLayout(1,2));
        p2.add(panelLinks);
        p2.add(panelOpcoes);
        p1.add(panelQuestao);
        p1.add(p2);
    }
    
    public void configPanelPergunta(){
        panelPergunta = new JPanel(new BorderLayout());
        panelPergunta.setBorder(new EmptyBorder(10, 10, 10, 10));
        
        panelTextArea = new JPanel(new GridLayout(1,1));
        textAreaPergunta = new JTextArea();
        textAreaPergunta.setBorder(new EmptyBorder(5, 5, 5, 5));
        textAreaPergunta.setLineWrap(true);
        textAreaPergunta.setWrapStyleWord(true);
        sp = new JScrollPane(textAreaPergunta);
        sp.setPreferredSize(new Dimension(300, 100));
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panelTextArea.add(sp);

        panelPergunta.add(p1, BorderLayout.NORTH);
        panelPergunta.add(panelTextArea, BorderLayout.CENTER);
    }
    
    public void configPanelResposta(){
        jpTipoQuestao = new TipoQuestao();
        jpTipoQuestao.configPaineis();
    }
    
    public void configPanelQuestao(){
        this.setLayout(new BorderLayout());
        this.add(panelPergunta, BorderLayout.NORTH);
        this.add(jpTipoQuestao, BorderLayout.CENTER);
    }
    
    @Override
    public void itemStateChanged(ItemEvent evt) {
        CardLayout cl = (CardLayout)jpTipoQuestao.getLayout();
        cl.show(jpTipoQuestao, (String)evt.getItem());
    }
    
}
