package com.example.stopwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;

public class StopAct extends AppCompatActivity {
    public boolean isRunning = false;
    public long pauseOffset =0;
    Button btnstart, btnstop,btnpause;
    ImageView icanchor;
    Animation roundingalone;
    Chronometer timer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stop);
        btnstart = findViewById(R.id.btnstart);
        btnstop = findViewById(R.id.btnstop);
        icanchor = findViewById(R.id.icanchor);
        timer = findViewById(R.id.timer);
        btnpause = findViewById(R.id.btnpause);

        btnpause.setAlpha(0);
        btnstop.setAlpha(0);

        roundingalone = AnimationUtils.loadAnimation(this, R.anim.roundingalone);


        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                icanchor.startAnimation(roundingalone);
                btnstop.animate().alpha(1).translationY(-100).setDuration(300).start();
                btnpause.animate().alpha(1).setDuration(300).start();
                btnstart.animate().alpha(0).setDuration(300).start();
                if(!isRunning){
                timer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
                timer.start();
                isRunning= true;
                }

            }

        });
      btnpause.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(isRunning){
                  timer.stop();
                  pauseOffset = SystemClock.elapsedRealtime() - timer.getBase();
                  isRunning=false;
                  btnstart.animate().alpha(1).setDuration(300).start();
                  icanchor.clearAnimation();
              }
          }
      });





        btnstop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timer.setBase(SystemClock.elapsedRealtime());
                timer.stop();
                icanchor.clearAnimation();
                btnstart.animate().alpha(1).setDuration(300).start();
            }

        });
    }
}