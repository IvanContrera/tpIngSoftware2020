package com.scrumteam.tictactoe.strategy;

import android.widget.ImageView;

import com.scrumteam.tictactoe.interfaces.EstiloGrafico;

import java.util.ArrayList;

public class ConjuntoFichas
{
    ArrayList<ImageView> mosaicos;
    //EstiloGrafico estiloGrafico;
    int ficha1,ficha2;

    public ConjuntoFichas(ArrayList<ImageView> mosaicos)
    {
        this.mosaicos = mosaicos;
    }

    public void setEstilo(EstiloGrafico estiloGrafico)
    {
        //this.estiloGrafico = estiloGrafico;
        ficha1 = estiloGrafico.getFicha1();
        ficha2 = estiloGrafico.getFicha2();
    }

    public boolean setFicha1(int posicion)
    {
        if(posicion >= 0 && posicion < mosaicos.size())
        {
            mosaicos.get(posicion).setImageResource(ficha1);
            return true;
        }
        else
        {
            return false;
        }
    }

    public boolean setFicha2(int posicion)
    {
        if(posicion >= 0 && posicion < mosaicos.size())
        {
            mosaicos.get(posicion).setImageResource(ficha2);
            return true;
        }
        else
        {
            return false;
        }
    }
}
