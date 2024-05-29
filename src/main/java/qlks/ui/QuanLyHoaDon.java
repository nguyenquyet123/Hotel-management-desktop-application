package qlks.ui;

import qlks.DAO.*;
import qlks.Entity.*;
import qlks.utils.DateHelper;
import qlks.utils.DiaLogHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class QuanLyHoaDon {
    private JTabbedPane tabbedPane1;
    private JPanel jpnQL_HoaDon;
    private JTextField txtMaHD;
    private JPanel tabs1;
    private JButton thanhToanButton;
    private JTextField txtTimKiem_CTT;
    private JButton btnTimKiemCTT;
    private JTable tblChuaThanhToan;
    private JButton capNhatButton;
    private JTable tblHoaDon;
    private JButton checkOutButton;
    private JTable tblDichVu;
    private JButton xoaDichVuDaButton;
    private JButton themButton;
    private JTable tblDichVu_DaChon;
    private JLabel lblTienPhong;
    private JTextField txtPhong;
    private JTextField txtMaKH;
    private JTextField txtTenKH;
    private JTextField txtCheckIn;
    private JTextField txtCheckOut;
    private JLabel lblTienDV;
    private JLabel lblTongTien;
    private JTextField txtHoaDon;
    private JButton btnTtimKiemHD;

    PhongDAO pDAO = new PhongDAO();
    DichVuDAO dvDAO = new DichVuDAO();
    phieuHoaDon_DvDAO pHD_DAO = new phieuHoaDon_DvDAO();
    KhachHangDAO khDAO = new KhachHangDAO();
    HoaDonDAO hdDAO = new HoaDonDAO();
    private DefaultTableModel model;
    private int index = 0;

    public QuanLyHoaDon(){
        createTable();
        index = -1;
        filltable_ChuaTT();
        filltable_DaTT();




        tblChuaThanhToan.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    index = tblChuaThanhToan.getSelectedRow();
                    edit();
                    fillTableDV();
                    fillTableDV_DC();
                }
            }
        });
        themButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDichVu();

            }
        });
        xoaDichVuDaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDichVu();
            }
        });
        checkOutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String currentDateTime = dateFormat.format(new Date());
                txtCheckOut.setText(currentDateTime);
                tinhSoGioChenhLech();
            }
        });
        thanhToanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateHoaDon();
                filltable_ChuaTT();
                filltable_DaTT();
                DiaLogHelper.alert(null,"Thanh Toan Thanh Cong");
                clearForm();
            }
        });
        tblHoaDon.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    index = tblHoaDon.getSelectedRow();
                    edit2();
                    fillTableDV();
                    fillTableDV_DC();
                    if(!txtCheckOut.getText().equals("")){
                        tinhSoGioChenhLech();
                    }

                }
            }
        });
        btnTimKiemCTT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKiem_Ctt();
            }
        });
        txtTimKiem_CTT.setToolTipText("Tên hoặc Số Phòng");
        btnTtimKiemHD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                timKiem_HD();
            }
        });
        txtHoaDon.setToolTipText("Mã Hóa Đơn hoặc Phòng");
    }
    private void clearForm(){
        txtMaHD.setText("");
        txtCheckOut.setText("");
        txtPhong.setText("");
        txtTenKH.setText("");
        txtCheckIn.setText("");
        txtMaKH.setText("");
    }

    private void updateHoaDon(){
        HoaDon hd = new HoaDon();
        Phong p = new Phong();

        hd.setTrangThai(true);
        hd.setCheckOut(DateHelper.toDate(txtCheckOut.getText(),"yyyy-MM-dd HH:mm"));
        hd.setTongTien(Double.parseDouble(lblTongTien.getText()));
        hd.setMaHoaDon(txtMaHD.getText());
        hdDAO.update(hd);

        p.setMaPhong(txtPhong.getText());
        p.setTrangThai(true);
        pDAO.update(p);
    }

    private void TongTien(){
        if(!lblTienPhong.getText().equals("000000") && !lblTienDV.getText().equals("000000")){
            double tienPhong = Double.parseDouble(lblTienPhong.getText());
            double tienDV = Double.parseDouble(lblTienDV.getText());
            double tongTien = tienPhong +tienDV;

            lblTongTien.setText(String.valueOf(tongTien));
        }
    }
    public void tinhSoGioChenhLech() {
        Date checkIn = DateHelper.toDate(txtCheckIn.getText(),"yyyy-MM-dd HH:mm");
        Date checkOut = DateHelper.toDate(txtCheckOut.getText(),"yyyy-MM-dd HH:mm");

        LocalDateTime checkInDateTime = LocalDateTime.ofInstant(checkIn.toInstant(), ZoneId.systemDefault());
        LocalDateTime checkOutDateTime = LocalDateTime.ofInstant(checkOut.toInstant(), ZoneId.systemDefault());

        Duration duration = Duration.between(checkInDateTime, checkOutDateTime);
        double soGio = (double) duration.toMinutes()/60;

        double tongTien;
        if(soGio <= 1){
            lblTienPhong.setText("60000");
        }else if(soGio <=6){
            tongTien = soGio*50000;
            lblTienPhong.setText(""+Math.ceil(tongTien));
        } else {
            tongTien = soGio*40000;
            lblTienPhong.setText(""+Math.ceil(tongTien));
        }

        TongTien();
    }

    private void tongTienDV(){
        double tongTien = 0;
        for (int i = 0;i < tblDichVu_DaChon.getRowCount();i++){
            double tienDV = (double) tblDichVu_DaChon.getValueAt(i,3);
            tongTien += tienDV;
        }
        lblTienDV.setText(""+tongTien);

        TongTien();
    }

    private void deleteDichVu(){
        for (int rows : tblDichVu_DaChon.getSelectedRows()) {
            Integer id = (Integer) tblDichVu_DaChon.getValueAt(rows,0);
            pHD_DAO.delete(String.valueOf(id));
        }
        fillTableDV();
        fillTableDV_DC();
    }
    private void addDichVu(){
        for (int rows : tblDichVu.getSelectedRows()) {
            phieuHoaDon_DV hv = new phieuHoaDon_DV();
            hv.setMaHoaDon(txtMaHD.getText());
            hv.setMaDichVu((String) tblDichVu.getValueAt(rows, 0));
            hv.setGiaThanh((Double) tblDichVu.getValueAt(rows, 2));

            pHD_DAO.add(hv);
        }
        fillTableDV();
        fillTableDV_DC();
    }

    private void edit() {
        String maNV = (String) tblChuaThanhToan.getValueAt(this.index, 0) ;

        HoaDon nv = hdDAO.selectByID(maNV);
        tabbedPane1.setSelectedIndex(0);
        this.setForm(nv);
    }

    private void edit2() {
        String maNV = (String) tblHoaDon.getValueAt(this.index, 0) ;

        HoaDon nv = hdDAO.selectByID(maNV);
        tabbedPane1.setSelectedIndex(0);
        this.setForm(nv);
    }

    private void setForm(HoaDon nv) {
        KhachHang kh = khDAO.selectByID(String.valueOf(nv.getMaKH()));

        txtMaHD.setText(nv.getMaHoaDon());
        txtPhong.setText(nv.getMaPhong());
        txtMaKH.setText(String.valueOf(nv.getMaKH()));


        txtTenKH.setText(kh.getTenKH());
        txtCheckIn.setText(DateHelper.toString(nv.getCheckIn(),"yyyy-MM-dd HH:mm"));

        if(nv.isTrangThai()){
            txtCheckOut.setText(DateHelper.toString(nv.getCheckOut(),"yyyy-MM-dd HH:mm"));
            checkOutButton.setEnabled(false);
            thanhToanButton.setEnabled(false);
            xoaDichVuDaButton.setEnabled(false);
            themButton.setEnabled(false);
        }else {
            txtCheckOut.setText("");
            lblTienPhong.setText("000000");
            lblTongTien.setText("000000");
            checkOutButton.setEnabled(true);
            thanhToanButton.setEnabled(true);
            xoaDichVuDaButton.setEnabled(true);
            themButton.setEnabled(true);
        }
    }
    private void timKiem_HD(){
        model = (DefaultTableModel) tblHoaDon   .getModel();
        String searchTerm = txtHoaDon.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        tblHoaDon.setRowSorter(rowSorter);

        List<RowFilter<Object, Object>> filters = new ArrayList<>();

        // Search in "Name" column
        filters.add(RowFilter.regexFilter("(?i)" + searchTerm, 0));

        // Search in "ID" column
        filters.add(RowFilter.regexFilter(searchTerm, 1));

        RowFilter<Object, Object> combinedFilter = RowFilter.orFilter(filters);
        rowSorter.setRowFilter(combinedFilter);
    }

    private void timKiem_Ctt(){
        model = (DefaultTableModel) tblChuaThanhToan.getModel();
        String searchTerm = txtTimKiem_CTT.getText().toLowerCase();
        TableRowSorter<DefaultTableModel> rowSorter = new TableRowSorter<>(model);
        tblChuaThanhToan.setRowSorter(rowSorter);

        List<RowFilter<Object, Object>> filters = new ArrayList<>();

        // Search in "Name" column
        filters.add(RowFilter.regexFilter("(?i)" + searchTerm, 3));

        // Search in "ID" column
        filters.add(RowFilter.regexFilter(searchTerm, 1));

        RowFilter<Object, Object> combinedFilter = RowFilter.orFilter(filters);
        rowSorter.setRowFilter(combinedFilter);
    }

    private void filltable_ChuaTT(){
        model = (DefaultTableModel) tblChuaThanhToan.getModel();
        model.setRowCount(0);
        List<HoaDon> list = hdDAO.selectAll_ChuaTT();

        for (HoaDon b : list) {
            Object objRow[] = new Object[5];
            objRow[0] = b.getMaHoaDon();
            objRow[1] = b.getMaPhong();
            objRow[2] = b.getMaKH();
            KhachHang kh = khDAO.selectByID(String.valueOf(b.getMaKH()));
            objRow[3] = kh.getTenKH();
            objRow[4] = DateHelper.toString(b.getCheckIn(),"yyyy-MM-dd HH:mm");
            model.addRow(objRow);
        }
    }

    private void filltable_DaTT(){
        model = (DefaultTableModel) tblHoaDon.getModel();
        model.setRowCount(0);
        List<HoaDon> list = hdDAO.selectAll_DaTT();

        for (HoaDon b : list) {
            Object objRow[] = new Object[6];
            objRow[0] = b.getMaHoaDon();
            objRow[1] = b.getMaPhong();
            objRow[2] = b.getMaKH();
            objRow[3] = DateHelper.toString(b.getCheckIn(),"yyyy-MM-dd HH:mm");
            objRow[4] = DateHelper.toString(b.getCheckOut(),"yyyy-MM-dd HH:mm");
            objRow[5] = b.getTongTien();
            model.addRow(objRow);
        }
    }

    private void fillTableDV_DC(){
        model = (DefaultTableModel) tblDichVu_DaChon.getModel();
        model.setRowCount(0);
        List<phieuHoaDon_DV> list = pHD_DAO.selectByMaHD(txtMaHD.getText());
        if(list != null){
            for (phieuHoaDon_DV b : list) {
                Object objRow[] = new Object[4];
                objRow[0] = b.getID();
                objRow[1] = b.getMaHoaDon();
                objRow[2] = b.getMaDichVu();
                objRow[3] = b.getGiaThanh();
                model.addRow(objRow);
            }
            tongTienDV();
        }
        fillTableDV();

    }

    private void fillTableDV(){
        model = (DefaultTableModel) tblDichVu.getModel();
        model.setRowCount(0);
        List<DichVu> list = dvDAO.selectNotInCourse(txtMaHD.getText());
        for (DichVu b : list) {
            Object objRow[] = new Object[4];
            objRow[0] = b.getMaDV();
            objRow[1] = b.getTenDV();
            objRow[2] = b.getGiaThanh();
            objRow[3] = b.getMoTa();
            model.addRow(objRow);
        }
    }

    public JPanel getJpnQL_HoaDon(){
        return jpnQL_HoaDon;
    }
    private void createTable(){
        tblChuaThanhToan.setModel(new DefaultTableModel(
                null,
                new String[]{"Ma Hoa Don","Phong","Ma Khach Hang","Ten Khach Hang","Check In"}
        ));

        tblHoaDon.setModel(new DefaultTableModel(
                null,
                new String[]{"Ma Hoa Don","Phong","Ma Khach Hang","Check In","Check Out","Tong Tien"}
        ));

        tblDichVu.setModel(new DefaultTableModel(
                null,
                new String[]{"Ma Dich Vu","Ten Dich Vu","Gia Thanh","Mo Ta"}
        ));

        tblDichVu_DaChon.setModel(new DefaultTableModel(
                null,
                new String[]{"ID","Ma Hoa Don","Ma Dich Vu","Gia Thanh"}
        ));
    }

}
