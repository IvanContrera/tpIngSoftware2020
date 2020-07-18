package com.scrumteam.tictactoe.hilos;

import android.os.AsyncTask;

import com.scrumteam.tictactoe.interfaces.Jugador;

public class AlgoritmoSistema extends AsyncTask<int[][],Void,Void>
{
    int[][] matrizJuego;
    Jugador jugador;
    int fila,columna;

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }

    public AlgoritmoSistema(Jugador jugador)
    {
        this.jugador = jugador;
        fila = -1;
        columna = -1;
    }

    @Override
    protected Void doInBackground(int[][]... matrizJuego)
    {
        this.matrizJuego = matrizJuego[0];
        try
        {
            Thread.sleep(500);
            hacerJugada();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid)
    {
        super.onPostExecute(aVoid);
        jugador.elegirMosaico(fila,columna);
    }

    public void hacerJugada()
    {
        int turno = jugador.getOrden();
        int turnoRival = 1 + turno%2;
        boolean encontroJugada;
        /*Intenta buscar jugadas ganadoras propias*/
        encontroJugada = encontrarJugadaGanadoraHorizontal(turno,matrizJuego);

        if(!encontroJugada)
        {
            encontroJugada = encontrarJugadaGanadoraVertical(turno,matrizJuego);
        }
        if(!encontroJugada)
        {
            encontroJugada = encontrarJugadaGanadoraDiagonal(turno,matrizJuego);
        }
        if(!encontroJugada)
        {
            encontroJugada = encontrarJugadaGanadoraDiagonalInv(turno,matrizJuego);
        }

        /*Intenta buscar posibles jugadas ganadoras del rival*/
        if(!encontroJugada)
        {
            encontroJugada = encontrarJugadaGanadoraHorizontal(turnoRival,matrizJuego);
        }
        if(!encontroJugada)
        {
            encontroJugada = encontrarJugadaGanadoraVertical(turnoRival,matrizJuego);
        }
        if(!encontroJugada)
        {
            encontroJugada = encontrarJugadaGanadoraDiagonal(turnoRival,matrizJuego);
        }
        if(!encontroJugada)
        {
            encontroJugada = encontrarJugadaGanadoraDiagonalInv(turnoRival,matrizJuego);
        }

        /*Hace jugada central*/
        if(!encontroJugada)
        {
            encontroJugada = hacerJugadaCentro(turno,matrizJuego);
        }

        /*Hace jugadas en la esquina*/
        if(!encontroJugada)
        {
            encontroJugada = hacerJugadaEnEsquina(turno,matrizJuego);
        }


        if(!encontroJugada)
        {
            encontrarMosaicoVacio(matrizJuego);
        }

    }
    /*Intenta buscar una situacion en la que se esté a una jugada de ganar la partida*/
    public boolean encontrarJugadaGanadoraHorizontal(int jugadorNro,int[][] matrizJuego)
    {
        /*Recorrido horizontal*/
        for(int f = 0; f < matrizJuego.length; f++)
        {
            boolean mosaicoVacio = false;
            for(int c = 0; c < matrizJuego.length; c++)
            {
                if(matrizJuego[f][c] != jugadorNro && matrizJuego[f][c] != 0)     //no es posible que jugadorNro tenga jugada ganadora En la linea actual. Se encontro ficha del rival
                {
                    break;
                }
                if(matrizJuego[f][c] == 0 && !mosaicoVacio)
                {
                    mosaicoVacio = true;
                    fila = f;
                    columna = c;
                }
                else if(matrizJuego[f][c] == 0 && mosaicoVacio)
                {
                    break;
                }
                /*Si llega al final del recorrido con mosaicoVacio = true y ejecuta el siguiente
                * codigo, significa que ninguna de las condiciones anteriores corto el bucle.
                * Existe una posible jugada ganadora*/
                if(c == (matrizJuego.length-1) && mosaicoVacio)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean encontrarJugadaGanadoraVertical(int jugadorNro,int[][] matrizJuego)
    {
        /*Recorrido horizontal*/
        for(int c = 0; c < matrizJuego.length; c++)
        {
            boolean mosaicoVacio = false;
            for(int f = 0; f < matrizJuego.length; f++)
            {
                if(matrizJuego[f][c] != jugadorNro && matrizJuego[f][c] != 0)     //no es posible que jugadorNro tenga jugada ganadora En la linea actual. Se encontro ficha del rival
                {
                    break;
                }
                if(matrizJuego[f][c] == 0 && !mosaicoVacio)
                {
                    mosaicoVacio = true;
                    fila = f;
                    columna = c;
                }
                else if(matrizJuego[f][c] == 0 && mosaicoVacio)
                {
                    break;
                }
                /*Si llega al final del recorrido con mosaicoVacio = true y ejecuta el siguiente
                 * codigo, significa que ninguna de las condiciones anteriores corto el bucle.
                 * Existe una posible jugada ganadora*/
                if(f == (matrizJuego.length-1) && mosaicoVacio)
                {
                    return true;
                }
            }
        }
        return false;
    }

    public void encontrarMosaicoVacio(int[][] matrizJuego)
    {
        for(int f = 0; f < matrizJuego.length; f++)
        {
            for(int c = 0; c < matrizJuego.length; c++)
            {
                if(matrizJuego[f][c] == 0)
                {
                    fila = f;
                    columna  = c;
                    return;
                }
            }
        }
    }

    public boolean encontrarJugadaGanadoraDiagonal(int jugadorNro,int[][] matrizJuego)
    {
        boolean mosaicoVacio = false;
        for(int f = 0; f < matrizJuego.length; f++)
        {
            if(matrizJuego[f][f] != jugadorNro && matrizJuego[f][f] != 0)     //no es posible que jugadorNro tenga jugada ganadora En la linea actual. Se encontro ficha del rival
            {
                break;
            }
            if(matrizJuego[f][f] == 0 && !mosaicoVacio)
            {
                mosaicoVacio = true;
                fila = f;
                columna = f;
            }
            else if(matrizJuego[f][f] == 0 && mosaicoVacio)
            {
                break;
            }
            /*Si llega al final del recorrido con mosaicoVacio = true y ejecuta el siguiente
             * codigo, significa que ninguna de las condiciones anteriores corto el bucle.
             * Existe una posible jugada ganadora*/
            if(f == (matrizJuego.length-1) && mosaicoVacio)
            {
                return true;
            }
        }
        return false;
    }

    public boolean encontrarJugadaGanadoraDiagonalInv(int jugadorNro,int[][] matrizJuego)
    {
        boolean mosaicoVacio = false;
        int dim = matrizJuego.length;
        for(int f = 0; f < dim; f++)
        {
            if(matrizJuego[f][dim-1-f] != jugadorNro && matrizJuego[f][dim-1-f] != 0)     //no es posible que jugadorNro tenga jugada ganadora En la linea actual. Se encontro ficha del rival
            {
                break;
            }
            if(matrizJuego[f][dim-1-f] == 0 && !mosaicoVacio)
            {
                mosaicoVacio = true;
                fila = f;
                columna = dim-1-f;
            }
            else if(matrizJuego[f][dim-1-f] == 0 && mosaicoVacio)
            {
                break;
            }
            /*Si llega al final del recorrido con mosaicoVacio = true y ejecuta el siguiente
             * codigo, significa que ninguna de las condiciones anteriores corto el bucle.
             * Existe una posible jugada ganadora*/
            if(f == (matrizJuego.length-1) && mosaicoVacio)
            {
                return true;
            }
        }
        return false;
    }

    public boolean hacerJugadaEnEsquina(int jugadorNro,int[][] matrizJuego)
    {
        if(matrizJuego[0][0] == 0)
        {
            fila = 0;
            columna = 0;
            return true;
        }
        else if(matrizJuego[matrizJuego.length-1][matrizJuego.length-1] == 0)
        {
            fila = matrizJuego.length-1;
            columna = matrizJuego.length-1;
            return true;
        }
        else if(matrizJuego[matrizJuego.length-1][0] == 0)
        {
            fila = matrizJuego.length-1;
            columna = 0;
            return true;
        }
        else if(matrizJuego[0][matrizJuego.length-1] == 0)
        {
            fila = 0;
            columna = matrizJuego.length-1;
            return true;
        }
        return false;
    }

    public boolean hacerJugadaCentro(int jugadorNro,int[][] matrizJuego)
    {
        int turnoRival = 1 + jugadorNro%2;
        if(matrizJuego[matrizJuego.length/2][matrizJuego.length/2] == 0 && encontrarJugada(turnoRival,matrizJuego) && !encontrarJugada(jugadorNro,matrizJuego))
        {
            fila = matrizJuego.length/2;
            columna = matrizJuego.length/2;
            return true;
        }
        return false;
    }

    /*Busca alguna jugada de un determinado jugador*/
    public boolean encontrarJugada(int jugadorNro,int[][] matrizJuego)
    {
        for(int f = 0; f < matrizJuego.length; f++)
        {
            for(int c = 0; c < matrizJuego.length; c++)
            {
                if(matrizJuego[f][c] == jugadorNro)
                {
                    return true;
                }
            }
        }
        return false;
    }
}
