package com.scrumteam.tictactoe.interfaces;

import java.util.ArrayList;

public abstract class Jugador implements Sujeto
{
    int fila,columna,orden;
    ArrayList<Observador> observadores;         //Lista de suscriptores

    public int getOrden() {
        return orden;
    }

    public Jugador(int orden)
    {
        fila = -1;
        columna = -1;
        observadores = new ArrayList<>();
        this.orden = orden;
    }

    @Override
    public void registrarObservador(Observador observador)
    {
        observadores.add(observador);
    }

    @Override
    public void eliminarObservador(Observador observador)
    {
        observadores.remove(observador);
    }

    @Override
    public void notificarObservadores()
    {
        for(Observador o : observadores)
        {
            o.update(-1,-1,-1,orden,-1,null,null);
        }
    }

    public void elegirMosaico(int fila, int columna)
    {
        this.fila = fila;
        this.columna = columna;
        notificarObservadores();
    }

    public int getFilaElegida()
    {
        int f = fila;
        fila = -1;
        return f;
    }


    public int getColumnaElegida()
    {
        int c = columna;
        columna = -1;
        return c;
    }
}
