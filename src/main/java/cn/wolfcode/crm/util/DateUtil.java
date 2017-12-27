package cn.wolfcode.crm.util;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    /**
     * 得到某一天的最后一秒钟
     */
    public static Date getEndDate(Date now) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        calendar.add(Calendar.DATE, 1);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE), 0, 0, 0);
        calendar.add(Calendar.SECOND, -1);
        now = calendar.getTime();
        return now;
    }
    //这个更改后的工具方法
    public static Date getDate(Date now,int date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        //自己传时间进来设置 开始时间
        calendar.add(Calendar.DATE, date);
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DATE), 23, 59, 59);
        calendar.add(Calendar.SECOND, +1);
        now = calendar.getTime();
        return now;
    }

    /**
     * 两个时间的间隔秒
     */
    public static int getBetweenTime(Date one, Date other) {
        return (int) (Math.abs(one.getTime() - other.getTime()) / 1000);
    }
}