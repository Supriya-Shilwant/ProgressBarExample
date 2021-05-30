package com.example.progressbardemo;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    //Initialize Variables
    ProgressBar progressBar;
    Button btnStart;
    int count = 0;
    Timer timer;
    //we are going to use a handler to be able to run in our TimerTask
    final Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Assign Variable
        progressBar = findViewById(R.id.prgssbar);
        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startTimer();
                btnStart.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        btnStart.setVisibility(View.GONE);
    }

    @Override
    protected void onResume() {
        super.onResume();
        startTimer();
    }

    public void startTimer() {
        if (timer == null) ;
        timer = new Timer();

        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        count++;
                        Log.d("Count : ", " " + count);
                        progressBar.setProgress(count);
                        if (count == 100) {
                            Log.d("Count Reach at : ", " " + count);
                            if (timer != null) {
                                timer.cancel();
                            }

                            count = 0;
                            progressBar.setVisibility(View.GONE);
                            btnStart.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
        };
        timer.schedule(timerTask, 0, 100);
    }
}