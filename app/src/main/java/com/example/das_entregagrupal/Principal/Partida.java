package com.example.das_entregagrupal.Principal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.das_entregagrupal.R;

import org.w3c.dom.Text;

public class Partida extends AppCompatActivity {

    private AlertDialog.Builder alertDialogBuilder;
    private String jugador1, jugador2, dificultad;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        context = this;

//        ImageView i8 = findViewById(R.id.imageView8);
//        i8.setImageResource(R.drawable.perfil);

        // Inicializar todas las casillas (inicializar tablero)

        // Columna 1
        ImageView c11 = findViewById(R.id.i11);
        c11.setImageResource(R.drawable.casilla);
        ImageView c12 = findViewById(R.id.i12);
        c12.setImageResource(R.drawable.casilla);
        ImageView c13 = findViewById(R.id.i13);
        c13.setImageResource(R.drawable.casilla);
        ImageView c14 = findViewById(R.id.i14);
        c14.setImageResource(R.drawable.casilla);
        ImageView c15 = findViewById(R.id.i15);
        c15.setImageResource(R.drawable.casilla);
        ImageView c16 = findViewById(R.id.i16);
        c16.setImageResource(R.drawable.casilla);

        // Columna 2
        ImageView c21 = findViewById(R.id.i21);
        c21.setImageResource(R.drawable.casilla);
        ImageView c22 = findViewById(R.id.i22);
        c22.setImageResource(R.drawable.casilla);
        ImageView c23 = findViewById(R.id.i23);
        c23.setImageResource(R.drawable.casilla);
        ImageView c24 = findViewById(R.id.i24);
        c24.setImageResource(R.drawable.casilla);
        ImageView c25 = findViewById(R.id.i25);
        c25.setImageResource(R.drawable.casilla);
        ImageView c26 = findViewById(R.id.i26);
        c26.setImageResource(R.drawable.casilla);

        // Columna 3
        ImageView c31 = findViewById(R.id.i31);
        c31.setImageResource(R.drawable.casilla);
        ImageView c32 = findViewById(R.id.i32);
        c32.setImageResource(R.drawable.casilla);
        ImageView c33 = findViewById(R.id.i33);
        c33.setImageResource(R.drawable.casilla);
        ImageView c34 = findViewById(R.id.i34);
        c34.setImageResource(R.drawable.casilla);
        ImageView c35 = findViewById(R.id.i35);
        c35.setImageResource(R.drawable.casilla);
        ImageView c36 = findViewById(R.id.i36);
        c36.setImageResource(R.drawable.casilla);

        // Columna 4
        ImageView c41 = findViewById(R.id.i41);
        c41.setImageResource(R.drawable.casilla);
        ImageView c42 = findViewById(R.id.i42);
        c42.setImageResource(R.drawable.casilla);
        ImageView c43 = findViewById(R.id.i43);
        c43.setImageResource(R.drawable.casilla);
        ImageView c44 = findViewById(R.id.i44);
        c44.setImageResource(R.drawable.casilla);
        ImageView c45 = findViewById(R.id.i45);
        c45.setImageResource(R.drawable.casilla);
        ImageView c46 = findViewById(R.id.i46);
        c46.setImageResource(R.drawable.casilla);

        // Columna 5
        ImageView c51 = findViewById(R.id.i51);
        c51.setImageResource(R.drawable.casilla);
        ImageView c52 = findViewById(R.id.i52);
        c52.setImageResource(R.drawable.casilla);
        ImageView c53 = findViewById(R.id.i53);
        c53.setImageResource(R.drawable.casilla);
        ImageView c54 = findViewById(R.id.i54);
        c54.setImageResource(R.drawable.casilla);
        ImageView c55 = findViewById(R.id.i55);
        c55.setImageResource(R.drawable.casilla);
        ImageView c56 = findViewById(R.id.i56);
        c56.setImageResource(R.drawable.casilla);

        // Columna 6
        ImageView c61 = findViewById(R.id.i61);
        c61.setImageResource(R.drawable.casilla);
        ImageView c62 = findViewById(R.id.i62);
        c62.setImageResource(R.drawable.casilla);
        ImageView c63 = findViewById(R.id.i63);
        c63.setImageResource(R.drawable.casilla);
        ImageView c64 = findViewById(R.id.i64);
        c64.setImageResource(R.drawable.casilla);
        ImageView c65 = findViewById(R.id.i65);
        c65.setImageResource(R.drawable.casilla);
        ImageView c66 = findViewById(R.id.i66);
        c66.setImageResource(R.drawable.casilla);

        // Columna 7
        ImageView c71 = findViewById(R.id.i71);
        c71.setImageResource(R.drawable.casilla);
        ImageView c72 = findViewById(R.id.i72);
        c72.setImageResource(R.drawable.casilla);
        ImageView c73 = findViewById(R.id.i73);
        c73.setImageResource(R.drawable.casilla);
        ImageView c74 = findViewById(R.id.i74);
        c74.setImageResource(R.drawable.casilla);
        ImageView c75 = findViewById(R.id.i75);
        c75.setImageResource(R.drawable.casilla);
        ImageView c76 = findViewById(R.id.i76);
        c76.setImageResource(R.drawable.casilla);

        TextView j1 = (TextView) findViewById(R.id.tJugador1);
        TextView j2 = (TextView) findViewById(R.id.tJugador2);
        TextView evento = (TextView) findViewById(R.id.tEventos);
        TextView turno = (TextView) findViewById(R.id.tTurno);

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

        // Pulsando este boton se finalizara la partida
        Button bRendir = (Button) findViewById(R.id.bRendirse);
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

        // Boton que genera un dialogo ofreciendo la ayuda al usuario
        ImageButton bAyudaEnJuego = (ImageButton) findViewById(R.id.bAyudaPartida);
        bAyudaEnJuego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialogoAyudaEnPartida().show();
            }
        });

        //
        Button bCmplm = (Button) findViewById(R.id.bComeplomo);
        bCmplm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        // Gestion de comeplomos
        ImageView iComeplomo = (ImageView) findViewById(R.id.iconCmplm);
        TextView numC = (TextView) findViewById(R.id.numComeplomos);

        // Gestion del turno
//        if () {
//            turno.setText("¡Es tu turno " + j1.getText().toString());
//        } else {
//            turno.setText("¡Es tu turno " + j2.getText().toString());
//        }

        // Gestion de eventos
//        if () {
//            evento.setTextSize(24);
//            evento.setText("Achuuuusss! Ups, se me han volado las fichas. Vais a tener que volver a empezar...");
//        } else if () {
//            evento.setTextSize(24);
//            evento.setText("Parece que alguien a abducido la comuna " + (num+1) + " entera...");
//        } else if () {
//            evento.setTextSize(24);
//            evento.setText("Para vosotros jugadores. Una ficha comeplomo para los dos.");
//        }

    }

    private AlertDialog createDialogoAyudaEnPartida() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_ayudaenpartida, null);
        builder.setView(v);
        builder.setCancelable(false);
        builder.setPositiveButton("Aceptar", (dialog, which) -> dialog.cancel());
        return builder.create();

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