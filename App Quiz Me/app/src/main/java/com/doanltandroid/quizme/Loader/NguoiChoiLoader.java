package com.doanltandroid.quizme.Loader;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import com.doanltandroid.quizme.NetWorkUtils;

import java.nio.channels.NetworkChannel;
import java.util.HashMap;

public class NguoiChoiLoader extends AsyncTaskLoader<String> {
    private String token;

    public NguoiChoiLoader(@NonNull Context context, String token) {
        super(context);
        this.token = token;
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetWorkUtils.doRequest("xep-hang","GET",null,token);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
}
