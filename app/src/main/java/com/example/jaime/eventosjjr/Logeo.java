package com.example.jaime.eventosjjr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Logeo extends AppCompatActivity {

    private Button buttonIniciarSecion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logeo);

        buttonIniciarSecion = (Button)findViewById(R.id.buttonIniciarSecion);

        buttonIniciarSecion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Logeo.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}