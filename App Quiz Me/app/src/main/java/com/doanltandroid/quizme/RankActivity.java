package com.doanltandroid.quizme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.doanltandroid.quizme.Adapter.RankAdapter;
import com.doanltandroid.quizme.Class.NguoiChoi;
import com.doanltandroid.quizme.Loader.RankLoader;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class RankActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>{
    private RecyclerView recyclerView;
    private final static ArrayList<NguoiChoi> mlstNguoiChoi = new ArrayList<>();
    private RankAdapter rankAdapter;

    private final static int PAGE_SIZE = 25;
    private int currentPage = 1;
    private int totalPage;
    private boolean isLoadingPage = false;
    private boolean isLastPage = false;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private String token;

    private final static String FILE_NAME_SHAREREF = "com.doanltandroid.quizme";


    private ImageView avatarImg;
    private TextView txtSoCredit;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rank);

        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        token = sharedPreferences.getString("TOKEN", "");

        if (token == "") {
            finish();
        }

        recyclerView = findViewById(R.id.rcv_ranking);
        rankAdapter = new RankAdapter(mlstNguoiChoi,this);
        recyclerView.setAdapter(rankAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        LoadData(null);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                LinearLayoutManager layoutManager =(LinearLayoutManager)recyclerView.getLayoutManager();
                int visibleItemCount = layoutManager.getChildCount();
                int totalItemCount = layoutManager.getItemCount();
                int first = layoutManager.findFirstVisibleItemPosition();

                if(!isLoadingPage && !isLastPage)
                {
                    if((visibleItemCount+first) >= totalItemCount
                            && first >= 0
                            && totalItemCount >=PAGE_SIZE)
                    {
                        isLoadingPage = true;
                        currentPage++;
                        mlstNguoiChoi.add(null);
                        rankAdapter.notifyItemInserted(mlstNguoiChoi.size()-1);

                        Bundle data = new Bundle();
                        data.putInt("page",currentPage);
                        data.putInt("limit",PAGE_SIZE);
                        LoadData(data);
                    }

                }
            }
        });

        avatarImg = findViewById(R.id.avata_img);
        txtSoCredit = findViewById(R.id.so_credit);

        String avatar = sharedPreferences.getString("AVATAR", "avatar_temp.jpg");
        int credit = sharedPreferences.getInt("CREDIT", -1);
        Picasso.with(this).load("http://10.0.3.2:8000/storage/avatar/"  + avatar).into(avatarImg);
        txtSoCredit.setText(credit + "");


    }

    private void LoadData(@Nullable Bundle data)
    {
        /*ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =null;
        if(connectivityManager != null)
        {
            networkInfo = connectivityManager.getActiveNetworkInfo();
        }

        if(networkInfo != null && networkInfo.isConnected())
        {*/
        if(getSupportLoaderManager().getLoader(0) != null)
        {
            getSupportLoaderManager().restartLoader(0,data,this);
        }
        getSupportLoaderManager().initLoader(0,data,this);
        /*}else {
            taoThongBao("Không thể kết nối server").show();
        }*/
    }
    private AlertDialog taoThongBao(String msg)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(msg).setTitle("Lỗi");
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                moveTaskToBack(true);
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);
            }
        });
        return  builder.create();
    }

    public void launchActivityLichSuChoi(View view) {
        Intent intent = new Intent(getApplicationContext(),LichSuChoiActivity.class);
        startActivity(intent);
        //overridePendingTransition(R.anim.intent_enter,R.anim.intent_exit);
    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new RankLoader(this,token);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            if(data == null)
            {
                taoThongBao("Không thể kết nối server").show();
                return;
            }
            JSONObject jsonObject = new JSONObject(data);
            Log.d("rank",data);
            int total = jsonObject.getInt("total");
            totalPage  = total/PAGE_SIZE;
            if(total % PAGE_SIZE !=0)
            {
                totalPage++;
            }
            JSONArray itemArray = jsonObject.getJSONArray("data");
            if(mlstNguoiChoi.size()>0)
            {
                mlstNguoiChoi.remove(mlstNguoiChoi.size()-1);
                int scrollPosition =mlstNguoiChoi.size();
                rankAdapter.notifyItemRemoved(scrollPosition);
            }
            for(int i = 0;i<itemArray.length();i++)
            {
                int id = itemArray.getJSONObject(i).getInt("id");
                String tenDangNhap = itemArray.getJSONObject(i).getString("ten_dang_nhap");
                String hinhDaiDien = itemArray.getJSONObject(i).getString("hinh_dai_dien");
                String diemCaoNhat = itemArray.getJSONObject(i).getString("diem_cao_nhat");

                mlstNguoiChoi.add(new NguoiChoi(id,tenDangNhap,hinhDaiDien,diemCaoNhat));
            }
            rankAdapter.notifyDataSetChanged();

            isLoadingPage = false;
            isLoadingPage = (currentPage == totalPage);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
