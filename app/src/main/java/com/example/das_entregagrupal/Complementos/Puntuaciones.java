package com.example.das_entregagrupal.Complementos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.das_entregagrupal.R;

public class Puntuaciones extends AppCompatActivity {

    private int ptosUser;
    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuaciones);

        ImageButton bAtras = (ImageButton) findViewById(R.id.bAtras4);
        ImageButton bAyuda = (ImageButton) findViewById(R.id.bAyuda2);

        // Puntos del usuario
        TextView puntos = (TextView) findViewById(R.id.ptosPersonales);

        // Ranking Global
        TextView num1 = (TextView) findViewById(R.id.tPrimero);
        TextView num2 = (TextView) findViewById(R.id.tSegundo);
        TextView num3 = (TextView) findViewById(R.id.tTercero);
        TextView num4 = (TextView) findViewById(R.id.tCuarto);
        TextView num5 = (TextView) findViewById(R.id.tQuinto);

        TextView ptos1 = (TextView) findViewById(R.id.ptosNum1);
        TextView ptos2 = (TextView) findViewById(R.id.ptosNum2);
        TextView ptos3 = (TextView) findViewById(R.id.ptosNum3);
        TextView ptos4 = (TextView) findViewById(R.id.ptosNum4);
        TextView ptos5 = (TextView) findViewById(R.id.ptosNum5);

        // Recibimos el nombre de usuario del usuario
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("username");
        }

        // Recoger los datos de la BD
        //recogerDatos();

        // Establecemos los puntos del usuario
        //puntos.setText(ptosUser);

        // Volver atras
        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent (getBaseContext(), Opciones.class);
                o.putExtra("username", user);
                startActivity(o);
                finish();
            }
        });

        // Solicitar la ayuda
        bAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}