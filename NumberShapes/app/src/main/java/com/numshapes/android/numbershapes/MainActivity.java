package com.numshapes.android.numbershapes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    class numberShapes{
        int number;
        int n = 1;
        int sum = 0;
        int sq = 0;
        private Context context;

        // Check if Number is Triangular
        private boolean isTriangular(){
            if (number < 0){
                Toast.makeText(this.context, "Invalid Input !!", Toast.LENGTH_LONG).show();
            }

            // Check if Number is Triangular
            while (sum <= number){
                sum += n;
                if (sum == number){
                    return true;
                }
                n += 1;
            }
            return false;
        }

        // Check if number is Square
        private boolean isSquare(){
            if (number < 0){
                Toast.makeText(this.context, "Invalid Input !!", Toast.LENGTH_LONG).show();
            }

            // Check if Number is a Square
            while (sq <= number){
                if ((sq * sq) == number){
                    return true;
                }
                sq += 1;
            }

            return false;
        }
    }

    // Function to handle button press
    public void clickFunction(View view){
        EditText num = (EditText)findViewById(R.id.editText);

        numberShapes shape = new numberShapes();
        shape.number = Integer.parseInt(num.getText().toString());

        if (shape.isSquare() && shape.isTriangular()){
            Toast.makeText(this, "Both Square & Triangular", Toast.LENGTH_LONG).show();
            Log.i("Shape","Both Square & Triangular");
        }
        else if (shape.isSquare() && !shape.isTriangular()){
            Toast.makeText(this,"Square", Toast.LENGTH_LONG).show();
            Log.i("Shape","Square");
        }
        else if (shape.isTriangular() && !shape.isSquare()){
            Toast.makeText(this, "Triangular", Toast.LENGTH_LONG).show();
            Log.i("Shape","Triangular");
        }
        else {
            Toast.makeText(this, "Neither", Toast.LENGTH_LONG).show();
            Log.i("Shape","Neither");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
