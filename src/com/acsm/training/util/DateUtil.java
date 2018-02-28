package com.acsm.training.util;

import javax.print.DocFlavor;
import java.text.Format;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 时间操作工具，可扩展
 * 
 * @author yuchao
 *
 */
public class DateUtil {
	public final static String YYYY = "yyyy";
	public final static String MM = "MM";
	public final static String DD = "dd";
	public final static String YYYY_MM_DD = "yyyy-MM-dd";
	public final static String YYYY_MM = "yyyy-MM";
	public final static String MM_DD = "MM-dd";
	public final static String HH_MM_SS = "HH:mm:ss";
	public final static String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
	public final static String YYYY_MM_DD_HH_MM = "yyyy-MM-dd HH:mm";

	/**
	 * Conver date to string
	 * 
	 * @param date
	 * @param pattern
	 * @return
	 */
	public static String format(Date date, String pattern) {
		if (date == null)
			return "";
		return new SimpleDateFormat(pattern).format(date);
	}

	/**
	 * Convert millseconds to string
	 * 
	 * @param millseconds
	 * @param pattern
	 * @return
	 */
	public static String format(long millseconds, String pattern) {
		Date date = new Date(millseconds);
		return format(date, pattern);
	}

	/**
	 * 获取两个日期之间相差的天数
	 * 
	 * @param date1
	 *            开始时间
	 * @param date2
	 *            结束时间
	 * @return
	 */
	public static int daysBetween(Date date1, Date date2) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date1);
		long time1 = cal.getTimeInMillis();
		cal.setTime(date2);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 遍历一段时间的日期
	 * 
	 * @param startDate
	 *            开始时间
	 * @param endDate
	 *            结束时间
	 * @return
	 * @throws Exception
	 */
	public static List<Date> dateSplit(Date startDate, Date endDate) throws Exception {
		if (!startDate.before(endDate))
			throw new Exception("开始时间应该在结束时间之后");
		Long spi = endDate.getTime() - startDate.getTime();
		Long step = spi / (24 * 60 * 60 * 1000);// 相隔天数

		List<Date> dateList = new ArrayList<Date>();
		dateList.add(endDate);
		for (int i = 1; i <= step; i++) {
			dateList.add(new Date(dateList.get(i - 1).getTime() - (24 * 60 * 60 * 1000)));// 比上一天减一
		}
		return dateList;
	}

	/**
	 * 获取指定日期的下一天
	 * 
	 * @param date
	 *            指定日期
	 * @return
	 */
	public static Date getDate(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, 1);
		Date date1 = new Date(calendar.getTimeInMillis());
		return date1;
	}

	public static Date getDate(Date date, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DAY_OF_MONTH, days);
		Date date1 = new Date(calendar.getTimeInMillis());
		return date1;
	}

	/**
	 * 将MM/DD/YYYY日期格式转换为YYYY-MM-DD日期格式
	 * 
	 * @param d1
	 * @return
	 * @throws java.text.ParseException
	 */
	public static String dateFormatConversion(String d1) {
		SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
		String d2 = null;
		try {
			Date date = format.parse(d1);
			format = new SimpleDateFormat("yyyy-MM-dd");
			d2 = format.format(date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return d2;
	}

	/**
	 * 将一定格式的String转为Date
	 * 
	 * @param dateStr
	 * @param formatStr
	 *            例如： "yyyy-MM-dd"
	 * @return
	 */
	public static Date StringToDate(String dateStr, String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		Date date = null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * 将Date转为String
	 * 
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static String DateToString(Date dateStr, String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		String date = null;
		date = sdf.format(dateStr);
		return date;
	}

	/**
	 * 获取当前日期字符串
	 * 
	 * @param formatStr
	 * @return
	 */
	public static String getCurrentDate(String formatStr) {
		SimpleDateFormat sdf = new SimpleDateFormat(formatStr);
		String date = sdf.format(new Date());
		return date;
	}

	/**
	 * 根据参数获取现在时间向后多少天
	 * 
	 * @param day
	 *            向前多少天
	 * @return 返回短时间格式 yyyy-MM-dd
	 */
	public static Date convertDate(int day) {
		Date today = new Date();// 当前时间
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(today);
		calendarDate.add(Calendar.DATE, day);// 设置为前day天
		Date dateNew = calendarDate.getTime(); // 得到前day天的时间
		Format formatter = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		String startdate_1 = formatter.format(dateNew); // 格式化前一天
		Date startdate = null;
		try {
			startdate = (Date) formatter.parseObject(startdate_1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startdate;
	}

	// 获得系统当前时间
	public static Date getCurrentTime() {
		Calendar cal = Calendar.getInstance();
		return cal.getTime();
	}

	// 获得当前时间N个月前的时间
	@SuppressWarnings("static-access")
	public static Date getOneMonthBeforeCurrentTime(int count) {
		Calendar cal = Calendar.getInstance();
		cal.add(cal.MONTH, -count);
		return cal.getTime();
	}

	/**
	 * 两个日期相差多少天
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getDatePoorDay(Date startDate, Date endDate) {
		long nd = 1000 * 24 * 60 * 60;
		// 获取两个时间的毫秒时间差
		long diff = endDate.getTime() - startDate.getTime();
		long day = diff / nd;
		return day;
	}

	/**
	 * 两个日期相差多少小时
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getDatePoorHour(Date startDate, Date endDate) {
		long nd = 1000 * 24 * 60 * 60;
		long nh = 1000 * 60 * 60;
		// 获取两个时间的毫秒时间差
		long diff = endDate.getTime() - startDate.getTime();
		long hour = diff % nd / nh;
		return hour;
	}

	/**
	 * 两个日期相差多少分钟
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getDatePoorMin(Date startDate, Date endDate) {
		long nm = 1000 * 60;
		// 获取两个时间的毫秒时间差
		long diff = endDate.getTime() - startDate.getTime();
		long min = diff / nm;// 相差多少分钟
		return min;
	}

	/**
	 * 计算两个日期相差多少秒
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static long getDatePoorSec(Date startDate, Date endDate) {
		long ns = 1000;
		long diff = endDate.getTime() - startDate.getTime();
		long sec = diff / ns;
		return sec;
	}

	/**
	 * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
	 * 
	 * @param strDate
	 * @return
	 */
	public static Date strToDateLong(String strDate) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ParsePosition pos = new ParsePosition(0);
		Date strtodate = formatter.parse(strDate, pos);
		return strtodate;
	}

	public static void main(String[] args) {
		// System.out.println(DateUtil.getCurrentDate("yyyy-MM-dd"));
		Date d1 = StringToDate("2018-02-02 11:20:20",YYYY_MM_DD_HH_MM_SS);
		Date d2 = StringToDate("2018-02-02 15:20:20",YYYY_MM_DD_HH_MM_SS);
		System.out.println(daysBetween(d1, d2));
		System.out.println(DateUtil.getNowDateShort(new Date()));
	}

	public static Integer getWeekOfDate(Date dt) {
		Integer[] weekDays = { 7, 1, 2, 3, 4, 5, 6 };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}
	public static String getZHWeekOfDate(Date dt) {
		String[] weekDays = { "日", "一", "二", "三", "四", "五", "六" };
		Calendar cal = Calendar.getInstance();
		cal.setTime(dt);
		int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
		if (w < 0)
			w = 0;
		return weekDays[w];
	}
	// 判断选择的日期是否是今天
	public static boolean isToday(long time) {
		return isThisTime(time, "yyyy-MM-dd");
	}

	// 判断选择的日期是否是本月
	public static boolean isThisMonth(long time) {
		return isThisTime(time, "yyyy-MM");
	}

	private static boolean isThisTime(long time, String pattern) {
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		String param = sdf.format(date);// 参数时间
		String now = sdf.format(new Date());// 当前时间
		if (param.equals(now)) {
			return true;
		}
		return false;
	}

	public static Date getNowDateShort(Date date) {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(date);
		return DateUtil.StringToDate(dateString, DateUtil.YYYY_MM_DD);
	}


	public static Date getEndTime(Date begingTime,int day) {
		Date today = begingTime;// 当前时间
		Calendar calendarDate = Calendar.getInstance();
		calendarDate.setTime(today);
		calendarDate.add(Calendar.DATE, day);// 设置为前day天
		Date dateNew = calendarDate.getTime(); // 得到前day天的时间
		Format formatter = new SimpleDateFormat("yyyy-MM-dd"); // 设置时间格式
		String startdate_1 = formatter.format(dateNew); // 格式化前一天
		Date startdate = null;
		try {
			startdate = (Date) formatter.parseObject(startdate_1);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return startdate;
	}


	public static int getMonthLastDay(int year, int month)
	{
		Calendar a = Calendar.getInstance();
		a.set(Calendar.YEAR, year);
		a.set(Calendar.MONTH, month - 1);
		a.set(Calendar.DATE, 1);//把日期设置为当月第一天
		a.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天
		int maxDate = a.get(Calendar.DATE);
		return maxDate;
	}

	public static boolean isThisWeek(Date testDate,Date nowDate){
		Date monday = WodUtil.getDateWeekMonday(nowDate);
		String today = format(new Date(),"yyyy-MM-dd");
		String time = format(testDate,"yyyy-MM-dd");
		if(today.equals(time)){
			return true;
		}
		if(monday.getTime() < testDate.getTime()){
			return true;
		}
		return false;
	}
	
	public static Date getStartTime(Date date) {  
        Calendar todayStart = Calendar.getInstance();  
        todayStart.setTime(date);
        todayStart.set(Calendar.HOUR_OF_DAY, 0);  
        todayStart.set(Calendar.MINUTE, 0);  
        todayStart.set(Calendar.SECOND, 0);  
        todayStart.set(Calendar.MILLISECOND, 0);  
        return todayStart.getTime();  
    }  
}
