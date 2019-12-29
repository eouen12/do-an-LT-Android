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
import android.widget.Button;

import com.doanltandroid.quizme.Adapter.LuotChoiAdapter;
import com.doanltandroid.quizme.Class.LuotChoi;
import com.doanltandroid.quizme.Loader.LichSuChoiLoader;
import com.doanltandroid.quizme.Loader.LuotChoiLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LichSuChoiActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private RecyclerView recyclerView;
    private final static ArrayList<LuotChoi> mlstLuotChoi = new ArrayList<>();
    private LuotChoiAdapter luotChoiAdapter;

    private final static int PAGE_SIZE = 25;
    private int currentPage = 1;
    private int totalPage;
    private boolean isLoadingPage = false;
    private boolean isLastPage = false;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private String token;
    private String user_id;

    private final static String FILE_NAME_SHAREREF = "com.doanltandroid.quizme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lich_su_choi);

        //btnRanking = findViewById(R.id.ranking_lichsuchoi_button);

        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        token = sharedPreferences.getString("TOKEN", "");
        user_id = sharedPreferences.getString("ID_USER","");

        if (token == "") {
            finish();
        }

        recyclerView = findViewById(R.id.rcv_lich_su_choi);
        luotChoiAdapter = new LuotChoiAdapter(mlstLuotChoi,this);
        recyclerView.setAdapter(luotChoiAdapter);
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
                        mlstLuotChoi.add(null);
                        luotChoiAdapter.notifyItemInserted(mlstLuotChoi.size()-1);

                        Bundle data = new Bundle();
                        data.putInt("page",currentPage);
                        data.putInt("limit",PAGE_SIZE);
                        LoadData(data);
                    }

                }
            }
        });

    }

    @Override
    public void finish() {
        super.finish();
        startActivity(new Intent(getApplicationContext(),MenuActivity.class));
    }

    public void launchActivityProfile(View view) {
        startActivity(new Intent(LichSuChoiActivity.this,ProfileActivity.class));
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


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new LichSuChoiLoader(this,token,user_id);
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
            Log.d("lichsu",data);
            int total = jsonObject.getInt("total");
            totalPage  = total/PAGE_SIZE;
            if(total % PAGE_SIZE !=0)
            {
                totalPage++;
            }
            JSONArray itemArray = jsonObject.getJSONArray("data");
            if(mlstLuotChoi.size()>0)
            {
                mlstLuotChoi.remove(mlstLuotChoi.size()-1);
                int scrollPosition =mlstLuotChoi.size();
                luotChoiAdapter.notifyItemRemoved(scrollPosition);
            }
            for(int i = 0;i<itemArray.length();i++)
            {
                int so_cau = itemArray.getJSONObject(i).getInt("so_cau");
                int diem = itemArray.getJSONObject(i).getInt("diem");
                String dateCreate = itemArray.getJSONObject(i).getString("created_at");

                mlstLuotChoi.add(new LuotChoi(so_cau,diem,dateCreate));
            }
            luotChoiAdapter.notifyDataSetChanged();

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
