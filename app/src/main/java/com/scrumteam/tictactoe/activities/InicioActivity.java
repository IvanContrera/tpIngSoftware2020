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

    /*Este metodo debe borrarse - solo sirve para que el que diseñe la sig pantalla
    * pueda pasar*/
    public void siguiente(View v)
    {
        Intent i = new Intent(this,ConfigNuevoJuegoActivity.class);
        startActivity(i);
    }
}
