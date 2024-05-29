package qlks.ui;

import qlks.DAO.NhanVienDAO;
import qlks.DAO.TaiKhoanDAO;
import qlks.Entity.NhanVien;
import qlks.Entity.TaiKhoan;
import qlks.utils.DateHelper;
import qlks.utils.DiaLogHelper;
import qlks.utils.ValidHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TaoTaiKhoan extends JDialog {
    private JPanel tabs2;
    private JTextField txtMaNV;
    private JTextField txtHoTen;
    private JTextField txtSoDTH;
    private JTextField txtEmail;
    private JTextField txtNgaySinh;
    private JTextField txtDangNhap_DK;
    private JTextField txtPass_DK;
    private JButton dangKiButton;
    private JRadioButton rdoNam;
    private JRadioButton rdoNu;
    private JRadioButton rdoTruongPhong;
    private JRadioButton rdoNhanVien;
    private JButton taoMaButton;

    TaiKhoanDAO tkDAO = new TaiKhoanDAO();
    NhanVienDAO nvDAO = new NhanVienDAO();
    private DiaLogHelper DialogHelper;

    public TaoTaiKhoan(JFrame parent){
        super(parent);
        initComponents();

        setTitle("Tao Tai Khoan");
        setSize(465,485);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setModal(true);
        setContentPane(tabs2);
        setLocationRelativeTo(parent);

        dangKiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(valid()){
                    dangKy();
                }
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
        if(txtMaNV.getText().equals("")){
            DiaLogHelper.alert(this,"Hay Tạo Mã");
            return false;
        }
        if(ValidHelper.checkString(txtHoTen)){
            DiaLogHelper.alert(this,"Hay Nhap ho ten va Chỉ chứa alphabet");
            return false;
        }
        if(ValidHelper.check(txtDangNhap_DK)){
            DiaLogHelper.alert(this,"Hay Nhap Ten Dăng Nhập");
            return false;
        }
        if(ValidHelper.checkEmail(txtEmail)){
            DiaLogHelper.alert(this,"Email Sai định dạng");
            return false;
        }

        try {
            DateHelper.toDate(txtNgaySinh.getText());
        }catch (Exception e){
            DialogHelper.alert(this, "Ngay Sinh Chua dung dinh dang(yyyy-MM-dd)");
            return false;
        }

        if(ValidHelper.checkSDT(txtSoDTH)){
            DiaLogHelper.alert(this,"Số điện thoại không phù hợp (11 chữ số)");
            return false;
        }
        if (!rdoNam.isSelected() && !rdoNu.isSelected()) {
            DiaLogHelper.alert(this, "Hay Chon Gioi Tinh");
            return false;
        }
        if (!rdoTruongPhong.isSelected() && !rdoNhanVien.isSelected()) {
            DiaLogHelper.alert(this, "Hay Chon Vai Tro");
            return false;
        }
        if(txtPass_DK.getText().length() >= 6){
            DiaLogHelper.alert(this,"Ma Khau phải > 6 kí tự");
            return false;
        }
        return true;
    }

    private void taoMa(){
        String maHD = nvDAO.selectMaNV().getMaNV();
        int soTT = Integer.parseInt(maHD.substring(maHD.length()-2))+1;
        txtMaNV.setText("NV"+String.valueOf(soTT));
    }

    private void dangKy(){
        NhanVien nv = getForm();
        TaiKhoan tk = getFormTK();
        if (tkDAO.selectByID(txtDangNhap_DK.getText()) != null) {
            DialogHelper.alert(this, "Tên Đăng Nhập Da Ton Tai!!");
        } else {
            try {
                nv.toString();
                nvDAO.add(nv);
                tkDAO.add(tk);

                this.clearForm();
                DialogHelper.alert(this, "Them moi Thanh Cong.");
            } catch (Exception e) {
                System.out.println("" + e.getMessage());
            }
        }
    }

    private void clearForm(){
        txtMaNV.setText("");
        txtHoTen.setText("");
        txtSoDTH.setText("");
        txtEmail.setText("");
        txtNgaySinh.setText("");
        txtDangNhap_DK.setText("");
        txtPass_DK.setText("");
    }

    private NhanVien getForm(){
        NhanVien nv = new NhanVien();
        nv.setMaNV(txtMaNV.getText());
        nv.setHoTenNV(txtHoTen.getText());
        nv.setSoDthNV(txtSoDTH.getText());
        nv.setEmail(txtEmail.getText());
        nv.setNgaySinh(DateHelper.toDate(txtNgaySinh.getText()));
        nv.setGioiTinh(rdoNam.isSelected());
        nv.setVaiTro(rdoTruongPhong.isSelected());
        return nv;
    }

    private TaiKhoan getFormTK(){
        TaiKhoan tk = new TaiKhoan();
        tk.setMaTK(txtDangNhap_DK.getText());
        tk.setMatKhau(txtPass_DK.getText());
        tk.setMaNV(txtMaNV.getText());
        return tk;
    }

    public void initComponents(){
        ButtonGroup btn1 = new ButtonGroup();
        btn1.add(rdoNam);
        btn1.add(rdoNu);

        ButtonGroup btn2 = new ButtonGroup();
        btn2.add(rdoTruongPhong);
        btn2.add(rdoNhanVien);
    }
}
