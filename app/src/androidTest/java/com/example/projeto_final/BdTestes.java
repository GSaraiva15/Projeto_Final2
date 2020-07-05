package com.example.projeto_final;

import android.content.Context;
import android.database.ContentObservable;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class BdTestes {
    private Context getTargetContext() {
        return InstrumentationRegistry.getInstrumentation().getTargetContext();
    }

    @Before
    public void apagaBaseDados() {
        getTargetContext().deleteDatabase(BdDoenteOpenHelper.NOME_BD_PROJ_FINAL);
    }

    public String getTableAsString(SQLiteDatabase db, String tableName) {
        String tableString = String.format("Table %s:\n", tableName);
        Cursor allRows = db.rawQuery("SELECT * FROM " + tableName, null);
        if (allRows.moveToFirst()) {
            String[] columnNames = allRows.getColumnNames();
            do {
                for (String name : columnNames) {
                    tableString += String.format("%s: %s\n", name,
                            allRows.getString(allRows.getColumnIndex(name)));
                }
                tableString += "\n";

            } while (allRows.moveToNext());
        }
        return tableString;
    }

    @Test
    public void consegueAbrirBaseDados() {
        // Context of the app under test.
        Context appContext = getTargetContext();

        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase bdProjFinal = openHelper.getReadableDatabase();
        assertTrue(bdProjFinal.isOpen());
        getTableAsString(bdProjFinal, "Concelhos");
        bdProjFinal.close();
    }
    private long insereConcelhos (BdTabelaConcelhos tabelaConcelhos, Concelhos concelhos){
        long id = tabelaConcelhos.insert(Converte.concelhosToContentValues(concelhos));
        assertNotEquals(-1, id);
        return id;
    }

    private long insereConcelhos(BdTabelaConcelhos tabelaConcelhos, String nome_concelho, int nr_Habitante, int nr_infetados, int nr_recuperados, int nr_obitos){
        Concelhos concelhos = new Concelhos();
        concelhos.setNome_concelho(nome_concelho);
        concelhos.setNr_Habitante(nr_Habitante);
        concelhos.setNr_infetados(nr_infetados);
        concelhos.setNr_recuperados(nr_recuperados);
        concelhos.setNr_obitos(nr_obitos);
        return insereConcelhos(tabelaConcelhos, concelhos);
    }

    @Test
    public void consegueInserirConcelhos(){
        Context appContext = getTargetContext();
        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase bd = openHelper.getWritableDatabase();

        BdTabelaConcelhos tabelaConcelhos = new BdTabelaConcelhos(bd);
        insereConcelhos(tabelaConcelhos,"Seia",24702,0,0,0);

        bd.close();
    }

    @Test
    public void consegueLerConcelhos(){
        Context appContext = getTargetContext();
        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase bd = openHelper.getWritableDatabase();

        BdTabelaConcelhos tabelaConcelhos = new BdTabelaConcelhos(bd);

        Cursor cursor = tabelaConcelhos.query(BdTabelaConcelhos.TODOS_CAMPOS_CONCELHO, null, null, null, null, null);
        int numeroConcelhos = cursor.getCount();
        cursor.close();

        insereConcelhos(tabelaConcelhos, "Seia", 24702,0,0,0);

        cursor = tabelaConcelhos.query(BdTabelaConcelhos.TODOS_CAMPOS_CONCELHO, null, null, null, null ,null);
        assertEquals(numeroConcelhos + 1, cursor.getCount());
        cursor.close();

        bd.close();
    }

    @Test
    public void consegueEditarConcelhos(){
        Context appContext = getTargetContext();
        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase bd = openHelper.getWritableDatabase();

        BdTabelaConcelhos tabelaConcelhos = new BdTabelaConcelhos(bd);

        Concelhos concelhos = new Concelhos();
        concelhos.setNome_concelho("Seia");
        concelhos.setNr_Habitante(24702);
        concelhos.setNr_obitos(0);
        concelhos.setNr_recuperados(0);
        concelhos.setNr_infetados(0);

        long id = insereConcelhos(tabelaConcelhos, concelhos);

        concelhos.setNome_concelho("Seia");
        concelhos.setNr_Habitante(24702);
        concelhos.setNr_obitos(0);
        concelhos.setNr_recuperados(0);
        concelhos.setNr_infetados(1);

        int registosAlteradosPais = tabelaConcelhos.update(Converte.concelhosToContentValues(concelhos), BdTabelaConcelhos._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosAlteradosPais);

        bd.close();
    }

    @Test
    public void conseguesApagarConcelhos(){
        Context appContext = getTargetContext();
        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase bd = openHelper.getWritableDatabase();

        BdTabelaConcelhos tabelaConcelhos = new BdTabelaConcelhos(bd);
        long id = insereConcelhos(tabelaConcelhos, "seia", 24702,0,0,0);

        int registoApagadoConcelhos = tabelaConcelhos.delete(BdTabelaConcelhos._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registoApagadoConcelhos);

        bd.close();
    }

    private long insereDoente(BdTabelaDoentes tabelaDoentes, Doentes doentes) {
        long id = tabelaDoentes.insert(Converte.doenteToContentValues(doentes));
        assertNotEquals(-1, id);
        return id;
    }

    private long insereDoente(BdTabelaDoentes tabelaDoentes, String Nome, String Data_Nascimento, String Telemovel, long Concelho, String Sexo, String Cronico, String Estado, String dataEstado) {
        Doentes doentes = new Doentes();
        doentes.setNome_doente(Nome);
        doentes.setNascimento_doente(Data_Nascimento);
        doentes.setTelemovel_doente(Telemovel);
        doentes.setId_concelho(Concelho);
        doentes.setSexo_doente(Sexo);
        doentes.setCronico_doente(Cronico);
        doentes.setEstado_doente(Estado);
        doentes.setData_estado(dataEstado);
        return insereDoente(tabelaDoentes, doentes);
    }

    @Test
    public void concegueInserirDoente() {
        Context appContext = getTargetContext();

        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        BdTabelaConcelhos tabelaConcelhos = new BdTabelaConcelhos(db);
        BdTabelaDoentes tabelaDoentes = new BdTabelaDoentes(db);
        getTableAsString(db, "Concelhos");
        Cursor cursor = tabelaDoentes.query(BdTabelaDoentes.TODOS_CAMPOS_DOENTE, null, null, null, null, null);
        int registos = cursor.getCount();
        cursor.close();
        Cursor cursor1 = tabelaConcelhos.query(new String[]{"_id"}, "nome_concelho_=?", new String[]{"Seia"}, null, null, null);
        long id_concelho = -1;
        if(cursor1 != null && cursor1.moveToFirst())
            id_concelho = cursor1.getInt(cursor1.getColumnIndex("_id"));
        insereDoente(tabelaDoentes, "Gonçalo", "15/02/2000", "987654321", id_concelho, "Masculino", "Não,", "Recuperado","25/06/2020");
        cursor = tabelaDoentes.query(BdTabelaDoentes.TODOS_CAMPOS_DOENTE, null, null, null, null, null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();
        db.close();
    }

    @Test
    public void consegueAlterarDoentes() {
        Context appContext = getTargetContext();

        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase bdDoente = openHelper.getWritableDatabase();

        BdTabelaDoentes tabelaDoentes = new BdTabelaDoentes(bdDoente);
        BdTabelaConcelhos tabelaConcelhos = new BdTabelaConcelhos(bdDoente);

        Doentes doentes = new Doentes();
        doentes.setNome_doente("Gonçalo");
        doentes.setNascimento_doente("15/02/2000");
        doentes.setTelemovel_doente("987654321");
        long id_concelho = tabelaConcelhos.query(new String[]{"_id"}, "nome_concelho_ = ?", new String[]{"Seia"}, null, null, null).getColumnIndex("_id");
        doentes.setId_concelho(id_concelho);
        doentes.setSexo_doente("Masculino");
        doentes.setCronico_doente("Não");
        doentes.setEstado_doente("Recuperado");
        doentes.setData_estado("25/06/2020");
        long id = insereDoente(tabelaDoentes, doentes);

        doentes.setNome_doente("Gonçalo Saraiva");
        doentes.setNascimento_doente("15/02/2000");
        doentes.setTelemovel_doente("987654321");
        id_concelho = tabelaConcelhos.query(new String[]{"_id"}, "nome_concelho_ = ?", new String[]{"Seia"}, null, null, null).getColumnIndex("_id");
        doentes.setId_concelho(id_concelho);
        doentes.setSexo_doente("Masculino");
        doentes.setCronico_doente("Não");
        doentes.setEstado_doente("Recuperado");
        doentes.setData_estado("25/06/2020");
        int registosAlterados = tabelaDoentes.update(Converte.doenteToContentValues(doentes), BdTabelaDoentes._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosAlterados);
        bdDoente.close();
    }

    @Test
    public void consegueApagarDoentes() {
        Context appContext = getTargetContext();
        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase db = openHelper.getWritableDatabase();

        BdTabelaDoentes tabelaDoentes = new BdTabelaDoentes(db);
        BdTabelaConcelhos tabelaConcelhos = new BdTabelaConcelhos(db);

        Cursor cursor1 = tabelaConcelhos.query(new String[]{"_id"}, "nome_concelho_=?", new String[]{"Seia"}, null, null, null);
        long id_conselho = -1;
        if(cursor1 != null && cursor1.moveToFirst())
            id_conselho = cursor1.getInt(cursor1.getColumnIndex("_id"));
        long id = insereDoente(tabelaDoentes, "Gonçalo", "15/02/2000", "987654321", id_conselho, "Masculino", "Não,", "Recuperado","25/06/2020");
        int registosApagados = tabelaDoentes.delete(BdTabelaDoentes._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1, registosApagados);
        db.close();
    }

    @Test
    public void consegueLerDoente() {
        Context appContext = getTargetContext();

        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        BdTabelaDoentes tabelaDoentes = new BdTabelaDoentes(db);
        BdTabelaConcelhos tabelaConcelhos = new BdTabelaConcelhos(db);

        Cursor cursor = tabelaDoentes.query(BdTabelaDoentes.TODOS_CAMPOS_DOENTE, null, null, null, null, null);
        int registos = cursor.getCount();
        cursor.close();
        Cursor cursor1 = tabelaConcelhos.query(new String[]{"_id"}, "nome_concelho_=?", new String[]{"Seia"}, null, null, null);
        long id_concelho = -1;
        if(cursor1 != null && cursor1.moveToFirst())
            id_concelho = cursor1.getInt(cursor1.getColumnIndex("_id"));
        insereDoente(tabelaDoentes, "Gonçalo", "15/02/2000", "987654321", id_concelho, "Masculino", "Não,", "Recuperado","25/06/2020");
        cursor = tabelaDoentes.query(BdTabelaDoentes.TODOS_CAMPOS_DOENTE, null, null, null, null, null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();
        db.close();
    }

    private long insereTeste(BdTabelaTestes tabelaTestes, Testes testes) {
        long id = tabelaTestes.insert(Converte.testesToContentValues(testes));
        assertNotEquals(-1, id);
        return id;
    }

    private long insereTeste(BdTabelaTestes tabelaTestes, String dataTestes, String Resultado_teste, long idDoente) {

        Testes testes = new Testes();

        testes.setData_testes(dataTestes);
        testes.setResultado_testes(Resultado_teste);
        testes.setIdDoente(idDoente);

        return insereTeste(tabelaTestes, testes);
    }
    @Test
    public void consegueInserirTeste() {
        Context appContext = getTargetContext();
        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        BdTabelaDoentes tabelaDoentes = new BdTabelaDoentes(db);
        BdTabelaTestes tabelaTestes = new BdTabelaTestes(db);
        getTableAsString(db,"Doentes");
        Cursor cursor = tabelaTestes.query(BdTabelaTestes.TODOS_CAMPOS_TESTES, null, null, null, null, null);
        long registos = cursor.getCount();
        cursor.close();
        Cursor cursor1 = tabelaDoentes.query(new String[]{"_id"}, "nome =?", new String[]{"Gonçalo"}, null, null, null);
        long id_doentes = -1;
        if(cursor1 != null && cursor1.moveToFirst())
            id_doentes = cursor1.getInt(cursor1.getColumnIndex("_id"));
        insereTeste(tabelaTestes,"03/07/2020","recuperado",id_doentes);;
        cursor = tabelaTestes.query(BdTabelaTestes.TODOS_CAMPOS_TESTES, null, null, null, null, null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();
        db.close();
    }

    @Test
    public void conseguelerTeste() {
        Context appContext = getTargetContext();

        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        BdTabelaDoentes tabelaDoentes = new BdTabelaDoentes(db);
        BdTabelaTestes tabelaTestes = new BdTabelaTestes(db);

        Cursor cursor = tabelaTestes.query(BdTabelaTestes.TODOS_CAMPOS_TESTES, null, null, null, null, null);
        int registos = cursor.getCount();
        cursor.close();
        Cursor cursor1 = tabelaDoentes.query(new String[]{"_id"}, "nome =?", new String[]{"Gonçalo"}, null, null, null);
        long id_doente = -1;
        if(cursor1 != null && cursor1.moveToFirst())
            id_doente = cursor1.getInt(cursor1.getColumnIndex("_id"));
        insereTeste(tabelaTestes, "13/06/2020", "Infetado",id_doente);
        cursor = tabelaTestes.query(BdTabelaTestes.TODOS_CAMPOS_TESTES, null, null, null, null, null);
        assertEquals(registos + 1, cursor.getCount());
        cursor.close();

        db.close();
    }

    @Test
    public void consegueAlterarTeste() {
        Context appContext = getTargetContext();

        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase bdTestes = openHelper.getWritableDatabase();
        SQLiteDatabase db = openHelper.getWritableDatabase();
        BdTabelaTestes tabelaTestes = new BdTabelaTestes(db);
        BdTabelaDoentes tabelaDoentes = new BdTabelaDoentes(db);

        Testes testes = new Testes();
        testes.setData_testes("04/07/2020");
        testes.setResultado_testes("infetado");
        long id_doentes = tabelaDoentes.query(new String[]{"_id"}, "nome = ?", new String[]{"Gonçalo"}, null, null, null).getColumnIndex("_id");
        testes.setIdDoente(id_doentes);
        long id = insereTeste(tabelaTestes,testes);

        testes.setData_testes("04/07/2020");
        testes.setResultado_testes("recuperado" );
        id_doentes = tabelaDoentes.query(new String[]{"_id"}, "nome = ?", new String[]{"Gonçalo"}, null, null, null).getColumnIndex("_id");
        testes.setIdDoente(id_doentes);
        int registosAlterados = tabelaTestes.update(Converte.testesToContentValues(testes),BdTabelaTestes._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1,registosAlterados);
        bdTestes.close();

    }

    @Test
    public void consegueEliminarTestes() {
        Context appContext = getTargetContext();

        BdDoenteOpenHelper openHelper = new BdDoenteOpenHelper(appContext);
        SQLiteDatabase db = openHelper.getWritableDatabase();
        BdTabelaTestes tabelaTestes = new BdTabelaTestes(db);
        BdTabelaDoentes tabelaDoentes = new BdTabelaDoentes(db);

        Cursor cursor = tabelaDoentes.query(new String[]{"_id"},"nome =?", new String[]{"Gonçalo"},null,null,null);
        long id_doentes = -1;
        if (cursor !=null && cursor.moveToFirst())
            id_doentes = cursor.getInt(cursor.getColumnIndex("_id"));
        long id = insereTeste(tabelaTestes,"04/07/2020","recuperado",id_doentes);
        int registoApagados = tabelaTestes.delete(BdTabelaTestes._ID + "=?", new String[]{String.valueOf(id)});
        assertEquals(1,registoApagados);
        db.close();
    }
}
