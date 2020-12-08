package com.beebank.monitor.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
    private DateUtil() {}

    /**
     * 获取当前时间 yyyy-MM-dd HH:mm:ss 格式（字符串）
     * @return
     */
    public static String getNowTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date nowDate = new Date();
        return sdf.format(nowDate);
    }

    /**
     * 返回年月日 例如：20200924
     * @return
     */
    public static String getDateString() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        return sdf.format(new Date());
    }

    /**
     * 返回年月日 例如：20200924
     * @return
     */
    public static Date getDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return sdf.parse(sdf.format(new Date()));
        } catch (ParseException e) {
            return null;
        }
    }
}
