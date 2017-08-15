package com.example.liruk.hangman;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class winnerOrLoser extends AppCompatActivity {
    boolean winners;
    String secreters;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner_or_loser);

        //Receives the boolean value if won or lost and secret word
        Intent intent = getIntent();
        winners = intent.getExtras().getBoolean("winner");
        secreters = intent.getExtras().getString("secret");
        TextView textView = (TextView) findViewById(R.id.textViewers);
        TextView textview1 = (TextView) findViewById(R.id.textViewer);

        //Prints out you win or lose
        if(winners == true){
            textView.setText("YOU WIN");
            textview1.setText(secreters);
        }
        else{
            textView.setText("YOU LOSE");
            textview1.setText(secreters);
        }
    }

}
