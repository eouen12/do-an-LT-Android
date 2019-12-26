package com.doanltandroid.quizme.Loader;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

import com.doanltandroid.quizme.NetWorkUtils;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class DangKyLoader extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        HashMap<String, String> param = new HashMap<>();
        param.put("img_avatar", strings[0]);
        param.put("ten_dang_nhap", strings[1]);
        param.put("email", strings[2]);
        param.put("mat_khau", strings[3]);
        param.put("ho_ten", strings[4]);
        return NetWorkUtils.postRequest("dang-ky", param);
    }
}
