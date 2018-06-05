
/* ##### CLASSES ##### */

//Classe
function RespostaMultiplaEscolha() {
  this.respA = "";
  this.respB = "";
  this.respC = "";
  this.respD = "";
  this.repostaCorreta = "";
}

//Classe
function VerdadeiroOuFalso() {
  this.respA = {
    txt: "",
    resp: false
  }
  this.respB = {
    txt: "",
    resp: false
  }
  this.respC = {
    txt: "",
    resp: false
  }
  this.respD = {
    txt: "",
    resp: false
  }
}

//Classe
function PerguntaDireta() {
  this.resposta = "";
}

//Classe
function Pergunta() {
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

  this.getPergunta = function () {
    return pergunta;
  }

  this.setPergunta = function (perg) {
    pergunta = perg;
  }

  this.getPerguntaDireta = function () {
    return perguntaDireta;
  }

  this.setPerguntaDireta = function (pergDir) {
    perguntaDireta = pergDir;
  }

  this.getVerdadeiroOuFalso = function () {
    return verdadeiroOuFalso;
  }

  this.setVerdadeiroOuFalso = function (vOuF) {
    verdadeiroOuFalso = vOuF;
  }

  this.getRespostaMultiplaEscolha = function () {
    return respostaMultiplaEscolha;
  }

  this.setRespostaMultiplaEscolha = function (resp) {
    respostaMultiplaEscolha = resp;
  }

  this.getTituloQuestao = function () {
    return tituloQuestao;
  }

  this.setTituloQuestao = function (titulo) {
    tituloQuestao = titulo;
  }

  this.getTipoQuestao = function () {
    return tipoQuestao;
  }

  this.setTipoQuestao = function (tipo) {
    tipoQuestao = tipo;
  }
}

//Classe
function Fase() {
  var questoes = [];

  this.setQuestoes = function (_questoes) {
    questoes = _questoes;
  }

  this.getQuestoes = function () {
    return questoes;
  }
}

/* ##### VARIÁVEIS GLOBAIS ##### */

var fases = [];
var indexFaseAtual = -1;
var indexQuestaoAtual;
var qtdFasesDoQuiz;
var qtdQuestoesDaFaseAtual;
var tipoQuestaoAtual;
var respostaQuestaoAtual;
var respostaQuestaoAtualVouF = [];
var porcentagemTotal;
var consultaMult = false;
var consultPerguntaDir = false;
var notaFase = [];
var cronometroAtivo;
var tempoCronometro = -1;
var timeout;
var tAcerto = [];


/* ##### MÉTODOS ##### */

function getObjFase(num) {
  var jsonFase = quiz.fases[num];
  var numQuestoes = Object.keys(jsonFase.questoes).length;
  var questoes = [];

  for (var i = 0; i < numQuestoes; i++) {
    var questao = new Questao();
    questao.setTipoQuestao(jsonFase.questoes["Questão " + (i + 1)].tipoQuestao);
    questao.setTituloQuestao(jsonFase.questoes["Questão " + (i + 1)].tituloQuestao);

    var pergunta = new Pergunta();
    pergunta.txtPergunta = jsonFase.questoes["Questão " + (i + 1)].pergunta.txtPergunta;
    pergunta.tipoMultimidia = jsonFase.questoes["Questão " + (i + 1)].pergunta.tipoMultimidia;
    pergunta.urlMultimidia = jsonFase.questoes["Questão " + (i + 1)].pergunta.urlMultimidia;

    questao.setPergunta(pergunta);

    switch (questao.getTipoQuestao()) {
      case "Múltipla escolha":
        var multipla = new RespostaMultiplaEscolha();
        multipla.respA = jsonFase.questoes["Questão " + (i + 1)].respMulti.respA;
        multipla.respB = jsonFase.questoes["Questão " + (i + 1)].respMulti.respB;
        multipla.respC = jsonFase.questoes["Questão " + (i + 1)].respMulti.respC;
        multipla.respD = jsonFase.questoes["Questão " + (i + 1)].respMulti.respD;
        multipla.repostaCorreta = jsonFase.questoes["Questão " + (i + 1)].respMulti.respostaCorreta;

        questao.setRespostaMultiplaEscolha(multipla);
        break;

      case "Verdadeiro ou falso":
        var vOuF = new VerdadeiroOuFalso();
        vOuF.respA = jsonFase.questoes["Questão " + (i + 1)].respVerd.respA;
        vOuF.respB = jsonFase.questoes["Questão " + (i + 1)].respVerd.respB;
        vOuF.respC = jsonFase.questoes["Questão " + (i + 1)].respVerd.respC;
        vOuF.respD = jsonFase.questoes["Questão " + (i + 1)].respVerd.respD;

        questao.setVerdadeiroOuFalso(vOuF);
        break;

      case "Pergunta direta":
        var pergDireta = new PerguntaDireta();
        pergDireta.resposta = jsonFase.questoes["Questão " + (i + 1)].respPerguntaDir.resposta;

        questao.setPerguntaDireta(pergDireta);
        break;
    }

    questoes.push(questao);
  }

  var fase = new Fase();
  fase.setQuestoes(questoes);
  return fase;
}

function gerarObjFases() {
  for (var i = 0; i < qtdFasesDoQuiz; i++) {
    fases[i] = getObjFase(i);
  }
}

function inserirNoHtmlProximaQuestao() {
  indexQuestaoAtual++;

  if (indexQuestaoAtual == qtdQuestoesDaFaseAtual) {
    exibirModalDeFaseAtualConcluida();
    return;
  }

  atualizarBarraDeProgresso();
  limparRespostas();

  var questao = fases[indexFaseAtual].getQuestoes()[indexQuestaoAtual];
  document.getElementById("tituloQuestao").innerHTML = questao.getTituloQuestao();
  document.getElementById("txtpergunta").innerHTML = questao.getPergunta().txtPergunta;

  //ATIVA O CRONOMETRO
  if (cronometroAtivo && questao.getPergunta().tipoMultimidia == null) {
    document.getElementById("col-progress").className = "col-md-10";
    document.getElementById("cronometro").parentElement.className = "col-md-2 addmargin";
    document.getElementById("cronometro").className = "text-center alert alert-warning tempo";
    tempoCronometro = quiz.customizacao.tempo;
    clearTimeout(timeout);
    cronometrarQuestao();
  }
  else if(cronometroAtivo && questao.getPergunta().tipoMultimidia != null){
    document.getElementById("cronometro").parentElement.className = "invisible";
    document.getElementById("col-progress").className = "col-md-12";
    clearTimeout(timeout);
  }

  if (questao.getPergunta().tipoMultimidia != null) {
    var multimidia = document.getElementById("multimidia");
    if (typeof multimidia != "undefined") {
      multimidia.className = "multimidia";
    }

    switch (questao.getPergunta().tipoMultimidia) {
      case "video":
        var link = document.getElementById("link");
        if (typeof link != "undefined") {
          link.className = "invisible";
        }
        var youtube = document.getElementById("youtube");
        if (youtube != "undefined") {
          youtube.className = "invisible";
        }
        var audio = document.getElementById("audio");
        if (typeof audio != "undefined") {
          audio.className = "invisible";
        }
        var img = document.getElementById("imagem");
        if (typeof img != "undefined") {
          img.className = "invisible";
        }
        var video = document.getElementById("video");
        video.innerHTML = '<video controls><source class="embed-responsive-item" src="' + questao.getPergunta().urlMultimidia + '"></video>';
        video.className = "embed-responsive embed-responsive-16by9 visible";
        break;

      case "audio":
        var link = document.getElementById("link");
        if (typeof link != "undefined") {
          link.className = "invisible";
        }
        var youtube = document.getElementById("youtube");
        if (youtube != "undefined") {
          youtube.className = "invisible";
        }
        var img = document.getElementById("imagem");
        if (typeof img != "undefined") {
          img.className = "invisible";
        }
        var video = document.getElementById("video");
        if (typeof video != "undefined") {
          video.className = "invisible";
        }
        var audio = document.getElementById("audio");
        audio.innerHTML = '<div class="row "><audio class="center" controls><source src="' + questao.getPergunta().urlMultimidia + '"></audio></div>';
        audio.className = "container-fluid visible";
        break;

      case "imagem":
        var link = document.getElementById("link");
        if (typeof link != "undefined") {
          link.className = "invisible";
        }
        var youtube = document.getElementById("youtube");
        if (youtube != "undefined") {
          youtube.className = "invisible";
        }
        var video = document.getElementById("video");
        if (typeof video != "undefined") {
          video.className = "invisible";
        }
        var audio = document.getElementById("audio");
        if (typeof audio != "undefined") {
          audio.className = "invisible";
        }
        var img = document.getElementById("imagem");
        img.className = "img-fluid visible";
        img.src = questao.getPergunta().urlMultimidia;
        break;

      case "link":
        var video = document.getElementById("video");
        if (typeof video != "undefined") {
          video.className = "invisible";
        }
        var audio = document.getElementById("audio");
        if (typeof audio != "undefined") {
          audio.className = "invisible";
        }
        var img = document.getElementById("imagem");
        if (typeof img != "undefined") {
          img.className = "invisible";
        }
        var link = questao.getPergunta().urlMultimidia;
        criarIFrameParaLink(link);
        break;
    }
  } else {
    var multimidia = document.getElementById("multimidia");
    if (typeof multimidia != "undefined") {
      multimidia.className = "invisible";
    }
  }

  switch (questao.getTipoQuestao()) {
    case "Múltipla escolha":
      tipoQuestaoAtual = "multipla-escolha";
      respostaQuestaoAtual = questao.getRespostaMultiplaEscolha().repostaCorreta;

      document.getElementById("verdadeiro-falso").className = "invisible";
      document.getElementById("pergunta-direta").className = "invisible";
      document.getElementById("multipla-escolha").className = "visible";

      var mult = questao.getRespostaMultiplaEscolha();
      document.getElementById("op-a").innerHTML = mult.respA;
      document.getElementById("op-b").innerHTML = mult.respB;
      document.getElementById("op-c").innerHTML = mult.respC;
      document.getElementById("op-d").innerHTML = mult.respD;
      break;

    case "Verdadeiro ou falso":
      tipoQuestaoAtual = "v-ou-f";

      document.getElementById("multipla-escolha").className = "invisible";
      document.getElementById("pergunta-direta").className = "invisible";
      document.getElementById("verdadeiro-falso").className = "visible";

      var vOuf = questao.getVerdadeiroOuFalso();
      document.getElementById("option-a").innerHTML = vOuf.respA[0];
      document.getElementById("option-b").innerHTML = vOuf.respB[0];
      document.getElementById("option-c").innerHTML = vOuf.respC[0];
      document.getElementById("option-d").innerHTML = vOuf.respD[0];

      respostaQuestaoAtualVouF[0] = vOuf.respA[1];
      respostaQuestaoAtualVouF[1] = vOuf.respB[1];
      respostaQuestaoAtualVouF[2] = vOuf.respC[1];
      respostaQuestaoAtualVouF[3] = vOuf.respD[1];
      break;

    case "Pergunta direta":
      tipoQuestaoAtual = "pergunta-direta";
      respostaQuestaoAtual = questao.getPerguntaDireta().resposta;


      document.getElementById("multipla-escolha").className = "invisible";
      document.getElementById("verdadeiro-falso").className = "invisible";
      document.getElementById("pergunta-direta").className = "visible";

      break;
  }
}

function limparRespostas() {
  consultaMult = false;
  document.getElementById("op-a").parentElement.className = "mult-opcao";
  document.getElementById("op-b").parentElement.className = "mult-opcao";
  document.getElementById("op-c").parentElement.className = "mult-opcao";
  document.getElementById("op-d").parentElement.className = "mult-opcao";

  document.getElementById("vf-a").className = "mult-opcao";
  document.getElementById("v-a").disabled = false;
  document.getElementById("f-a").disabled = false;
  document.getElementById("v-a").checked = false;
  document.getElementById("f-a").checked = false;
  document.getElementById("vf-b").className = "mult-opcao";
  document.getElementById("v-b").disabled = false;
  document.getElementById("f-b").disabled = false;
  document.getElementById("v-b").checked = false;
  document.getElementById("f-b").checked = false;
  document.getElementById("vf-c").className = "mult-opcao";
  document.getElementById("v-c").disabled = false;
  document.getElementById("f-c").disabled = false;
  document.getElementById("v-c").checked = false;
  document.getElementById("f-c").checked = false;
  document.getElementById("vf-d").className = "mult-opcao";
  document.getElementById("v-d").disabled = false;
  document.getElementById("f-d").disabled = false;
  document.getElementById("v-d").checked = false;
  document.getElementById("f-d").checked = false;

  consultPerguntaDir = false;
  document.getElementById("resposta-direta").disabled = false;
  document.getElementById("resposta-direta").value = "";
  document.getElementById("resposta-direta").className = "form-control";
}

function criarIFrameParaLink(link) {
  if (!link.includes("youtube")) {
    var tagIframe = document.getElementById("link");
    tagIframe.innerHTML = '<iframe class="embed-responsive-item" src="' + link + '"></iframe>';
    tagIframe.className = "embed-responsive embed-responsive-16by9 visible";
    return;
  }

  var posicaoInicio = link.search("=") + 1;
  var posicaoFinal = link.length;
  var novoLink = link.slice(posicaoInicio, posicaoFinal);
  var iframe = '<iframe class="embed-responsive-item" src="https://www.youtube.com/embed/' + novoLink + '" frameborder="0" allow="autoplay; encrypted-media" allowfullscreen></iframe>';
  document.getElementById("youtube").innerHTML = iframe;
  document.getElementById("youtube").className = "embed-responsive embed-responsive-16by9 visible";
}

function verificarRespostaMult(opcao) {
  if (opcao.toUpperCase() == respostaQuestaoAtual && consultaMult == false) {
    var opcaoSelecionada = document.getElementById("op-" + opcao).parentElement;
    opcaoSelecionada.className = "alert alert-success";

    if (opcao != 'a') {
      document.getElementById("op-a").parentElement.className = "desativado";
    }
    if (opcao != 'b') {
      document.getElementById("op-b").parentElement.className = "desativado";
    }
    if (opcao != 'c') {
      document.getElementById("op-c").parentElement.className = "desativado";
    }
    if (opcao != 'd') {
      document.getElementById("op-d").parentElement.className = "desativado";
    }
    consultaMult = true;
    notaFase[indexFaseAtual]++;
    exibirModalDe('acerto');

  } else if (consultaMult == false) {
    var opcaoSelecionada = document.getElementById("op-" + opcao).parentElement;
    opcaoSelecionada.className = "alert alert-danger";

    if (opcao != 'a') {
      document.getElementById("op-a").parentElement.className = "desativado";
    }
    if (opcao != 'b') {
      document.getElementById("op-b").parentElement.className = "desativado";
    }
    if (opcao != 'c') {
      document.getElementById("op-c").parentElement.className = "desativado";
    }
    if (opcao != 'd') {
      document.getElementById("op-d").parentElement.className = "desativado";
    }
    consultaMult = true;
    exibirModalDe('erro');
  }
}

function verificarRespostaPerguntaDireta() {
  var resp = document.getElementById("resposta-direta");
  if (resp.value.toUpperCase() == respostaQuestaoAtual.toUpperCase()) {
    resp.className = "form-control is-valid";
    resp.disabled = true;
    consultPerguntaDir = true;
    notaFase[indexFaseAtual]++;
    exibirModalDe('acerto');
  } else {
    resp.className = "form-control is-invalid";
    resp.disabled = true;
    consultPerguntaDir = true;
    exibirModalDe('erro');
  }
}

function verificarRespostaVerdadeiroOuFalso(booleano, opcao) {
  switch (opcao) {
    case 'a':
      if (booleano == (respostaQuestaoAtualVouF[0] == 'true')) {
        document.getElementById('vf-a').className = "alert alert-success";
        document.getElementById('v-a').disabled = true;
        document.getElementById('f-a').disabled = true;
        notaFase[indexFaseAtual] += 0.25;
        exibirModalDe('acerto');
      } else {
        document.getElementById('vf-a').className = "alert alert-danger";
        document.getElementById('v-a').disabled = true;
        document.getElementById('f-a').disabled = true;
        exibirModalDe('erro');
      }
      break;

    case 'b':
      if (booleano == (respostaQuestaoAtualVouF[1] == 'true')) {
        document.getElementById('vf-b').className = "alert alert-success";
        document.getElementById('v-b').disabled = true;
        document.getElementById('f-b').disabled = true;
        notaFase[indexFaseAtual] += 0.25;
        exibirModalDe('acerto');
      } else {
        document.getElementById('vf-b').className = "alert alert-danger";
        document.getElementById('v-b').disabled = true;
        document.getElementById('f-b').disabled = true;
        exibirModalDe('erro');
      }
      break;

    case 'c':
      if (booleano == (respostaQuestaoAtualVouF[2] == 'true')) {
        document.getElementById('vf-c').className = "alert alert-success";
        document.getElementById('v-c').disabled = true;
        document.getElementById('f-c').disabled = true;
        notaFase[indexFaseAtual] += 0.25;
        exibirModalDe('acerto');
      } else {
        document.getElementById('vf-c').className = "alert alert-danger";
        document.getElementById('v-c').disabled = true;
        document.getElementById('f-c').disabled = true;
        exibirModalDe('erro');
      }
      break;

    case 'd':
      if (booleano == (respostaQuestaoAtualVouF[3] == 'true')) {
        document.getElementById('vf-d').className = "alert alert-success";
        document.getElementById('v-d').disabled = true;
        document.getElementById('f-d').disabled = true;
        notaFase[indexFaseAtual] += 0.25;
        exibirModalDe('acerto');
      } else {
        document.getElementById('vf-d').className = "alert alert-danger";
        document.getElementById('v-d').disabled = true;
        document.getElementById('f-d').disabled = true;
        exibirModalDe('erro');
      }
      break;
  }
}

function exibirModalDe(erroOuAcerto) {
  if (erroOuAcerto == 'acerto') {
    document.getElementById("txtErroOuAcerto").innerHTML = quiz.customizacao.msgAcerto;
    document.getElementById("modal-ea-color").className = "modal-body text-center color-modal-success";
  }
  if (erroOuAcerto == 'erro') {
    document.getElementById("txtErroOuAcerto").innerHTML = quiz.customizacao.msgErro;
    document.getElementById("modal-ea-color").className = "modal-body text-center color-modal-danger";
  }
  $('#modal-erro-acerto').modal('show');
  setTimeout('esconderModalDeErroOuAcerto()', 1500);
}

function esconderModalDeErroOuAcerto() {
  $('#modal-erro-acerto').modal('hide');
}

function exibirModalDeFaseAtualConcluida() {
  var notaMinima = qtdQuestoesDaFaseAtual * (quiz.customizacao.taxaAcerto / 100);

  //Aprovado
  if (notaFase[indexFaseAtual] >= Math.round(notaMinima)) {

    //Exibir Modal do Resultado final
    if((indexFaseAtual+1) == qtdFasesDoQuiz){
      document.getElementById("color-modal").className = "modal-body color-modal-success";
      document.getElementById("reprovado").className = "invisible";
      document.getElementById("aprovado").className = "visible";
      document.getElementById("div-aprovado").className = "invisible";
      document.getElementById("btn-modal-avancar").className = "invisible";
      document.getElementById("btn-modal-exportar").className = "btn btn-primary width100 visible";
      document.getElementById("btn-proxima").className = "invisible";
      document.getElementById("f-success").innerHTML = "RESULTADO FINAL";
      for(var i = 0 ; i < qtdFasesDoQuiz ; i++){
        var qtdQuestoesDestaFase = fases[i].getQuestoes().length;
        tAcerto[i] = Math.round((notaFase[i] / qtdQuestoesDestaFase)*100);
        var tErro = Math.round(((qtdQuestoesDestaFase-notaFase[i]) / qtdQuestoesDestaFase)*100);
        document.getElementById("fase"+(i+1)).innerHTML = 
          "<h4 class='text-center'><b>Fase "+(i+1)+"</b></h4>"
          +"<h4>Resultado: "+notaFase[i]+" pontos</h4>"
          +"<h4>Taxa de acerto: "+tAcerto[i]+"%</h4>"
          +"<h4>Taxa de erro: "+tErro+"%</h4><br>";
      }
      document.getElementById("exportar").className = "visible";
    }
    //Exibir Modal de Aprovado na Fase
    else{
      document.getElementById("color-modal").className = "modal-body color-modal-success";
      document.getElementById("reprovado").className = "invisible";
      document.getElementById("aprovado").className = "visible";
      document.getElementById("f-success").innerHTML = "FASE " + (indexFaseAtual + 1);
      document.getElementById("resultado-success").innerHTML = "Resultado: " + notaFase[indexFaseAtual] +" pontos";
      document.getElementById("exportar").className = "invisible";
      document.getElementById("btn-modal-avancar").className = "btn btn-light width100 visible";

      //exibir taxas
      var taxaAcerto = Math.round((notaFase[indexFaseAtual] / qtdQuestoesDaFaseAtual)*100);
      var taxaErro = Math.round(((qtdQuestoesDaFaseAtual-notaFase[indexFaseAtual]) / qtdQuestoesDaFaseAtual)*100);
      document.getElementById("taxa-acerto").innerHTML = "<b>Taxa de acerto:</b> "+taxaAcerto+"%";
      document.getElementById("taxa-erro").innerHTML = "<b>Taxa de erro:</b> "+taxaErro+"%";
    }
  } 
  //Reprovado
  else {
    document.getElementById("color-modal").className = "modal-body color-modal-danger";
    document.getElementById("reprovado").className = "visible";
    document.getElementById("aprovado").className = "invisible";
    document.getElementById("btn-proxima").className = "invisible";
    document.getElementById("f-danger").innerHTML = "FASE " + (indexFaseAtual + 1);
    document.getElementById("resultado-danger").innerHTML = "Resultado: " + notaFase[indexFaseAtual] +" pontos";
    document.getElementById("texto-danger").innerHTML = "Infelizmente você não foi aprovado nesta " +
      "fase do Quiz. Seu resultado foi inferior a taxa de " +
      quiz.customizacao.taxaAcerto + "% das questões.";

    //exibir taxas
    var taxaAcerto = Math.round((notaFase[indexFaseAtual] / qtdQuestoesDaFaseAtual)*100);
    var taxaErro = Math.round(((qtdQuestoesDaFaseAtual-notaFase[indexFaseAtual]) / qtdQuestoesDaFaseAtual)*100);
    document.getElementById("err-taxa-acerto").innerHTML = "<b>Taxa de acerto:</b> "+taxaAcerto+"%";
    document.getElementById("err-taxa-erro").innerHTML = "<b>Taxa de erro:</b> "+taxaErro+"%";
  }
  $('#modal-fim-fase').modal('show');
}

function exibirModalDeFimDoTempo() {
  document.getElementById("txtErroOuAcerto").innerHTML = quiz.customizacao.msgTempoAcabou;
  document.getElementById("modal-ea-color").className = "modal-body text-center color-modal-danger";
  document.getElementById("btn-proxima").className = "invisible";
  $('#modal-erro-acerto').modal('show');
}

function exibirModalIntrucoes(){
  $('#texto-instrucoes').html("O Quiz sobre "+quiz.tituloQuiz+" possui questões de mútipla escolha, verdadeiro ou falso e pergunta direta. Cada questão valerá 1 ponto, as proposições das questões de verdadeiro ou falso valerão 0,25 pontos que somadas resultarão em 1 ponto. Para obter sua aprovação por fase será necessário alcançar "+quiz.customizacao.taxaAcerto+"% da pontuação total. Você terá duas chances para alcançar a aprovação no Quiz. Para auxiliar na resolução, algumas questões poderão exibir arquivos multimídias (imagem, áudio, vídeo ou link).");
  
  for(var i = 0 ; i < qtdFasesDoQuiz ; i++){
    var qtdQuestoes = fases[i].getQuestoes().length;
    var taxaF = quiz.customizacao.taxaAcerto;
    var minimoAcerto = (qtdQuestoes * taxaF)/100;
    $('#detalhamento'+(i+1)).html("<br><h4>Fase "+(i+1)+"</h4><span>Quantidade de questões: "+qtdQuestoes+"</span><br><span>Mínimo de acerto para aprovação: "+minimoAcerto+" pontos</span>");
  }
  
  if(quiz.customizacao.ativarTempo){
    $('#tempoQuestao').html("<p class='text-justify'>Você terá o tempo de "+quiz.customizacao.tempo+" segundos para responder cada questão que não possuir arquivo multimídia. Se o cronômetro chegar a zero você será reprovado na fase do Quiz.</p>");
  }

  $('#modal-instrucoes').modal('show');
}

function toDataURL(src, callback) {
  var image = new Image();
  image.crossOrigin = 'Anonymous';
  
  image.onload = function() {
      var canvas = document.createElement('canvas');
      var context = canvas.getContext('2d');
      canvas.height = this.naturalHeight;
      canvas.width = this.naturalWidth;
      context.drawImage(this, 0, 0);
      var dataURL = canvas.toDataURL('image/jpeg');
      callback(dataURL);
  };
  image.src = src;
}

function getDataAtual(){
  var dayName = new Array ("domingo", "segunda", "terça", "quarta", "quinta", "sexta", "sábado") 
  var monName = new Array ("janeiro", "fevereiro", "março", "abril", "Maio", "junho", "agosto", "outubro", "novembro", "dezembro")
  var data = new Date();
  return dayName[data.getDay()]+", "+data.getDate()+" de "+monName[data.getMonth()]+" de "+data.getFullYear()+".";
}

function getHoraAtual(){
  var data = new Date();
  return data.getHours()+":"+data.getMinutes()+":"+data.getSeconds();
}

function exportarResultadoFinal(){
  var nome = document.getElementById("nome");
  if(nome.value.length <= 10){
    nome.style.borderColor = 'red';
    document.getElementById('labelnome').style.color = 'red';

    getHoraAtual();
  }
  else {
    var texto;
    switch(qtdFasesDoQuiz){
      case 1:
        texto = 'Certifica-se que '+nome.value.toUpperCase()+' foi aprovado no Quiz sobre '+quiz.tituloQuiz+' com rendimento de '+tAcerto[0]+'% na Fase 1.';
        break;
      case 2:
        texto = 'Certifica-se que '+nome.value.toUpperCase()+' foi aprovado no Quiz sobre '+quiz.tituloQuiz+' com rendimento de '+tAcerto[0]+'% na Fase 1 e '+tAcerto[1]+'% na Fase 2.';
        break;
      case 3:
        texto = 'Certifica-se que '+nome.value.toUpperCase()+' foi aprovado no Quiz sobre '+quiz.tituloQuiz+' com rendimento de '+tAcerto[0]+'% na Fase 1, '+tAcerto[1]+'% na Fase 2 e '+tAcerto[2]+'% na Fase 3.';
        break;
    }

    toDataURL('recursos/multimidia/imagem/aprovado.jpg', function(dataURL) {
      var doc = new jsPDF('l', 'mm', 'a5');

      //linhas
      doc.setLineWidth(4);
      doc.setDrawColor(49, 128, 159);
      doc.line(10, 10, 200, 10);
      doc.line(10, 138, 200, 138);
      //textos
      doc.setFontSize(16);
      doc.setFontStyle('normal');
      var lMargin=20; //left margin in mm
      var rMargin=20; //right margin in mm
      var pdfInMM=210;  // width of A5 in mm
      var lines = doc.splitTextToSize(texto, (pdfInMM-lMargin-rMargin));
      doc.text(20,80,lines);
      doc.setFontSize(12);
      doc.text(20,110, getDataAtual());
      doc.text(20, 116, getHoraAtual());
      //imagem
      doc.addImage (dataURL, 'JPEG', 75, 20, 56, 42); 
      doc.save("Quiz-Resultado-Final.pdf");
    });
    
  }
}

function atualizarBarraDeProgresso() {
  porcentagemTotal += Math.round((1 / qtdQuestoesDaFaseAtual) * 100);
  if (porcentagemTotal > 94 && porcentagemTotal < 105) {
    porcentagemTotal = 100;
  }
  document.getElementById("progressbar").innerHTML = porcentagemTotal + "%";
  document.getElementById("progressbar").style.width = porcentagemTotal + "%";
}

function avancarDeFase() {
  iniciarQuiz();
}

function cronometrarQuestao() {
  if ((tempoCronometro - 1) >= 0) {
    $('#contador').html(tempoCronometro);

    timeout = setTimeout('cronometrarQuestao()', 1000);
    tempoCronometro--;
  } else {
    exibirModalDeFimDoTempo();
  }
}

function iniciarQuiz() {
  indexFaseAtual++;
  indexQuestaoAtual = -1;
  porcentagemTotal = 0;
  notaFase[indexFaseAtual] = 0;
  qtdFasesDoQuiz = quiz.fases.length;
  cronometroAtivo = quiz.customizacao.ativarTempo;

  if (indexFaseAtual == 0) {
    gerarObjFases();
    document.getElementById("tituloQuiz").innerHTML = quiz.tituloQuiz;
  }
  qtdQuestoesDaFaseAtual = fases[indexFaseAtual].getQuestoes().length;

  if(indexFaseAtual == 0){
    exibirModalIntrucoes();
  }
  else{
    inserirNoHtmlProximaQuestao();
  }
}

function contagemDasTentativasDeResolucaoDoQuiz(){
  var cookies = document.cookie;
  var begin = cookies.charAt(10);

  if(cookies.length == 0){
    console.log("tentativa 1");
    document.cookie = "tentativa=1";
    iniciarQuiz();
  }
  else if(begin == 1){
    console.log("tentativa=2");
    document.cookie = "tentativa=2";
    iniciarQuiz();
  }
  else if(begin == 2){
    console.log("game over");
    $('#game-over').modal('show');
  }
}

