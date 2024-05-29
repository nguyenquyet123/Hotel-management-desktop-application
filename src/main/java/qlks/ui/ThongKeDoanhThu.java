package qlks.ui;

import net.sf.dynamicreports.jasper.builder.JasperReportBuilder;
import net.sf.dynamicreports.report.builder.DynamicReports;
import net.sf.dynamicreports.report.builder.chart.Bar3DChartBuilder;
import net.sf.dynamicreports.report.builder.column.TextColumnBuilder;
import net.sf.dynamicreports.report.builder.style.StyleBuilder;
import net.sf.dynamicreports.report.constant.HorizontalTextAlignment;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import static net.sf.dynamicreports.report.builder.DynamicReports.*;


import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import qlks.DAO.HoaDonDAO;
import qlks.DAO.ThongKeDAO;
import qlks.utils.DiaLogHelper;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class ThongKeDoanhThu {
    private JPanel jpnThongKe;
    private JTabbedPane tabbedPane1;
    private JComboBox cboNam;
    private JComboBox cboThang;
    private JTable tblDoanhThu;
    private JLabel lblTongTien;
    private JButton INButton;
    private JPanel jpnChar;
    private JPanel jpnDoanhThu;
    private JButton btnXuatDV;
    private JComboBox cboNamDV;
    private JComboBox cboThangDV;
    private JTable tblDichVu;

    HoaDonDAO hdDao = new HoaDonDAO();
    ThongKeDAO tkDAO = new ThongKeDAO();

    public ThongKeDoanhThu(){
        createTable();
        fillCboNam();
        fillCboNamDV();
        fillTableDoanhThu();
        setChart2023();
        cboNam.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillCboThang();
                setChart2023();
            }
        });
        cboThang.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillTableDoanhThu();
            }
        });
        cboNamDV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillCboThangDV();
            }
        });
        cboThangDV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                fillTableDichVu();
            }
        });

        INButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildDoanhThuPDF();
            }
        });

        btnXuatDV.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buildDichVuPDF();
            }
        });
    }
    public void setChart2023(){
        ChartPanel bieudo = new ChartPanel(createChart());
        bieudo.setPreferredSize(new java.awt.Dimension(560, 367));
        jpnChar.removeAll();
        jpnChar.setLayout(new CardLayout());
        jpnChar.add(bieudo);
        jpnChar.validate();
        jpnChar.repaint();
    }
    public JFreeChart createChart(){
        int nam = (int) cboNam.getSelectedItem();
        JFreeChart barChar = ChartFactory.createBarChart("BIEU DO DOANH THU THANG CUA NAM "+String.valueOf(nam), "THANG", "DOANHTHU",
                createDataset(), PlotOrientation.VERTICAL, false, false, false);
        return barChar;
    }
    public  CategoryDataset createDataset(){
        DefaultCategoryDataset data = new DefaultCategoryDataset();


        for(int i = 0;i<tblDoanhThu.getRowCount();i++){
            int thang = (int) tblDoanhThu.getValueAt(i, 1);
            double dTHu= (double) tblDoanhThu.getValueAt(i,3);
            data.addValue(dTHu,"SN",String.valueOf(thang));
        }
        return data;
    }
    private void buildDoanhThuPDF(){
        StyleBuilder textStyle = stl.style().setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
        StyleBuilder boldStyle         = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
        StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);

        DRDataSource dataSource = new DRDataSource("Nam", "Thang","Ngay","DoanhThu");
        for(int i = 0;i<tblDoanhThu.getRowCount();i++){
            int year = (int) tblDoanhThu.getValueAt(i, 0);
            int month = (int) tblDoanhThu.getValueAt(i, 1);
            int day = (int) tblDoanhThu.getValueAt(i, 2);
            double value = (double) tblDoanhThu.getValueAt(i, 3);

            dataSource.add(String.valueOf(year),String.valueOf(month),String.valueOf(day),value);
        }


        // Tạo cột dữ liệu cho báo cáo
        TextColumnBuilder<String> namColumn = col.column("Nam", "Nam", type.stringType()).setStyle(textStyle).setPattern("#");
        TextColumnBuilder<String> thangColumn = col.column("Thang", "Thang", type.stringType()).setStyle(textStyle);
        TextColumnBuilder<String> ngayColumn = col.column("Ngay", "Ngay", type.stringType()).setStyle(textStyle);
        TextColumnBuilder<Double> dThuColumn = col.column("Doanh Thu", "DoanhThu", type.doubleType()).setStyle(textStyle);

        // Tạo báo cáo
        JasperReportBuilder report = report()
                    .columns(namColumn,thangColumn,ngayColumn,dThuColumn)
                    .title(cmp.text("Bao Cao Doanh Thu"))
                    .setColumnTitleStyle(columnTitleStyle)
                    .highlightDetailEvenRows()
                    .pageFooter(cmp.pageXofY().setStyle(boldCenteredStyle))
                    .subtotalsAtSummary();

        // Kết nối dữ liệu và tạo báo cáo
        report.setDataSource(dataSource);

        Bar3DChartBuilder bar3DChart = DynamicReports.cht.bar3DChart();
        Integer Nam = (Integer) cboNam.getSelectedItem();
        Integer Thang = (Integer) cboThang.getSelectedItem();
                if(Thang == 0){
                    bar3DChart.setTitle("Biểu đồ doanh thu trong Năm "+Nam);
                    bar3DChart.setCategory(thangColumn);
                    bar3DChart.setUseSeriesAsCategory(true);// Sử dụng cột thứ 2 trở đi làm danh mục
                    bar3DChart.addSerie(cht.serie(dThuColumn));
                }else {
                    bar3DChart.setTitle("Biểu đồ doanh thu trong Tháng "+Thang);
                    bar3DChart.setCategory(ngayColumn);
                    bar3DChart.setUseSeriesAsCategory(true);// Sử dụng cột thứ 2 trở đi làm danh mục
                    bar3DChart.addSerie(cht.serie(dThuColumn));
                }

        report.summary(bar3DChart);

        // Xuất báo cáo ra tệp PDF
        try {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(getJpnThongKe()) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String fileName = file.getAbsolutePath(); // Lấy đường dẫn tệp người dùng đã chọn
                if (!fileName.toLowerCase().endsWith(".pdf")) {
                    fileName += ".pdf"; // Đảm bảo rằng tệp có phần mở rộng là .pdf
                }
                report.toPdf(new FileOutputStream(fileName));
                DiaLogHelper.alert(jpnThongKe,"Da Luu");
                System.out.println(fileName);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DRException e) {
            throw new RuntimeException(e);
        }
    }
    private void buildDichVuPDF(){
        StyleBuilder textStyle = stl.style().setHorizontalTextAlignment(HorizontalTextAlignment.LEFT);
        StyleBuilder boldStyle         = stl.style().bold();
        StyleBuilder boldCenteredStyle = stl.style(boldStyle).setHorizontalTextAlignment(HorizontalTextAlignment.CENTER);
        StyleBuilder columnTitleStyle  = stl.style(boldCenteredStyle).setBorder(stl.pen1Point()).setBackgroundColor(Color.LIGHT_GRAY);

        DRDataSource dataSource = new DRDataSource("Nam", "Thang","Ngay","TenDV","DoanhThu");
        for(int i = 0;i<tblDichVu.getRowCount();i++){
            int year = (int) tblDichVu.getValueAt(i, 0);
            int month = (int) tblDichVu.getValueAt(i, 1);
            int day = (int) tblDichVu.getValueAt(i, 2);
            String tenDv = (String) tblDichVu.getValueAt(i,3);
            double value = (double) tblDichVu.getValueAt(i, 4);

            dataSource.add(String.valueOf(year),String.valueOf(month),String.valueOf(day),tenDv,value);
        }


        // Tạo cột dữ liệu cho báo cáo
        TextColumnBuilder<String> namColumn = col.column("Nam", "Nam", type.stringType()).setStyle(textStyle).setPattern("#");
        TextColumnBuilder<String> thangColumn = col.column("Thang", "Thang", type.stringType()).setStyle(textStyle);
        TextColumnBuilder<String> ngayColumn = col.column("Ngay", "Ngay", type.stringType()).setStyle(textStyle);
        TextColumnBuilder<String> tenDVColumn = col.column("Ten Dich Vu", "TenDV", type.stringType()).setStyle(textStyle);
        TextColumnBuilder<Double> dThuColumn = col.column("Doanh Thu", "DoanhThu", type.doubleType()).setStyle(textStyle);

        // Tạo báo cáo
        JasperReportBuilder report = report()
                .columns(namColumn,thangColumn,ngayColumn,tenDVColumn,dThuColumn)
                .title(cmp.text("Bao Cao Doanh Thu"))
                .setColumnTitleStyle(columnTitleStyle)
                .highlightDetailEvenRows()
                .pageFooter(cmp.pageXofY().setStyle(boldCenteredStyle))
                .subtotalsAtSummary();

        // Kết nối dữ liệu và tạo báo cáo
        report.setDataSource(dataSource);

        Bar3DChartBuilder bar3DChart = DynamicReports.cht.bar3DChart();
        Integer Nam = (Integer) cboNamDV.getSelectedItem();
        Integer Thang = (Integer) cboThangDV.getSelectedItem();
        if(Thang == 0){
            bar3DChart.setTitle("Biểu đồ doanh thu trong Năm "+Nam);
            bar3DChart.setCategory(thangColumn);
            bar3DChart.setCategory(tenDVColumn);
            bar3DChart.setUseSeriesAsCategory(true);// Sử dụng cột thứ 2 trở đi làm danh mục
            bar3DChart.addSerie(cht.serie(dThuColumn));
        }else {
            bar3DChart.setTitle("Biểu đồ doanh thu trong Tháng "+Thang);
            bar3DChart.setCategory(ngayColumn);
            bar3DChart.setCategory(tenDVColumn);
            bar3DChart.setUseSeriesAsCategory(true);// Sử dụng cột thứ 2 trở đi làm danh mục
            bar3DChart.addSerie(cht.serie(dThuColumn));
        }

        report.summary(bar3DChart);

        // Xuất báo cáo ra tệp PDF
        try {
            JFileChooser fileChooser = new JFileChooser();
            if (fileChooser.showSaveDialog(getJpnThongKe()) == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
                String fileName = file.getAbsolutePath(); // Lấy đường dẫn tệp người dùng đã chọn
                if (!fileName.toLowerCase().endsWith(".pdf")) {
                    fileName += ".pdf"; // Đảm bảo rằng tệp có phần mở rộng là .pdf
                }
                report.toPdf(new FileOutputStream(fileName));
                DiaLogHelper.alert(jpnThongKe,"Da Luu");
                System.out.println(fileName);
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (DRException e) {
            throw new RuntimeException(e);
        }
    }



    private void tongDoanhThu(){
        double tongTien = 0;
        for (int i = 0;i < tblDoanhThu.getRowCount();i++){
            double tienDV = (double) tblDoanhThu.getValueAt(i,3);
            tongTien += tienDV;
        }
        lblTongTien.setText("Tong : "+tongTien);
    }

    private void fillTableDichVu(){
        DefaultTableModel model = (DefaultTableModel) tblDichVu.getModel();
        model.setRowCount(0);
        Integer nam = (Integer) cboNamDV.getSelectedItem();
        Integer thang = (Integer) cboThangDV.getSelectedItem();
        if (nam != null && thang != null) {
            if(thang.equals(0)){
                List<Object[]> list = tkDAO.getDichVuNam(nam);
                for (Object[] row : list) {
                    model.addRow(row);
                }

            }else {
                List<Object[]> list = tkDAO.getDichVu(nam,thang);
                for (Object[] row : list) {
                    model.addRow(row);
                }
            }
        }
    }

    private void fillTableDoanhThu(){
        DefaultTableModel model = (DefaultTableModel) tblDoanhThu.getModel();
        model.setRowCount(0);
        Integer nam = (Integer) cboNam.getSelectedItem();
        Integer thang = (Integer) cboThang.getSelectedItem();

        if (nam != null && thang != null) {
            if(thang.equals(0)){
                List<Object[]> list = tkDAO.getDoanhThuNam(nam);
                for (Object[] row : list) {
                    model.addRow(row);
                }

            }else {
                List<Object[]> list = tkDAO.getDoanhThu(nam,thang);
                for (Object[] row : list) {
                    model.addRow(row);
                }
            }
        }
        tongDoanhThu();
    }

    private void fillCboNam(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNam.getModel();
        model.removeAllElements();
        List<Integer> list = hdDao.selectYear();
        for (Integer year : list) {
            model.addElement(year);
        }
        fillCboThang();
    }

    private void fillCboThang(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboThang.getModel();
        model.removeAllElements();

        Integer thang = (Integer) cboNam.getSelectedItem();
        if(thang != null){
            List<Integer> list = hdDao.selectMonth(thang);
            model.addElement(0);
            for (Integer year : list) {
                model.addElement(year);
            }
        }
    }
    private void fillCboNamDV(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboNamDV.getModel();
        model.removeAllElements();
        List<Integer> list = hdDao.selectYear();
        for (Integer year : list) {
            model.addElement(year);
        }
        fillCboThangDV();
    }

    private void fillCboThangDV(){
        DefaultComboBoxModel model = (DefaultComboBoxModel) cboThangDV.getModel();
        model.removeAllElements();

        Integer thang = (Integer) cboNamDV.getSelectedItem();
        if(thang != null){
            List<Integer> list = hdDao.selectMonth(thang);
            model.addElement(0);
            for (Integer year : list) {
                model.addElement(year);
            }
        }
    }

    public JPanel getJpnThongKe(){
        return jpnThongKe;
    }
    private void createTable(){
        tblDoanhThu.setModel(new DefaultTableModel(
                null,
                new String[]{"Nam","Thang","Ngay","Doanh Thu"}
        ));
        tblDichVu.setModel(new DefaultTableModel(
                null,
                new String[]{"Nam","Thang","Ngay","TenDV","Tong Tien"}
        ));
    }
}
