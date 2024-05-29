package qlks.Entity;

public class phieuHoaDon_DV {
    private int ID;
    private String maHoaDon;
    private String maDichVu;
    private double giaThanh;

    public phieuHoaDon_DV() {
    }

    public phieuHoaDon_DV(int ID, String maHoaDon, String maDichVu, double giaThanh) {
        this.ID = ID;
        this.maHoaDon = maHoaDon;
        this.maDichVu = maDichVu;
        this.giaThanh = giaThanh;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public String getMaDichVu() {
        return maDichVu;
    }

    public void setMaDichVu(String maDichVu) {
        this.maDichVu = maDichVu;
    }

    public double getGiaThanh() {
        return giaThanh;
    }

    public void setGiaThanh(double giaThanh) {
        this.giaThanh = giaThanh;
    }

    @Override
    public String toString() {
        return "phieuHoaDon_DV{" +
                "ID=" + ID +
                ", maHoaDon='" + maHoaDon + '\'' +
                ", maDichVu='" + maDichVu + '\'' +
                ", giaThanh=" + giaThanh +
                '}';
    }
}
