package com.images.android.images;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private int count = 0;

    public void clickFunction(View view){
        count += 1;
        // Define image that is set as ImageView Variable
        ImageView image = (ImageView) findViewById(R.id.imageView);
        // Log Button Press
        Log.i("Info", "Button Pressed...");

        // Change Image based on Number of times the button is pressed
        if (count % 2 == 0){
            // On Button Press, change Image Resource with New Image
            image.setImageResource(R.drawable.img4);
        }
        else {
            // On Button Press, change Image Resource with New Image
            image.setImageResource(R.drawable.img2);
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
