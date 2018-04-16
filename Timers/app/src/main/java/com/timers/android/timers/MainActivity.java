package com.timers.android.timers;

import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create a new Handler
/*        final Handler handler = new Handler();

        // Create new Runnable
        // Runs the code in here after every delay value
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Log.i("Info", "Runnable");

                // pass in delay to run the statement after the delay
                handler.postDelayed(this, 2000);
            }
        };

        // Run the Delay Block
        handler.post(runnable);*/

        // ---------------- COUNT DOWN TIMER ---------------------
        new CountDownTimer(10000, 1000){
            // Function runs every time the "Count Down Interval" is reached
            public void onTick(long millisecondsUntilDone){
                Log.i("Seconds Left", String.valueOf(millisecondsUntilDone / 1000));
            }

            public void onFinish(){
                Log.i("We're done", "No more Countdown...");
            }
        }.start();
    }
}
