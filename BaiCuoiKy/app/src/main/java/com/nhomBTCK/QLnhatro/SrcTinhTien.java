package com.nhomBTCK.QLnhatro;

import java.io.Serializable;

public class SrcTinhTien implements Serializable {
    private String TenPHG;
    private int Dien;
    private int Nuoc;
    private int TienTro;
    private String NgayHD;
    private String TrangThai;
    private int ThanhTien;

    public String getTenPHG() {
        return TenPHG;
    }

    public void setTenPHG(String tenPHG) {
        TenPHG = tenPHG;
    }

    public int getDien() {
        return Dien;
    }

    public void setDien(int dien) {
        Dien = dien;
    }

    public int getNuoc() {
        return Nuoc;
    }

    public void setNuoc(int nuoc) {
        Nuoc = nuoc;
    }

    public int getTienTro() {
        return TienTro;
    }

    public void setTienTro(int tienTro) {
        TienTro = tienTro;
    }

    public String getNgayHD() {
        return NgayHD;
    }

    public void setNgayHD(String ngayHD) {
        NgayHD = ngayHD;
    }

    public String getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(String trangThai) {
        TrangThai = trangThai;
    }

    public int getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(int thanhTien) {
        ThanhTien = thanhTien;
    }

    public SrcTinhTien(String tenPHG, int dien, int nuoc, int tienTro, String ngayHD, String trangThai, int thanhTien) {
        TenPHG = tenPHG;
        Dien = dien;
        Nuoc = nuoc;
        TienTro = tienTro;
        NgayHD = ngayHD;
        TrangThai = trangThai;
        ThanhTien = thanhTien;
    }
}
