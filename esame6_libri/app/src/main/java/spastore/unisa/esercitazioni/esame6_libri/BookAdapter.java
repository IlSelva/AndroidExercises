package spastore.unisa.esercitazioni.esame6_libri;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<Book> {

    private LayoutInflater inflater;

    public BookAdapter(Context context, int resourceId, List<Book> objects){
        super(context,resourceId,objects);
        inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View v, ViewGroup parent) {
        if (v == null) {
            Log.d("DEBUG","Inflating view");
            v = inflater.inflate(R.layout.book_element, null);
            //v = inflater.inflate(resource, null);
        }

        Book b = getItem(position);

        Log.d("DEBUG","book b="+b);

        TextView titleTV;
        TextView authorTV;

        titleTV = v.findViewById(R.id.titleTV);
        authorTV = v.findViewById(R.id.authorTV);

        titleTV.setText(b.getTitle());
        authorTV.setText(b.getAuthor());

        return v;
    }

}
