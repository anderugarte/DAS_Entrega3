package com.example.das_entregagrupal.GestionDeUsuarios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.text.AutoText;
import android.text.Editable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.das_entregagrupal.BaseDeDatos.ConexionLogin;
import com.example.das_entregagrupal.BaseDeDatos.ConexionRegistro;
import com.example.das_entregagrupal.Principal.MenuPrincipal;
import com.example.das_entregagrupal.R;

public class InicioSesion extends AppCompatActivity {

    EditText username;
    EditText password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        Button bNTCA = (Button) findViewById(R.id.bNTCA);
        bNTCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent r = new Intent (getBaseContext(), Registro.class);
                startActivity(r);
                finish();
            }
        });

        Button bIniciarSesion = (Button) findViewById(R.id.bIniciarSesion);
        username = (EditText) findViewById(R.id.etNU);
        password = (EditText) findViewById(R.id.etC);

        bIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (username.getText().toString().equals("")) {
                    String text = "Nombre de usuario vacío";
                    Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                    toast.show();
                } else if (password.getText().toString().equals("")) {
                    String text = "Contraseña vacía";
                    Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    // INICIAR SESIÓN
                    iniciarSesion();
                }
            }
        });

    }

    private void iniciarSesion() {
        Data datos = new Data.Builder()
                .putString("username", username.getText().toString())
                .putString("password", password.getText().toString())
                .build();

        OneTimeWorkRequest otwr = new OneTimeWorkRequest.Builder(ConexionLogin.class)
                .setInputData(datos).build();
        WorkManager.getInstance(getBaseContext()).getWorkInfoByIdLiveData(otwr.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo != null && workInfo.getState().isFinished()) {
                            Log.i("hola", workInfo.getOutputData().getString("result"));
                            if (workInfo.getOutputData().getString("result").equals("logOK")) {
                                // El inicio de sesion se ha realizado correctamente
                                // Intent MenuPrincipal
                                Intent mp = new Intent(getBaseContext(), MenuPrincipal.class);
                                mp.putExtra("username", username.getText().toString());
                                startActivity(mp);
                                finish();
                            } else {
                                String text = "Usuario o contraseña incorrectos";
                                Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                                toast.show();
                            }
                        }
                    }
                });
        WorkManager.getInstance(getBaseContext()).enqueue(otwr);
    }
}