package com.example.das_entregagrupal.Complementos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.das_entregagrupal.Complementos.Opciones;
import com.example.das_entregagrupal.Complementos.Personalizacion;
import com.example.das_entregagrupal.R;

public class MiPerfil extends AppCompatActivity {

    private String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);

        EditText nombreUsuarioMP = (EditText) findViewById(R.id.etNombreUsuarioMP);
        EditText nombreMP = (EditText) findViewById(R.id.etNombreMP);
        EditText contrasenaMP = (EditText) findViewById(R.id.etContrasenaMP);
        EditText cumpleanosMP = (EditText) findViewById(R.id.etCumpleanosMP);

        // Recibimos el nombre de usuario del usuario
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("username");
        }

        //
        nombreUsuarioMP.setText(user);

        // Configuracion de los botones
        Button bModificar = (Button) findViewById(R.id.bModificar);

        bModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String u = nombreUsuarioMP.getText().toString();
                String n = nombreMP.getText().toString();
                String p = contrasenaMP.getText().toString();
                String c = cumpleanosMP.getText().toString();

                // Se realizan las comprobaciones

                updateUser(u,n,p,c);
                Intent o = new Intent (getBaseContext(), Opciones.class);
                startActivity(o);
                finish();

            }
        });

        ImageButton bAtras3 = (ImageButton) findViewById(R.id.bAtras3);

        bAtras3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent (getBaseContext(), Opciones.class);
                startActivity(o);
                finish();
            }
        });

    }

    // Se empleara este metodo para actualixar las credenciales del usuario
    private void updateUser(String u, String n, String p, String c) {
    }
}