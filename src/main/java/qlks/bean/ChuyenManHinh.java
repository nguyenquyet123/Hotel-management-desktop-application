package qlks.bean;

import qlks.ui.QuanLyNhanVien;
import qlks.ui.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

public class ChuyenManHinh {
    private JPanel root;
    private String kindSelected = "";

    List<DanhMuc> list = null;

    public ChuyenManHinh(JPanel jpnRoot){
        this.root = jpnRoot;
    }

    public void setView(JPanel jpnItem, JLabel jlbItem){
        kindSelected = "TrangChu";

        jpnItem.setBackground(new Color(241,194,50));
        jlbItem.setBackground(new Color(241,194,50));

        root.removeAll();
        root.setLayout(new BorderLayout());
        root.add(new TrangChu().getJpnTrangChu());
        root.validate();
        root.repaint();
    }

    public void setEvent(List<DanhMuc> list){
        this.list = list;

        for (DanhMuc danhMuc : list){
            danhMuc.getJlb().addMouseListener(new LabelEvent(danhMuc.getKind(),danhMuc.getJpn(),danhMuc.getJlb()));
        }
    }

    class LabelEvent implements MouseListener{
        private JPanel none;
        private String kind;

        private JPanel jpnItem;
        private JLabel jlbItem;

        public LabelEvent(String kind, JPanel jpnItem, JLabel jlbItem) {
            this.kind = kind;
            this.jpnItem = jpnItem;
            this.jlbItem = jlbItem;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            switch (kind){
                case "TrangChu":
                    none = new TrangChu().getJpnTrangChu();
                    break;
                case "DatPhong":
                    none = new DatPhong().getPanel1();
                    break;
                case "Phong":
                    none = new QuanLyPhong().getJpnQL_Phong();
                    break;
                case "DichVu":
                    none = new QuanLyDV().getJpnQL_DichVu();
                    break;
                case "KhachHang":
                    none = new QuanLyKhanhHang().getJpnQL_KhachHang();
                    break;
                case "NhanVien":
                    none = new QuanLyNhanVien().getJpnQL_NhanVien();
                    break;
                case "HoaDon":
                    none = new QuanLyHoaDon().getJpnQL_HoaDon();
                    break;
                case "TaiKhoan":
                    none = new QuanLyTaiKhoan().getJpnQL_TaiKhoan();
                    break;
                case "ThongKe":
                    none = new ThongKeDoanhThu().getJpnThongKe();
                    break;
                default:
                    none = new TrangChu().getJpnTrangChu();
                    break;
            }
            root.removeAll();
            root.setLayout(new BorderLayout());
            root.add(none);
            root.validate();
            root.repaint();
            setChangeBackground(kind);
        }

        @Override
        public void mousePressed(MouseEvent e) {
            kindSelected = kind;
            jpnItem.setBackground(new Color(241,194,50));
            jlbItem.setBackground(new Color(241,194,50));
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {
            jpnItem.setBackground(new Color(241,194,50));
            jlbItem.setBackground(new Color(241,194,50));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(!kindSelected.equalsIgnoreCase(kind)){
                jpnItem.setBackground(new Color(253,216,102));
                jlbItem.setBackground(new Color(253,216,102));
            }
        }

        private void setChangeBackground(String kind){
            for(DanhMuc item : list){
                if(item.getKind().equalsIgnoreCase(kind)){
                    item.getJlb().setBackground(new Color(241,194,50));
                    item.getJpn().setBackground(new Color(241,194,50));
                }else {
                    item.getJlb().setBackground(new Color(253,216,102));
                    item.getJpn().setBackground(new Color(253,216,102));
                }
            }
        }
    }
}
