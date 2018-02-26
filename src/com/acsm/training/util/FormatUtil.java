package com.acsm.training.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatUtil {

	/**
	 * 将一定格式的String转为Date
	 * @param dateStr
	 * @param formatStr 例如： "yyyy-MM-dd"
	 * @return
	 */
	public static Date StringToDate(String dateStr,String formatStr){
		DateFormat sdf=new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return date;
	}
	
	/**
	 * 将一定格式的字符串转化为Calendar
	 * @param dateStr
	 * @param formatStr  例如： "yyyy-MM-dd"
	 * @return
	 */
	public static Calendar StringToCalendar(String dateStr,String formatStr){
		DateFormat sdf=new SimpleDateFormat(formatStr);
		Date date=null;
		try {
			date = sdf.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return c;
	}
	
	/**
	 * 将Date转为String
	 * @param dateStr
	 * @param formatStr
	 * @return
	 */
	public static String DateToString(Date dateStr,String formatStr){
		DateFormat sdf=new SimpleDateFormat(formatStr);
		String date=null;
		date = sdf.format(dateStr);
		return date;
	}
	
	
}
