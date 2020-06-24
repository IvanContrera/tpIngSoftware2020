package com.scrumteam.tictactoe;

import android.content.Context;

import com.scrumteam.tictactoe.cviews.PanelPuntuacionesView;
import com.scrumteam.tictactoe.cviews.TurneroView;
import com.scrumteam.tictactoe.modelos.Juego;

import org.junit.Test;
import org.junit.runner.RunWith;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class ProyectoIntegrationTest
{
    Context context = InstrumentationRegistry.getInstrumentation().getTargetContext();
    TurneroView turneroView = new TurneroView(context,null);
    PanelPuntuacionesView panelPuntuacionesView = new PanelPuntuacionesView(context,null);
    Juego juego = new Juego();

    /*Comprueba mensaje al iniciar una partida*/
    @Test
    public void getMensajeTurneroViewTurno1Test()
    {
        juego.registrarObservador(turneroView);
        juego.setNombres("Ivan","Milagros");
        juego.nuevaPartida();

        assertEquals("Juega: Ivan",turneroView.getMensaje());
    }

    /*Comprueba mensaje en la segunda jugada de una partida*/
    @Test
    public void getMensajeTurneroViewTurno2Test()
    {
        juego.registrarObservador(turneroView);
        juego.setNombres("Ivan","Milagros");
        juego.nuevaPartida();
        juego.hacerJugada(1,1);

        assertEquals("Juega: Milagros",turneroView.getMensaje());
    }

    /*Comprueba mensaje al ganar una partida el jugador 1*/
    @Test
    public void getMensajeTurneroViewGanaJ1Test()
    {
        juego.registrarObservador(turneroView);
        juego.setNombres("Ivan","Milagros");
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(1,1); //Segunda jugada J2
        juego.hacerJugada(0,1); //Tercera jugada J1
        juego.hacerJugada(1,2); //Cuarta jugada J2
        juego.hacerJugada(0,2); //Quinta jugada J1 (Hace jugada ganadora)


        assertEquals("Ganador: Ivan",turneroView.getMensaje());
    }

    /*Comprueba mensaje al ganar una partida el jugador 2*/
    @Test
    public void getMensajeTurneroViewGanaJ2Test()
    {
        juego.registrarObservador(turneroView);
        juego.setNombres("Ivan","Milagros");

        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(0,2); //Segunda jugada J2
        juego.hacerJugada(1,1); //Tercera jugada J1
        juego.hacerJugada(1,2); //Cuarta jugada J2
        juego.hacerJugada(0,1); //Quinta jugada J1
        juego.hacerJugada(2,2); //Sexta jugada J2 (Hace jugada ganadora)

        assertEquals("Ganador: Milagros",turneroView.getMensaje());
    }

    /*Comprueba mensaje al haber un empate*/
    @Test
    public void getMensajeTurneroViewEmpateTest()
    {
        juego.registrarObservador(turneroView);
        juego.setNombres("Ivan","Milagros");

        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(0,1); //Segunda jugada J2
        juego.hacerJugada(1,1); //Tercera jugada J1
        juego.hacerJugada(0,2); //Cuarta jugada J2
        juego.hacerJugada(1,2); //Quinta jugada J1
        juego.hacerJugada(1,0); //Sexta jugada J2
        juego.hacerJugada(2,0); //Septima jugada J1
        juego.hacerJugada(2,2); //Octava jugada J2
        juego.hacerJugada(2,1); //Novena jugada J1

        assertEquals("Empataron!!!",turneroView.getMensaje());
    }

    /*Comprueba puntaje de jugador 1 al ganar una partida*/
    @Test
    public void getPuntajeJug1Test()
    {
        juego.registrarObservador(panelPuntuacionesView);
        juego.setNombres("Ivan","Milagros");
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(1,1); //Segunda jugada J2
        juego.hacerJugada(0,1); //Tercera jugada J1
        juego.hacerJugada(1,2); //Cuarta jugada J2
        juego.hacerJugada(0,2); //Quinta jugada J1 (Hace jugada ganadora)

        assertEquals(1,panelPuntuacionesView.getPuntajeJug1());
    }

    /*Comprueba puntaje de jugador 2 al ganar una partida*/
    @Test
    public void getPuntajeJug2Test()
    {
        juego.registrarObservador(panelPuntuacionesView);
        juego.setNombres("Ivan","Milagros");
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(0,2); //Segunda jugada J2
        juego.hacerJugada(1,1); //Tercera jugada J1
        juego.hacerJugada(1,2); //Cuarta jugada J2
        juego.hacerJugada(0,1); //Quinta jugada J1
        juego.hacerJugada(2,2); //Sexta jugada J2 (Hace jugada ganadora)

        assertEquals(1,panelPuntuacionesView.getPuntajeJug2());
    }

    /*Comprueba la cantidad de empates al empatar una partida*/
    @Test
    public void getPuntajeEmpateTest()
    {
        juego.registrarObservador(panelPuntuacionesView);
        juego.setNombres("Ivan","Milagros");
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(0,1); //Segunda jugada J2
        juego.hacerJugada(1,1); //Tercera jugada J1
        juego.hacerJugada(0,2); //Cuarta jugada J2
        juego.hacerJugada(1,2); //Quinta jugada J1
        juego.hacerJugada(1,0); //Sexta jugada J2
        juego.hacerJugada(2,0); //Septima jugada J1
        juego.hacerJugada(2,2); //Octava jugada J2
        juego.hacerJugada(2,1); //Novena jugada J1

        assertEquals(1,panelPuntuacionesView.getPuntajeEmpate());
    }
}
