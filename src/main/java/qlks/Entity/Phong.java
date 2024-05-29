package qlks.Entity;

public class Phong {
    private String maPhong;
    private boolean trangThai;

    public Phong() {
    }

    public Phong(String maPhong, boolean trangThai) {
        this.maPhong = maPhong;
        this.trangThai = trangThai;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "Phong " +maPhong;
    }
}
