package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class CreditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left,R.anim.sile_out_right);
    }

    public void launchActivityProfile(View view) {
        startActivity(new Intent(CreditActivity.this,ProfileActivity.class));
        overridePendingTransition(R.anim.sile_right,R.anim.sile_out_left);
    }
}
