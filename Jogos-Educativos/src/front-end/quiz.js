

//Classe
function RespostaMultiplaEscolha(){
  this.respA = "";
  this.respB = "";
  this.respC = "";
  this.respD = "";
  this.repostaCorreta = "";
}

//Classe
function VerdadeiroOuFalso(){
  this.respA = {txt : "", resp : false}
  this.respB = {txt : "", resp : false}
  this.respC = {txt : "", resp : false}
  this.respD = {txt : "", resp : false}
}

//Classe
function PerguntaDireta(){
  this.resposta = "";
}

//Classe
function Pergunta(){
  this.txtPergunta = "";
  this.urlMultimidia = "";
  this.tipoMultimidia = "";
}

//Classe
function Questao() {
  var tituloQuestao = "";
  var tipoQuestao = "";
  var pergunta = new Pergunta();
  var respostaMultiplaEscolha = new RespostaMultiplaEscolha();
  var perguntaDireta = new PerguntaDireta();
  var verdadeiroOuFalso = new VerdadeiroOuFalso();
  
  this.getPergunta = function(){
    return pergunta;
  }
  
  this.setPergunta = function(perg){
    pergunta = perg;
  }
  
  this.getPerguntaDireta = function(){
    return perguntaDireta;
  }
  
  this.setPerguntaDireta = function(pergDir){
    perguntaDireta = pergDir;
  }
  
  this.getVerdadeiroOuFalso = function(){
    return verdadeiroOuFalso;
  }
  
  this.setVerdadeiroOuFalso = function(vOuF){
    verdadeiroOuFalso = vOuF;
  }
  
  this.getRespostaMultiplaEscolha = function(){
    return respostaMultiplaEscolha;
  }
  
  this.setRespostaMultiplaEscolha = function(resp){
    respostaMultiplaEscolha = resp;
  }

  this.getTituloQuestao = function(){
    return tituloQuestao;
  }
  
  this.setTituloQuestao = function(titulo){
    tituloQuestao = titulo;
  }
  
  this.getTipoQuestao = function(){
    return tipoQuestao;
  }
  
  this.setTipoQuestao = function(tipo){
    tipoQuestao = tipo;
  }
}

var teste = new Questao();
var t = teste.getVerdadeiroOuFalso().respA.txt;
console.log(t);





































