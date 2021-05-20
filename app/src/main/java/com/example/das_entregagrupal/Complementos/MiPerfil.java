package com.example.das_entregagrupal.Complementos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.das_entregagrupal.BaseDeDatos.ConexionCambiarContraseña;
import com.example.das_entregagrupal.BaseDeDatos.ConexionExisteUsuario;
import com.example.das_entregagrupal.BaseDeDatos.ConexionRecogerDatosUser;
import com.example.das_entregagrupal.BaseDeDatos.ConexionRegistro;
import com.example.das_entregagrupal.BaseDeDatos.ConexionUpdateUser;
import com.example.das_entregagrupal.GestionDeUsuarios.Registro;
import com.example.das_entregagrupal.Principal.MainActivity;
import com.example.das_entregagrupal.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Constructor;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MiPerfil extends AppCompatActivity {

    private String user;

    private AlertDialog.Builder alertDialogBuilder;
    private Context context;

    private static final int CODIGO_PERMISOS_CAMERA = 1;
    private static final int CODIGO_PERMISOS_GALERIA = 2;
    private static final String[] CAMERA_PERMISO = {Manifest.permission.CAMERA};
    private static final String[] GALERIA_PERMISO = {Manifest.permission.READ_EXTERNAL_STORAGE};

    EditText nombreUsuarioMP;
    EditText nombreMP;
    EditText cumpleanosMP;
    ImageView foto;

    TextView contrasenaMP;

    EditText nuevaP;
    EditText repetirP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mi_perfil);

        context = this;

        nombreUsuarioMP = (EditText) findViewById(R.id.etNombreUsuarioMP);
        nombreMP = (EditText) findViewById(R.id.etNombreMP);
        cumpleanosMP = (EditText) findViewById(R.id.etCumpleanosMP);
        foto = (ImageView) findViewById(R.id.iFotoDePerfil);

        contrasenaMP = (TextView) findViewById(R.id.tvCambiarContrasena);

        // Recibimos el nombre de usuario del usuario
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("username");
        }

        // Poner el nombre de usuario en el campo correspondiente
        nombreUsuarioMP.setText(user);

        // Recoger los datos de la BD
        recogerDatos();

        // Configuracion de los botones
        Button bModificar = (Button) findViewById(R.id.bModificar);

        // Cambiar la contraseña del usuario
        contrasenaMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { createDialogoCC().show(); }
        });

        // Modificar los datos del usuario
        bModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Se realizan las comprobaciones
                comprobarSiExiste();

            }
        });

        // Volvemos a la pantalla anterior
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

        // Actualizar foto de perfil
        ImageButton iCF = (ImageButton) findViewById(R.id.iCambiarFoto);
        iCF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { createDialogoCF().show(); }
        });

        // Eliminar foto de perfil
        ImageButton iEF = (ImageButton) findViewById(R.id.iEliminarFoto);
        iEF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setTitle("Bichötes: Conecta 4");
                alertDialogBuilder.setMessage("¿Deseas eliminar tu foto de perfil?")
                        .setCancelable(false)
                        .setPositiveButton("Aceptar", (dialog, which) -> {
                            // Ponemos la foto de perfil por defecto
                            foto.setImageResource(R.drawable.perfil);
                        })
                        .setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel()).create().show();
            }
        });

        // Al pulsar este EditText, desplegaremos un dialogo donde se podra modificar la fecha de nacimientop del usuario
        cumpleanosMP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.etCumpleanos:
                        showDatePickerDialog(cumpleanosMP);
                        break;
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

    // Se genara un dialogo para modificar la contraseña del usuario
    private AlertDialog createDialogoCC() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_cambiarcontrasena, null);
        builder.setView(v);
        nuevaP = (EditText) v.findViewById(R.id.etNuevaPass);
        repetirP = (EditText) v.findViewById(R.id.etRepetidaPass);

        builder.setPositiveButton("Modificar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (nuevaP.getText().toString().length() < 5) {
                            String text = "Contraseña demasiado corta";
                            Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                            toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                            toast.show();
                        } else {
                            if (nuevaP.getText().toString().equals(repetirP.getText().toString())) {
                                // Se modifica la contrasena
                                cambiarContraseña();

                            } else {
                                String text = "Las contraseñas no coinciden";
                                Toast toast = Toast.makeText(getBaseContext(), text, Toast.LENGTH_LONG);
                                toast.setGravity(Gravity.BOTTOM | Gravity.CENTER, 0, 0);
                                toast.show();
                            }
                        }
                    }
                });
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());


        return builder.create();

    }

    // Realizar el cambio de contraseña en la base de datos
    private void cambiarContraseña() {

        Data datos = new Data.Builder()
                .putString("username", user)
                .putString("password", nuevaP.getText().toString())
                .build();


        OneTimeWorkRequest otwr = new OneTimeWorkRequest.Builder(ConexionCambiarContraseña.class)
                .setInputData(datos)
                .build();

        WorkManager.getInstance(getApplicationContext()).getWorkInfoByIdLiveData(otwr.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo != null && workInfo.getState().isFinished()) {
                            if (workInfo.getOutputData().getString("result").equals("done")) {
                                // Se ha actualizado la contraseña en la base de datos
                                Intent o = new Intent (getBaseContext(), Opciones.class);
                                o.putExtra("username",user);
                                startActivity(o);
                                finish();
                            }

                        }
                    }
                });
        WorkManager.getInstance(getBaseContext()).enqueue(otwr);
    }

    // Se genara un dialogo para modificar la foto de perfil del usuario
    public AlertDialog createDialogoCF() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_cambiarfotoperfil, null);
        builder.setView(v);
        builder.setNegativeButton("Cancelar", (dialog, which) -> dialog.cancel());

        Button bTFoto = (Button) v.findViewById(R.id.bTomarFoto);
        Button bSGaleria = (Button) v.findViewById(R.id.bSeleccionarGaleria);

        // El usuario desea tomar una foto con su camara
        bTFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Se comprobara si el usuario ha concedido los permisos necesarios
                if (comprobarPermisosCamara()){
                    dispatchTakePictureIntent();
                }
            }
        });

        // El usuario desea seleccionar una foto de su galeria
        bSGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Se comprobara si el usuario ha concedido los permisos necesarios
                if (comprobarPermisosGaleria()){
                    Intent elIntentGal = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(elIntentGal, 9999);
                }
            }
        });

        return builder.create();

    }

    // Se empleara este metodo para actualizar las credenciales del usuario
    private void updateUser() {
        Data datos = new Data.Builder()
                .putString("username", nombreUsuarioMP.getText().toString())
                .putString("nombre", nombreMP.getText().toString())
                .putString("password", contrasenaMP.getText().toString())
                .putString("cumple", cumpleanosMP.getText().toString())
//                .putString("foto", )
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
                                // Ha sucedio un error
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

        OneTimeWorkRequest otwr = new OneTimeWorkRequest.Builder(ConexionExisteUsuario.class)
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
                                // Ese nombre de usuario está en uso, pero es el mismo usuario
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

    private void recogerDatos() {

        Data datos = new Data.Builder()
                .putString("username", nombreUsuarioMP.getText().toString())
                .build();

        OneTimeWorkRequest otwr = new OneTimeWorkRequest.Builder(ConexionRecogerDatosUser.class)
                .setInputData(datos).build();

        WorkManager.getInstance(getBaseContext()).getWorkInfoByIdLiveData(otwr.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo != null && workInfo.getState().isFinished()) {
                            if (workInfo.getOutputData().getString("username").equals(user)) {
                                // Se han recogido correctamente los datos
                                // -> Asignamos los valores a los EditText e ImageView
                                nombreMP.setText(workInfo.getOutputData().getString("nombre"));
                                cumpleanosMP.setText(workInfo.getOutputData().getString("cumple"));
                                String fp = workInfo.getOutputData().getString("foto");
                                foto.setImageURI(Uri.parse(workInfo.getOutputData().getString("foto")));
                            }
                        }
                    }
                });
        WorkManager.getInstance(getBaseContext()).enqueue(otwr);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Imagen de la camara
        if (requestCode == 8888 && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap laminiatura = (Bitmap) extras.get("data");
            File eldirectorio = this.getFilesDir();
            String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
            String nombrefichero = "IMG_" + timeStamp + "_";
            File imagenFich = new File(eldirectorio, nombrefichero + ".jpg");
            OutputStream os;
            ByteArrayOutputStream stream = new ByteArrayOutputStream();
            laminiatura.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] fototransformada = stream.toByteArray();
            String fotoen64 = Base64.encodeToString(fototransformada,Base64.DEFAULT);
            Uri.Builder builder = new Uri.Builder()
                    .appendQueryParameter("identificador", user)
                    .appendQueryParameter("imagen", fotoen64);
            String parametrosURL = builder.build().getEncodedQuery();
            try {
                foto.setImageBitmap(laminiatura);
                os = new FileOutputStream(imagenFich);
                laminiatura.compress(Bitmap.CompressFormat.JPEG, 100, os);
                os.flush();
                os.close();
            } catch (Exception e) {

            }
        }

        // Imagen de la galeria
        if (requestCode == 9999 && resultCode == RESULT_OK) {
            Uri imagenSeleccionada = data.getData();
            foto.setImageURI(imagenSeleccionada);
        }

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, 8888);
        }
    }

    // Este metodo comprobara si el usuario ha concedido los permisos de la camara
    private boolean comprobarPermisosCamara() {
        try {
            int permiso = ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            if (permiso != PackageManager.PERMISSION_GRANTED) {
                // Forzamos los permisos
                ActivityCompat.requestPermissions(this,CAMERA_PERMISO,CODIGO_PERMISOS_CAMERA);
            } else {
                // El usuario ya ha concedido los permisos
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Este metodo comprobara si el usuario ha concedido los permisos del acceso a la galeria
    private boolean comprobarPermisosGaleria() {
        try {
            int permisoG = ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE);
            if (permisoG != PackageManager.PERMISSION_GRANTED) {
                // Forzamos los permisos
                ActivityCompat.requestPermissions(this,GALERIA_PERMISO,CODIGO_PERMISOS_GALERIA);
            } else {
                // El usuario ya ha concedido los permisos
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Sobreescribiremos este metodo para saber si el usuario ha aceptado o denegado los permisos
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CODIGO_PERMISOS_CAMERA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso concedido
                    dispatchTakePictureIntent();
                } else {
                    // Permiso denegado
                    int tiempo= Toast.LENGTH_SHORT;
                    Toast aviso = Toast.makeText(getApplicationContext(), "Debes conceder los permisos de cámara", tiempo);
                    aviso.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 0);
                    aviso.show();
                } break;
            case CODIGO_PERMISOS_GALERIA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Permiso concedido
                    Intent elIntentGal = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(elIntentGal, 9999);
                } else {
                    // Permiso denegado
                    int tiempo= Toast.LENGTH_SHORT;
                    Toast aviso = Toast.makeText(getApplicationContext(), "Debes conceder los permisos de la galería", tiempo);
                    aviso.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 0);
                    aviso.show();
                } break;
        }
    }

}