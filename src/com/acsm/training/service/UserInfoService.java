package com.acsm.training.service;

import com.acsm.training.model.CommonResult;
import com.acsm.training.model.PersonnelInfo;
import com.acsm.training.model.UserInfo;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by lq on 2018/2/26.
 */
public interface UserInfoService {
    /**
     * 登录验证
     *
     * @param userName 登录名
     * @param password 密码
     * @return
     */
    public CommonResult login(HttpSession session, String userName, String password);

    public void addUserInfo(String userName, String passWord, Integer proviceId);

    public List<PersonnelInfo> personnelList(Integer areaId, String searchUserName, Integer userType);

    public UserInfo queryById(Integer userId);

    public void updateUserInfo(Integer userId, String userName, String passWord, Integer proviceId);
}
