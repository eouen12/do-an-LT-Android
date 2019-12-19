package com.doanltandroid.quizme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.doanltandroid.quizme.Adapter.CreditAdapter;
import com.doanltandroid.quizme.Class.Credit;
import com.doanltandroid.quizme.Loader.CreditLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class CreditActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {

    private RecyclerView rcvGoiCredit;
    private ArrayList<Credit> mLstCredit;
    private CreditAdapter adapter;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private String token;

    private final static String FILE_NAME_SHAREREF = "com.doanltandroid.quizme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);

        this.mLstCredit = new ArrayList<>();
        this.rcvGoiCredit = this.findViewById(R.id.rcv_goi_credit);
        this.adapter = new CreditAdapter(this, this.mLstCredit);
        this.rcvGoiCredit.setAdapter(this.adapter);
        this.rcvGoiCredit.setLayoutManager(new GridLayoutManager(this, 2));
        this.rcvGoiCredit.addItemDecoration(new LinhVucGridDirection(2, 64, true));

        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        token = sharedPreferences.getString("TOKEN", "");

        if (token == "") {
            finish();
        }
        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
        getSupportLoaderManager().restartLoader(0, null, this);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left,R.anim.sile_out_right);
    }

    public void launchActivityProfile(View view) {
        startActivity(new Intent(CreditActivity.this,ProfileActivity.class));
        overridePendingTransition(R.anim.sile_right,R.anim.sile_out_left);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new CreditLoader(this,token);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        Log.d("credit", data);
        try {
            JSONObject obj = new JSONObject(data);
            JSONArray items = obj.getJSONArray("data");
            for (int i=0; i<items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                int id = item.getInt("id");
                String tenGoiCredit = item.getString("ten_goi");
                int soTien = item.getInt("so_tien");
                int soCredit = item.getInt("credit");

                this.mLstCredit.add(new Credit(id, tenGoiCredit, soTien, soCredit));
            }
            this.adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
