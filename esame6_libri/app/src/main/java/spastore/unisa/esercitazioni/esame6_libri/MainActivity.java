package spastore.unisa.esercitazioni.esame6_libri;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText titleET, authorET;
    ListView booksLV;
    List<Book> books;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        titleET = findViewById(R.id.titleET);
        authorET = findViewById(R.id.authorET);
        booksLV = findViewById(R.id.libriList);

        books = savedInstanceState == null ? new ArrayList<>() : (ArrayList<Book>) savedInstanceState.getSerializable("books");
        BookAdapter adapter = new BookAdapter(this, R.layout.book_element, books);

        booksLV.setAdapter(adapter);

        booksLV.setOnItemClickListener((parent, view, position, id) -> {
            Book b = adapter.getItem(position);
            if (b == null)
                return;

            DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    switch (which) {
                        case DialogInterface.BUTTON_POSITIVE:
                            Log.i("context", getApplicationContext().toString());
                            runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Ok!", Toast.LENGTH_LONG).show());
                            adapter.remove(b);
                            break;
                        case DialogInterface.BUTTON_NEGATIVE:
                            Log.i("context", getApplicationContext().toString());
                            runOnUiThread(() -> Toast.makeText(getApplicationContext(), "Azione annullata", Toast.LENGTH_LONG).show());
                            break;
                    }
                }
            };


            AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
            builder.setMessage("sei sicuro di voler eliminare l'elemento?");
            builder.setPositiveButton("SI", dialogClickListener);
            builder.setNegativeButton("NO", dialogClickListener);
            builder.show();
        });

    }

    public void onInsertItem(View view) {
        Book b = new Book();

        b.setTitle(titleET.getText().toString());
        b.setAuthor(authorET.getText().toString());

        ((BookAdapter) booksLV.getAdapter()).add(b);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("books", (Serializable) books);
    }
}