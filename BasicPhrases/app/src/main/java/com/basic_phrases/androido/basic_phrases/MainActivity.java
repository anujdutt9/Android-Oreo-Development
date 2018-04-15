package com.basic_phrases.androido.basic_phrases;

import android.media.MediaPlayer;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.w3c.dom.Text;

import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    TextToSpeech tts;

    // Function to Play Phrase
    public void playPhrase(View view){
        // Button
        Button buttonPressed = (Button)view;
        Log.i("Button Pressed",buttonPressed.getTag().toString());

        // Media Player to Play Audio Files
        // Get File Name i.e. same as Tag Name
        String fileName = buttonPressed.getTag().toString();

        // Create MediaPlayer instance and provide audio file name as variable
        MediaPlayer mediaPlayer = MediaPlayer.create(this,getResources().getIdentifier(fileName, "raw", getPackageName()));
        mediaPlayer.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
