package com.example.das_entregagrupal.Complementos;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.das_entregagrupal.R;

public class Amigos extends AppCompatActivity {

    // Activity donde se podran consultar nuestros amigos

    private String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_amigos);

        // Volver atras
        ImageButton bAtras2 = (ImageButton) findViewById(R.id.bAtras2);
        bAtras2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent (getBaseContext(), Opciones.class);
                startActivity(o);
                finish();
            }
        });

        EditText idUsu = (EditText) findViewById(R.id.idUsuario);
        id = idUsu.getText().toString();

        // Boton para enviar la solicitud de amistad
        Button bEnviar = (Button) findViewById(R.id.bSol);
        bEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (id.length() > 0) {
                    // Se envia la solicitud a dicho nombre de usuario

                    String text = "Solicitud de amistad enviada";
                    Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    // Si el campo del usuario está vacio aparecerá un Toast avisando
                    String text = "Nombre de usuario vacío";
                    Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                    toast.show();
                }
            }
        });

    }
}