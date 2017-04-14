package com.example.admin.bookstore.Entity;

/**
 * Created by Admin on 3/4/2017.
 */

public class DonHangSach {
    private String gia;
    private String soLuong;
    private String tinhTrangCT;


    public DonHangSach() {
    }

    public DonHangSach(String gia, String soLuong, String tinhTrangCT) {
        this.gia = gia;
        this.soLuong = soLuong;
        this.tinhTrangCT = tinhTrangCT;
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

    public String getTinhTrangCT() {
        return tinhTrangCT;
    }

    public void setTinhTrangCT(String tinhTrangCT) {
        this.tinhTrangCT = tinhTrangCT;
    }
}
