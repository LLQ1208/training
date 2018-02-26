package com.acsm.training.util;

public class NumUtil {
	public static double meg(double i){
		int b = (int)Math.round(i * 10); //小数点后两位前移，并四舍五入 
		//System.out.println(b);
		double c = ((double)b/10.0); //还原小数点后两位
		//System.out.println(c);
//		if((c*10)%5!=0){
//			int d = (int)Math.round(c); //小数点前移，并四舍五入 
//			c = ((double)d); //还原小数点
//		}
		return c;
	 }
	
	public static void main(String args[]){
		System.out.println(meg(13.2567));
	}
}
