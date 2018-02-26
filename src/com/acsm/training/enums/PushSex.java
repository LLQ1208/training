package com.acsm.training.enums;

/**
 *  推送性别
 *  Created by xiaobing.liu on 2017/6/14.
 */
public enum PushSex {

    ALL(0, "所有"),
    MAN(1, "男"),
    WOMAN(2, "女");

    public final int CODE;
    public final String DESC;

    PushSex(int code, String desc) {
        CODE = code;
        DESC = desc;
    }

    public static PushSex fromCode(int code) {
        for (PushSex obj : PushSex.values())
            if (obj.CODE == code)
                return obj;
        return null;
    }
}
