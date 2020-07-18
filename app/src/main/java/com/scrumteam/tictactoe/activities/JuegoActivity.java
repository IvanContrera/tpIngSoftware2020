package com.scrumteam.tictactoe.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.scrumteam.tictactoe.R;
import com.scrumteam.tictactoe.cviews.PanelPuntuacionesView;
import com.scrumteam.tictactoe.cviews.TurneroView;
import com.scrumteam.tictactoe.interfaces.Jugador;
import com.scrumteam.tictactoe.interfaces.Observador;
import com.scrumteam.tictactoe.interfaces.Sujeto;
import com.scrumteam.tictactoe.modelos.Juego;
import com.scrumteam.tictactoe.modelos.JugadorLocal;
import com.scrumteam.tictactoe.modelos.JugadorSistema;
import com.scrumteam.tictactoe.strategy.ConjuntoFichas;
import com.scrumteam.tictactoe.strategy.EstiloClasico;
import com.scrumteam.tictactoe.strategy.EstiloGeometrico;

import java.util.ArrayList;

public class JuegoActivity extends AppCompatActivity implements Observador {

    protected PowerManager.WakeLock wakelock;       //para mantener la pantalla activa
    ArrayList<ImageView> mosaicos;
    ArrayList<ImageView> lineasGanadoras;
    ArrayList<Observador> observadores;         //Lista de suscriptores
    ConjuntoFichas conjuntoFichas;
    TurneroView turneroView;
    PanelPuntuacionesView panelPuntuacionesView;
    Juego juego;
    Button btnNuevaPartida,btnTerminarJuego;
    Bundle datosRecibidos;
    Jugador jugador1,jugador2;
    int dimensionElegida,estiloElegido;

    /*solo para pruebas*/
    int turno;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_juego);

        /*Inicializo variables*/
        mosaicos = new ArrayList<>();
        lineasGanadoras = new ArrayList<>();
        cargarMosaicos();
        cargarLineasGanadoras();
        conjuntoFichas = new ConjuntoFichas(mosaicos);
        turneroView = findViewById(R.id.turnero);
        btnNuevaPartida = findViewById(R.id.btnNuevaPartida);
        btnTerminarJuego = findViewById(R.id.btnTerminarJuego);
        panelPuntuacionesView = findViewById(R.id.panel);
        datosRecibidos = getIntent().getExtras();
        observadores = new ArrayList<>();


        btnTerminarJuego.setVisibility(View.INVISIBLE);
        btnNuevaPartida.setVisibility(View.INVISIBLE);

        /*por ahora es solo de prueba*/
        jugador1 = new JugadorLocal(1);
        jugador2 = new JugadorSistema(2);
        jugador1.registrarObservador(this);
        jugador2.registrarObservador(this);


        //solo para prueba, despues de deberan extraer de la actividad anterior
        //dimensionElegida = 2;
        //estiloElegido = 2;

        //conjuntoFichas.setEstilo(new EstiloClasico());
        //selectTablero3x3();
        /*turno = 1;
        ArrayList<String> nombres = new ArrayList<>();
        nombres.add("Ivan");
        nombres.add("Milagros");
        turneroView.update(0,0,0,1,-1,nombres);
        turneroView.update(0,0,0,1,-1,null);*/

        /*Configuracion inicial del juego*/
        juego = new Juego();
        juego.registrarObservador(turneroView);
        juego.registrarObservador(panelPuntuacionesView);
        juego.registrarObservador((Observador)jugador1);
        juego.registrarObservador((Observador)jugador2);

        elegirTablero(datosRecibidos.getInt("nivelJuego"));       //descomentar cuando este completo el proyecto
        //elegirEstiloFichas(estiloElegido);
        elegirEstiloFichas(datosRecibidos.getInt("estiloGrafico"));
        juego.setNombres(datosRecibidos.getString("jugador1"),datosRecibidos.getString("jugador2"));  //descomentar cuando este completo el proyecto
        juego.nuevaPartida();

    }

    /*Obtengo la instancia de cada objeto ImageView del tablero*/
    public void cargarMosaicos()
    {
        mosaicos.add((ImageView)findViewById(R.id.imageView0));
        mosaicos.add((ImageView)findViewById(R.id.imageView1));
        mosaicos.add((ImageView)findViewById(R.id.imageView2));
        mosaicos.add((ImageView)findViewById(R.id.imageView3));
        mosaicos.add((ImageView)findViewById(R.id.imageView4));
        mosaicos.add((ImageView)findViewById(R.id.imageView5));
        mosaicos.add((ImageView)findViewById(R.id.imageView6));
        mosaicos.add((ImageView)findViewById(R.id.imageView7));
        mosaicos.add((ImageView)findViewById(R.id.imageView8));
        mosaicos.add((ImageView)findViewById(R.id.imageView9));
        mosaicos.add((ImageView)findViewById(R.id.imageView10));
        mosaicos.add((ImageView)findViewById(R.id.imageView11));
        mosaicos.add((ImageView)findViewById(R.id.imageView12));
        mosaicos.add((ImageView)findViewById(R.id.imageView13));
        mosaicos.add((ImageView)findViewById(R.id.imageView14));
        mosaicos.add((ImageView)findViewById(R.id.imageView15));
    }

    public void cargarLineasGanadoras()
    {
        lineasGanadoras.add((ImageView) findViewById(R.id.imgFila0));
        lineasGanadoras.add((ImageView) findViewById(R.id.imgFila1));
        lineasGanadoras.add((ImageView) findViewById(R.id.imgFila2));
        lineasGanadoras.add((ImageView) findViewById(R.id.imgFila3));
        lineasGanadoras.add((ImageView) findViewById(R.id.imgColumna0));
        lineasGanadoras.add((ImageView) findViewById(R.id.imgColumna1));
        lineasGanadoras.add((ImageView) findViewById(R.id.imgColumna2));
        lineasGanadoras.add((ImageView) findViewById(R.id.imgColumna3));
        lineasGanadoras.add((ImageView) findViewById(R.id.imgDiagPrinc));
        lineasGanadoras.add((ImageView) findViewById(R.id.imgDiagInv));
    }

    void elegirTablero(int opcion)
    {
        switch(opcion)
        {
            case 1:
                selectTablero3x3();
                break;
            case 2:
                selectTablero4x4();
                break;
        }
    }

    void elegirEstiloFichas(int opcion)
    {
        switch(opcion)
        {
            case 1:
                conjuntoFichas.setEstilo(new EstiloClasico());
                break;
            case 2:
                conjuntoFichas.setEstilo(new EstiloGeometrico());
                break;
        }
    }

    /*Evento llamado al tocar un mosaico del tablero de juego*/
    public void clickTablero(View v)
    {
        Jugador jugadorActual = null;
        int mosaicoSeleccionado = v.getId();

        /*Se elige el jugador correcto siempre que sea local*/
        if(juego.getTurno() == 1 && (jugador1 instanceof JugadorLocal))
        {
            jugadorActual = jugador1;
        }
        else if(juego.getTurno() == 2 && (jugador2 instanceof JugadorLocal))
        {
            jugadorActual = jugador2;
        }

        //boolean jugadaValida;
        //turno = 1 + turno%2; // mas adelante lo determinará la clase juego
        //turno = juego.getTurno();
        //turneroView.update(0,0,0,turno,-1,null);//PRUEBA

        if(jugadorActual != null)
        {
            switch(mosaicoSeleccionado)
            {
                case R.id.imageView0:
                    //hacerJugada(0,0,0);
                    jugadorActual.elegirMosaico(0,0);
                    break;
                case R.id.imageView1:
                    //hacerJugada(0,1,1);
                    jugadorActual.elegirMosaico(0,1);
                    break;
                case R.id.imageView2:
                    //hacerJugada(0,2,2);
                    jugadorActual.elegirMosaico(0,2);
                    break;
                case R.id.imageView3:
                    //hacerJugada(0,3,3);
                    jugadorActual.elegirMosaico(0,3);
                    break;
                case R.id.imageView4:
                    //hacerJugada(1,0,4);
                    jugadorActual.elegirMosaico(1,0);
                    break;
                case R.id.imageView5:
                    //hacerJugada(1,1,5);
                    jugadorActual.elegirMosaico(1,1);
                    break;
                case R.id.imageView6:
                    //hacerJugada(1,2,6);
                    jugadorActual.elegirMosaico(1,2);
                    break;
                case R.id.imageView7:
                    //hacerJugada(1,3,7);
                    jugadorActual.elegirMosaico(1,3);
                    break;
                case R.id.imageView8:
                    //hacerJugada(2,0,8);
                    jugadorActual.elegirMosaico(2,0);
                    break;
                case R.id.imageView9:
                    //hacerJugada(2,1,9);
                    jugadorActual.elegirMosaico(2,1);
                    break;
                case R.id.imageView10:
                    //hacerJugada(2,2,10);
                    jugadorActual.elegirMosaico(2,2);
                    break;
                case R.id.imageView11:
                    //hacerJugada(2,3,11);
                    jugadorActual.elegirMosaico(2,3);
                    break;
                case R.id.imageView12:
                    //hacerJugada(3,0,12);
                    jugadorActual.elegirMosaico(3,0);
                    break;
                case R.id.imageView13:
                    //hacerJugada(3,1,13);
                    jugadorActual.elegirMosaico(3,1);
                    break;
                case R.id.imageView14:
                    //hacerJugada(3,2,14);
                    jugadorActual.elegirMosaico(3,2);
                    break;
                case R.id.imageView15:
                    //hacerJugada(3,3,15);
                    jugadorActual.elegirMosaico(3,3);
                    break;
            }
        }
    }

    public void clickNuevaPartida(View v)
    {
        btnTerminarJuego.setVisibility(View.INVISIBLE);
        btnNuevaPartida.setVisibility(View.INVISIBLE);
        ocultarLineasGanadoras();
        reiniciarTablero();
        juego.nuevaPartida();
    }

    public void clickSalir(View v)
    {
        finish();
    }

    /*Si se elige este nivel de juego, se deben hacer ocultas la ultima fila y columna*/
    public void selectTablero3x3()
    {
        mosaicos.get(3).setVisibility(View.GONE);
        mosaicos.get(7).setVisibility(View.GONE);
        mosaicos.get(11).setVisibility(View.GONE);
        mosaicos.get(12).setVisibility(View.GONE);
        mosaicos.get(13).setVisibility(View.GONE);
        mosaicos.get(14).setVisibility(View.GONE);
        mosaicos.get(15).setVisibility(View.GONE);
        juego.elegirTablero(3);
    }

    public void selectTablero4x4()
    {
        mosaicos.get(3).setVisibility(View.VISIBLE);
        mosaicos.get(7).setVisibility(View.VISIBLE);
        mosaicos.get(11).setVisibility(View.VISIBLE);
        mosaicos.get(12).setVisibility(View.VISIBLE);
        mosaicos.get(13).setVisibility(View.VISIBLE);
        mosaicos.get(14).setVisibility(View.VISIBLE);
        mosaicos.get(15).setVisibility(View.VISIBLE);
        juego.elegirTablero(4);
    }


    /*vuelven a ponerse los mosaicos grises para comenzar
    * otra partida*/
    void reiniciarTablero()
    {
        for(ImageView mosaico : mosaicos)
        {
            mosaico.setImageResource(R.drawable.ic_empty_square);
        }
    }

    /*Oculta la linea ganadora para comenzar otra
    * partida*/
    void ocultarLineasGanadoras()
    {
        for(ImageView linea : lineasGanadoras)
        {
            linea.setVisibility(View.INVISIBLE);
        }
    }
    /*Coloca una ficha en un mosaico si corresponde. Si el jugador presiono un mosaico
     * con una ficha o ya se termino la partida, no hace nada*/
    void hacerJugada(int filaElegida,int columnaElegida)
    {
        int turno = juego.getTurno();
        boolean jugadaValida = juego.hacerJugada(filaElegida,columnaElegida);

        if(jugadaValida)
        {
            switch(turno)
            {
                case 1:
                    conjuntoFichas.setFicha1(4*filaElegida + columnaElegida);
                    break;
                case 2:
                    conjuntoFichas.setFicha2(4*filaElegida + columnaElegida);
                    break;
            }
        }
    }

    @Override
    protected void onResume()
    {
        super.onResume();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

    }

    @Override
    public void update(int puntJ1, int puntJ2, int puntEmp, int turno, int ganador, ArrayList<String> nombreJugadores, int[][] matrizJuego)
    {
        /*Comprueba que haga la jugada el jugador correcto*/
        if(turno == juego.getTurno())
        {
            switch(turno)
            {
                case 1:
                    hacerJugada(jugador1.getFilaElegida(),jugador1.getColumnaElegida());
                    break;
                case 2:
                    hacerJugada(jugador2.getFilaElegida(),jugador2.getColumnaElegida());
                    break;
            }
        }

        if(juego.getLineaGanadora() != -1)
        {
            lineasGanadoras.get(juego.getLineaGanadora()).setVisibility(View.VISIBLE);
        }
        if(juego.getGanador() > 0)
        {
            btnTerminarJuego.setVisibility(View.VISIBLE);
            btnNuevaPartida.setVisibility(View.VISIBLE);
            Animation aparecer_btnJugar = AnimationUtils.loadAnimation(this,R.anim.aparecer_boton1);
            btnTerminarJuego.startAnimation(aparecer_btnJugar);
            btnNuevaPartida.startAnimation(aparecer_btnJugar);
        }


    }

}
