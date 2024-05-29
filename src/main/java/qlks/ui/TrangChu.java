package qlks.ui;

import javax.swing.*;

public class TrangChu {
    private JPanel jpnTrangChu;
    private JLabel imageLabel;

    public TrangChu(){
        // Thêm hình ảnh vào Slide Show (giả sử bạn có danh sách các hình ảnh trong một mảng)
        ImageIcon[] images = {new ImageIcon("src/logos/iloveimg-resized/GRAND_PDLK_02.jpg"),
                new ImageIcon("src/logos/iloveimg-resized/Lobby1.jpg"),
                new ImageIcon("src/logos/iloveimg-resized/SUPER-DELUXE2.jpg")};

        // Cài đặt Slide Show bằng cách sử dụng Timer
        int delay = 2000; // Thời gian hiển thị mỗi hình ảnh (2000ms = 2 giây)
        final int[] currentImageIndex = {0};
        Timer timer = new Timer(delay, e -> {
            if (currentImageIndex[0] == images.length) {
                currentImageIndex[0] = 0; // Quay lại hình ảnh đầu tiên sau khi đã xem tất cả
            }
            imageLabel.setIcon(images[currentImageIndex[0]]);
            currentImageIndex[0]++;
        });
        timer.start();
    }

    public JPanel getJpnTrangChu(){
        return jpnTrangChu;
    }
}
