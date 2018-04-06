package com.message.android.displayingmessages;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view){
        Log.i("Info", "Button Pressed...");

        EditText nameText = (EditText)findViewById(R.id.nameTextView);
        EditText password = (EditText)findViewById(R.id.passwordTextView);

        Log.i("Name",nameText.getText().toString());
        Log.i("Password",password.getText().toString());

        // Add Toast Message
        // this: refer to this app where toast will appear
        // LENGTH_LONG: Amount of time you want the toast message to be displayed

        //Toast.makeText(this, "Login Successful", Toast.LENGTH_LONG).show();
        Toast.makeText(this, "Welcome " + nameText.getText().toString(), Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
