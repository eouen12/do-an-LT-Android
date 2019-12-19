package com.doanltandroid.quizme.Class;

public class Credit {
    private int id;
    private String tenGoiCredit;
    private int soTien;
    private int soCredit;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenGoiCredit() {
        return tenGoiCredit;
    }

    public void setTenGoiCredit(String tenGoiCredit) {
        this.tenGoiCredit = tenGoiCredit;
    }

    public int getSoTien() {
        return soTien;
    }

    public void setSoTien(int soTien) {
        this.soTien = soTien;
    }

    public int getSoCredit() {
        return soCredit;
    }

    public void setSoCredit(int soCredit) {
        this.soCredit = soCredit;
    }

    public Credit (int id, String tenGoiCredit, int soTien, int soCredit)
    {
        this.id = id;
        this.tenGoiCredit = tenGoiCredit;
        this.soTien = soTien;
        this.soCredit = soCredit;
    }
}
