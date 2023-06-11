package spastore.unisa.esercitazioni.esame5_liste;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public ListView listView1, listView2;
    public Button button1, button2;
    public Switch function;
    ArrayAdapter adapter1,adapter2;
    public List<String> list1, list2;
    public String[] names = {"Andrea", "Alice", "Carlo", "Claudia", "Dario", "Dana", "Elena", "Enrico", "Giovanna", "Luca", "Marco", "Maria", "Roberto", "Silvio"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView1 = findViewById(R.id.lista1);
        listView2 = findViewById(R.id.lista2);
        button1 = findViewById(R.id.insert_button1);
        button2 = findViewById(R.id.insert_button2);
        function = findViewById(R.id.function_switch);

        list1 = new ArrayList<>();
        list2 = new ArrayList<>();

        adapter1 = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element, R.id.lista1, list1);
        adapter2 = new ArrayAdapter<String>(getApplicationContext(), R.layout.list_element, R.id.lista2, list2);

        listView1.setAdapter(adapter1);
        listView2.setAdapter(adapter2);

        listView1.setOnItemClickListener(getListener(list1, list2));
        listView1.setOnItemClickListener(getListener(list2, list1));

    }

    @NonNull
    private AdapterView.OnItemClickListener getListener(List activeList, List backList) {
        return new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String element = (String) activeList.remove(position);

                if (function.isChecked())
                    backList.add(element);

                adapter1.notifyDataSetChanged();
                adapter2.notifyDataSetChanged();
            }
        };
    }

    public void onInsertInList(View view) {
        Random random = new Random();
        int n = random.nextInt(4) + 1;
        ListView currentList = view.getId() == R.id.lista1 ? listView1 : listView2;
        for (int i = 0; i < n; i++) {
            ((ArrayAdapter) currentList.getAdapter()).add(names[random.nextInt(15)]);
        }
    }


}