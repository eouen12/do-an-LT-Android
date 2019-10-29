package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class MenuActivity extends AppCompatActivity {
    ImageView imgThuThach, imgRanking, imgMuaCredit, imgDangXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        imgThuThach     = findViewById(R.id.thuthach_menu_image);
        imgRanking      = findViewById(R.id.xephang_menu_image);
        imgMuaCredit    = findViewById(R.id.muacredit_menu_image);
        imgDangXuat     = findViewById(R.id.dangxuat_menu_image);

    }

    public void launchActivityThuThach(View view) {
        setIntent(LinhVucActivity.class);
    }

    public void launchActivityRank(View view) {
        setIntent(RankActivity.class);
    }

    public void launchActivityCredit(View view) {
        setIntent(CreditActivity.class);
    }

    public void launchActivityMain(View view) {
        setIntent(MainActivity.class);
    }

    public void setIntent (Class lop){
        Intent intent = new Intent(getApplicationContext(), lop);
        startActivity(intent);
        //overridePendingTransition(R.anim.intent_enter,R.anim.intent_exit);
    }

    public void launchActivityProfile(View view) {
        setIntent(ProfileActivity.class);
    }
}
