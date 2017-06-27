package com.example.jaime.eventosjjr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class CompraEntradas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compra_entradas);
        getIntent();
    }
}
