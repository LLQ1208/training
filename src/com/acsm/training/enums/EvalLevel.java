package com.acsm.training.enums;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lq on 2018/2/28.
 */
public enum EvalLevel {

    GOOD(1,"很好"),
    GENERAL(2,"一般"),
    BAD(3,"不好");

    public final int CODE;
    public final String DESC;

    public int getCODE() {
        return CODE;
    }

    public String getDESC() {
        return DESC;
    }

    EvalLevel(int CODE, String DESC) {
        this.CODE = CODE;
        this.DESC = DESC;
    }

    public static EvalLevel fromCode(int code) {
        for (EvalLevel obj : EvalLevel.values())
            if (obj.CODE == code)
                return obj;
        return null;
    }

}
