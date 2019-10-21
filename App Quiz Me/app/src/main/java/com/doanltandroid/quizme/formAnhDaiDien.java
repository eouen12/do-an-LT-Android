package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class formAnhDaiDien extends AppCompatActivity {
    public ImageView imgAnhDaiDien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_anh_dai_dien);

        imgAnhDaiDien = findViewById(R.id.anhdaidien_imageview);
    }

}
