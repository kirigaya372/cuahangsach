package com.example.admin.bookstore.Entity;

/**
 * Created by Admin on 3/4/2017.
 */

public class DonHang {
    private String TenKH;
    private String TinhTrang;
    private String TongGiaTien;

    public DonHang(){

    }

    public DonHang(String tenKH, String tinhTrang, String tongGiaTien) {
        TenKH = tenKH;
        TinhTrang = tinhTrang;
        TongGiaTien = tongGiaTien;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }

    public String getTongGiaTien() {
        return TongGiaTien;
    }

    public void setTongGiaTien(String tongGiaTien) {
        TongGiaTien = tongGiaTien;
    }
}
