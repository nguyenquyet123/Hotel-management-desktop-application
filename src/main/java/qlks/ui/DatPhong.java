package qlks.ui;

import qlks.DAO.*;
import qlks.Entity.*;
import qlks.utils.Auth;
import qlks.utils.DateHelper;
import qlks.utils.DiaLogHelper;
import qlks.utils.ValidHelper;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class DatPhong {
    private JPanel panel1;
    private JTabbedPane tabbedPane1;
    private JTextField txtMaHD;
    private JButton chonDichVuButton;
    private JTextArea textArea1;
    private JRadioButton namRadioButton;
    private JRadioButton nuRadioButton;
    private JButton datPhongButton;
    private JComboBox cboPhong;
    private JTable tblDichVu_DaChon;
    private JTable tblDichVu;
    private JButton xoaButton;
    private JButton themButton;
    private JTextField txtKhachHang;
    private JTextField txtSoDT;
    private JTextField txtTuoi;
    private JTextField txtGioVao;
    private JButton checkInButton;
    private JPanel contentPane;
    private JPanel jpnP1;
    private JPanel jpn1;
    private JButton btn1;
    private JButton btn5;
    private JButton btn9;
    private JButton btn13;
    private JButton btn2;
    private JButton btn3;
    private JButton btn4;
    private JButton btn6;
    private JButton btn7;
    private JButton btn8;
    private JButton btn10;
    private JButton btn11;
    private JButton btn12;
    private JButton btn14;
    private JButton btn15;
    private JButton btn16;
    private JTable table1;
    private JTextField txtPhong;
    private JButton chonPhongButton;
    private JButton taoMaButton;
    private JPanel jpnDatPhong;
    private PhongDAO dao = new PhongDAO();
    private List<Phong> DSPHONG;


    PhongDAO pDAO = new PhongDAO();
    DichVuDAO dvDAO = new DichVuDAO();
    phieuHoaDon_DvDAO pHD_DAO = new phieuHoaDon_DvDAO();
    KhachHangDAO khDAO = new KhachHangDAO();
    HoaDonDAO hdDAO = new HoaDonDAO();
    private DefaultTableModel model;

    private static String savedData = null;

    public JPanel getPanel1(){
        return panel1;
    }
    private void fillTableModel(){
        DSPHONG = dao.selectAll();
        RoomTableModel modell = new RoomTableModel(DSPHONG);
        table1.setModel(modell);
    }
    private void trangThai(){
        DSPHONG = dao.selectAll();
        for (Phong item:DSPHONG) {
            if (item.getMaPhong().equals("101") && item.isTrangThai() == false){
                btn1.setBackground(Color.red);
            }
            if (item.getMaPhong().equals("102") && item.isTrangThai() == false){
                btn2.setBackground(Color.red);
            }
            if (item.getMaPhong().equals("103") && item.isTrangThai() == false){
                btn3.setBackground(Color.red);
            }
            if (item.getMaPhong().equals("104") && item.isTrangThai() == false){
                btn4.setBackground(Color.red);
            }
            if (item.getMaPhong().equals("105") && item.isTrangThai() == false){
                btn5.setBackground(Color.red);
            }
            if (item.getMaPhong().equals("106") && item.isTrangThai() == false){
                btn6.setBackground(Color.red);
            }
            if (item.getMaPhong().equals("107") && item.isTrangThai() == false){
                btn7.setBackground(Color.red);
            }
            if (item.getMaPhong().equals("108") && item.isTrangThai() == false){
                btn8.setBackground(Color.red);
            }
            if (item.getMaPhong().equals("109") && item.isTrangThai() == false){
                btn9.setBackground(Color.red);
            }
            if (item.getMaPhong().equals("201") && item.isTrangThai() == false){
                btn10.setBackground(Color.red);
            }
            if (item.getMaPhong().equals("202") && item.isTrangThai() == false){
                btn11.setBackground(Color.red);
            }
            if (item.getMaPhong().equals("203") && item.isTrangThai() == false){
                btn12.setBackground(Color.red);
            }
            if (item.getMaPhong().equals("204") && item.isTrangThai() == false){
                btn14.setBackground(Color.red);
            }
            if (item.getMaPhong().equals("205") && item.isTrangThai() == false){
                btn15.setBackground(Color.red);
            }
            if (item.getMaPhong().equals("206") && item.isTrangThai() == false){
                btn16.setBackground(Color.red);
            }
        }
    }
    private void setRoom(){
        btn1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Phong item = dao.selectByID(btn1.getText());
                if (item.isTrangThai() == true){
                    btn1.setBackground(Color.red);
                    txtPhong.setText(btn1.getText());
                    item.setTrangThai(false);
                    dao.update(item);
                }else{
                    btn1.setBackground(new ColorUIResource(253,216,102));
                    txtPhong.setText("");
                    item.setTrangThai(true);
                    dao.update(item);
                }
                fillTableModel();
            }
        });
        btn2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Phong item = dao.selectByID(btn2.getText());
                if (item.isTrangThai() == true){
                    btn2.setBackground(Color.red);
                    txtPhong.setText(btn2.getText());
                    item.setTrangThai(false);
                    dao.update(item);
                }else{
                    btn2.setBackground(new ColorUIResource(253,216,102));
                    txtPhong.setText("");
                    item.setTrangThai(true);
                    dao.update(item);
                }
                fillTableModel();
            }
        });
        btn3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Phong item = dao.selectByID(btn3.getText());
                if (item.isTrangThai() == true){
                    btn3.setBackground(Color.red);
                    txtPhong.setText(btn3.getText());
                    item.setTrangThai(false);
                    dao.update(item);
                }else{
                    btn3.setBackground(new ColorUIResource(253,216,102));
                    txtPhong.setText("");
                    item.setTrangThai(true);
                    dao.update(item);
                }
                fillTableModel();
            }
        });
        btn4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Phong item = dao.selectByID(btn4.getText());
                if (item.isTrangThai() == true){
                    btn4.setBackground(Color.red);
                    txtPhong.setText(btn4.getText());
                    item.setTrangThai(false);
                    dao.update(item);
                }else{
                    btn4.setBackground(new ColorUIResource(253,216,102));
                    txtPhong.setText("");
                    item.setTrangThai(true);
                    dao.update(item);
                }
                fillTableModel();
            }
        });
        btn5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Phong item = dao.selectByID(btn5.getText());
                if (item.isTrangThai() == true){
                    btn5.setBackground(Color.red);
                    txtPhong.setText(btn5.getText());
                    item.setTrangThai(false);
                    dao.update(item);
                }else{
                    btn5.setBackground(new ColorUIResource(253,216,102));
                    txtPhong.setText("");
                    item.setTrangThai(true);
                    dao.update(item);
                }
                fillTableModel();
            }
        });
        btn6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Phong item = dao.selectByID(btn6.getText());
                if (item.isTrangThai() == true){
                    btn6.setBackground(Color.red);
                    txtPhong.setText(btn6.getText());
                    item.setTrangThai(false);
                    dao.update(item);
                }else{
                    btn6.setBackground(new ColorUIResource(253,216,102));
                    txtPhong.setText("");
                    item.setTrangThai(true);
                    dao.update(item);
                }
                fillTableModel();
            }
        });
        btn7.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Phong item = dao.selectByID(btn7.getText());
                if (item.isTrangThai() == true){
                    btn7.setBackground(Color.red);
                    txtPhong.setText(btn7.getText());
                    item.setTrangThai(false);
                    dao.update(item);
                }else{
                    btn7.setBackground(new ColorUIResource(253,216,102));
                    txtPhong.setText("");
                    item.setTrangThai(true);
                    dao.update(item);
                }
                fillTableModel();
            }
        });
        btn8.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Phong item = dao.selectByID(btn8.getText());
                if (item.isTrangThai() == true){
                    btn8.setBackground(Color.red);
                    txtPhong.setText(btn8.getText());
                    item.setTrangThai(false);
                    dao.update(item);
                }else{
                    btn8.setBackground(new ColorUIResource(253,216,102));
                    txtPhong.setText("");
                    item.setTrangThai(true);
                    dao.update(item);
                }
                fillTableModel();
            }
        });
        btn9.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Phong item = dao.selectByID(btn9.getText());
                if (item.isTrangThai() == true){
                    btn9.setBackground(Color.red);
                    txtPhong.setText(btn9.getText());
                    item.setTrangThai(false);
                    dao.update(item);
                }else{
                    btn9.setBackground(new ColorUIResource(253,216,102));
                    txtPhong.setText("");
                    item.setTrangThai(true);
                    dao.update(item);
                }
                fillTableModel();
            }
        });
        btn10.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Phong item = dao.selectByID(btn10.getText());
                if (item.isTrangThai() == true){
                    btn10.setBackground(Color.red);
                    txtPhong.setText(btn10.getText());
                    item.setTrangThai(false);
                    dao.update(item);
                }else{
                    btn10.setBackground(new ColorUIResource(253,216,102));
                    txtPhong.setText("");
                    item.setTrangThai(true);
                    dao.update(item);
                }
                fillTableModel();
            }
        });
        btn11.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Phong item = dao.selectByID(btn11.getText());
                if (item.isTrangThai() == true){
                    btn11.setBackground(Color.red);
                    txtPhong.setText(btn11.getText());
                    item.setTrangThai(false);
                    dao.update(item);
                }else{
                    btn11.setBackground(new ColorUIResource(253,216,102));
                    txtPhong.setText("");
                    item.setTrangThai(true);
                    dao.update(item);
                }
                fillTableModel();
            }
        });
        btn12.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Phong item = dao.selectByID(btn12.getText());
                if (item.isTrangThai() == true){
                    btn12.setBackground(Color.red);
                    txtPhong.setText(btn12.getText());
                    item.setTrangThai(false);
                    dao.update(item);
                }else{
                    btn12.setBackground(new ColorUIResource(253,216,102));
                    txtPhong.setText("");
                    item.setTrangThai(true);
                    dao.update(item);
                }
                fillTableModel();
            }
        });
        btn13.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Phong item = dao.selectByID(btn13.getText());
                if (item.isTrangThai() == true){
                    btn13.setBackground(Color.red);
                    txtPhong.setText(btn13.getText());
                    item.setTrangThai(false);
                    dao.update(item);
                }else{
                    btn13.setBackground(new ColorUIResource(253,216,102));
                    txtPhong.setText("");
                    item.setTrangThai(true);
                    dao.update(item);
                }
                fillTableModel();
            }
        });
        btn14.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Phong item = dao.selectByID(btn14.getText());
                if (item.isTrangThai() == true){
                    btn14.setBackground(Color.red);
                    txtPhong.setText(btn14.getText());
                    item.setTrangThai(false);
                    dao.update(item);
                }else{
                    btn14.setBackground(new ColorUIResource(253,216,102));
                    txtPhong.setText("");
                    item.setTrangThai(true);
                    dao.update(item);
                }
                fillTableModel();
            }
        });
        btn15.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Phong item = dao.selectByID(btn15.getText());
                if (item.isTrangThai() == true){
                    btn15.setBackground(Color.red);
                    txtPhong.setText(btn15.getText());
                    item.setTrangThai(false);
                    dao.update(item);
                }else{
                    btn15.setBackground(new ColorUIResource(253,216,102));
                    txtPhong.setText("");
                    item.setTrangThai(true);
                    dao.update(item);
                }
                fillTableModel();
            }
        });
        btn16.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Phong item = dao.selectByID(btn16.getText());
                if (item.isTrangThai() == true){
                    btn16.setBackground(Color.red);
                    txtPhong.setText(btn16.getText());
                    item.setTrangThai(false);
                    dao.update(item);
                }else{
                    btn16.setBackground(new ColorUIResource(253,216,102));
                    txtPhong.setText("");
                    item.setTrangThai(true);
                    dao.update(item);
                }
                fillTableModel();
            }
        });
    }
    private class RoomTableModel extends AbstractTableModel {
        private String [] COLUMNS = {"So Phong", "Trang Thai"};
        private List<Phong> DSPhong;

        public RoomTableModel(List<Phong> DSPhong) {
            this.DSPhong = DSPhong;
        }

        @Override
        public int getRowCount() {
            return DSPhong.size();
        }

        @Override
        public int getColumnCount() {
            return COLUMNS.length;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return switch (columnIndex){
                case 0 -> DSPhong.get(rowIndex).getMaPhong();
                case 1 -> DSPhong.get(rowIndex).isTrangThai() ? "Con Trong" : "Dang Su Dung";
                default -> "-";
            };
        }

        @Override
        public String getColumnName(int column) {
            return COLUMNS[column];
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            if (getValueAt(0, columnIndex) != null){
                return getValueAt(0, columnIndex).getClass();
            }else {
                return Object.class;
            }
        }
    }
    public DatPhong(){
        //fillCboPhong();
        fillTableModel();
        trangThai();
        setRoom();
        createTable();
        initComponents();
        tabbedPane1.getDisabledIconAt(1);


        chonDichVuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(valid()){
                    tabbedPane1.setSelectedIndex(1);
                    savedData = "This is the saved data";
                    insertKH();
                    insertHD();

                    fillTableDV();
                }

            }
        });

        themButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addDichVu();
            }
        });
        datPhongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(valid()){
                    if (savedData != null) {
                        cleanFrom();
                        JOptionPane.showMessageDialog(null, "Dat Phong Thanh Cong");
                    } else {
                        insertKH();
                        insertHD();
                        cleanFrom();
                        JOptionPane.showMessageDialog(null, "Dat Phong Thanh Cong");
                    }
                }
            }
        });
        checkInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                String currentDateTime = dateFormat.format(new Date());
                txtGioVao.setText(currentDateTime);
            }
        });
        xoaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteDichVu();
            }
        });

        chonPhongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tabbedPane1.setSelectedIndex(2);
            }
        });
        taoMaButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                taoMa();
            }
        });


    }

    private boolean valid(){
        if(txtMaHD.getText().equals("")){
            DiaLogHelper.alert(jpnDatPhong,"Hay Tạo Mã");
            return false;
        }
        if(ValidHelper.checkString(txtKhachHang)){
            DiaLogHelper.alert(jpnDatPhong,"Hay Nhap ho ten va Chỉ chứa alphabet và ký tự trắng");
            return false;
        }
        if(ValidHelper.checkSDT(txtSoDT)){
            DiaLogHelper.alert(jpnDatPhong,"Số điện thoại không phù hợp (11 chữ số)");
            return false;
        }
        if (!namRadioButton.isSelected() && !nuRadioButton.isSelected()) {
            DiaLogHelper.alert(jpnDatPhong, "Hay Chon Gioi Tinh");
            return false;
        }
        if(txtGioVao.getText().equals("")){
            DiaLogHelper.alert(jpnDatPhong,"Hay Check In");
            return false;
        }
        if(txtPhong.getText().equals("")){
            DiaLogHelper.alert(jpnDatPhong,"Hay Chon Phong");
            return false;
        }

        return true;
    }

    private void taoMa(){
        String maHD = hdDAO.selectMaHD().getMaHoaDon();
        int soTT = Integer.parseInt(maHD.substring(maHD.length()-2))+1;
        txtMaHD.setText("HD"+String.valueOf(soTT));
    }

    private void cleanFrom(){
        txtMaHD.setText("");
        txtKhachHang.setText("");
        txtSoDT.setText("");
        txtTuoi.setText("");
        txtGioVao.setText("");
        savedData = null;
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

    private void insertKH(){
        KhachHang kh = getForm_KH();

        try {
            khDAO.add(kh);
            System.out.println("Thêm khách hàng thành công.");
        } catch (RuntimeException ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        }
    }

    private void insertHD(){
        HoaDon hd = getForm_HD();
        Phong p = pDAO.selectByID(txtPhong.getText());
        try {
            hdDAO.add(hd);
            p.setTrangThai(false);
            pDAO.update(p);
            System.out.println("Thêm Hoa Don thành công.");
        } catch (RuntimeException ex) {
            System.out.println("Lỗi: " + ex.getMessage());
        }

    }

    private HoaDon getForm_HD() {
        HoaDon nv = new HoaDon();
        KhachHang kh = khDAO.selectKHnew();

        nv.setMaHoaDon(txtMaHD.getText());
        nv.setMaTK(Auth.user.getMaTK());
        nv.setMaPhong(txtPhong.getText());
        nv.setMaKH(kh.getMaKH());
        nv.setCheckIn(DateHelper.toDate(txtGioVao.getText(),"yyyy-MM-dd HH:mm"));
        nv.setTrangThai(false);
        return nv;
    }

    private KhachHang getForm_KH() {
        String tenKH = txtKhachHang.getText();
        String soDT = txtSoDT.getText();
        int tuoi = Integer.parseInt(txtTuoi.getText());
        boolean gioiTinh = namRadioButton.isSelected();

        KhachHang kh = new KhachHang(tenKH, soDT, tuoi, gioiTinh);
        return kh;
    }

    private void fillTableDV_DC(){
        model = (DefaultTableModel) tblDichVu_DaChon.getModel();
        model.setRowCount(0);
        List<phieuHoaDon_DV> list = pHD_DAO.selectByMaHD(txtMaHD.getText());
        for (phieuHoaDon_DV b : list) {
            Object objRow[] = new Object[4];
            objRow[0] = b.getID();
            objRow[1] = b.getMaHoaDon();
            objRow[2] = b.getMaDichVu();
            objRow[3] = b.getGiaThanh();
            model.addRow(objRow);
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

    private void createTable(){
        tblDichVu.setModel(new DefaultTableModel(
                null,
                new String[]{"Ma Dich Vu","Ten Dich Vu","Gia Thanh","Mo Ta"}
        ));

        tblDichVu_DaChon.setModel(new DefaultTableModel(
                null,
                new String[]{"ID","Ma Hoa Don","Ma Dich Vu","Gia Thanh"}
        ));

    }

    public void initComponents(){
        ButtonGroup btn1 = new ButtonGroup();
        btn1.add(namRadioButton);
        btn1.add(nuRadioButton);
    }

}
