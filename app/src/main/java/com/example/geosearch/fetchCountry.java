package com.example.geosearch;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class fetchCountry extends AsyncTaskLoader<Bundle> {
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
    public Bundle loadInBackground() { return networkUtils.searchCountry(mQueryString); }
}