package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    TextView txtTenNguoiChoi;
    EditText txtSuaTenNguoiChoi, txtMatKhauMoi, txtReMatKhau;
    ImageButton ibtnSuaTenNguoiChoi;
    CheckBox chkDoiMatKhau;
    Button btnDoiMatKhau;

    int dem=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        txtTenNguoiChoi = findViewById(R.id.tentitleNguoiChoi_profile_textview);
        txtSuaTenNguoiChoi = findViewById(R.id.tennguoichoi_profile_edittext);
        ibtnSuaTenNguoiChoi = findViewById(R.id.suatennguoichoi_profile_imagebutton);
        chkDoiMatKhau = findViewById(R.id.doimatkhau_profile_checkBox);
        txtMatKhauMoi = findViewById(R.id.matkhaumoi_profile_edittext);
        txtReMatKhau = findViewById(R.id.rematkhaumoi_profile_edittext);
        btnDoiMatKhau = findViewById(R.id.doimatkhau_profile_button);


        chkDoiMatKhau.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    txtMatKhauMoi.setVisibility(View.VISIBLE);
                    txtReMatKhau.setVisibility(View.VISIBLE);
                    btnDoiMatKhau.setVisibility(View.VISIBLE);
                }
                else
                {
                    txtMatKhauMoi.setVisibility(View.INVISIBLE);
                    txtReMatKhau.setVisibility(View.INVISIBLE);
                    btnDoiMatKhau.setVisibility(View.INVISIBLE);
                    chkDoiMatKhau.setChecked(false);
                }
            }
        });

        btnDoiMatKhau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                txtMatKhauMoi.setVisibility(View.INVISIBLE);
                txtReMatKhau.setVisibility(View.INVISIBLE);
                btnDoiMatKhau.setVisibility(View.INVISIBLE);
                chkDoiMatKhau.setChecked(false);
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

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left,R.anim.sile_out_right);
    }
}
