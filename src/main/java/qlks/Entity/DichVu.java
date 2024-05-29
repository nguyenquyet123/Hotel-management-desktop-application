package qlks.Entity;

public class DichVu {
    private String maDV;
    private String tenDV;
    private double giaThanh;
    private String moTa;

    public DichVu() {
    }

    public DichVu(String maDV, String tenDV, double giaThanh, String moTa) {
        this.maDV = maDV;
        this.tenDV = tenDV;
        this.giaThanh = giaThanh;
        this.moTa = moTa;
    }

    public String getMaDV() {
        return maDV;
    }

    public void setMaDV(String maDV) {
        this.maDV = maDV;
    }

    public String getTenDV() {
        return tenDV;
    }

    public void setTenDV(String tenDV) {
        this.tenDV = tenDV;
    }

    public double getGiaThanh() {
        return giaThanh;
    }

    public void setGiaThanh(double giaThanh) {
        this.giaThanh = giaThanh;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }
}
