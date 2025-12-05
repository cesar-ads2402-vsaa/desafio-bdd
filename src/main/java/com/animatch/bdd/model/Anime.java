package com.animatch.bdd.model;

import java.util.List;

/**
 * Modelo simplificado de Anime para testes BDD independentes
 */
public class Anime {
    private Long id;
    private String tituloPrincipal;
    private List<String> generos;
    private String classificacao;
    private String status;

    public Anime() {
    }

    public Anime(Long id, String tituloPrincipal, List<String> generos, 
                 String classificacao, String status) {
        this.id = id;
        this.tituloPrincipal = tituloPrincipal;
        this.generos = generos;
        this.classificacao = classificacao;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTituloPrincipal() {
        return tituloPrincipal;
    }

    public void setTituloPrincipal(String tituloPrincipal) {
        this.tituloPrincipal = tituloPrincipal;
    }

    public List<String> getGeneros() {
        return generos;
    }

    public void setGeneros(List<String> generos) {
        this.generos = generos;
    }

    public String getClassificacao() {
        return classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Anime{" +
                "id=" + id +
                ", tituloPrincipal='" + tituloPrincipal + '\'' +
                ", generos=" + generos +
                ", classificacao='" + classificacao + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}

