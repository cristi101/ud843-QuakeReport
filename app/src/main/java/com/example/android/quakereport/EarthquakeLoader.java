package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>> {
    String mUrl;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    public List<Earthquake> loadInBackground() {
        if (mUrl == null || mUrl.length() == 0) return null;
        return QueryUtils.extractEarthquakes(mUrl);
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
