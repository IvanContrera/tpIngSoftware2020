package com.scrumteam.tictactoe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.scrumteam.tictactoe.R;

public class ConfigNuevoJuegoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_nuevo_juego);
    }

    /*Este metodo debe borrarse - solo sirve para que el que diseñe la sig pantalla
     * pueda pasar*/
    public void siguiente(View v)
    {
        Intent i = new Intent(this,JuegoActivity.class);
        startActivity(i);
    }
}
