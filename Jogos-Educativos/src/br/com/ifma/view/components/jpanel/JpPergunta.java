package br.com.ifma.view.components.jpanel;

import br.com.ifma.view.components.config.Fonte;
import br.com.ifma.view.components.dialog.DialogMidia;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.ImageIcon;
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
    private JLabel midia;
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
                
        midia = new JLabel(new ImageIcon(getClass().getClassLoader().getResource("icones/multimedia.png")));
        midia.setBorder(new EmptyBorder(0, 10 , 0, 0));
        midia.addMouseListener(new EventoMouse());
        midia.setToolTipText("Selecionar multimídia");
        
        jpOpcoes = new JPanel(new FlowLayout(5, 15, 5));
        jpOpcoes.add(midia);
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

class EventoMouse implements MouseListener{

    @Override
    public void mouseClicked(MouseEvent e) {
        EventQueue.invokeLater(() -> {
            DialogMidia dialog = new DialogMidia();
        });
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}