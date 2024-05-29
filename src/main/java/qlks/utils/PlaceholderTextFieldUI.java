package qlks.utils;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

public class PlaceholderTextFieldUI extends javax.swing.plaf.basic.BasicTextFieldUI{
    private String placeholder;

    public PlaceholderTextFieldUI(String placeholder) {
        this.placeholder = placeholder;
    }

    @Override
    protected void paintSafely(Graphics g) {
        super.paintSafely(g);
        JTextComponent comp = getComponent();
        if (comp.getText().isEmpty() && !comp.hasFocus()) {
            Font prevFont = g.getFont();
            g.setColor(UIManager.getColor("textInactiveText"));
            g.drawString(placeholder, 2, 14);
            g.setFont(prevFont);
        }
    }
}
