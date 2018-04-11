package br.com.ifma.controller;

import br.com.ifma.model.Fase;
import br.com.ifma.view.components.jpanel.JpFase;
import br.com.ifma.view.components.jpanel.JpQuestao;
import java.util.ArrayList;

/**
 *
 * @author Pedro Brito
 */
public class FaseController {
    
    private ArrayList<JpFase> jpFases;
    private ArrayList<Fase> fases;
    
    public FaseController(){
    }

    public void setFases(ArrayList<JpFase> jpFases) {
        this.jpFases = jpFases;
    }
    
    public void getFase(JpFase jpFase){

        ArrayList<JpQuestao> jpQuestoes = jpFases.get(0).getJpQuestoes();//CORRIGIR ISSO
        for (JpQuestao jpQuestao : jpQuestoes) {
            
            if(jpQuestao.questaoCompleta()){
                String tituloQuestao = jpQuestao.getTituloQuestao();
                String pergunta = jpQuestao.getJpPergunta().getPergunta();
                String urlMultimidia = jpQuestao.getJpPergunta().getUrlMultimidia();
                String tipoQuestao = jpQuestao.getJpPergunta().getTipoResposta();
                
                System.out.println("Titulo questão: "+tituloQuestao);
                System.out.println("Tipo da questão: "+tipoQuestao);
                System.out.println("Pergunta: "+pergunta);
                System.out.println("URL multimidia: "+urlMultimidia);
                System.out.println("Respostas:");
                
                switch (tipoQuestao) {
                    case "Múltipla escolha":
                        {
                            String respA = jpQuestao.getJpRespostaMultiplaEscolha().getRespA();
                            String respB = jpQuestao.getJpRespostaMultiplaEscolha().getRespB();
                            String respC = jpQuestao.getJpRespostaMultiplaEscolha().getRespC();
                            String respD = jpQuestao.getJpRespostaMultiplaEscolha().getRespD();
                            String respCorreta = jpQuestao.getJpRespostaMultiplaEscolha().getRespCorreta();
                            System.out.println("RespA: "+respA + "\nRespB: " + respB + "\nRespC: " + respC + "\nRespD: " + respD);
                            System.out.println("Resposta correta:" +respCorreta);
                            break;
                        }
                    case "Pergunta direta":
                        String resp = jpQuestao.getJpRespostaPerguntaDireta().getResposta();
                        System.out.println(resp);
                        break;
                    case "Verdadeiro ou falso":
                        {
                            String[] respA = jpQuestao.getJpRespostaVerdadeiroOuFalso().getRespA();
                            String[] respB = jpQuestao.getJpRespostaVerdadeiroOuFalso().getRespB();
                            String[] respC = jpQuestao.getJpRespostaVerdadeiroOuFalso().getRespC();
                            String[] respD = jpQuestao.getJpRespostaVerdadeiroOuFalso().getRespD();
                            System.out.println("resp A: "+respA[0]+" - "+respA[1]);
                            System.out.println("resp B: "+respB[0]+" - "+respB[1]);
                            System.out.println("resp C: "+respC[0]+" - "+respC[1]);
                            System.out.println("resp D: "+respD[0]+" - "+respD[1]);
                            break;
                        }
                    default:
                        break;
                }
            }
            System.out.println("---------------------------------------");
        }
        
        
        
        
        
        
    }

}
