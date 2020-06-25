package com.scrumteam.tictactoe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.scrumteam.tictactoe.R;

public class InicioActivity extends AppCompatActivity {

    private Button botonAcercaDe,botonJugar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio);
        botonJugar = findViewById(R.id.button);
        botonAcercaDe= findViewById(R.id.button2);


        Animation aparecer_btnJugar = AnimationUtils.loadAnimation(this,R.anim.aparecer_boton1);
        Animation aparecer_btnAcercaDe = AnimationUtils.loadAnimation(this,R.anim.aparecer_boton2);
        botonJugar.startAnimation(aparecer_btnJugar);
        botonAcercaDe.startAnimation(aparecer_btnAcercaDe);
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
