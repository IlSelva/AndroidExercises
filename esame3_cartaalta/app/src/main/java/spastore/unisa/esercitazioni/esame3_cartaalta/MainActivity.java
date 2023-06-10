package spastore.unisa.esercitazioni.esame3_cartaalta;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.PriorityQueue;
import java.util.Random;
import java.util.SortedSet;

public class MainActivity extends AppCompatActivity {

    private TextView scoreTV;
    private TextView cardTV;
    private Button startButton;
    private Button endButton;
    private Button minorButton;
    private Button majorButton;
    private int score = 0;
    private int currentCard = 0;
    private PriorityQueue<Player> players;
    String name;
    private SharedPreferences sharedPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreTV = findViewById(R.id.score);
        cardTV = findViewById(R.id.carta);
        startButton = findViewById(R.id.start_button);
        endButton = findViewById(R.id.end_button);
        minorButton = findViewById(R.id.minor_button);
        majorButton = findViewById(R.id.major_button);

        sharedPreferences = getSharedPreferences("players", MODE_PRIVATE);
        String savedPlayers = sharedPreferences.getString("players", null);
        if (savedPlayers != null) {
            ObjectInputStream objectInputStream = null;
            try {
                objectInputStream = new ObjectInputStream(new ByteArrayInputStream(Base64.decode(savedPlayers, 0)));

                players = (PriorityQueue<Player>) objectInputStream.readObject();
                //convert string to player
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Error restoring scores", Toast.LENGTH_LONG).show();
                Log.e("data", "reading error", e);
                players = new PriorityQueue<>();
            }
        } else
            players = new PriorityQueue<>();
    }

    public void onStartMatch(View view) {
        startButton.setEnabled(false);
        endButton.setEnabled(true);
        score = -1;
        updateScore();
        drawCard();
        minorButton.setEnabled(true);
        majorButton.setEnabled(true);
    }

    public void onEndMatch(View view) {

        if (players.size() < 3 || score > players.peek().getScore()) {
            //show dialog to get player name
            int playerScore = score;
            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            final EditText edittext = new EditText(getApplicationContext());
            builder.setMessage("Congratulazioni! inserisci il tuo nome per entrare in classifica");
            builder.setView(edittext);
            builder.setPositiveButton("inserisci", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int button) {
                    name = edittext.getText().toString();
                    Player player = new Player(name, playerScore);

                    if (players.size() == 3)
                        players.poll();
                    players.add(player);


                    Intent i = new Intent(getApplicationContext(), EndActivity.class);
                    i.putExtra("players", players);

                    startActivity(i);
                }
            });

            builder.show();
            //reset game
            score = -1;
            updateScore();
            startButton.setEnabled(true);
            endButton.setEnabled(false);
            minorButton.setEnabled(false);
            majorButton.setEnabled(false);
        } else {
            Toast.makeText(getApplicationContext(), "You didn't qualify, keep playing!", Toast.LENGTH_LONG).show();
        }
    }

    public void onShowRanking(View view){
        Intent i = new Intent(getApplicationContext(), EndActivity.class);
        i.putExtra("players", players);

        startActivity(i);
    }

    public void updateScore() {
        score++;
        scoreTV.setText("Score: " + score);
    }

    public void drawCard() {
        currentCard = new Random().nextInt(11);
        cardTV.setText("" + currentCard);
    }

    public void onLowerCard(View view) {
        int lastCard = currentCard;
        drawCard();
        if (currentCard < lastCard) {
            updateScore();
            Toast.makeText(getApplicationContext(), "You scored!", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), "WRONG!", Toast.LENGTH_SHORT).show();
        }
    }

    public void onHigherCard(View view) {
        int lastCard = currentCard;
        drawCard();
        if (currentCard > lastCard) {
            updateScore();
            Toast.makeText(getApplicationContext(), "You scored!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(), "WRONG!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        //Qui possiamo salvare lo stato del gioco
        if (players != null) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            try {

                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
                objectOutputStream.writeObject(players);
                objectOutputStream.close();
                editor.putString("players", Base64.encodeToString(byteArrayOutputStream.toByteArray(), 0));
                editor.commit();


            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "ERROR SAVING PROGRESS", Toast.LENGTH_LONG).show();
                Log.e("data", "Writing error", e);
                e.printStackTrace();
            }

        }
        // call superclass to save any view hierarchy
        super.onSaveInstanceState(outState);
    }

}