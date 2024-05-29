package qlks.DAO;

import qlks.Entity.Phong;
import qlks.Entity.phieuHoaDon_DV;
import qlks.utils.JdbcHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class phieuHoaDon_DvDAO implements daoInterface<phieuHoaDon_DV,String>{

    final String add_SQL = "insert into phieuHoaDon_DV(maHoaDon,maDichVu,giaThanh) values(?,?,?)";
    final String update_SQL = "update phieuHoaDon_DV set HOVATEN = ? ,SODT = ?,EMAIL = ? where MANV = ?";
    final String delete_SQL = "delete from phieuHoaDon_DV where id = ?";
    final String selectAll = "select * from phieuHoaDon_DV ";
    final String selectByID_SQL = "select * from phieuHoaDon_DV where id = ?";



    @Override
    public void add(phieuHoaDon_DV phieuHoaDonDv) {
        JdbcHelper.update(add_SQL,phieuHoaDonDv.getMaHoaDon(),phieuHoaDonDv.getMaDichVu(),phieuHoaDonDv.getGiaThanh());
    }

    @Override
    public void update(phieuHoaDon_DV phieuHoaDonDv) {

    }

    @Override
    public void delete(String s) {
        JdbcHelper.update(delete_SQL,s);
    }

    @Override
    public List<phieuHoaDon_DV> selectAll() {
        return selectBySql(selectAll);
    }

    @Override
    public phieuHoaDon_DV selectByID(String s) {
        List<phieuHoaDon_DV> list = selectBySql(selectByID_SQL, s);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    public List<phieuHoaDon_DV> selectByMaHD(String s){
        String selectByMaHD = "select * from phieuHoaDon_DV where maHoaDon = ?";
        List<phieuHoaDon_DV> list = selectBySql(selectByMaHD, s);
        if(list.isEmpty()){
            return null;
        }
        return list;
    }

    @Override
    public List<phieuHoaDon_DV> selectBySql(String sql, Object... args) {
        List<phieuHoaDon_DV> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()){
                phieuHoaDon_DV nv = new phieuHoaDon_DV();
                nv.setID(rs.getInt("id"));
                nv.setMaHoaDon(rs.getString("maHoaDon"));
                nv.setMaDichVu(rs.getString("maDichVu"));
                nv.setGiaThanh(rs.getDouble("giaThanh"));
                list.add(nv);
            }
        } catch (SQLException e) {
        }
        return list;
    }


}
