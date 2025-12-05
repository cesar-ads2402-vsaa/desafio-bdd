package com.animatch.bdd;

import com.animatch.bdd.model.Anime;
import com.animatch.bdd.service.AnimeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Exemplo de teste unitário para o Cenário 1: Buscar anime por nome parcial
 * 
 * Este teste é completamente independente do projeto principal AniMatch
 * e pode ser executado isoladamente
 */
@DisplayName("Testes BDD - Busca de Animes por Nome")
class AnimeServiceBuscaTest {

    private AnimeService animeService;

    @BeforeEach
    void setUp() {
        animeService = new AnimeService();
    }

    @Test
    @DisplayName("Cenário 1: Buscar anime por nome parcial - deve encontrar animes que contenham a palavra-chave")
    void deveBuscarAnimePorNomeParcial() {
        // Given: que estou na página de busca de animes
        assertNotNull(animeService, "AnimeService deve estar disponível");

        // When: eu digito "One" no campo de busca por nome
        String palavraChave = "One";
        List<Anime> resultados = animeService.buscarAnimesComFiltros(
            null,  // genero
            null,  // classificacao
            null,  // status
            palavraChave  // palavraChave
        );

        // Then: o sistema deve retornar animes que contenham "One" no título
        assertNotNull(resultados, "A lista de resultados não deve ser nula");
        assertFalse(resultados.isEmpty(), 
            "Deve retornar pelo menos um resultado para '" + palavraChave + "'");
        
        // Verifica que todos os resultados contêm a palavra-chave
        resultados.forEach(anime -> {
            assertNotNull(anime.getTituloPrincipal(), 
                "O título do anime não deve ser nulo");
            
            String titulo = anime.getTituloPrincipal().toLowerCase();
            assertTrue(titulo.contains(palavraChave.toLowerCase()), 
                String.format(
                    "O anime '%s' deve conter '%s' no título",
                    anime.getTituloPrincipal(),
                    palavraChave
                )
            );
        });

        // E: o anime "One Punch Man" deve estar nos resultados
        boolean encontrado = resultados.stream()
            .anyMatch(anime -> 
                anime.getTituloPrincipal() != null &&
                anime.getTituloPrincipal().equalsIgnoreCase("One Punch Man")
            );
        
        assertTrue(encontrado, 
            "O anime 'One Punch Man' deve estar nos resultados");
    }

    @Test
    @DisplayName("Cenário 9: Busca não deve diferenciar maiúsculas e minúsculas")
    void buscaNaoDeveDiferenciarMaiusculasEMinusculas() {
        // Given: que estou na página de busca de animes
        String palavraChave = "one";

        // When: eu digito variações da palavra-chave
        List<Anime> resultadosMinusculo = animeService.buscarAnimesComFiltros(
            null, null, null, palavraChave.toLowerCase()
        );
        
        List<Anime> resultadosMaiusculo = animeService.buscarAnimesComFiltros(
            null, null, null, palavraChave.toUpperCase()
        );
        
        List<Anime> resultadosMisto = animeService.buscarAnimesComFiltros(
            null, null, null, "OnE"
        );

        // Then: todos devem retornar a mesma quantidade de resultados
        assertEquals(resultadosMinusculo.size(), resultadosMaiusculo.size(),
            "Resultados devem ser iguais independente de maiúsculas/minúsculas");
        
        assertEquals(resultadosMinusculo.size(), resultadosMisto.size(),
            "Resultados devem ser iguais independente de maiúsculas/minúsculas");

        // Verifica que os mesmos animes aparecem
        if (!resultadosMinusculo.isEmpty()) {
            List<String> titulosMinusculo = resultadosMinusculo.stream()
                .map(a -> a.getTituloPrincipal().toLowerCase())
                .sorted()
                .toList();
            
            List<String> titulosMaiusculo = resultadosMaiusculo.stream()
                .map(a -> a.getTituloPrincipal().toLowerCase())
                .sorted()
                .toList();
            
            assertEquals(titulosMinusculo, titulosMaiusculo,
                "Os mesmos animes devem aparecer independente de maiúsculas/minúsculas");
        }
    }

    @Test
    @DisplayName("Cenário 10: Buscar anime usando parte do nome")
    void deveBuscarAnimeUsandoParteDoNome() {
        // Given: que estou na página de busca de animes
        String parteDoNome = "Punch";

        // When: eu digito parte do nome no campo de busca
        List<Anime> resultados = animeService.buscarAnimesComFiltros(
            null, null, null, parteDoNome
        );

        // Then: o sistema deve retornar animes que contenham essa parte no título
        assertNotNull(resultados, "A lista de resultados não deve ser nula");
        assertFalse(resultados.isEmpty(), 
            "Deve retornar pelo menos um resultado para '" + parteDoNome + "'");
        
        resultados.forEach(anime -> {
            String titulo = anime.getTituloPrincipal().toLowerCase();
            assertTrue(titulo.contains(parteDoNome.toLowerCase()), 
                String.format(
                    "O anime '%s' deve conter '%s' no título",
                    anime.getTituloPrincipal(),
                    parteDoNome
                )
            );
        });
    }

    @Test
    @DisplayName("Cenário 2: Filtrar animes por gênero específico")
    void deveFiltrarAnimesPorGenero() {
        // Given: que estou na página de busca de animes
        String genero = "Action";

        // When: eu seleciono o gênero "Action"
        List<Anime> resultados = animeService.buscarAnimesComFiltros(
            genero, null, null, null
        );

        // Then: o sistema deve retornar apenas animes do gênero "Action"
        assertNotNull(resultados, "Resultados não devem ser nulos");
        assertFalse(resultados.isEmpty(), 
            "Deve retornar pelo menos um anime do gênero " + genero);
        
        resultados.forEach(anime -> {
            assertNotNull(anime.getGeneros(), "Os gêneros não devem ser nulos");
            assertTrue(anime.getGeneros().contains(genero),
                String.format("O anime '%s' deve ter o gênero '%s'",
                    anime.getTituloPrincipal(), genero));
        });
    }
}

