package qlks.DAO;

import qlks.Entity.DichVu;
import qlks.Entity.TaiKhoan;
import qlks.utils.JdbcHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DichVuDAO implements daoInterface<DichVu,String>{

    final String add_SQL = "insert into DICHVU values(?,?,?,?)";
    final String update_SQL = "update DICHVU set TENDV = ?,GIATIEN = ? ,MOTA = ? where MADV = ?";
    final String delete_SQL = "delete from DICHVU where MADV = ?";
    final String selectAll = "select * from DICHVU";
    final String selectByID_SQL = "select * from DICHVU where MADV = ?";

    final String selectNotInCourse_SQL = "select * from DICHVU"
            + " where "
            + " MADV not in (select maDichVu from phieuHoaDon_DV where maHoaDon= ?)";
    @Override
    public void add(DichVu dichVu) {
        JdbcHelper.update(add_SQL,dichVu.getMaDV(),dichVu.getTenDV(),dichVu.getGiaThanh(),dichVu.getMoTa());
    }

    @Override
    public void update(DichVu dichVu) {
        JdbcHelper.update(update_SQL,dichVu.getTenDV(),dichVu.getGiaThanh(),dichVu.getMoTa(),dichVu.getMaDV());
    }

    @Override
    public void delete(String s) {
        JdbcHelper.update(delete_SQL,s);
    }

    @Override
    public List<DichVu> selectAll() {
        return selectBySql(selectAll);
    }

    @Override
    public DichVu selectByID(String s) {
        List<DichVu> list = selectBySql(selectByID_SQL, s);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<DichVu> selectBySql(String sql, Object... args) {
        List<DichVu> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()){
                DichVu nv = new DichVu();
                nv.setMaDV(rs.getString("MADV"));
                nv.setTenDV(rs.getString("TENDV"));
                nv.setGiaThanh(rs.getDouble("GIATIEN"));
                nv.setMoTa(rs.getString("MOTA"));

                list.add(nv);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<DichVu> selectNotInCourse(String maHD) {
        return selectBySql(selectNotInCourse_SQL,maHD);
    }
}
