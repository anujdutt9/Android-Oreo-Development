package com.currency.android.currencyconverter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    public void clickFunction(View view){
        double total = 0;

        EditText fromText = (EditText)findViewById(R.id.fromTextView);
        EditText toText = (EditText)findViewById(R.id.toTextView);
        EditText fromAmountText = (EditText)findViewById(R.id.fromAmountText);
        EditText toAmountText = (EditText)findViewById(R.id.toAmountText);

        Log.i("Info","Button Pressed...");

        if ((fromText.getText().toString().equals("EUR")) && (toText.getText().toString().equals("USD"))){
            // Take in Text and parse as an Integer
            total = Math.round(Double.parseDouble(fromAmountText.getText().toString()) * 1.22);
            toAmountText.setText(String.valueOf(total));
            Log.i("Total",String.valueOf(total));
        }
        else if ((fromText.getText().toString().equals("USD")) && (toText.getText().toString().equals("EUR"))){
            total = Math.round(Double.parseDouble(fromAmountText.getText().toString()) / 1.22);
            toAmountText.setText(String.valueOf(total));
            Log.i("Total",String.valueOf(total));
        }
        else {
            toAmountText.setText(" ");
            fromAmountText.setText(" ");
            Toast.makeText(this, "Currency Not Supported !!", Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
