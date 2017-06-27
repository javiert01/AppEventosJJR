package com.example.jaime.eventosjjr;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.PopupMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

/**
 * Created by Aspire on 12/06/2017.
 */

public class FragmentInicio extends Fragment{

    ListView list;
    String[] web = {
            "Evento 1",
            "Evento 2",
            "Evento 3"

    } ;
    Integer[] imageId = {
            R.drawable.evento1,
            R.drawable.evento2,
            R.drawable.evento3
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);

        CustomList adapter = new CustomList(getActivity(), web, imageId);

        list=(ListView)view.findViewById(R.id.lista_eventos);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(getContext(), "Evento seleccionado: " +web[+ position], Toast.LENGTH_SHORT).show();

                PopupMenu popupMenu = new PopupMenu(getActivity(),view);

                popupMenu.getMenuInflater().inflate(R.menu.popmenu_evento,popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener(){
                    @Override
                    public boolean onMenuItemClick(MenuItem item){
                        Toast.makeText( getContext(), item.getTitle(), Toast.LENGTH_SHORT).show();

                        if(item.getTitle().equals("Ver")){
                            DialogEvento dialogoEvento = new DialogEvento();
                            dialogoEvento.show(getActivity().getFragmentManager(),"");
                        }

                        return true;
                    }
                });
                popupMenu.show();
            }
        });

        return view;
    }
}