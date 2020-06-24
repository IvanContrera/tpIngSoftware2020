package com.scrumteam.tictactoe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.scrumteam.tictactoe.R;



public class ConfigNuevoJuegoActivity extends AppCompatActivity {

        private EditText etJugador1, etJugador2;
        private RadioButton rbTablero3x3,rbEstiloClasico;
        private RadioGroup groupNivelJuego,groupEstiloGrafico;
        private Button btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_config_nuevo_juego);

        etJugador1 = findViewById(R.id.txtJugador1);
        etJugador2 = findViewById(R.id.txtJugador2);
        btnIniciar = findViewById(R.id.iniciar);
        groupEstiloGrafico = findViewById(R.id.groupEstiloGrafico);
        groupNivelJuego = findViewById(R.id.groupNivelJuego);
        rbEstiloClasico = findViewById(R.id.estiloClasico);
        rbTablero3x3 = findViewById(R.id.nivel3x3);

        rbEstiloClasico.setChecked(true);
        rbTablero3x3.setChecked(true);

    }

    public void iniciarJuego(View v)
    {
        int opcionNivel = 0;
        int opcionEstilo = 0;
        switch(groupNivelJuego.getCheckedRadioButtonId())
        {
            case R.id.nivel3x3:
                opcionNivel = 1;
                break;
            case R.id.nivel4x4:
                opcionNivel = 2;
                break;
        }
        switch(groupEstiloGrafico.getCheckedRadioButtonId())
        {
            case R.id.estiloClasico:
                opcionEstilo = 1;
                break;
            case R.id.estiloGeometrico:
                opcionEstilo = 2;
                break;
        }
        String jugador1 = "";
        String jugador2 = "";

        if(!etJugador1.getText().toString().equals(""))
        {
            jugador1 = etJugador1.getText().toString();
        }
        if(!etJugador2.getText().toString().equals(""))
        {
            jugador2 = etJugador2.getText().toString();
        }


        Intent i = new Intent(this,JuegoActivity.class);
        i.putExtra("jugador1",jugador1);
        i.putExtra("jugador2",jugador2);
        i.putExtra("nivelJuego",opcionNivel);
        i.putExtra("estiloGrafico",opcionEstilo);
        startActivity(i);
        finish();
    }
}
