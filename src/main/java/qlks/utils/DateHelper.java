package qlks.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateHelper {
    static SimpleDateFormat formater = new SimpleDateFormat("yyyy-MM-dd");

    public static Date toDate(String date, String... pattern) {
        try {
            if (pattern.length > 0) {
                formater.applyPattern(pattern[0]);
            }
            if (date == null) {
                return DateHelper.now();
            }
            return formater.parse(date);
        } catch (ParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String toString(Date date, String... pattern) {
        if (pattern.length > 0) {
            formater.applyPattern(pattern[0]);
        }
        if (date == null) {
            date = DateHelper.now();
        }
        return formater.format(date);
    }

//    public static String formatToString(Date date, String... pattern) {
//        SimpleDateFormat formatter = new SimpleDateFormat();
//        if (pattern.length > 0) {
//            formatter.applyPattern(pattern[0]);
//        } else {
//            formatter.applyPattern("yyyy-MM-dd HH:mm"); // Mẫu mặc định nếu không có mẫu nào được cung cấp
//        }
//        if (date == null) {
//            date = new Date(); // Sử dụng ngày giờ hiện tại nếu ngày truyền vào là null
//        }
//        return formatter.format(date);
//    }

    /**
     * Bổ sung số ngày vào thời gian hiện hành
     *
     * @param days số ngày cần bổ sung vào thời gian hiện tại
     * @return Date kết quả
     */
    public static Date add(int days) {
        Date now = DateHelper.now();
        now.setTime(now.getTime() + days * 24 * 60 * 60 * 1000);
        return now;
    }

    /**
     * Bổ sung số ngày vào thời gian
     *
     * @param date thời gian hiện có
     * @param days số ngày cần bổ sung váo date
     * @return Date kết quả
     */
    public static Date addDate(Date date, long days) {
        date.setTime(date.getTime() + days * 24 * 60 * 60 * 1000);
        return date;
    }

    public static Date now() {
        return new Date();
    }
}
