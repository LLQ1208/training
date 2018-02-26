package com.acsm.training.util;/**
 * Created by lq on 2017/11/30.
 */

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @Author lianglinqiang
 * @create 2017-11-30
 */
public class WodUtil {

    public static Map<Integer,String> wodUnitMap=new HashMap<Integer,String>(){
        {
            put(1,"kg");
            put(2,"lb");
            put(3,"mile");
            put(4,"meters");
            put(5,"km");
            put(6,"yards");
            put(7,"feet");
            put(8,"inches");
            put(9,"cm");
        }
    };

    /**
     * 用于给日历插件颜色
     */
    public static Map<Integer,String> weekTitleMap=new HashMap<Integer,String>(){
        {
            put(1,"mon");
            put(2,"tue");
            put(3,"wed");
            put(4,"thu");
            put(5,"fri");
            put(6,"sat");
            put(7,"sun");
        }
    };
    /**
     * 常规content类型
     */
    public static Map<Integer,String> generalContentTypeMap=new HashMap<Integer,String>(){
        {
            put(1,"Warm-Ups");
            put(2,"Gymnastics");
            put(3,"Weightlifting");
            put(4,"Metcons");
            put(5,"amous Wod");
        }
    };
    /**
     * 自定义content类型
     */
    public static Map<Integer,String> customizeContentTypeMap=new HashMap<Integer,String>(){
        {
            put(6,"Warm-Ups");
            put(7,"Gymnastics");
            put(8,"Weightlifting");
            put(9,"Metcons");
        }
    };
    /**
     * 获取某个时间的周一
     * @param date
     * @return
     */
    public static Date getDateWeekMonday(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 获得当前日期是一个星期的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        // 设置一个星期的第一天，按中国的习惯一个星期的第一天是星期一
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        // 获得当前日期是一个星期的第几天
        int day = cal.get(Calendar.DAY_OF_WEEK);
        // 根据日历的规则，给当前日期减去星期几与一个星期第一天的差值
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - day);
        return cal.getTime();
    }

    /**
     * 获取某个时间numWeek周后的周天
     * @param date
     * @param numWeek
     * @return
     */
    public static Date getLastDate(Date date,int numWeek){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, numWeek*7-1);
        return cal.getTime();
    }

    public static Date getLastWeekBeginDate(Date thisMondyDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(thisMondyDate);
        cal.add(Calendar.DAY_OF_MONTH,-7);
        return cal.getTime();
    }

    /**
     * 根据当前时间（某个周一）获取下个周一的时间
     * @param thisMondyDate
     * @return
     */
    public static Date getNextWeekBeginDate(Date thisMondyDate){
        Calendar cal = Calendar.getInstance();
        cal.setTime(thisMondyDate);
        cal.add(Calendar.DAY_OF_MONTH,7);
        return cal.getTime();
    }


    public static String queryRecordName(Integer recordTypeId){
        String recordName = "";
        switch (recordTypeId){
            case 1 : recordName = "Time"; break;
            case 2 : recordName = "AMRAP-Rounds"; break;
            case 3 : recordName = "AMRAP-Reps"; break;
            case 4 : recordName = "AMRAP-Rounds and Reps"; break;
            case 5 : recordName = "each round"; break;
            case 6 : recordName = "distance"; break;
            case 7 : recordName = "Calories"; break;
            case 8 : recordName = "No Measure"; break;
            case 9 : recordName = "Max Reps"; break;
            case 10 : recordName = "weight"; break;
        }
        return  recordName;
    }
    //当前月的第一天
    public static String getThisMonthBeginDay(Date nowMonth){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowMonth);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return sf.format(calendar.getTime());

    }

    //当前月的最后一天
    public static String getThisMonthEndDay(Date nowMonth){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowMonth);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return sf.format(calendar.getTime());
    }


    //获取上个月的第一天
    public static String getBeforeFirstMonthDay(Date monthDay)throws Exception{
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(monthDay);
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(calendar.getTime());
    }
    //获取下个月的第一天
    public static String getBeforeLastMonthDay(Date monthDay)throws Exception{
        SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar=Calendar.getInstance();
        calendar.setTime(monthDay);
        calendar.add(Calendar.MONTH, +1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return format.format(calendar.getTime());
    }

    //当前季度的开始时间
    public static String getCurrentQuarterStartTime(Date nowDate) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        calendar.setTime(nowDate);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3)
                calendar.set(Calendar.MONTH, 0);
            else if (currentMonth >= 4 && currentMonth <= 6)
                calendar.set(Calendar.MONTH, 3);
            else if (currentMonth >= 7 && currentMonth <= 9)
                calendar.set(Calendar.MONTH, 6);
            else if (currentMonth >= 10 && currentMonth <= 12)
                calendar.set(Calendar.MONTH, 9);
            calendar.set(Calendar.DATE, 1);
//            now = format.parse(format.format(calendar.getTime()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sf.format(calendar.getTime());
    }

    /**
     * 当前季度的结束时间
     *
     * @return
     */
    public static String getCurrentQuarterEndTime(Date nowDate) {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        calendar.setTime(nowDate);
        int currentMonth = calendar.get(Calendar.MONTH) + 1;
//        Date now = null;
        try {
            if (currentMonth >= 1 && currentMonth <= 3) {
                calendar.set(Calendar.MONTH, 2);
                calendar.set(Calendar.DATE, 31);
            } else if (currentMonth >= 4 && currentMonth <= 6) {
                calendar.set(Calendar.MONTH, 5);
                calendar.set(Calendar.DATE, 30);
            } else if (currentMonth >= 7 && currentMonth <= 9) {
                calendar.set(Calendar.MONTH, 8);
                calendar.set(Calendar.DATE, 30);
            } else if (currentMonth >= 10 && currentMonth <= 12) {
                calendar.set(Calendar.MONTH, 11);
                calendar.set(Calendar.DATE, 31);
            }
//            now = format.parse(format.format(calendar.getTime()) + " 23:59:59");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sf.format(calendar.getTime());
    }

    //上一个季度开始时间
    public static String getBeforeCurrentQuarterStartTime(Date nowDate){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.set(Calendar.MONTH, ((int) calendar.get(Calendar.MONTH) / 3 - 1) * 3);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return sf.format(calendar.getTime());
    }
    //下一个季度开始时间
    public static String getLastCurrentQuarterStartTime(Date nowDate){
        SimpleDateFormat sf=new SimpleDateFormat("yyyy-MM-dd");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(nowDate);
        calendar.set(Calendar.MONTH, ((int) calendar.get(Calendar.MONTH) / 3 + 1) * 3);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return sf.format(calendar.getTime());
    }

    public static List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);
            /* Calendar tempEnd = Calendar.getInstance();
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            tempEnd.setTime(end);
            while (tempStart.before(tempEnd)) {
                result.add(tempStart.getTime());
                tempStart.add(Calendar.DAY_OF_YEAR, 1);
            }*/
        while(start.getTime()<=end.getTime()){
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
            start = tempStart.getTime();
        }
        return result;
    }

    //保留两位小数
    public static String getDecimalTwo(int first, int sectond){
        Double s = new Double(sectond);
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_UP);
        return df.format(first/s);
    }
}
