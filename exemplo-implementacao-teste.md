# Exemplo de Implementação de Teste BDD

Este documento detalha como implementar o teste para o **Cenário 1: Buscar anime por nome parcial**.

## 📋 Cenário a Ser Testado

```gherkin
Cenário: Buscar anime por nome parcial
  Dado que estou na página de busca de animes
  Quando eu digito "One" no campo de busca por nome
  Então o sistema deve retornar animes que contenham "One" no título
  E o anime "One Punch Man" deve estar nos resultados
  E a busca não deve diferenciar maiúsculas e minúsculas
```

## 🔧 Configuração Inicial

### 1. Dependências Maven (pom.xml)

```xml
<dependencies>
    <!-- Cucumber para BDD -->
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-java</artifactId>
        <version>7.14.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-junit-platform-engine</artifactId>
        <version>7.14.0</version>
        <scope>test</scope>
    </dependency>
    <dependency>
        <groupId>io.cucumber</groupId>
        <artifactId>cucumber-spring</artifactId>
        <version>7.14.0</version>
        <scope>test</scope>
    </dependency>
    
    <!-- JUnit 5 -->
    <dependency>
        <groupId>org.junit.platform</groupId>
        <artifactId>junit-platform-suite</artifactId>
        <version>1.10.0</version>
        <scope>test</scope>
    </dependency>
    
    <!-- Spring Boot Test -->
    <dependency>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-test</artifactId>
        <scope>test</scope>
    </dependency>
</dependencies>
```

### 2. Estrutura de Pastas

```
src/test/java/com/example/animematch/
├── bdd/
│   ├── steps/
│   │   └── AnimeSearchStepDefinitions.java
│   └── runner/
│       └── CucumberTestRunner.java
└── resources/
    └── features/
        └── historia-usuario.feature
```

## 💻 Implementação Completa

### Arquivo 1: Step Definitions

```java
package com.example.animematch.bdd.steps;

import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.E;
import com.example.animematch.model.Anime;
import com.example.animematch.service.AnimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
public class AnimeSearchStepDefinitions {

    @Autowired
    private AnimeService animeService;
    
    private String palavraChave;
    private List<Anime> resultados;
    private Exception excecaoCapturada;

    @Dado("que estou na página de busca de animes")
    public void que_estou_na_pagina_de_busca_de_animes() {
        // Verifica se o serviço está disponível
        assertNotNull(animeService, "AnimeService deve estar disponível");
        // Limpa estado anterior
        this.resultados = null;
        this.palavraChave = null;
        this.excecaoCapturada = null;
    }

    @Quando("eu digito {string} no campo de busca por nome")
    public void eu_digito_no_campo_de_busca_por_nome(String palavra) {
        try {
            this.palavraChave = palavra;
            this.resultados = animeService.buscarAnimesComFiltros(
                null,  // genero
                null,  // classificacao
                null,  // status
                palavraChave  // palavraChave
            );
        } catch (Exception e) {
            this.excecaoCapturada = e;
        }
    }

    @Então("o sistema deve retornar animes que contenham {string} no título")
    public void o_sistema_deve_retornar_animes_que_contenham_no_titulo(String palavra) {
        // Verifica se não houve exceção
        assertNull(excecaoCapturada, 
            "Não deve ocorrer exceção durante a busca: " + 
            (excecaoCapturada != null ? excecaoCapturada.getMessage() : ""));
        
        // Verifica se a lista não é nula
        assertNotNull(resultados, "A lista de resultados não deve ser nula");
        
        // Verifica se há resultados
        assertFalse(resultados.isEmpty(), 
            "Deve retornar pelo menos um resultado para a busca: " + palavra);
        
        // Verifica se todos os resultados contêm a palavra-chave no título
        resultados.forEach(anime -> {
            assertNotNull(anime.getTituloPrincipal(), 
                "O título do anime não deve ser nulo");
            
            String titulo = anime.getTituloPrincipal().toLowerCase();
            String palavraLower = palavra.toLowerCase();
            
            assertTrue(titulo.contains(palavraLower), 
                String.format(
                    "O anime '%s' deve conter '%s' no título",
                    anime.getTituloPrincipal(),
                    palavra
                )
            );
        });
    }

    @E("o anime {string} deve estar nos resultados")
    public void o_anime_deve_estar_nos_resultados(String tituloEsperado) {
        assertNotNull(resultados, "Resultados não devem ser nulos");
        
        boolean encontrado = resultados.stream()
            .anyMatch(anime -> 
                anime.getTituloPrincipal() != null &&
                anime.getTituloPrincipal().equalsIgnoreCase(tituloEsperado)
            );
        
        assertTrue(encontrado, 
            String.format(
                "O anime '%s' deve estar nos resultados. Animes encontrados: %s",
                tituloEsperado,
                resultados.stream()
                    .map(Anime::getTituloPrincipal)
                    .reduce((a, b) -> a + ", " + b)
                    .orElse("nenhum")
            )
        );
    }

    @E("a busca não deve diferenciar maiúsculas e minúsculas")
    public void a_busca_nao_deve_diferenciar_maiusculas_e_minusculas() {
        assertNotNull(palavraChave, "Palavra-chave deve estar definida");
        
        // Testa com maiúsculas
        List<Anime> resultadosMaiusculo = animeService.buscarAnimesComFiltros(
            null, null, null, palavraChave.toUpperCase()
        );
        
        // Testa com minúsculas
        List<Anime> resultadosMinusculo = animeService.buscarAnimesComFiltros(
            null, null, null, palavraChave.toLowerCase()
        );
        
        // Testa com misto
        List<Anime> resultadosMisto = animeService.buscarAnimesComFiltros(
            null, null, null, "OnE"
        );
        
        // Verifica que todos retornam a mesma quantidade de resultados
        assertEquals(resultados.size(), resultadosMaiusculo.size(),
            "Resultados devem ser iguais independente de maiúsculas/minúsculas (MAIÚSCULO)");
        
        assertEquals(resultados.size(), resultadosMinusculo.size(),
            "Resultados devem ser iguais independente de maiúsculas/minúsculas (MINÚSCULO)");
        
        assertEquals(resultados.size(), resultadosMisto.size(),
            "Resultados devem ser iguais independente de maiúsculas/minúsculas (MISTO)");
        
        // Verifica que os mesmos animes aparecem em todos os casos
        List<String> titulosOriginais = resultados.stream()
            .map(a -> a.getTituloPrincipal().toLowerCase())
            .sorted()
            .toList();
        
        List<String> titulosMaiusculo = resultadosMaiusculo.stream()
            .map(a -> a.getTituloPrincipal().toLowerCase())
            .sorted()
            .toList();
        
        assertEquals(titulosOriginais, titulosMaiusculo,
            "Os mesmos animes devem aparecer independente de maiúsculas/minúsculas");
    }
}
```

### Arquivo 2: Test Runner

```java
package com.example.animematch.bdd.runner;

import io.cucumber.junit.platform.engine.Constants;
import org.junit.platform.suite.api.ConfigurationParameter;
import org.junit.platform.suite.api.IncludeEngines;
import org.junit.platform.suite.api.SelectClasspathResource;
import org.junit.platform.suite.api.Suite;

@Suite
@IncludeEngines("cucumber")
@SelectClasspathResource("features")
@ConfigurationParameter(key = Constants.PLUGIN_PROPERTY_NAME, 
    value = "pretty, html:target/cucumber-report.html, json:target/cucumber-report.json")
@ConfigurationParameter(key = Constants.GLUE_PROPERTY_NAME, 
    value = "com.example.animematch.bdd.steps")
@ConfigurationParameter(key = Constants.FEATURES_PROPERTY_NAME, 
    value = "src/test/resources/features")
public class CucumberTestRunner {
}
```

### Arquivo 3: Configuração de Teste (application-test.properties)

```properties
# Configuração para ambiente de teste
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.h2.console.enabled=true

# Desabilita cache de temporada para testes
animematch.cache.enabled=false
```

## 🧪 Executando os Testes

### Via Maven

```bash
mvn test
```

### Via IDE

Execute a classe `CucumberTestRunner` como JUnit Test.

### Executar Cenário Específico

Adicione a tag `@busca-por-nome` no cenário:

```gherkin
@busca-por-nome
Cenário: Buscar anime por nome parcial
  ...
```

E execute:

```bash
mvn test -Dcucumber.filter.tags="@busca-por-nome"
```

## 📊 Relatórios

Após a execução, os relatórios estarão disponíveis em:
- **HTML**: `target/cucumber-report.html`
- **JSON**: `target/cucumber-report.json`

## ✅ Validações Implementadas

1. ✅ Verifica que o serviço está disponível
2. ✅ Valida que a lista de resultados não é nula
3. ✅ Verifica que há pelo menos um resultado
4. ✅ Confirma que todos os resultados contêm a palavra-chave
5. ✅ Valida que o anime específico está nos resultados
6. ✅ Testa busca case-insensitive com múltiplas variações
7. ✅ Compara resultados de diferentes casos (maiúscula/minúscula/misto)

## 🔍 Melhorias Futuras

1. Adicionar mocks para não depender de dados reais
2. Criar fixtures de teste com dados conhecidos
3. Adicionar testes de performance
4. Implementar testes de integração com banco de dados em memória
5. Adicionar validação de logs e métricas

