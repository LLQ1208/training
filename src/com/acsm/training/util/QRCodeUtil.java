package com.acsm.training.util;/**
 * Created by lq on 2018/2/27.
 */

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import org.springframework.http.ResponseEntity;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author lianglinqiang
 * @create 2018-02-27
 */
public class QRCodeUtil {

    /**
     *  生成web版本二维码
     * @param url 要生成二维码的路径
     * @param response response对象
     * @param width 二维码宽度
     * @param height 二维码高度
     * @throws IOException
     */
    public void getTwoDimension(String url,HttpServletResponse response,int width,int height) throws IOException{
        if (url != null && !"".equals(url)) {
            ServletOutputStream stream = null;
            try {
                stream = response.getOutputStream();
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, height, width);
                MatrixToImageWriter.writeToStream(m, "png", stream);
            } catch (WriterException e) {
                e.printStackTrace();
            } finally {
                if (stream != null) {
                    stream.flush();
                    stream.close();
                }
            }
        }
    }
}
