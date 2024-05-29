package qlks.utils;

import javax.swing.*;
import java.awt.*;

public class ValidHelper {
    public static boolean check(JTextField txt) {
        if (txt.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, "Không được để Trống");
            txt.setBackground(Color.GRAY);
            txt.requestFocus();
            return true;
        }
        txt.setBackground(Color.white);
        return false;
    }

    public static boolean check(JTextArea txt) {
        if (txt.getText().equals("")) {
//            JOptionPane.showMessageDialog(this, "Không được để Trống");
            txt.setBackground(Color.GRAY);
            txt.requestFocus();
            return true;
        }
        txt.setBackground(Color.white);
        return false;
    }

    public static boolean checkDouble(JTextField txt) {
        try {
            double n = Double.parseDouble(txt.getText());

            if (n < 0 ) {
//                JOptionPane.showMessageDialog(this, "So Luong phải Lớn hơn 0");
                txt.setBackground(Color.GRAY);
                txt.requestFocus();
                return true;
            }
        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Phải Nhập số");
            txt.setBackground(Color.GRAY);
            txt.requestFocus();
            return true;
        }
        txt.setBackground(Color.WHITE);
        return false;
    }

    public static boolean checkInt(JTextField txt) {
        try {
            double n = Integer.parseInt(txt.getText());

            if (n < 0 ) {
//                JOptionPane.showMessageDialog(this, "So Luong phải Lớn hơn 0");
                txt.setBackground(Color.GRAY);
                txt.requestFocus();
                return true;
            }
        } catch (NumberFormatException e) {
//            JOptionPane.showMessageDialog(this, "Phải Nhập số");
            txt.setBackground(Color.GRAY);
            txt.requestFocus();
            return true;
        }
        txt.setBackground(Color.WHITE);
        return false;
    }

    public static boolean checkEmail(JTextField txt) {
        if (!txt.getText().matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$")) {
            txt.requestFocus();
            return true;
        }
        return false;
    }

    public static boolean checkSDT(JTextField txt) {
        if (!txt.getText().matches("^\\d{7,11}$")) {
            txt.requestFocus();
            return true;
        }
        return false;
    }

    public static boolean checkString(JTextField txt) {
        try {
            String s = String.valueOf(txt.getText());
            if (s.length() < 5) {
                txt.setBackground(Color.GRAY);
                txt.requestFocus();
                return true;
            }
        } catch (Exception e) {
            txt.setBackground(Color.GRAY);
            txt.requestFocus();
            return true;
        }
        txt.setBackground(Color.WHITE);
        return false;
    }

}
