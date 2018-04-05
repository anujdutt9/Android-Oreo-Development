package com.textfield.androido.textfields;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // Function to do something on pressing the Button
    // Aim: Enter text in Box and Show that in Log when button is pressed
    public void clickFunction(View view){

        // Create EditText Variable to refer to "nameText"
        // nameText: Type of "EditText class"
        // To get the Value from "text field view" into "nameText" of type "EditText", we do a "findViewById"
        // Id: R -> Resources, id: for set of resources, nameText: particular id
        // ID: is under attributes for each "Pallete"
        // findViewById: Returns a View ; we want an EditText
        // Convert View to EditText View by casting View to EditText
        EditText nameText = (EditText) findViewById(R.id.nameText);

        // Do the same for Password
        EditText passwordText = (EditText) findViewById(R.id.passwordText);
        //passwordText.setTransformationMethod(new PasswordTransformationMethod());

        Log.i("Info","Button Pressed");

        // Print Value of text field "nameText"
        // Convert the data from text field to String to print in Logs
        Log.i("Name", nameText.getText().toString());

        // Print Password
        Log.i("Password",passwordText.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
