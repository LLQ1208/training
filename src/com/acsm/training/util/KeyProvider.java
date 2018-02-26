package com.acsm.training.util;

import java.util.UUID;

public class KeyProvider {

	public static String getPrimaryKey(){
		UUID u=UUID.randomUUID();
		return u.toString();
	}
	
	public static void main(String args[]){
		System.out.println(KeyProvider.getPrimaryKey());
	}
	
	
}
