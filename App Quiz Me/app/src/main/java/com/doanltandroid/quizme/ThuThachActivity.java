package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ThuThachActivity extends AppCompatActivity {
 private Button btnA, btnB, btnC, btnD;
 private ImageView imgSinhMang1;
 private ImageView imgSinhMang2;
 private ImageView imgSinhMang3;
 private ImageView imgSinhMang4;
 private ImageView imgSinhMang5;

 public void AnhXa()
 {
     btnA = findViewById(R.id.ButtonA);
     btnB = findViewById(R.id.ButtonB);
     imgSinhMang1 = findViewById(R.id.sinhmang_1);
     imgSinhMang2 = findViewById(R.id.sinhmang_2);
     imgSinhMang3 = findViewById(R.id.sinhmang_3);
     imgSinhMang4 = findViewById(R.id.sinhmang_4);
     imgSinhMang5 = findViewById(R.id.sinhmang_5);
 }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_thach);

        AnhXa();
    }


    public void ChonA(View view) {
        btnA.setBackgroundResource(R.drawable.custom_btn_dapan_sai);
        btnB.setBackgroundResource(R.drawable.custom_btn_dapan_dung);
    }

    public void launchActivityProfile(View view) {
        startActivity(new Intent(ThuThachActivity.this,ProfileActivity.class));
    }
}
