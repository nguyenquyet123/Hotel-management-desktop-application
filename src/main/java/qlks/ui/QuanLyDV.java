package qlks.ui;

import qlks.DAO.DichVuDAO;
import qlks.Entity.DichVu;
import qlks.Entity.Phong;
import qlks.utils.Auth;
import qlks.utils.DiaLogHelper;

import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.EventObject;
import java.util.List;

public class QuanLyDV {
    private JPanel jpnQL_DichVu;
    private JTabbedPane tabpanel;
    private JPanel tabs1;
    private JTextField txtMaDV;
    private JTextField txtTenDV;
    private JTextField txtGiaThanh;
    private JTextArea txtMoTa;
    private JButton themButton;
    private JButton suaButton;
    private JButton xoaButton;
    private JButton lamMoiButton;
    private JTable tblDichVU;
    private JPanel tabs2;

    DichVuDAO dvDAO = new DichVuDAO();
    private DefaultTableModel model;
    private int index = 0;

    public QuanLyDV(){

        createTable();
        fillTable();
        index = -1;
        updateStatus();
        tblDichVU.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 1) {
                    index = tblDichVU.getSelectedRow();
                    edit();
                }
            }
        });
        themButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insert();
            }
        });
        suaButton.addActionListener(new ActionListener() {
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
        lamMoiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

    }

    private void updateStatus() {
        boolean edit = (this.index >= 0);

        txtMaDV.setEditable(!edit);
        themButton.setEnabled(!edit);
        suaButton.setEnabled(edit);
        xoaButton.setEnabled(edit);
    }
    private void delete(){
        DichVu nv = getForm();
        if (dvDAO.selectByID(txtMaDV.getText()) == null) {
            DiaLogHelper.alert(jpnQL_DichVu, "Nguoi Hoc khong ton tai!!");
        } else {
            try {
                dvDAO.delete(nv.getMaDV());
                this.fillTable();
                this.clearForm();
                DiaLogHelper.alert(jpnQL_DichVu, "Xoa Thanh Cong.");
            } catch (Exception e) {
                DiaLogHelper.alert(jpnQL_DichVu, "Xoa That Bai.");
            }

        }
    }
    private void update(){
        DichVu nv = getForm();
        System.out.println(nv.toString());
        if (dvDAO.selectByID(txtMaDV.getText()) == null) {
            DiaLogHelper.alert(jpnQL_DichVu, "Nguoi Hoc khong ton tai!!");
        } else {
            try {
                dvDAO.update(nv);
                this.fillTable();
                this.clearForm();
                DiaLogHelper.alert(jpnQL_DichVu, "Cap Nhat Thanh Cong.");
            } catch (Exception e) {
                DiaLogHelper.alert(jpnQL_DichVu, "Cap nhat That Bai.");
            }

        }
    }
    private void insert(){
        DichVu p = getForm();
        if (dvDAO.selectByID(txtMaDV.getText()) != null) {
            DiaLogHelper.alert(jpnQL_DichVu, "Ma Dich Vu Da Ton Tai!!");
        } else {
            try {
                dvDAO.add(p);
                fillTable();
                this.clearForm();
                DiaLogHelper.alert(jpnQL_DichVu, "Them moi Thanh Cong.");
            } catch (Exception e) {
                System.out.println("" + e.getMessage());
            }
        }
    }


    private void clearForm() {
        DichVu nv = new DichVu();
        this.setForm(nv);
        this.index = -1;
        this.updateStatus();
    }

    private void edit() {
        String maNV = (String) tblDichVU.getValueAt(this.index, 0);
        DichVu nv = dvDAO.selectByID(maNV);
        tabpanel.setSelectedIndex(0);
        this.setForm(nv);
        this.updateStatus();
        if (!Auth.isManager()) {
            xoaButton.setEnabled(false);
        }
    }


    private void setForm(DichVu nv) {
        txtMaDV.setText(nv.getMaDV());
        txtTenDV.setText(nv.getTenDV());
        txtGiaThanh.setText(String.valueOf(nv.getGiaThanh()));
        txtMoTa.setText(nv.getMoTa());
    }

    private DichVu getForm() {
        DichVu nv = new DichVu();

        nv.setMaDV(txtMaDV.getText());
        nv.setTenDV(txtTenDV.getText());
        nv.setGiaThanh(Double.parseDouble(txtGiaThanh.getText()));
        nv.setMoTa(txtMoTa.getText());
        return nv;
    }

    private void fillTable(){
        model = (DefaultTableModel) tblDichVU.getModel();
        model.setRowCount(0);
        List<DichVu> list = dvDAO.selectAll();
        for (DichVu b : list) {
            Object objRow[] = new Object[4];
            objRow[0] = b.getMaDV();
            objRow[1] = b.getTenDV();
            objRow[2] = b.getGiaThanh();
            objRow[3] = b.getMoTa();
            model.addRow(objRow);
        }
    }

    public JPanel getJpnQL_DichVu(){
        return jpnQL_DichVu;
    }
    private void createTable(){
        tblDichVU.setModel(new DefaultTableModel(
                null,
                new String[]{"Ma Dich Vu","Ten Dich Vu","Gia Thanh","Mo Ta"}
        ));

    }



}
