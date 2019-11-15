package com.doanltandroid.quizme.Loader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.doanltandroid.quizme.NetWorkUtils;

import java.util.HashMap;

public class DangNhapLoader extends AsyncTaskLoader<String> {
    private String uri;
    private String method;
    private HashMap<String, String> params;
    public DangNhapLoader(@NonNull Context context, String uri, String method, HashMap<String, String> params) {
        super(context);
        this.uri = uri;
        this.method = method;
        this.params = params;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetWorkUtils.doRequest(uri, method, params, null);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
