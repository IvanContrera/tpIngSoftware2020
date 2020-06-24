package com.scrumteam.tictactoe.strategy;

import com.scrumteam.tictactoe.R;
import com.scrumteam.tictactoe.interfaces.EstiloGrafico;

public class EstiloGeometrico implements EstiloGrafico
{
    int ficha1,ficha2;

    public EstiloGeometrico()
    {
        ficha1 = R.drawable.ic_jugador1_geometrico;
        ficha2 = R.drawable.ic_jugador2_geometrico;
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
