package spastore.unisa.esercitazioni.esame3_cartaalta;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class EndActivity extends AppCompatActivity {
    private PriorityQueue<Player> players;
    private ListView playerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);
        playerList = findViewById(R.id.player_listview);

        Intent intent = getIntent();
        players = (PriorityQueue<Player>) intent.getSerializableExtra("players");
        PlayerAdapter playerAdapter = new PlayerAdapter(getApplicationContext(), R.layout.player_element, new ArrayList<Player>());

        players.stream().sorted(Collections.reverseOrder()).forEach(player -> playerAdapter.add(player));

        playerList.setAdapter(playerAdapter);
    }


}
