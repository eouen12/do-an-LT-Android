package com.doanltandroid.quizme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RankActivity extends AppCompatActivity{ //implements LoaderManager.LoaderCallbacks<String> {
    private Button btnLichSuChoi;
    private RecyclerView mRecyclerView;
    private RankAdapter mRankAdapter;
    private final ArrayList<NguoiChoi> mListNguoiChoi = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        //btnLichSuChoi = findViewById(R.id.lichsu_lichsuchoi_button);

        mRecyclerView = findViewById(R.id.rcv_ranking);

        mRankAdapter = new RankAdapter(mListNguoiChoi,this);

        mRecyclerView.setAdapter(mRankAdapter);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //if(getSupportLoaderManager().getLoader(0)!=null)
        //{
           // getSupportLoaderManager().initLoader(0,null,this);
        //}
        //getSupportLoaderManager().restartLoader(0,null,this);
    }

    public void launchActivityLichSuChoi(View view) {
        Intent intent = new Intent(getApplicationContext(),LichSuChoiActivity.class);
        startActivity(intent);
       //overridePendingTransition(R.anim.intent_enter,R.anim.intent_exit);
    }

    public void launchActivityProfile(View view) {
        startActivity(new Intent(RankActivity.this,ProfileActivity.class));
    }



   /* @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new RankLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject jsonObject = new JSONObject(data);
            JSONArray itemArray = jsonObject.getJSONArray("dsnNguoiChoi");
            for (int i=0;i<itemArray.length();i++)
            {
                String id = itemArray.getJSONObject(i).getString("id");
                String tenDangNhap = itemArray.getJSONObject(i).getString("ten_dang_nhap");
                String email = itemArray.getJSONObject(i).getString("email");
                String hinhDaiDien = itemArray.getJSONObject(i).getString("hinh_dai_dien");
                String diem = itemArray.getJSONObject(i).getString("diem_cao_nhat");
                String credit = itemArray.getJSONObject(i).getString("credit");

                this.mListNguoiChoi.add(new NguoiChoi(id,tenDangNhap,email,hinhDaiDien,diem,credit));

            }
            this.mRankAdapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }*/
}
