package com.example.jaime.eventosjjr;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListAdapter;
import android.widget.Toast;

/**
 * Created by Aspire on 12/06/2017.
 */

public class Buscar extends Fragment {

    ImageButton gps;
    GridView grid;
    String[] web = {
            "Aventura",
            "Música",
            "Negocios",
            "Deportes",
            "Hospedaje",
            "X-Sports",
            "Bares",
            "Aficiones",
            "Moda"

    } ;
    int[] imageId = {
            R.drawable.aventura_pic_1,
            R.drawable.musica_pic,
            R.drawable.negocios_pic,
            R.drawable.deportes_pic,
            R.drawable.hospedaje_pic,
            R.drawable.bici_pic,
            R.drawable.bar_pic,
            R.drawable.aficiones_pic,
            R.drawable.moda_pic

    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buscar, container, false);
        gps = (ImageButton) view.findViewById(R.id.imageButton_gps_icon);
        gps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(),EventosCerca.class);
                startActivity(intent);
            }
        });

        CustomGridViewAdapter adapter = new CustomGridViewAdapter(getActivity(), web, imageId);
        grid=(GridView)view.findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getContext(), "" +web[+ position] + " seleccionado", Toast.LENGTH_SHORT).show();

            }
        });

        return view;//super.onCreateView(inflater, container, savedInstanceState);
    }


}