package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnDangNhap , btnDangKy;
    TextView txtQuenMatKhau;

    public void AnhXa(){
        btnDangNhap = findViewById(R.id.dangnhap_main_button);
        btnDangKy = findViewById(R.id.dangky_main_button);
        txtQuenMatKhau = findViewById(R.id.quenmatkhau_main_textview);
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
}
