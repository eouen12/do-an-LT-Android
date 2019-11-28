package com.doanltandroid.quizme.Loader;

import android.os.AsyncTask;

import com.doanltandroid.quizme.NetWorkUtils;

import java.util.HashMap;

public class DangNhapLoader1 extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String tenDangNhap = strings[0];
        String password = strings[1];
        HashMap<String, String> params = new HashMap<>();
        params.put("ten_dang_nhap", tenDangNhap);
        params.put("password", password);
        return NetWorkUtils.doRequest("dang-nhap", "POST", params, null);
    }
}
