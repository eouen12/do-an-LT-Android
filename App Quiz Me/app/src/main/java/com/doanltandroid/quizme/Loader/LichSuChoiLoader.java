package com.doanltandroid.quizme.Loader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.doanltandroid.quizme.NetWorkUtils;

public class LichSuChoiLoader extends AsyncTaskLoader<String> {
    private String token;
    private String id;

    public LichSuChoiLoader(@NonNull Context context,String token,String id) {
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
        return NetWorkUtils.doRequest("lich-su-choi/"+id,"GET",null,token);
    }
}
