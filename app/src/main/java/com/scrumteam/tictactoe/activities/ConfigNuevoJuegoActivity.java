package com.scrumteam.tictactoe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.scrumteam.tictactoe.R;

    private

public class ConfigNuevoJuegoActivity extends AppCompatActivity {

        private EditText etJugador1, etJugador2;
        private Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_nuevo_juego);

        etJugador1 = findViewById(R.id.editTextTextPersonName);
        etJugador2 = findViewById(R.id.editTextTextPersonName2);
        btnIniciar = findViewById(R.id.iniciar);

    }







    /*Este metodo debe borrarse - solo sirve para que el que diseñe la sig pantalla
     * pueda pasar*/
    public void siguiente(View v)
    {
        Intent i = new Intent(this,JuegoActivity.class);
        startActivity(i);
    }
}
