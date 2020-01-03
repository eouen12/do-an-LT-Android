package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.doanltandroid.quizme.Loader.LuotChoiLoader;

import org.json.JSONException;
import org.json.JSONObject;

public class LuuLuotChoiActivity extends AppCompatActivity {
    private TextView txtTen, txtSoCau, txtDiem;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String token, user_id, hoten, diem, socau;

    private final static String FILE_NAME_SHAREREF = "com.doanltandroid.quizme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_luu_luot_choi);

        txtTen = findViewById(R.id.luotchoi_ten);
        txtSoCau = findViewById(R.id.luotchoi_socau);
        txtDiem = findViewById(R.id.luotchoi_diem);

        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        token = sharedPreferences.getString("TOKEN", "");
        user_id = sharedPreferences.getString("ID_USER","");
        hoten = sharedPreferences.getString("HOTEN","");
        socau = sharedPreferences.getString("SOCAU","");
        diem = sharedPreferences.getString("DIEM","");
        Log.d("DIEMM",diem);
        if (token == "") {
            finish();
        }
        txtTen.setText(hoten);
        txtSoCau.setText(socau);
        txtDiem.setText(diem);
    }
    public void LuuKetQua(View view) {
        new LuotChoiLoader(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    Log.d("LUUCHOI",s);
                    Boolean status = jsonObject.getBoolean("success");
                    if(status)
                    {
                        String msg = jsonObject.getString("msg");
                        Toast.makeText(LuuLuotChoiActivity.this, msg, Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        String msg = jsonObject.getString("msg");
                        Toast.makeText(LuuLuotChoiActivity.this, msg, Toast.LENGTH_LONG).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute(user_id,socau,diem,token);
        startActivity(new Intent(getApplicationContext(),MenuActivity.class));
    }

    public void launchActivityMenu(View view) {
        startActivity(new Intent(getApplicationContext(),MenuActivity.class));
        overridePendingTransition(R.anim.slide_left,R.anim.sile_out_right);
    }

    @Override
    public void finish() {
        super.finish();
        startActivity(new Intent(getApplicationContext(),MenuActivity.class));
        overridePendingTransition(R.anim.slide_left,R.anim.sile_out_right);
    }
}
