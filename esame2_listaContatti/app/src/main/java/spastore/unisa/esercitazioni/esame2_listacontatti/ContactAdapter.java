package spastore.unisa.esercitazioni.esame2_listacontatti;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;

import java.io.Serializable;
import java.util.List;

public class ContactAdapter extends ArrayAdapter<Contact> {
    private LayoutInflater inflater;

    public ContactAdapter(Context context, int resourceId, List<Contact> objects){
        super(context, resourceId, objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            Log.d("DEBUG","Inflating view");
            v = inflater.inflate(R.layout.contact_element, null);
            //v = inflater.inflate(resource, null);
        }

        Contact c = getItem(position);

        Log.d("DEBUG","contact c="+c);

        TextView nameTextView;
        TextView telTextView;

        nameTextView = v.findViewById(R.id.contact_name);

        Log.d("DEBUG","nameTextView="+nameTextView);

        telTextView = v.findViewById(R.id.contact_phone);

        nameTextView.setText(c.getName()+" "+c.getSurname());
        telTextView.setText(c.getTelephone());

        return v;
    }

    @Override
    public void remove(@Nullable Contact object) {
        super.remove(object);
    }

}
