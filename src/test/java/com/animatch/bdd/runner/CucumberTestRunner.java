package com.animatch.bdd.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * Runner para executar os testes BDD com Cucumber
 * Este runner é completamente independente do projeto principal
 */
@RunWith(Cucumber.class)
@CucumberOptions(
    features = "src/test/resources/features",
    glue = "com.animatch.bdd.steps",
    plugin = {
        "pretty",
        "html:target/cucumber-report.html",
        "json:target/cucumber-report.json",
        "junit:target/cucumber-report.xml"
    },
    monochrome = true,
    snippets = io.cucumber.junit.CucumberOptions.SnippetType.CAMELCASE
)
public class CucumberTestRunner {
    // Classe vazia - apenas configuração do Cucumber
}

