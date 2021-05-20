package com.example.das_entregagrupal.Complementos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.das_entregagrupal.R;

public class Amigos extends AppCompatActivity {

    // Se podran consultar nuestros amigos

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigos);

        ImageButton bAtras2 = (ImageButton) findViewById(R.id.bAtras2);
        bAtras2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent (getBaseContext(), Opciones.class);
                startActivity(o);
                finish();
            }
        });

        EditText idUsu = (EditText) findViewById(R.id.idUsuario);
        id = idUsu.getText().toString();

        Button bEnviar = (Button) findViewById(R.id.bSol);
        bEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}