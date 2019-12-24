package com.doanltandroid.quizme.Class;

public class NguoiChoi {
    private int id;
    private String tenDangNhap;
    private int soDiemCaoNhat;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public int getSoDiemCaoNhat() {
        return soDiemCaoNhat;
    }

    public void setSoDiemCaoNhat(int soDiemCaoNhat) {
        this.soDiemCaoNhat = soDiemCaoNhat;
    }
    public  NguoiChoi(int id, String tenDangNhap, int soDiemCaoNhat){
        this.id = id;
        this.tenDangNhap = tenDangNhap;
        this.soDiemCaoNhat = soDiemCaoNhat;
    }
}
