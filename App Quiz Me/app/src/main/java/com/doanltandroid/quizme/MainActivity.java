package com.doanltandroid.quizme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.app.AlertDialog;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

public class MainActivity extends AppCompatActivity
                                    implements LoaderManager.LoaderCallbacks<String> {

    Button btnDangNhap , btnDangKy;
    TextView txtQuenMatKhau;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private HashMap<String, String> params;

    private final static String FILE_NAME_SHAREREF = "com.doanltandroid.quizme";

    public void AnhXa(){
        btnDangNhap = findViewById(R.id.dangnhap_main_button);
        btnDangKy = findViewById(R.id.dangky_main_button);
        txtQuenMatKhau = findViewById(R.id.quenmatkhau_main_textview);
        params = new HashMap<>();
        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF, MODE_PRIVATE);
        editor = sharedPreferences.edit();
    }

    public void setIntent (Class lop){
        Intent intent = new Intent(getApplicationContext(),lop);
        startActivity(intent);
        overridePendingTransition(R.anim.sile_right,R.anim.sile_out_left);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Tham chiếu find by id ...
        AnhXa();

        String token = sharedPreferences.getString("TOKEN", "");

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

    public void launchActivityDangNhap(View view) {
        setIntent(MenuActivity.class);
    }

    public void launchActivityDangKy(View view) {
        setIntent(DangKyActivity.class);
    }

    public void doLogin(View view) {
        EditText txtTenDangNhap = findViewById(R.id.txtTenDangNhap);
        EditText txtMatKhau = findViewById(R.id.txtMatKhau);

        String tenDangNhap = txtTenDangNhap.getText().toString();
        String matKhau = txtMatKhau.getText().toString();

        params.put("ten_dang_nhap", tenDangNhap);
        params.put("password", matKhau);

        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
        getSupportLoaderManager().restartLoader(0, null, this);


    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new DangNhapLoader(this, "dang-nhap", "POST", params);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Log.d("DATA-JSON", data);
        try {
            JSONObject object = new JSONObject(data);
            Boolean success = object.getBoolean("success");
            String msg = object.getString("msg");
            if (success) {
                String token = object.getString("token");
                String type = object.getString("type");
                editor.putString("TOKEN", type + " " + token);
                editor.apply();
                Intent intent = new Intent(this, MenuActivity.class);
                startActivity(intent);
            } else {
                taoThongBao("Thông báo", msg).show();
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

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
