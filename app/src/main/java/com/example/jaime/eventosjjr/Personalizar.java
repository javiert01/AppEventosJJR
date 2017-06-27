package com.example.jaime.eventosjjr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.Toast;


public class Personalizar extends AppCompatActivity {

    Button buttonPasar;

    GridView grid;
    String[] web = {
            "gps",
            "aventura",
            "deportes",
            "bici",
            "bar"

    } ;
    int[] imageId = {
            R.drawable.gps_icon,
            R.drawable.aventura_pic,
            R.drawable.deportes_pic,
            R.drawable.bici_pic,
            R.drawable.bar_pic

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizar);

        CustomGridViewAdapter adapter = new CustomGridViewAdapter(Personalizar.this, web, imageId);
        grid=(GridView)findViewById(R.id.grid);
        grid.setAdapter(adapter);
        grid.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Toast.makeText(Personalizar.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();

            }
        });

        buttonPasar = (Button)findViewById(R.id.buttonPasar);

        buttonPasar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Personalizar.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }



}