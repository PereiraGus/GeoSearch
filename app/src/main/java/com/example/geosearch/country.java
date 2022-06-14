package com.example.geosearch;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class country implements Serializable
{
    private String _name;
    private String _init;
    private String _capital;
    private int _pop;
    private String _dens;
    private String _hdi;
    private String _ruralPop;
    private String _urbanPop;
    private String _lifeExpec;
    private String _totalArea;
    private String _bruteGDP;
    private String _capitaGDP;
    private String _hist;
    private String _crrc;
    private String _region;
    private String _lang;

    public country (){}

    public country(String name)
    {
        this.set_name(name);
    }

    public country (String name, String init, String capital, int pop, String dens,
                    String hdi, String ruralPop, String urbanPop, String lifeExpec, String totalArea,
                    String bruteGDP, String capitaGDP, String hist, String crrc, String region,
                    String lang)
    {
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
    public String get_name() {
        return _name;
    }
    public void set_name(String name) {
        this._name = name;
    }

    public String get_init() {
        return _init;
    }
    public void set_init(String init) {
        this._init = init;
    }

    public String get_capital() {
        return _capital;
    }
    public void set_capital(String capital) {
        this._capital = capital;
    }

    public int get_pop() {
        return _pop;
    }
    public void set_pop(int pop) {
        this._pop = pop;
    }

    public String get_dens() {
        return _dens;
    }
    public void set_dens(String dens) {
        this._dens = dens;
    }

    public String get_hdi() {
        return _hdi;
    }
    public void set_hdi(String hdi) {
        this._hdi = hdi;
    }

    public String get_ruralPop() {
        return _ruralPop;
    }
    public void set_ruralPop(String ruralPop) {
        this._ruralPop = ruralPop;
    }

    public String get_urbanPop() {
        return _urbanPop;
    }
    public void set_urbanPop(String urbanPop) {
        this._urbanPop = urbanPop;
    }

    public String get_lifeExpec() {
        return _lifeExpec;
    }
    public void set_lifeExpec(String lifeExpec) {
        this._lifeExpec = lifeExpec;
    }

    public String get_totalArea() {
        return _totalArea;
    }
    public void set_totalArea(String totalArea) {
        this._totalArea = totalArea;
    }

    public String get_bruteGDP() {
        return _bruteGDP;
    }
    public void set_bruteGDP(String bruteGDP) {
        this._bruteGDP = bruteGDP;
    }

    public String get_capitaGDP() {
        return _capitaGDP;
    }
    public void set_capitaGDP(String capitaGDP) {
        this._capitaGDP = capitaGDP;
    }

    public String get_hist() {
        return _hist;
    }
    public void set_hist(String hist) {
        this._hist = hist;
    }

    public String get_crrc() {
        return _crrc;
    }
    public void set_crrc(String crrc) {
        this._crrc = crrc;
    }

    public String get_region() {
        return _region;
    }
    public void set_region(String region) {
        this._region = region;
    }

    public String get_lang() {
        return _lang;
    }
    public void set_lang(String lang) {
        this._lang = lang;
    }

    public JSONObject countryJSON (){
        JSONObject obj = new JSONObject();
        try{
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
