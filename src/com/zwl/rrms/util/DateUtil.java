package com.zwl.rrms.util;

import java.text.SimpleDateFormat;

public class DateUtil {
    public static String date2str(Long planTime) {
        String format = "yyyy年MM月dd日";
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(planTime);
    }
}
