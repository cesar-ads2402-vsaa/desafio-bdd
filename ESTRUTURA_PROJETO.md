# Estrutura do Projeto BDD - AniMatch

## 📁 Organização dos Arquivos

```
desafio-bdd/
├── historia-usuario.feature          # Arquivo Gherkin com histórias e cenários
├── README.md                         # Documentação principal com respostas às perguntas
├── exemplo-implementacao-teste.md    # Guia detalhado de implementação
├── AnimeServiceBuscaTest.java        # Exemplo de teste unitário
└── ESTRUTURA_PROJETO.md              # Este arquivo
```

## 📝 Descrição dos Arquivos

### 1. `historia-usuario.feature`
Arquivo principal em formato Gherkin contendo:
- **Funcionalidade**: Descrição da história de usuário
- **Contexto**: Pré-condições comuns a todos os cenários
- **12 Cenários**: Casos de teste detalhados cobrindo diferentes aspectos da busca

**Formato**: Gherkin (BDD)
**Idioma**: Português (`# language: pt`)

### 2. `README.md`
Documentação principal respondendo as 3 perguntas:
1. Como ficou sua história?
2. Como ficaram seus cenários?
3. Como implementar o teste para pelo menos um dos cenários?

**Conteúdo**:
- Descrição detalhada da história de usuário
- Explicação dos 12 cenários criados
- Exemplos de implementação de testes (Cucumber, Cypress, JUnit)

### 3. `exemplo-implementacao-teste.md`
Guia completo de implementação incluindo:
- Configuração de dependências (Maven)
- Estrutura de pastas recomendada
- Código completo dos Step Definitions
- Configuração do Test Runner
- Instruções de execução
- Validações implementadas

### 4. `AnimeServiceBuscaTest.java`
Exemplo prático de teste unitário que pode ser executado imediatamente:
- Teste para o Cenário 1 (busca por nome parcial)
- Teste para o Cenário 9 (case-insensitive)
- Teste para o Cenário 10 (busca por parte do nome)
- Teste para o Cenário 11 (sem filtros)

**Uso**: Copiar para `src/test/java/com/example/animematch/bdd/` e executar

## 🎯 Histórias e Cenários Criados

### História Principal
**Funcionalidade**: Busca e Filtro de Animes

**Como**: um usuário do AniMatch  
**Eu quero**: buscar e filtrar animes por diferentes critérios  
**Para**: encontrar rapidamente os animes que me interessam

### Cenários Implementados

1. ✅ **Buscar anime por nome parcial** - Busca básica por palavra-chave
2. ✅ **Filtrar animes por gênero específico** - Filtro isolado por gênero
3. ✅ **Filtrar animes por classificação indicativa** - Filtro por faixa etária
4. ✅ **Filtrar animes por status de exibição** - Filtro por status
5. ✅ **Buscar anime combinando múltiplos filtros** - Combinação de filtros
6. ✅ **Buscar por nome e aplicar filtros simultaneamente** - Busca + filtros
7. ✅ **Limpar todos os filtros aplicados** - Reset de filtros
8. ✅ **Exibir mensagem quando nenhum anime é encontrado** - Tratamento de erro
9. ✅ **Busca não deve diferenciar maiúsculas e minúsculas** - Case-insensitive
10. ✅ **Buscar anime usando parte do nome** - Correspondência parcial
11. ✅ **Quando nenhum filtro é selecionado, mostrar todos os animes** - Comportamento padrão
12. ✅ **Sistema deve ocultar animes com classificação proibida** - Filtro automático

## 🛠️ Tecnologias Utilizadas

- **Gherkin**: Linguagem de especificação BDD
- **Cucumber**: Framework para executar testes BDD em Java
- **JUnit 5**: Framework de testes para Java
- **Spring Boot Test**: Suporte a testes de integração
- **Maven**: Gerenciador de dependências

## 🚀 Como Usar

### Opção 1: Implementação Completa com Cucumber

1. Adicionar dependências do Cucumber ao `pom.xml`
2. Criar estrutura de pastas conforme `exemplo-implementacao-teste.md`
3. Copiar `historia-usuario.feature` para `src/test/resources/features/`
4. Implementar Step Definitions conforme exemplo
5. Executar testes via Maven ou IDE

### Opção 2: Testes Unitários Simples

1. Copiar `AnimeServiceBuscaTest.java` para `src/test/java/com/example/animematch/bdd/`
2. Executar como teste JUnit normal
3. Adaptar conforme necessário

### Opção 3: Testes End-to-End

1. Usar Cypress ou Selenium para testes no frontend
2. Seguir exemplos em `README.md` seção "Opção 2"

## 📊 Cobertura de Testes

Os cenários cobrem:
- ✅ Busca individual por palavra-chave
- ✅ Filtros individuais (gênero, classificação, status)
- ✅ Combinação de filtros
- ✅ Casos especiais (case-insensitive, correspondência parcial)
- ✅ Tratamento de erros e casos extremos
- ✅ Comportamento do sistema (limpar filtros, sem resultados)

## 🔄 Próximos Passos

1. **Configurar ambiente de teste**: Criar `application-test.properties`
2. **Criar fixtures**: Dados de teste conhecidos para garantir consistência
3. **Implementar Step Definitions**: Para todos os 12 cenários
4. **Integrar CI/CD**: Adicionar testes ao pipeline
5. **Gerar relatórios**: Configurar relatórios HTML/JSON do Cucumber
6. **Adicionar mais cenários**: Baseado em feedback dos usuários

## 📚 Referências

- [Cucumber Documentation](https://cucumber.io/docs/cucumber/)
- [Gherkin Syntax](https://cucumber.io/docs/gherkin/reference/)
- [Spring Boot Testing](https://spring.io/guides/gs/testing-web/)
- [JUnit 5 User Guide](https://junit.org/junit5/docs/current/user-guide/)

