package com.example.projeto_final;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.Spinner;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DisplayInserirTestes extends AppCompatActivity {

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
        dropdownResultadoTeste.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {

        }
    });
}
    public void registaTeste (View view){

        EditText textInputEditTextNome = (EditText) findViewById(R.id.textInputEditTextNome);
        String nome = textInputEditTextNome.getText().toString();

        if (nome.length() == 0){
            textInputEditTextNome.setError(getString(R.string.NomeObrigatorio));
            textInputEditTextNome.requestFocus();
            return;
        }
        //https://www.youtube.com/watch?v=j_-dmsRWL3g&fbclid=IwAR1jo1CXntOg7lLi6Il8j4oXwdGhsuA0LsYGOX92UWWs6zcGV_cYH_BQNyg
        CalendarView calendarViewDataTeste = (CalendarView) findViewById(R.id.calendarViewDataTeste);

        calendarViewDataTeste.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String dateTeste = dayOfMonth + "/" + month + "/" + year;
            }
        });

    }
}

