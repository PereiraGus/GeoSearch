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

    private TextView txtHist;
    String query = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_country_page);

        txtName = (TextView) findViewById(R.id.txtCtTitle);

        txtTotPop = (TextView) findViewById(R.id.txtTotPop);
        txtCapital = (TextView) findViewById(R.id.txtCapital);
        txtLangs = (TextView) findViewById(R.id.txtLangs);
        txtHDI = (TextView) findViewById(R.id.txtHDI);

        txtDens = (TextView) findViewById(R.id.txtDens);
        txtPopRural = (TextView) findViewById(R.id.txtPopRural);
        txtPopUrban = (TextView) findViewById(R.id.txtPopUrban);
        txtLifeExpec = (TextView) findViewById(R.id.txtLifeExpec);

        txtRegion = (TextView) findViewById(R.id.txtRegion);
        txtArea = (TextView) findViewById(R.id.txtTotalArea);

        txtCrrc = (TextView) findViewById(R.id.txtCrrc);
        txtGDPB = (TextView) findViewById(R.id.txtGDPB);
        txtGDPC = (TextView) findViewById(R.id.txtGDPC);

        txtHist = (TextView) findViewById(R.id.txtHist);

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
            String totalArea = null;
            String region = null;
            String langs = "";
            String capitalCity = null;
            String currency = "";
            String historic = null;
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
                    String comma = "";
                    for(i = 0; linguas.length() > i; i++)
                    {
                        langs = langs + linguas.getJSONObject(i).getString("nome") + comma;
                        comma = ", ";
                    }
                    capitalCity = capital.getString("nome");
                    comma = "";
                    for (i = 0; monetario.length() > i; i++)
                    {
                        currency = currency + monetario.getJSONObject(i).getString("nome") +
                                    " (" + monetario.getJSONObject(i).getJSONObject("id").getString("ISO-4217-ALPHA") +
                                    ")" + comma;
                        comma = ", ";
                    }
                    historic = country.getString("historico");
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }
            txtName.setText(name);

            //txtTotPop.setText();
            txtCapital.setText(getString(R.string.ctCapital) + ": " + capitalCity);
            txtLangs.setText(getString(R.string.ctLangs) + ": " + langs);
            //txtHDI.setText();

            /*txtDens.setText();
            txtPopRural.setText();
            txtPopUrban.setText();
            txtLifeExpec.setText();*/

            txtRegion.setText(getString(R.string.ctRegion) + ": " + region);
            txtArea.setText(getString(R.string.ctTotalArea) + ": " + totalArea);

            txtCrrc.setText(getString(R.string.ctCurrency) + ": " + currency);
            //txtGDPB.setText();
            //txtGDPC.setText();

            txtHist.setText(historic);
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