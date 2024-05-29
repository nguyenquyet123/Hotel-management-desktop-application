package qlks.Entity;

import java.util.Date;

public class HoaDon {
    private String maHoaDon,maTK,maPhong;
    private int maKH;
    private Date checkIn,checkOut;
    private double tongTien;
    private boolean trangThai;

    public HoaDon() {
    }



    public HoaDon(String maHoaDon, String maTK, String maPhong, int maKH, Date checkIn, Date checkOut, double tongTien, boolean trangThai) {
        this.maHoaDon = maHoaDon;
        this.maTK = maTK;
        this.maPhong = maPhong;
        this.maKH = maKH;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.tongTien = tongTien;
        this.trangThai = trangThai;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaTK() {
        return maTK;
    }

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn = checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut = checkOut;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }
}
