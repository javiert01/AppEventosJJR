package com.example.jaime.eventosjjr;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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

import com.rest.networking.HttpClient;
import com.rest.networking.OnHttpRequestComplete;
import com.rest.networking.Response;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Aspire on 12/06/2017.
 */

public class FragmentInicio extends Fragment{

    ListView list;
    ArrayList<String> nombresEventos = new ArrayList<>();
    ArrayList<String> urlImagenes = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        httpClient.excecute("http://172.29.0.113:1337/evento/");
        View view = inflater.inflate(R.layout.fragment_inicio, container, false);


        CustomList adapter = new CustomList(getActivity(), nombresEventos, urlImagenes);
        list=(ListView)view.findViewById(R.id.lista_eventos);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                //Toast.makeText(getContext(), "Evento seleccionado: " +web[+ position], Toast.LENGTH_SHORT).show();

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

    HttpClient httpClient = new HttpClient(new OnHttpRequestComplete() {
        @Override
        public void onComplete(Response status) {
            if(status.isSuccess()){
                Gson gson = new GsonBuilder().create();

                try {
                    Log.d("try","entro al try");
                    JSONArray jsonArray = new JSONArray(status.getResult());
                    //String mensaje = jsonObject.get("ip").toString();
                    //mostrarToast(mensaje);
                    //JSONArray jsonArray = jsonObject.getJSONArray("results");
                    ArrayList<Evento> listaEventos = new ArrayList<>();
                    for(int i=0; i<jsonArray.length();i++){
                        String eventoS = jsonArray.getString(i);
                        /*JSONObject categoria = jsonArray.getJSONObject(i).getJSONObject("fkIdCategoria");
                        Categoria categoria1 = gson.fromJson(categoria.toString(),Categoria.class);
                        JSONObject organizador = jsonArray.getJSONObject(i).getJSONObject("fkIdOrganizador");
                        Organizador organizador1 = gson.fromJson(organizador.toString(),Organizador.class);
                        JSONObject tipoEvento = jsonArray.getJSONObject(i).getJSONObject("fkIdTipoEvento");
                        TipoEvento tipoEvento1 = gson.fromJson(tipoEvento.toString(),TipoEvento.class);*/
                        Evento eventoC = gson.fromJson(eventoS,Evento.class);
                        listaEventos.add(eventoC);
                        nombresEventos.add(eventoC.nombreEvento);
                        urlImagenes.add(eventoC.imagenEvento);
                    }
                    Log.d("toast",listaEventos.get(0).nombreEvento.toString());
                    mostrarToast(listaEventos.get(0).nombreEvento.toString());
                    //mostrarToast(listaPeliculas.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    });

    public void mostrarToast(String mensaje){
       // Toast.makeText(MainActivity.this,mensaje,Toast.LENGTH_LONG).show();
    }

}