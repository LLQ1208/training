package com.acsm.training.enums;

/**
 *  推送对象
 *  Created by xiaobing.liu on 2017/6/14.
 */
public enum UserType {

    BOSS(1, "老板"),
    EMPLOYEE(5, "员工"),
    COACH(3, "教练"),
    MEMBER(4, "会员");

    public final int CODE;
    public final String DESC;

    UserType(int code, String desc) {
        CODE = code;
        DESC = desc;
    }

    public static UserType fromCode(int code) {
        for (UserType obj : UserType.values())
            if (obj.CODE == code)
                return obj;
        return null;
    }

    public String getDESC() {
        return DESC;
    }

    public int getCODE() {
        return CODE;
    }
}
