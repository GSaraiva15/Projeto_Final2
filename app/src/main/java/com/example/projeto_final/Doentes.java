package com.example.projeto_final;

import android.content.ContentValues;
import android.database.Cursor;

public class Doentes {
    private long id = -1;
    private String nome_doente;//
    private String nascimento_doente;//
    private String telemovel_doente;
    private long id_concelho;//
    private String sexo_doente;//
    private String cronico_doente;//
    private String estado_doente;////
    private String data_estado;//

    public String getData_estado() {
        return data_estado;
    }

    public void setData_estado(String data_estado) {
        this.data_estado = data_estado;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome_doente() {
        return nome_doente;
    }

    public void setNome_doente(String nome_doente) {
        this.nome_doente = nome_doente;
    }

    public String getNascimento_doente() {
        return nascimento_doente;
    }

    public void setNascimento_doente(String nascimento_doente) {
        this.nascimento_doente = nascimento_doente;
    }

    public String getTelemovel_doente() {
        return telemovel_doente;
    }

    public void setTelemovel_doente(String telemovel_doente) {
        this.telemovel_doente = telemovel_doente;
    }

    public long getId_concelho() {
        return id_concelho;
    }

    public void setId_concelho(long id_concelho) {
        this.id_concelho = id_concelho;
    }

    public String getSexo_doente() {
        return sexo_doente;
    }

    public void setSexo_doente(String sexo_doente) {
        this.sexo_doente = sexo_doente;
    }

    public String getCronico_doente() {
        return cronico_doente;
    }

    public void setCronico_doente(String cronico_doente) {
        this.cronico_doente = cronico_doente;
    }

    public String getEstado_doente() {
        return estado_doente;
    }

    public void setEstado_doente(String estado_doente) {
        this.estado_doente = estado_doente;
    }

    public ContentValues getContentValues() {
        ContentValues valores = new ContentValues();
        valores.put(BdTabelaDoentes.NOME_DOENTE,nome_doente );
        valores.put(BdTabelaDoentes.NASCIMENTO_DOENTE, nascimento_doente);
        valores.put(BdTabelaDoentes.TELEMOVEL_DOENTE,telemovel_doente);
        valores.put(BdTabelaDoentes.CAMPO_ID_CONCELHO,id_concelho);
        valores.put(BdTabelaDoentes.SEXO_DOENTE,sexo_doente);
        valores.put(BdTabelaDoentes.CRONICO_DOENTE,cronico_doente);
        valores.put(BdTabelaDoentes.ESTADO_DOENTE, estado_doente);
        valores.put(BdTabelaDoentes.DATA_ESTADO_ATUAL,data_estado);


        return valores;
    }

    public static Doentes fromCursorDoentes(Cursor cursor) {

        long id = cursor.getLong(
                cursor.getColumnIndex(BdTabelaDoentes._ID)
        );

        String nome = cursor.getString(
                cursor.getColumnIndex(BdTabelaDoentes.NOME_DOENTE  )
        );

        String dataNascimetno = cursor.getString(
                cursor.getColumnIndex(BdTabelaDoentes.NASCIMENTO_DOENTE)
        );
        String telemovel = cursor.getString(
                cursor.getColumnIndex(BdTabelaDoentes.TELEMOVEL_DOENTE)
        );
        Integer concelho = cursor.getInt(
                cursor.getColumnIndex(BdTabelaDoentes.CAMPO_ID_CONCELHO)
        );
        String genero = cursor.getString(
                cursor.getColumnIndex(BdTabelaDoentes.SEXO_DOENTE)
        );
        String cronico = cursor.getString(
                cursor.getColumnIndex(BdTabelaDoentes.CRONICO_DOENTE)
        );
        String estado = cursor.getString(
                cursor.getColumnIndex(BdTabelaDoentes.ESTADO_DOENTE)
        );
        String dataEstado = cursor.getString(
                cursor.getColumnIndex(BdTabelaDoentes.DATA_ESTADO_ATUAL)
        );


        Doentes doentes = new Doentes();

        doentes.setId(id);
        doentes.setNome_doente(nome);
        doentes.setData_estado(dataNascimetno);
        doentes.setTelemovel_doente(telemovel);
        doentes.setId_concelho(concelho);
        doentes.setSexo_doente(genero);
        doentes.setCronico_doente(cronico);
        doentes.setEstado_doente(estado);
        doentes.setData_estado(dataEstado);

        return doentes;
    }
}
