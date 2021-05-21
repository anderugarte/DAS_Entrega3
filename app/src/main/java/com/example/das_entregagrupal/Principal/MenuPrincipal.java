package com.example.das_entregagrupal.Principal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.das_entregagrupal.Complementos.Ayuda;
import com.example.das_entregagrupal.Complementos.Opciones;
import com.example.das_entregagrupal.Modelo.ListaJugadores;
import com.example.das_entregagrupal.Modelo.Partida;
import com.example.das_entregagrupal.R;

public class MenuPrincipal extends AppCompatActivity {

    // Menu Principal de nuestro juego con sus diversos modos de juego y opciones

    private AlertDialog.Builder alertDialogBuilder;
    private Boolean estadoF, estadoD = false;
    private Context context;
    private String user;

    private TextView j1;
    private EditText j2;

    private RadioButton facil, dificil;

    private ImageView gV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_principal);

        setSupportActionBar(findViewById(R.id.labarraOpciones));

        context = this;

        Button bJContraIA = (Button) findViewById(R.id.bJIA);
        Button bJ2Jugadores = (Button) findViewById(R.id.bJ2J);
        Button bJOnline = (Button) findViewById(R.id.bJO);
        Button bOpciones = (Button) findViewById(R.id.bO);
        ImageButton iAyuda = (ImageButton) findViewById(R.id.iAyuda);

        // Recibimos el nombre de usuario del usuario que se ha registrado al igual que el resto de sus datos
        Bundle extras = getIntent().getExtras();
        if (extras != null) {user = extras.getString("username");}

        // Jugar contra la IA
        bJContraIA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // El usuario debe seleccionar la dificultad de la IA
                createDialogoIA().show();
            }
        });

        // Jugar 2 jugadores
        bJ2Jugadores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createDialogo2J().show();
            }
        });

        // Jugar online
        bJOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { createDialogoOnline().show(); }
        });

        // Acceder al menu de opciones
        bOpciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent (getBaseContext(), Opciones.class);
                o.putExtra("username",user);
                startActivity(o);
            }
        });

        // Acceder al manual de ayuda
        iAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent (getBaseContext(), Ayuda.class);
                startActivity(a);
            }
        });

    }

    // Se genera este dialogo porque aun no es posible jugar online
    private AlertDialog createDialogoOnline() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_online, null);
        builder.setView(v);
        builder.setNegativeButton("Aceptar", (dialog, which) -> dialog.cancel());

        return builder.create();

    }

    // Cuando el usuario pulse el boton "Atras" de su dispositivo movil,
    // le preguntaremos si desea cerrar sesion.
    @Override
    public void onBackPressed(){
        alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("Bichötes: Conecta 4");
        alertDialogBuilder.setMessage("¿Deseas cerrar la sesión?")
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

    // Dialogo que gestiona la introduccion del nombre del segundo jugador
    public AlertDialog createDialogo2J() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_jugador2, null);
        builder.setView(v);
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());
        builder.setPositiveButton("Jugar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (j2.getText().toString().length() == 0) { // Nombre del jugador 2 vacio
                    String text = "Debes introducir un nombre para el Jugador 2";
                    Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                    toast.show();
                } else { // Iniciamos la partida con 2 jugadores
                    Intent p = new Intent(getBaseContext(), PartidaV.class);
                    p.putExtra("jugador1", user.toString());
                    p.putExtra("jugador2", j2.getText().toString());
                    p.putExtra("dificultad",0);
                    p.putExtra("modoJuego",false);
                    ListaJugadores.getListaJugadores().borrarJugadores();
                    Partida.getPartida().inicializarJugadores(user.toString(),j2.getText().toString());
                    startActivity(p);
                    finish();
                }
            }
        });

        j1 = (TextView) v.findViewById(R.id.tJugadorUno);
        j2 = (EditText) v.findViewById(R.id.etJugadorDos);

        j1.setText(user);

        return builder.create();

    }

    // Dialogo que gestiona la eleccion de dificultad de la IA
    private AlertDialog createDialogoIA() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_dificultad, null);
        builder.setView(v);
        builder.setPositiveButton("Jugar", (dialog, which) -> {
            // Comprobamos la dificultad seleccionada
            if (facil.isChecked()){ // Iniciamos la partida contra la IA en modo facil
                Intent p = new Intent(getBaseContext(), PartidaV.class);
                p.putExtra("jugador1", user.toString());
                p.putExtra("jugador2", "Ordenador");
                p.putExtra("dificultad",0);
                p.putExtra("modoJuego",true);
                ListaJugadores.getListaJugadores().borrarJugadores();
                Partida.getPartida().inicializarJugadorYMaq(user.toString(),0);
                startActivity(p);
                finish();
            } else if (dificil.isChecked()) { // Iniciamos la partida contra la IA en modo dificil
                Intent p = new Intent(getBaseContext(), PartidaV.class);
                p.putExtra("jugador1", user.toString());
                p.putExtra("jugador2", "Ordenador");
                p.putExtra("dificultad",1);
                p.putExtra("modoJuego",true);
                ListaJugadores.getListaJugadores().borrarJugadores();
                Partida.getPartida().inicializarJugadorYMaq(user.toString(),1);
                startActivity(p);
                finish();
            }
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        facil = (RadioButton) v.findViewById(R.id.rbFacil);
        dificil = (RadioButton) v.findViewById(R.id.rbDificil);

        return builder.create();

    }

    // ToolBox | Menu de herramientas
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuiniciosesion,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.cs:{ // Se desea cerrar sesion
                alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle("Bichötes: Conecta 4");
                alertDialogBuilder.setMessage("¿Deseas cerrar la sesión?")
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
        return super.onOptionsItemSelected(item);
    }

}