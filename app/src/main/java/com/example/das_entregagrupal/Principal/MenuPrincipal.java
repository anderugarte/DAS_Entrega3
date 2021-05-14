package com.example.das_entregagrupal.Principal;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.das_entregagrupal.Complementos.Ayuda;
import com.example.das_entregagrupal.Complementos.Opciones;
import com.example.das_entregagrupal.R;

public class MenuPrincipal extends AppCompatActivity {

    private AlertDialog.Builder alertDialogBuilder;
    private Boolean estadoF, estadoD = false;
    private Context context;
    private String user;

    private RadioButton facil, dificil;

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
            public void onClick(View v) {

            }
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

    // Cuando el usuario pulse el boton "Atras" de su dispositivo movil,
    // le preguntaremos si desea cerrar sesion.
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

    // Dialogo que gestiona la introduccion del nombre del segundo jugador
    public AlertDialog createDialogo2J() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_jugador2, null);
        builder.setView(v);
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        TextView j1 = (TextView) v.findViewById(R.id.tJugadorUno);
        EditText j2 = (EditText) v.findViewById(R.id.etJugadorDos);
        Button bJugar = (Button) v.findViewById(R.id.bJugar);

        j1.setText(user.toString());

        bJugar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (j2.getText().toString().length() == 0) {
                    String text = "Debes introducir un nombre para el Jugador 2";
                    Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    Intent p = new Intent(getBaseContext(), Partida.class);
                    p.putExtra("jugador1", user.toString());
                    p.putExtra("jugador2", j2.getText().toString());
                    startActivity(p);
                    finish();
                }
            }
        });

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
            if (facil.isChecked()){
                Intent p = new Intent(getBaseContext(), Partida.class);
                p.putExtra("jugador1", user.toString());
                p.putExtra("jugador2", "Ordenador");
                p.putExtra("dificultad","facil");
                startActivity(p);
                finish();
            } else if (dificil.isChecked()) {
                Intent p = new Intent(getBaseContext(), Partida.class);
                p.putExtra("jugador1", user.toString());
                p.putExtra("jugador2", "Ordenador");
                p.putExtra("dificultad","dificil");
                startActivity(p);
                finish();
            }
        });
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        facil = (RadioButton) v.findViewById(R.id.rbFacil);
        dificil = (RadioButton) v.findViewById(R.id.rbDificil);

        return builder.create();

    }

}