package com.example.geosearch;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

public class profile implements Serializable {
    private int _id;
    private String _name;
    private String _count;
    private String _passw;
    private int _isLogged;

    public profile(){}

    public profile(int id, String name)
    {
        this.set_id(id);
        this.set_name(name);
    }

    public profile(int id, String name, String count, String passw, int _isLogged){
        this.set_id(id);
        this.set_name(name);
        this.set_count(count);
        this.set_passw(passw);
        this.set_isLogged(_isLogged);
    }

    public void set_id(int id) { this._id = id; }
    public void set_name(String name) { this._name = name; }
    public void set_count(String count) { this._count = count; }
    public void set_passw(String passw) { this._passw = passw; }
    public void set_isLogged(int isLogged) { this._isLogged = isLogged; }

    public JSONObject profileJSON (){
        JSONObject obj = new JSONObject();
        try{
            obj.put("ID:", this._id);
            obj.put(String.valueOf(R.string.profName), this._name);
            obj.put(String.valueOf(R.string.profDescContDf), this._count);
            obj.put(String.valueOf(R.string.profPassw), this._count);
            obj.put("isLogged", this._isLogged);
        }
        catch(JSONException e)
        {}
        return obj;
    }
}
