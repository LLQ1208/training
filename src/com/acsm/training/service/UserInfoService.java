package com.acsm.training.service;

import com.acsm.training.model.CommonResult;

import javax.servlet.http.HttpSession;

/**
 * Created by lq on 2018/2/26.
 */
public interface UserInfoService {
    /**
     * 登录验证
     * @param userName 登录名
     * @param password 密码
     * @return
     */
    public CommonResult login(HttpSession session, String userName, String password);
}
