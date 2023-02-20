package NWTW.Engine.Utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class DateTime {
    /**
     *
     * @param a 日期a
     * @param b 日期b
     * @param unit 時間單位
     * @return 回傳兩日相差多少時間單位
     */
    public static long Between(Date a,Date b,TimeUnit unit){
        long day = Math.abs(a.getTime()-b.getTime());
        return unit.convert(day,TimeUnit.MILLISECONDS);
    }

    /**
     *
     * @param a 日期a
     * @param b 日期b
     * @return 回傳兩天是否相同
     */
    public static boolean isSame(Date a,Date b){
        return a.compareTo(b) == 0;
    }
    public static Calendar toCalendar(Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }
    public static Date toDate(long time){
        return new Date(time);
    }
    public static String toString(Calendar calendar,TimeFormat format){
        SimpleDateFormat format1 = new SimpleDateFormat(format.getFormat());
        return format1.format(calendar.getTime());
    }
    public static String toString(Date date,TimeFormat format){
        SimpleDateFormat format1 = new SimpleDateFormat(format.getFormat());
        return format1.format(date);
    }
}
