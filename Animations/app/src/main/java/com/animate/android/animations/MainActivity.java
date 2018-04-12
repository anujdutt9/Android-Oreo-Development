package com.animate.android.animations;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public void fadeImage(View view){
        Log.i("Info","Button Pressed...");

        ImageView bartImage = (ImageView)findViewById(R.id.bartImageView);
        ImageView homerImage = (ImageView)findViewById(R.id.homerImageView);

        // TranslationYBy: Move Image downwards by "Y" value in "dp"
        // bartImage.animate().translationYBy(2000).setDuration(2000);
        //bartImage.animate().translationXBy(-1000).setDuration(2000);

        // Rotations
        bartImage.animate().rotation(180).alpha(0).setDuration(2000);

        // Scale Images
        bartImage.animate().scaleX(0.5f).scaleY(0.5f).setDuration(1000);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageView bartImage = (ImageView)findViewById(R.id.bartImageView);
        //bartImage.animate().rotation(1800).alpha(1).setDuration(2000);
        //bartImage.animate().scaleX(0.5f).scaleY(0.5f).setDuration(1000);
        //bartImage.animate().translationXBy(-1000);
        bartImage.setX(-1000);
        bartImage.animate().translationXBy(1000).rotation(3600).setDuration(2000);
    }
}
