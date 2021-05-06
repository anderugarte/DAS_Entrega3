package com.example.das_entregagrupal.Complementos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.das_entregagrupal.GestionDeUsuarios.InicioSesion;
import com.example.das_entregagrupal.Principal.MenuPrincipal;
import com.example.das_entregagrupal.R;

public class Ayuda extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ayuda);

        TextView tA = (TextView) findViewById(R.id.tAyuda);
        tA.setMovementMethod(new ScrollingMovementMethod());

        ImageButton bAtras = (ImageButton) findViewById(R.id.bAtras);

        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mp = new Intent (getBaseContext(), MenuPrincipal.class);
                startActivity(mp);
                finish();
            }
        });
    }
}