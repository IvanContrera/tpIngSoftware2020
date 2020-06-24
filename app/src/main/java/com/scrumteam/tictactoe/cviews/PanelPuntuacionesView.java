package com.scrumteam.tictactoe.cviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.scrumteam.tictactoe.R;
import com.scrumteam.tictactoe.interfaces.Observador;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class PanelPuntuacionesView extends LinearLayout implements Observador
{
    TextView txtNombreJug1,txtNombreEmpates,txtNombreJug2,txtValorPuntJug1,txtValorPuntEmp,txtValorPuntJug2;
    public PanelPuntuacionesView(Context context, @Nullable AttributeSet attrs)
    {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_panelpuntuaciones,this,true);

        /*Obtengo las instancias de los TextView*/
        txtNombreJug1 = findViewById(R.id.txtNombreJug1);
        txtNombreEmpates = findViewById(R.id.txtNombreEmpates);
        txtNombreJug2 = findViewById(R.id.txtNombreJug2);
        txtValorPuntJug1 = findViewById(R.id.txtValorPuntJug1);
        txtValorPuntEmp = findViewById(R.id.txtValorPuntEmp);
        txtValorPuntJug2 = findViewById(R.id.txtValorPuntJug2);
    }

    /*Valores:  - puntJ1,puntJ2,puntEmp: >= 0
     *          - turno: 1,2 si se está jugando. -1 si no empezó el juego. 0 en otros casos
     *          - ganador: 1,2 si hubo un ganador. -1 si aun no hubo ganador
     *          - nombreJugadores: distinto de null, establecer nombre de jugadores. null, no hacer nada*/
    @Override
    public void update(int puntJ1, int puntJ2, int puntEmp, int turno, int ganador, ArrayList<String> nombreJugadores)
    {
        /*si turno es -1, aun no inicio el juego. Se asignan nombres*/
        if(!(nombreJugadores == null) && turno == -1)
        {
            txtNombreJug1.setText(nombreJugadores.get(0));
            txtNombreJug2.setText(nombreJugadores.get(1));
        }

        /*Actualizo las puntuaciones*/
        txtValorPuntJug1.setText(Integer.toString(puntJ1));
        txtValorPuntJug2.setText(Integer.toString(puntJ2));
        txtValorPuntEmp.setText(Integer.toString(puntEmp));
    }

    public int getPuntajeJug1()
    {
        return Integer.parseInt(txtValorPuntJug1.getText().toString());
    }

    public int getPuntajeJug2()
    {
        return Integer.parseInt(txtValorPuntJug2.getText().toString());
    }

    public int getPuntajeEmpate()
    {
        return Integer.parseInt(txtValorPuntEmp.getText().toString());
    }
}
