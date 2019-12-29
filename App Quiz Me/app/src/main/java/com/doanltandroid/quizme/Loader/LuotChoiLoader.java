package com.doanltandroid.quizme.Loader;

import android.os.AsyncTask;

import com.doanltandroid.quizme.NetWorkUtils;

import java.util.HashMap;

public class LuotChoiLoader extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        HashMap<String, String> param = new HashMap<>();
        param.put("nguoi_choi_id", strings[0]);
        param.put("so_cau", strings[1]);
        param.put("diem", strings[2]);
        return NetWorkUtils.postRequest("luu-luot-choi", param);
    }
}
