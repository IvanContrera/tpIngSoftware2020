package com.scrumteam.tictactoe.modelos;

import com.scrumteam.tictactoe.hilos.AlgoritmoSistema;
import com.scrumteam.tictactoe.interfaces.Jugador;
import com.scrumteam.tictactoe.interfaces.Observador;
import com.scrumteam.tictactoe.interfaces.Sujeto;

import java.util.ArrayList;
import java.util.jar.JarEntry;

public class JugadorSistema extends Jugador implements Observador
{
    int orden;
    public JugadorSistema(int orden)
    {
        super(orden);
        this.orden = orden;
    }

    @Override
    public void update(int puntJ1, int puntJ2, int puntEmp, int turno, int ganador, ArrayList<String> nombreJugadores,int[][] matrizJuego)
    {
        if(turno == orden)
        {
            AlgoritmoSistema a1 = new AlgoritmoSistema(this);
            a1.execute(matrizJuego);

        }
    }

}
