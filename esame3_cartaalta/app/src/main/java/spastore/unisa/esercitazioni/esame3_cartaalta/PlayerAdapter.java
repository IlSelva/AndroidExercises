package spastore.unisa.esercitazioni.esame3_cartaalta;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.util.List;

public class PlayerAdapter extends ArrayAdapter<Player> {

    private LayoutInflater inflater;

    public PlayerAdapter(Context context, int resourceId, List<Player> objects){
        super(context, resourceId, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            Log.d("DEBUG","Inflating view");
            v = inflater.inflate(R.layout.player_element, null);
            //v = inflater.inflate(resource, null);
        }

        Player p = getItem(position);

        Log.d("DEBUG","player p="+p);

        TextView positionTextView;
        TextView nameTextView;
        TextView scoreTextView;

        positionTextView = v.findViewById(R.id.position);
        nameTextView = v.findViewById(R.id.name);
        scoreTextView = v.findViewById(R.id.score);

        positionTextView.setText(String.valueOf(position+1));
        nameTextView.setText(p.getName());
        scoreTextView.setText(String.valueOf(p.getScore()));

        return v;
    }

    @Override
    public void remove(@Nullable Player object) {
        super.remove(object);
    }


}
