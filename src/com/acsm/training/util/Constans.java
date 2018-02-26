package com.acsm.training.util;/**
 * Created by lq on 2017/11/27.
 */

import java.util.HashMap;
import java.util.Map;

/**
 * @Author lianglinqiang
 * @create 2017-11-27
 */
public class Constans {
    public static final String CHECKCODE = "CHECK_CODE";//验证码

    public static final int ADMIN_ID = 174; //系统管理员id

    public static final float LB_TO_KG = 0.4535924f;// 英镑换算kg

    public static final Map<Integer,Double>  WEIGHT_UNIT_M = new HashMap<Integer,Double>(){{
        put(1,1d); //kg
        put(2,0.4535924d); //lb
    }};

    //distance统一单位m
    public static final Map<Integer,Double>  DISTANCE_UNIT_M = new HashMap<Integer,Double>(){{
        put(3,1609.344); //mile
        put(4,1d); //meters
        put(5,1000d); //km
        put(6,0.9144d); //yard
        put(7,0.3048d); //feet
        put(8,0.0254d); //inches
        put(9,0.01d); //inches
    }};
}
