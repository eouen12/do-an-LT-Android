package com.doanltandroid.quizme;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class Avata extends AppCompatActivity {
    private ImageView imgHinhDaiDien;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String FILE_NAME_SHAREREF = "com.doanltandroid.quizme";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avata);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.avata_constrain);
        int color = ContextCompat.getColor(this,R.color.color_Text_White);
        constraintLayout.setBackgroundColor(color);

        imgHinhDaiDien = findViewById(R.id.avata_img);
        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF, MODE_PRIVATE);

        String avatar = sharedPreferences.getString("AVATAR", "avatar_temp.jpg");
        Log.d("Anh dai dien", avatar);
        Picasso.with(this).load("http://10.0.3.2:8000/storage/avatar/"  + avatar).into(imgHinhDaiDien);

    }

    public void launchActivityProfile(View view) {
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
}
