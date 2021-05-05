package com.example.das_entregagrupal.GestionDeUsuarios;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.content.DialogInterface;
import android.content.Intent;
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
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class RegistroFoto extends AppCompatActivity {

    ImageView fp;
    Boolean cambiado = false;
    String user,nomb,pass,date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_foto);

        fp = (ImageView) findViewById(R.id.iFotoPerfil);

        Button bTomarFoto = (Button) findViewById(R.id.bTFoto);
        Button bTomarGaleria = (Button) findViewById(R.id.bSIGaleria);
        Button bRegistrarse = (Button) findViewById(R.id.bRegis);

        // Establecemos la foto de perfil por defecto
        fp.setImageResource(R.drawable.perfil);

        // Recibimos el nombre de usuario del usuario que se ha registrado al igual que el resto de sus datos
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("Usuario");
            nomb = extras.getString("Nombre");
            pass = extras.getString("Password");
            date = extras.getString("Cumple");
        }

        // Gestionar sacar una foto con la camara
        bTomarFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {dispatchTakePictureIntent();}
        });

        // Gestionar recoger una foto de la galeria
        bTomarGaleria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent elIntentGal = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(elIntentGal, 9999);
            }
        });

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
                            gestionarRegistroFoto(user, nomb, pass, date, fp.toString());

                            Intent mp = new Intent(getBaseContext(), MenuPrincipal.class);
                            mp.putExtra("Usuario",user);
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
                    gestionarRegistroFoto(user, nomb, pass, date, fp.toString());
                }
            }
        });

    }

    private void gestionarRegistroFoto(String user, String nomb, String pass, String date, String foto) {

        Data resultadosRF = new Data.Builder()
                .putString("username",user)
                .putString("nombre",nomb)
                .putString("password",pass)
                .putString("cumpleanos",date)
                .putString("fotoperfil",foto)
                .build();

        OneTimeWorkRequest trabajoPuntualRF = new OneTimeWorkRequest.Builder(ConexionRegistroFoto.class)
                .setInputData(resultadosRF)
                .build();

        WorkManager.getInstance(getApplicationContext()).getWorkInfoByIdLiveData(trabajoPuntualRF.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo status) {
                        if (status != null && status.getState().isFinished()) {
                            if (status.getOutputData().getString("resultado").equals("true")) {
                                // Registro correcto
                                Intent mp = new Intent (getBaseContext(), MenuPrincipal.class);
                                mp.putExtra("Usuario", user);
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
        WorkManager.getInstance(getApplicationContext()).enqueue(trabajoPuntualRF);

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

}