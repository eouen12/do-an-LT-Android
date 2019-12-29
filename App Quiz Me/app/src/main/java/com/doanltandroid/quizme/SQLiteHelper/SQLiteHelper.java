package com.doanltandroid.quizme.SQLiteHelper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.doanltandroid.quizme.Class.CauHoi;
import com.doanltandroid.quizme.Class.LuotChoi;

import java.util.ArrayList;

public class SQLiteHelper extends SQLiteOpenHelper {
    public static final String DB_NAME="Doan_db.db";

    public SQLiteHelper(@Nullable Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CauHoi.TAO_BANG);
        sqLiteDatabase.execSQL(LuotChoi.TAO_BANG);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(LuotChoi.XOA_BANG);
        onCreate(sqLiteDatabase);
    }
    // Câu hỏi
    public void themMoiCauHoi(CauHoi cauHoi){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(CauHoi.COL_NOIDung, cauHoi.getNoi_dung());
        values.put(CauHoi.COL_LINHVUC_ID, cauHoi.getLinh_vuc_id());
        values.put(CauHoi.COL_PHUONG_AN_A, cauHoi.getPhuong_an_a());
        values.put(CauHoi.COL_PHUONG_AN_B, cauHoi.getPhuong_an_b());
        values.put(CauHoi.COL_PHUONG_AN_C, cauHoi.getPhuong_an_c());
        values.put(CauHoi.COL_PHUONG_AN_D, cauHoi.getPhuong_an_d());
        values.put(CauHoi.COL_DAP_AN, cauHoi.getDap_an());
        //thực thi truy vấn
        sqLiteDatabase.insert(CauHoi.TABLE_NAME, null, values);
    }
    public int KtraIdLinhVucDaTonTai(int linh_vuc_id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String selecttion =CauHoi.COL_LINHVUC_ID+" = ?";
        String[] selectionArgs={linh_vuc_id+""};
        Cursor cursor =db.query(CauHoi.TABLE_NAME, null, selecttion, selectionArgs, null, null, null);
        int kq = 0;
        if(cursor.moveToNext()) { kq = cursor.getInt(cursor.getColumnIndexOrThrow(CauHoi.COL_LINHVUC_ID)); }
        return kq;
    }
    public ArrayList<CauHoi> layDanhSach(int linh_vuc_id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String selecttion =CauHoi.COL_LINHVUC_ID+" = ?";
        String[] selectionArgs={linh_vuc_id+""};
        Cursor cursor =db.query(CauHoi.TABLE_NAME, null, selecttion, selectionArgs, null, null, null);
        ArrayList<CauHoi> listCauHoi=new ArrayList<>();
        while(cursor.moveToNext()){
            String noidung = cursor.getString(cursor.getColumnIndexOrThrow(CauHoi.COL_NOIDung));
            int idlinhvuc  = cursor.getInt(cursor.getColumnIndexOrThrow(CauHoi.COL_LINHVUC_ID));
            String a = cursor.getString(cursor.getColumnIndexOrThrow(CauHoi.COL_PHUONG_AN_A));
            String b = cursor.getString(cursor.getColumnIndexOrThrow(CauHoi.COL_PHUONG_AN_B));
            String c = cursor.getString(cursor.getColumnIndexOrThrow(CauHoi.COL_PHUONG_AN_C));
            String d = cursor.getString(cursor.getColumnIndexOrThrow(CauHoi.COL_PHUONG_AN_D));
            String dapan = cursor.getString(cursor.getColumnIndexOrThrow(CauHoi.COL_DAP_AN));
            CauHoi cauHoi = new CauHoi(noidung,idlinhvuc,a,b,c,d,dapan);
            listCauHoi.add(cauHoi); }
        cursor.close();
        return listCauHoi;
    }

    // Lượt chơi
    public void themMoiLuotChoi(LuotChoi luotChoi){
        SQLiteDatabase sqLiteDatabase=this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(LuotChoi.COL_NGUOI_CHOI_ID, luotChoi.getNguoi_choi_id());
        values.put(LuotChoi.COL_SO_CAU, luotChoi.getSo_cau());
        values.put(LuotChoi.COL_DIEM, luotChoi.getDiem());
        //thực thi truy vấn
        sqLiteDatabase.insert(LuotChoi.TABLE_NAME, null, values);
    }
}
