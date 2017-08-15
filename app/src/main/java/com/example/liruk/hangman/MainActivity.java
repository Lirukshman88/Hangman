package com.example.liruk.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    public static final String codeName = "com.example.myfirstapp.MESSAGE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //This method sends the hidden word chosen by the first user to the next class
    public void secretMessage(View view){
        //Get the secret word from user and send it to the next page
        Intent intent = new Intent(this, GuessingPage.class);
        EditText editText = (EditText)findViewById(R.id.secretWord);
        String secretWord = editText.getText().toString();
        intent.putExtra(codeName, secretWord);
        startActivity(intent);
        editText.getText().clear();
    }
}
