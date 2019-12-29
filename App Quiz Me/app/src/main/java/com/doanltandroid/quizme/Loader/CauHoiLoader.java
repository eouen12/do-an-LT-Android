package com.doanltandroid.quizme.Loader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.doanltandroid.quizme.NetWorkUtils;

public class CauHoiLoader extends AsyncTaskLoader<String> {
    private String token;
    private int id;
    public CauHoiLoader(@NonNull Context context, String token, int id) {
        super(context);
        this.token = token;
        this.id = id;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetWorkUtils.doRequest("cau-hoi/"+id,"GET",null,token);
    }
}
