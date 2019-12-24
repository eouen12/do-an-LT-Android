package com.doanltandroid.quizme;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

public class RankLoader extends AsyncTaskLoader<String> {
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    public RankLoader(@NonNull Context context) {
        super(context);
    }

    @Nullable
    @Override
    public String loadInBackground() {
        return NetWorkUtils.getJSONData("nguoi-choi","GET");
    }
}
