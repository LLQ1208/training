package com.acsm.training.model;

import com.alibaba.fastjson.JSON;

/**
 * Created by xiaobing.liu on 2017/7/3.
 */
public class CommonResult {

    private int ret;
    private String msg;
    private boolean result;

    public int getRet() {
        return ret;
    }

    public void setRet(int ret) {
        this.ret = ret;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isResult() {
        return result;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public CommonResult(int ret, String msg, boolean result) {
        this.ret = ret;
        this.msg = msg;
        this.result = result;
    }
    public static String toJsonResult(int ret, String msg, boolean result){
        CommonResult commonResult = new CommonResult(ret, msg, result);
        return JSON.toJSONString(commonResult);
    }
    private CommonResult() {
    }


    @Override
    public String toString() {
        return "HotelJoinResult{" +
                "ret=" + ret +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }
}
