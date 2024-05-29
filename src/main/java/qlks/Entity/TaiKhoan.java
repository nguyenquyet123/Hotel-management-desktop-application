package qlks.Entity;

public class TaiKhoan {
    private String maTK;
    private String matKhau;
    private String maNV;

    public TaiKhoan() {
    }

    public TaiKhoan(String maTK, String matKhau, String maNV) {
        this.maTK = maTK;
        this.matKhau = matKhau;
        this.maNV = maNV;
    }

    public String getMaTK() {
        return maTK;
    }

    public void setMaTK(String maTK) {
        this.maTK = maTK;
    }

    public String getMatKhau() {
        return matKhau;
    }

    public void setMatKhau(String matKhau) {
        this.matKhau = matKhau;
    }

    public String getMaNV() {
        return maNV;
    }

    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    @Override
    public String toString() {
        return "TaiKhoan{" +
                "maTK='" + maTK + '\'' +
                ", matKhau='" + matKhau + '\'' +
                ", maNV='" + maNV + '\'' +
                '}';
    }
}
