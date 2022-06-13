package com.example.geosearch;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;

public class countryPage extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private TextView txtName;

    private TextView txtTotPop;
    private TextView txtCapital;
    private TextView txtLangs;
    private TextView txtHDI;

    private TextView txtDens;
    private TextView txtPopRural;
    private TextView txtPopUrban;
    private TextView txtLifeExpec;

    private TextView txtRegion;
    private TextView txtArea;

    private TextView txtCrrc;
    private TextView txtGDPB;
    private TextView txtGDPC;
    String query = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_page);

        txtName = (TextView) findViewById(R.id.txtCtTitle);
        //txtPop = (TextView) findViewById(R.id.txtTotPop);
        txtCapital = (TextView) findViewById(R.id.txtCapital);

        Intent intent = getIntent();
        query = intent.getStringExtra("query");
        Bundle queryBundle = new Bundle();
        queryBundle.putString("query", query);
        if(getSupportLoaderManager().getLoader(0) != null){
            getSupportLoaderManager().initLoader(0,null,this);
        }

        fetchCountry();
    }

    public void fetchCountry ()
    {
        ConnectivityManager checkConnec = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo net = null;
        if (checkConnec != null){
            net = checkConnec.getActiveNetworkInfo();
        }
        if(net != null && net.isConnected()){
            if(query.length() == 0){
                //CASO A QUERY SEJA VAZIA
            }
            else{
                Bundle queryBundle = new Bundle();
                queryBundle.putString("query", query);
                getSupportLoaderManager().restartLoader(0, queryBundle,this);
            }
        }
        else{
            //SEM INTERNET
        }
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        String query = "";
        if(args != null)
        {
            query = args.getString("query");
        }
        return new fetchCountry(this, query);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try{
            JSONArray itemsArray = new JSONArray(data);
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
                    JSONObject regiao = localizacao.getJSONObject("sub-regiao");
                JSONArray linguas = country.getJSONArray("linguas");
                JSONObject governo = country.getJSONObject("governo");
                    JSONObject capital = governo.getJSONObject("capital");
                JSONArray monetario = country.getJSONArray("unidades-monetarias");

                try{
                    name = nome.getString("abreviado");
                    totalArea = area.getString("total") + " " + unidade.getString("símbolo");
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