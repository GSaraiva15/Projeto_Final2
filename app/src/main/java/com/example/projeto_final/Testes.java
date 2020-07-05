package com.example.projeto_final;

public class Testes {
    private long id = -1;
    private String data_testes;
    private String resultado_testes;
    private long idDoente = -1;

    public long getIdDoente() {
        return idDoente;
    }

    public void setIdDoente(long idDoente) {
        this.idDoente = idDoente;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getData_testes() {
        return data_testes;
    }

    public void setData_testes(String data_testes) {
        this.data_testes = data_testes;
    }

    public String getResultado_testes() {
        return resultado_testes;
    }

    public void setResultado_testes(String resultado_testes) {
        this.resultado_testes = resultado_testes;
    }
}
