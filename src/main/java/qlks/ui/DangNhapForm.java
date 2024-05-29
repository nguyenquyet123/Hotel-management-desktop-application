package qlks.ui;

import qlks.DAO.NhanVienDAO;
import qlks.DAO.TaiKhoanDAO;
import qlks.Entity.NhanVien;
import qlks.Entity.TaiKhoan;
import qlks.utils.Auth;
import qlks.utils.DateHelper;
import qlks.utils.DiaLogHelper;

import javax.swing.*;
import javax.swing.event.AncestorListener;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;

public class DangNhapForm extends JDialog{
    private JPanel container;
    private JTextField txtTenDangNhap;
    private JButton dangNhapButton;

    private JLabel lblLogo;

    private JPasswordField txtPass;
    private JPanel tabs1;
    private JButton btnThoat;

    TaiKhoanDAO tkDAO = new TaiKhoanDAO();
    NhanVienDAO nvDAO = new NhanVienDAO();
    private DiaLogHelper DialogHelper;


    public DangNhapForm(JFrame parent){
        super(parent);


        setTitle("Dang Nhap");
        setSize(560,269);
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setUndecorated(true);
        setShape(new RoundRectangle2D.Double(0,0,getWidth(),getHeight(),25,25));
        setModal(true);
        setContentPane(container);
        setLocationRelativeTo(parent);
        dangNhapButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dangNhap();
            }
        });

        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    private void dangNhap(){
        if(!txtTenDangNhap.getText().equals("")){
            TaiKhoan nv = tkDAO.selectByID(txtTenDangNhap.getText());
            if(nv == null){
                DialogHelper.alert(this, "Sai Tên Đăng Nhập");
            }else if(!new String(txtPass.getPassword()).equals(nv.getMatKhau())){
                DialogHelper.alert(this, "Sai Tên mật Khẩu");
            }else{
                Auth.user = nv;
                this.setVisible(false);
            }
        }
    }




    private void createUIComponents() {
        // TODO: place custom component creation code here
        lblLogo = new JLabel(new ImageIcon("src/logos/logo.png"));
    }
}
