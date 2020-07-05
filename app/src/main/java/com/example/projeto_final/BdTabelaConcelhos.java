package com.example.projeto_final;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;

public class BdTabelaConcelhos implements BaseColumns {
    public static String NOME_TABELA = "Concelhos";
    public static String NOME_CONCELHO = "nome_concelho_";
    public static String NR_INFETADOS_CONCELHO = "nr_infetados";
    public static String NR_RECUPERADOS_CONCELHO = "nr_recuperados";
    public static String NR_OBITOS_CONCELHO = "nr_obitos";
    public static String NR_HABITANTES_CONCELHO = "nr_habitantes";

    public static String CAMPO_ID_COMPLETO = NOME_TABELA + "." + _ID;
    public static String NOME_CONCELHO_COMPLETO = NOME_TABELA + "." + NOME_CONCELHO;
    public static String NR_INFETADOS_CONCELHO_COMPLETO = NOME_TABELA + "." + NR_INFETADOS_CONCELHO;
    public static String NR_RECUPERADOS_CONCELHO_COMPLETO = NOME_TABELA + "." + NR_RECUPERADOS_CONCELHO;
    public static String NR_OBITOS_CONCELHO_COMPLETO = NOME_TABELA + "." + NR_OBITOS_CONCELHO;
    public static String NR_HABITANTES_COMPLETO = NOME_TABELA + "." + NR_HABITANTES_CONCELHO;



    public static final String[] TODOS_CAMPOS_CONCELHO = {_ID, NOME_CONCELHO,NR_INFETADOS_CONCELHO,NR_RECUPERADOS_CONCELHO,NR_OBITOS_CONCELHO};
    private SQLiteDatabase db;

    public BdTabelaConcelhos(SQLiteDatabase db){this.db =db;}
    public void criar(){
        db.execSQL("CREATE TABLE " + NOME_TABELA + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        NOME_CONCELHO + " TEXT NOT NULL, " +
                        NR_INFETADOS_CONCELHO + " INTEGER DEFAULT 0," +
                        NR_RECUPERADOS_CONCELHO +  " INTEGER DEFAULT 0," +
                        NR_OBITOS_CONCELHO + " INTEGER DEFAULT 0," +
                        NR_HABITANTES_CONCELHO + " INTEGER NOT NULL " +
                        ")"
        );
    }
 /**
 * Convenience method for inserting a row into the database.
 *
 *
 * @param values this map contains the initial column values for the
 *            row. The keys should be the column names and the values the
 *            column values
 * @return the row ID of the newly inserted row, or -1 if an error occurred
 */
public long insert ( ContentValues values){
    return db.insert(NOME_TABELA, null,values);
}
    /**
     * Query the given table, returning a {@link Cursor} over the result set.
     *
     *
     * @param columns A list of which columns to return. Passing null will
     *            return all columns, which is discouraged to prevent reading
     *            data from storage that isn't going to be used.
     * @param selection A filter declaring which rows to return, formatted as an
     *            SQL WHERE clause (excluding the WHERE itself). Passing null
     *            will return all rows for the given table.
     * @param selectionArgs You may include ?s in selection, which will be
     *         replaced by the values from selectionArgs, in order that they
     *         appear in the selection. The values will be bound as Strings.
     * @param groupBy A filter declaring how to group rows, formatted as an SQL
     *            GROUP BY clause (excluding the GROUP BY itself). Passing null
     *            will cause the rows to not be grouped.
     * @param having A filter declare which row groups to include in the cursor,
     *            if row grouping is being used, formatted as an SQL HAVING
     *            clause (excluding the HAVING itself). Passing null will cause
     *            all row groups to be included, and is required when row
     *            grouping is not being used.
     * @param orderBy How to order the rows, formatted as an SQL ORDER BY clause
     *            (excluding the ORDER BY itself). Passing null will use the
     *            default sort order, which may be unordered.
     * @return A {@link Cursor} object, which is positioned before the first entry. Note that
     * {@link Cursor}s are not synchronized, see the documentation for more details.
     * @see Cursor
     */
    public Cursor query (String[] columns, String selection,
                         String[] selectionArgs,String groupBy,String having,
                         String orderBy){
        return db.query(NOME_TABELA,columns,selection,selectionArgs,groupBy,having,orderBy);
    }
    /**
     * Convenience method for updating rows in the database.
     *
     * @param values a map from column names to new column values. null is a
     *            valid value that will be translated to NULL.
     * @param whereClause the optional WHERE clause to apply when updating.
     *            Passing null will update all rows.
     * @param whereArgs You may include ?s in the where clause, which
     *            will be replaced by the values from whereArgs. The values
     *            will be bound as Strings.
     * @return the number of rows affected
     */
    public int update(ContentValues values,String whereClause,String[] whereArgs){
        return db.update(NOME_TABELA,values,whereClause,whereArgs);
    }

    /**
     * Convenience method for deleting rows in the database.
     *
     * @param whereClause the optional WHERE clause to apply when deleting.
     *            Passing null will delete all rows.
     * @param whereArgs You may include ?s in the where clause, which
     *            will be replaced by the values from whereArgs. The values
     *            will be bound as Strings.
     * @return the number of rows affected if a whereClause is passed in, 0
     *         otherwise. To remove all rows and get a count pass "1" as the
     *         whereClause.
     */
    public int delete(String whereClause,String[] whereArgs){
        return db.delete(NOME_TABELA,whereClause,whereArgs);
    }
}
