package com.acsm.training.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

public class StringUtils {
	/**
	 * 判断字符串是否为空
	 * @param s
	 * @return
	 */
	public static boolean isNotEmpty(String s){
		if(s!=null && !s.equals("")){
			return true;
		}else{
			return false;
		}
	}
	/**
	 * 获取当前时间
	 * @return
	 */
	public static String getNowTime(){
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}
	/**
	 * 获取当前日期
	 * @return
	 */
	public static String getNowDay(){
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(d);
	}
	/**
	 * 获取IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {   
	    if (request.getHeader("x-forwarded-for") == null) {
	    	return request.getRemoteAddr();   
	    }   
	    return request.getHeader("x-forwarded-for");   
	}
	
	public static String getYMCode(){
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMM");
		return sdf.format(d);
	}
	
	public static String getYM(){
		Date d=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM");
		return sdf.format(d);
	}
	
	public static String createCode(int total){
		String code = "-000";
		if(total <= 9){
			code = "-00"+total;
		}else if(total > 9 && total <= 99){
			code = "-0"+total;
		}else if(total > 99 && total <= 999){
			code = "-"+total;
		}
		return code;
	}
	
	public static String getMatcher(String regex, String sourceStr) {  
        String result = "";  
        Pattern pattern = Pattern.compile(regex);  
        Matcher matcher = pattern.matcher(sourceStr);  
        while (matcher.find()) {  
        	result = matcher.group(1);
        }  
        return result;  
    }  
	
	/**
	 * 根据身份证号计算年龄
	 * @param IdNO
	 * @return
	 */
	public static int IdNOToAge(String IdNO){
        int leh = IdNO.length();
        String dates="";
        if (leh == 18) {
            dates = IdNO.substring(6, 10);
            SimpleDateFormat df = new SimpleDateFormat("yyyy");
            String year=df.format(new Date());
            int u=Integer.parseInt(year)-Integer.parseInt(dates);
            return u;
        }else{
            dates = IdNO.substring(6, 8);
            return Integer.parseInt(dates);
        }
    }

    public static int birthdayToAge(Date birthDay){
		Calendar cal = Calendar.getInstance();
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH);
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(birthDay);
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH);
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
		int age = yearNow - yearBirth;
		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				if (dayOfMonthNow < dayOfMonthBirth) age--;
			}else{
				age--;
			}
		}
		return age;
	}
	
	public static String getNewDateString(){
		return FormatUtil.DateToString(new Date(), "yyyyMMddHHmmss");
	}
	
	public static void main(String[] args) {
		System.out.println(StringUtils.IdNOToAge("522122199110096815"));
	}
	
}
