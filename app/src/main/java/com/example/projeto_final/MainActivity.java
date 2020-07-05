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
    public void inserirDados (View view){

        Intent intentInserirDados = new Intent(this, DisplayMostrar.class);
        startActivity(intentInserirDados);
    }
    public void verEstatisticas (View view){

        Intent intentEstatistica = new Intent(this,DisplayVerEstatisticas.class);
        startActivity(intentEstatistica);
    }


}
