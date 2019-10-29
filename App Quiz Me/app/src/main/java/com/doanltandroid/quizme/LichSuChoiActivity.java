package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class LichSuChoiActivity extends AppCompatActivity {
    private Button btnRanking;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_choi);

        btnRanking = findViewById(R.id.ranking_lichsuchoi_button);
    }

    public void launchActivityRanking(View view) {
        Intent intent = new Intent(getApplicationContext(),RankActivity.class);
        startActivity(intent);
       // overridePendingTransition(R.anim.intent_enter,R.anim.intent_exit);
    }

    public void launchActivityProfile(View view) {
        startActivity(new Intent(LichSuChoiActivity.this,ProfileActivity.class));
    }

}
