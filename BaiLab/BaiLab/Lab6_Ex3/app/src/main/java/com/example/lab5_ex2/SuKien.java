package com.example.lab5_ex2;

import android.widget.BaseAdapter;

import java.io.Serializable;

public class SuKien implements Serializable {
    private String ten;
    private String phong;
    private String ngay;
    private String gio;
    private String trangthai;

    public String getTrangthai() {
        return trangthai;
    }

    public void setTrangthai(String trangthai) {
        this.trangthai = trangthai;
    }

    public SuKien() {
        this.ten = "";
        this.ngay = "";
        this.phong = "";
        this.gio = "";
        this.trangthai = "true";
    }

    public SuKien(String ten, String phong, String ngay, String gio, String trangthai) {
        this.ten = ten;
        this.phong = phong;
        this.ngay = ngay;
        this.gio = gio;
        this.trangthai = trangthai;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getPhong() {
        return phong;
    }

    public void setPhong(String phong) {
        this.phong = phong;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGio() {
        return gio;
    }

    public void setGio(String gio) {
        this.gio = gio;
    }

    @Override
    public String toString() {
        return "SuKien{" +
                "ten='" + ten + '\'' +
                ", phong='" + phong + '\'' +
                ", ngay='" + ngay + '\'' +
                ", gio='" + gio + '\'' +
                ", trangthai='" + trangthai + '\'' +
                '}';
    }
}
