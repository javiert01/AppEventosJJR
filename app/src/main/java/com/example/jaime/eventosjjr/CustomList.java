package com.example.jaime.eventosjjr;

/**
 * Created by jaime on 13/06/17.
 */

import android.app.Activity;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.graphics.Bitmap;
import com.squareup.picasso.*;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Aspire on 13/06/2017.
 */

public class CustomList extends ArrayAdapter<String> {

    private final Activity context;
    private  ArrayList<String> nombreEvento = new ArrayList<>();
    private  ArrayList<String> urlImagen = new ArrayList<>();

    public CustomList(Activity context, ArrayList<String> nombreEvento, ArrayList<String> urlImagen) {

        super(context, R.layout.list_single, nombreEvento);
        this.context = context;
        this.nombreEvento = nombreEvento;
        this.urlImagen = urlImagen;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {

        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.list_texto);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.list_imagen);
        Picasso.with(context).load(urlImagen.get(position)).into(imageView);
        txtTitle.setText(nombreEvento.get(position));
        return rowView;
    }

}