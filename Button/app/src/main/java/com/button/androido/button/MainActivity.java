package com.button.androido.button;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Function to Print Log / Change Text in TextView on Button Press
    public void clickFunction(View view){
        // Print the Log Message on Button Press
        Log.i("Info: ", "Button Pressed...");
        // Change "Hello World" textView to custom Message on Button Press
        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText("Welcome to Android...");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
