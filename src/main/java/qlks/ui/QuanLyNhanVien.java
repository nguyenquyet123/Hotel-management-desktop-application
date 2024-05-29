package qlks.ui;

import qlks.DAO.NhanVienDAO;
import qlks.Entity.NhanVien;
import qlks.utils.DateHelper;
import qlks.utils.DiaLogHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.List;

public class QuanLyNhanVien {
    private JPanel jpnQL_NhanVien;
    private JTextField txtTimKiem;
    private JButton capNhatButton;
    private JButton timKiemButton;
    private JButton xoaButton;
    private JTable tblNhanVien;

    NhanVienDAO nvDAO = new NhanVienDAO();
    private DefaultTableModel model;

    public JPanel getJpnQL_NhanVien(){
        return jpnQL_NhanVien;
    }

    public QuanLyNhanVien(){

        createTable();
        fillTable();
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
        timKiemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillTable();
            }
        });

        txtTimKiem.setToolTipText("Tim Kiem Theo Ten");
    }

    private void delete(){
        if (DiaLogHelper.confirm(jpnQL_NhanVien, "Ban muon Xoa")) {
            for (int rows : tblNhanVien.getSelectedRows()) {
                String maHv = (String) tblNhanVien.getValueAt(rows, 0);
                nvDAO.delete(maHv);
            }
            fillTable();

        }
    }
    private void update(){
        try {
            for (int i = 0; i < tblNhanVien.getRowCount(); i++) {
                String maNV = (String) tblNhanVien.getValueAt(i, 0);
                System.out.println(""+maNV);
                NhanVien nv = nvDAO.selectByID(maNV);

                nv.setHoTenNV(tblNhanVien.getValueAt(i,1).toString());
                nv.setSoDthNV(tblNhanVien.getValueAt(i,2).toString());
                nv.setEmail(tblNhanVien.getValueAt(i,3).toString());
                nv.setNgaySinh(DateHelper.toDate(tblNhanVien.getValueAt(i,5).toString()));
//                if(!tblNhanVien.getValueAt(i,7).equals("")){
//                    nv.setGhiChu(tblNhanVien.getValueAt(i,7).toString());
//                }
                if(tblNhanVien.getValueAt(i,4).toString().equalsIgnoreCase("Nam")){
                    boolean gt = true;
                    nv.setGioiTinh(gt);
                }else if(tblNhanVien.getValueAt(i,4).toString().equalsIgnoreCase("Nu")){
                    boolean gt = false;
                    nv.setGioiTinh(gt);
                }else {
                    DiaLogHelper.alert(jpnQL_NhanVien,"Gioi Tinh Nam hoac Nu");
                    return;
                }

                if(tblNhanVien.getValueAt(i,6).toString().equalsIgnoreCase("Truong Phong")){
                    boolean gt = true;
                    nv.setVaiTro(gt);
                }else if(tblNhanVien.getValueAt(i,6).toString().equalsIgnoreCase("Nhan Vien")){
                    boolean gt = false;
                    nv.setVaiTro(gt);
                }else {
                    DiaLogHelper.alert(jpnQL_NhanVien,"Vai Tro la Truong Phong hoac Nhan Vien");
                    tblNhanVien.setRowSelectionInterval(i,i);
                    return;
                }

                System.out.println("" + nv.toString());
                nvDAO.update(nv);
            }
            fillTable();
            DiaLogHelper.alert(jpnQL_NhanVien,"Cap Nhat Thanh Cong");
        } catch (NumberFormatException e) {
            System.out.println("" + e.getMessage());
            DiaLogHelper.alert(jpnQL_NhanVien, "Cap Nhat That Bai");
        }
    }

    private void fillTable(){
        model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0);
        List<NhanVien> list = nvDAO.selectByKeyword(txtTimKiem.getText());
        for (NhanVien b : list) {
            Object objRow[] = new Object[8];
            objRow[0] = b.getMaNV();
            objRow[1] = b.getHoTenNV();
            objRow[2] = b.getSoDthNV();
            objRow[3] = b.getEmail();
            objRow[4] = b.isGioiTinh() ? "Nam" : "Nu";
            objRow[5] = b.getNgaySinh();
            objRow[6] = b.isVaiTro() ? "Truong phong" : "Nhan Vien";
            objRow[7] = b.getGhiChu();


            model.addRow(objRow);
        }

    }

    private void createTable(){
        tblNhanVien.setModel(new DefaultTableModel(
                null,
                new String[]{"Ma Nhan Vien","Ho Ten","So dien Thoai","Email","Gioi Tinh","Ngay Sinh","Vai Tro","Ghi Chu"}
        ));
    }
}
