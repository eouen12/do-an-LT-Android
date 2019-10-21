package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class ThuThachActivity extends AppCompatActivity {
 ImageView btn;
 Button btnA, btnB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_thach);
        btn = findViewById(R.id.sinhmang_5);
        btnA = findViewById(R.id.ButtonA);
        btnB = findViewById(R.id.ButtonB);

    }


    public void ChonA(View view) {
        btnA.setBackgroundResource(R.color.colorAccent);
        btnB.setBackgroundResource(R.color.colorPrimary);
    }
}
