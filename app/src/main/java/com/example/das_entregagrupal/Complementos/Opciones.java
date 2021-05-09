package com.example.das_entregagrupal.Complementos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.das_entregagrupal.R;

public class Opciones extends AppCompatActivity {

    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        Button bMiPerfil = (Button) findViewById(R.id.bPerfil);
        Button bMMPunt = (Button) findViewById(R.id.bMMPuntuciaones);
        Button bPers = (Button) findViewById(R.id.bPersonalizacion);
        Button bAyuda = (Button) findViewById(R.id.bAyuda);

        // Recibimos el nombre de usuario del usuario que se ha registrado al igual que el resto de sus datos
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("username");
        }

        bMiPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mip = new Intent (getBaseContext(), MiPerfil.class);
                mip.putExtra("username",user);
                startActivity(mip);
            }
        });

        bMMPunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bPers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent (getBaseContext(), Personalizacion.class);
                startActivity(p);
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