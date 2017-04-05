package com.example.admin.bookstore.Entity;

/**
 * Created by Admin on 3/4/2017.
 */

public class Sach {
    private String hinh;
    private String tenSach;
    private String gia;
    private String tenTG;
    private String nhaXB;
    private String tinhTrang;
    private String tomTat;

    public Sach() {

    }

    public Sach( String Hinh,String TenSach, String Gia, String TenTG, String NhaXB, String TinhTrang, String TomTat) {
        hinh = Hinh;
        tenSach = TenSach;
        tenTG = TenTG;
        tomTat = TomTat;
        nhaXB = NhaXB;
        tinhTrang = TinhTrang;
        gia = Gia;
    }

    public String getHinh() {
        return hinh;
    }

    public void setHinh(String hinh) {
        this.hinh = hinh;
    }

    public String getTenTG() {
        return tenTG;
    }

    public void setTenTG(String tenTG) {
        this.tenTG = tenTG;
    }

    public String getTomTat() {
        return tomTat;
    }

    public void setTomTat(String tomTat) {
        this.tomTat = tomTat;
    }

    public String getNhaXB() {
        return nhaXB;
    }

    public void setNhaXB(String nhaXB) {
        this.nhaXB = nhaXB;
    }

    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    public String getGia() {
        return gia;
    }

    public void setGia(String gia) {
        this.gia = gia;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }
}
