package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Locale;

public class ThuThachActivity extends AppCompatActivity {
 private Button btnA, btnB, btnC, btnD;
 private ImageView imgSinhMang1;
 private ImageView imgSinhMang2;
 private ImageView imgSinhMang3;
 private ImageView imgSinhMang4;
 private ImageView imgSinhMang5;

    //Count down time
    private static final long START_TIME_IN_MILLIS= 30000;
    private TextView mTextViewCountDown;
    private ProgressBar mProgressBar;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

 public void AnhXa()
 {
     btnA = findViewById(R.id.ButtonA);
     btnB = findViewById(R.id.ButtonB);

     mTextViewCountDown = findViewById(R.id.countdown_textView);
     mProgressBar = findViewById(R.id.progressBar);
     mProgressBar.setMax((int) START_TIME_IN_MILLIS / 1000);
     mProgressBar.setProgress((int) START_TIME_IN_MILLIS / 1000);

 }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_thach);

        AnhXa();

        if(!mTimerRunning)
            startTimer();
    }

    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                mProgressBar.setProgress((int) (millisUntilFinished / 1000));
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                Toast.makeText(ThuThachActivity.this,"Đã hết giờ",Toast.LENGTH_SHORT).show();
            }
        }.start();

        mTimerRunning = true;
    }

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000 ) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;


        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        mTextViewCountDown.setText(timeLeftFormatted);
    }

//    public void Reset(View view) {
//        mCountDownTimer.cancel();
//        mTimerRunning = false;
//        mTimeLeftInMillis = START_TIME_IN_MILLIS;
//        Intent intent = new Intent (this,LinhVucActivity.class);
//        startActivity(intent);
   // }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mCountDownTimer.cancel();
            mTimerRunning = false;
            mTimeLeftInMillis = START_TIME_IN_MILLIS;
            Intent intent = new Intent (this,LinhVucActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onKeyDown(keyCode, event);
    }


    public void ChonA(View view) {
        btnA.setBackgroundResource(R.drawable.custom_btn_dapan_sai);
        btnB.setBackgroundResource(R.drawable.custom_btn_dapan_dung);
    }

    public void launchActivityProfile(View view) {
        startActivity(new Intent(ThuThachActivity.this,ProfileActivity.class));
    }
}
