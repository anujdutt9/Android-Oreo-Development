package com.braintrainer.androido.brain_trainer;

import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    int correctAnswerLocation;
    ArrayList<Integer> answers = new ArrayList<Integer>();
    int score = 0;
    int num_questions = 0;
    TextView scoreView;
    TextView timerView;
    TextView resultView;
    Button playAgain;
    ConstraintLayout gameLayout;

    // Function to Start Game
    public void start(View view){
        // Make button invisible when clicked
        goButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playGameAgain(findViewById(R.id.goButton));
    }

    // Function to handle button answer choice
    public void chooseAnswer(View view){
        // Get tag number of the button that is pressed
        if (Integer.valueOf(view.getTag().toString()) == correctAnswerLocation){
            resultView.setText("Correct :)");
            score ++;
        }
        else {
            resultView.setText("Wrong :(");
        }
        num_questions ++;
        scoreView.setText(Integer.toString(score) + "/" + Integer.toString(num_questions));
        resultView.setVisibility(View.VISIBLE);
        newQuestion();
    }


    // Function to ask new question
    public void newQuestion(){
        TextView sumTextView = (TextView) findViewById(R.id.sumTextView);
        Button button0 = findViewById(R.id.button0);
        Button button1 = findViewById(R.id.button1);
        Button button2 = findViewById(R.id.button2);
        Button button3 = findViewById(R.id.button3);

        int max = 100;
        int min = 0;

        // Get Two Random Numbers
        Random rand = new Random();

        int a = rand.nextInt(max - min) + min;
        int b = rand.nextInt(max - min) + min;

        // Show the two random numbers  with a plus in text view
        sumTextView.setText(Integer.toString(a) + " + " + Integer.toString(b));

        // Get the Location for Correct Answer Button Randomly
        correctAnswerLocation = rand.nextInt(4);

        // Clear Array List before putting new values
        answers.clear();

        // Place the Correct Answer to one Location randomly and the rest with Wrong Answer
        for (int i=0; i<4 ; i++){
            // If index corresponds to correct answer location, place it in array
            if (i == correctAnswerLocation){
                answers.add(a+b);
            }
            else{
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a+b){
                    wrongAnswer = rand.nextInt(41);
                }
                answers.add(wrongAnswer);
            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));
    }


    // Function to Play Game Again
    public void playGameAgain(View view){
        // Reset Score
        score = 0;
        num_questions = 0;
        timerView.setText("30s");
        resultView.setVisibility(View.INVISIBLE);

        scoreView.setText(Integer.toString(score) + " + " + Integer.toString(num_questions));
        newQuestion();

        // Play Again Button
        playAgain = findViewById(R.id.playAgainButton);
        playAgain.setVisibility(View.INVISIBLE);

        // Make Countdown Timer
        new CountDownTimer(30100, 1000){
            @Override
            public void onTick(long millisUntilFinished) {
                timerView.setText(String.valueOf(millisUntilFinished / 1000) + "s");
            }

            @Override
            public void onFinish() {
                MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.air_horn);
                mediaPlayer.start();
                resultView.setText("Done !");
                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Timer View
        timerView = (TextView) findViewById(R.id.timerTextView);
        // Score Text View
        scoreView = (TextView) findViewById(R.id.scoreTextView);
        // Correct/Wrong Text View
        resultView = (TextView) findViewById(R.id.resultTextView);
        resultView.setVisibility(View.INVISIBLE);
        gameLayout = findViewById(R.id.gameLayout);
        goButton = findViewById(R.id.goButton);
        goButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);
    }
}
