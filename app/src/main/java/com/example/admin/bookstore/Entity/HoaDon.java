package com.example.admin.bookstore.Entity;

/**
 * Created by nuruha on 06/04/2017.
 */

public class HoaDon {
    private String MaHD;
    private String TenKH;
    private String TenSach;
    private String SoLuong;
    private String TongGiaTien;
    private String TinhTrang;

    public HoaDon(){

    }

    public HoaDon(String maHD, String tenKH, String tenSach, String soLuong, String tongGiaTien, String tinhTrang) {
        MaHD = maHD;
        TenKH = tenKH;
        TenSach = tenSach;
        SoLuong = soLuong;
        TongGiaTien = tongGiaTien;
        TinhTrang = tinhTrang;
    }

    public String getMaHD() {
        return MaHD;
    }

    public void setMaHD(String maHD) {
        MaHD = maHD;
    }

    public String getTenKH() {
        return TenKH;
    }

    public void setTenKH(String tenKH) {
        TenKH = tenKH;
    }

    public String getTenSach() {
        return TenSach;
    }

    public void setTenSach(String tenSach) {
        TenSach = tenSach;
    }

    public String getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(String soLuong) {
        SoLuong = soLuong;
    }

    public String getTongGiaTien() {
        return TongGiaTien;
    }

    public void setTongGiaTien(String tongGiaTien) {
        TongGiaTien = tongGiaTien;
    }

    public String getTinhTrang() {
        return TinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        TinhTrang = tinhTrang;
    }
}
