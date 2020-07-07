package com.example.projeto_final;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.CursorLoader;
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

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import android.os.Bundle;
import android.widget.Toast;

public class InserirDoente<adapter> extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private Spinner spinnerConcelhos;
    public static final  int ID_CURSOR_LOADER_CONCELHOS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inserir_doente);
        Intent intentInserirDoente = getIntent();

        Spinner dropdownGenero;
        dropdownGenero = (Spinner) findViewById(R.id.spinnerGenero);
        final List<String> generoList = new ArrayList<>();
        generoList.add(getString(R.string.masculino));
        generoList.add(getString(R.string.feminino));

        ArrayAdapter<String> adapterGenero = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, generoList);
        dropdownGenero.setAdapter(adapterGenero);
        dropdownGenero.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        spinnerConcelhos = (Spinner) findViewById(R.id.spinnerConcelho);
        mostrarDadosSpinnerConcelhos(null);
        LoaderManager.getInstance(this).initLoader(ID_CURSOR_LOADER_CONCELHOS,null,this);

        //https://www.youtube.com/watch?v=4xKsWNmULr0&fbclid=IwAR0rz5oUDNvXXcVGvvkwlCbaORgpQZZvfnTk4QvGNBdUXsK2vb3Em1fNpiE
        Spinner dropdownDoenteCronico;
        dropdownDoenteCronico = (Spinner) findViewById(R.id.spinnerDoenteCronico);
        final List<String> cronicoList = new ArrayList<>();
        cronicoList.add(getString(R.string.sim));
        cronicoList.add(getString(R.string.nao));
        ArrayAdapter<String> adapterCronico = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, cronicoList);
        dropdownDoenteCronico.setAdapter(adapterCronico);

        Spinner dropdownEstadoAtual;
        dropdownEstadoAtual = (Spinner) findViewById(R.id.spinnerEstadoAtual);
        final List<String> estadoAtualList = new ArrayList<>();
        estadoAtualList.add(getString(R.string.recuperado));
        estadoAtualList.add(getString(R.string.infetado));
        estadoAtualList.add(getString(R.string.falecido));
        ArrayAdapter<String> adapterEstadoAtual = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, estadoAtualList);
        dropdownEstadoAtual.setAdapter(adapterEstadoAtual);

    }
    private void mostrarDadosSpinnerConcelhos(Cursor data){
        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this,android.R.layout.simple_spinner_item,data,new String[]{BdTabelaConcelhos.NOME_CONCELHO},new int[]{android.R.id.text1});
        spinnerConcelhos.setAdapter(adapter);
    }

    public void registaDoente(View view) {
        EditText textInputEditTextNome = (EditText) findViewById(R.id.textInputEditTextNome);
        EditText textInputEditTextTelemovel = (EditText) findViewById(R.id.textInputEditTextLayoutTelemovel);

        String Telemovel = textInputEditTextTelemovel.getText().toString();
        String nome = textInputEditTextNome.getText().toString();

        String genero = ((Spinner) findViewById(R.id.spinnerGenero)).getSelectedItem().toString();
        String cronicoDoente = ((Spinner) findViewById(R.id.spinnerDoenteCronico)).getSelectedItem().toString();
        String estadoDoente = ((Spinner) findViewById(R.id.spinnerEstadoAtual)).getSelectedItem().toString();

        if (nome.length() == 0) {
            textInputEditTextNome.setError(getString(R.string.NomeObrigatorio));
            textInputEditTextNome.requestFocus();
            return;
        }
        else if ((Telemovel.length() != 9)) {
            textInputEditTextTelemovel.setError(getString(R.string.CaracteresTelemovelObrigatorio));
            textInputEditTextTelemovel.requestFocus();
        }else {
            CalendarView calendarViewDataAniversario = (CalendarView) findViewById(R.id.calendarViewDataAniversario);


            CalendarView calendarViewDateEstadoAtual = (CalendarView) findViewById(R.id.calendarViewDataEstadoAtual);

            long idConcelho = spinnerConcelhos.getSelectedItemId();
            String dataAniversaio = new Date(calendarViewDataAniversario.getDate()).toString();
            String dataEstado = new Date(calendarViewDateEstadoAtual.getDate()).toString();
            Doentes doentes = new Doentes();
            doentes.setNome_doente(nome);
            doentes.setNascimento_doente(dataAniversaio);
            doentes.setTelemovel_doente(Telemovel);
            doentes.setId_concelho(idConcelho);
            doentes.setSexo_doente(genero);
            doentes.setCronico_doente(cronicoDoente);
            doentes.setEstado_doente(estadoDoente);
            doentes.setData_estado(dataEstado);
            try {
                this.getContentResolver().insert(ContentProvider.ENDERECO_DOENTES, Converte.doenteToContentValues(doentes));
                Toast.makeText(this, R.string.inserido_com_sucesso, Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this, R.string.nao_inserido, Toast.LENGTH_SHORT).show();
            }
        }

    }
    @Override
    protected void onResume() {
        getSupportLoaderManager().restartLoader(ID_CURSOR_LOADER_CONCELHOS, null, this);
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
        return new androidx.loader.content.CursorLoader(this,ContentProvider.ENDRECO_CONCELHOS,BdTabelaConcelhos.TODOS_CAMPOS_CONCELHO,null,null,null);
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
        mostrarDadosSpinnerConcelhos(data);
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
        mostrarDadosSpinnerConcelhos(null);
    }

}
