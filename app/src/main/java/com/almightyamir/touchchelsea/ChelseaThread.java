package com.almightyamir.touchchelsea;

import android.annotation.SuppressLint;
import android.graphics.Canvas;

/**
 * Created by Almighty Amir on 08-Dec-15.
 */
public class ChelseaThread extends Thread {

    private GameView view;

    private Boolean running = false;

    public ChelseaThread(GameView viewIn)
    {
        this.view = viewIn;
    }

    public void setRunning(boolean run)
    {
        running = run;
    }

    @SuppressLint("WrongCall")
    @Override
    public void run()
    {
        while (running)
        {
            Canvas c = null;
            try
            {
                c = view.getHolder().lockCanvas();
                synchronized (view.getHolder())
                {
                    view.onDraw(c);
                }
            }
            finally
            {
                if (c != null)
                {
                    view.getHolder().unlockCanvasAndPost(c);
                }
            }
        }
    }
}
