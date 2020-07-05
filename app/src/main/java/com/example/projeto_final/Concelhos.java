package com.example.projeto_final;

public class Concelhos {
    private long id = -1;
    private String nome_concelho;
    private Integer nr_infetados=0;
    private Integer nr_recuperados=0;
    private Integer nr_obitos=0;
    private Integer nr_Habitante = 0;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome_concelho() {
        return nome_concelho;
    }

    public void setNome_concelho(String nome_concelho) {
        this.nome_concelho = nome_concelho;
    }

    public Integer getNr_infetados() {
        return nr_infetados;
    }

    public void setNr_infetados(Integer nr_infetados) {
        this.nr_infetados = nr_infetados;
    }

    public Integer getNr_recuperados() {
        return nr_recuperados;
    }

    public void setNr_recuperados(Integer nr_recuperados) {
        this.nr_recuperados = nr_recuperados;
    }

    public Integer getNr_obitos() {
        return nr_obitos;
    }

    public void setNr_obitos(Integer nr_obitos) {
        this.nr_obitos = nr_obitos;
    }

    public Integer getNr_Habitante() {
        return nr_Habitante;
    }

    public void setNr_Habitante(Integer nr_Habitante) {
        this.nr_Habitante = nr_Habitante;
    }
}