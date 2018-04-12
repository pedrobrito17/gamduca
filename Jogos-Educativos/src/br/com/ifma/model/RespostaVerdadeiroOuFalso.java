package br.com.ifma.model;

/**
 *
 * @author Pedro Brito
 */
public class RespostaVerdadeiroOuFalso {
    
    public String[] respA, respB, respC, respD;

    public RespostaVerdadeiroOuFalso(String[] respA, String[] respB, String[] respC, String[] respD) {
        this.respA = respA;
        this.respB = respB;
        this.respC = respC;
        this.respD = respD;
    }

    public String[] getRespA() {
        return respA;
    }

    public void setRespA(String[] respA) {
        this.respA = respA;
    }

    public String[] getRespB() {
        return respB;
    }

    public void setRespB(String[] respB) {
        this.respB = respB;
    }

    public String[] getRespC() {
        return respC;
    }

    public void setRespC(String[] respC) {
        this.respC = respC;
    }

    public String[] getRespD() {
        return respD;
    }

    public void setRespD(String[] respD) {
        this.respD = respD;
    }
    
}
