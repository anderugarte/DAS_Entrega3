package com.example.das_entregagrupal.Principal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.das_entregagrupal.Complementos.Ayuda;
import com.example.das_entregagrupal.Complementos.Opciones;
import com.example.das_entregagrupal.R;

public class MenuPrincipal extends AppCompatActivity {

    private AlertDialog.Builder alertDialogBuilder;
    private Context context;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        context = this;

        Button bJContraIA = (Button) findViewById(R.id.bJIA);
        Button bJ2Jugadores = (Button) findViewById(R.id.bJ2J);
        Button bJOnline = (Button) findViewById(R.id.bJO);
        Button bOpciones = (Button) findViewById(R.id.bO);
        ImageButton iAyuda = (ImageButton) findViewById(R.id.iAyuda);

        // Recibimos el nombre de usuario del usuario que se ha registrado al igual que el resto de sus datos
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("username");
        }

        bJContraIA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bJ2Jugadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bJOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        bOpciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent (getBaseContext(), Opciones.class);
                startActivity(o);
            }
        });

        iAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent (getBaseContext(), Ayuda.class);
                startActivity(a);
            }
        });

    }

    @Override
    public void onBackPressed(){

        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Bichötes: Conecta 4");
        alertDialogBuilder.setMessage("¿Desea cerrar la sesión?")
                .setCancelable(false)
                .setPositiveButton("Aceptar", (dialog, which) -> {
                    // Cerrar sesión
                    Intent ma = new Intent(context, MainActivity.class);
                    ma.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(ma);
                    finish();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel()).create().show();
    }

}