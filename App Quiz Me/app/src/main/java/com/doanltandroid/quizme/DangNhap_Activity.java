package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class DangNhap_Activity extends AppCompatActivity {

    Button btnQuenMatKhau , btnDangNhap;
    EditText txtTenDangNhap , txtMatKhau;

    public void AnhXa(){
        txtTenDangNhap = findViewById(R.id.tendangnhap_dangnhap_edittext);
        txtMatKhau = findViewById(R.id.matkhau_dangnhap_edittext);
        btnDangNhap = findViewById(R.id.laylaimatkhau_button);
        btnQuenMatKhau = findViewById(R.id.quenmatkhau_dangnhap_button);
    }

    public void setIntent (Class lop){
        Intent intent = new Intent(getApplicationContext(),lop);
        startActivity(intent);
        //overridePendingTransition(R.anim.intent_enter,R.anim.intent_exit);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangnhap);


    }

    public void launchActivityQuenMatKhau(View view) {
        setIntent(LayLaiMatKhauActivity.class);
    }

    public void launchActivityMenu(View view) {
        setIntent(MenuActivity.class);
    }
}
