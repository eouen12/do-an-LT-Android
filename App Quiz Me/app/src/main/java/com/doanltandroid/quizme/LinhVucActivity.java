package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class LinhVucActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linhvuc);
    }


    public void launchActivityThuThach(View view){
        setIntent(ThuThachActivity.class);
    }

    public void setIntent(Class lop) {
        Intent intent = new Intent(getApplicationContext(),lop);
        startActivity(intent);
    }
}
