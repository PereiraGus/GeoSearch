package com.example.geosearch;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class networkUtils {
    private static final String LOG_TAG = networkUtils.class.getSimpleName();
    private static final String URL_COUNTRY = "https://servicodados.ibge.gov.br/api/v1/paises";
    private static final String COUNTRY_QUERY = "/";
    String countryJSON = null;
    static String url = null;

    static Bundle searchCountry(String query) {
        HttpURLConnection conexao = null;
        BufferedReader reader = null;
        String countryJSON = null;
        try {
            Uri buildURI = Uri.parse(URL_COUNTRY).buildUpon()
                    .appendPath(query)
                    .build();
            URL requestURL = new URL(buildURI.toString());
            url = buildURI.toString();
            conexao = (HttpURLConnection) requestURL.openConnection();
            conexao.setRequestMethod("GET");
            conexao.connect();

            InputStream inputStream = conexao.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("/n");
            }
            if (builder.length() == 0) {
                return null;
            }
            countryJSON = builder.toString();
        }
        catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            conexao.disconnect();
        }

        conexao = null;
        reader = null;
        String socioEcoJSON = null;
        try {
            Uri buildURI = Uri.parse(URL_COUNTRY).buildUpon()
                    .appendPath(query)
                    .appendPath("indicadores")
                    .build();
            URL requestURL = new URL(buildURI.toString());
            url = buildURI.toString();
            conexao = (HttpURLConnection) requestURL.openConnection();
            conexao.setRequestMethod("GET");
            conexao.connect();

            InputStream inputStream = conexao.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            StringBuilder builder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
                builder.append("/n");
            }
            if (builder.length() == 0) {
                return null;
            }
            socioEcoJSON = builder.toString();
        } catch (ProtocolException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            conexao.disconnect();
        }
        Bundle fullData = new Bundle();
        fullData.putString("socioEco",socioEcoJSON);
        fullData.putString("country",countryJSON);
        return fullData;
    }
}