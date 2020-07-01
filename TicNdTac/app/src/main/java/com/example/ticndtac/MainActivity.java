package com.example.ticndtac;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.gridlayout.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btn;
    int activePlayer=1;
    Boolean gameActive=true;
    Boolean draw=true;
    int[] gameState={2,2,2,2,2,2,2,2,2};
    int[][] winningPositions={{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
    String str;

    public void dropIn(View view){

        if(gameActive) {

            ImageView counter = (ImageView) view;
            int tg = Integer.parseInt(view.getTag().toString());
            if (gameState[tg] == 2) {

                if (activePlayer == 1) {
                    counter.setImageResource(R.drawable.cross2);
                    activePlayer = 0;
                } else {
                    counter.setImageResource(R.drawable.circle1);
                    activePlayer = 1;
                }
                gameState[tg] = activePlayer;
                counter.setTranslationY(-1500);
                counter.animate().translationYBy(1500);

                for (int[] wp : winningPositions) {

                    if (gameState[wp[0]] == gameState[wp[1]] && gameState[wp[1]] == gameState[wp[2]] && gameState[wp[0]] != 2) {

                        if (gameState[wp[0]] == 0) {

                            str="player1 wins!!";

                        }

                        else if (gameState[wp[0]]==1) {

                            str="player2  wins!!";

                        }

                        gameActive = false;
                        textView.setText(str);
                        textView.setVisibility(View.VISIBLE);
                        btn.setVisibility(View.VISIBLE);

                    }
                }
                draw=true;

                for(int j=0;j<9;j++){
                    if(gameState[j]==2){
                        draw=false;
                    }
                }
                if(draw==true){

                    textView.setText("draw!!");
                    textView.setVisibility(View.VISIBLE);
                    btn.setVisibility(View.VISIBLE);

                }
            }
        }
    }

    public void playAgain(View view){

        textView.setVisibility(View.GONE);
        btn.setVisibility(View.GONE);
        gameActive=true;


        GridLayout gridLayout=(GridLayout) findViewById(R.id.gridLayout);
        int n=gridLayout.getChildCount();
        for(int i=0;i<n;i++)
        {
            ImageView ct=(ImageView) gridLayout.getChildAt(i);
            ct.setImageDrawable(null);

        }

        for(int i=0;i<gameState.length;i++){
            gameState[i]=2;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView=(TextView)findViewById(R.id.textView);
        btn=(Button)findViewById(R.id.button2);

    }
}
