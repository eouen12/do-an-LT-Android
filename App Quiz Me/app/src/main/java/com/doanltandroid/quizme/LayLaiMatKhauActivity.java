package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class LayLaiMatKhauActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laylaimatkhau);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left,R.anim.sile_out_right);
    }
}
