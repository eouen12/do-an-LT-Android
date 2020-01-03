package com.doanltandroid.quizme.Loader;

import android.os.AsyncTask;

import com.doanltandroid.quizme.NetWorkUtils;

public class ThongTinNguoiChoiLoader extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String id = strings[0];
        String token = strings[1];
        return NetWorkUtils.doRequest("nguoi-choi/thong-tin/" + id, "GET", null, token);
    }
}
