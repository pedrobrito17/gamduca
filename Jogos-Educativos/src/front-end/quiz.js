

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
  if (cronometroAtivo) {
    tempoCronometro = quiz.customizacao.tempo;
    cronometrarQuestao();
  }

  if (indexQuestaoAtual == qtdQuestoesDaFaseAtual) {
    exibirModalDeFaseAtualConcluida();
    return;
  }
  
  atualizarBarraDeProgresso();
  limparRespostas();

  var questao = fases[indexFaseAtual].getQuestoes()[indexQuestaoAtual];
  document.getElementById("tituloQuestao").innerHTML = questao.getTituloQuestao();
  document.getElementById("txtpergunta").innerHTML = questao.getPergunta().txtPergunta;

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

  indexQuestaoAtual++;
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
  setTimeout('esconderModalDeErroOuAcerto()',1500);
}

function esconderModalDeErroOuAcerto(){
  $('#modal-erro-acerto').modal('hide');
}

function exibirModalDeFaseAtualConcluida() {
  var notaMinima = qtdQuestoesDaFaseAtual * (quiz.customizacao.taxaAcerto / 100);

  if (notaFase[indexFaseAtual] >= Math.round(notaMinima)) {
    document.getElementById("color-modal").className = "modal-body color-modal-success";
    document.getElementById("reprovado").className = "invisible";
    document.getElementById("aprovado").className = "visible";
    document.getElementById("f-success").innerHTML = "FASE " + (indexFaseAtual + 1);
    document.getElementById("resultado-success").innerHTML = "Resultado: " + notaFase[indexFaseAtual];
    if (qtdFasesDoQuiz == (indexFaseAtual + 1)) {
      document.getElementById("btn-modal-fechar").className = "btn btn-light width100 visible";
      document.getElementById("btn-modal-avancar").className = "btn btn-light width100 invisible";
    } else {
      document.getElementById("btn-modal-fechar").className = "btn btn-light width100 invisible";
      document.getElementById("btn-modal-avancar").className = "btn btn-light width100 visible";
    }
  } else {
    document.getElementById("color-modal").className = "modal-body color-modal-danger";
    document.getElementById("reprovado").className = "visible";
    document.getElementById("aprovado").className = "invisible";
    document.getElementById("f-danger").innerHTML = "FASE " + (indexFaseAtual + 1);
    document.getElementById("resultado-danger").innerHTML = "Resultado: " + notaFase[indexFaseAtual];
    document.getElementById("texto-danger").innerHTML = "Infelizmente você não foi aprovado nesta " +
      "fase do Quiz. Seu resultado foi inferior a taxa de " +
      quiz.customizacao.taxaAcerto + "% das questões.";
  }
  $('#modal-fim-fase').modal('show');
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
    setTimeout('cronometrarQuestao()', 1000);
    tempoCronometro--;
  } else {
    window.alert("O tempo acabou");
  }
}

function iniciarQuiz() {
  indexFaseAtual++;
  indexQuestaoAtual = 0;
  porcentagemTotal = 0;
  notaFase[indexFaseAtual] = 0;
  qtdFasesDoQuiz = quiz.fases.length;
  cronometroAtivo = quiz.customizacao.ativarTempo;
  if (cronometroAtivo) {
    document.getElementById("cronometro").className = "text-center alert alert-warning tempo";
  } else {
    document.getElementById("cronometro").parentElement.className = "invisible";
    document.getElementById("col-progress").className = "col-md-12";
  }

  if (indexFaseAtual == 0) {
    gerarObjFases();
    document.getElementById("tituloQuiz").innerHTML = quiz.tituloQuiz;
  }
  qtdQuestoesDaFaseAtual = fases[indexFaseAtual].getQuestoes().length;
  inserirNoHtmlProximaQuestao();
}
