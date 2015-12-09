package com.almightyamir.touchchelsea;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SplashActivity extends Activity implements View.OnClickListener{

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 20000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setGravity(Gravity.BOTTOM);

        mainLayout.setBackgroundResource(R.drawable.chelsea_ground);

        TextView tv1 = new TextView(this);
        tv1.setText("CHELSEA TOUCH THE LOGO");
        tv1.setGravity(Gravity.CENTER);
        tv1.setTextColor(Color.RED);
        tv1.setTextSize(25);
        tv1.setTypeface(null, Typeface.BOLD);
        //mainLayout.addView(tv1);

        Button btn = new Button(this);
        btn.setGravity(Gravity.BOTTOM | Gravity.CENTER);
        btn.setText("This screen will disappear in some time. \n Click here to go to the game directly.");
        btn.setTextColor(Color.RED);
        btn.setBackgroundColor(Color.CYAN);
        btn.setTextSize(25);
        btn.setTypeface(null, Typeface.BOLD);
        btn.setOnClickListener(this);
        mainLayout.addView(btn);


        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, ChelseaActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

        setContentView(mainLayout);

    }


    @Override
    public void onClick(View v) {

        Intent intent = new Intent(SplashActivity.this, ChelseaActivity.class);
        startActivity(intent);
        SplashActivity.this.finish();
    }
}
