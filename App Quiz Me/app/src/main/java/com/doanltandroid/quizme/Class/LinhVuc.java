package com.doanltandroid.quizme.Class;

public class LinhVuc {
    private int id;
    private String tenLinhVuc;
    private String hinhAnh;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenLinhVuc() {
        return tenLinhVuc;
    }

    public void setTenLinhVuc(String tenLinhVuc) {
        this.tenLinhVuc = tenLinhVuc;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public LinhVuc(int id, String tenLinhVuc, String hinhAnh) {
        this.id = id;
        this.tenLinhVuc = tenLinhVuc;
        this.hinhAnh = hinhAnh;
    }
}
