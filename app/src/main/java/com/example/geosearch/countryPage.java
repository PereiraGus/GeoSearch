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
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class countryPage extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Bundle>{
    private static final String URL_MAPS = "https://www.google.com/maps/place";
    private DatabaseHelper db;

    private Button btnSave;

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

        btnSave = (Button) findViewById(R.id.btnSave);
        btnSave.setEnabled(true);

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

        db = new DatabaseHelper(this);

        Intent intent = getIntent();
        query = intent.getStringExtra("query");
        Bundle queryBundle = new Bundle();
        queryBundle.putString("query", query);
        if(getSupportLoaderManager().getLoader(0) != null){
            getSupportLoaderManager().initLoader(0,null,this);
        }
        fetchCountry();
    }

    public void backToMenu (View view){
        Intent intent = new Intent(getApplicationContext(), mainMenu.class);
        startActivity(intent);
    }

    public void loadMap(String ctName){
        WebView map = (WebView) findViewById(R.id.mapWebView);
        WebSettings mapSttgs = map.getSettings();
        mapSttgs.setJavaScriptEnabled(true);
        Uri buildURI = Uri.parse(URL_MAPS).buildUpon()
                        .appendPath(ctName)
                        .build();
        map.loadUrl(buildURI.toString());
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
    public Loader<Bundle> onCreateLoader(int id, @Nullable Bundle args) {
        String query = "";
        if(args != null)
        {
            query = args.getString("query");
        }
        return new fetchCountry(this, query);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Bundle> loader, Bundle data) {
        try {
            Bundle allData = data;
            JSONArray countryArray = new JSONArray(allData.getString("country"));
            JSONArray socioEcoArray = new JSONArray(allData.getString("socioEco"));
            int i = 0;
            String name = null;
            String init = null;
            String totalArea = null;
            String region = null;
            String langs = "";
            String capitalCity = null;
            String currency = "";
            String historic = null;
            while (countryArray.length() > i) {
                JSONObject country = countryArray.getJSONObject(i);
                JSONObject id = country.getJSONObject("id");
                JSONObject nome = country.getJSONObject("nome");
                JSONObject area = country.getJSONObject("area");
                JSONObject unidade = area.getJSONObject("unidade");
                JSONObject localizacao = country.getJSONObject("localizacao");
                JSONObject regiao = localizacao.getJSONObject("sub-regiao");
                JSONArray linguas = country.getJSONArray("linguas");
                JSONObject governo = country.getJSONObject("governo");
                JSONObject capital = governo.getJSONObject("capital");
                JSONArray monetario = country.getJSONArray("unidades-monetarias");
                try {
                    name = nome.getString("abreviado");
                    init = id.getString("ISO-3166-1-ALPHA-2");
                    totalArea = area.getString("total") + " " + unidade.getString("símbolo");
                    region = regiao.getString("nome");
                    String comma = "";
                    for (i = 0; linguas.length() > i; i++) {
                        langs = langs + linguas.getJSONObject(i).getString("nome") + comma;
                        comma = ", ";
                    }
                    capitalCity = capital.getString("nome");
                    comma = "";
                    for (i = 0; monetario.length() > i; i++) {
                        currency = currency + monetario.getJSONObject(i).getString("nome") +
                                " (" + monetario.getJSONObject(i).getJSONObject("id").getString("ISO-4217-ALPHA") +
                                ")" + comma;
                        comma = ", ";
                    }
                    historic = country.getString("historico");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }
            String totPop = null;
            String idh = null;
            String densDemo = null;
            String popRural = null;
            String popUrb = null;
            String lifeExpec = null;
            String pibB = null;
            String pibPC = null;
            i = 0;
            while (i == 0) {
                JSONObject popData = socioEcoArray.getJSONObject(26);
                JSONArray popPaises = popData.getJSONArray("series");
                JSONObject popPais = popPaises.getJSONObject(0);
                JSONArray popPorAno = popPais.getJSONArray("serie");
                JSONObject popAtualizada = popPorAno.getJSONObject(popPorAno.length() - 3);
                JSONObject idhData = socioEcoArray.getJSONObject(11);
                JSONArray idhPaises = idhData.getJSONArray("series");
                JSONObject idhPais = idhPaises.getJSONObject(0);
                JSONArray idhPorAno = idhPais.getJSONArray("serie");
                JSONObject idhAtualizado = idhPorAno.getJSONObject(idhPorAno.length() - 3);
                JSONObject densData = socioEcoArray.getJSONObject(21);
                JSONArray densPaises = densData.getJSONArray("series");
                JSONObject densPais = densPaises.getJSONObject(0);
                JSONArray densPorAno = densPais.getJSONArray("serie");
                JSONObject densAtualizado = densPorAno.getJSONObject(densPorAno.length() - 3);
                JSONObject popRData = socioEcoArray.getJSONObject(24);
                JSONArray popRPaises = popRData.getJSONArray("series");
                JSONObject popRPais = popRPaises.getJSONObject(0);
                JSONArray popRPorAno = popRPais.getJSONArray("serie");
                JSONObject popRAtualizado = popRPorAno.getJSONObject(popRPorAno.length() - 3);
                JSONObject popUData = socioEcoArray.getJSONObject(25);
                JSONArray popUPaises = popUData.getJSONArray("series");
                JSONObject popUPais = popUPaises.getJSONObject(0);
                JSONArray popUPorAno = popUPais.getJSONArray("serie");
                JSONObject popUAtualizado = popUPorAno.getJSONObject(popUPorAno.length() - 3);
                JSONObject lifeExData = socioEcoArray.getJSONObject(10);
                JSONArray lifeExPaises = lifeExData.getJSONArray("series");
                JSONObject lifeExPais = lifeExPaises.getJSONObject(0);
                JSONArray lifeExPorAno = lifeExPais.getJSONArray("serie");
                JSONObject lifeExAtualizado = lifeExPorAno.getJSONObject(lifeExPorAno.length() - 3);
                JSONObject pibBData = socioEcoArray.getJSONObject(9);
                JSONArray pibBPaises = pibBData.getJSONArray("series");
                JSONObject pibBPais = pibBPaises.getJSONObject(0);
                JSONArray pibBPorAno = pibBPais.getJSONArray("serie");
                JSONObject pibBAtualizado = pibBPorAno.getJSONObject(pibBPorAno.length() - 3);
                JSONObject pibPCData = socioEcoArray.getJSONObject(5);
                JSONArray pibPCPaises = pibPCData.getJSONArray("series");
                JSONObject pibPCPais = pibPCPaises.getJSONObject(0);
                JSONArray pibPCPorAno = pibPCPais.getJSONArray("serie");
                JSONObject pibPCAtualizado = pibPCPorAno.getJSONObject(pibPCPorAno.length() - 3);

                try {

                    totPop = popAtualizada.getString("2019");
                    idh = idhAtualizado.getString("2019");
                    densDemo = densAtualizado.getString("2019");
                    popRural = popRAtualizado.getString("2019");
                    popUrb = popUAtualizado.getString("2019");
                    lifeExpec = lifeExAtualizado.getString("2019");
                    pibB = pibBAtualizado.getString("2019");
                    pibPC = pibPCAtualizado.getString("2019");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                i++;
            }
            txtName.setText(name);
            loadMap(name);

            txtTotPop.setText(getString(R.string.ctTotalPop) + ": " + totPop + " habitantes");
            txtCapital.setText(getString(R.string.ctCapital) + ": " + capitalCity);
            txtLangs.setText(getString(R.string.ctLangs) + ": " + langs);
            txtHDI.setText(getString(R.string.ctHDI) + ": " + idh);

            txtDens.setText(getString(R.string.ctDemoDesi) + ": " + densDemo + " hab/km²");
            txtPopRural.setText(getString(R.string.ctPopRural) + ": " + popRural + "%");
            txtPopUrban.setText(getString(R.string.ctPopUrban) + ": " + popUrb + "%");
            txtLifeExpec.setText(getString(R.string.ctLifeExpect) + ": " + lifeExpec + " anos");

            txtRegion.setText(getString(R.string.ctRegion) + ": " + region);
            txtArea.setText(getString(R.string.ctTotalArea) + ": " + totalArea + "km²");

            txtCrrc.setText(getString(R.string.ctCurrency) + ": " + currency);
            txtGDPB.setText(getString(R.string.ctGDPBrute) + ": " + pibB);
            txtGDPC.setText(getString(R.string.ctGDPCapita) + ": " + pibPC);

            txtHist.setText(historic);

            country ct = new country(name, init,capitalCity,Integer.parseInt(totPop),densDemo,
                        idh,popRural,popUrb,lifeExpec,totalArea,pibB,pibPC,
                        historic,currency,region,langs);
            db.insertCountry(ct);
        }
        catch (JSONException e) {
            //FAZER OPÇÃO QUE MOSTRE PARA O USUARIO QUE DEU ERRO
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Bundle> loader) {

    }
}