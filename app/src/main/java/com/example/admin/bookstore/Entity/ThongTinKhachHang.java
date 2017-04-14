package com.example.admin.bookstore.Entity;

/**
 * Created by Admin on 14/4/2017.
 */

public class ThongTinKhachHang {
    private String tenKH;
    private int sdt;
    private String diaChi;
    private boolean active;

    public ThongTinKhachHang() {
    }

    public ThongTinKhachHang(String tenKH, int sdt, String diaChi, boolean active) {
        this.tenKH = tenKH;
        this.sdt = sdt;
        this.diaChi = diaChi;
        this.active = active;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public int getSdt() {
        return sdt;
    }

    public void setSdt(int sdt) {
        this.sdt = sdt;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
