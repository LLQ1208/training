package com.acsm.training.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Description: MD5加密用户密码
 * @author: liuxiaobing
 * @Version: V1.00
 * @Date: 2014-11-26
 */
public class SecurityUtil {
	//添加用户时对密码MD5加密
	public static String md5(String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes());
		return new BigInteger(1,md.digest()).toString(16);
	}
	
	//在修改用户密码时或者登录时或添加用户时最安全的加密方法
	public static String md5(String username,String password) throws NoSuchAlgorithmException {
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(username.getBytes());
		md.update(password.getBytes());
		return new BigInteger(1,md.digest()).toString(16);
	}
	
	public static void main(String args[]){
		try {
			System.out.println(SecurityUtil.md5("crossfit_pard", "123456"));
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
