package com.example.geosearch;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class fetchCountry extends AsyncTaskLoader<String> {
    private String mQueryString;
    fetchCountry (Context context, String queryString){
        super(context);
        mQueryString = queryString;
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    @Nullable
    @Override
    public String loadInBackground() {
        return networkUtils.searchCountry(mQueryString);
    }
}