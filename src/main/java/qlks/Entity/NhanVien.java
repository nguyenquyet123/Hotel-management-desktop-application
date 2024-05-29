package qlks.Entity;

import java.util.Date;

public class NhanVien {
    private String maNV;
    private String hoTenNV;
    private String soDthNV;
    private String email;
    private boolean gioiTinh;

    private Date ngaySinh;
    private boolean vaiTro = false;
    private String ghiChu;

    public NhanVien() {
    }

    public NhanVien(String maNV, String hoTenNV, String soDthNV, String email, boolean gioiTinh, Date ngaySinh , boolean vaiTro, String ghiChu) {
        this.maNV = maNV;
        this.hoTenNV = hoTenNV;
        this.soDthNV = soDthNV;
        this.email = email;
        this.gioiTinh = gioiTinh;
        this.ngaySinh = ngaySinh;
        this.vaiTro = vaiTro;
        this.ghiChu = ghiChu;
    }

    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public String getHoTenNV() {
        return hoTenNV;
    }

    public void setHoTenNV(String hoTenNV) {
        this.hoTenNV = hoTenNV;
    }

    public String getSoDthNV() {
        return soDthNV;
    }

    public void setSoDthNV(String soDthNV) {
        this.soDthNV = soDthNV;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(boolean gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    public boolean isVaiTro() {
        return vaiTro;
    }

    public void setVaiTro(boolean vaiTro) {
        this.vaiTro = vaiTro;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    @Override
    public String toString() {
        return "NhanVien{" +
                "maNV='" + maNV + '\'' +
                ", hoTenNV='" + hoTenNV + '\'' +
                ", soDthNV='" + soDthNV + '\'' +
                ", email='" + email + '\'' +
                ", gioiTinh=" + gioiTinh +
                ", ngaySinh=" + ngaySinh +
                ", vaiTro=" + vaiTro +
                ", ghiChu='" + ghiChu + '\'' +
                '}';
    }
}
