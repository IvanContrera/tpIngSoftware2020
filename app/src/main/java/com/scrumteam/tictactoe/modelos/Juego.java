package com.scrumteam.tictactoe.modelos;

import com.scrumteam.tictactoe.interfaces.Observador;
import com.scrumteam.tictactoe.interfaces.Sujeto;

import java.util.ArrayList;

public class Juego implements Sujeto
{
    int puntJ1,puntJ2,puntEmp,turno,ganador,numJugadas,lineaGanadora,tablero[][];
    ArrayList<String> nombreJugadores;
    ArrayList<Observador> observadores;         //Lista de suscriptores
    public Juego()
    {
        inicializarVariables();
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
            o.update(puntJ1,puntJ2,puntEmp,turno,ganador,nombreJugadores);
        }
    }

    void inicializarVariables()
    {
        observadores = new ArrayList<>();
        puntJ1 = 0;
        puntJ2 = 0;
        puntEmp = 0;
        turno = -1;
        ganador = -1;
        numJugadas = 0;
        lineaGanadora = -1;
        tablero = new int[3][3];        //Por defecto, se inicia un tablero 3x3
        nombreJugadores = new ArrayList<>();
        nombreJugadores.add("Jugador 1");
        nombreJugadores.add("Jugador 2");

        notificarObservadores();
    }

    /*Selecciona la dimension del tablero*/
    public void elegirTablero(int dimension)
    {
        tablero = new int[dimension][dimension];
        reiniciarTablero();
    }

    public void nuevaPartida()
    {
        turno = 1;
        ganador = -1;
        numJugadas = 0;
        lineaGanadora = -1;
        reiniciarTablero();
        notificarObservadores();
    }

    public void setNombres(String jugador1, String jugador2)
    {
        if(!jugador1.equals(""))
        {
            nombreJugadores.set(0,jugador1);
        }
        if(!jugador2.equals(""))
        {
            nombreJugadores.set(1,jugador2);
        }
        notificarObservadores();
    }

    public void reiniciarTablero()
    {
        for(int f = 0; f < tablero.length; f++)
        {
            for(int c = 0; c < tablero.length; c++)
            {
                tablero[f][c] = 0;
            }
        }
    }

    /*Hace una jugada. Recibe la fila y la columna*/
    public boolean hacerJugada(int filaElegida, int columnaElegida)
    {
        if((tablero[filaElegida][columnaElegida] == 0) && turno > 0)
        {
            tablero[filaElegida][columnaElegida] = turno;
            turno = 1 + turno%2;    //Alterno turno
            numJugadas++;
            checkJugadaGanadora(filaElegida,columnaElegida);  //Analizo tablero para saber si hay jugada ganadora
            notificarObservadores();
            return true;
        }
        else
        {
            return false;
        }
    }

    public void checkJugadaGanadora(int filaElegida, int columnaElegida)
    {
        if(checkJugadaHorizontal(filaElegida) || checkJugadaVertical(columnaElegida) || checkJugadaDiagPrincipal() || checkJugadaDiagInvertida())
        {
            ganador = tablero[filaElegida][columnaElegida];
            turno = 0;
            if(ganador == 1)
            {
                puntJ1++;
            }
            else
            {
                puntJ2++;
            }
        }
        else if(numJugadas == tablero.length*tablero.length)
        {
            ganador = 3;
            turno = 0;
            puntEmp++;
        }
    }

    public boolean checkJugadaHorizontal(int filaElegida)
    {
        boolean hayGanador = true;                  //valor inicial
        int jugador = tablero[filaElegida][0];      //coloca el numero de jugador
        for(int c = 1; c < tablero.length; c++)     //recorre cada posicion de la fila elegida a partir de la segunda
        {
            /*Si el valor del tablero en una posicion es 0 o distinta de la anterior,
            * se sabe que no hay ganador y se corta el chequeo*/
            if((tablero[filaElegida][c] != jugador) || tablero[filaElegida][c] == 0)
            {
                hayGanador = false;
                break;
            }
        }
        if(hayGanador)
        {
            lineaGanadora = filaElegida;
        }
        return hayGanador;
    }

    public boolean checkJugadaVertical(int columnaElegida)
    {
        boolean hayGanador = true;                  //valor inicial
        int jugador = tablero[0][columnaElegida];      //coloca el numero de jugador
        for(int f = 1; f < tablero.length; f++)     //recorre cada posicion de la fila elegida a partir de la segunda
        {
            /*Si el valor del tablero en una posicion es 0 o distinta de la anterior,
             * se sabe que no hay ganador y se corta el chequeo*/
            if((tablero[f][columnaElegida] != jugador) || tablero[f][columnaElegida] == 0)
            {
                hayGanador = false;
                break;
            }
        }
        if(hayGanador)
        {
            lineaGanadora = 4 + columnaElegida;
        }
        return hayGanador;
    }

    public boolean checkJugadaDiagPrincipal()
    {
        boolean hayGanador = true;                  //valor inicial
        int jugador = tablero[0][0];      //coloca el numero de jugador
        for(int i = 1; i < tablero.length; i++)
        {
            if((tablero[i][i] != jugador) || tablero[i][i] == 0)
            {
                hayGanador = false;
                break;
            }
        }
        if(hayGanador)
        {
            lineaGanadora = 8;
        }
        return hayGanador;
    }

    public int getLineaGanadora()
    {
        return lineaGanadora;
    }

    public boolean checkJugadaDiagInvertida()
    {
        boolean hayGanador = true;                  //valor inicial
        int n = tablero.length-1;
        int jugador = tablero[0][n];      //coloca el numero de jugador
        for(int i = 1; i < tablero.length; i++)
        {
            if((tablero[i][n-i] != jugador) || tablero[i][n-i] == 0)
            {
                hayGanador = false;
                break;
            }
        }
        if(hayGanador)
        {
            lineaGanadora = 9;
        }
        return hayGanador;
    }

    public int getTurno()
    {
        return turno;
    }

    public int getPuntuacionJ1()
    {
        return puntJ1;
    }

    public int getPuntuacionJ2()
    {
        return puntJ2;
    }

    public int getPuntEmpates()
    {
        return puntEmp;
    }

    public int getGanador() {
        return ganador;
    }

    public int[][] getTablero() {
        return tablero;
    }

    public ArrayList<String> getNombreJugadores() {
        return nombreJugadores;
    }

}
