const { Given, When, Then } = require("@cucumber/cucumber");
const assert = require("assert");

let habitos = {};
let mensagem = "";

Given('que o usuário possui hábitos cadastrados', function () {
  habitos = {
    "Beber 2L de água": { concluidoHoje: false }
  };
});

Given('hoje é um dia ainda não registrado para o hábito {string}', function (habito) {
  habitos[habito].concluidoHoje = false;
});

When('o usuário marca o hábito {string} como concluído', function (habito) {
  if (!habitos[habito].concluidoHoje) {
    habitos[habito].concluidoHoje = true;
    mensagem = "Hábito registrado com sucesso";
  } else {
    mensagem = "Este hábito já foi registrado hoje";
  }
});

Then('o sistema deve registrar a conclusão com sucesso', function () {
  assert.strictEqual(mensagem, "Hábito registrado com sucesso");
});

Then('exibir a mensagem {string}', function (msgEsperada) {
  assert.strictEqual(mensagem, msgEsperada);
});

Then('atualizar o calendário de contribuições', function () {
  assert.strictEqual(habitos["Beber 2L de água"].concluidoHoje, true);
});

Given('que o usuário já registrou hoje o hábito {string}', function (habito) {
  habitos[habito] = { concluidoHoje: true };
});

When('o usuário tenta marcar novamente o hábito {string}', function (habito) {
  if (habitos[habito].concluidoHoje) {
    mensagem = "Este hábito já foi registrado hoje";
  } else {
    habitos[habito].concluidoHoje = true;
    mensagem = "Hábito registrado com sucesso";
  }
});

Then('o sistema deve impedir o registro duplicado', function () {
  assert.strictEqual(mensagem, "Este hábito já foi registrado hoje");
});

Given('que o usuário registrou o hábito {string} hoje', function (habito) {
  habitos[habito] = { concluidoHoje: true };
});

When('o usuário abre a tela de calendário', function () {
  mensagem = "Tela carregada";
});

Then('o dia atual deve aparecer como "concluído" para esse hábito', function () {
  const habito = "Beber 2L de água";
  assert.strictEqual(habitos[habito].concluidoHoje, true);
});
