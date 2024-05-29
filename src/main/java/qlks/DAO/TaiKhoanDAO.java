package qlks.DAO;

import qlks.Entity.NhanVien;
import qlks.Entity.TaiKhoan;
import qlks.utils.JdbcHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaiKhoanDAO implements daoInterface<TaiKhoan,String>{

    final String add_SQL = "insert into TAIKHOAN values(?,?,?)";
    final String update_SQL = "update TAIKHOAN set MATKHAU = ?,MANV = ? where MATK = ?";
    final String delete_SQL = "delete from TAIKHOAN where MATK = ?";
    final String selectAll = "select * from TAIKHOAN";
    final String selectByID_SQL = "select * from TAIKHOAN where MATK = ?";

    @Override
    public void add(TaiKhoan taiKhoan) {
        JdbcHelper.update(add_SQL,taiKhoan.getMaTK(),taiKhoan.getMatKhau(),taiKhoan.getMaNV());
    }

    @Override
    public void update(TaiKhoan taiKhoan) {
        JdbcHelper.update(update_SQL,taiKhoan.getMatKhau(),taiKhoan.getMaNV(),taiKhoan.getMaTK());
    }

    @Override
    public void delete(String s) {
        JdbcHelper.update(delete_SQL,s);
    }

    @Override
    public List<TaiKhoan> selectAll() {

        return selectBySql(selectAll);
    }

    @Override
    public TaiKhoan selectByID(String s) {

        List<TaiKhoan> list = selectBySql(selectByID_SQL, s);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<TaiKhoan> selectBySql(String sql, Object... args) {

        List<TaiKhoan> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()){
                TaiKhoan nv = new TaiKhoan();
                nv.setMaNV(rs.getString("MANV"));
                nv.setMaTK(rs.getString("MATK"));
                nv.setMatKhau(rs.getString("MATKHAU"));

                list.add(nv);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<TaiKhoan> selectByKeyword(String keyword){
        String sql = "select * from TAIKHOAN where MANV Like ?";
        return this.selectBySql(sql, "%"+keyword+"%");
    }
}
