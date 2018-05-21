package top.oahnus.util;

import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.*;
import java.util.*;

/**
 * Created by Administrator on 2017/5/24.
 */
public class DateUtils {

    private static SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMdd");//小写的mm表示的是分钟
    public static String dateString = "yyyy-MM-dd HH:mm:ss";//小写的mm表示的是分钟
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
    private static SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟
    private static SimpleDateFormat sdf4 = new SimpleDateFormat("HH:mm:ss");
    private static SimpleDateFormat sdf5 = new SimpleDateFormat("yyyy-MM");
    private static SimpleDateFormat sdf6 = new SimpleDateFormat("yyyy年MM月dd日");
    private static SimpleDateFormat sdf7 = new SimpleDateFormat("yyyyMM");
    private static SimpleDateFormat sdf8 = new SimpleDateFormat("yyMM");
    private static String[] weeks = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};

    private static final Set<DayOfWeek> weekends = EnumSet.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
    private static final ZoneId defaultZoneId = ZoneId.systemDefault();

    private final static Map<String, String> weekAndWeek;

    static {
        weekAndWeek = new HashMap<>();
        weekAndWeek.put("SUNDAY", "星期日");
        weekAndWeek.put("MONDAY", "星期一");
        weekAndWeek.put("TUESDAY", "星期二");
        weekAndWeek.put("WEDNESDAY", "星期三");
        weekAndWeek.put("THURSDAY", "星期四");
        weekAndWeek.put("FRIDAY", "星期五");
        weekAndWeek.put("SATURDAY", "星期六");
    }


    public static Date nextDayStart() {
        Calendar cal = Calendar.getInstance();
        cal.setTime(new Date());
        cal.add(Calendar.DATE, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    public static String date2String(Date date, SimpleDateFormat sdf) {
        try {
            return sdf.format(date);
        } catch (Exception e) {
            return "";
        }
    }

    public static String date2String(Date date) {
        return date2String(date, sdf1);
    }

    public static String date2String2(Date date) {
        return date2String(date, sdf2);
    }

    public static String date2String3(Date date) {
        return date2String(date, sdf3);
    }


    public static Date string2Date(String str, SimpleDateFormat sdf) {
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (Exception e) {
            return null;
        }
        return date;
    }

    public static Date localDate2date(LocalDate localDate) {
        return java.sql.Timestamp.valueOf(localDate.atStartOfDay());
    }

    public static Date string2Date4(String time) {
        return string2Date(time, sdf4);
    }

    public static Date string2Date5(String time) {
        return string2Date(time, sdf5);
    }

    public static Date string2Date7(String time) {
        return string2Date(time, sdf7);
    }


    public static String date2String5(Date date) {
        return date2String(date, sdf5);
    }

    public static String date2String6(Date date) {
        return date2String(date, sdf6);
    }

    public static String date2String7(Date date) {
        return date2String(date, sdf7);
    }

    public static String date2String8(Date date) {
        return date2String(date, sdf8);
    }


    public static Date getDate() {
        return new Date();
    }

    public static String getTime() {
        return sdf4.format(new Date());
    }

    public static String getDateTime() {
        Time sTime = new Time((new Date()).getTime());
        return sdf4.format(sTime);
    }

    public static String getDateTime(Date date) {
        Time sTime = new Time(date.getTime());
        return sdf4.format(sTime);
    }

    public static String getTime4(Time time) {
        return sdf4.format(time);
    }


    public static Integer curYear() {
        return YearMonth.now().getYear();
    }

    public static List<LocalDate> curMonthRange() {
        YearMonth now = YearMonth.now();
        return monthRange(now);
    }

    public static List<LocalDate> monthRange(LocalDate yearMonth) {
        YearMonth month = YearMonth.of(yearMonth.getYear(), yearMonth.getMonth());
        return monthRange(month);
    }

    /**
     * ex:
     * 2017-9-1
     *
     * @return
     */
    private static List<LocalDate> monthRange(YearMonth yearMonth) {
        LocalDate firstDay = yearMonth.atDay(1);
        LocalDate lastDay = firstDay.plusMonths(1).minusDays(1);
        return Arrays.asList(firstDay, lastDay);
    }

    public static Integer curMonth() {
        return YearMonth.now().getMonthValue();
    }


    public static Boolean isWeekend(Date date) {
        return isWeekend(date2localDate(date));
    }

    public static Boolean isWorkDay(Date date) {
        return isWorkDay(date2localDate(date));
    }

    public static Boolean isWeekend(LocalDate date) {
        return weekends.contains(date.getDayOfWeek());
    }

    public static Boolean isWorkDay(LocalDate date) {
        return !isWeekend(date);
    }


    public static LocalDate date2localDate(Date date) {
        return date.toInstant().atZone(defaultZoneId).toLocalDate();
    }

    /**
     * 获取星期
     *
     * @param date
     * @return
     */
    public static String getWeek(Date date) {
        int week_index = date2localDate(date).getDayOfWeek().getValue();
        return weeks[week_index];
    }


    /**
     * java.time.LocalDate --> java.util.Date
     *
     * @param localDate
     * @return
     */
    public static Date LocalDateTimeToUpdate(LocalDate localDate) {
        ZoneId zone = ZoneId.systemDefault();
        Instant instant = localDate.atStartOfDay().atZone(zone).toInstant();
        return Date.from(instant);
    }

    /**
     * 获取日历
     *
     * @param date
     * @return
     */
    public static Map<Date, String> getCalendar(Date date) {
        //date -> localDate
        LocalDate localDate = date2localDate(date);

        //获取当月
        int month = localDate.getMonthValue();
        //获取当前是当月的第几天
        int today = localDate.getDayOfMonth();

        //将日期设置为当月的第一天
        localDate = localDate.minusDays(today - 1);
        //本月的日期信息Map<"2017-09-01", "SATURDAY">
        Map<Date, String> map = new HashMap<>();
        while (localDate.getMonthValue() == month) {
            Date date2 = LocalDateTimeToUpdate(localDate);
            map.put(date2, localDate.getDayOfWeek().toString());
            localDate = localDate.plusDays(1);
        }
        return map;
    }

    /**
     * 返回中文星期
     *
     * @param week
     * @return
     */
    public static String getStringWeek(String week) {
        return weekAndWeek.get(week);
    }

    /**
     * 获取两个时间相差分钟
     *
     * @param date1
     * @param date2
     * @return
     */
    public static Integer minutesBetween(Date date1, Date date2) {
//        long between = ChronoUnit.MINUTES.between(date2localDate(date1), date2localDate(date2));
//        Integer.parseInt(String.valueOf(between));
        Time endTime = new Time(date1.getTime());
        Time nowTime = new Time(date2.getTime());
        // 获得两个时间的毫秒时间差异
        int diff = 0;
        diff = new Long(nowTime.getTime() - endTime.getTime()).intValue();
        // 计算差多少分钟
//        return Math.abs(diff % (1000 * 24 * 60 * 60) % (1000 * 60 * 60) / (1000 * 60));
        return Math.abs(diff / (1000 * 60));
    }
    public static LocalDateTime date2localDateTime(Date date) {
        return date.toInstant().atZone(defaultZoneId).toLocalDateTime();
    }

    public static boolean isBetween(Date date, Date start, Date end) {
        return date.getTime() > start.getTime() && date.getTime() < end.getTime();
    }

    public static void main(String[] args) {
//        System.out.println(DateUtils.curMonth());
//        System.out.println(DateUtils.curYear());
//        Date date = DateUtils.string2Date5("2017-10");
//        Map<Date, String> map = DateUtils.getCalendar(date);
//
//        System.out.println(DateUtils.nextDayStart());

        Date now = DateUtils.localDate2date(LocalDate.now());

        System.out.println(now);
    }
}
