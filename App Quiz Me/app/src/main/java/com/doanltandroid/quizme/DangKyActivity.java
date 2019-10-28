package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class DangKyActivity extends AppCompatActivity {
    Button btnDangKy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);

        btnDangKy = findViewById(R.id.dangKy_dangky_button);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DangKyActivity.this,MenuActivity.class));
                //overridePendingTransition(R.anim.intent_enter,R.anim.intent_exit);
            }
        });
    }
}
