package qlks;

import qlks.ui.DangNhapForm;
import qlks.ui.MainForm;
import qlks.utils.Auth;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                openDangNhap();
                createGUI();
            }
        });
    }

    private static void createGUI(){

        MainForm ui = new MainForm();
        JPanel root = ui.getRootPane();
        JFrame frame = new JFrame();

        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setContentPane(root);
        frame.setTitle("Quan Ly Khach San");
        frame.setLocation(350,200);

        if(Auth.isLogin()){
            System.out.println(""+Auth.user.toString());
            frame.setVisible(true);
        }else {
            frame.dispose();
            System.exit(0);
        }
        frame.pack();
    }
    public static void openDangNhap(){
        new DangNhapForm(null).setVisible(true);
    }
}
