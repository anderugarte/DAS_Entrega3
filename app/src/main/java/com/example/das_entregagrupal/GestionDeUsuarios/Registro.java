package com.example.das_entregagrupal.GestionDeUsuarios;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import com.example.das_entregagrupal.BaseDeDatos.ConexionRegistro;
import com.example.das_entregagrupal.Complementos.ClaseDialogoFecha;
import com.example.das_entregagrupal.R;

public class Registro extends AppCompatActivity {

    EditText etNombre;
    EditText etUsuario;
    EditText etContrasena;
    EditText etCumple;
    boolean existe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        etCumple = (EditText) findViewById(R.id.etCumpleanos);
        Button bInicSes = (Button) findViewById(R.id.bYTC);

        // Al pulsar este EditText, desplegaremos un dialogo donde se podra seleccionar la fecha de nacimientop del usuario
        etCumple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.etCumpleanos:
                        showDatePickerDialog(etCumple);
                        break;
                }
            }
        });

        bInicSes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent is = new Intent (getBaseContext(), InicioSesion.class);
                startActivity(is);
                finish();
            }
        });

        etNombre = (EditText) findViewById(R.id.etNombre);
        etUsuario = (EditText) findViewById(R.id.etNombreUsuario);
        etContrasena = (EditText) findViewById(R.id.etContrasena);

        Button bRegistro = (Button) findViewById(R.id.bRegistro);

        bRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etNombre.getText().toString().equals("") ||
                        etUsuario.getText().toString().equals("") ||
                        etContrasena.getText().toString().equals("") ||
                        etCumple.getText().toString().equals("")) {
                    String text = "Rellena todos los campos";
                    Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                    toast.show();
                } else {
                    if (comprobarSiExiste()) {
                        // devuelve true si ya existe -> mostrar toast
                        String text = "Nombre de usuario ya en uso";
                        Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                        toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                        toast.show();
                    } else {
                        // devuelve false si se puede registrar -> Intent foto
                        Intent iFoto = new Intent(getBaseContext(), RegistroFoto.class);
                        iFoto.putExtra("Usuario", etUsuario.getText().toString());
                        iFoto.putExtra("Nombre", etNombre.getText().toString());
                        iFoto.putExtra("Cumple", etCumple.getText().toString());
                        iFoto.putExtra("Password", etCumple.getText().toString());
                        startActivity(iFoto);
                    }
                }
            }
        });
    }

    // Este metodo nos ayuda a desplegar el dialogo para la seleccion de la fecha de nacimiento y nos permite enviar
    // el EditText por parametro para una vez obtenida la fecha poder realizar un .setText("fecha") en el
    private void showDatePickerDialog(final EditText editText) {
        ClaseDialogoFecha dialogoCumpleanos = new ClaseDialogoFecha(editText);
        dialogoCumpleanos.show(getSupportFragmentManager(),"cumple");
    }

    private boolean comprobarSiExiste() {
        Data datos = new Data.Builder().putString("Usuario", etUsuario.getText().toString()).build();
        OneTimeWorkRequest otwr = new OneTimeWorkRequest.Builder(ConexionRegistro.class)
                .setInputData(datos).build();
        WorkManager.getInstance(this).getWorkInfoByIdLiveData(otwr.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo != null && workInfo.getState().isFinished()) {
                            if (workInfo.getOutputData().getString("result").equals("existe")) {
                                existe = true;
                            } else {
                                // Ese nombre de usuario no esta en uso, se puede registrar
                                existe = false;
                            }
                        }
                    }
                });
        return existe;
    }
}