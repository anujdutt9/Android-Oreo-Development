package com.connect.android.connect_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // ActivePlayer: 0: Yellow, 1: Red
    public int activePlayer = 0;
    // 2: Represents empty state
    public int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // Winning Positions Array
    public int[][] winningPosition = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8}, {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, {0, 4, 8}, {2, 4, 6}};

    public boolean gameActive = true;

    public int yellowScore = 0;
    public int redScore = 0;

    // Function to "drop In Yellow Images" on click
    // Counter variable assigned to ImageView "view"
    public void dropIn(View view) {
        ImageView counter = (ImageView) view;
        TextView redTotalScore = (TextView) findViewById(R.id.redTotal);
        TextView yellowTotalScore = (TextView) findViewById(R.id.yellowTotal);

        // Tags of each image in box
        //Log.i("Tag",counter.getTag().toString());

        int tappedCounter = Integer.parseInt(counter.getTag().toString());

        // Check if box already has a color or if it's empty
        if ((gameState[tappedCounter] == 2) && gameActive) {

            // Keep a track that who is the active player in the game
            gameState[tappedCounter] = activePlayer;

            // Translate Image to top of screen in order to drop in
            counter.setTranslationY(-1500);

            if (activePlayer == 0) {
                counter.setImageResource(R.drawable.yellow);
                activePlayer = 1;
            } else {
                counter.setImageResource(R.drawable.red);
                activePlayer = 0;
            }

            counter.animate().translationYBy(1500).rotation(3600).setDuration(300);

            // To check that who won the match, Compare the values at winning position with the values in
            // game state array. The places where there is a red will be "1" and with yellow a "0".
            // If the values at the location matches with what it should be at a winning position i.e.
            // 3 0's or 3 1's at a winning position, the player wins.
            for (int[] winningPosition : winningPosition) {
                // Compare the values at winning position with the game states
                if ((gameState[winningPosition[0]] == gameState[winningPosition[1]]) && (gameState[winningPosition[1]] == gameState[winningPosition[2]]) && gameState[winningPosition[0]] != 2) {

                    gameActive = false;

                    // Check who has won
                    // Since we change the activePlayer on execution of the loop, so check for the opposite player
                    // Check if yellow has won as the initial condition was "activePlayer = 0"
                    if (activePlayer == 1) {
                        yellowScore += 1;
                        yellowTotalScore.setText(String.valueOf(yellowScore));
                        Toast.makeText(this, "Player Yellow Wins !!", Toast.LENGTH_LONG).show();
                    }
                    // Check if Red has Won
                    else if (activePlayer == 0) {
                        redScore += 1;
                        redTotalScore.setText(String.valueOf(redScore));
                        Toast.makeText(this, "Player Red Wins !!", Toast.LENGTH_LONG).show();
                    }

                    Button playReset = (Button) findViewById(R.id.playAndReset);
                    // Set Visibility to Visible
                    playReset.setVisibility(View.VISIBLE);
                }
            }
        }
    }



    // Function to Reset the Playboard and Clear the Screen
    public void clickFunction(View view) {

        Button playAgainButton = (Button) findViewById(R.id.playAndReset);
        playAgainButton.setVisibility(View.INVISIBLE);

        Log.i("Info","Button Pressed...");

        GridLayout gridLayout = (GridLayout) findViewById(R.id.gridLayoutView);

        for(int i=0; i<gridLayout.getChildCount(); i++) {
            ImageView counter = (ImageView) gridLayout.getChildAt(i);
            counter.setImageDrawable(null);
        }

        for (int i=0; i<gameState.length; i++) {
            gameState[i] = 2;
        }

        activePlayer = 0;
        gameActive = true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
