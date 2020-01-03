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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.doanltandroid.quizme.Adapter.LinhVucAdapter;
import com.doanltandroid.quizme.Class.LinhVuc;
import com.doanltandroid.quizme.Loader.LinhVucLoader;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class LinhVucActivity extends AppCompatActivity
                                implements LoaderManager.LoaderCallbacks<String> {

    private ArrayList<LinhVuc> mListLinhVuc;
    private LinhVucAdapter adapter;
    private RecyclerView recyclerView;
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private ImageView avatarImg;
    private TextView txtSoCredit;

    private String token;

    private final static String FILE_NAME_SHAREREF = "com.doanltandroid.quizme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linhvuc);
        this.mListLinhVuc = new ArrayList<>();
        this.recyclerView = this.findViewById(R.id.rcv_linh_vuc);
        this.adapter = new LinhVucAdapter(this, this.mListLinhVuc);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        this.recyclerView.addItemDecoration(new LinhVucGridDirection(2, 45, true));

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

        avatarImg = findViewById(R.id.avata_img);
        txtSoCredit = findViewById(R.id.so_credit);

        String avatar = sharedPreferences.getString("AVATAR", "avatar_temp.jpg");
        int credit = sharedPreferences.getInt("CREDIT", -1);
        Picasso.with(this).load("http://10.0.3.2:8000/storage/avatar/"  + avatar).into(avatarImg);
        txtSoCredit.setText(credit + "");
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new LinhVucLoader(this, token);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject obj = new JSONObject(data);
            JSONArray items = obj.getJSONArray("data");
            for (int i=0; i<items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                int id = item.getInt("id");
                String tenLinhVuc = item.getString("ten_linh_vuc");
                String hinhAnh = item.getString("hinh_anh");
                Log.d("HINHANH", hinhAnh);
                this.mListLinhVuc.add(new LinhVuc(id, tenLinhVuc, hinhAnh));
            }
            this.adapter.notifyDataSetChanged();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }


//    public void launchActivityThuThach(View view){
//        startActivity(new Intent(LinhVucActivity.this,ThuThachActivity.class));
//    }
//
//    public void launchActivityProfile(View view) {
//        startActivity(new Intent(LinhVucActivity.this,ProfileActivity.class));
//    }


}
