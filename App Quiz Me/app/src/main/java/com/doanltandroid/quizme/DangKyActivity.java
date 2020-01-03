package com.doanltandroid.quizme;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.doanltandroid.quizme.Loader.DangKyLoader;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class DangKyActivity extends AppCompatActivity {
    private EditText txtUserName, txtEmail, txtFullName, txtPassword, txtConfirmPwd;
    private ImageView img_avatar;
    private ProgressBar pgbDangKy;
    private Button btnRegister;

    final int REQUEST_PICK_IMAGE = 1;
    private Uri uri;
    private Bitmap bitmap;
    private String uploadImg;

    private int colorPrimary;
    private int colorDisablePrimary;

    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;

    private static final String FILE_NAME_SHAREREF = "com.doanltandroid.quizme";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dangky);
//        btnDangKy = findViewById(R.id.dangKy_dangky_button);
//        btnDangKy.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                startActivity(new Intent(DangKyActivity.this,MenuActivity.class));
//                overridePendingTransition(R.anim.sile_right,R.anim.sile_out_left);
//            }
//        });
        anhXa();

        String token = sharedPreferences.getString("TOKEN", "");

        if (token != "") {
            finish();
        }

    }

    public void anhXa() {
        img_avatar = findViewById(R.id.img_avatar);
        txtUserName = findViewById(R.id.txtUserNameReg);
        txtEmail = findViewById(R.id.txtEmailReg);
        txtFullName = findViewById(R.id.txtFullNameReg);
        txtPassword = findViewById(R.id.txtPasswordReg);
        txtConfirmPwd = findViewById(R.id.txtConfirmPwdReg);
        pgbDangKy = findViewById(R.id.pgbDangKy);
        btnRegister = findViewById(R.id.btnRegister);

        colorPrimary = ContextCompat.getColor(this, R.color.colorPrimary);
        colorDisablePrimary = ContextCompat.getColor(this, R.color.disable_primary);

        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF, MODE_PRIVATE);
        editor = sharedPreferences.edit();

        txtUserName.requestFocus();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == REQUEST_PICK_IMAGE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            // Lay Uri den file duoc chon
            uri = data.getData();

            try {
                // Lay hinh anh Bitmap tu Uri
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                uploadImg = encodeBitmapToString(bitmap);
                Log.d("Upload-img", uploadImg);
                // Hien thi hinh anh len ImageView
                img_avatar.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left,R.anim.sile_out_right);
    }

    public void chooseImage(View view) {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(intent.ACTION_GET_CONTENT);

        startActivityForResult(Intent.createChooser(intent, "Select image"),
                REQUEST_PICK_IMAGE);
    }

    public String encodeBitmapToString(Bitmap bmp){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bmp.compress(Bitmap.CompressFormat.JPEG, 100, baos);

        byte[] imageBytes = baos.toByteArray();
        String encodedImage = Base64.encodeToString(imageBytes, Base64.DEFAULT);

        return encodedImage;
    }

    public void dangKy(View view) {
        btnRegister.setEnabled(false);
        btnRegister.setBackgroundColor(colorDisablePrimary);
        pgbDangKy.setVisibility(View.VISIBLE);
        String imgBase64 = "";
        if (bitmap != null) {
             imgBase64 = encodeBitmapToString(bitmap);
        }
        String tenDangNhap = txtUserName.getText().toString();
        String email = txtEmail.getText().toString();
        String matKhau = txtPassword.getText().toString();
        String xacNhanMatKhau = txtConfirmPwd.getText().toString();
        String hoTen = txtFullName.getText().toString();

        if (!matKhau.equals(xacNhanMatKhau)) {
            taoThongBao("Lỗi", "Mật khẩu và mật khẩu xác nhận không trùng khớp").show();
            pgbDangKy.setVisibility(View.INVISIBLE);
            btnRegister.setEnabled(true);
            btnRegister.setBackgroundColor(colorPrimary);
            return;
        }

        new DangKyLoader(){
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                pgbDangKy.setVisibility(View.INVISIBLE);
                btnRegister.setEnabled(true);
                btnRegister.setBackgroundColor(colorPrimary);
                try {
                    JSONObject obj = new JSONObject(s);
                    Boolean success = obj.getBoolean("success");
                    Log.d("json-data", s);
                    if (success) {
                        String id = obj.getString("id");
                        String avatar = obj.getString("avatar");
                        int credit = obj.getInt("credit");
                        String token = "Bearer " + obj.getString("token");
                        Log.d("ID", id);
                        editor.putString("ID_USER", id + "");
                        editor.putString("AVATAR", avatar);
                        editor.putString("TOKEN", token);
                        editor.putInt("CREDIT", credit);
                        editor.commit();
                        finish();
                    } else {
                        String msg = obj.getString("msg");
                        taoThongBao("Lỗi", msg).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }.execute(imgBase64, tenDangNhap, email, matKhau, hoTen);
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


}
