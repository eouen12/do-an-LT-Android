package com.doanltandroid.quizme;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.doanltandroid.quizme.Loader.SendEmailForgotPwdLoader;

import org.json.JSONException;
import org.json.JSONObject;

public class LayLaiMatKhauActivity extends AppCompatActivity {
    private EditText txtInputForgotPwd;
    private Button btnForgotPwd;
    private ProgressDialog dialog;
    private View layoutSendEmail, layoutResetPwd, layoutConfirmCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laylaimatkhau);

        anhXa();
    }

    public void anhXa() {
        dialog = new ProgressDialog(this);

        txtInputForgotPwd = findViewById(R.id.txtInputForgotPwd);
        btnForgotPwd = findViewById(R.id.btnForgotPwd);

        layoutResetPwd = (View) findViewById(R.id.layoutResetPwd);
        layoutSendEmail = (View) findViewById(R.id.layoutSendEmail);
        layoutConfirmCode = (View) findViewById(R.id.layoutConfirmCode);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_left,R.anim.sile_out_right);
    }

    public void sendEmailForgotPwd(View view) {
        String email = txtInputForgotPwd.getText().toString();
        dialog.setMessage("Đang xử lý, chờ một chút");
        dialog.show();
        new SendEmailForgotPwdLoader() {
            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                dialog.dismiss();
                try {
                    JSONObject object = new JSONObject(s);
                    String msg = object.getString("msg");
                    Boolean success = object.getBoolean("success");
                    Log.d("data-json", s);
                    if (!success) {
                        taoThongBao("Lỗi", msg).show();
                    } else {
                        layoutSendEmail.setVisibility(View.GONE);
                        layoutConfirmCode.setVisibility(View.VISIBLE);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }.execute(email);
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
