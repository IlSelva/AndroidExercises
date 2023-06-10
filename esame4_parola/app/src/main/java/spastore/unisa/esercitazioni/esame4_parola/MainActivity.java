package spastore.unisa.esercitazioni.esame4_parola;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

public class MainActivity extends AppCompatActivity {
    public String word;
    public char[] currentGuess;
    public TextView wordTV;
    public EditText letterET;
    public TextView triesTV;
    public int tries;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        wordTV = findViewById(R.id.parola_textview);
        letterET = findViewById(R.id.letter_edit);
        triesTV = findViewById(R.id.tries_textview);

        //init
        word = "PROMOSSO";

        if(savedInstanceState == null) {
            tries = 0;
            currentGuess = new char[word.length()];
            for(int i=0; i< word.length(); i++){
                currentGuess[i] = '_';
            }
        }else{
            tries = savedInstanceState.getInt("tries");
            currentGuess = (char[]) savedInstanceState.getSerializable("guess");
        }

        updateGuessWord();
    }

    public void onSubmit(View view) {
        tries++;
        updateTries();
        char letter = String.valueOf(letterET.getText()).toUpperCase().charAt(0);
        if(word.contains(String.valueOf(letter)) && !String.valueOf(currentGuess).contains(String.valueOf(letter))){
            for (int i = 0; i < word.length(); i++) {
                if(word.charAt(i) == letter)
                    currentGuess[i] = letter;
            }
            updateGuessWord();
            Toast.makeText(getApplicationContext(),"CORRECT",Toast.LENGTH_SHORT).show();
        } else
            Toast.makeText(getApplicationContext(),"WRONG",Toast.LENGTH_SHORT).show();
        letterET.setText("");
    }

    public void onReset(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        final EditText edittext = new EditText(getApplicationContext());
        builder.setMessage("Stai per resettare il gioco, sei sicuro?");
        builder.setNegativeButton("Annulla", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        builder.setPositiveButton("Conferma", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int button) {
                tries = 0;
                for (int i = 0; i < word.length(); i++) {
                    currentGuess[i]='_';
                }
                updateTries();
                updateGuessWord();
            }
        });


        builder.show();
    }

    public void updateTries(){
        triesTV.setText("Tentativi : "+tries);
    }

    public void updateGuessWord(){
        wordTV.setText(String.valueOf(currentGuess));
    }

    public void onSuggestion(View view){
        tries += 5;
        Random r = new Random();
        int i=0;
        do{
            i = r.nextInt(word.length());
        }while(currentGuess[i] != '_');
        char letter = word.charAt(i);
        for (i = 0; i < currentGuess.length; i++) {
                if(word.charAt(i) == letter){
                  currentGuess[i] = letter;
                }
        }
        updateTries();
        updateGuessWord();
        letterET.setText("");
    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putSerializable("guess", currentGuess);
        outState.putInt("tries",tries);

        super.onSaveInstanceState(outState);
    }
}