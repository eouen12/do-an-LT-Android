package com.doanltandroid.quizme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LinhVucActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String>  {
    private Button btnLinhVuc1;
    private Button btnLinhVuc2;
    private Button btnLinhVuc3;
    private Button btnLinhVuc4;

    public void AnhXa()
    {
        btnLinhVuc1 = findViewById(R.id.linhvuc_1_button);
        btnLinhVuc2 = findViewById(R.id.linhvuc_2_button);
        btnLinhVuc3 = findViewById(R.id.linhvuc_3_button);
        btnLinhVuc4 = findViewById(R.id.linhvuc_4_button);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linhvuc);

        AnhXa();

        //Kiểm tra nếu Loader tồn tại thì khởi tạo lại
        if(getSupportLoaderManager().getLoader(0)!=null)
        {
            getSupportLoaderManager().initLoader(0,null,this);
        }

        getSupportLoaderManager().restartLoader(0,null,this);

    }



    public void launchActivityThuThach(View view){
        startActivity(new Intent(LinhVucActivity.this,ThuThachActivity.class));
    }

    public void launchActivityProfile(View view) {
        startActivity(new Intent(LinhVucActivity.this,ProfileActivity.class));
    }


    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        //Tạo mới đối tượng LinhVucLoader
        return new LinhVucLoader(this);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        //Nhận chuỗi JSON gán cho các button
        try {
            JSONObject jsonObject = new JSONObject(data); //Nhận chuỗi JSON
            JSONArray itemArray = jsonObject.getJSONArray("dsLinhVuc"); //Lấy dsLinhVuc

            //Gán cho button
            //Ở đây do chuỗi JSON chỉ có 4 lĩnh vực nên vị trị của chuỗi từ 0->3
            btnLinhVuc1.setText(itemArray.getJSONObject(0).getString("ten_linh_vuc"));
            btnLinhVuc2.setText(itemArray.getJSONObject(1).getString("ten_linh_vuc"));
            btnLinhVuc3.setText(itemArray.getJSONObject(2).getString("ten_linh_vuc"));
            btnLinhVuc4.setText(itemArray.getJSONObject(3).getString("ten_linh_vuc"));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }
}
