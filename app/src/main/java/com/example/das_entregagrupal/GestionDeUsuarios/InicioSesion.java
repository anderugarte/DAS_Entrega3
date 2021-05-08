package com.example.das_entregagrupal.GestionDeUsuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.AutoText;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.das_entregagrupal.Principal.MenuPrincipal;
import com.example.das_entregagrupal.R;

public class InicioSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        EditText etNU = (EditText) findViewById(R.id.etNU);
        EditText etC = (EditText) findViewById(R.id.etC);

        Button bNTCA = (Button) findViewById(R.id.bNTCA);

        bNTCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent (getBaseContext(), Registro.class);
                startActivity(r);
                finish();
            }
        });

        Button bIniciarSesion = (Button) findViewById(R.id.bIniciarSesion);

        bIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mp = new Intent (getBaseContext(), MenuPrincipal.class);
                mp.putExtra("username",etNU.getText().toString());
                startActivity(mp);
                finish();
            }
        });

    }
}