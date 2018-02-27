package com.acsm.training.controller;


import com.acsm.training.model.UserInfo;

import javax.servlet.http.HttpServletRequest;

public class BaseController {

    /**
     * 获取登录用户
     * @param request
     * @return
     */
    public UserInfo getUser(HttpServletRequest request){
        UserInfo user=null;
        user=(UserInfo)request.getSession().getAttribute("currentUser");
        return user;
    }
}
