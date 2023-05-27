package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class game extends AppCompatActivity {

//    Intent intent=getIntent();
//    String player1=intent.getStringExtra("name1");
//    String player2=intent.getStringExtra("name2");
    String player1,player2;
    Button bestMove;


    boolean gameActive = true;

    // Player representation
    // 0 - X
    // 1 - O
    int activePlayer = 0;
    int[] gameState = {2, 2, 2, 2, 2, 2, 2, 2, 2};

    // State meanings:
    //    0 - X
    //    1 - O
    //    2 - Null
    // put all win positions in a 2D array
    int[][] winPositions = {{0, 1, 2}, {3, 4, 5}, {6, 7, 8},
            {0, 3, 6}, {1, 4, 7}, {2, 5, 8},
            {0, 4, 8}, {2, 4, 6}};
    public int counter = 0;

    // this function will be called every time a
    // players tap in an empty box of the grid
    @SuppressLint("ResourceAsColor")
    public void playerTap(View view) {
        TextView txt = (TextView) view;
        int tappedImage = Integer.parseInt(txt.getTag().toString());

        // game reset function will be called
        // if someone wins or the boxes are full
        if (!gameActive) {
            gameReset(view);
        }

        // if the tapped text is empty
        if (gameState[tappedImage] == 2) {
            // increase the counter
            // after every tap
            counter++;

            // check if its the last box
            if (counter == 9) {
                // reset the game
                gameActive = false;

            }

            // mark this position
            gameState[tappedImage] = activePlayer;



            // change the active player
            // from 0 to 1 or 1 to 0
            if (activePlayer == 0) {
                // set the image of x

                txt.setText("X");
                txt.setTextColor(Color.parseColor("#ff0000"));
                //holder.text.setTextColor(Color.RED)
                //txt.setTextColor(R.color.red);
                activePlayer = 1;
                TextView turn=findViewById(R.id.turn);
                turn.setText(player2);
                //turn.setText("O");
                //turn.setTextColor(R.color.yellow);

                // change the status
            } else {
                // set the image of o
                txt.setText("O");
                txt.setTextColor(Color.parseColor("#ffff00"));
                //txt.setTextColor(R.color.yellow);
                activePlayer = 0;
                TextView turn=findViewById(R.id.turn);
                turn.setText(player1);
                //turn.setText("X");
                //turn.setTextColor(R.color.red);
            }
        }
        int flag = 0;
        // Check if any player has won
        for (int[] winPosition : winPositions) {
            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {
                flag = 1;


                // Somebody has won! - Find out who!
                String winnerStr;

                // game reset function be called
                gameActive = false;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = player1+ " has won";
                } else {
                    winnerStr = player2+" has won";
                }
                // Update the status bar for winner announcement
                Toast.makeText(this, winnerStr, Toast.LENGTH_SHORT).show();
                gameReset(view);
            }
        }
        if (counter == 9 && flag == 0) {
            //counter=0;
            Toast.makeText(this, "Match is Drawn", Toast.LENGTH_SHORT).show();
            gameReset(view);
        }
    }

    // reset the game
    public void gameReset(View view) {
        counter=0;
        gameActive = true;
        activePlayer = 0;
        for (int i = 0; i < gameState.length; i++) {
            gameState[i] = 2;
        }
        // remove all the images from the boxes inside the grid
        ((TextView) findViewById(R.id.imageView0)).setText("");
        ((TextView) findViewById(R.id.imageView1)).setText("");
        ((TextView) findViewById(R.id.imageView2)).setText("");
        ((TextView) findViewById(R.id.imageView3)).setText("");
        ((TextView) findViewById(R.id.imageView4)).setText("");
        ((TextView) findViewById(R.id.imageView5)).setText("");
        ((TextView) findViewById(R.id.imageView6)).setText("");
        ((TextView) findViewById(R.id.imageView7)).setText("");
        ((TextView) findViewById(R.id.imageView8)).setText("");

        Toast.makeText(this, "Game Restarts", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        Intent intent=getIntent();
        player1=intent.getStringExtra("name1");
        player2=intent.getStringExtra("name2");
        bestMove=findViewById(R.id.best_move);
        bestMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int[] arr=TicTac.Finder(gameState,activePlayer);
                if(arr[2]<0){
                    Toast.makeText(game.this, "luck game", Toast.LENGTH_SHORT).show();
                    // As Both Players are Intelligent, irrespective of wherever we move we will always loose as opponent will play optimally.
                }
                else{
                    Toast.makeText(game.this, "Row : "+String.valueOf(arr[0])+" ,"+" Col : "+String.valueOf(arr[1]), Toast.LENGTH_SHORT).show();
                }

            }
        });


    }
}