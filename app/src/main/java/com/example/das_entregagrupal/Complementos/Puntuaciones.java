package com.example.das_entregagrupal.Complementos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkInfo;
import androidx.work.WorkManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.das_entregagrupal.BaseDeDatos.ConexionRecogerPuntuaciones;
import com.example.das_entregagrupal.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.SplittableRandom;

public class Puntuaciones extends AppCompatActivity {

    private int ptosUser;
    private String user;

    List<JSONObject> lpuntos = new ArrayList<>();
    TextView puntos;
    TextView num1;
    TextView num2;
    TextView num3;
    TextView num4;
    TextView num5;
    TextView ptos1;
    TextView ptos2;
    TextView ptos3;
    TextView ptos4;
    TextView ptos5;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puntuaciones);

        ImageButton bAtras = (ImageButton) findViewById(R.id.bAtras4);
        ImageButton bAyuda = (ImageButton) findViewById(R.id.bAyuda2);

        // Puntos del usuario
        puntos = (TextView) findViewById(R.id.ptosPersonales);

        // Ranking Global
        num1 = (TextView) findViewById(R.id.tPrimero);
        num2 = (TextView) findViewById(R.id.tSegundo);
        num3 = (TextView) findViewById(R.id.tTercero);
        num4 = (TextView) findViewById(R.id.tCuarto);
        num5 = (TextView) findViewById(R.id.tQuinto);

        ptos1 = (TextView) findViewById(R.id.ptosNum1);
        ptos2 = (TextView) findViewById(R.id.ptosNum2);
        ptos3 = (TextView) findViewById(R.id.ptosNum3);
        ptos4 = (TextView) findViewById(R.id.ptosNum4);
        ptos5 = (TextView) findViewById(R.id.ptosNum5);

        // Recibimos el nombre de usuario del usuario
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("username");
        }

        // Recoger los datos de la BD de las mejores puntuaciones y
        // mostrarlas en los campos
        // En el mismo método se muestran los puntos del user en la interfaz
        try {
            recogerDatos();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        // Volver atras
        bAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent o = new Intent (getBaseContext(), Opciones.class);
                o.putExtra("username", user);
                startActivity(o);
                finish();
            }
        });

        // Solicitar la ayuda
        bAyuda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {createDialogoAyuda().show();}
        });

    }

    private void recogerDatos() throws JSONException {

        OneTimeWorkRequest otwr = new OneTimeWorkRequest.Builder(ConexionRecogerPuntuaciones.class).build();

        WorkManager.getInstance(getApplicationContext()).getWorkInfoByIdLiveData(otwr.getId())
                .observe(this, new Observer<WorkInfo>() {
                    @Override
                    public void onChanged(WorkInfo workInfo) {
                        if (workInfo != null && workInfo.getState().isFinished()) {
                            String[] r = workInfo.getOutputData().getStringArray("result");
                            for (int i = 0; i < r.length; i++) {
                                String[] l = r[i].split(",");
                                JSONObject json = new JSONObject();
                                try {
                                    json.put("nombre", l[0]);
                                    json.put("puntos", l[1]);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                                if (l[0].equals(user)) {
                                    puntos.setText(l[1]);
                                }
                                lpuntos.add(json);
                            }
                        }
                    }
                });
        WorkManager.getInstance(getBaseContext()).enqueue(otwr);
        Collections.sort(lpuntos, new Comparator<JSONObject>() {
            @Override
            public int compare(JSONObject o1, JSONObject o2) {
                String p1 = new String();
                String p2 = new String();

                try {
                    p1 = (String) o1.get("puntos");
                    p2 = (String) o2.get("puntos");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                return p1.compareTo(p2);
            }
        });

        num1.setText(lpuntos.get(0).get("nombre").toString());
        ptos1.setText(lpuntos.get(0).get("puntos").toString());
        num2.setText(lpuntos.get(1).get("nombre").toString());
        ptos2.setText(lpuntos.get(1).get("puntos").toString());
        num3.setText(lpuntos.get(2).get("nombre").toString());
        ptos3.setText(lpuntos.get(2).get("puntos").toString());
        num4.setText(lpuntos.get(3).get("nombre").toString());
        ptos4.setText(lpuntos.get(3).get("puntos").toString());
        num5.setText(lpuntos.get(4).get("nombre").toString());
        ptos5.setText(lpuntos.get(4).get("puntos").toString());
    }

    private AlertDialog createDialogoAyuda() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = this.getLayoutInflater();
        View v = inflater.inflate(R.layout.dialogo_ayudapuntuaciones, null);
        builder.setView(v);
        builder.setPositiveButton("Aceptar", (dialog, which) -> dialog.cancel());

        return builder.create();

    }
}