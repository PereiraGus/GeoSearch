package com.example.geosearch;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class SegundoPlano extends AsyncTask<Void, Void, String> {
    @Override
    protected String doInBackground(Void... voids) {
        StringBuilder respAPI = new StringBuilder();

        try {
            URL urlPais = new URL("https://servicodados.ibge.gov.br/api/v1/paises/");
            HttpURLConnection conexao = (HttpURLConnection) urlPais.openConnection();
            conexao.setRequestMethod("GET");
            conexao.setRequestProperty("Content-type", "application/json");
            conexao.setDoOutput(true);
            conexao.setConnectTimeout(3000);
            conexao.connect();

            Scanner sc = new Scanner(urlPais.openStream());
            while (sc.hasNext()){
                respAPI.append(sc.next());
            }
            conexao.disconnect();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
            Log.d("Erro", e.toString());
        }
        return respAPI.toString();
    }
}


