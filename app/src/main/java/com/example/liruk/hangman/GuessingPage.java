package com.example.liruk.hangman;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Arrays;

public class GuessingPage extends AppCompatActivity {
    int guessCounter = 0;
    int guessCorrect = 0;
    int guessIncorrect = 0;
    int numberOfSpaces = 0;

    boolean winner = false;

    String secret = "";
    String secretLetter = "";

    TextView revealSecret;
    TextView revealIncorrect;
    TextView guessesLeft;

    StringBuilder changingSecret = new StringBuilder();

    ArrayList charactersGuessedCorrectly = new ArrayList();
    ArrayList letterCorrect = new ArrayList();
    ArrayList letterIncorrect = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guessing_page);
        //To receive the secret word from the MainActivity and puts it into local variable secret
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.codeName);
        secret = message;

        //Puts '_' in the arrayList for the unchosen characters of secret
        //Checks if space occurs then replace with '/'
        for (int j = 0; j < secret.length(); j++) {
            if (secret.charAt(j) == ' ') {
                charactersGuessedCorrectly.add("/");
                changingSecret.append("/  ");
                numberOfSpaces++;
            } else {
                charactersGuessedCorrectly.add("_");
                changingSecret.append("_  ");
            }
        }

        TextView textView = (TextView) findViewById(R.id.Word);
        TextView textView1 = (TextView) findViewById(R.id.Word1);
        TextView textView2 = (TextView) findViewById(R.id.Word4);
        revealSecret = textView;
        revealIncorrect = textView1;
        guessesLeft = textView2;
        //Prints out incorrect and '_'
        revealIncorrect.setText("INCORRECT BOX: " + Arrays.toString(letterIncorrect.toArray()));
        guessesLeft.setText("Guesses left: " + (6 - guessIncorrect));
        revealSecret.setText(changingSecret);

    }

    public void toGuess(View view) {
        EditText editText = (EditText) findViewById(R.id.guessLetter);
        String letter = editText.getText().toString();
        //This if statement is to check whether something is put into to the edittext
        if (letter.equals(null) || letter.equals("")) {
            Toast.makeText(this,"Please enter a character",Toast.LENGTH_SHORT).show();

        } else {
            secretLetter = letter;
            //Gets the char value of the guessed value from the user
            char c1 = secretLetter.charAt(0);
            changingSecret.delete(0, changingSecret.length());

            //Check whether if the user already guessed the character
            if (letterCorrect.contains(c1) || letterIncorrect.contains(c1)) {
                Toast.makeText(getApplicationContext(), "You already guessed that", Toast.LENGTH_SHORT).show();
            } else {
                //Checks if character is equal to a charcter of secret, if it is then puts it in an arrayList
                // called charactersGuessedCorrectly and puts at the same index as secret and adds it to letterCorrect
                for (int i = 0; i < secret.length(); i++) {
                    char c2 = secret.charAt(i);
                    if (c1 == c2 && (c1 != ' ')) {
                        charactersGuessedCorrectly.set(i, secretLetter);
                        guessCorrect++;
                        guessCounter++;
                        letterCorrect.add(c1);
                    }
                }

                // if it is not the same character it puts it into incorrect and increases of the number of incorrects by 1
                //resets guessCounter
                if (guessCounter == 0) {
                    guessIncorrect++;
                    letterIncorrect.add(c1);
                }
                guessCounter = 0;

                revealIncorrect.setText("INCORRECT BOX: " + Arrays.toString(letterIncorrect.toArray()));
                guessesLeft.setText("Guesses left: " + (6 - guessIncorrect));

                //Puts the characterGuessed ArrayList in a string to print it out as a string on the screen
                for (int i = 0; i < charactersGuessedCorrectly.size(); i++) {
                    changingSecret.append(charactersGuessedCorrectly.get(i) + "  ");
                }
                revealSecret.setText(changingSecret);

                //Check whether if you solved it by number of guessedCorrect and length of the string
                //Takes into the spaces as well because the count in the length of the string but not
                //number of guessedCorrext and checks if you reach the limit of guesses which is 6
                //Sends it to the next page
                Intent intent = new Intent(this, winnerOrLoser.class);
                if (secret.contains(" ") && (guessCorrect == secret.length() - numberOfSpaces)) {
                    Toast winToast = Toast.makeText(getApplicationContext(), "You Win", Toast.LENGTH_SHORT);
                    winToast.setGravity(Gravity.CENTER, 0, 0);
                    winToast.show();
                    winner = true;
                    intent.putExtra("winner", winner);
                    intent.putExtra("secret", secret);
                    startActivity(intent);
                    finish();
                } else if ((guessCorrect == secret.length())) {
                    Toast winToast = Toast.makeText(getApplicationContext(), "You Win", Toast.LENGTH_SHORT);
                    winToast.setGravity(Gravity.CENTER, 0, 0);
                    winToast.show();
                    winner = true;
                    intent.putExtra("winner", winner);
                    intent.putExtra("secret", secret);
                    startActivity(intent);
                    finish();
                } else if (guessIncorrect == 6) {
                    Toast loseToast = Toast.makeText(getApplicationContext(), "You Lose", Toast.LENGTH_SHORT);
                    loseToast.setGravity(Gravity.CENTER, 0, 0);
                    loseToast.show();
                    winner = false;
                    intent.putExtra("winner", winner);
                    intent.putExtra("secret", secret);
                    startActivity(intent);
                    finish();
                }
            }
            editText.getText().clear();
        }
    }
}

