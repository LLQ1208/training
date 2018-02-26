package com.acsm.training.util;/**
 * Created by lq on 2017/12/7.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lianglinqiang
 * @create 2017-12-07
 */
public class ClassScheduleUtil {

    public Map<String,Integer> weekDayMap = new HashMap<String,Integer>(){{
        put("monday",1);
        put("tuesday",2);
        put("wednesday",3);
        put("thursday",4);
        put("friday",5);
        put("saturday",6);
        put("sunday",7);
    }};

    public static Map<Integer,Integer> minusValueToKeyMap = new HashMap<Integer,Integer>(){{
        put(0,0);
        put(15,1);
        put(30,2);
        put(45,3);
    }};

    public static Map<Integer,String> minusKeyToValueMap = new HashMap<Integer,String>(){{
        put(0,"00");
        put(1,"15");
        put(2,"30");
        put(3,"45");
    }};
}
