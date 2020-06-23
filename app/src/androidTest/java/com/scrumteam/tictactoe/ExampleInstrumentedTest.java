package com.scrumteam.tictactoe;

import android.content.Context;

import com.scrumteam.tictactoe.cviews.PanelPuntuacionesView;
import com.scrumteam.tictactoe.cviews.TurneroView;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.scrumteam.tictactoe", appContext.getPackageName());
    }

    Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();


    @Test
    public void probarPanelPuntuaciones()
    {
        //Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        PanelPuntuacionesView p = new PanelPuntuacionesView(appContext,null);

        p.update(1,2,0,1,0,null);

        assertEquals(1,p.getPuntajeJug1());
    }

    /*Genero turnero y una lista con los nombres de los dos jugadores*/
    TurneroView turneroView = new TurneroView(appContext,null);
    ArrayList<String> nombres = new ArrayList<>();

    @Test
    public void getMensajeGanadorJ1TurneroTest()
    {
        nombres.add("Ivan");
        nombres.add("Milagros");
        turneroView.update(0,0,0,-1,-1,nombres);
        turneroView.update(0,0,0,-1,1,null);

        assertEquals("Ganador: Ivan",turneroView.getMensaje());
    }

    @Test
    public void getMensajeGanadorJ2TurneroTest()
    {
        nombres.clear();
        nombres.add("Ivan");
        nombres.add("Milagros");
        turneroView.update(0,0,0,-1,-1,nombres);
        turneroView.update(0,0,0,2,2,null);

        assertEquals("Ganador: Milagros",turneroView.getMensaje());
    }

    @Test
    public void getMensajeEmpateTurneroTest()
    {
        nombres.clear();
        nombres.add("Ivan");
        nombres.add("Milagros");
        turneroView.update(0,0,0,-1,-1,nombres);
        turneroView.update(0,0,0,-1,3,null);

        assertEquals("Empataron!!!",turneroView.getMensaje());
    }

    @Test
    public void getMensajeTurnoJ1TurneroTest()
    {
        nombres.clear();
        nombres.add("Ivan");
        nombres.add("Milagros");
        turneroView.update(0,0,0,-1,-1,nombres);
        turneroView.update(0,0,0,1,-1,null);

        assertEquals("Juega: Ivan",turneroView.getMensaje());
    }

    @Test
    public void getMensajeTurnoJ2TurneroTest()
    {
        nombres.clear();
        nombres.add("Ivan");
        nombres.add("Milagros");
        turneroView.update(0,0,0,-1,-1,nombres);
        turneroView.update(0,0,0,2,-1,null);

        assertEquals("Juega: Milagros",turneroView.getMensaje());
    }

}
