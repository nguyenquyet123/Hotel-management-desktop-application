package qlks.ui;

import qlks.DAO.KhachHangDAO;
import qlks.Entity.KhachHang;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class QuanLyKhanhHang {
    private JTextField txtTimKiem;
    private JButton timKiemButton;
    private JTable tblKhachHang;
    private JPanel jpnQL_KhachHang;
    private JButton xoaButton;
    private JButton capNhatButton;

    KhachHangDAO khDAO = new KhachHangDAO();
    private DefaultTableModel model;

    public QuanLyKhanhHang(){
        createTable();
        fillTable();
        timKiemButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKiem();
            }
        });
    }

    private void timKiem(){
        model = (DefaultTableModel) tblKhachHang.getModel();
        String searchTerm = txtTimKiem.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        tblKhachHang.setRowSorter(rowSorter);

        List<RowFilter<Object, Object>> filters = new ArrayList<>();

        // Search in "Name" column
        filters.add(RowFilter.regexFilter("(?i)" + searchTerm, 1));

        // Search in "Sdt" column
        filters.add(RowFilter.regexFilter(searchTerm, 2));

        RowFilter<Object, Object> combinedFilter = RowFilter.orFilter(filters);
        rowSorter.setRowFilter(combinedFilter);
    }

    private void fillTable(){
        model = (DefaultTableModel) tblKhachHang.getModel();
        model.setRowCount(0);
        List<KhachHang> list = khDAO.selectAll();
        for (KhachHang b : list) {
            Object objRow[] = new Object[5];
            objRow[0] = b.getMaKH();
            objRow[1] = b.getTenKH();
            objRow[2] = b.getSoDT();
            objRow[3] = b.getTuoi();
            objRow[4] = b.isGioiTinh() ? "Nam" : "Nu";
            model.addRow(objRow);
        }
    }

    public JPanel getJpnQL_KhachHang(){
        return jpnQL_KhachHang;
    }
    private void createTable(){
        tblKhachHang.setModel(new DefaultTableModel(
                null,
                new String[]{"Ma Khach Hang","Ten Khach Hang","So dien Thoai","Tuoi","Gioi Tinh"}
        ));
    }

}
