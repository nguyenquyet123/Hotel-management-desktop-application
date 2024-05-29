package qlks.DAO;

import qlks.Entity.Phong;
import qlks.Entity.TaiKhoan;
import qlks.utils.JdbcHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhongDAO implements daoInterface<Phong,String>{

    final String add_SQL = "insert into PHONG values(?,?)";
    final String update_SQL = "update PHONG set TRANGTHAI = ? where MAP = ?";
    final String delete_SQL = "delete from PHONG where MAP = ?";
    final String selectAll = "select * from PHONG";
    final String selectByID_SQL = "select * from PHONG where MAP = ?";
    @Override
    public void add(Phong phong) {
        JdbcHelper.update(add_SQL,phong.getMaPhong(),phong.isTrangThai());
    }

    @Override
    public void update(Phong phong) {
        JdbcHelper.update(update_SQL,phong.isTrangThai(),phong.getMaPhong());
    }

    @Override
    public void delete(String s) {
        JdbcHelper.update(delete_SQL,s);
    }

    @Override
    public List<Phong> selectAll() {
        return selectBySql(selectAll);
    }

    public List<Phong> selecAll_PhongTrong(){
        String sql = "select * from PHONG where TRANGTHAI = 'true'";
        return selectBySql(sql);
    }

    @Override
    public Phong selectByID(String s) {
        List<Phong> list = selectBySql(selectByID_SQL, s);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<Phong> selectBySql(String sql, Object... args) {
        List<Phong> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()){
                Phong nv = new Phong();
                nv.setMaPhong(rs.getString("MAP"));
                nv.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(nv);
            }
        } catch (SQLException e) {
        }
        return list;
    }

    public List<Phong> selectByKeyword(String keyword){
        String sql = "select * from PHONG where MAP Like ?";
        return this.selectBySql(sql, "%"+keyword+"%");
    }
}
