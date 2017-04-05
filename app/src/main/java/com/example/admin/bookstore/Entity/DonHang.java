package com.example.admin.bookstore.Entity;

/**
 * Created by Admin on 3/4/2017.
 */

public class DonHang {
    private String tenKH;
    private String tinhTrang;
    private String tongGiaTien;

    public DonHang() {
    }

    public DonHang(String TenKH, String TinhTrang, String TongGiaTien) {
        this.tenKH = TenKH;
        this.tinhTrang = TinhTrang;
        this.tongGiaTien = TongGiaTien;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getTongGiaTien() {
        return tongGiaTien;
    }

    public void setTongGiaTien(String tongGiaTien) {
        this.tongGiaTien = tongGiaTien;
    }
}
