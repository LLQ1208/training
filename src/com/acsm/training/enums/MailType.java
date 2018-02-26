package com.acsm.training.enums;

/**
 *  推送对象
 *  Created by xiaobing.liu on 2017/6/14.
 */
public enum MailType {

    BIRTHDAY(1, "生日"),
    FUTURETENSE(2, "即将到期"),
    EXPIRY(3, "到期");

    public final int CODE;
    public final String DESC;

    MailType(int code, String desc) {
        CODE = code;
        DESC = desc;
    }

    public static MailType fromCode(int code) {
        for (MailType obj : MailType.values())
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
