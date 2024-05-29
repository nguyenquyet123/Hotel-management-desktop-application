package qlks.Entity;

public class KhachHang {
    private int maKH;
    private String tenKH;
    private String soDT;
    private int tuoi;
    private boolean gioiTinh;

    public KhachHang() {
    }

    public KhachHang(String tenKH, String soDT, int tuoi, boolean gioiTinh) {
        this.tenKH = tenKH;
        this.soDT = soDT;
        this.tuoi = tuoi;
        this.gioiTinh = gioiTinh;
    }

    public KhachHang(int maKH, String tenKH, String soDT, int tuoi, boolean gioiTinh) {
        this.maKH = maKH;
        this.tenKH = tenKH;
        this.soDT = soDT;
        this.tuoi = tuoi;
        this.gioiTinh = gioiTinh;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }

    public String getTenKH() {
        return tenKH;
    }

    public void setTenKH(String tenKH) {
        this.tenKH = tenKH;
    }

    public String getSoDT() {
        return soDT;
    }

    public void setSoDT(String soDT) {
        this.soDT = soDT;
    }

    public int getTuoi() {
        return tuoi;
    }

    public void setTuoi(int tuoi) {
        this.tuoi = tuoi;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Override
    public String toString() {
        return "KhachHang{" +
                "maKH=" + maKH +
                ", tenKH='" + tenKH + '\'' +
                ", soDT='" + soDT + '\'' +
                ", tuoi=" + tuoi +
                ", gioiTinh=" + gioiTinh +
                '}';
    }
}
