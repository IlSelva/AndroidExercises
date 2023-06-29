package spastore.unisa.esercitazioni.pastoresilvio;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ViewUtils;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int colors[] = {Color.RED, Color.GREEN, Color.YELLOW, Color.BLUE, Color.MAGENTA, Color.CYAN};
    int paintButtons[] = {R.id.button1, R.id.button2, R.id.button3, R.id.button4, R.id.button5, R.id.button6, R.id.button7, R.id.button8, R.id.button9};
    int dropButtons[] = {R.id.colonna1, R.id.colonna2, R.id.colonna3};
    Spinner colorSelector;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        colorSelector = findViewById(R.id.colorSelector);
        colorSelector.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, new String[]{"RED", "GREEN", "YELLOW", "BLUE", "MAGENTA", "CYAN"}));
        fillRandom();

    }

    public void onDropPaint(View v) {
        int color = colorSelector.getSelectedItemPosition();
        if (color < 0 || color > 6)
            return;
        for (int i = 0; i < 3; i++) {
            if (v.getId() == dropButtons[i])
                dropPaint(i, color);
        }
    }

    public void onColor(View v) {
        int color = colorSelector.getSelectedItemPosition();
        if (color < 0 || color > 6)
            return;
        v.setBackgroundColor(colors[color]);

    }

    public void dropPaint(int i, int color) {
        for (; i < 9; i += 3) {
            Button b = findViewById(paintButtons[i]);
            b.setBackgroundColor(colors[color]);
        }
    }

    public void onFillRandom(View v) {
        fillRandom();
    }

    public void fillRandom() {
        Random r = new Random();
        for (int i = 0; i < 9; i++) {
            Button b = findViewById(paintButtons[i]);
            b.setBackgroundColor(colors[r.nextInt(6)]);
        }
    }

}
