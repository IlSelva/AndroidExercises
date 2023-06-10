package spastore.unisa.esercitazioni.esame2_listacontatti;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public EditText nameInput;
    public EditText surnameInput;
    public EditText telephoneInput;
    public ListView contactList;
    ArrayList<Contact> contacts;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        contactList = findViewById(R.id.contact_listview);
        nameInput = findViewById(R.id.nome_input);
        surnameInput = findViewById(R.id.cognome_input);
        telephoneInput = findViewById(R.id.telefono_input);

        contacts = savedInstanceState != null ? (ArrayList<Contact>) savedInstanceState.getSerializable("contacts") : new ArrayList<>();

        ContactAdapter listAdapter = new ContactAdapter(this, R.layout.contact_element, contacts);

        contactList.setAdapter(listAdapter);
        Log.i("context", getApplicationContext().toString());
        contactList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // dialog box
                Contact contact = listAdapter.getItem(position);
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case DialogInterface.BUTTON_POSITIVE:
                                Log.i("context", getApplicationContext().toString());
                                runOnUiThread(() ->Toast.makeText(getApplicationContext(), "Ok!", Toast.LENGTH_LONG).show());
                                listAdapter.remove(contact);
                                break;
                            case DialogInterface.BUTTON_NEGATIVE:
                                Log.i("context", getApplicationContext().toString());
                                runOnUiThread(() ->Toast.makeText(getApplicationContext(), "Azione annullata", Toast.LENGTH_LONG).show());
                                break;
                        }
                    }
                };

                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setMessage(String.format("Stai per eiliminare il contatto %s %s, sei sicuro?", contact.getName(), contact.getSurname()));
                builder.setPositiveButton("Si", dialogClickListener);
                builder.setNegativeButton("No", dialogClickListener);
                builder.show();
            }
        });
    }


    public void addContact(View v) {
        Contact contact = new Contact(nameInput.getText().toString(), surnameInput.getText().toString(), telephoneInput.getText().toString());
        ((ContactAdapter) contactList.getAdapter()).add(contact);

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle savedInstanceState) {

        savedInstanceState.putSerializable("contacts", (Serializable) contacts);
        super.onSaveInstanceState(savedInstanceState);
    }



}