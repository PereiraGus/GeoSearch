package com.example.geosearch;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class country implements Serializable
{
    private int _id;
    private String _name;
    private String _init;
    private String _capital;
    private int _pop;
    private String _dens;
    private String _hdi;
    private String _ruralPop;
    private String _urbanPop;
    private int _lifeExpec;
    private String _totalArea;
    private String _bruteGDP;
    private String _capitaGDP;
    private String _hist;
    private String _crrc;
    private String _region;
    private String _lang;

    public country (){}

    public country(int id, String name)
    {
        this.set_id(id);
        this.set_name(name);
    }

    public country (int id, String name, String init, String capital, int pop, String dens,
                    String hdi, String ruralPop, String urbanPop, int lifeExpec, String totalArea,
                    String bruteGDP, String capitaGDP, String hist, String crrc, String region,
                    String lang)
    {
        this.set_id(id);
        this.set_name(name);
        this.set_init(init);
        this.set_capital(capital);
        this.set_pop(pop);
        this.set_dens(dens);
        this.set_hdi(hdi);
        this.set_ruralPop(ruralPop);
        this.set_urbanPop(urbanPop);
        this.set_lifeExpec(lifeExpec);
        this.set_totalArea(totalArea);
        this.set_bruteGDP(bruteGDP);
        this.set_capitaGDP(capitaGDP);
        this.set_hist(hist);
        this.set_crrc(crrc);
        this.set_region(region);
        this.set_lang(lang);
    }

    public void set_id(int id) {
        this._id = id;
    }
    public void set_name(String name) {
        this._name = name;
    }
    public void set_init(String init) {
        this._init = init;
    }
    public void set_capital(String capital) {
        this._capital = capital;
    }
    public void set_pop(int pop) {
        this._pop = pop;
    }
    public void set_dens(String dens) {
        this._dens = dens;
    }
    public void set_hdi(String hdi) {
        this._hdi = hdi;
    }
    public void set_ruralPop(String ruralPop) {
        this._ruralPop = ruralPop;
    }
    public void set_urbanPop(String urbanPop) {
        this._urbanPop = urbanPop;
    }
    public void set_lifeExpec(int lifeExpec) {
        this._lifeExpec = lifeExpec;
    }
    public void set_totalArea(String totalArea) {
        this._totalArea = totalArea;
    }
    public void set_bruteGDP(String bruteGDP) {
        this._bruteGDP = bruteGDP;
    }
    public void set_capitaGDP(String capitaGDP) {
        this._capitaGDP = capitaGDP;
    }
    public void set_hist(String hist) {
        this._hist = hist;
    }
    public void set_crrc(String crrc) {
        this._crrc = crrc;
    }
    public void set_region(String region) {
        this._region = region;
    }
    public void set_lang(String lang) {
        this._lang = lang;
    }

    public JSONObject countryJSON (){
        JSONObject obj = new JSONObject();
        try{
            obj.put("ID:", this._id);
            obj.put(String.valueOf(R.string.ctName), this._name);
            obj.put(String.valueOf(R.string.ctInit), this._init);
            obj.put(String.valueOf(R.string.ctCapital), this._capital);
            obj.put(String.valueOf(R.string.ctTotalPop), this._pop);
            obj.put(String.valueOf(R.string.ctDemoDesi), this._dens);
            obj.put(String.valueOf(R.string.ctHDI), this._hdi);
            obj.put(String.valueOf(R.string.ctPopRural), this._ruralPop);
            obj.put(String.valueOf(R.string.ctPopUrban), this._urbanPop);
            obj.put(String.valueOf(R.string.ctLifeExpect), this._lifeExpec);
            obj.put(String.valueOf(R.string.ctTotalArea), this._totalArea);
            obj.put(String.valueOf(R.string.ctGDPBrute), this._bruteGDP);
            obj.put(String.valueOf(R.string.ctGDPCapita), this._bruteGDP);
            obj.put(String.valueOf(R.string.ctSubHistory), this._hist);
            obj.put(String.valueOf(R.string.ctCurrency), this._crrc);
            obj.put(String.valueOf(R.string.ctRegion), this._region);
            obj.put(String.valueOf(R.string.ctLangs), this._lang);
        }
        catch(JSONException e)
        {}
        return obj;
    }
}
