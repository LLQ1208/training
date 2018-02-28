package com.acsm.training.model.page;/**
 * Created by lq on 2018/2/28.
 */

import java.io.Serializable;

/**
 * @Author lianglinqiang
 * @create 2018-02-28
 */
public class EvalLevel implements Serializable{
    private static final long serialVersionUID = 3627061265217925448L;

    private Integer code;
    private String desc;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public EvalLevel(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
