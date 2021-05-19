package com.example.das_entregagrupal.Principal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.das_entregagrupal.Modelo.Evento;
import com.example.das_entregagrupal.Modelo.Jugador;
import com.example.das_entregagrupal.Modelo.ListaJugadores;
import com.example.das_entregagrupal.Modelo.Partida;
import com.example.das_entregagrupal.Modelo.Tablero;
import com.example.das_entregagrupal.R;

public class PartidaV extends AppCompatActivity {

    private AlertDialog.Builder alertDialogBuilder;
    private String jugador1, jugador2;
    private int dificultad;
    private Boolean modoJuego;
    private Context context;
    private boolean comeplomoOn=false;
    private int numTurno = 1;
    private int numTotal = 1;
    private ImageView[][] casillas = new ImageView[7][6];

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
        casillas[0][0]=c11;
        ImageView c12 = findViewById(R.id.i12);
        c12.setImageResource(R.drawable.casilla);
        casillas[0][1]=c12;
        ImageView c13 = findViewById(R.id.i13);
        c13.setImageResource(R.drawable.casilla);
        casillas[0][2]=c13;
        ImageView c14 = findViewById(R.id.i14);
        c14.setImageResource(R.drawable.casilla);
        casillas[0][3]=c14;
        ImageView c15 = findViewById(R.id.i15);
        c15.setImageResource(R.drawable.casilla);
        casillas[0][4]=c15;
        ImageView c16 = findViewById(R.id.i16);
        c16.setImageResource(R.drawable.casilla);
        casillas[0][5]=c16;

        // Columna 2
        ImageView c21 = findViewById(R.id.i21);
        c21.setImageResource(R.drawable.casilla);
        casillas[1][0]=c21;
        ImageView c22 = findViewById(R.id.i22);
        c22.setImageResource(R.drawable.casilla);
        casillas[1][1]=c22;
        ImageView c23 = findViewById(R.id.i23);
        c23.setImageResource(R.drawable.casilla);
        casillas[1][2]=c23;
        ImageView c24 = findViewById(R.id.i24);
        c24.setImageResource(R.drawable.casilla);
        casillas[1][3]=c24;
        ImageView c25 = findViewById(R.id.i25);
        c25.setImageResource(R.drawable.casilla);
        casillas[1][4]=c25;
        ImageView c26 = findViewById(R.id.i26);
        c26.setImageResource(R.drawable.casilla);
        casillas[1][5]=c26;

        // Columna 3
        ImageView c31 = findViewById(R.id.i31);
        c31.setImageResource(R.drawable.casilla);
        casillas[2][0]=c31;
        ImageView c32 = findViewById(R.id.i32);
        c32.setImageResource(R.drawable.casilla);
        casillas[2][1]=c32;
        ImageView c33 = findViewById(R.id.i33);
        c33.setImageResource(R.drawable.casilla);
        casillas[2][2]=c33;
        ImageView c34 = findViewById(R.id.i34);
        c34.setImageResource(R.drawable.casilla);
        casillas[2][3]=c34;
        ImageView c35 = findViewById(R.id.i35);
        c35.setImageResource(R.drawable.casilla);
        casillas[2][4]=c35;
        ImageView c36 = findViewById(R.id.i36);
        c36.setImageResource(R.drawable.casilla);
        casillas[2][5]=c36;

        // Columna 4
        ImageView c41 = findViewById(R.id.i41);
        c41.setImageResource(R.drawable.casilla);
        casillas[3][0]=c41;
        ImageView c42 = findViewById(R.id.i42);
        c42.setImageResource(R.drawable.casilla);
        casillas[3][1]=c42;
        ImageView c43 = findViewById(R.id.i43);
        c43.setImageResource(R.drawable.casilla);
        casillas[3][2]=c43;
        ImageView c44 = findViewById(R.id.i44);
        c44.setImageResource(R.drawable.casilla);
        casillas[3][3]=c44;
        ImageView c45 = findViewById(R.id.i45);
        c45.setImageResource(R.drawable.casilla);
        casillas[3][4]=c45;
        ImageView c46 = findViewById(R.id.i46);
        c46.setImageResource(R.drawable.casilla);
        casillas[3][5]=c46;

        // Columna 5
        ImageView c51 = findViewById(R.id.i51);
        c51.setImageResource(R.drawable.casilla);
        casillas[4][0]=c51;
        ImageView c52 = findViewById(R.id.i52);
        c52.setImageResource(R.drawable.casilla);
        casillas[4][1]=c52;
        ImageView c53 = findViewById(R.id.i53);
        c53.setImageResource(R.drawable.casilla);
        casillas[4][2]=c53;
        ImageView c54 = findViewById(R.id.i54);
        c54.setImageResource(R.drawable.casilla);
        casillas[4][3]=c54;
        ImageView c55 = findViewById(R.id.i55);
        c55.setImageResource(R.drawable.casilla);
        casillas[4][4]=c55;
        ImageView c56 = findViewById(R.id.i56);
        c56.setImageResource(R.drawable.casilla);
        casillas[4][5]=c56;

        // Columna 6
        ImageView c61 = findViewById(R.id.i61);
        c61.setImageResource(R.drawable.casilla);
        casillas[5][0]=c61;
        ImageView c62 = findViewById(R.id.i62);
        c62.setImageResource(R.drawable.casilla);
        casillas[5][1]=c62;
        ImageView c63 = findViewById(R.id.i63);
        c63.setImageResource(R.drawable.casilla);
        casillas[5][2]=c63;
        ImageView c64 = findViewById(R.id.i64);
        c64.setImageResource(R.drawable.casilla);
        casillas[5][3]=c64;
        ImageView c65 = findViewById(R.id.i65);
        c65.setImageResource(R.drawable.casilla);
        casillas[5][4]=c65;
        ImageView c66 = findViewById(R.id.i66);
        c66.setImageResource(R.drawable.casilla);
        casillas[5][5]=c66;

        // Columna 7
        ImageView c71 = findViewById(R.id.i71);
        c71.setImageResource(R.drawable.casilla);
        casillas[6][0]=c71;
        ImageView c72 = findViewById(R.id.i72);
        c72.setImageResource(R.drawable.casilla);
        casillas[6][1]=c72;
        ImageView c73 = findViewById(R.id.i73);
        c73.setImageResource(R.drawable.casilla);
        casillas[6][2]=c73;
        ImageView c74 = findViewById(R.id.i74);
        c74.setImageResource(R.drawable.casilla);
        casillas[6][3]=c74;
        ImageView c75 = findViewById(R.id.i75);
        c75.setImageResource(R.drawable.casilla);
        casillas[6][4]=c75;
        ImageView c76 = findViewById(R.id.i76);
        c76.setImageResource(R.drawable.casilla);
        casillas[6][5]=c76;

        TextView j1 = (TextView) findViewById(R.id.tJugador1);
        TextView j2 = (TextView) findViewById(R.id.tJugador2);
        TextView evento = (TextView) findViewById(R.id.tEventos);
        TextView turno = (TextView) findViewById(R.id.tTurno);

        // Recibimos los nombres de los jugadores
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jugador1 = extras.getString("jugador1");
            jugador2 = extras.getString("jugador2");
            dificultad = extras.getInt("dificultad");
            // Si el modo juego es True, la partida será contra la IA
            modoJuego = extras.getBoolean("modoJuego");
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
        if(modoJuego){
            Partida.getPartida().inicializarJugadorYMaq(jugador1,dificultad);
            jugarIA();
        }
        else{
            Partida.getPartida().inicializarJugadores(jugador1,jugador2);
            jugar();
        }
        Tablero.getTablero().inicializarTablero();
    }

    private void jugar() {
        while(!Partida.getPartida().partidaAcabada()){
            Button b1 = findViewById(R.id.b1);
            Button b2 = findViewById(R.id.b2);
            Button b3 = findViewById(R.id.b3);
            Button b4 = findViewById(R.id.b4);
            Button b5 = findViewById(R.id.b5);
            Button b6 = findViewById(R.id.b6);
            Button b7 = findViewById(R.id.b7);

            b1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (comeplomoOn) {
                        Tablero.getTablero().colocarComeplomo(1);
                        comeplomoOn = false;
                        ListaJugadores.getListaJugadores().obtenerJugador(numTurno).getFichas().eliminarComeplomo();

                        actualizarCasillasEvento();
                        cambiarTurno();
                        numTotal++;
                    } else {
                        Jugador j = ListaJugadores.getListaJugadores().obtenerJugador(numTurno);
                        if (j.colocarFicha(1)) {
                            actualizarCasillas();
                        }else{
                            String text = "Columna llena!";
                            Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }
                }
            });
            b2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (comeplomoOn) {
                        Tablero.getTablero().colocarComeplomo(2);
                        comeplomoOn = false;
                        ListaJugadores.getListaJugadores().obtenerJugador(numTurno).getFichas().eliminarComeplomo();

                        actualizarCasillasEvento();
                        cambiarTurno();
                        numTotal++;
                    } else {
                        Jugador j = ListaJugadores.getListaJugadores().obtenerJugador(numTurno);
                        if (j.colocarFicha(2)) {
                            actualizarCasillas();
                        }else{
                            String text = "Columna llena!";
                            Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }
                }
            });
            b3.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (comeplomoOn) {
                        Tablero.getTablero().colocarComeplomo(3);
                        comeplomoOn = false;
                        ListaJugadores.getListaJugadores().obtenerJugador(numTurno).getFichas().eliminarComeplomo();

                        actualizarCasillasEvento();
                        cambiarTurno();
                        numTotal++;
                    } else {
                        Jugador j = ListaJugadores.getListaJugadores().obtenerJugador(numTurno);
                        if (j.colocarFicha(3)) {
                            actualizarCasillas();
                        }else{
                            String text = "Columna llena!";
                            Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }
                }
            });
            b4.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (comeplomoOn) {
                        Tablero.getTablero().colocarComeplomo(4);
                        comeplomoOn = false;
                        ListaJugadores.getListaJugadores().obtenerJugador(numTurno).getFichas().eliminarComeplomo();

                        actualizarCasillasEvento();
                        cambiarTurno();
                        numTotal++;
                    } else {
                        Jugador j = ListaJugadores.getListaJugadores().obtenerJugador(numTurno);
                        if (j.colocarFicha(4)) {
                            actualizarCasillas();
                        }else{
                            String text = "Columna llena!";
                            Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }
                }
            });
            b5.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (comeplomoOn) {
                        Tablero.getTablero().colocarComeplomo(5);
                        comeplomoOn = false;
                        ListaJugadores.getListaJugadores().obtenerJugador(numTurno).getFichas().eliminarComeplomo();

                        actualizarCasillasEvento();
                        cambiarTurno();
                        numTotal++;
                    } else {
                        Jugador j = ListaJugadores.getListaJugadores().obtenerJugador(numTurno);
                        if (j.colocarFicha(5)) {
                            actualizarCasillas();
                        }else{
                            String text = "Columna llena!";
                            Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }
                }
            });
            b6.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (comeplomoOn) {
                        Tablero.getTablero().colocarComeplomo(6);
                        comeplomoOn = false;
                        ListaJugadores.getListaJugadores().obtenerJugador(numTurno).getFichas().eliminarComeplomo();

                        actualizarCasillasEvento();
                        cambiarTurno();
                        numTotal++;
                    } else {
                        Jugador j = ListaJugadores.getListaJugadores().obtenerJugador(numTurno);
                        if (j.colocarFicha(6)) {
                            actualizarCasillas();
                        }else{
                            String text = "Columna llena!";
                            Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }
                }
            });
            b7.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (comeplomoOn) {
                        Tablero.getTablero().colocarComeplomo(7);
                        comeplomoOn = false;
                        ListaJugadores.getListaJugadores().obtenerJugador(numTurno).getFichas().eliminarComeplomo();

                        actualizarCasillasEvento();
                        cambiarTurno();
                        numTotal++;
                    } else {
                        Jugador j = ListaJugadores.getListaJugadores().obtenerJugador(numTurno);
                        if (j.colocarFicha(7)) {
                            actualizarCasillas();
                        }else{
                            String text = "Columna llena!";
                            Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                            toast.show();
                        }
                    }
                }
            });
        }
    }

    private void jugarIA() {
    }

    private void actualizarCasillas(){
        for (int fila = 0; fila < 7; fila++) {
            for (int columna = 0; columna < 6; columna++) {
                char estado = Tablero.getTablero().getEstado(fila, columna);
                if (estado == '1') {
                    casillas[fila][columna].setImageResource(R.drawable.casillarojo);
                } else if (estado == '2') {
                    casillas[fila][columna].setImageResource(R.drawable.casillaazul);
                } else if (estado == 'P') {
                    casillas[fila][columna].setImageResource(R.drawable.casillapiquete);
                } else {
                    casillas[fila][columna].setImageResource(R.drawable.casilla);
                }
            }
        }
        comprobarSiGanado();
        cambiarTurno();
        numTotal++;
        if (this.numTotal % 5 == 0) {
            Evento.getEvento().generarEvento();
            actualizarCasillasEvento();
        }
//        if (ListaJugadores.getListaJugadores().obtenerJugador(numTurno).getFichas().obtenerTotalComeplomos() != 0) {
//            if (numTurno == 1) {
//                btnUsarizq.setEnabled(true);
//                btnUsarder.setEnabled(false);
//            } else {
//                btnUsarder.setEnabled(true);
//                btnUsarizq.setEnabled(false);
//            }
//        }
    }

    private void comprobarSiGanado() {
//        boolean salir = false;
//        if (modoJuego) {
//            if (Tablero.getTablero().comprobarCuatro(numTurno)) {
//                if (numTurno == 1) {
//                    Victoria1Vs1 window = new Victoria1Vs1(idioma, modo, nombre, nombre1);
//                    window.setVisible(true);
//                    IU_Partida.dispose();
//                    salir = true;
//
//                } else {
//                    Victoria1Vs1 window = new Victoria1Vs1(idioma, modo, nombre1, nombre);
//                    window.setVisible(true);
//                    numTurno = 1;
//                    IU_Partida.dispose();
//                    salir = true;
//                }
//                ListaJugadores.getListaJugadores().obtenerJugador(1).getFichas().resetear();
//                ListaJugadores.getListaJugadores().obtenerJugador(2).getFichas().resetear();
//
//            }
//        } else if (!modoJuego && numTurno == 2) {
//            if (Tablero.getTablero().comprobarCuatro(numTurno)) {
//                Derrota window = new Derrota(idioma, modo, nombre, dif);
//                window.setVisible(true);
//                IU_Partida.dispose();
//                ListaJugadores.getListaJugadores().obtenerJugador(1).getFichas().resetear();
//                ListaJugadores.getListaJugadores().obtenerJugador(2).getFichas().resetear();
//                salir = true;
//            }
//
//        } else {
//            if (Tablero.getTablero().comprobarCuatro(numTurno)) {
//                Victoria window = new Victoria(idioma, modo, nombre, Integer.toString(Sesion.getSesion().getTiempo()),
//                        dif);
//                window.setVisible(true);
//                IU_Partida.dispose();
//                ListaJugadores.getListaJugadores().obtenerJugador(1).getFichas().resetear();
//                ListaJugadores.getListaJugadores().obtenerJugador(2).getFichas().resetear();
//                salir = true;
//            }
//        }
//        if (salir) {
//            Tablero.getTablero().resetear();
//            Sesion.getSesion().terminar();
//        }
    }

    private void actualizarCasillasEvento(){
        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 7; columna++) {
                char estado = Tablero.getTablero().getEstado(fila, columna);
                if (estado == '1') {
                    casillas[fila][columna].setImageResource(R.drawable.casillarojo);
                } else if (estado == '2') {
                    casillas[fila][columna].setImageResource(R.drawable.casillaazul);
                } else if (estado == 'P') {
                    casillas[fila][columna].setImageResource(R.drawable.casillapiquete);
                } else {
                    casillas[fila][columna].setImageResource(R.drawable.casilla);
                }
            }
        }
        comprobarSiGanado();
    }

    private void cambiarTurno(){
        if (numTurno == 1) {
            numTurno = 2;
            if (modoJuego) {
                actualizarCasillasEvento();
                ListaJugadores.getListaJugadores().obtenerJugador(numTurno).colocarFicha(0);
                actualizarCasillas();
            }
        } else {
            numTurno = 1;
        }
    }

    private AlertDialog createDialogoAyudaEnPartida() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_ayudaenpartida, null);
        builder.setView(v);
        builder.setCancelable(false);
        builder.setPositiveButton("Aceptar", (dialog, which) -> dialog.cancel());

        TextView texto = (TextView) v.findViewById(R.id.txtAyuda);
        texto.setMovementMethod(new ScrollingMovementMethod());

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