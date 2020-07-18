package com.scrumteam.tictactoe.modelos;

import com.scrumteam.tictactoe.interfaces.Jugador;
import com.scrumteam.tictactoe.interfaces.Observador;
import com.scrumteam.tictactoe.interfaces.Sujeto;

import java.util.ArrayList;

public class JugadorLocal extends Jugador implements Observador
{
    public JugadorLocal(int orden)
    {
        super(orden);
    }

    @Override
    public void update(int puntJ1, int puntJ2, int puntEmp, int turno, int ganador, ArrayList<String> nombreJugadores,int[][] matrizJuego)
    {

    }
}
