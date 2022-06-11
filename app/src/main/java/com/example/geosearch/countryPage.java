package com.example.geosearch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class countryPage extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private TextView txtName;
    private TextView txtPop;
    private TextView txtCapital;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_page);

        txtName = (TextView) findViewById(R.id.txtCtTitle);
        txtPop = (TextView) findViewById(R.id.txtTotPop);
        txtCapital = (TextView) findViewById(R.id.txtCapital);

        Intent intent = getIntent();
        String query = intent.getStringExtra("query");
        Bundle queryBundle = new Bundle();
        queryBundle.putString("query", query);
        getSupportLoaderManager().restartLoader(0,queryBundle,this);
    }

    //CHAMAR A CLASSE DA TAREFA ASSÍNCRONA

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String queryString = "";
        if(args != null)
        {
            queryString = args.getString("query");
        }
        return new fetchCountry(this, queryString);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try{
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemsArray = jsonObject.getJSONArray("array");
            int i = 0;
            String name = null;
            String totalArea;
            String region;
            String langs = null;
            String capitalCity = null;
            String currency = null;
            String historic;
            while(itemsArray.length() > i)
            {
                JSONObject country = itemsArray.getJSONObject(i);
                JSONObject nome = country.getJSONObject("nome");
                JSONObject area = country.getJSONObject("area");
                    JSONObject unidade = area.getJSONObject("unidade");
                JSONObject localizacao = country.getJSONObject("localizacao");
                    JSONObject regiao = localizacao.getJSONObject("regiao-intermediaria");
                JSONArray linguas = country.getJSONArray("linguas");
                JSONObject governo = country.getJSONObject("governo");
                    JSONObject capital = governo.getJSONObject("capital");
                JSONArray monetario = country.getJSONArray("unidades-monetarias");
                try{
                    name = nome.getString("abreviado");
                    totalArea = area.getString("total") + " " + unidade.getString("simbolo");
                    region = regiao.getString("nome");
                    for(i = 0; linguas.length() >= i; i++)
                    {
                        if(linguas.length() > i)
                        langs = langs + linguas.getJSONObject(i).getString("nome") + ", ";
                        if(linguas.length() == i)
                        {
                            langs = langs + linguas.getJSONObject(i).getString("nome");
                        }
                    }
                    capitalCity = capital.getString("nome");
                    for (i = 0; monetario.length() >= i; i++)
                    {
                        if(monetario.length() > i)
                            currency = currency + monetario.getJSONObject(i).getString("nome") +
                                    " (" + monetario.getJSONObject(i).getJSONObject("id").getString("ISO-4217-ALPHA") +
                                    "), ";
                        if(monetario.length() == i)
                        {
                            currency = currency + linguas.getJSONObject(i).getString("nome") +
                                    " (" + monetario.getJSONObject(i).getJSONObject("id").getString("ISO-4217-ALPHA") +
                                    ") ";
                        }
                    }
                    historic = country.getString("historico");
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }
            txtName.setText(name);
            txtCapital.setText(R.string.ctCapital + ": " + capitalCity);
        }
        catch (JSONException e) {
            //FAZER OPÇÃO QUE MOSTRE PARA O USUARIO QUE DEU ERRO
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}