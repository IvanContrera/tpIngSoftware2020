package com.scrumteam.tictactoe.interfaces;

import java.util.ArrayList;

public interface Observador
{
    /*Valores: - puntJ1,puntJ2,puntEmp: >= 0
    *          - turno: 1,2 si se está jugando. -1 si no empezó el juego. 0 en otros casos
    *          - ganador: 1,2 si hubo un ganador. -1 si aun no hubo ganador*/
    public void update(int puntJ1, int puntJ2, int puntEmp, int turno, int ganador, ArrayList<String> nombreJugadores);
}
