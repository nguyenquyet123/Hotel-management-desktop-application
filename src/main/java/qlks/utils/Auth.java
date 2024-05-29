package qlks.utils;

import qlks.DAO.NhanVienDAO;
import qlks.Entity.NhanVien;
import qlks.Entity.TaiKhoan;

public class Auth {
    public static TaiKhoan user = null;

    public static void logoff(){
        Auth.user = null;
    }

    public static boolean isLogin(){
        return Auth.user != null;
    }

    public static boolean isManager(){
        NhanVienDAO nvDAO = new NhanVienDAO();
        if(user != null){
            NhanVien nv = nvDAO.selectByID(user.getMaNV());
            return Auth.isLogin() && nv.isVaiTro();
        }
        return false;
    }
}
