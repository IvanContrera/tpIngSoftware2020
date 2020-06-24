package com.scrumteam.tictactoe.cviews;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import com.scrumteam.tictactoe.R;
import com.scrumteam.tictactoe.interfaces.Observador;

import java.util.ArrayList;

import androidx.annotation.Nullable;

public class TurneroView extends LinearLayout implements Observador
{
    TextView txtInformacion;
    ArrayList<String> nombreJugadores;
    String mensaje;
    public TurneroView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.view_turnero,this,true);

        txtInformacion = findViewById(R.id.txtInformacion);
        nombreJugadores = new ArrayList<>();
        mensaje = "";

        /*Por defecto agrego nombres vacios, para evitar excepciones*/
        nombreJugadores.add("");
        nombreJugadores.add("");
    }

    /*Valores:  - puntJ1,puntJ2,puntEmp: >= 0
     *          - turno: 1,2 si se está jugando. -1 si no empezó el juego. 0 en otros casos
     *          - ganador: 1,2 si hubo un ganador. 3 si hubo empate. -1 si aun no hubo ganador
     *          - nombreJugadores: distinto de null, establecer nombre de jugadores. null, no hacer nada*/
    @Override
    public void update(int puntJ1, int puntJ2, int puntEmp, int turno, int ganador, ArrayList<String> nombreJugadores)
    {
        /*si turno es -1, aun no inicio el juego. Se asignan nombres*/
        if(!(nombreJugadores == null) && turno == -1)
        {
            if(nombreJugadores.size() == 2)
            {
                this.nombreJugadores = nombreJugadores;
            }
        }
        else if(turno != -1)   /*En otra instancia del juego*/
        {
            if((turno == 1 || turno == 2) && ganador == -1) //Se está jugando
            {
                mensaje = "Juega: " + this.nombreJugadores.get(turno-1);
            }

            if(ganador == 1 || ganador == 2)                //Uno de los jugadores ganó la partida
            {
                mensaje = "Ganador: " + this.nombreJugadores.get(ganador-1);
            }

            if(ganador == 3)                                //Hubo empate
            {
                mensaje = "Empataron!!!";
            }

            txtInformacion.setText(mensaje);
        }
    }

    public String getMensaje()
    {
        return mensaje;
    }
}
