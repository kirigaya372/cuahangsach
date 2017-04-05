package com.example.admin.bookstore.Entity;

/**
 * Created by Admin on 3/4/2017.
 */

public class DonHangSach {
    private String gia;
    private String soLuong;

    public DonHangSach() {
    }

    public DonHangSach(String Gia, String SoLuong) {
        this.gia = Gia;
        this.soLuong = SoLuong;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(String soLuong) {
        this.soLuong = soLuong;
    }
}
