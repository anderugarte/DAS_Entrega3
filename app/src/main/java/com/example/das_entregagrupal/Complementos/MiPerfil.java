package com.example.das_entregagrupal.Complementos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.das_entregagrupal.BaseDeDatos.ConexionRegistro;
import com.example.das_entregagrupal.BaseDeDatos.ConexionUpdateUser;
import com.example.das_entregagrupal.Complementos.Opciones;
import com.example.das_entregagrupal.Complementos.Personalizacion;
import com.example.das_entregagrupal.GestionDeUsuarios.RegistroFoto;
import com.example.das_entregagrupal.R;

public class MiPerfil extends AppCompatActivity {

    private String user;

    EditText nombreUsuarioMP;
    EditText nombreMP;
    EditText contrasenaMP;
    EditText cumpleanosMP;
    ImageView foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);

        nombreUsuarioMP = (EditText) findViewById(R.id.etNombreUsuarioMP);
        nombreMP = (EditText) findViewById(R.id.etNombreMP);
        contrasenaMP = (EditText) findViewById(R.id.etContrasenaMP);
        cumpleanosMP = (EditText) findViewById(R.id.etCumpleanosMP);
        foto = (ImageView) findViewById(R.id.iFotoDePerfil);

        // Recibimos el nombre de usuario del usuario
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("username");
        }

        // Poner el nombre de usuario en el campo correspondiente
        nombreUsuarioMP.setText(user);

        // Configuracion de los botones
        Button bModificar = (Button) findViewById(R.id.bModificar);

        bModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Se realizan las comprobaciones
                comprobarSiExiste();

            }
        });

        ImageButton bAtras3 = (ImageButton) findViewById(R.id.bAtras3);

        bAtras3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent (getBaseContext(), Opciones.class);
                o.putExtra("username", user);
                startActivity(o);
                finish();
            }
        });

    }

    // Se empleara este metodo para actualizar las credenciales del usuario
    private void updateUser() {
        Data datos = new Data.Builder()
                .putString("username", nombreUsuarioMP.getText().toString())
                .putString("nombre", nombreMP.getText().toString())
                .putString("password", contrasenaMP.getText().toString())
                .putString("cumple", cumpleanosMP.getText().toString())
                .putString("foto", foto.toString())
                .build();

        OneTimeWorkRequest otwr = new OneTimeWorkRequest.Builder(ConexionUpdateUser.class)
                .setInputData(datos).build();

        WorkManager.getInstance(getBaseContext()).getWorkInfoByIdLiveData(otwr.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo != null && workInfo.getState().isFinished()) {
                            if (workInfo.getOutputData().getString("result").equals("done")) {
                                // Se han actualizado los datos del usuario en la BD
                                // Intent Opciones
                                Intent o1 = new Intent(getBaseContext(), Opciones.class);
                                o1.putExtra("username", nombreUsuarioMP.getText());
                                startActivity(o1);
                                finish();
                            } else {
                                String text = "Ha ocurrido un error";
                                Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                                toast.show();
                            }
                        }
                    }
                });
        WorkManager.getInstance(getBaseContext()).enqueue(otwr);
    }

    // Comprueba si el nombre de usuario introducido existe en la base de datos
    // Si encuentra uno, comprueba que no sea el de el usuario
    private void comprobarSiExiste() {

        Data datos = new Data.Builder()
                .putString("username", nombreUsuarioMP.getText().toString())
                .build();

        OneTimeWorkRequest otwr = new OneTimeWorkRequest.Builder(ConexionRegistro.class)
                .setInputData(datos).build();

        WorkManager.getInstance(getBaseContext()).getWorkInfoByIdLiveData(otwr.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo != null && workInfo.getState().isFinished()) {
                            if (workInfo.getOutputData().getString("result").equals("noexiste")) {
                                // Ese nombre de usuario no esta en uso, se puede registrar
                                // Intent foto
                                updateUser();
                            } else {
                                // Ese nombre de usuario está en uso, pero puede ser el mismo usuario
                                if (nombreUsuarioMP.getText().toString().equals(user)) {
                                    // Es el mismo usuario
                                    updateUser();
                                } else {
                                    // No es el mismo usuario
                                    String text = "Nombre de usuario ya en uso";
                                    Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                                    toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                                    toast.show();
                                }
                            }
                        }
                    }
                });
        WorkManager.getInstance(getBaseContext()).enqueue(otwr);
    }
}