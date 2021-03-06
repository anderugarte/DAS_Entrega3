package com.example.das_entregagrupal.Complementos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.das_entregagrupal.Principal.MenuPrincipal;
import com.example.das_entregagrupal.R;

public class Opciones extends AppCompatActivity {

    // Desde aquí se podrá acceder al perfil del usuario, al ranking
    // de puntuaciones, a la pantalla de amigos y a la ayuda.

    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opciones);

        Button bMiPerfil = (Button) findViewById(R.id.bPerfil);
        Button bMPunt = (Button) findViewById(R.id.bMPuntuaciones);
        Button bAmigos = (Button) findViewById(R.id.bAmigos);
        Button bAyuda = (Button) findViewById(R.id.bAyuda);
        ImageButton ibVolver = (ImageButton) findViewById(R.id.iVolver);

        // Recibimos el nombre de usuario del usuario que se ha registrado al igual que el resto de sus datos
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("username");
        }

        // Accedemos a la interfaz de Mi Perfil
        bMiPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mip = new Intent (getBaseContext(), MiPerfil.class);
                mip.putExtra("username",user);
                startActivity(mip);
            }
        });

        // Accedemos a la interfaz de Puntuaciones
        bMPunt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent p = new Intent (getBaseContext(), Puntuaciones.class);
                p.putExtra("username",user);
                startActivity(p);
            }
        });

        // Accedemos a la interfaz de Mis Amigos
        bAmigos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent (getBaseContext(), Amigos.class);
                a.putExtra("username",user);
                startActivity(a);
            }
        });

        // Accedemos a la interfaz de Ayuda
        bAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent a = new Intent (getBaseContext(), Ayuda.class);
                startActivity(a);
            }
        });

        // Volvemos atras
        ibVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mp = new Intent (getBaseContext(), MenuPrincipal.class);
                mp.putExtra("username",user);
                startActivity(mp);
                finish();
            }
        });

    }

    // El usuario pulse el boton "Atras" de su dispositivo
    @Override
    public void onBackPressed(){
        Intent mp = new Intent (getBaseContext(), MenuPrincipal.class);
        mp.putExtra("username", user);
        startActivity(mp);
        finish();
    }

}