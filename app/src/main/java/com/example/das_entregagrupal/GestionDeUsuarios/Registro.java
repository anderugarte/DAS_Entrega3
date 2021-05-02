package com.example.das_entregagrupal.GestionDeUsuarios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.das_entregagrupal.Principal.ClaseDialogoFecha;
import com.example.das_entregagrupal.Principal.MenuPrincipal;
import com.example.das_entregagrupal.R;

public class Registro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        EditText etCumple = (EditText) findViewById(R.id.etCumpleanos);
        Button bInicSes = (Button) findViewById(R.id.bYTC);

        // Al pulsar este EditText, desplegaremos un dialogo donde se podra seleccionar la fecha de nacimientop del usuario
        etCumple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.etCumpleanos:
                        showDatePickerDialog(etCumple);
                        break;
                }
            }
        });

        bInicSes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is = new Intent (getBaseContext(), InicioSesion.class);
                startActivity(is);
                finish();
            }
        });

        Button bRegistro = (Button) findViewById(R.id.bRegistro);

        bRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mp = new Intent (getBaseContext(), MenuPrincipal.class);
                startActivity(mp);
                finish();
            }
        });
    }

    // Este metodo nos ayuda a desplegar el dialogo para la seleccion de la fecha de nacimiento y nos permite enviar
    // el EditText por parametro para una vez obtenida la fecha poder realizar un .setText("fecha") en el
    private void showDatePickerDialog(final EditText editText) {
        ClaseDialogoFecha dialogoCumpleanos = new ClaseDialogoFecha(editText);
        dialogoCumpleanos.show(getSupportFragmentManager(),"cumple");
    }

}