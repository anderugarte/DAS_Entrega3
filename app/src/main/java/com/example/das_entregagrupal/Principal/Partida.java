package com.example.das_entregagrupal.Principal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.das_entregagrupal.R;

public class Partida extends AppCompatActivity {

    private AlertDialog.Builder alertDialogBuilder;
    private String jugador1, jugador2, dificultad;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        context = this;

        ImageView i8 = findViewById(R.id.imageView8);
        i8.setImageResource(R.drawable.perfil);

        TextView j1 = (TextView) findViewById(R.id.tJugador1);
        TextView j2 = (TextView) findViewById(R.id.tJugador2);
        Button bRendir = (Button) findViewById(R.id.bRendirse);

        // Recibimos los nombres de los jugadores
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jugador1 = extras.getString("jugador1");
            jugador2 = extras.getString("jugador2");
            dificultad = extras.getString("dificultad");
        }

        // Escribimos los datos de los jugadores
        j1.setText(jugador1);
        j2.setText(jugador2);

        bRendir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Bichötes: Conecta 4");
                alertDialogBuilder.setMessage("¿Estás seguro que deseas abandonar la partida?")
                        .setCancelable(false)
                        .setPositiveButton("Rendirse", (dialog, which) -> {
                            // Abandonar partida
                            Intent mp = new Intent(context, MenuPrincipal.class);
                            mp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(mp);
                            finish();
                        })
                        .setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel()).create().show();
            }
        });

    }

    @Override
    public void onBackPressed(){
        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Bichötes: Conecta 4");
        alertDialogBuilder.setMessage("¿Estás seguro que deseas abandonar la partida?")
                .setCancelable(false)
                .setPositiveButton("Rendirse", (dialog, which) -> {
                    // Abandonar partida
                    Intent mp = new Intent(context, MenuPrincipal.class);
                    mp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mp);
                    finish();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel()).create().show();
    }

}