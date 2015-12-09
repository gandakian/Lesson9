package com.almightyamir.touchchelsea;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.util.Random;

/**
 * Created by Almighty Amir on 08-Dec-15.
 */
public class GameView extends SurfaceView {

    private SurfaceHolder holder;
    private Bitmap background;

    private ChelseaThread chelseaThread = null;
    private Bitmap logo;
    private float logoX;
    private float logoY;
    private int score = 0;
    private Paint scorePaint;
    private Random logoRandomizer;
    private long logoTimer;

    public GameView(Context context) {
        super(context);

        holder = getHolder();
        holder.addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {

                background = BitmapFactory.decodeResource(getResources(), R.drawable.background);
                background = Bitmap.createScaledBitmap(background, getWidth(), getHeight(), false);

                logo = BitmapFactory.decodeResource(getResources(), R.drawable.chelsea_logo);
                logoRandomizer = new Random();
                moveLogo();

                scorePaint = new Paint();
                scorePaint.setTextSize(50.0f);
                scorePaint.setColor(Color.BLACK);

                makeThread();
                chelseaThread.setRunning(true);
                chelseaThread.start();
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {

            }
        });

    }

    public void moveLogo()
    {
        logoX = (float)logoRandomizer.nextInt(getWidth() - 100);
        logoY = (float)logoRandomizer.nextInt(getHeight() - 175);
        logoY += 100.0f;
        logoTimer = System.currentTimeMillis();
    }

    public void makeThread()
    {
        chelseaThread = new ChelseaThread(this);

    }

    public void killThread()
    {
        boolean retry = true;
        chelseaThread.setRunning(false);
        while(retry)
        {
            try
            {
                chelseaThread.join();
                retry = false;
            }
            catch (InterruptedException e){}
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent e)
    {
        float touchedX = e.getX();
        float touchedY = e.getY();

        if(  touchedX >= logoX
                && touchedX <= logoX + 100.0f
                && touchedY >= logoY
                && touchedY <= logoY + 75.0f )
        {
            score++;
            moveLogo();
        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas)
    {
        //canvas.drawColor(Color.WHITE);
        canvas.drawBitmap(background, 0, 0, null);
        canvas.drawText("Score: " + String.valueOf(score), 10.0f, 50.0f, scorePaint);
        if(System.currentTimeMillis() > logoTimer + 1000) moveLogo();
        canvas.drawBitmap(logo, logoX, logoY, null);
    }

    public void onDestroy()
    {
        logo.recycle();
        logo=null;
        System.gc();
    }
}
