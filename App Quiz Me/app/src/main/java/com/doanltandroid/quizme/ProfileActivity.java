package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.doanltandroid.quizme.Loader.DoiMatKhauLoader;
import com.doanltandroid.quizme.Loader.ThongTinNguoiChoiLoader;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {

    private View layoutInfo, layoutEdit;

    private EditText txtMKHienTai, txtMatKhauMoi, txtXacNhanMatKhauMoi;

    private CircleImageView imgAvatar;

    private TextView txtHoTenProfile, txtHoTenBody, txtEmailBody, txtTongDiem, txtCredit, txtHang;

    private Context context;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String FILE_NAME_SHAREREF = "com.doanltandroid.quizme";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        anhXa();

        String avatar = sharedPreferences.getString("AVATAR", "avatar_temp.jpg");
//        String username = sharedPreferences.getString("USER_NAME", "none");
//        String email = sharedPreferences.getString("EMAIL", "none");
//        String hoTen = sharedPreferences.getString("HOTEN", "none");

        Picasso.with(this).load("http://10.0.3.2:8000/storage/avatar/"  + avatar).into(imgAvatar);
//        txtHoTenProfile.setText(username);
//        txtHoTenBody.setText(hoTen);
//        txtEmailBody.setText(email);

        String id = sharedPreferences.getString("ID_USER", "-1");
        String token = sharedPreferences.getString("TOKEN", "");



        new ThongTinNguoiChoiLoader(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d("data-server", s);
                try {
                    JSONObject json = new JSONObject(s);
                    boolean success = json.getBoolean("success");
                    JSONObject data = json.getJSONObject("data");
                    String tenDangNhap = data.getString("ten_dang_nhap");
                    String email = data.getString("email");
                    String hoTen = data.getString("ho_ten");
                    int diemCaoNhat = data.getInt("diem_cao_nhat");
                    int credit = data.getInt("credit");
                    int hang = json.getInt("hang");

                    txtHoTenProfile.setText(tenDangNhap);
                    txtHoTenBody.setText(hoTen);
                    txtEmailBody.setText(email);
                    txtTongDiem.setText(diemCaoNhat + "");
                    txtCredit.setText(credit + "");
                    txtHang.setText(hang + "");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute(id, token);

    }

    public void anhXa()
    {
        context = this;

        layoutEdit = (View) findViewById(R.id.layoutEditProfile);
        layoutInfo = (View) findViewById(R.id.layoutInfo);

        txtMKHienTai = findViewById(R.id.txtMatKhauHienTai);
        txtMatKhauMoi = findViewById(R.id.txtMatKhauMoi);
        txtXacNhanMatKhauMoi = findViewById(R.id.txtXacNhanMatKhauMoi);

        txtHoTenProfile = findViewById(R.id.txtHoTenProfile);
        txtHoTenBody = findViewById(R.id.txtHoTenBody);
        txtEmailBody = findViewById(R.id.txtEmailBody);
        txtTongDiem = findViewById(R.id.txtTongDiem);
        txtCredit = findViewById(R.id.txtCredit);
        txtHang = findViewById(R.id.txtHang);

        imgAvatar = findViewById(R.id.img_avatar);
    }


    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left,R.anim.sile_out_right);
    }

    public void suaThongTin(View view) {
        layoutInfo.setVisibility(View.GONE);
        layoutEdit.setVisibility(View.VISIBLE);
        txtMKHienTai.requestFocus();
    }

    public void doiMatKhau(View view) {
        final View view1 = view;
        String token = sharedPreferences.getString("TOKEN", "");
        String id = sharedPreferences.getString("ID_USER", "-1");
        String matKhauCu = txtMKHienTai.getText().toString();
        String matKhauMoi = txtMatKhauMoi.getText().toString();
        String xacNhanMKMoi = txtXacNhanMatKhauMoi.getText().toString();

        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Đang xử lý");
        dialog.show();

        new DoiMatKhauLoader(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.d("ket qua", s);
                dialog.dismiss();
                try {
                    JSONObject json = new JSONObject(s);
                    String msg = json.getString("msg");
                    Boolean success = json.getBoolean("success");
                    if (success) {
                        taoThongBao("Thành công", msg).show();
                        huyDoiMatKhau(view1);
                    } else {
                        taoThongBao("Lỗi", msg).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute(id, matKhauCu, matKhauMoi, token);
    }

    public AlertDialog taoThongBao(String tieuDe, String thongBao) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(thongBao).setTitle(tieuDe);
        builder.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return builder.create();
    }

    public void huyDoiMatKhau(View view) {
        layoutInfo.setVisibility(View.VISIBLE);
        layoutEdit.setVisibility(View.GONE);
        txtXacNhanMatKhauMoi.setText("");
        txtMatKhauMoi.setText("");
        txtMKHienTai.setText("");
    }
}
