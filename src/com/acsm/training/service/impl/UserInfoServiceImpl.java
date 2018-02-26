package com.acsm.training.service.impl;/**
 * Created by lq on 2018/2/26.
 */

import com.acsm.training.dao.UserInfoDao;
import com.acsm.training.model.CommonResult;
import com.acsm.training.model.UserInfo;
import com.acsm.training.service.UserInfoService;
import com.acsm.training.util.UserRegisterValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @Author lianglinqiang
 * @create 2018-02-26
 */

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    UserInfoDao userInfoDao;

    @Override
    public CommonResult login(HttpSession session, String userName, String password) {
        UserInfo user = userInfoDao.queryUserByUserName(userName);
        String sha1Pwd = UserRegisterValidateUtil.encodePassword(password, "SHA");
        System.out.println(sha1Pwd);
        if(user!=null && sha1Pwd.equals(user.getPassword())){
            session.setAttribute("currentUser",user);
            return new CommonResult(1, "success", true);
        }else{
            return new CommonResult(2, "用户名或密码不正确", false);
        }
    }
}
