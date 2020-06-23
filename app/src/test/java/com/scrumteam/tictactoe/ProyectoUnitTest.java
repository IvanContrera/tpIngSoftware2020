package com.scrumteam.tictactoe;

import android.util.Log;
import android.widget.ImageView;

import com.scrumteam.tictactoe.modelos.Juego;

import org.junit.Test;

import java.util.ArrayList;
import java.util.logging.LoggingMXBean;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ProyectoUnitTest
{
    /*@Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }*/

    Juego juego = new Juego();

    /*@Test
    public void nuevaPartidaTest()
    {

        juego.nuevaPartida();

        assertTrue((juego.getTurno() == 1) && (juego.getGanador() == 0));
    }*/

    @Test
    public void setNombresNoNulosTest()
    {
        juego.setNombres("Ivan","Milagros");
        ArrayList<String> nombres = juego.getNombreJugadores();
        assertTrue((nombres.get(0) == "Ivan") && (nombres.get(1) == "Milagros"));
    }

    @Test
    public void setNombresNulosTest()
    {
        juego.setNombres("","");
        ArrayList<String> nombres = juego.getNombreJugadores();
        assertTrue((nombres.get(0) == "Jugador 1") && (nombres.get(1) == "Jugador 2"));
    }

    /*Comprobar si la primera jugada se almacena correctamente en la matriz*/
    @Test
    public void hacerJugadaNro1Test()
    {
        juego.nuevaPartida();
        juego.hacerJugada(1,2);
        int[][]tablero = juego.getTablero();
        assertEquals(1,tablero[1][2]);
    }

    /*Comprobar si la segunda jugada distinta a la primera
     * registra la jugada del jugador 2 y se conserva el mismo valor
     * de la primera*/
    @Test
    public void hacerJugadaNro2DistintaTest()
    {
        juego.nuevaPartida();
        juego.hacerJugada(1,2); //Primera jugada
        juego.hacerJugada(0,1); //Segunda jugada
        int[][]tablero = juego.getTablero();
        assertTrue(tablero[1][2] == 1 && tablero[0][1] == 2);
    }

    /*La tercera jugada distinta a las otras 2 debe registrar nuevamente
    * como hecha por el jugador 1*/
    @Test
    public void hacerJugadaNro3DistintaTest()
    {
        juego.nuevaPartida();
        juego.hacerJugada(1,2); //Primera jugada
        juego.hacerJugada(0,1); //Segunda jugada
        juego.hacerJugada(1,1); //Tercera jugada
        int[][]tablero = juego.getTablero();
        assertTrue(tablero[1][1] == 1);
    }

    /*Hago la tercera jugada igual que la segunda, se debe probar
    * que el valor no se actualiza*/
    @Test
    public void hacerJugadaNro3RepetidaTest()
    {
        juego.nuevaPartida();
        juego.hacerJugada(1,2); //Primera jugada
        juego.hacerJugada(0,1); //Segunda jugada
        juego.hacerJugada(0,1); //Tercera jugada (igual a la segunda)
        int[][]tablero = juego.getTablero();
        assertTrue(tablero[0][1] == 2);
    }

    /*Se hacen las primeras dos jugadas, por lo que no hay jugada ganadora.*/
    @Test
    public void getGanadorJugadaNoGanadoraTest()
    {
        juego.nuevaPartida();
        juego.hacerJugada(1,2); //Primera jugada
        juego.hacerJugada(0,1); //Segunda jugada
        assertEquals(-1,juego.getGanador());
    }



    /*Se compruba con algunas jugadas que no hay linea horizontal
    * ganadora*/
    @Test
    public void checkJugadaHorizontalFalse()
    {
        juego.nuevaPartida();
        juego.hacerJugada(0,1); //Primera jugada J1
        juego.hacerJugada(0,0); //Segunda jugada J2
        juego.hacerJugada(0,2); //Tercera jugada J1
        juego.hacerJugada(1,0); //Cuarta jugada J2
        juego.hacerJugada(1,1); //Segunda jugada J2
        assertFalse(juego.checkJugadaHorizontal(1)); //se comprueba fila 1
    }

    /*Se compruba con algunas jugadas que sí hay linea horizontal
     * ganadora*/
    @Test
    public void checkJugadaHorizontalTrue()
    {
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(1,1); //Segunda jugada J2
        juego.hacerJugada(0,1); //Tercera jugada J1
        juego.hacerJugada(1,2); //Cuarta jugada J2
        juego.hacerJugada(0,2); //Quinta jugada J1 (Hace jugada ganadora)

        assertTrue(juego.checkJugadaHorizontal(0)); //se comprueba fila 0
    }

    /*Serie de jugadas que no conducen a una jugada ganadora vertical*/
    @Test
    public void checkJugadaVerticalFalse()
    {
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(0,2); //Segunda jugada J2
        juego.hacerJugada(0,1); //Tercera jugada J1
        juego.hacerJugada(1,1); //Cuarta jugada J2
        juego.hacerJugada(1,2); //Quinta jugada J1
        assertFalse(juego.checkJugadaVertical(2)); //se comprueba columna 2
    }

    /*Serie de jugadas que sí conducen a una jugada ganadora vertical*/
    @Test
    public void checkJugadaVerticalTrue()
    {
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(0,2); //Segunda jugada J2
        juego.hacerJugada(1,1); //Tercera jugada J1
        juego.hacerJugada(1,2); //Cuarta jugada J2
        juego.hacerJugada(0,1); //Quinta jugada J1
        juego.hacerJugada(2,2); //Sexta jugada J2
        assertTrue(juego.checkJugadaVertical(2)); //se comprueba columna 2
    }

    /*Con una series de jugadas, se comprueba que no haya jugada
    * ganadora en la diagonal principal*/
    @Test
    public void checkJugadaDiagPrincipalFalse()
    {
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(0,2); //Segunda jugada J2
        juego.hacerJugada(1,1); //Tercera jugada J1
        juego.hacerJugada(1,2); //Cuarta jugada J2
        juego.hacerJugada(0,1); //Quinta jugada J1
        juego.hacerJugada(2,2); //Sexta jugada J2
        assertFalse(juego.checkJugadaDiagPrincipal()); //se comprueba diagonal principal
    }

    /*Con una series de jugadas, se comprueba que sí haya jugada
     * ganadora en la diagonal principal*/
    @Test
    public void checkJugadaDiagPrincipalTrue()
    {
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(0,2); //Segunda jugada J2
        juego.hacerJugada(1,1); //Tercera jugada J1
        juego.hacerJugada(1,2); //Cuarta jugada J2
        juego.hacerJugada(2,2); //Quinta jugada J1
        assertTrue(juego.checkJugadaDiagPrincipal()); //se comprueba diagonal principal
    }

    /*Con una series de jugadas, se comprueba que no haya jugada
     * ganadora en la diagonal invertida*/
    @Test
    public void checkJugadaDiagInvertidaFalse()
    {
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(0,2); //Segunda jugada J2
        juego.hacerJugada(1,1); //Tercera jugada J1
        juego.hacerJugada(1,2); //Cuarta jugada J2
        juego.hacerJugada(2,2); //Quinta jugada J1
        assertFalse(juego.checkJugadaDiagInvertida()); //se comprueba diagonal invertida
    }

    /*Con una series de jugadas, se comprueba que no haya jugada
     * ganadora en la diagonal invertida*/
    @Test
    public void checkJugadaDiagInvertidaTrue()
    {
        juego.nuevaPartida();
        juego.hacerJugada(0,2); //Primera jugada J1
        juego.hacerJugada(0,1); //Segunda jugada J2
        juego.hacerJugada(1,1); //Tercera jugada J1
        juego.hacerJugada(1,2); //Cuarta jugada J2
        juego.hacerJugada(2,0); //Quinta jugada J1
        assertTrue(juego.checkJugadaDiagInvertida()); //se comprueba diagonal invertida
    }

    /*Jugador 1 gana una partida. Se comprueba que se almacena
    * el ganador correcto*/
    @Test
    public void getGanadorJugadaGanadoraJ1Test()
    {
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(1,1); //Segunda jugada J2
        juego.hacerJugada(0,1); //Tercera jugada J1
        juego.hacerJugada(1,2); //Cuarta jugada J2
        juego.hacerJugada(0,2); //Quinta jugada J1 (Hace jugada ganadora)


        assertEquals(1,juego.getGanador());
    }

    /*Jugador 1 gana una partida. Se comprueba que se almacena
     * el ganador correcto*/
    @Test
    public void getGanadorJugadaGanadoraJ2Test()
    {
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(0,2); //Segunda jugada J2
        juego.hacerJugada(1,1); //Tercera jugada J1
        juego.hacerJugada(1,2); //Cuarta jugada J2
        juego.hacerJugada(0,1); //Quinta jugada J1
        juego.hacerJugada(2,2); //Sexta jugada J2

        assertEquals(2,juego.getGanador());
    }

    @Test
    public void getGanadorJugadaEmpateTest()
    {
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(0,1); //Segunda jugada J2
        juego.hacerJugada(1,1); //Tercera jugada J1
        juego.hacerJugada(0,2); //Cuarta jugada J2
        juego.hacerJugada(1,2); //Quinta jugada J1
        juego.hacerJugada(1,0); //Sexta jugada J2
        juego.hacerJugada(2,0); //Septima jugada J1
        juego.hacerJugada(2,2); //Octava jugada J2
        juego.hacerJugada(2,1); //Novena jugada J1

        assertEquals(3,juego.getGanador());
    }

    /*Haciendo 2 partidas, gana las 2 el Jug 1. Probar que tiene
    * el puntaje indicado*/
    @Test
    public void getPuntuacionJ12PartidasGanTest()
    {
        /*Partida 1*/
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(1,1); //Segunda jugada J2
        juego.hacerJugada(0,1); //Tercera jugada J1
        juego.hacerJugada(1,2); //Cuarta jugada J2
        juego.hacerJugada(0,2); //Quinta jugada J1 (Hace jugada ganadora)
        /*Partida 2*/
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(1,1); //Segunda jugada J2
        juego.hacerJugada(0,1); //Tercera jugada J1
        juego.hacerJugada(1,2); //Cuarta jugada J2
        juego.hacerJugada(0,2); //Quinta jugada J1 (Hace jugada ganadora)

        assertEquals(2,juego.getPuntuacionJ1());
    }

    /*Haciendo 2 partidas, gana las 2 el Jug 2. Probar que tiene
     * el puntaje indicado*/
    @Test
    public void getPuntuacionJ22PartidasGanTest()
    {
        /*Partida 1*/
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(0,2); //Segunda jugada J2
        juego.hacerJugada(1,1); //Tercera jugada J1
        juego.hacerJugada(1,2); //Cuarta jugada J2
        juego.hacerJugada(0,1); //Quinta jugada J1
        juego.hacerJugada(2,2); //Sexta jugada J2
        /*Partida 2*/
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(0,2); //Segunda jugada J2
        juego.hacerJugada(1,1); //Tercera jugada J1
        juego.hacerJugada(1,2); //Cuarta jugada J2
        juego.hacerJugada(0,1); //Quinta jugada J1
        juego.hacerJugada(2,2); //Sexta jugada J2

        assertEquals(2,juego.getPuntuacionJ2());
    }

    @Test
    public void getPuntEmpates2PartidasTest()
    {
        /*Partida 1*/
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(0,1); //Segunda jugada J2
        juego.hacerJugada(1,1); //Tercera jugada J1
        juego.hacerJugada(0,2); //Cuarta jugada J2
        juego.hacerJugada(1,2); //Quinta jugada J1
        juego.hacerJugada(1,0); //Sexta jugada J2
        juego.hacerJugada(2,0); //Septima jugada J1
        juego.hacerJugada(2,2); //Octava jugada J2
        juego.hacerJugada(2,1); //Novena jugada J1
        /*Partida 2*/
        juego.nuevaPartida();
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(0,1); //Segunda jugada J2
        juego.hacerJugada(1,1); //Tercera jugada J1
        juego.hacerJugada(0,2); //Cuarta jugada J2
        juego.hacerJugada(1,2); //Quinta jugada J1
        juego.hacerJugada(1,0); //Sexta jugada J2
        juego.hacerJugada(2,0); //Septima jugada J1
        juego.hacerJugada(2,2); //Octava jugada J2
        juego.hacerJugada(2,1); //Novena jugada J1

        assertEquals(2,juego.getPuntEmpates());
    }

    @Test
    public void getLineaGanadoraTest()
    {
        juego.nuevaPartida();
        juego.elegirTablero(4);
        juego.hacerJugada(0,0); //Primera jugada J1
        juego.hacerJugada(0,3); //Segunda jugada J2
        juego.hacerJugada(1,1); //Tercera jugada J1
        juego.hacerJugada(1,3); //Cuarta jugada J2
        juego.hacerJugada(0,1); //Quinta jugada J1
        juego.hacerJugada(2,3); //Sexta jugada J2
        juego.hacerJugada(2,1); //Septima jugada J1
        juego.hacerJugada(3,3); //Octava jugada J2

        assertEquals(7,juego.getLineaGanadora());
    }
}