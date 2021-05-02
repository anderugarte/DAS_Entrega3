package com.example.das_entregagrupal.Principal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.das_entregagrupal.R;

public class Opciones extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        Button bMiPerfil = (Button) findViewById(R.id.bPerfil);
        Button bMMPunt = (Button) findViewById(R.id.bMMPuntuciaones);
        Button bAyuda = (Button) findViewById(R.id.bAyuda);

        bMiPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bMMPunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent (getBaseContext(), Ayuda.class);
                startActivity(a);
            }
        });

    }
}