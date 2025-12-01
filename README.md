# Projeto BDD – Registro de Hábitos (Estilo GitHub Contributions)

Este projeto demonstra o uso de Behavior Driven Development (BDD) com Cucumber para testar a funcionalidade de registro diário de hábitos em um aplicativo de acompanhamento de consistência — semelhante ao calendário de contribuições do GitHub.

O objetivo é exemplificar:

* Criação de História de Usuário
* Escrita de Cenários em Gherkin
* Implementação de Step Definitions
* Execução de testes automatizados usando Cucumber + JavaScript

---

## 1. História de Usuário

Como um usuário que deseja acompanhar sua consistência diária,
eu quero registrar meus hábitos concluídos no dia,
para que eu possa visualizar um calendário de progresso semelhante às GitHub contributions.

### Critérios de Aceite

* Permitir registrar hábito concluído no dia atual
* Impedir registro duplicado
* Atualizar o calendário de progresso
* Exibir mensagens de confirmação

---

## 2. Cenários BDD (Gherkin)

### Cenário 1 – Registrar hábito concluído

```
Cenário: Registrar um hábito concluído no dia atual
  Dado que o usuário possui hábitos cadastrados
  E hoje é um dia ainda não registrado para o hábito "Beber 2L de água"
  Quando o usuário marca o hábito "Beber 2L de água" como concluído
  Então o sistema deve registrar a conclusão com sucesso
  E exibir a mensagem "Hábito registrado com sucesso"
  E atualizar o calendário de contribuições
```

### Cenário 2 – Impedir registro duplicado

```
Cenário: Tentativa de registrar o mesmo hábito duas vezes no mesmo dia
  Dado que o usuário já registrou hoje o hábito "Beber 2L de água"
  Quando o usuário tenta marcar novamente o hábito "Beber 2L de água"
  Então o sistema deve impedir o registro duplicado
  E exibir a mensagem "Este hábito já foi registrado hoje"
```

### Cenário 3 – Visualizar calendário

```
Cenário: Visualizar calendário após registro
  Dado que o usuário registrou o hábito "Beber 2L de água" hoje
  Quando o usuário abre a tela de calendário
  Então o dia atual deve aparecer como "concluído" para esse hábito
```

---

## 3. Tecnologias Utilizadas

* Node.js
* Cucumber.js
* Jest
* Gherkin

---

## 4. Estrutura do Projeto

```
/desafio-bdd/
  package.json
  cucumber.js
  README.md
  .gitignore
  /features/
      registro_habito.feature
  /step_definitions/
      registro_habito.steps.js
```

---

## 5. Instalação e Execução

### 1. Clonar o repositório

```
git clone https://github.com/cesar-ads2402-vsaa/desafio-bdd
cd desafio-bdd
```

### 2. Instalar dependências

```
npm install
```

### 3. Rodar os testes

```
npm test
```

---

## 6. Exemplo de Step Definitions

```javascript
const { Given, When, Then } = require("@cucumber/cucumber");
const { expect } = require("@jest/globals");

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
  expect(mensagem).toBe("Hábito registrado com sucesso");
});

Then('exibir a mensagem {string}', function (msgEsperada) {
  expect(mensagem).toBe(msgEsperada);
});

Then('atualizar o calendário de contribuições', function () {
  expect(habitos["Beber 2L de água"].concluidoHoje).toBe(true);
});
```