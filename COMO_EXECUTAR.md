# 🚀 Como Executar o Projeto BDD Independente

## Pré-requisitos

- **Java 17** ou superior
- **Maven 3.6** ou superior

## Verificação Rápida

```bash
java -version    # Deve mostrar Java 17 ou superior
mvn -version     # Deve mostrar Maven 3.6 ou superior
```

## Executando os Testes

### 1. Navegue até a pasta do projeto

```bash
cd desafio-bdd
```

### 2. Compile o projeto

```bash
mvn clean compile
```

### 3. Execute todos os testes BDD

```bash
mvn test
```

Este comando irá:
- Compilar o código
- Executar os testes BDD com Cucumber
- Executar os testes unitários
- Gerar relatórios em `target/`

### 4. Execute apenas os testes BDD (Cucumber)

```bash
mvn test -Dtest=CucumberTestRunner
```

### 5. Execute apenas os testes unitários

```bash
mvn test -Dtest=AnimeServiceBuscaTest
```

## Visualizando Relatórios

Após executar `mvn test`, os relatórios estarão disponíveis em:

- **HTML**: `target/cucumber-report.html` - Abra no navegador
- **JSON**: `target/cucumber-report.json` - Para integração com outras ferramentas
- **JUnit XML**: `target/cucumber-report.xml` - Para CI/CD

### Abrir relatório HTML

**Windows:**
```bash
start target/cucumber-report.html
```

**Linux/Mac:**
```bash
open target/cucumber-report.html
# ou
xdg-open target/cucumber-report.html
```

## Estrutura de Saída Esperada

Ao executar `mvn test`, você deve ver algo como:

```
-------------------------------------------------------
 T E S T S
-------------------------------------------------------
Running com.animatch.bdd.runner.CucumberTestRunner
Tests run: 12, Failures: 0, Errors: 0, Skipped: 0, Time elapsed: X.XXX s

Results :

Tests run: 12, Failures: 0, Errors: 0, Skipped: 0

[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
```

## Executando Cenários Específicos

Para executar apenas cenários com uma tag específica, você pode:

1. Adicionar tags no arquivo `.feature`:
```gherkin
@busca-por-nome
Cenário: Buscar anime por nome parcial
  ...
```

2. Executar apenas esses cenários:
```bash
mvn test -Dcucumber.filter.tags="@busca-por-nome"
```

## Troubleshooting

### Erro: "Java version not supported"

Certifique-se de ter Java 17 ou superior instalado:
```bash
java -version
```

### Erro: "Maven not found"

Instale o Maven ou use o wrapper:
```bash
# Se tiver Maven instalado
mvn --version

# Ou baixe o Maven wrapper (se disponível)
```

### Erro de compilação

Limpe e recompile:
```bash
mvn clean compile test
```

### Testes falhando

Verifique se:
1. O arquivo `historia-usuario.feature` está em `src/test/resources/features/`
2. Os Step Definitions estão em `src/test/java/com/animatch/bdd/steps/`
3. O package está correto em todas as classes

## Executando em IDEs

### IntelliJ IDEA

1. Abra o projeto como projeto Maven
2. Aguarde o Maven baixar as dependências
3. Clique com botão direito em `CucumberTestRunner.java` → Run
4. Ou execute `mvn test` no terminal integrado

### Eclipse

1. Importe como projeto Maven existente
2. Aguarde o Maven baixar as dependências
3. Clique com botão direito no projeto → Run As → Maven Test
4. Ou execute `mvn test` no terminal

### VS Code

1. Instale a extensão "Extension Pack for Java"
2. Abra a pasta `desafio-bdd`
3. Execute `mvn test` no terminal integrado

## Próximos Passos

Após executar os testes com sucesso:

1. ✅ Verifique os relatórios HTML para ver detalhes dos testes
2. ✅ Explore os Step Definitions para entender a implementação
3. ✅ Modifique os cenários ou adicione novos conforme necessário
4. ✅ Integre com CI/CD se desejado

## Suporte

Se encontrar problemas, verifique:
- Versões do Java e Maven
- Estrutura de diretórios
- Conteúdo dos arquivos `.feature` e Step Definitions
- Logs de erro completos

