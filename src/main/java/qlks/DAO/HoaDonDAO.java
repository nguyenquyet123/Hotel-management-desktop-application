package qlks.DAO;

import qlks.Entity.DichVu;
import qlks.Entity.HoaDon;
import qlks.Entity.Phong;
import qlks.utils.JdbcHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HoaDonDAO implements daoInterface<HoaDon,String>{
    final String add_sql = "insert into HoaDon(maHoaDon,maTk,maPhong,maKH,checkIn,trangThai) values(?,?,?,?,?,?)";

    final String update_sql = "update HoaDon set checkOut = ?, tongTien = ? , trangThai = ? where maHoaDon = ?";
    final String selectAll= " select * from HoaDon";

    final String selectByID_SQL = "select * from HoaDon where maHoaDon = ?";

    final String selectAll_true= " select * from HoaDon where trangThai = 'true'";
    final String selectAll_false= " select * from HoaDon where trangThai = 'false'";

    final String selectYear_SQL = "select distinct year(checkOut) as 'Year' from HoaDon where trangThai ='true' order by Year desc";

    final String selectMonth_SQL = "select distinct month(checkOut) as 'month' from HoaDon where trangThai ='true' and year(checkOut) = ? order by month desc";

    final String selectMaHD_sql = "select top(1) * from HoaDon order by maHoaDon desc";
    @Override
    public void add(HoaDon hoaDon) {
        JdbcHelper.update(add_sql,hoaDon.getMaHoaDon(),hoaDon.getMaTK(),hoaDon.getMaPhong(),hoaDon.getMaKH(),
                hoaDon.getCheckIn(),hoaDon.isTrangThai());
    }

    @Override
    public void update(HoaDon hoaDon) {
        JdbcHelper.update(update_sql,hoaDon.getCheckOut(),hoaDon.getTongTien()
                ,hoaDon.isTrangThai(),hoaDon.getMaHoaDon());
    }

    @Override
    public void delete(String s) {

    }

    @Override
    public List<HoaDon> selectAll() {
        return selectBySql(selectAll);
    }

    public List<HoaDon> selectAll_ChuaTT() {
        return selectBySql(selectAll_false);
    }
    public List<HoaDon> selectAll_DaTT() {
        return selectBySql(selectAll_true);
    }
    public HoaDon selectMaHD(){
        List<HoaDon> list = selectBySql(selectMaHD_sql);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }


    @Override
    public HoaDon selectByID(String s) {
        List<HoaDon> list = selectBySql(selectByID_SQL, s);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<HoaDon> selectBySql(String sql, Object... args) {
        List<HoaDon> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()){
                HoaDon nv = new HoaDon();
                nv.setMaHoaDon(rs.getString("maHoaDon"));
                nv.setMaTK(rs.getString("maTK"));
                nv.setMaPhong(rs.getString("maPhong"));
                nv.setMaKH(rs.getInt("maKH"));
                nv.setCheckIn(rs.getTimestamp("checkIn"));
                nv.setCheckOut(rs.getTimestamp("checkOut"));
                nv.setTongTien(rs.getDouble("tongTien"));
                nv.setTrangThai(rs.getBoolean("trangThai"));
                list.add(nv);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Integer> selectYear(){
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(selectYear_SQL);
            while(rs.next()){
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> selectMonth(int thang){
        List<Integer> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(selectMonth_SQL,thang);
            while(rs.next()){
                list.add(rs.getInt(1));
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
