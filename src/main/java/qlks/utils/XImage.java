package qlks.utils;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class XImage {
    public static Image getAppIcon(){
        URL url = XImage.class.getResource("src/logos/fpt.png");
        return new ImageIcon(url).getImage();
    }

    public static void save(File src){
        File file = new File("D:\\DuAnMau_PRO1041\\QuanLyKhachSan\\BaoCao", src.getName());
        if(!file.getParentFile().exists()){
            file.getParentFile().mkdirs();//tao thu muc
        }

        try {
            Path from = Paths.get(src.getAbsolutePath());
            Path to = Paths.get(file.getAbsolutePath());
            Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static ImageIcon read(String file){
        File path = new File("src/logos",file);
        return new ImageIcon(path.getAbsolutePath());
    }
}
