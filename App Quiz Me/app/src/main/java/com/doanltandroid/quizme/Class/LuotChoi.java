package com.doanltandroid.quizme.Class;

import java.sql.Timestamp;

public class LuotChoi {
    public static final String TABLE_NAME="luot_choi";

    public static final String COL_ID="id";
    public static final String COL_NGUOI_CHOI_ID="nguoi_choi_id";
    public static final String COL_SO_CAU="so_cau";
    public static final String COL_DIEM = "diem";

    public static final String TAO_BANG="CREATE TABLE " + TABLE_NAME +" ( "
            + COL_ID +" INTEGER PRIMARY KEY,"
            + COL_NGUOI_CHOI_ID +" INTEGER,"
            + COL_SO_CAU +" INTEGER,"
            + COL_DIEM +" INTEGER )";

    public static final String XOA_BANG="DROP TABLE IF EXIST "+TABLE_NAME;

    private int id;
    private int nguoi_choi_id;
    private int so_cau;
    private int diem;
    private String dateCreate;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNguoi_choi_id() {
        return nguoi_choi_id;
    }

    public void setNguoi_choi_id(int nguoi_choi_id) {
        this.nguoi_choi_id = nguoi_choi_id;
    }

    public int getSo_cau() {
        return so_cau;
    }

    public void setSo_cau(int so_cau) {
        this.so_cau = so_cau;
    }

    public int getDiem() {
        return diem;
    }

    public void setDiem(int diem) {
        this.diem = diem;
    }

    public LuotChoi (int nguoi_choi_id,int so_cau,int diem)
    {
        this.id = 0;
        this.nguoi_choi_id = nguoi_choi_id;
        this.so_cau = so_cau;
        this.diem = diem;
    }
    public LuotChoi(int so_cau,int diem, String dateCreate)
    {
        this.id = 0;
        this.nguoi_choi_id = 0;
        this.so_cau = so_cau;
        this.diem = diem;
        this.dateCreate = dateCreate;
    }

    public String getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(String dateCreate) {
        this.dateCreate = dateCreate;
    }
}
