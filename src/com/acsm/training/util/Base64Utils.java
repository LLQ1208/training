package com.acsm.training.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64Utils {  
    public static void main(String[] args) {  
//        String strImg = GetImageStr();  
//        System.out.println(strImg);  
//        GenerateImage("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");  
    }  
    //图片转化成base64字符串  
    public static String GetImageStr() {//将图片文件转化为字节数组字符串，并对其进行Base64编码处理  
        String imgFile = "c://images/photo.jpg";//待处理的图片  
        InputStream in = null;  
        byte[] data = null;  
        //读取图片字节数组  
        try {  
            in = new FileInputStream(imgFile);          
            data = new byte[in.available()];  
            in.read(data);  
            in.close();  
        }   
        catch (IOException e){  
            e.printStackTrace();  
        }  
        //对字节数组Base64编码  
        BASE64Encoder encoder = new BASE64Encoder();  
        return encoder.encode(data);//返回Base64编码过的字节数组字符串  
    }  
      
    //base64字符串转化成图片  
    public static boolean GenerateImage(String imgStr, String imageName){   //对字节数组字符串进行Base64解码并生成图片  
        if (imgStr == null){
        	//图像数据为空  
        	 return false;  
        }
        BASE64Decoder decoder = new BASE64Decoder();  
        try {  
            //Base64解码  
            byte[] b = decoder.decodeBuffer(imgStr);  
            for(int i=0;i<b.length;++i) {  
                if(b[i]<0) {//调整异常数据  
                    b[i]+=256;  
                }  
            }  
            //生成jpeg图片  
            String imgFilePath = "/usr/local/images/"+imageName+".jpg";//新生成的图片  
            OutputStream out = new FileOutputStream(imgFilePath);      
            out.write(b);  
            out.flush();  
            out.close();  
            return true;  
        } catch (Exception e){  
            return false;  
        }  
    }

    //base64字符串转化成图片
    public static boolean generateImage(String imgStr, String imageName, String baseUrl){   //对字节数组字符串进行Base64解码并生成图片
        if (imgStr == null){
            //图像数据为空
            return false;
        }
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(imgStr);
            for(int i=0;i<b.length;++i) {
                if(b[i]<0) {//调整异常数据
                    b[i]+=256;
                }
            }
            //生成jpeg图片
            String imgFilePath = baseUrl + imageName + ".jpg";//新生成的图片
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            return true;
        } catch (Exception e){
            return false;
        }
    }
}  