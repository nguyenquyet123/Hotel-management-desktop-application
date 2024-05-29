package qlks.ui;

import qlks.DAO.PhongDAO;
import qlks.Entity.NhanVien;
import qlks.Entity.Phong;
import qlks.Entity.TaiKhoan;
import qlks.utils.DiaLogHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class QuanLyPhong{
    private JTextField txtTimKiem;
    private JButton themMoiButton;
    private JButton timKiemButton;
    private JButton xoaPhongButton;
    private JTable tblPhong;
    private JPanel jpnQL_Phong;

    PhongDAO pDAO = new PhongDAO();
    private DefaultTableModel model;

    public QuanLyPhong(){
        createTable();

        themMoiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insert();
            }
        });
        fillTable();
        timKiemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillTableTimKiem();
            }
        });
        xoaPhongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                delete();
            }
        });
    }

    private void delete(){
        if (DiaLogHelper.confirm(jpnQL_Phong, "Ban muon Xoa")) {
            for (int rows : tblPhong.getSelectedRows()) {
                String maHv = (String) tblPhong.getValueAt(rows, 0);
                pDAO.delete(maHv);
            }
            fillTable();

        }
    }
    private void insert(){
        Phong p = getForm();
        if (pDAO.selectByID(txtTimKiem.getText()) != null) {
            DiaLogHelper.alert(jpnQL_Phong, "Ma Nhan Vien Da Ton Tai!!");
        } else {
            try {
                pDAO.add(p);
                fillTable();
                this.clearForm();
                DiaLogHelper.alert(jpnQL_Phong, "Them moi Thanh Cong.");
            } catch (Exception e) {
                System.out.println("" + e.getMessage());
            }
        }
    }

    private void clearForm(){
        txtTimKiem.setText("");
    }

    private Phong getForm(){
        Phong p = new Phong();
        p.setMaPhong(txtTimKiem.getText());
        p.setTrangThai(true);
        return p;
    }
    private void fillTable(){
        model = (DefaultTableModel) tblPhong.getModel();
        model.setRowCount(0);
        List<Phong> list = pDAO.selectAll();
        for (Phong b : list) {
            Object objRow[] = new Object[2];
            objRow[0] = b.getMaPhong();
            objRow[1] = b.isTrangThai() ? "Con Trong" : "Dang Su Dung";

            model.addRow(objRow);
        }
    }

    private void fillTableTimKiem(){
        model = (DefaultTableModel) tblPhong.getModel();
        model.setRowCount(0);
        List<Phong> list = pDAO.selectByKeyword(txtTimKiem.getText());
        for (Phong b : list) {
            Object objRow[] = new Object[2];
            objRow[0] = b.getMaPhong();
            objRow[1] = b.isTrangThai() ? "Con Trong" : "Dang Su Dung";

            model.addRow(objRow);
        }
    }

    public JPanel getJpnQL_Phong(){
        return jpnQL_Phong;
    }

    private void createTable(){
        tblPhong.setModel(new DefaultTableModel(
                null,
                new String[]{"So Phong","Trang Thai"}
        ));
    }

}
