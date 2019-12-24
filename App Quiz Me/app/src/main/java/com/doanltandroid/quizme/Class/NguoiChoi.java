package com.doanltandroid.quizme;

public class NguoiChoi {
    private String id;
    private String tenDangNhap;
    private String email;
    private String hinhDaiDien;
    private String diemCaoNhat;
    private String credit;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTenDangNhap() {
        return tenDangNhap;
    }

    public void setTenDangNhap(String tenDangNhap) {
        this.tenDangNhap = tenDangNhap;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHinhDaiDien() {
        return hinhDaiDien;
    }

    public void setHinhDaiDien(String hinhDaiDien) {
        this.hinhDaiDien = hinhDaiDien;
    }

    public String getDiemCaoNhat() {
        return diemCaoNhat;
    }

    public void setDiemCaoNhat(String diemCaoNhat) {
        this.diemCaoNhat = diemCaoNhat;
    }

    public String getCredit() {
        return credit;
    }

    public void setCredit(String credit) {
        this.credit = credit;
    }

    public NguoiChoi(String id, String tenDangNhap, String email, String hinhDaiDien, String diemCaoNhat, String credit)
    {
        this.id = id;
        this.tenDangNhap = tenDangNhap;
        this.email = email;
        this.hinhDaiDien = hinhDaiDien;
        this.diemCaoNhat = diemCaoNhat;
        this.credit = credit;
    }
}
