package com.doanltandroid.quizme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.doanltandroid.quizme.Loader.DangNhapLoader;
import com.doanltandroid.quizme.Loader.DangNhapLoader1;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    Button btnDangNhap , btnDangKy;
    TextView txtQuenMatKhau;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String FILE_NAME_SHAREREF = "com.doanltandroid.quizme";

    public void AnhXa(){
        btnDangNhap = findViewById(R.id.dangnhap_main_button);
        btnDangKy = findViewById(R.id.dangky_main_button);
        txtQuenMatKhau = findViewById(R.id.quenmatkhau_main_textview);
        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF, MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setIntent (Class lop){
        Intent intent = new Intent(getApplicationContext(),lop);
        startActivity(intent);
        overridePendingTransition(R.anim.sile_right,R.anim.sile_out_left);
    }

    @Override
    protected void onResume() {
        super.onResume();
        String token = sharedPreferences.getString("TOKEN", "");
        Log.d("TOKEN", token);
        if (token != "") {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Tham chiếu find by id ...
        AnhXa();

        String token = sharedPreferences.getString("TOKEN", "");
        Log.d("TOKEN", token);
        if (token != "") {
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
        }

        txtQuenMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setIntent(LayLaiMatKhauActivity.class);
            }
        });
    }

    public void launchActivityDangKy(View view) {
        setIntent(DangKyActivity.class);
    }

    public void launchActivityMenu() {
        setIntent(MenuActivity.class);
    }

    public void doLogin(View view) {
        EditText txtTenDangNhap = findViewById(R.id.txtTenDangNhap);
        EditText txtMatKhau = findViewById(R.id.txtMatKhau);

        String tenDangNhap = txtTenDangNhap.getText().toString();
        String matKhau = txtMatKhau.getText().toString();

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setTitle("Đang đăng nhập");
        dialog.setMessage("Chờ xử lý...");
        dialog.show();
        new DangNhapLoader1(){
            @Override
            protected void onPostExecute(String s) {
                dialog.cancel();
                try {
                    JSONObject json = new JSONObject(s);
                    boolean success = json.getBoolean("success");
                    if (success) {
                        String token = "Bearer " + json.getString("token");
                        editor.putString("TOKEN", token);
                        editor.commit();
                        launchActivityMenu();
                    } else {
                        String msg = json.getString("msg");
                        taoThongBao("Thông báo", msg).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute(tenDangNhap, matKhau);

    }

    public AlertDialog taoThongBao(String tieuDe, String thongBao) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(thongBao).setTitle(tieuDe);
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }
}
