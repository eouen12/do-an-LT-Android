package com.doanltandroid.quizme;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.doanltandroid.quizme.Class.CauHoi;
import com.doanltandroid.quizme.Class.LinhVuc;
import com.doanltandroid.quizme.Loader.CauHoiLoader;
import com.doanltandroid.quizme.Loader.LuotChoiLoader;
import com.doanltandroid.quizme.SQLiteHelper.SQLiteHelper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class ThuThachActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<String> {
    private Button btnA, btnB, btnC, btnD;
    private TextView txtCauHoi, txtSttCauHoi, txtDiem, txtSoMang;

    //Count down time
    private static final long START_TIME_IN_MILLIS= 30000;
    private TextView mTextViewCountDown;
    private ProgressBar mProgressBar;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMillis = START_TIME_IN_MILLIS;

    // shared và token
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private String token;

    private int idLinhVuc;
    private String user_id;
    private String dapAn;

    private SQLiteHelper sqLiteHelper;
    private CauHoi cauHoi;
    private ArrayList<CauHoi> mListCauHoi;

    private int position = 0;
    private int stt = 1;
    private final static int DIEM = 200;
    private int soCauTLDung = 0;
    private AlertDialog.Builder batDauTroChoi;

    private final static String FILE_NAME_SHAREREF = "com.doanltandroid.quizme";

 public void AnhXa()
 {
     btnA = findViewById(R.id.ButtonA);
     btnB = findViewById(R.id.ButtonB);
     btnC = findViewById(R.id.ButtonC);
     btnD = findViewById(R.id.ButtonD);
     txtCauHoi = findViewById(R.id.quiz_thuthach_textview);
     txtSttCauHoi = findViewById(R.id.stt_cauhoi_thuthach_textview);
     txtDiem = findViewById(R.id.so_diem_textview);
     txtSoMang = findViewById(R.id.so_mang_textview);



     mTextViewCountDown = findViewById(R.id.countdown_textView);
     mProgressBar = findViewById(R.id.progressBar);
     mProgressBar.setMax((int) START_TIME_IN_MILLIS / 1000);
     mProgressBar.setProgress((int) START_TIME_IN_MILLIS / 1000);

     sqLiteHelper = new SQLiteHelper(this);

 }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thu_thach);

        AnhXa();

        sharedPreferences = getSharedPreferences(FILE_NAME_SHAREREF, MODE_PRIVATE);
        editor = sharedPreferences.edit();
        token = sharedPreferences.getString("TOKEN", "");
        user_id = sharedPreferences.getString("ID_USER","");
        Log.d("users",user_id);
        if (token == "") {
            finish();
        }

        Intent intent = getIntent();
        idLinhVuc = intent.getIntExtra("ID_LinhVuc",1);
        Log.d("idlinhvuc",idLinhVuc+"");

        if (getSupportLoaderManager().getLoader(0) != null) {
            getSupportLoaderManager().initLoader(0, null, this);
        }
        getSupportLoaderManager().restartLoader(0, null, this);

        mListCauHoi = sqLiteHelper.layDanhSach(idLinhVuc);
        batDauTroChoi = new AlertDialog.Builder(this);
        if(mListCauHoi.size() != 0) {
            batDauTroChoi.setMessage("Nhấn để bắt đầu trò chơi").setTitle("Thông báo");
            batDauTroChoi.setCancelable(false);
            batDauTroChoi.setPositiveButton("Start", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    btnA.setEnabled(true);
                    btnB.setEnabled(true);
                    btnC.setEnabled(true);
                    btnD.setEnabled(true);
                    mTimeTraLoiCauHoi.start();
                }
            });
        }
        else
        {
            batDauTroChoi.setMessage("Hiện tại lĩnh vực này chưa có câu hỏi.\n Xin vui lòng chọn lĩnh vực khác").setTitle("Thông báo");
            batDauTroChoi.setCancelable(false);
            batDauTroChoi.setPositiveButton("Quay lại", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    startActivity(new Intent(getApplicationContext(),LinhVucActivity.class));
                }
            });
        }
        batDauTroChoi.create().show();
    }
     CountDownTimer   mTimeTraLoiCauHoi = new CountDownTimer(mTimeLeftInMillis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMillis = millisUntilFinished;
                mProgressBar.setProgress((int) (millisUntilFinished / 1000));
                updateCountDownText();
            }
            @Override
            public void onFinish() {
                taoThongBao("Thông báo","Hết giờ rồi!").show();
            }
        };

    private void updateCountDownText(){
        int minutes = (int) (mTimeLeftInMillis / 1000 ) / 60;
        int seconds = (int) (mTimeLeftInMillis / 1000) % 60;
        String timeLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted); }

//    public void Reset(View view) {
//        mCountDownTimer.cancel();
//        mTimerRunning = false;
//        mTimeLeftInMillis = START_TIME_IN_MILLIS;
//        Intent intent = new Intent (this,LinhVucActivity.class);
//        startActivity(intent);
   // }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            mCountDownTimer.cancel();
            mTimerRunning = false;
            mTimeLeftInMillis = START_TIME_IN_MILLIS;
            Intent intent = new Intent (this,LinhVucActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int id, @Nullable Bundle args) {
        return new CauHoiLoader(this,token,idLinhVuc);
    }
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String data) {
        try {
            JSONObject object = new JSONObject(data);
            Log.d("cauhoi", data);
            JSONArray itemArrays = object.getJSONArray("data");
            if (idLinhVuc != sqLiteHelper.KtraIdLinhVucDaTonTai(idLinhVuc)) {
                for (int i = 0; i < itemArrays.length(); i++) {
                    JSONObject vitri = itemArrays.getJSONObject(i);
                    String noidung = vitri.getString("noi_dung");
                    int linh_vuc_id = vitri.getInt("linh_vuc_id");
                    String a = vitri.getString("phuong_an_a");
                    String b = vitri.getString("phuong_an_b");
                    String c = vitri.getString("phuong_an_c");
                    String d = vitri.getString("phuong_an_d");
                    String dap_an = vitri.getString("dap_an");
                    cauHoi = new CauHoi(noidung, linh_vuc_id, a, b, c, d, dap_an);
                    sqLiteHelper.themMoiCauHoi(cauHoi);
                }
            }
            hienCauHoi(0, 1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {

    }

    public void hienCauHoi(int i, int stt)
    {
        if(i < mListCauHoi.size()) {
            txtSttCauHoi.setText("Câu hỏi số "+ stt + ": ");
            txtCauHoi.setText(mListCauHoi.get(i).getNoi_dung());
            btnA.setText(mListCauHoi.get(i).getPhuong_an_a());
            btnB.setText(mListCauHoi.get(i).getPhuong_an_b());
            btnC.setText(mListCauHoi.get(i).getPhuong_an_c());
            btnD.setText(mListCauHoi.get(i).getPhuong_an_d());
            dapAn = mListCauHoi.get(i).getDap_an();
            if(i==0){position++;this.stt++;}
        }
        else
        {

        }
    }

    public void thongBaoLuuLuotChoi()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Chức mừng bạn: Trần Triển Chí");
        builder.setMessage("Số câu trả lời đúng: "+soCauTLDung + "\nTổng điểm: "+txtDiem.getText());
        builder.setCancelable(false);
        builder.setNegativeButton("Lưu kết quả", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                new LuotChoiLoader().execute(user_id, soCauTLDung + "", txtDiem + "");
            }
        });
        builder.setPositiveButton("Thoát", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(getApplicationContext(), MenuActivity.class));
            }
        });
        builder.create().show();
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
    public int idDapAn (String dapAn)
    {
        switch (dapAn) {
            case "A": return btnA.getId();
            case "B": return btnB.getId();
            case "C": return btnC.getId();
            case "D": return btnD.getId();
        }
        return 0;
    }

    public void EnableBtnPhuongAn (Boolean b)
    {
        btnA.setEnabled(b);
        btnB.setEnabled(b);
        btnC.setEnabled(b);
        btnD.setEnabled(b);
    }

    public void traLoiCauHoi(View view) {
     EnableBtnPhuongAn(false);
     mTimeTraLoiCauHoi.cancel();
     if(view.getId() == idDapAn(dapAn))
     {
         view.setBackgroundResource(R.drawable.custom_btn_dapan_dung);
         int diem = Integer.parseInt(txtDiem.getText().toString());
         diem += DIEM;
         soCauTLDung++;
         txtDiem.setText(diem+"");
     }
     else {
         if (btnA.getId() == idDapAn(dapAn)) {
             btnA.setBackgroundResource(R.drawable.custom_btn_dapan_dung);
             view.setBackgroundResource(R.drawable.custom_btn_dapan_sai);
         } else if (btnB.getId() == idDapAn(dapAn)) {
             btnB.setBackgroundResource(R.drawable.custom_btn_dapan_dung);
             view.setBackgroundResource(R.drawable.custom_btn_dapan_sai);
         } else if (btnC.getId() == idDapAn(dapAn)) {
             btnC.setBackgroundResource(R.drawable.custom_btn_dapan_dung);
             view.setBackgroundResource(R.drawable.custom_btn_dapan_sai);
         } else {
             btnD.setBackgroundResource(R.drawable.custom_btn_dapan_dung);
             view.setBackgroundResource(R.drawable.custom_btn_dapan_sai);
         }
         txtSoMang.setText(Integer.parseInt(txtSoMang.getText().toString()) - 1 +"");

     }
     CountDownTimer countDownTimer = new CountDownTimer(2000,1000) {
         @Override
         public void onTick(long l) {
         }

         @Override
         public void onFinish() {
             Reset();
             hienCauHoi(position++, stt++);
             mTimeTraLoiCauHoi.start();
             if(position>mListCauHoi.size())
             {
                 mTimeTraLoiCauHoi.cancel();
             }
     }
     }.start();
    }
    public void Reset() {
     EnableBtnPhuongAn(true);
     btnA.setBackgroundResource(R.drawable.custom_btn_thu_thach);
     btnB.setBackgroundResource(R.drawable.custom_btn_thu_thach);
     btnC.setBackgroundResource(R.drawable.custom_btn_thu_thach);
     btnD.setBackgroundResource(R.drawable.custom_btn_thu_thach);
    }
}
