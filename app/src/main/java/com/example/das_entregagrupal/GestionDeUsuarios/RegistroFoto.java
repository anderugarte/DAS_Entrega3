package com.example.das_entregagrupal.GestionDeUsuarios;

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
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.das_entregagrupal.BaseDeDatos.ConexionRegistroFoto;
import com.example.das_entregagrupal.Principal.MenuPrincipal;
import com.example.das_entregagrupal.R;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegistroFoto extends AppCompatActivity {

    ImageView fp;
    Boolean cambiado = false;
    String username,nomb,pass,date;

    private static final int CODIGO_PERMISOS_CAMERA = 1;
    private static final int CODIGO_PERMISOS_GALERIA = 2;
    private static final String[] CAMERA_PERMISO = {Manifest.permission.CAMERA};
    private static final String[] GALERIA_PERMISO = {Manifest.permission.READ_EXTERNAL_STORAGE};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_foto);

        // Establecemos la foto de perfil por defecto
        fp = (ImageView) findViewById(R.id.iFotoPerfil);
        fp.setImageResource(R.drawable.perfil);

        Button bTomarFoto = (Button) findViewById(R.id.bTFoto);
        Button bTomarGaleria = (Button) findViewById(R.id.bSIGaleria);
        Button bRegistrarse = (Button) findViewById(R.id.bRegis);

        // Recibimos el nombre de usuario del usuario que se ha registrado al igual que el resto de sus datos
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            username = extras.getString("username");
            nomb = extras.getString("nomb");
            pass = extras.getString("pass");
            date = extras.getString("date");
        }

        // Modificar el campo de la fecha para introducirla en la base de datos
        String[] d = date.split(" / ");
        date = d[2] + "-" + d[1] + "-" + d[0];

        // Gestionar sacar una foto con la camara
        bTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Se comprobara si el usuario ha concedido los permisos necesarios
                if (!comprobarPermisosCamara()){

                } else {
                    dispatchTakePictureIntent();
                }
            }
        });

        // Gestionar recoger una foto de la galeria
        bTomarGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Se comprobara si el usuario ha concedido los permisos necesarios
                if (!comprobarPermisosGaleria()){

                } else {
                    Intent elIntentGal = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                    startActivityForResult(elIntentGal, 9999);
                }
            }
        });

        // Gestionar el resgistro
        bRegistrarse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cambiado) {
                    // Se genera un dialogo para preguntar si el usuario esta seguro de no anadir una foto de perfil
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegistroFoto.this);
                    String txt1 = "Aceptar";
                    String txt2 = "Cancelar";
                    builder.setTitle("Añadir foto de perfil");
                    builder.setMessage("¿Está seguro de no añadir ninguna foto de perfil?");
                    builder.setCancelable(true);

                    builder.setPositiveButton(txt1, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Crear cuenta sin foto de perfil
                            gestionarRegistroFoto(username, nomb, pass, date, fp.toString());

                            Intent mp = new Intent(getBaseContext(), MenuPrincipal.class);
                            mp.putExtra("username",username);
                            startActivity(mp);
                            finish();
                        }
                    });

                    builder.setNegativeButton(txt2, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });

                    builder.show();

                } else {
                    // Crear cuenta con foto de perfil
                    gestionarRegistroFoto(username, nomb, pass, date, fp.toString());
                }
            }
        });

    }

    // Este metodo gestionara el registro de un nuevo usuario
    private void gestionarRegistroFoto(String user, String nomb, String pass, String date, String foto) {

        Data datos = new Data.Builder()
                .putString("username",user)
                .putString("nombre",nomb)
                .putString("password",pass)
                .putString("cumpleanos",date)
                .putString("fotoperfil",foto)
                .build();

        OneTimeWorkRequest otwr = new OneTimeWorkRequest.Builder(ConexionRegistroFoto.class)
                .setInputData(datos)
                .build();

        WorkManager.getInstance(getApplicationContext()).getWorkInfoByIdLiveData(otwr.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo != null && workInfo.getState().isFinished()) {
                            if (workInfo.getOutputData().getString("result").equals("true")) {
                                // Registro correcto
                                Intent mp = new Intent (getBaseContext(), MenuPrincipal.class);
                                mp.putExtra("username", user);
                                startActivity(mp);
                                finish();
                            } else {
                                // Registro incorrecto

                                int tiempo= Toast.LENGTH_SHORT;
                                Toast aviso = Toast.makeText(getApplicationContext(), "Error", tiempo);
                                aviso.setGravity(Gravity.BOTTOM| Gravity.CENTER, 0, 0);
                                aviso.show();
                            }
                        }
                    }
                });
        WorkManager.getInstance(this).enqueue(otwr);
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
                    .appendQueryParameter("identificador", username)
                    .appendQueryParameter("imagen", fotoen64);
            String parametrosURL = builder.build().getEncodedQuery();
            try {
                fp.setImageBitmap(laminiatura);
                os = new FileOutputStream(imagenFich);
                laminiatura.compress(Bitmap.CompressFormat.JPEG, 100, os);
                cambiado = true;
                os.flush();
                os.close();
            } catch (Exception e) {

            }
        }

        // Imagen de la galeria
        if (requestCode == 9999 && resultCode == RESULT_OK) {
            Uri imagenSeleccionada = data.getData();
            fp.setImageURI(imagenSeleccionada);
            cambiado = true;
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
                }
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