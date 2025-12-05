package com.animatch.bdd.steps;

import com.animatch.bdd.model.Anime;
import com.animatch.bdd.service.AnimeService;
import io.cucumber.java.pt.Dado;
import io.cucumber.java.pt.Quando;
import io.cucumber.java.pt.Então;
import io.cucumber.java.pt.E;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Step Definitions para os cenários de busca de animes
 * Implementação independente que não depende do projeto principal
 */
public class AnimeSearchStepDefinitions {

    private AnimeService animeService;
    private String palavraChave;
    private String generoFiltro;
    private String classificacaoFiltro;
    private String statusFiltro;
    private List<Anime> resultados;
    private Exception excecaoCapturada;

    @Dado("que estou na página de busca de animes")
    public void que_estou_na_pagina_de_busca_de_animes() {
        animeService = new AnimeService();
        resultados = null;
        palavraChave = null;
        generoFiltro = null;
        classificacaoFiltro = null;
        statusFiltro = null;
        excecaoCapturada = null;
    }

    @Dado("que existem animes cadastrados no sistema")
    public void que_existem_animes_cadastrados_no_sistema() {
        animeService = new AnimeService();
        List<Anime> todosAnimes = animeService.listarTodos();
        assertFalse(todosAnimes.isEmpty(), "Deve existir pelo menos um anime no sistema");
    }

    @Dado("que alguns animes têm gênero {string}")
    public void que_alguns_animes_tem_genero(String genero) {
        animeService = new AnimeService();
        List<Anime> animesComGenero = animeService.buscarAnimesComFiltros(genero, null, null, null);
        assertFalse(animesComGenero.isEmpty(), 
            "Deve existir pelo menos um anime com gênero " + genero);
    }

    @Dado("que alguns animes têm classificação {string}")
    public void que_alguns_animes_tem_classificacao(String classificacao) {
        animeService = new AnimeService();
        List<Anime> animesComClassificacao = animeService.buscarAnimesComFiltros(
            null, classificacao, null, null);
        assertFalse(animesComClassificacao.isEmpty(), 
            "Deve existir pelo menos um anime com classificação " + classificacao);
    }

    @Dado("que alguns animes têm status {string}")
    public void que_alguns_animes_tem_status(String status) {
        animeService = new AnimeService();
        List<Anime> animesComStatus = animeService.buscarAnimesComFiltros(
            null, null, status, null);
        assertFalse(animesComStatus.isEmpty(), 
            "Deve existir pelo menos um anime com status " + status);
    }

    @Dado("que existe um anime com título {string}")
    public void que_existe_um_anime_com_titulo(String titulo) {
        animeService = new AnimeService();
        List<Anime> animesComTitulo = animeService.buscarAnimesComFiltros(
            null, null, null, titulo);
        assertFalse(animesComTitulo.isEmpty(), 
            "Deve existir um anime com título " + titulo);
    }

    @Quando("eu digito {string} no campo de busca por nome")
    public void eu_digito_no_campo_de_busca_por_nome(String palavra) {
        try {
            palavraChave = palavra;
            resultados = animeService.buscarAnimesComFiltros(
                generoFiltro, classificacaoFiltro, statusFiltro, palavraChave);
        } catch (Exception e) {
            excecaoCapturada = e;
        }
    }

    @Quando("eu seleciono o gênero {string} no filtro de gênero")
    public void eu_seleciono_o_genero_no_filtro_de_genero(String genero) {
        try {
            generoFiltro = genero;
            resultados = animeService.buscarAnimesComFiltros(
                generoFiltro, classificacaoFiltro, statusFiltro, palavraChave);
        } catch (Exception e) {
            excecaoCapturada = e;
        }
    }

    @Quando("eu seleciono a classificação {string}")
    public void eu_seleciono_a_classificacao(String classificacao) {
        try {
            classificacaoFiltro = classificacao;
            resultados = animeService.buscarAnimesComFiltros(
                generoFiltro, classificacaoFiltro, statusFiltro, palavraChave);
        } catch (Exception e) {
            excecaoCapturada = e;
        }
    }

    @Quando("eu seleciono o status {string}")
    public void eu_seleciono_o_status(String status) {
        try {
            statusFiltro = status;
            resultados = animeService.buscarAnimesComFiltros(
                generoFiltro, classificacaoFiltro, statusFiltro, palavraChave);
        } catch (Exception e) {
            excecaoCapturada = e;
        }
    }

    @Quando("eu aplico filtros que não correspondem a nenhum anime")
    public void eu_aplico_filtros_que_nao_correspondem_a_nenhum_anime() {
        try {
            resultados = animeService.buscarAnimesComFiltros(
                "GeneroInexistente", "ClassificacaoInexistente", "StatusInexistente", null);
        } catch (Exception e) {
            excecaoCapturada = e;
        }
    }

    @Quando("nenhum filtro está selecionado")
    public void nenhum_filtro_esta_selecionado() {
        try {
            resultados = animeService.buscarAnimesComFiltros(null, null, null, null);
        } catch (Exception e) {
            excecaoCapturada = e;
        }
    }

    @Quando("eu busco animes sem filtros")
    public void eu_busco_animes_sem_filtros() {
        try {
            resultados = animeService.listarTodos();
        } catch (Exception e) {
            excecaoCapturada = e;
        }
    }

    @Então("o sistema deve retornar animes que contenham {string} no título")
    public void o_sistema_deve_retornar_animes_que_contenham_no_titulo(String palavra) {
        assertNull(excecaoCapturada, 
            "Não deve ocorrer exceção durante a busca");
        assertNotNull(resultados, "A lista de resultados não deve ser nula");
        assertFalse(resultados.isEmpty(), 
            "Deve retornar pelo menos um resultado para a busca: " + palavra);

        resultados.forEach(anime -> {
            assertNotNull(anime.getTituloPrincipal(), 
                "O título do anime não deve ser nulo");
            String titulo = anime.getTituloPrincipal().toLowerCase();
            assertTrue(titulo.contains(palavra.toLowerCase()), 
                String.format("O anime '%s' deve conter '%s' no título",
                    anime.getTituloPrincipal(), palavra));
        });
    }

    @Então("o anime {string} deve estar nos resultados")
    public void o_anime_deve_estar_nos_resultados(String tituloEsperado) {
        assertNotNull(resultados, "Resultados não devem ser nulos");
        boolean encontrado = resultados.stream()
            .anyMatch(anime -> anime.getTituloPrincipal() != null &&
                             anime.getTituloPrincipal().equalsIgnoreCase(tituloEsperado));
        assertTrue(encontrado, 
            String.format("O anime '%s' deve estar nos resultados", tituloEsperado));
    }

    @E("a busca não deve diferenciar maiúsculas e minúsculas")
    public void a_busca_nao_deve_diferenciar_maiusculas_e_minusculas() {
        assertNotNull(palavraChave, "Palavra-chave deve estar definida");
        
        List<Anime> resultadosMaiusculo = animeService.buscarAnimesComFiltros(
            generoFiltro, classificacaoFiltro, statusFiltro, palavraChave.toUpperCase());
        List<Anime> resultadosMinusculo = animeService.buscarAnimesComFiltros(
            generoFiltro, classificacaoFiltro, statusFiltro, palavraChave.toLowerCase());
        
        assertEquals(resultados.size(), resultadosMaiusculo.size(),
            "Resultados devem ser iguais independente de maiúsculas/minúsculas");
        assertEquals(resultados.size(), resultadosMinusculo.size(),
            "Resultados devem ser iguais independente de maiúsculas/minúsculas");
    }

    @Então("o sistema deve retornar apenas animes do gênero {string}")
    public void o_sistema_deve_retornar_apenas_animes_do_genero(String genero) {
        assertNotNull(resultados, "Resultados não devem ser nulos");
        assertFalse(resultados.isEmpty(), 
            "Deve retornar pelo menos um anime do gênero " + genero);
        
        resultados.forEach(anime -> {
            assertNotNull(anime.getGeneros(), 
                "Os gêneros do anime não devem ser nulos");
            assertTrue(anime.getGeneros().contains(genero),
                String.format("O anime '%s' deve ter o gênero '%s'",
                    anime.getTituloPrincipal(), genero));
        });
    }

    @E("todos os animes retornados devem ter {string} em seus gêneros")
    public void todos_os_animes_retornados_devem_ter_em_seus_generos(String genero) {
        resultados.forEach(anime -> {
            assertTrue(anime.getGeneros().contains(genero),
                String.format("O anime '%s' deve ter o gênero '%s'",
                    anime.getTituloPrincipal(), genero));
        });
    }

    @E("animes de outros gêneros não devem aparecer nos resultados")
    public void animes_de_outros_generos_nao_devem_aparecer_nos_resultados() {
        // Validação já feita no passo anterior
        assertNotNull(resultados);
    }

    @Então("o sistema deve retornar apenas animes com essa classificação")
    public void o_sistema_deve_retornar_apenas_animes_com_essa_classificacao() {
        assertNotNull(resultados, "Resultados não devem ser nulos");
        assertFalse(resultados.isEmpty(), "Deve retornar pelo menos um anime");
        
        resultados.forEach(anime -> {
            assertEquals(classificacaoFiltro, anime.getClassificacao(),
                String.format("O anime '%s' deve ter a classificação '%s'",
                    anime.getTituloPrincipal(), classificacaoFiltro));
        });
    }

    @Então("o sistema deve retornar apenas animes que estão em exibição")
    public void o_sistema_deve_retornar_apenas_animes_que_estao_em_exibicao() {
        assertNotNull(resultados, "Resultados não devem ser nulos");
        assertFalse(resultados.isEmpty(), "Deve retornar pelo menos um anime");
        
        resultados.forEach(anime -> {
            assertEquals("Currently Airing", anime.getStatus(),
                String.format("O anime '%s' deve ter status 'Currently Airing'",
                    anime.getTituloPrincipal()));
        });
    }

    @Então("o sistema deve exibir uma mensagem informativa")
    public void o_sistema_deve_exibir_uma_mensagem_informativa() {
        assertNotNull(resultados, "Resultados não devem ser nulos");
        assertTrue(resultados.isEmpty(), 
            "Quando não há resultados, a lista deve estar vazia");
    }

    @E("a mensagem deve indicar que nenhum anime foi encontrado")
    public void a_mensagem_deve_indicar_que_nenhum_anime_foi_encontrado() {
        assertTrue(resultados.isEmpty(), 
            "A lista de resultados deve estar vazia");
    }

    @Então("o sistema deve exibir todos os animes disponíveis")
    public void o_sistema_deve_exibir_todos_os_animes_disponiveis() {
        assertNotNull(resultados, "Resultados não devem ser nulos");
        List<Anime> todosAnimes = animeService.listarTodos();
        assertEquals(todosAnimes.size(), resultados.size(),
            "Deve exibir todos os animes quando não há filtros");
    }

    @E("animes com classificações proibidas não devem aparecer nos resultados")
    public void animes_com_classificacoes_proibidas_nao_devem_aparecer_nos_resultados() {
        List<String> classificacoesProibidas = List.of(
            "R+ - Mild Nudity", "Rx - Hentai", "R+", "Rx", "Hentai"
        );
        
        resultados.forEach(anime -> {
            if (anime.getClassificacao() != null) {
                String classificacao = anime.getClassificacao().toLowerCase();
                assertFalse(classificacoesProibidas.stream()
                    .anyMatch(proibida -> classificacao.contains(proibida.toLowerCase())),
                    String.format("O anime '%s' não deve ter classificação proibida",
                        anime.getTituloPrincipal()));
            }
        });
    }
}

