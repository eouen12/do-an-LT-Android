package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class Avata extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avata);
        ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.avata_constrain);
        int color = ContextCompat.getColor(this,R.color.color_Text_White);
        constraintLayout.setBackgroundColor(color);
    }

    public void launchActivityProfile(View view) {
        Intent intent = new Intent(this,ProfileActivity.class);
        startActivity(intent);
    }
}
