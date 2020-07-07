package com.example.projeto_final;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.loader.content.CursorLoader;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.CursorAdapter;
import android.widget.EditText;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DisplayInserirTestes extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private Spinner spinnerNomeDoente;
    public static final  int ID_CURSOR_LOADER_NOME_DOENTE = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_inserir_testes);

    Intent intentInserirTestes = getIntent();

    Spinner dropdownResultadoTeste;
    dropdownResultadoTeste = (Spinner) findViewById(R.id.spinnerResultadoTeste);
    final List<String> resultadoTesteList = new ArrayList<>();
        resultadoTesteList.add(getString(R.string.resultadoPositivo));
        resultadoTesteList.add(getString(R.string.ResultadoNegativo));

    ArrayAdapter<String> adapterResultadoTest = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, resultadoTesteList);
        dropdownResultadoTeste.setAdapter(adapterResultadoTest);

        spinnerNomeDoente = (Spinner) findViewById(R.id.spinnerNomeDoente);
        mostrarDadosSpinnerNomeDoente(null);
        LoaderManager.getInstance(this).initLoader(ID_CURSOR_LOADER_NOME_DOENTE,null,this);

    }
    private void mostrarDadosSpinnerNomeDoente(Cursor data){
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item,data,new String[]{BdTabelaDoentes.NOME_DOENTE},new int[]{android.R.id.text1});
        spinnerNomeDoente.setAdapter(adapter);
    }
    public void registaTeste (View view){
        //https://www.youtube.com/watch?v=j_-dmsRWL3g&fbclid=IwAR1jo1CXntOg7lLi6Il8j4oXwdGhsuA0LsYGOX92UWWs6zcGV_cYH_BQNyg
        CalendarView calendarViewDataTeste = (CalendarView) findViewById(R.id.calendarViewDataTeste);

        calendarViewDataTeste.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String dateTeste = dayOfMonth + "/" + month + "/" + year;
            }
        });
        String resultado_teste = ((Spinner) findViewById(R.id.spinnerResultadoTeste)).getSelectedItem().toString();
        String dataTestes = new Date(calendarViewDataTeste.getDate()).toString();
        long idNome = spinnerNomeDoente.getSelectedItemId();

        Testes testes = new Testes();
        testes.setIdDoente(idNome);
        testes.setResultado_testes(resultado_teste);
        testes.setData_testes(dataTestes);
        try{
            this.getContentResolver().insert(ContentProvider.ENDERECO_TESTES, Converte.testesToContentValues(testes));
            Toast.makeText(this, R.string.teste_inserido_com_sucesso,Toast.LENGTH_SHORT).show();
        }catch (Exception e){
            Toast.makeText(this, R.string.teste_nao_inserido, Toast.LENGTH_SHORT).show();
        }

    }
    @Override
    protected void onResume() {
        getSupportLoaderManager().restartLoader(ID_CURSOR_LOADER_NOME_DOENTE, null, this);
        super.onResume();
    }

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param id   The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        return new CursorLoader(this,ContentProvider.ENDERECO_DOENTES,BdTabelaDoentes.TODOS_CAMPOS_DOENTE,null,null,null);
    }

    /**
     * Called when a previously created loader has finished its load.  Note
     * that normally an application is <em>not</em> allowed to commit fragment
     * transactions while in this call, since it can happen after an
     * activity's state is saved.  See {@link FragmentManager#beginTransaction()
     * FragmentManager.openTransaction()} for further discussion on this.
     *
     * <p>This function is guaranteed to be called prior to the release of
     * the last data that was supplied for this Loader.  At this point
     * you should remove all use of the old data (since it will be released
     * soon), but should not do your own release of the data since its Loader
     * owns it and will take care of that.  The Loader will take care of
     * management of its data so you don't have to.  In particular:
     *
     * <ul>
     * <li> <p>The Loader will monitor for changes to the data, and report
     * them to you through new calls here.  You should not monitor the
     * data yourself.  For example, if the data is a {@link Cursor}
     * and you place it in a {@link CursorAdapter}, use
     * the {@link CursorAdapter#CursorAdapter(Context,
     * Cursor, int)} constructor <em>without</em> passing
     * in either {@link CursorAdapter#FLAG_AUTO_REQUERY}
     * or {@link CursorAdapter#FLAG_REGISTER_CONTENT_OBSERVER}
     * (that is, use 0 for the flags argument).  This prevents the CursorAdapter
     * from doing its own observing of the Cursor, which is not needed since
     * when a change happens you will get a new Cursor throw another call
     * here.
     * <li> The Loader will release the data once it knows the application
     * is no longer using it.  For example, if the data is
     * a {@link Cursor} from a {@link CursorLoader},
     * you should not call close() on it yourself.  If the Cursor is being placed in a
     * {@link CursorAdapter}, you should use the
     * {@link CursorAdapter#swapCursor(Cursor)}
     * method so that the old Cursor is not closed.
     * </ul>
     *
     * <p>This will always be called from the process's main thread.
     *  @param loader The Loader that has finished.
     *
     * @param data The data generated by the Loader.
     */
    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mostrarDadosSpinnerNomeDoente(data);
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {
        mostrarDadosSpinnerNomeDoente(null);
    }
}

