package qlks.ui;

import qlks.Main;
import qlks.bean.ChuyenManHinh;
import qlks.bean.DanhMuc;
import qlks.utils.Auth;
import qlks.utils.XImage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class MainForm {

    private JButton btnDangXuat;
    private JButton btnDoiMatKhau;
    private JButton btnTroGiup;
    private JPanel rootPanel;
    private JPanel jbnView;
    private JPanel jpnMenu;
    private JLabel lblTrangChu;
    private JLabel lblDatPhong;
    private JPanel jpnTrangChu;
    private JPanel jpnDatPhong;
    private JPanel jpnQL_Phong;
    private JPanel jpnQL_NhanVien;
    private JPanel jpnQL_DichVu;
    private JPanel jpnQL_KhachHang;
    private JPanel jpnQL_HoaDon;
    private JPanel jpnQL_TaiKhoan;
    private JPanel jpnThongKe;
    private JLabel jlbThongKe;
    private JLabel jlbTaiKhoan;
    private JLabel jlbHoaDon;
    private JLabel jlbKhachHang;
    private JLabel jlbDichVu;
    private JLabel jlbNhanVien;
    private JLabel jlbPhong;
    private JButton btnTaoTaiKhoan;

    public JPanel getRootPane(){

        return rootPanel;
    }
    public MainForm(){

        ChuyenManHinh controller = new ChuyenManHinh(jbnView);
        controller.setView(jpnTrangChu,lblTrangChu);

        List<DanhMuc> list = new ArrayList<>();
        list.add(new DanhMuc("TrangChu",jpnTrangChu,lblTrangChu));
        list.add(new DanhMuc("DatPhong",jpnDatPhong,lblDatPhong));
        list.add(new DanhMuc("Phong",jpnQL_Phong,jlbPhong));
        list.add(new DanhMuc("NhanVien",jpnQL_NhanVien,jlbNhanVien));
        list.add(new DanhMuc("DichVu",jpnQL_DichVu,jlbDichVu));
        list.add(new DanhMuc("KhachHang",jpnQL_KhachHang,jlbKhachHang));
        list.add(new DanhMuc("HoaDon",jpnQL_HoaDon,jlbHoaDon));

        if(Auth.isManager()){
            list.add(new DanhMuc("TaiKhoan",jpnQL_TaiKhoan,jlbTaiKhoan));
            list.add(new DanhMuc("ThongKe",jpnThongKe,jlbThongKe));
        }else {
            btnTaoTaiKhoan.setEnabled(false);
            jpnThongKe.setBackground(Color.GRAY);
            jpnQL_TaiKhoan.setBackground(Color.GRAY);
        }


        controller.setEvent(list);
        btnDangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dangXuat();
            }
        });


        btnTaoTaiKhoan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taoTaiKhoan();
            }
        });
    }

    private void taoTaiKhoan(){
        new TaoTaiKhoan(null).setVisible(true);
    }

    private void dangXuat(){
        for (Window window : Window.getWindows()) {
            if (window instanceof JFrame) {
                window.dispose();
            }
        }
        Auth.logoff();
        String [] arg = {};
        Main.main(arg);
    }

}
