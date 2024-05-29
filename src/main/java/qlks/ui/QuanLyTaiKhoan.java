package qlks.ui;

import qlks.DAO.TaiKhoanDAO;
import qlks.Entity.NhanVien;
import qlks.Entity.TaiKhoan;
import qlks.utils.DateHelper;
import qlks.utils.DiaLogHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class QuanLyTaiKhoan {
    private JPanel jpnQL_TaiKhoan;
    private JTextField txtTImKiem;
    private JTable tblTaiKhoan;
    private JButton xoaButton;
    private JButton capNhatButton;
    private JButton timKiemButton;

    private DefaultTableModel model;

    TaiKhoanDAO tkDAO = new TaiKhoanDAO();

    public QuanLyTaiKhoan(){

        createTable();
        fillTable();
        timKiemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillTable();
            }
        });
        capNhatButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                update();
            }
        });
        xoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
    }

    private void delete() {
        if (DiaLogHelper.confirm(jpnQL_TaiKhoan, "Ban muon Xoa")) {
            for (int rows : tblTaiKhoan.getSelectedRows()) {
                String maHv = (String) tblTaiKhoan.getValueAt(rows, 0);
                tkDAO.delete(maHv);
            }
            fillTable();
        }
    }
    private void update(){
        try {
            for (int i = 0; i < tblTaiKhoan.getRowCount(); i++) {
                String maNV = (String) tblTaiKhoan.getValueAt(i, 0);
                System.out.println(""+maNV);
                TaiKhoan nv = tkDAO.selectByID(maNV);

                nv.setMatKhau(tblTaiKhoan.getValueAt(i,1).toString());
                System.out.println("" + nv.toString());
                tkDAO.update(nv);
            }
            fillTable();
            DiaLogHelper.alert(jpnQL_TaiKhoan,"Cap Nhat Thanh Cong");
        } catch (NumberFormatException e) {
            System.out.println("" + e.getMessage());
            DiaLogHelper.alert(jpnQL_TaiKhoan, "Cap Nhat That Bai");
        }
    }
    private void fillTable(){
        model = (DefaultTableModel) tblTaiKhoan.getModel();
        model.setRowCount(0);
        List<TaiKhoan> list = tkDAO.selectByKeyword(txtTImKiem.getText());
        for (TaiKhoan b : list) {
            Object objRow[] = new Object[3];
            objRow[0] = b.getMaTK();
            objRow[1] = b.getMatKhau();
            objRow[2] = b.getMaNV();
            model.addRow(objRow);
        }
    }

    public JPanel getJpnQL_TaiKhoan(){
        return jpnQL_TaiKhoan;
    }
    private void createTable(){
        tblTaiKhoan.setModel(new DefaultTableModel(
                null,
                new String[]{"Ma Tai Khoan","Mat Khau","Ma Nhan Vien"}
        ));
    }

}
