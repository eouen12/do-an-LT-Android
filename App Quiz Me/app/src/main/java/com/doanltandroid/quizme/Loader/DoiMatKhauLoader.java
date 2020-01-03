package com.doanltandroid.quizme.Loader;

import android.os.AsyncTask;

import com.doanltandroid.quizme.NetWorkUtils;

import java.util.HashMap;

public class DoiMatKhauLoader extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String token = strings[3];
        HashMap<String, String> param = new HashMap<>();
        param.put("id", strings[0]);
        param.put("mat_khau_cu", strings[1]);
        param.put("mat_khau_moi", strings[2]);
        return NetWorkUtils.postRequest("nguoi-choi/doi-mat-khau", param, token);
    }
}
