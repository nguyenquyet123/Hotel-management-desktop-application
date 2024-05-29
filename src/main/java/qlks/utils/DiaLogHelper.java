package qlks.utils;

import javax.swing.*;
import java.awt.*;

public class DiaLogHelper {
    public static void alert(Component parent, String mess){
        JOptionPane.showMessageDialog(parent, mess, "He Quan Tri Dao Tao", JOptionPane.INFORMATION_MESSAGE);
    }

    public static boolean confirm(Component parent,String mess){
        int result = JOptionPane.showConfirmDialog(parent, mess,"He Quan Tri Dao Tao" , JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return result == JOptionPane.YES_OPTION;
    }

    public static String prompt(Component parent,String mess){
        return JOptionPane.showInputDialog(parent, mess, "He Quan Tri Dao Tao", JOptionPane.INFORMATION_MESSAGE);
    }
}
