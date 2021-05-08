package com.example.das_entregagrupal.Complementos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.das_entregagrupal.Principal.MenuPrincipal;
import com.example.das_entregagrupal.R;

public class Personalizacion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personalizacion);

        ImageButton bAtras2 = (ImageButton) findViewById(R.id.bAtras2);

        bAtras2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent (getBaseContext(), Opciones.class);
                startActivity(o);
                finish();
            }
        });

    }
}