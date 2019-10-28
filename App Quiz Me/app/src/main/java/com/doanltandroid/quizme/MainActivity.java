package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnDangNhap , btnDangKy;

    public  void AnhXa(){
        btnDangNhap = findViewById(R.id.dangnhap_main_button);
        btnDangKy = findViewById(R.id.dangky_main_button);
    }

    public void setIntent (Class lop){
        Intent intent = new Intent(getApplicationContext(),lop);
        startActivity(intent);
       // overridePendingTransition(R.anim.intent_enter,R.anim.intent_exit);
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Tham chiếu find by id ...
        AnhXa();
    }

    public void launchActivityDangNhap(View view) {
        setIntent(DangNhap_Activity.class);
    }

    public void launchActivityDangKy(View view) {
        setIntent(DangKyActivity.class);
    }
}
