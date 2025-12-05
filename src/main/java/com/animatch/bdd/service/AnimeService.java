package com.animatch.bdd.service;

import com.animatch.bdd.model.Anime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Serviço mockado para busca de animes
 * Este serviço simula o comportamento do sistema de busca sem depender do projeto principal
 */
public class AnimeService {
    
    // Base de dados mockada de animes para testes
    private static final List<Anime> ANIMES_MOCK = criarAnimesMock();

    /**
     * Busca animes aplicando os filtros especificados
     */
    public List<Anime> buscarAnimesComFiltros(String genero, String classificacao, 
                                              String status, String palavraChave) {
        List<Anime> resultados = new ArrayList<>(ANIMES_MOCK);

        // Aplica filtro de gênero
        if (genero != null && !genero.trim().isEmpty()) {
            resultados = resultados.stream()
                .filter(anime -> anime.getGeneros() != null && 
                               anime.getGeneros().contains(genero))
                .collect(Collectors.toList());
        }

        // Aplica filtro de classificação
        if (classificacao != null && !classificacao.trim().isEmpty()) {
            resultados = resultados.stream()
                .filter(anime -> classificacao.equals(anime.getClassificacao()))
                .collect(Collectors.toList());
        }

        // Aplica filtro de status
        if (status != null && !status.trim().isEmpty()) {
            resultados = resultados.stream()
                .filter(anime -> status.equals(anime.getStatus()))
                .collect(Collectors.toList());
        }

        // Aplica filtro de palavra-chave (busca case-insensitive)
        if (palavraChave != null && !palavraChave.trim().isEmpty()) {
            String palavraLower = palavraChave.toLowerCase().trim();
            resultados = resultados.stream()
                .filter(anime -> anime.getTituloPrincipal() != null &&
                               anime.getTituloPrincipal().toLowerCase().contains(palavraLower))
                .collect(Collectors.toList());
        }

        // Filtra animes inadequados
        resultados = filtrarAnimesInadequados(resultados);

        return resultados;
    }

    /**
     * Lista todos os animes disponíveis
     */
    public List<Anime> listarTodos() {
        return filtrarAnimesInadequados(new ArrayList<>(ANIMES_MOCK));
    }

    /**
     * Filtra animes com classificações proibidas
     */
    private List<Anime> filtrarAnimesInadequados(List<Anime> animes) {
        List<String> classificacoesProibidas = Arrays.asList(
            "R+ - Mild Nudity", "Rx - Hentai", "R+", "Rx", "Hentai"
        );

        return animes.stream()
            .filter(anime -> {
                if (anime.getClassificacao() == null) return true;
                String classificacao = anime.getClassificacao().toLowerCase();
                return classificacoesProibidas.stream()
                    .noneMatch(proibida -> classificacao.contains(proibida.toLowerCase()));
            })
            .collect(Collectors.toList());
    }

    /**
     * Cria uma base de dados mockada de animes para testes
     */
    private static List<Anime> criarAnimesMock() {
        List<Anime> animes = new ArrayList<>();

        // Animes de exemplo para testes
        animes.add(new Anime(1L, "One Punch Man", 
            Arrays.asList("Action", "Comedy", "Supernatural"), 
            "PG-13 - Teens 13 or older", 
            "Finished Airing"));

        animes.add(new Anime(2L, "Attack on Titan", 
            Arrays.asList("Action", "Drama", "Horror"), 
            "R - 17+ (violence & profanity)", 
            "Finished Airing"));

        animes.add(new Anime(3L, "My Hero Academia", 
            Arrays.asList("Action", "Comedy", "Supernatural"), 
            "PG-13 - Teens 13 or older", 
            "Currently Airing"));

        animes.add(new Anime(4L, "Demon Slayer", 
            Arrays.asList("Action", "Adventure", "Fantasy"), 
            "R - 17+ (violence & profanity)", 
            "Finished Airing"));

        animes.add(new Anime(5L, "Spy x Family", 
            Arrays.asList("Action", "Comedy", "Slice of Life"), 
            "PG-13 - Teens 13 or older", 
            "Currently Airing"));

        animes.add(new Anime(6L, "Jujutsu Kaisen", 
            Arrays.asList("Action", "Horror", "Supernatural"), 
            "R - 17+ (violence & profanity)", 
            "Currently Airing"));

        animes.add(new Anime(7L, "One Piece", 
            Arrays.asList("Action", "Adventure", "Comedy"), 
            "PG-13 - Teens 13 or older", 
            "Currently Airing"));

        animes.add(new Anime(8L, "Naruto", 
            Arrays.asList("Action", "Adventure", "Comedy"), 
            "PG-13 - Teens 13 or older", 
            "Finished Airing"));

        animes.add(new Anime(9L, "Death Note", 
            Arrays.asList("Mystery", "Supernatural", "Drama"), 
            "R - 17+ (violence & profanity)", 
            "Finished Airing"));

        animes.add(new Anime(10L, "Fullmetal Alchemist", 
            Arrays.asList("Action", "Adventure", "Drama"), 
            "PG-13 - Teens 13 or older", 
            "Finished Airing"));

        return animes;
    }
}

