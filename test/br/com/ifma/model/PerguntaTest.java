package br.com.ifma.model;

import org.junit.Assert;
import org.junit.Test;

/**
 *
 * @author Pedro Brito
 */
public class PerguntaTest {

    @Test
    public void testDescobrirTipoMultimidia() {
        //cenário
        String URL = "/home/pedro/Imagens/carro.mp4";        
        //ação
        String resp = new Pergunta().descobrirTipoMultimidia(URL);
        //verificação
        Assert.assertEquals("video", resp);
    }
    
}
