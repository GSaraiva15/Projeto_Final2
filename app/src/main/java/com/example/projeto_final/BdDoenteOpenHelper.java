package com.example.projeto_final;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class BdDoenteOpenHelper extends SQLiteOpenHelper {
    public static final String NOME_BD_PROJ_FINAL = "bdProjetoFinal.db";
    public static final int VERSAO_BASE_DADOS = 1;
    private final Context context;
    private final boolean DESENVOLVIMENTO = true;


    /**
     * Create a helper object to create, open, and/or manage a database.
     * This method always returns very quickly.  The database is not actually
     * created or opened until one of {@link #getWritableDatabase} or
     * {@link #getReadableDatabase} is called.
     *
     * @param context to use for locating paths to the the database
     */
    public BdDoenteOpenHelper(@Nullable Context context) {
        super(context, NOME_BD_PROJ_FINAL, null, VERSAO_BASE_DADOS);

        this.context = context;
    }

    /**
     * Called when the database is created for the first time. This is where the
     * creation of tables and the initial population of the tables should happen.
     *
     * @param db The database.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        BdTabelaDoentes tabelaRegistoDoentes = new BdTabelaDoentes(db);
        tabelaRegistoDoentes.criar();

        BdTabelaTestes tabelaTestes = new BdTabelaTestes(db);
        tabelaTestes.criar();

        BdTabelaConcelhos tabelaConcelhos = new BdTabelaConcelhos(db);
        tabelaConcelhos.criar();

        inserirConcelho(tabelaConcelhos);

        if(DESENVOLVIMENTO){
            seedData(db);
        }

    }
    private void seedData(SQLiteDatabase db){
        BdTabelaDoentes tabelaDoentes = new BdTabelaDoentes(db);
        BdTabelaConcelhos tabelaConcelhos   = new BdTabelaConcelhos(db);

        Concelhos concelhos = new Concelhos();
        concelhos.setNome_concelho("Figueira da Foz");
        concelhos.setNr_infetados(1);
        concelhos.setNr_recuperados(1);
        concelhos.setNr_obitos(0);
        concelhos.setNr_Habitante(123456879);
        long idFigueria = tabelaConcelhos.insert(Converte.concelhosToContentValues(concelhos));

        Doentes doentes = new Doentes();
        doentes.setNome_doente("Gonçalo");
        doentes.setNascimento_doente("15/02/2000");
        doentes.setTelemovel_doente("987654321");
        doentes.setId_concelho(idFigueria);
        doentes.setSexo_doente("Masculino");
        doentes.setCronico_doente("Não");
        doentes.setEstado_doente("Recuperado");
        doentes.setData_estado("25/06/2020");

        tabelaDoentes.insert(Converte.doenteToContentValues(doentes));


        concelhos.setNome_concelho("Santiago");
        concelhos.setNr_infetados(1);
        concelhos.setNr_recuperados(1);
        concelhos.setNr_obitos(0);
        concelhos.setNr_Habitante(123456879);
        long idSantiago = tabelaConcelhos.insert(Converte.concelhosToContentValues(concelhos));


        doentes.setNome_doente("Valter");
        doentes.setNascimento_doente("15/02/2000");
        doentes.setTelemovel_doente("987654321");
        doentes.setId_concelho(idSantiago);
        doentes.setSexo_doente("Masculino");
        doentes.setCronico_doente("Não");
        doentes.setEstado_doente("Recuperado");
        doentes.setData_estado("25/06/2020");

        tabelaDoentes.insert(Converte.doenteToContentValues(doentes));
    }
    private void inserirConcelho(BdTabelaConcelhos tabelaConcelhos) {
        Concelhos concelhos = new Concelhos();

        concelhos.setNome_concelho(context.getString(R.string.aguiarDaBeira));
        concelhos.setNr_Habitante(5473);
        tabelaConcelhos.insert(Converte.concelhosToContentValues(concelhos));

        concelhos.setNome_concelho(context.getString(R.string.almeida));
        concelhos.setNr_Habitante(7242);
        tabelaConcelhos.insert(Converte.concelhosToContentValues(concelhos));

        concelhos.setNome_concelho(context.getString(R.string.celoricoDaBeira));
        concelhos.setNr_Habitante(7693);
        tabelaConcelhos.insert(Converte.concelhosToContentValues(concelhos));

        concelhos.setNome_concelho(context.getString(R.string.figueiraDeCasteloRodrigo));
        concelhos.setNr_Habitante(6260);
        tabelaConcelhos.insert(Converte.concelhosToContentValues(concelhos));

        concelhos.setNome_concelho(context.getString(R.string.fornosdealgodres));
        concelhos.setNr_Habitante(4989);
        tabelaConcelhos.insert(Converte.concelhosToContentValues(concelhos));

        concelhos.setNome_concelho(context.getString(R.string.gouveia));
        concelhos.setNr_Habitante(14046);
        tabelaConcelhos.insert(Converte.concelhosToContentValues(concelhos));

        concelhos.setNome_concelho(context.getString(R.string.guarda));
        concelhos.setNr_Habitante(42541);
        tabelaConcelhos.insert(Converte.concelhosToContentValues(concelhos));

        concelhos.setNome_concelho(context.getString(R.string.manteigas));
        concelhos.setNr_Habitante(3430);
        tabelaConcelhos.insert(Converte.concelhosToContentValues(concelhos));

        concelhos.setNome_concelho(context.getString(R.string.meda));
        concelhos.setNr_Habitante(5202);
        tabelaConcelhos.insert(Converte.concelhosToContentValues(concelhos));

        concelhos.setNome_concelho(context.getString(R.string.pinhel));
        concelhos.setNr_Habitante(9627);
        tabelaConcelhos.insert(Converte.concelhosToContentValues(concelhos));

        concelhos.setNome_concelho(context.getString(R.string.sabugal));
        concelhos.setNr_Habitante(12544);
        tabelaConcelhos.insert(Converte.concelhosToContentValues(concelhos));

        concelhos.setNome_concelho(context.getString(R.string.seia));
        concelhos.setNr_Habitante(24702);
        tabelaConcelhos.insert(Converte.concelhosToContentValues(concelhos));

        concelhos.setNome_concelho(context.getString(R.string.trancoso));
        concelhos.setNr_Habitante(9878);
        tabelaConcelhos.insert(Converte.concelhosToContentValues(concelhos));

        concelhos.setNome_concelho(context.getString(R.string.vilaNovaDeFozCoa));
        concelhos.setNr_Habitante(7312);
        tabelaConcelhos.insert(Converte.concelhosToContentValues(concelhos));
    }

    /**
     * Called when the database needs to be upgraded. The implementation
     * should use this method to drop tables, add tables, or do anything else it
     * needs to upgrade to the new schema version.
     *
     * <p>
     * The SQLite ALTER TABLE documentation can be found
     * <a href="http://sqlite.org/lang_altertable.html">here</a>. If you add new columns
     * you can use ALTER TABLE to insert them into a live table. If you rename or remove columns
     * you can use ALTER TABLE to rename the old table, then create the new table and then
     * populate the new table with the contents of the old table.
     * </p><p>
     * This method executes within a transaction.  If an exception is thrown, all changes
     * will automatically be rolled back.
     * </p>
     *
     * @param db         The database.
     * @param oldVersion The old database version.
     * @param newVersion The new database version.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
