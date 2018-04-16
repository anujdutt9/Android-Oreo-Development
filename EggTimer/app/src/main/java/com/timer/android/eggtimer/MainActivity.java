package com.timer.android.eggtimer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextClock;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public CountDownTimer countDownTimer;
    public TextView textView;
    public SeekBar seekBar;
    public boolean isCounterActive = false;
    public Button button;


    public void resetTimer(){
        textView.setText("00:30");
        seekBar.setProgress(30);
        seekBar.setEnabled(true);
        countDownTimer.cancel();
        button.setText("GO !");
        isCounterActive = false;
    }

    // Function to Control Button State
    public void clickFunction(View view){
        button = (Button) findViewById(R.id.button);

        if (isCounterActive) {
            resetTimer();
        }
        else {
            button.setText("STOP !");
            isCounterActive = true;
            seekBar.setEnabled(false);
        }

        // ---------------- COUNT DOWN TIMER ---------------------
        countDownTimer = new CountDownTimer(seekBar.getProgress() * 1000 + 100, 1000){
            // Function runs every time the "Count Down Interval" is reached
            public void onTick(long millisecondsUntilDone){
                updateTimer((int) millisecondsUntilDone / 1000);
                Log.i("Seconds Left", String.valueOf(millisecondsUntilDone / 1000));
            }

            public void onFinish(){
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.air_horn);
                mediaPlayer.start();
                resetTimer();
                Log.i("We're done", "No more Countdown...");
            }
        }.start();
    }


    public void updateTimer(int secondsLeft){
        int mins = secondsLeft / 60;
        int sec = secondsLeft - (mins * 60);
        String secondString = Integer.toString(sec);
        if (sec <= 9){
            secondString = "0" + secondString;
        }
        String timeText = "0" + String.valueOf(mins) + ":" + secondString;
        textView.setText(timeText);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = (SeekBar)findViewById(R.id.seekBar);
        seekBar.setMin(10);
        seekBar.setMax(600);
        seekBar.setProgress(30);

       textView = (TextView) findViewById(R.id.textView);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                updateTimer(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
