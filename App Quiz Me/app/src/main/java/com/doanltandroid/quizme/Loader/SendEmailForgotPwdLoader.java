package com.doanltandroid.quizme.Loader;

import android.os.AsyncTask;

import com.doanltandroid.quizme.NetWorkUtils;

import java.util.HashMap;

public class SendEmailForgotPwdLoader extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        String email = strings[0];
        HashMap<String, String> param = new HashMap<>();
        param.put("email", email);
        return NetWorkUtils.postRequest("quen-mat-khau", param);
    }
}
