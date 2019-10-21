package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RankActivity extends AppCompatActivity {
    private Button btnLichSuChoi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        btnLichSuChoi = findViewById(R.id.lichsu_lichsuchoi_button);
    }

    public void launchActivityLichSuChoi(View view) {
        Intent intent = new Intent(getApplicationContext(),LichSuChoiActivity.class);
        startActivity(intent);
    }
}
