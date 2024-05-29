package qlks.DAO;

import qlks.utils.JdbcHelper;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ThongKeDAO {
    private List<Object[]> getListOfArray(String sql, String[] cols, Object...args){
        try {
            List<Object[]> list = new ArrayList<>();
            ResultSet rs = JdbcHelper.query(sql, args);
            while(rs.next()){
                Object[] vals = new Object[cols.length];
                for(int i = 0;i < cols.length;i++){
                    vals[i] = rs.getObject(cols[i]);
                }
                list.add(vals);
            }
            rs.getStatement().getConnection().close();
            return list;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new RuntimeException();
        }
    }

    public List<Object[]> getDoanhThu(int nam,int thang){
        String sql = "{Call usp_ThongKeTongTien(?,?,null)}";
        String cols[] = {"Nam","Thang","Ngay","TongTien"};
        return this.getListOfArray(sql, cols, nam,thang);
    }

    public List<Object[]> getDoanhThuNam(int nam){
        String sql = "{Call usp_ThongKeTongTien(?,null,null)}";
        String cols[] = {"Nam","Thang","Ngay","TongTien"};
        return this.getListOfArray(sql, cols, nam);
    }

    public List<Object[]> getDichVu(int nam,int thang){
        String sql = "{Call usp_ThongKeDichVu(?,?,null)}";
        String cols[] = {"Nam","Thang","Ngay","tenDV","TongTienDichVu"};
        return this.getListOfArray(sql, cols, nam,thang);
    }

    public List<Object[]> getDichVuNam(int nam){
        String sql = "{Call usp_ThongKeDichVu(?,null,null)}";
        String cols[] = {"Nam","Thang","Ngay","tenDV","TongTienDichVu"};
        return this.getListOfArray(sql, cols, nam);
    }
}
