package com.acsm.training.enums;

/**
 *  推送对象
 *  Created by xiaobing.liu on 2017/6/14.
 */
public enum PushObject {

    ALL(0, "所有"),
    MONTH_CARD(1, "月卡用户"),
    QUANTITY_CARD(2, "次卡用户");

    public final int CODE;
    public final String DESC;

    PushObject(int code, String desc) {
        CODE = code;
        DESC = desc;
    }

    public static PushObject fromCode(int code) {
        for (PushObject obj : PushObject.values())
            if (obj.CODE == code)
                return obj;
        return null;
    }
}
