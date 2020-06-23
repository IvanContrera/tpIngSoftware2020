package com.scrumteam.tictactoe.interfaces;

public interface Sujeto
{
    public void registrarObservador(Observador observador);
    public void eliminarObservador(Observador observador);
    public void notificarObservadores();
}
