package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class MenuActivity extends AppCompatActivity {
//    ImageView imgThuThach, imgRanking, imgMuaCredit, imgDangXuat;
    private ImageView avatarImg;
    private TextView txtSoCredit;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    boolean doubleBackToExitPressedOnce = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        sharedPreferences = getSharedPreferences("com.doanltandroid.quizme", MODE_PRIVATE);
        editor = sharedPreferences.edit();

        avatarImg = findViewById(R.id.avata_img);
        txtSoCredit = findViewById(R.id.so_credit);

        String avatar = sharedPreferences.getString("AVATAR", "avatar_temp.jpg");
        int credit = sharedPreferences.getInt("CREDIT", -1);
        Picasso.with(this).load("http://10.0.3.2:8000/storage/avatar/"  + avatar).into(avatarImg);
        txtSoCredit.setText(credit + "");

        String id = sharedPreferences.getString("ID_USER", "-1");
        Log.d("ID-MENU", id);


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
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_left,R.anim.sile_out_right);
    }

    public void setIntent (Class lop){
        Intent intent = new Intent(getApplicationContext(), lop);
        startActivity(intent);
        overridePendingTransition(R.anim.sile_right,R.anim.sile_out_left);
    }
//
    public void launchActivityProfile(View view) {
        setIntent(ProfileActivity.class);
    }

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
//            Intent intent = new Intent(this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            intent.putExtra("EXIT", true);
//            startActivity(intent);
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Nhấn 1 lần nữa để thoát", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 3000);
    }

    public void doLogout(View view) {
        editor.clear();
        editor.commit();
        startActivity(new Intent(this,MainActivity.class));
    }
}
