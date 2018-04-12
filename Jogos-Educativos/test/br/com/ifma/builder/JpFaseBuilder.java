package br.com.ifma.builder;

import br.com.ifma.view.components.jpanel.JpFase;
import br.com.ifma.view.components.jpanel.JpPergunta;
import br.com.ifma.view.components.jpanel.JpQuestao;
import br.com.ifma.view.components.jpanel.JpRespostaMultiplaEscolha;
import java.util.ArrayList;

/**
 *
 * @author Pedro Brito
 */
public class JpFaseBuilder {
    
    private final JpFase jpFase;
    
    public JpFaseBuilder(){
        this.jpFase = new JpFase();
    }
    
    public JpFaseBuilder obterJpFaseBuilder(){
        JpFaseBuilder jpBuilder = new JpFaseBuilder();
    
        ArrayList<JpQuestao> jpQuestoes = jpBuilder.getJpFase().getJpQuestoes();
        JpQuestao jpQuestao = jpQuestoes.get(0);
        
        JpPergunta jpPergunta = jpQuestao.getJpPergunta();
        jpPergunta.setTxtPergunta("Campeão da Copa do Mundo de 2002?");
        jpPergunta.setTipoResposta("Múltipla escolha");
        jpPergunta.setUrlMultimidia("/home/pedro/Imagens/carro.png");
        
        JpRespostaMultiplaEscolha jpResp = jpQuestao.getJpRespostaMultiplaEscolha();
        jpResp.setRespA("Brasil");
        jpResp.setRespB("Alemanha");
        jpResp.setRespC("Uruguai");
        jpResp.setRespD("Holanda");
        jpResp.setRespCorreta("A");
    
        return jpBuilder;
    }
    
    public JpFase getJpFase(){
        return this.jpFase;
    }
    

}
