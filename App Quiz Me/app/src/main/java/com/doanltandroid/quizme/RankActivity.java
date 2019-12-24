package com.doanltandroid.quizme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.doanltandroid.quizme.Adapter.NguoiChoiAdapter;
import com.doanltandroid.quizme.Class.NguoiChoi;
import com.doanltandroid.quizme.Loader.NguoiChoiLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RankActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private RecyclerView mRecyclerView;
    private NguoiChoiAdapter mNguoiChoiAdapter;
    private ArrayList<NguoiChoi> mListNguoiChoi;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String token;
    private final static String FILE_NAME_SHAREREF = "com.doanltandroid.quizme";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        this.mListNguoiChoi = new ArrayList<>();
        this.mRecyclerView = this.findViewById(R.id.rcv_ranking);
        this.mNguoiChoiAdapter = new NguoiChoiAdapter(this, this.mListNguoiChoi);
        this.mRecyclerView.setAdapter(this.mNguoiChoiAdapter);
        this.mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        token = sharedPreferences.getString("TOKEN", "");
        if(token == ""){
            finish();
        }
        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
        getSupportLoaderManager().restartLoader(0, null, this);

    }
    public void launchActivityLichSuChoi(View view) {
        Intent intent = new Intent(getApplicationContext(),LichSuChoiActivity.class);
        startActivity(intent);
       //overridePendingTransition(R.anim.intent_enter,R.anim.intent_exit);
    }

    public void launchActivityProfile(View view) {
        startActivity(new Intent(RankActivity.this,ProfileActivity.class));
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new NguoiChoiLoader(this,token);
    }
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try{
            JSONObject object = new JSONObject(data);
            JSONArray itemArray = object.getJSONArray("data");
            Log.d("rank",data);
            for(int i=0; i<itemArray.length(); i++){
                JSONObject item = itemArray.getJSONObject(i);
                int id = item.getInt("id");
                String tenDangNhap = item.getString("ten_dang_nhap");
                int diem = item.getInt("diem_cao_nhat");
                Log.d("DN",tenDangNhap);
                mListNguoiChoi.add(new NguoiChoi(id,tenDangNhap,diem));
            }
            mNguoiChoiAdapter.notifyDataSetChanged();
        } catch (JSONException e){
            e.printStackTrace();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
