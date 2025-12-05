# Desafio BDD - Sistema de Filtros de Busca AniMatch

## 🎯 Sobre Este Projeto

Este é um **projeto BDD completamente independente** que demonstra como testar a funcionalidade de busca e filtros de animes usando técnicas de Behavior-Driven Development (BDD). 

**Importante**: Este projeto não depende do código do AniMatch principal e pode ser executado isoladamente.

## 📋 Respostas às Perguntas

### 1. Como ficou sua história? (Descreva)

A história de usuário criada descreve a funcionalidade de **Busca e Filtro de Animes** no sistema AniMatch. A história foi escrita seguindo o formato padrão de User Story:

**"Como um usuário do AniMatch, eu quero buscar e filtrar animes por diferentes critérios, para encontrar rapidamente os animes que me interessam."**

Esta história foi escolhida porque:
- É uma funcionalidade central do sistema AniMatch
- Tem múltiplos critérios de busca que podem ser testados de forma isolada e combinada
- É uma funcionalidade que os usuários utilizam frequentemente
- Permite demonstrar diferentes técnicas de teste BDD

A história contempla os seguintes aspectos:
- **Busca por palavra-chave**: Permite encontrar animes digitando parte do nome
- **Filtros individuais**: Por gênero, classificação indicativa e status
- **Combinação de filtros**: Possibilidade de usar múltiplos filtros simultaneamente
- **Limpeza de filtros**: Funcionalidade para resetar os filtros aplicados
- **Tratamento de casos especiais**: Mensagens quando não há resultados, busca case-insensitive, etc.

### 2. Como ficaram seus cenários? (Descreva)

Foram criados **12 cenários de teste** em Gherkin que cobrem diferentes aspectos da funcionalidade:

#### **Cenários de Busca Individual:**
1. **Buscar anime por nome parcial** - Testa busca por palavra-chave com correspondência parcial
2. **Busca case-insensitive** - Verifica que a busca não diferencia maiúsculas/minúsculas
3. **Busca por correspondência parcial** - Testa busca usando apenas parte do nome

#### **Cenários de Filtros Individuais:**
4. **Filtrar por gênero** - Testa filtro isolado por gênero
5. **Filtrar por classificação** - Testa filtro isolado por classificação indicativa
6. **Filtrar por status** - Testa filtro isolado por status de exibição

#### **Cenários de Combinação:**
7. **Combinar múltiplos filtros** - Testa aplicação simultânea de vários filtros
8. **Busca combinada com filtros** - Testa busca por nome + filtros simultaneamente

#### **Cenários de Comportamento do Sistema:**
9. **Limpar filtros** - Testa funcionalidade de reset dos filtros
10. **Nenhum resultado encontrado** - Testa tratamento quando não há resultados
11. **Filtro vazio** - Testa comportamento quando nenhum filtro está ativo
12. **Filtro de animes inadequados** - Testa filtro automático de conteúdo proibido

Cada cenário segue a estrutura **Given-When-Then** do BDD:
- **Given (Dado)**: Define o estado inicial e pré-condições
- **When (Quando)**: Descreve a ação do usuário
- **Then (Então)**: Especifica o resultado esperado

Os cenários foram escritos em **português** para facilitar a comunicação com stakeholders brasileiros, seguindo a diretiva `# language: pt` no arquivo `.feature`.

### 3. Como implementar o teste para pelo menos um dos cenários?

Este projeto implementa testes BDD usando **Cucumber** para Java. A implementação é completamente independente e inclui:

- **Modelo simplificado** (`Anime.java`) - Classe de modelo para representar animes
- **Serviço mockado** (`AnimeService.java`) - Simula o comportamento do sistema sem depender do projeto principal
- **Step Definitions** (`AnimeSearchStepDefinitions.java`) - Implementação dos passos dos cenários Gherkin
- **Test Runner** (`CucumberTestRunner.java`) - Configuração do Cucumber para executar os testes

## 🚀 Como Executar Este Projeto

### Pré-requisitos

- Java 17 ou superior
- Maven 3.6 ou superior

### Executando os Testes

1. **Navegue até a pasta do projeto:**
   ```bash
   cd desafio-bdd
   ```

2. **Compile o projeto:**
   ```bash
   mvn clean compile
   ```

3. **Execute os testes BDD com Cucumber:**
   ```bash
   mvn test
   ```

4. **Ou execute apenas os testes unitários:**
   ```bash
   mvn test -Dtest=AnimeServiceBuscaTest
   ```

5. **Execute o Cucumber Test Runner diretamente:**
   ```bash
   mvn test -Dtest=CucumberTestRunner
   ```

### Visualizando Relatórios

Após a execução, os relatórios estarão disponíveis em:
- **HTML**: `target/cucumber-report.html`
- **JSON**: `target/cucumber-report.json`
- **JUnit XML**: `target/cucumber-report.xml`

Abra o arquivo HTML no navegador para ver os resultados detalhados.

## 📁 Estrutura do Projeto

```
desafio-bdd/
├── pom.xml                                    # Configuração Maven
├── README.md                                  # Este arquivo
├── ESTRUTURA_PROJETO.md                       # Documentação da estrutura
├── exemplo-implementacao-teste.md              # Guia detalhado de implementação
│
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── animatch/
│   │               └── bdd/
│   │                   ├── model/
│   │                   │   └── Anime.java              # Modelo de Anime
│   │                   └── service/
│   │                       └── AnimeService.java        # Serviço mockado
│   │
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── animatch/
│       │           └── bdd/
│       │               ├── steps/
│       │               │   └── AnimeSearchStepDefinitions.java  # Step Definitions
│       │               ├── runner/
│       │               │   └── CucumberTestRunner.java           # Test Runner
│       │               └── AnimeServiceBuscaTest.java           # Testes unitários
│       │
│       └── resources/
│           └── features/
│               └── historia-usuario.feature             # Arquivo Gherkin
```

## 🧪 Exemplo de Execução

Ao executar `mvn test`, você verá uma saída similar a:

```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.animatch.bdd.runner.CucumberTestRunner
...
12 Scenarios (12 passed)
48 Steps (48 passed)
0m0.123s
```

## 🔍 Detalhes da Implementação

### Serviço Mockado

O `AnimeService` inclui uma base de dados mockada com 10 animes de exemplo, incluindo:
- One Punch Man
- Attack on Titan
- My Hero Academia
- Demon Slayer
- Spy x Family
- E outros...

### Step Definitions

Os Step Definitions implementam todos os passos dos 12 cenários, incluindo:
- Busca por palavra-chave
- Filtros por gênero, classificação e status
- Combinação de filtros
- Validações de resultados

### Testes Unitários

O arquivo `AnimeServiceBuscaTest.java` contém exemplos de testes unitários que podem ser executados independentemente dos testes BDD.

## 🛠️ Tecnologias Utilizadas

- **Java 17**: Linguagem de programação
- **Maven**: Gerenciador de dependências e build
- **Cucumber**: Framework BDD
- **JUnit 5**: Framework de testes
- **AssertJ**: Biblioteca de asserções (opcional)

## 📝 Próximos Passos

1. ✅ Projeto criado e funcionando independentemente
2. ✅ Todos os 12 cenários implementados
3. ✅ Testes unitários criados
4. 🔄 Adicionar mais cenários conforme necessário
5. 🔄 Integrar com CI/CD se desejado

## 📚 Referências

- [Cucumber Documentation](https://cucumber.io/docs/cucumber/)
- [Gherkin Syntax](https://cucumber.io/docs/gherkin/reference/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)
- [Maven Documentation](https://maven.apache.org/guides/)

## 📄 Licença

Este projeto é um exemplo educacional para demonstração de técnicas BDD.
