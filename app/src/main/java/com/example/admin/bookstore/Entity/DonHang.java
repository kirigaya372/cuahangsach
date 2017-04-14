package com.example.admin.bookstore.Entity;

/**
 * Created by Admin on 3/4/2017.
 */

public class DonHang {
    private String tenKH;
    private String ngàyDH;
    private String tinhTrangHD;
    private String tongGiaTien;

    public DonHang(){

    }

    public DonHang(String tenKH, String ngàyDH, String tinhTrang, String tongGiaTien) {
        this.tenKH = tenKH;
        this.ngàyDH = ngàyDH;
        this.tinhTrangHD = tinhTrang;
        this.tongGiaTien = tongGiaTien;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getNgàyDH() {
        return ngàyDH;
    }

    public void setNgàyDH(String ngàyDH) {
        this.ngàyDH = ngàyDH;
    }

    public String getTinhTrang() {
        return tinhTrangHD;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrangHD = tinhTrang;
    }

    public String getTongGiaTien() {
        return tongGiaTien;
    }

    public void setTongGiaTien(String tongGiaTien) {
        this.tongGiaTien = tongGiaTien;
    }
}
