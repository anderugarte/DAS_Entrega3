package com.example.das_entregagrupal.BaseDeDatos;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Data;
import androidx.work.ListenableWorker;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import org.json.simple.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class ConexionRecogerPuntuaciones extends Worker {

    // Tarea para recoger las puntuaciones de los usuarios de la base de datos
    // con el php recogerPuntuacionesE3.php

    public ConexionRecogerPuntuaciones(@NonNull Context pcontext, @NonNull WorkerParameters workerParams) {
        super(pcontext, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {

        String direccion = "http://ec2-54-167-31-169.compute-1.amazonaws.com/igonzalez274/WEB/Entrega3/recogerPuntuacionesE3.php";
        String[] result = new String[2];
        Data resultados = null;
        HttpURLConnection urlConnection = null;

        try {
            URL destino = new URL(direccion);
            urlConnection = (HttpURLConnection) destino.openConnection();
            urlConnection.setConnectTimeout(5000);
            urlConnection.setReadTimeout(5000);
            urlConnection.setRequestMethod("GET");
            int statusCode = urlConnection.getResponseCode();

            if (statusCode == 200) {
                BufferedInputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
                String line = "";
                int i = 0;
                while ((line = bufferedReader.readLine()) != null) {
                    result[i] = line;
                    i++;
                }
                inputStream.close();

                resultados = new Data.Builder()
                        .putStringArray("result", result)
                        .build();

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return Result.success(resultados);
    }
}
