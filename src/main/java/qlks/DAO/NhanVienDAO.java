package qlks.DAO;

import qlks.Entity.HoaDon;
import qlks.Entity.NhanVien;
import qlks.utils.JdbcHelper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class NhanVienDAO implements daoInterface<NhanVien,String>{

    final String add_SQL = "insert into NHANVIEN values(?,?,?,?,?,?,?,?)";
    final String update_SQL = "update NHANVIEN set HOVATEN = ? ,SODT = ?,EMAIL = ?,GIOITINH = ?, NGAYSINH = ?, VAITRO = ?, GHICHU = ? where MANV = ?";
    final String delete_SQL = "delete from NHANVIEN where MaNV = ?";
    final String selectAll = "select * from NHANVIEN";
    final String selectByID_SQL = "select * from NHANVIEN where MANV = ?";

    final String selectMaTK_sql = "select top(1) * from NHANVIEN order by MANV desc";

    SimpleDateFormat formatDate = new SimpleDateFormat("yyyy/MM/dd");
    @Override
    public void add(NhanVien nhanVien) {
        JdbcHelper.update(add_SQL,nhanVien.getMaNV(),nhanVien.getHoTenNV(),nhanVien.getSoDthNV()
                ,nhanVien.getEmail(),nhanVien.isGioiTinh(),nhanVien.getNgaySinh(),nhanVien.isVaiTro(),nhanVien.getGhiChu());
    }

    @Override
    public void update(NhanVien nhanVien) {
        JdbcHelper.update(update_SQL,nhanVien.getHoTenNV(),nhanVien.getSoDthNV(),nhanVien.getEmail(),nhanVien.isGioiTinh()
                ,formatDate.format(nhanVien.getNgaySinh()),nhanVien.isVaiTro(),nhanVien.getGhiChu(),nhanVien.getMaNV());
    }

    @Override
    public void delete(String s) {
        JdbcHelper.update(delete_SQL,s);
    }

    @Override
    public List<NhanVien> selectAll() {
        return selectBySql(selectAll);
    }

    @Override
    public NhanVien selectByID(String s) {
        List<NhanVien> list = selectBySql(selectByID_SQL, s);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }

    @Override
    public List<NhanVien> selectBySql(String sql, Object... args) {
        List<NhanVien> list = new ArrayList<>();
        try {
            ResultSet rs = JdbcHelper.query(sql, args);
            while (rs.next()){
                NhanVien nv = new NhanVien();
                nv.setMaNV(rs.getString("MANV"));
                nv.setHoTenNV(rs.getString("HOVATEN"));
                nv.setSoDthNV(rs.getString("SODT"));
                nv.setEmail(rs.getString("EMAIL"));
                nv.setGioiTinh(rs.getBoolean("GIOITINH"));
                nv.setNgaySinh(rs.getDate("NGAYSINH"));
                nv.setVaiTro(rs.getBoolean("VAITRO"));
                nv.setGhiChu(rs.getString("GHICHU"));
                list.add(nv);
            }
        } catch (SQLException e) {
        }
        return list;

    }

    public List<NhanVien> selectByKeyword(String keyword){
        String sql = "select * from NHANVIEN where HOVATEN Like ?";
        return this.selectBySql(sql, "%"+keyword+"%");
    }

    public NhanVien selectMaNV(){
        List<NhanVien> list = selectBySql(selectMaTK_sql);
        if(list.isEmpty()){
            return null;
        }
        return list.get(0);
    }
}
