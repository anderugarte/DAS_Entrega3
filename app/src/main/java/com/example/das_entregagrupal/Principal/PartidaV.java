package com.example.das_entregagrupal.Principal;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.example.das_entregagrupal.BaseDeDatos.ConexionSumarPuntos;
import com.example.das_entregagrupal.Complementos.Puntuaciones;
import com.example.das_entregagrupal.Modelo.Evento;
import com.example.das_entregagrupal.Modelo.Jugador;
import com.example.das_entregagrupal.Modelo.ListaJugadores;
import com.example.das_entregagrupal.Modelo.Tablero;
import com.example.das_entregagrupal.R;

public class PartidaV extends AppCompatActivity {

    private AlertDialog.Builder alertDialogBuilder;
    private AlertDialog.Builder alertDialogBuilder2;
    private String jugador1, jugador2;
    private int dificultad;
    private Boolean modoJuego;
    private Boolean salir;
    private boolean online;
    private Context context;
    private boolean comeplomoOn=false;
    private int numTurno = 1;
    private int numTotal = 1;
    private ImageView[][] casillas = new ImageView[6][7];
    private Button b1, b2, b3, b4, b5, b6, b7;
    private Button bCmplm, bAmis;
    private TextView j1,j2,evento,turno,numComeplomo,ganador,puntaje;
    private ImageView iComeplomo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_partida);

        context = this;

        // El modo de juego online esta sin implementar
        online = false;

        // Inicializar los selectores de columnas
        b1 = findViewById(R.id.b1);
        b2 = findViewById(R.id.b2);
        b3 = findViewById(R.id.b3);
        b4 = findViewById(R.id.b4);
        b5 = findViewById(R.id.b5);
        b6 = findViewById(R.id.b6);
        b7 = findViewById(R.id.b7);

        // Inicializar todas las casillas (inicializar tablero)

        // Columna 1
        ImageView c11 = findViewById(R.id.i11);
        c11.setImageResource(R.drawable.casilla);
        casillas[5][0]=c11;
        ImageView c12 = findViewById(R.id.i12);
        c12.setImageResource(R.drawable.casilla);
        casillas[4][0]=c12;
        ImageView c13 = findViewById(R.id.i13);
        c13.setImageResource(R.drawable.casilla);
        casillas[3][0]=c13;
        ImageView c14 = findViewById(R.id.i14);
        c14.setImageResource(R.drawable.casilla);
        casillas[2][0]=c14;
        ImageView c15 = findViewById(R.id.i15);
        c15.setImageResource(R.drawable.casilla);
        casillas[1][0]=c15;
        ImageView c16 = findViewById(R.id.i16);
        c16.setImageResource(R.drawable.casilla);
        casillas[0][0]=c16;

        // Columna 2
        ImageView c21 = findViewById(R.id.i21);
        c21.setImageResource(R.drawable.casilla);
        casillas[5][1]=c21;
        ImageView c22 = findViewById(R.id.i22);
        c22.setImageResource(R.drawable.casilla);
        casillas[4][1]=c22;
        ImageView c23 = findViewById(R.id.i23);
        c23.setImageResource(R.drawable.casilla);
        casillas[3][1]=c23;
        ImageView c24 = findViewById(R.id.i24);
        c24.setImageResource(R.drawable.casilla);
        casillas[2][1]=c24;
        ImageView c25 = findViewById(R.id.i25);
        c25.setImageResource(R.drawable.casilla);
        casillas[1][1]=c25;
        ImageView c26 = findViewById(R.id.i26);
        c26.setImageResource(R.drawable.casilla);
        casillas[0][1]=c26;

        // Columna 3
        ImageView c31 = findViewById(R.id.i31);
        c31.setImageResource(R.drawable.casilla);
        casillas[5][2]=c31;
        ImageView c32 = findViewById(R.id.i32);
        c32.setImageResource(R.drawable.casilla);
        casillas[4][2]=c32;
        ImageView c33 = findViewById(R.id.i33);
        c33.setImageResource(R.drawable.casilla);
        casillas[3][2]=c33;
        ImageView c34 = findViewById(R.id.i34);
        c34.setImageResource(R.drawable.casilla);
        casillas[2][2]=c34;
        ImageView c35 = findViewById(R.id.i35);
        c35.setImageResource(R.drawable.casilla);
        casillas[1][2]=c35;
        ImageView c36 = findViewById(R.id.i36);
        c36.setImageResource(R.drawable.casilla);
        casillas[0][2]=c36;

        // Columna 4
        ImageView c41 = findViewById(R.id.i41);
        c41.setImageResource(R.drawable.casilla);
        casillas[5][3]=c41;
        ImageView c42 = findViewById(R.id.i42);
        c42.setImageResource(R.drawable.casilla);
        casillas[4][3]=c42;
        ImageView c43 = findViewById(R.id.i43);
        c43.setImageResource(R.drawable.casilla);
        casillas[3][3]=c43;
        ImageView c44 = findViewById(R.id.i44);
        c44.setImageResource(R.drawable.casilla);
        casillas[2][3]=c44;
        ImageView c45 = findViewById(R.id.i45);
        c45.setImageResource(R.drawable.casilla);
        casillas[1][3]=c45;
        ImageView c46 = findViewById(R.id.i46);
        c46.setImageResource(R.drawable.casilla);
        casillas[0][3]=c46;

        // Columna 5
        ImageView c51 = findViewById(R.id.i51);
        c51.setImageResource(R.drawable.casilla);
        casillas[5][4]=c51;
        ImageView c52 = findViewById(R.id.i52);
        c52.setImageResource(R.drawable.casilla);
        casillas[4][4]=c52;
        ImageView c53 = findViewById(R.id.i53);
        c53.setImageResource(R.drawable.casilla);
        casillas[3][4]=c53;
        ImageView c54 = findViewById(R.id.i54);
        c54.setImageResource(R.drawable.casilla);
        casillas[2][4]=c54;
        ImageView c55 = findViewById(R.id.i55);
        c55.setImageResource(R.drawable.casilla);
        casillas[1][4]=c55;
        ImageView c56 = findViewById(R.id.i56);
        c56.setImageResource(R.drawable.casilla);
        casillas[0][4]=c56;

        // Columna 6
        ImageView c61 = findViewById(R.id.i61);
        c61.setImageResource(R.drawable.casilla);
        casillas[5][5]=c61;
        ImageView c62 = findViewById(R.id.i62);
        c62.setImageResource(R.drawable.casilla);
        casillas[4][5]=c62;
        ImageView c63 = findViewById(R.id.i63);
        c63.setImageResource(R.drawable.casilla);
        casillas[3][5]=c63;
        ImageView c64 = findViewById(R.id.i64);
        c64.setImageResource(R.drawable.casilla);
        casillas[2][5]=c64;
        ImageView c65 = findViewById(R.id.i65);
        c65.setImageResource(R.drawable.casilla);
        casillas[1][5]=c65;
        ImageView c66 = findViewById(R.id.i66);
        c66.setImageResource(R.drawable.casilla);
        casillas[0][5]=c66;

        // Columna 7
        ImageView c71 = findViewById(R.id.i71);
        c71.setImageResource(R.drawable.casilla);
        casillas[5][6]=c71;
        ImageView c72 = findViewById(R.id.i72);
        c72.setImageResource(R.drawable.casilla);
        casillas[4][6]=c72;
        ImageView c73 = findViewById(R.id.i73);
        c73.setImageResource(R.drawable.casilla);
        casillas[3][6]=c73;
        ImageView c74 = findViewById(R.id.i74);
        c74.setImageResource(R.drawable.casilla);
        casillas[2][6]=c74;
        ImageView c75 = findViewById(R.id.i75);
        c75.setImageResource(R.drawable.casilla);
        casillas[1][6]=c75;
        ImageView c76 = findViewById(R.id.i76);
        c76.setImageResource(R.drawable.casilla);
        casillas[0][6]=c76;

        j1 = (TextView) findViewById(R.id.tJugador1);
        j2 = (TextView) findViewById(R.id.tJugador2);
        evento = (TextView) findViewById(R.id.tEventos);
        turno = (TextView) findViewById(R.id.tTurno);
        numComeplomo = (TextView) findViewById(R.id.numComeplomos);

        // Recibimos los nombres de los jugadores
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            jugador1 = extras.getString("jugador1");
            jugador2 = extras.getString("jugador2");
            // 0 modo facil y 1 modo dificil
            dificultad = extras.getInt("dificultad");
            // Si el modo juego es True, la partida será contra la IA
            modoJuego = extras.getBoolean("modoJuego");
        }

        // Escribimos los datos de los jugadores
        j1.setText(jugador1);
        j2.setText(jugador2);

        // Pulsando este boton, cuando nos encontramos en una partida online,
        // se envia una solicitud de amistad a nuestro rival
        bAmis = (Button) findViewById(R.id.bAmistad);
        bAmis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Enviar solicitud de amistad
                if (modoJuego) {
                    alertDialogBuilder2 = new AlertDialog.Builder(context);
                    alertDialogBuilder2.setTitle("Bichötes: Conecta 4");
                    alertDialogBuilder2.setMessage("No puedes enviar una solicitud de amistad a la IA")
                            .setCancelable(false)
                            .setNegativeButton("Aceptar", (dialog, which) -> dialog.cancel()).create().show();
                } else {
                    if (online) {
                        createDialogoAmistad().show();
                    } else {
                        alertDialogBuilder2 = new AlertDialog.Builder(context);
                        alertDialogBuilder2.setTitle("Bichötes: Conecta 4");
                        alertDialogBuilder2.setMessage("No puedes enviar una solicitud a un jugador en local")
                                .setCancelable(false)
                                .setNegativeButton("Aceptar", (dialog, which) -> dialog.cancel()).create().show();
                    }
                }
            }
        });

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
                            Tablero.getTablero().resetear();
                            Intent mp = new Intent(context, MenuPrincipal.class);
                            mp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            mp.putExtra("username",jugador1);
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

        // Gestion de comeplomos
        iComeplomo = (ImageView) findViewById(R.id.iconCmplm);
        TextView numC = (TextView) findViewById(R.id.numComeplomos);

        // Boton que gestiona el uso de un comeplomo
        bCmplm = (Button) findViewById(R.id.bComeplomo);
        bCmplm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ListaJugadores.getListaJugadores().obtenerJugador(numTurno).getFichas().obtenerTotalComeplomos() != 0) {
                    if (comeplomoOn) { // Desactivar comeplomo
                        comeplomoOn = false;
                    } else { // Activar comeplomo
                        comeplomoOn = true;
                    }
                }
            }
        });

        // Gestion del turno
        if (numTurno == 1) {
            turno.setText("¡Es tu turno " + j1.getText().toString() + "!");
        } else {
            turno.setText("¡Es tu turno " + j2.getText().toString() + "!");
        }

        // Gestion de eventos
        evento.setTextSize(18);

        // Establecer el modo de juego
        Tablero.getTablero().inicializarTablero();
//        if (modoJuego){
//            Partida.getPartida().inicializarJugadorYMaq(jugador1,dificultad);
//        } else {
//            Partida.getPartida().inicializarJugadores(jugador1,jugador2);
//        }

        //jugar();
        // Boton correspondiente a la columna 1 del tablero
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
                    if (j.colocarFicha(1)) {actualizarCasillas();}
                    else {
                        String text = "Columna llena!";
                        Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });

        // Boton correspondiente a la columna 2 del tablero
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
                    if (j.colocarFicha(2)) {actualizarCasillas();}
                    else {
                        String text = "Columna llena!";
                        Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });

        // Boton correspondiente a la columna 3 del tablero
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
                    if (j.colocarFicha(3)) {actualizarCasillas();}
                    else {
                        String text = "Columna llena!";
                        Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });

        // Boton correspondiente a la columna 4 del tablero
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
                    if (j.colocarFicha(4)) {actualizarCasillas();}
                    else {
                        String text = "Columna llena!";
                        Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });

        // Boton correspondiente a la columna 5 del tablero
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
                    if (j.colocarFicha(5)) {actualizarCasillas();}
                    else{
                        String text = "Columna llena!";
                        Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });

        // Boton correspondiente a la columna 6 del tablero
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
                    if (j.colocarFicha(6)) {actualizarCasillas();}
                    else {
                        String text = "Columna llena!";
                        Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });

        // Boton correspondiente a la columna 7 del tablero
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
                    if (j.colocarFicha(7)) {actualizarCasillas();}
                    else {
                        String text = "Columna llena!";
                        Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        toast.show();
                    }
                }
            }
        });
    }

    // Metodo para comprobar si alguien ha ganado
    // En el modo de 2 juagores, solo se suman los puntos al primer jugador, ya que el segundo
    //      no está en la base de datos
    // En el modo de IA, se le sumarán puntos si gana el usuario
    private void comprobarSiGanado() {
        salir = false;
        if (modoJuego) { // Partida contra la IA
            if (Tablero.getTablero().comprobarCuatro(numTurno)) {
                if (numTurno == 1) {
                    if (dificultad == 0) { // El jugador gana en modo facil
                        // php que actualiza los puntos del jugador
                        sumarPuntos("10");
                        salir = true;
                    } else if (dificultad == 1) { // El jugador gana en modo dificil
                        // php que actualiza los puntos del jugador
                        sumarPuntos("50");
                        salir = true;
                    }
                } else {
                    generateDialogoDerrota(jugador1).show();
                    salir = true;
                }
//                ListaJugadores.getListaJugadores().obtenerJugador(1).getFichas().resetear();
//                ListaJugadores.getListaJugadores().obtenerJugador(2).getFichas().resetear();
            }
        } else if (!modoJuego) {
            if (Tablero.getTablero().comprobarCuatro(numTurno)) {
                if (numTurno == 1) {
                    sumarPuntos("25");
                    salir = true;
                } else {
                    generateDialogoVictoria(jugador2,0).show();
                    salir = true;
                }
            }
        } else {
//            if (Tablero.getTablero().comprobarCuatro(numTurno)) {
//                Victoria window = new Victoria(idioma, modo, nombre, Integer.toString(Sesion.getSesion().getTiempo()),
//                        dif);
//                window.setVisible(true);
//                IU_Partida.dispose();
//                ListaJugadores.getListaJugadores().obtenerJugador(1).getFichas().resetear();
//                ListaJugadores.getListaJugadores().obtenerJugador(2).getFichas().resetear();
//                salir = true;
//            }
        }
        if (salir) {
            Tablero.getTablero().resetear();
        }
    }

    // Método para sumarle los puntos correspondientes al jugador cuando gane
    private void sumarPuntos(String puntos) {

        Data datos = new Data.Builder()
                .putString("username", jugador1)
                .putString("puntos", puntos)
                .build();

        OneTimeWorkRequest otwr = new OneTimeWorkRequest.Builder(ConexionSumarPuntos.class)
                .setInputData(datos)
                .build();

        WorkManager.getInstance(getApplicationContext()).getWorkInfoByIdLiveData(otwr.getId())
            .observe(this, new Observer<WorkInfo>() {
                @Override
                public void onChanged(WorkInfo workInfo) {
                    if (workInfo != null && workInfo.getState().isFinished()) {
                        Log.i("hola", workInfo.getOutputData().getString("result"));
                        if (workInfo.getOutputData().getString("result").equals("done")) {
                            generateDialogoVictoria(jugador1, Integer.valueOf(puntos)).show();
                        }
                    }
                }
            });
        WorkManager.getInstance(getBaseContext()).enqueue(otwr);
    }

    // Se actualizan las casillas tras suceder un evento
    private void actualizarCasillasEvento(){
        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 7; columna++) {
                char estado = Tablero.getTablero().getEstado(fila, columna);
                if (estado == '1') { // Ficha del jugador 1
                    casillas[fila][columna].setImageResource(R.drawable.casillarojo);
                } else if (estado == '2') { // Ficha del jugador 2
                    casillas[fila][columna].setImageResource(R.drawable.casillaazul);
                } else if (estado == 'P') { // Ficha Piquete
                    casillas[fila][columna].setImageResource(R.drawable.casillapiquete);
                } else { // Casilla normal
                    casillas[fila][columna].setImageResource(R.drawable.casilla);
                }
            }
        }
        comprobarSiGanado();
    }

    // Se actualizan las casillas
    private void actualizarCasillas(){
        for (int fila = 0; fila < 6; fila++) {
            for (int columna = 0; columna < 7; columna++) {
                char estado = Tablero.getTablero().getEstado(fila, columna);
                if (estado == '1') { // Ficha del jugador 1
                    casillas[fila][columna].setImageResource(R.drawable.casillarojo);
                } else if (estado == '2') { // Ficha del jugador 2
                    casillas[fila][columna].setImageResource(R.drawable.casillaazul);
                } else if (estado == 'P') { // Ficha Piquete
                    casillas[fila][columna].setImageResource(R.drawable.casillapiquete);
                } else { // Casilla normal
                    casillas[fila][columna].setImageResource(R.drawable.casilla);
                }
            }
        }
        comprobarSiGanado();
        cambiarTurno();
        numTotal++;
        if (this.numTotal % 5 == 0) {
            int i = Evento.getEvento().generarEvento();
            if (i == 1) { // Evento Viruseses
                evento.setText("Achuuuusss! Ups, se me han volado las fichas. Vais a tener que volver a empezar...");
            } else if (i == 2){ // Evento Rompecolumnas
                evento.setText("Parece que alguien a eliminado una columna entera...");
            } else if (i == 3){ // Evento Comeplomos
                evento.setText("Para vosotros jugadores. Una ficha comeplomo para los dos.");
                numComeplomo.setText(""+ListaJugadores.getListaJugadores().obtenerJugador(numTurno).getFichas().obtenerTotalComeplomos());
                iComeplomo.setImageResource(R.drawable.iconocomeplomoactivo);
            } else if(i == 4){ // Evento Piquete
                evento.setText("¡Vaya! Alguien ha bloqueado una casilla. Espero que no os moleste...");
            }
            actualizarCasillasEvento();
        } else {evento.setText("");}

        // Establece el numero de fichas comeplomo que disponga el usuario
        numComeplomo.setText(""+ListaJugadores.getListaJugadores().obtenerJugador(numTurno).getFichas().obtenerTotalComeplomos());

        // Se ilumina o desilumina la ficha comeplomo dependiendo si el usuario posee o no una de ellas
        if (ListaJugadores.getListaJugadores().obtenerJugador(numTurno).getFichas().obtenerTotalComeplomos() != 0) {
            iComeplomo.setImageResource(R.drawable.iconocomeplomoactivo);
        } else {
            iComeplomo.setImageResource(R.drawable.iconocomeplomo);
        }


    }

    // Se cambia el texto que advierte de a quien le toca colocar una ficha
    private void cambiarTurno(){

        if (numTurno == 1) {
            turno.setText("¡Es tu turno " + j2.getText().toString() + "!");
            numTurno = 2;
            if (modoJuego) {
                actualizarCasillasEvento();
                ListaJugadores.getListaJugadores().obtenerJugador(numTurno).colocarFicha(0);
                actualizarCasillas();
            }
        } else {
            numTurno = 1;
            turno.setText("¡Es tu turno " + j1.getText().toString() + "!");
        }
        // Establece el numero de fichas comeplomo que disponga el usuario
        numComeplomo.setText(""+ListaJugadores.getListaJugadores().obtenerJugador(numTurno).getFichas().obtenerTotalComeplomos());

        // Se ilumina o desilumina la ficha comeplomo dependiendo si el usuario posee o no una de ellas
        if (ListaJugadores.getListaJugadores().obtenerJugador(numTurno).getFichas().obtenerTotalComeplomos() != 0) {
            iComeplomo.setImageResource(R.drawable.iconocomeplomoactivo);
        }
        else{
            iComeplomo.setImageResource(R.drawable.iconocomeplomo);
        }
    }

    ///////////////////////////////// DIÁLOGOS /////////////////////////////////

    // Genera un dialogo que ofrece una ayuda en partida al usuario
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

    // Se genera un dialogo porque esta funcionalidad esta enfocada en el modo de jugar online
    private AlertDialog createDialogoAmistad() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_online, null);
        builder.setView(v);
        builder.setCancelable(false);
        builder.setNegativeButton("Aceptar", (dialog, which) -> dialog.cancel());
        return builder.create();

    }

    // Generar dialogo de victoria
    private AlertDialog generateDialogoVictoria(String jugador, int puntos) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_victoria, null);
        builder.setView(v);
        builder.setNeutralButton("Consultar puntuaciones",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Redirigimos al jugador a la interfaz de puntuaciones
                Intent p = new Intent(context, Puntuaciones.class);
                p.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                p.putExtra("username",jugador1);
                startActivity(p);
                finish();
            }
        });
        builder.setNegativeButton("Salir",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Finalizar la partida
                Intent mp = new Intent(context, MenuPrincipal.class);
                mp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mp.putExtra("username",jugador1);
                startActivity(mp);
                finish();
            }
        });
        builder.setPositiveButton("Volver a jugar",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Reiniciar la partida
                ListaJugadores.getListaJugadores().borrarComeplomos();
                Tablero.getTablero().resetear();
                numTurno=1;
                numTotal=1;
                actualizarCasillasEvento();
            }
        });

        // Mostramos quien ha ganado
        ganador = (TextView) v.findViewById(R.id.tGanador);
        ganador.setText("¡Enhorabuena " + jugador + "! ¡Eres el ganador!");

        // Mostramos cuantos puntos ha ganado
        puntaje = (TextView) v.findViewById(R.id.tPuntos);
        if (puntos > 0) {puntaje.setText("Has ganado " + puntos + " puntos.");}
        else {puntaje.setText("El segundo jugador no puede ganar puntos.");}

        return builder.create();

    }

    // Generar dialogo de derrota
    private AlertDialog generateDialogoDerrota(String jugador) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_derrota, null);
        builder.setView(v);
        builder.setNegativeButton("Salir",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Finalizar la partida
                Intent mp = new Intent(context, MenuPrincipal.class);
                mp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                mp.putExtra("username",jugador1);
                startActivity(mp);
                finish();
            }
        });
        builder.setPositiveButton("Volver a jugar",new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Reiniciar la partida
                ListaJugadores.getListaJugadores().borrarComeplomos();
                Tablero.getTablero().resetear();
                numTurno=1;
                numTotal=1;
                actualizarCasillasEvento();
            }
        });

        // Mostramos quien ha perdido
        ganador = (TextView) v.findViewById(R.id.tDerrota);
        ganador.setText("Lo sentimos " + jugador + ", has perdido :(");

        // Mostramos que no ha ganado puntos
        puntaje = (TextView) v.findViewById(R.id.tSubDerrota);
        puntaje.setText("No has ganado ningún punto.");

        return builder.create();

    }

    // Gestiona el evento al pulsar el boton 'Atras' de nuestro dispositivo
    @Override
    public void onBackPressed(){
        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Bichötes: Conecta 4");
        alertDialogBuilder.setMessage("¿Estás seguro que deseas abandonar la partida?")
                .setCancelable(false)
                .setPositiveButton("Rendirse", (dialog, which) -> {
                    // Abandonar partida
                    Tablero.getTablero().resetear();
                    Intent mp = new Intent(context, MenuPrincipal.class);
                    mp.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    mp.putExtra("username",jugador1);
                    startActivity(mp);
                    finish();
                })
                .setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel()).create().show();
    }

}