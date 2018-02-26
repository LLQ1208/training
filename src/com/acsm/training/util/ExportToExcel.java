package com.acsm.training.util;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Excel导出工具
 * @author liuxiaobing
 *
 */
public class ExportToExcel {

	
	/**
	 * 将JavaBean转换成Excel
	 * @param headers
	 * @param list
	 * @param outPathAndName
	 */
	public <T> void exportObjectToExcel(String[] headers , List<T> list,ByteArrayOutputStream bout,String fileName) {
		   WriteExcelUtil<T> ex = new WriteExcelUtil<T>();
		      List<T> dataset = new ArrayList<T>();
		      for(T t : list){
		    	  dataset.add(t);
		      }
			try {
				ex.exportExcel(headers, dataset, bout, fileName);
		        System.out.println("excel导出成功！");
			} catch (Exception e) {
				e.printStackTrace();
			} 
	}
}
