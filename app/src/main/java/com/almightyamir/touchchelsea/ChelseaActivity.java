package com.almightyamir.touchchelsea;

import android.app.Activity;
import android.os.Bundle;
import android.widget.FrameLayout;

public class ChelseaActivity extends Activity {

    private GameView chelseaGame;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        chelseaGame = new GameView(this);

        FrameLayout outerLayout = new FrameLayout(this);
        outerLayout.addView(chelseaGame);

        setContentView(outerLayout);
    }

    @Override
    protected void onPause()
    {
        super.onPause();
        chelseaGame.killThread();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        chelseaGame.onDestroy();
    }

}
