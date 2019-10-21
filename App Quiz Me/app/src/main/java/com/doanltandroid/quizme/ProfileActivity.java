package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    TextView txtTenNguoiChoi, txtDoiMatKhau;
    EditText txtSuaTenNguoiChoi;
    ImageButton ibtnSuaTenNguoiChoi;

    int dem=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtTenNguoiChoi = findViewById(R.id.tentitleNguoiChoi_profile_textview);
        txtSuaTenNguoiChoi = findViewById(R.id.tennguoichoi_profile_edittext);
        txtDoiMatKhau = findViewById(R.id.doimatkhau_profile_textview);
        ibtnSuaTenNguoiChoi = findViewById(R.id.suatennguoichoi_profile_imagebutton);

        txtDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DoiMatKhauActivity.class);
                startActivity(intent);
            }
        });
    }

    public void suaTenNguoiChoi(View view) {
        dem++;
        if(dem%2 != 0)
        {
            txtSuaTenNguoiChoi.setEnabled(true);
            ibtnSuaTenNguoiChoi.setImageDrawable(getDrawable(R.drawable.check));
        }
        else
        {
            txtTenNguoiChoi.setText(txtSuaTenNguoiChoi.getText().toString());
            txtSuaTenNguoiChoi.setEnabled(false);
            ibtnSuaTenNguoiChoi.setImageDrawable(getDrawable(R.drawable.pen));
        }
    }
}
