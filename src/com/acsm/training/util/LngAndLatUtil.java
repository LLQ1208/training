package com.acsm.training.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class LngAndLatUtil {
//    private static final String AK = "mv8fy8NLRuRs7BT2IdrD6sXmlbYKTPoG";
    private static final String AK = "ewNjBYkZllQhUElWX4WqCUirUBSWjZ6U";
    /**
     * 根据地址获得经纬度
     */
    public static LatitudeAndLongitude getLngAndLat(String address) {
        LatitudeAndLongitude latAndLng = new LatitudeAndLongitude();
        String url = "http://api.map.baidu.com/geocoder/v2/?address=" + address + "&output=json&ak=" + AK;
        String json = loadJSON(url);
        if (StringUtils.isEmpty(json)) {
            return latAndLng;
        }
        int len = json.length();
        // 如果不是合法的json格式
        if (json.indexOf("{") != 0 || json.lastIndexOf("}") != len - 1) {
            return latAndLng;
        }
        JSONObject obj = JSON.parseObject(json);
        if (obj.get("status").toString().equals("0")) {
            double lng = obj.getJSONObject("result").getJSONObject("location").getDouble("lng");
            double lat = obj.getJSONObject("result").getJSONObject("location").getDouble("lat");
            latAndLng.setLatitude(lat);
            latAndLng.setLongitude(lng);
        }
        return latAndLng;
    }

    public static String loadJSON(String url) {
        StringBuilder json = new StringBuilder();
        try {
            URL urlObj = new URL(url);
            URLConnection uc = urlObj.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(uc.getInputStream()));
            String inputLine = null;
            while ((inputLine = br.readLine()) != null) {
                json.append(inputLine);
            }
            br.close();
        } catch (MalformedURLException e) {
        } catch (IOException e) {
        }
        return json.toString();
    }

    /**
     * 测试方法 说明：把代码中的ak值（红色字部分）更改为你自己的ak值，在百度地图API中注册一下就有。
     * 百度路径：http://lbsyun.baidu.com/index.php?title=webapi/guide/changeposition
     */
    public static void main(String[] args) {
//        LatitudeAndLongitude latAndLng = LngAndLatUtil.getLngAndLat("北京市海淀区东升科技园");
//        System.out.println("经度：" + latAndLng.getLongitude() + "---纬度：" + latAndLng.getLatitude());
        //{"status":0,"result":{"location":{"lng":116.36234916449023,"lat":40.05232638459175},"precise":0,"confidence":50,"level":"工业园区"}}
        double distance1 = getDistance("北京市永旺家园", "长沙市雨花区佳天大厦3层");
        System.out.println(distance1);
        double distance2 = getDistance("北京市永旺家园", "北京市朝阳区大望路熏皮厂村17号");
        System.out.println(distance2);
    }

    /**
     * 补充：计算两点之间真实距离
     * @return 米
     */
    public static double getDistance(double longitude1, double latitude1, double longitude2, double latitude2) {
        // 维度
        double lat1 = (Math.PI / 180) * latitude1;
        double lat2 = (Math.PI / 180) * latitude2;
        // 经度
        double lon1 = (Math.PI / 180) * longitude1;
        double lon2 = (Math.PI / 180) * longitude2;
        // 地球半径
        double R = 6371;
        // 两点间距离 km，如果想要米的话，结果*1000就可以了
        double d = Math.acos(Math.sin(lat1) * Math.sin(lat2) + Math.cos(lat1) * Math.cos(lat2) * Math.cos(lon2 - lon1)) * R;
        return d * 1000;
    }
    public static double getDistance(double longitude1, double latitude1, String address){
        try {
            LatitudeAndLongitude lngAndLat = getLngAndLat(address);
            double distance = getDistance(longitude1, latitude1, lngAndLat.getLongitude(), lngAndLat.getLatitude());
            return distance;
        }catch (Exception e){
            return 0;
        }
    }
    public static double getDistance(String sourceAddress, String targetAddress){
        try {
            LatitudeAndLongitude sourceLngAndLat = getLngAndLat(sourceAddress);
            LatitudeAndLongitude targetLngAndLat = getLngAndLat(targetAddress);
            System.out.println(sourceLngAndLat.toString());
            System.out.println(targetLngAndLat.toString());
            double distance = getDistance(sourceLngAndLat.getLongitude(), sourceLngAndLat.getLatitude(), targetLngAndLat.getLongitude(), targetLngAndLat.getLatitude());
            return distance;
        }catch (Exception e){
            return 0;
        }
    }

    static class LatitudeAndLongitude{
        private double latitude;
        private double longitude;

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        @Override
        public String toString() {
            return "LatitudeAndLongitude{" +
                    "latitude=" + latitude +
                    ", longitude=" + longitude +
                    '}';
        }
    }

}