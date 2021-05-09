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
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.das_entregagrupal.BaseDeDatos.ConexionLogin;
import com.example.das_entregagrupal.Principal.MenuPrincipal;
import com.example.das_entregagrupal.R;

public class InicioSesion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inicio_sesion);

        EditText etNU = (EditText) findViewById(R.id.etNU);
        EditText etC = (EditText) findViewById(R.id.etC);

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

        // Se realizan las comprobaciones para el inicio de sesion
        bIniciarSesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Se recogen los campos rellenados por el usuario
                String user = etNU.getText().toString();
                String pass = etC.getText().toString();

                // Gestion del inicio de sesion
                // Primero, se comprueba si existen campos vacios
                if (user.length()==0 || pass.length()==0) {
                    if (user.length()==0) { // Campo username vacio
                        int tiempo= Toast.LENGTH_SHORT;
                        Toast aviso = Toast.makeText(getApplicationContext(), "Nombre de usuario vacío", tiempo);
                        aviso.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 0);
                        aviso.show();
                    } else { // Campo contrasena vacia
                        int tiempo= Toast.LENGTH_SHORT;
                        Toast aviso = Toast.makeText(getApplicationContext(), "Contraseña vacía", tiempo);
                        aviso.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 0);
                        aviso.show();
                    }
                } else if (user.length()>0 && pass.length()>0) { // No existe campos vacios, se comprobara si los campos introducidos por el usuario son correctos

                    ////////////////////// TEMPORAL ///////////////////////////////
                    Intent mp = new Intent (getBaseContext(), MenuPrincipal.class);
                    mp.putExtra("username",etNU.getText().toString());
                    startActivity(mp);
                    finish();
                    ///////////////////////////////////////////////////////////////

                    // Se accede al metodo que gestiona la conexion e inicio de sesion
                    //gestionarConexion(user,pass);
                }

            }
        });

    }

    // Metodo que gestiona el inicio de sesion del usuario
    private void gestionarConexion(String user, String pass) {

        Data resultados = new Data.Builder()
                .putString("username",user)
                .putString("password",pass)
                .build();

        OneTimeWorkRequest trabajoPuntualIS = new OneTimeWorkRequest.Builder(ConexionLogin.class)
                .setInputData(resultados)
                .build();

        WorkManager.getInstance(getApplicationContext()).getWorkInfoByIdLiveData(trabajoPuntualIS.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo status) {
                        if (status != null && status.getState().isFinished()) {
                            if (status.getOutputData().getString("result").equals("true")) {
                                // Login correcto (existe un usuario con dichas credenciales asi que se inicia sesion)
                                Intent mp = new Intent (getBaseContext(), MenuPrincipal.class);
                                mp.putExtra("username", user);
                                startActivity(mp);
                                finish();
                            } else {
                                // Login incorrecto (no existe ningun usuario con dichas credenciales)
                                int tiempo= Toast.LENGTH_SHORT;
                                Toast aviso = Toast.makeText(getApplicationContext(), "Error / Campos incorrectos", tiempo);
                                aviso.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 0);
                                aviso.show();
                            }
                        }
                    }
                });
        WorkManager.getInstance(getApplicationContext()).enqueue(trabajoPuntualIS);

    }
}