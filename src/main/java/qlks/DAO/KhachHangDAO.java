package qlks.DAO;

import qlks.Entity.DichVu;
import qlks.Entity.KhachHang;
import qlks.utils.JdbcHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhachHangDAO implements daoInterface<KhachHang,String>{
    final String add_SQL = "insert into KhachHang(tenKH,soDT,tuoi,gioiTinh) values (?,?,?,?)";
    final String update_SQL = "update KhachHang set tenKH = ?,soDT = ? ,tuoi = ?, gioiTinh = ? where maKH = ?";
    final String delete_SQL = "delete from KhachHang where maKH = ?";
    final String selectAll = "select * from KhachHang";
    final String selectByID_SQL = "select * from KhachHang where maKH = ?";

    final String selectByKHnew_SQL = "select * from KhachHang order by maKH desc";
    @Override
    public void add(KhachHang kh) {
        JdbcHelper.update(add_SQL,kh.getTenKH(),kh.getSoDT(),kh.getTuoi(),kh.isGioiTinh());
    }

    @Override
    public void update(KhachHang khachHang) {
        JdbcHelper.update(update_SQL,khachHang.getTenKH(),khachHang.getSoDT(),khachHang.getTuoi(),khachHang.isGioiTinh(),khachHang.getMaKH());
    }

    @Override
    public void delete(String s) {
        JdbcHelper.update(delete_SQL,s);
    }

    @Override
    public List<KhachHang> selectAll() {
        return selectBySql(selectAll);
    }

    @Override
    public KhachHang selectByID(String s) {
        List<KhachHang> list = selectBySql(selectByID_SQL, s);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    public KhachHang selectKHnew() {
        List<KhachHang> list = selectBySql(selectByKHnew_SQL);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<KhachHang> selectBySql(String sql, Object... args) {
        List<KhachHang> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()){
                KhachHang nv = new KhachHang();
                nv.setMaKH(rs.getInt("maKH"));
                nv.setTenKH(rs.getString("tenKH"));
                nv.setSoDT(rs.getString("soDT"));
                nv.setTuoi(rs.getInt("tuoi"));
                nv.setGioiTinh(rs.getBoolean("gioiTinh"));

                list.add(nv);
            }
        } catch (SQLException e) {
        }
        return list;
    }
}
