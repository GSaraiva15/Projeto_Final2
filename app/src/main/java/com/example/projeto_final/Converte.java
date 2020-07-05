package com.example.projeto_final;

import android.content.ContentValues;
import android.database.Cursor;

public class Converte {
    public static ContentValues doenteToContentValues(Doentes doentes) {
        ContentValues valores = new ContentValues();
        valores.put(BdTabelaDoentes.NOME_DOENTE, doentes.getNome_doente());
        valores.put(BdTabelaDoentes.NASCIMENTO_DOENTE, doentes.getNascimento_doente());
        valores.put(BdTabelaDoentes.TELEMOVEL_DOENTE, doentes.getTelemovel_doente());
        valores.put(BdTabelaDoentes.CAMPO_ID_CONCELHO, doentes.getId_concelho());
        valores.put(BdTabelaDoentes.SEXO_DOENTE, doentes.getSexo_doente());
        valores.put(BdTabelaDoentes.CRONICO_DOENTE, doentes.getCronico_doente());
        valores.put(BdTabelaDoentes.ESTADO_DOENTE, doentes.getEstado_doente());
        valores.put(BdTabelaDoentes.DATA_ESTADO_ATUAL,doentes.getData_estado());
        return valores;
    }

    public static Doentes contentValuesToDoentes(ContentValues valores) {
        Doentes doentes = new Doentes();
        doentes.setId(valores.getAsLong(BdTabelaDoentes._ID));
        doentes.setNome_doente(valores.getAsString(BdTabelaDoentes.NOME_DOENTE));
        doentes.setTelemovel_doente(valores.getAsString(BdTabelaDoentes.TELEMOVEL_DOENTE));
        doentes.setId_concelho(valores.getAsInteger(BdTabelaDoentes.CAMPO_ID_CONCELHO));
        doentes.setSexo_doente(valores.getAsString(BdTabelaDoentes.SEXO_DOENTE));
        doentes.setCronico_doente(valores.getAsString(BdTabelaDoentes.CRONICO_DOENTE));
        doentes.setEstado_doente(valores.getAsString(BdTabelaDoentes.ESTADO_DOENTE));
        doentes.setData_estado(valores.getAsString(BdTabelaDoentes.DATA_ESTADO_ATUAL));
        return doentes;
    }
    public static Doentes cursorToDoente(Cursor cursor){
        Doentes doentes = new Doentes();
        doentes.setId(cursor.getLong(cursor.getColumnIndex(BdTabelaDoentes._ID)));
        doentes.setNome_doente(cursor.getString(cursor.getColumnIndex(BdTabelaDoentes.NOME_DOENTE)));
        doentes.setNascimento_doente(cursor.getString(cursor.getColumnIndex(BdTabelaDoentes.NASCIMENTO_DOENTE)));
        doentes.setTelemovel_doente(cursor.getString(cursor.getColumnIndex(BdTabelaDoentes.TELEMOVEL_DOENTE)));
        doentes.setId_concelho(cursor.getInt(cursor.getColumnIndex(BdTabelaDoentes.CAMPO_ID_CONCELHO)));
        doentes.setSexo_doente(cursor.getString(cursor.getColumnIndex(BdTabelaDoentes.SEXO_DOENTE)));
        doentes.setCronico_doente(cursor.getString(cursor.getColumnIndex(BdTabelaDoentes.CRONICO_DOENTE)));
        doentes.setEstado_doente(cursor.getString(cursor.getColumnIndex(BdTabelaDoentes.ESTADO_DOENTE)));
        doentes.setData_estado(cursor.getString(cursor.getColumnIndex(BdTabelaDoentes.DATA_ESTADO_ATUAL)));
        return doentes;
    }
    public static ContentValues testesToContentValues(Testes testes){
        ContentValues valores = new ContentValues();
        valores.put(BdTabelaTestes.DATA_TESTE, testes.getData_testes());
        valores.put(BdTabelaTestes.RESULTADO_TESTE, testes.getResultado_testes());
        valores.put(BdTabelaTestes.CAMPO_ID_DOENTE, testes.getIdDoente());
        return valores;
    }
    public static Testes contentValuesToTestes(ContentValues valores){
        Testes testes = new Testes();
        testes.setId(valores.getAsLong(BdTabelaTestes._ID));
        testes.setData_testes(valores.getAsString(BdTabelaTestes.DATA_TESTE));
        testes.setResultado_testes(valores.getAsString(BdTabelaTestes.RESULTADO_TESTE));
        testes.setIdDoente(valores.getAsLong(BdTabelaTestes.CAMPO_ID_DOENTE));
        return testes;
    }
    public static Testes cursorToTestes(Cursor cursor){
        Testes testes = new Testes();
        testes.setId(cursor.getLong(cursor.getColumnIndex(BdTabelaTestes._ID)));
        testes.setData_testes(cursor.getString(cursor.getColumnIndex(BdTabelaTestes.DATA_TESTE)));
        testes.setResultado_testes(cursor.getString(cursor.getColumnIndex(BdTabelaTestes.RESULTADO_TESTE)));
        testes.setIdDoente(cursor.getLong(cursor.getColumnIndex(BdTabelaTestes.CAMPO_ID_DOENTE)));
        return testes;
    }
    public static ContentValues concelhosToContentValues(Concelhos concelhos){
        ContentValues valores = new ContentValues();
        valores.put(BdTabelaConcelhos.NOME_CONCELHO, concelhos.getNome_concelho());
        valores.put(BdTabelaConcelhos.NR_INFETADOS_CONCELHO, concelhos.getNr_infetados());
        valores.put(BdTabelaConcelhos.NR_RECUPERADOS_CONCELHO, concelhos.getNr_recuperados());
        valores.put(BdTabelaConcelhos.NR_OBITOS_CONCELHO, concelhos.getNr_obitos());
        valores.put(BdTabelaConcelhos.NR_HABITANTES_CONCELHO,concelhos.getNr_Habitante());
        return  valores ;
    }
    public static Concelhos contentValuesToConcelhos(ContentValues valores){
        Concelhos concelhos = new Concelhos();
        concelhos.setId(valores.getAsLong(BdTabelaConcelhos._ID));
        concelhos.setNr_infetados(valores.getAsInteger(BdTabelaConcelhos.NR_INFETADOS_CONCELHO));
        concelhos.setNr_recuperados(valores.getAsInteger(BdTabelaConcelhos.NR_RECUPERADOS_CONCELHO));
        concelhos.setNr_obitos(valores.getAsInteger(BdTabelaConcelhos.NR_OBITOS_CONCELHO));
        concelhos.setNr_Habitante(valores.getAsInteger(BdTabelaConcelhos.NR_HABITANTES_CONCELHO));
        return concelhos;
    }
    public static Concelhos cursorToConcelho(Cursor cursor){
        Concelhos concelhos = new Concelhos();
        concelhos.setId(cursor.getLong(cursor.getColumnIndex(BdTabelaConcelhos._ID)));
        concelhos.setNr_infetados(cursor.getInt(cursor.getColumnIndex(BdTabelaConcelhos.NR_INFETADOS_CONCELHO)));
        concelhos.setNr_recuperados(cursor.getInt(cursor.getColumnIndex(BdTabelaConcelhos.NR_OBITOS_CONCELHO)));
        concelhos.setNr_Habitante(cursor.getInt(cursor.getColumnIndex(BdTabelaConcelhos.NR_HABITANTES_CONCELHO)));
        return concelhos;
    }
}