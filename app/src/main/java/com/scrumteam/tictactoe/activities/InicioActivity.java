package com.scrumteam.tictactoe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.scrumteam.tictactoe.R;

public class InicioActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
    }


    public void nuevoJuego(View v)
    {
        Intent i = new Intent(this,ConfigNuevoJuegoActivity.class);
        startActivity(i);
    }

    public void lanzarAcercaDe(View v)
    {
        Intent i = new Intent(this,AcercaDeActivity.class);
        startActivity(i);
    }
}
