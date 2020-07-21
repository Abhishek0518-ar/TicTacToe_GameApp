package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    boolean gameActive = true;
    // Player Representation
    // 0 - X
    // 1 - O
    int activePlayer = 0,count = 0;
    int gameState[] = {2, 2, 2, 2, 2, 2, 2, 2, 2};
    // State meanings:
    // 0 - X
    // 1 - O
    // 2 - Null

    String winnerStr = "",s;
    int[][] winPositions = {{0,1,2}, {3,4,5}, {6,7,8},
                            {0,3,6}, {1,4,7}, {2,5,8},
                            {2,4,6}, {0,4,8}};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void playerTap(View view){
        //gameActive = true;
        ImageView img = (ImageView) view;
        int tappedImage = Integer.parseInt(img.getTag().toString());
        if(!gameActive) {
            Button playAgain = (Button) findViewById(R.id.playAgain);
            playAgain.setVisibility(View.VISIBLE);
        }
        if(gameState[tappedImage] == 2 && winnerStr.length()==0) {
            gameState[tappedImage] = activePlayer;
            img.setTranslationY(-1000f);
            if (activePlayer == 0) {
                img.setImageResource(R.drawable.x);
                img.animate().translationYBy(1000f).setDuration(300);
                activePlayer = 1;
                TextView status = findViewById(R.id.status);
                status.setText("O's turn-tap to play");
            } else {
                img.setImageResource(R.drawable.o);
                img.animate().translationYBy(1000f).setDuration(300);
                activePlayer = 0;
                TextView status = findViewById(R.id.status);
                status.setText("X's turn-tap to play");
            }

        }

        //Check if any player has won

        for(int[] winPosition:winPositions) {
            winnerStr = "";

            if (gameState[winPosition[0]] == gameState[winPosition[1]] &&
                    gameState[winPosition[1]] == gameState[winPosition[2]] &&
                    gameState[winPosition[0]] != 2) {


                int pos = winPosition[0];
                int pos1 = winPosition[1];
                int pos2 = winPosition[2];
                s = String.valueOf(pos)+String.valueOf(pos1)+String.valueOf(pos2);

                // 012 - leftright
                // 345 - leftright2
                // 678 - leftright3
                // 036 - topdown1
                // 147 - topdown2
                // 258 - topdown3
                // 246 - d1
                // 048 - d2

                //someone have won the game

                count++;
                if (gameState[winPosition[0]] == 0) {
                    winnerStr = "X has won";

                } else {
                    winnerStr = "O has won";

                }
                gameActive = false;

                if(s.equals("036")){
                    ImageView line1 = findViewById(R.id.topdown1);
                    line1.setVisibility(View.VISIBLE);
                    line1.bringToFront();
                }

                else if(s.equals("147")){
                    ImageView line1 = findViewById(R.id.topdown2);
                    line1.setVisibility(View.VISIBLE);
                    line1.bringToFront();
                }

                else if(s.equals("258")){
                    ImageView line1 = findViewById(R.id.topdown3);
                    line1.setVisibility(View.VISIBLE);
                    line1.bringToFront();
                }

                else if(s.equals("246")){
                    ImageView line1 = findViewById(R.id.d1);
                    line1.setVisibility(View.VISIBLE);
                    line1.bringToFront();
                }

                else if(s.equals("048")){
                    ImageView line1 = findViewById(R.id.d2);
                    line1.setVisibility(View.VISIBLE);
                    line1.bringToFront();
                }

                else if(s.equals("012")){
                    ImageView line1 = findViewById(R.id.leftright);
                    line1.setVisibility(View.VISIBLE);
                    line1.bringToFront();
                }

                else if(s.equals("345")){
                    ImageView line1 = findViewById(R.id.leftright2);
                    line1.setVisibility(View.VISIBLE);
                    line1.bringToFront();
                }
                else {
                    if (s.equals("678")) {
                        ImageView line1 = findViewById(R.id.leftright3);
                        line1.setVisibility(View.VISIBLE);
                        line1.bringToFront();
                    }
                }
                Button playAgain = (Button) findViewById(R.id.playAgain);
                playAgain.setVisibility(View.VISIBLE);

                TextView status = findViewById(R.id.status);
                status.setText(winnerStr);
                break;
            }
        }

        if(!my_contains(gameState, 2) && gameActive){

            gameActive =false;
            winnerStr = "Game Draw";
            TextView status = findViewById(R.id.status);
            status.setText(winnerStr);
            Button playAgain = (Button) findViewById(R.id.playAgain);
            playAgain.setVisibility(View.VISIBLE);

        }

    }

    public static boolean my_contains(int[] arr, int item ){

        for(int n:arr){
            if(item == n){
                return true;
            }
        }
        return false;
    }

    public void gameReset(View view){

        gameActive = true;
        activePlayer = 0;
        for(int i=0; i<gameState.length; i++){
            gameState[i] = 2;
        }

        Button playAgain = (Button) findViewById(R.id.playAgain);
        playAgain.setVisibility(View.INVISIBLE);
        ImageView line1 = findViewById(R.id.topdown1);
        line1.setVisibility(View.INVISIBLE);
        ImageView line2 = findViewById(R.id.topdown2);
        line2.setVisibility(View.INVISIBLE);
        ImageView line3 = findViewById(R.id.topdown3);
        line3.setVisibility(View.INVISIBLE);
        ImageView line4 = findViewById(R.id.leftright);
        line4.setVisibility(View.INVISIBLE);
        ImageView line5 = findViewById(R.id.leftright2);
        line5.setVisibility(View.INVISIBLE);
        ImageView line6 = findViewById(R.id.leftright3);
        line6.setVisibility(View.INVISIBLE);
        ImageView line7 = findViewById(R.id.d1);
        line7.setVisibility(View.INVISIBLE);
        ImageView line8 = findViewById(R.id.d2);
        line8.setVisibility(View.INVISIBLE);

        ((ImageView)findViewById(R.id.imageView0)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView1)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView2)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView3)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView4)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView5)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView6)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView7)).setImageResource(0);
        ((ImageView)findViewById(R.id.imageView8)).setImageResource(0);

        winnerStr = "";

        TextView status = findViewById(R.id.status);
        status.setText("X's turn - tap to play");


    }

}