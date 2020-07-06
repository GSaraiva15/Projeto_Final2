package com.example.projeto_final;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void mostrarDoente(View view){

        Intent intentMostrarDadosDoente = new Intent(this, DisplayMostrarDoentes.class);
        startActivity(intentMostrarDadosDoente);
    }
    public void verEstatisticas (View view){

        Intent intentEstatistica = new Intent(this,DisplayVerEstatisticas.class);
        startActivity(intentEstatistica);
    }
    public void mostrarTestes(View view){

        Intent intentMostrarTestes = new Intent(this, DisplayMostrarTestes.class);
        startActivity(intentMostrarTestes);
    }

}
