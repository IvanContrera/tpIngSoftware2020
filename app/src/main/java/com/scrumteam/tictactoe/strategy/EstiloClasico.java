package com.scrumteam.tictactoe.strategy;

import com.scrumteam.tictactoe.R;
import com.scrumteam.tictactoe.interfaces.EstiloGrafico;

public class EstiloClasico implements EstiloGrafico
{
    int ficha1,ficha2;

    public EstiloClasico()
    {
        ficha1 = R.drawable.ic_jugador1_clasico;
        ficha2 = R.drawable.ic_jugador2_clasico;
    }


    @Override
    public int getFicha1() {
        return ficha1;
    }

    @Override
    public int getFicha2() {
        return ficha2;
    }
}
