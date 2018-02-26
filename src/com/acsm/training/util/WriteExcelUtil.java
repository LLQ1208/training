package com.acsm.training.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

public class WriteExcelUtil<T> {
	 
	   public void exportExcel(Collection<T> dataset, ByteArrayOutputStream bout,String fileName) {
	      exportExcel(fileName, null, dataset, bout, "yyyy-MM-dd");
	   }
	 
	   public void exportExcel(String[] headers, Collection<T> dataset,ByteArrayOutputStream bout,String fileName) {
	      exportExcel(fileName, headers, dataset, bout, "yyyy-MM-dd");
	   }
	   
	   public void exportExcel(String[] headers, Collection<T> dataset,
			   ByteArrayOutputStream bout, String pattern,String fileName) {
	      exportExcel(fileName, headers, dataset, bout, pattern);
	   }
	 
	   /**
	    * 这是一个通用的方法，利用了JAVA的反射机制，可以将放置在JAVA集合中并且符号一定条件的数据以EXCEL 的形式输出到指定IO设备上
	    *
	    * @param title
	    *            表格标题名
	    * @param headers
	    *            表格属性列名数组
	    * @param dataset
	    *            需要显示的数据集合,集合中一定要放置符合javabean风格的类的对象。此方法支持的
	    *            javabean属性的数据类型有基本数据类型及String,Date,byte[](图片数据)
	    * @param bout
	    *            与输出设备关联的流对象，可以将EXCEL文档导出到本地文件或者网络中
	    * @param pattern
	    *            如果有时间数据，设定输出格式。默认为"yyy-MM-dd"
	    */
	   @SuppressWarnings("resource")
	public void exportExcel(String title, String[] headers,Collection<T> dataset, ByteArrayOutputStream bout, String pattern) {
	      // 声明一个工作薄
	      HSSFWorkbook workbook = new HSSFWorkbook();
	      workbook.writeProtectWorkbook("venus888888", "venus");//打开加密
	      // 生成一个表格
	      HSSFSheet sheet = workbook.createSheet(title);
	      sheet.protectSheet("venus888888");//设置单元格只读
	      // 设置表格默认列宽度为15个字节
	      sheet.setDefaultColumnWidth(15);
//	      sheet.autoSizeColumn(1, true);//自适应
	      // 生成一个表头样式
	      HSSFCellStyle headStyle = workbook.createCellStyle();
	      // 设置这些样式
//	      headStyle.setLocked(true);//锁定
	      headStyle.setFillForegroundColor(HSSFColor.SKY_BLUE.index);//颜色
	      headStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
	      headStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);//居中显示
	      headStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);//下边框
	      headStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);//左边框
	      headStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);//右边框
	      headStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);//上边框
	      // 生成一个字体
	      HSSFFont headFont = workbook.createFont();
//	      headFont.setColor(HSSFColor.VIOLET.index);//字体颜色
	      headFont.setFontHeightInPoints((short) 12);//字体大小
//	      headFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);//加粗
	      // 把字体应用到当前的样式
	      headStyle.setFont(headFont);
	      //产生表格标题行
	      HSSFRow row = sheet.createRow(0);
	      HSSFCell cell = null;
	      for (int i = 0; i < headers.length; i++) {
	         cell = row.createCell(i);//创建单元格
	         cell.setCellStyle(headStyle);//单元格样式
	         /**
	          * 根据不同列设置列宽
	          */
	         if(headers[i].equals("漏洞描述")||headers[i].equals("漏洞来源")||headers[i].equals("修补建议")||headers[i].equals("参考网址")||headers[i].equals("受影响实体")){
	        	 sheet.setColumnWidth(i, 80*2*256);
	         }else if(headers[i].equals("漏洞名称")){
	        	 sheet.setColumnWidth(i, 40*2*256);
	         }else if(headers[i].equals("CVE编号")||headers[i].equals("CNNVD编号")||headers[i].equals("漏洞类型")
	        		 ||headers[i].equals("危害等级")||headers[i].equals("发布时间")||headers[i].equals("录入时间")
	        		 ||headers[i].equals("威胁类型")){
	        	 sheet.setColumnWidth(i, 20*2*256);
	         }
	         HSSFRichTextString text = new HSSFRichTextString(headers[i]);
		     cell.setCellValue(text);//设置单元格内容
	      }
	      //遍历集合数据，产生数据行
	      Iterator<T> it = dataset.iterator();
	      int index = 0;
	      while (it.hasNext()) {
	         index++;
	         row = sheet.createRow(index);
	         T t = (T) it.next();
	         //利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
	         Field[] fields = t.getClass().getDeclaredFields();
	         for (int i = 0; i < fields.length; i++) {
	            HSSFCell cell2 = row.createCell(i);
	            Field field = fields[i];
	            String fieldName = field.getName();
	            String getMethodName = "get"+ fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
	            try {
	                Class<? extends Object> tCls = t.getClass();
	                Method getMethod = tCls.getMethod(getMethodName,new Class[] {});
	                Object value = getMethod.invoke(t, new Object[] {});
	                //判断值的类型后进行强制类型转换
	                String textValue = null;
	                if (value instanceof Date) {
	                   Date date = (Date) value;
	                   SimpleDateFormat sdf = new SimpleDateFormat(pattern);
	                    textValue = sdf.format(date);
	                } else{
	                   //其它数据类型都当作字符串简单处理
	                	if(value==null){
	                		textValue = "";
	                	}else{
	                		textValue = value.toString();
	                	}
	                }
	                //利用正则表达式判断textValue是否全部由数字组成
	                if(textValue!=null){
	                   Pattern p = Pattern.compile("^//d+(//.//d+)?$");  
	                   Matcher matcher = p.matcher(textValue);
	                   if(matcher.matches()){
	                      //是数字当作double处理
	                      cell2.setCellValue(Double.parseDouble(textValue));
	                   }else{
	                	   //否则按String处理
	                      HSSFRichTextString richString = new HSSFRichTextString(textValue);
	                      cell2.setCellValue(richString);
	                   }
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            } finally {
	                //清理资源
	            }
	         }
	      }
	      try {
	    	  workbook.write(bout);//写入到输出流中
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	      /**
	       * 直接写到文件中
	       */
//	      FileOutputStream fout;
//	      try {
//	    	  fout = new FileOutputStream("E:/students2.xls");
//	    	  workbook.write(fout);
//	      } catch (Exception e1) {
//	    	  e1.printStackTrace();
//	      }  
	   }
}
