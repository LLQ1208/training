package com.acsm.training.service.impl;/**
 * Created by lq on 2018/2/26.
 */

import com.acsm.training.dao.BasesDao;
import com.acsm.training.dao.UserInfoDao;
import com.acsm.training.enums.UserType;
import com.acsm.training.model.CommonResult;
import com.acsm.training.model.PersonnelInfo;
import com.acsm.training.model.UserInfo;
import com.acsm.training.service.UserInfoService;
import com.acsm.training.util.UserRegisterValidateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-02-26
 */

@Service("userInfoService")
public class UserInfoServiceImpl implements UserInfoService{

    @Autowired
    UserInfoDao userInfoDao;

    @Autowired
    BasesDao basesDao;

    @Override
    public CommonResult login(HttpSession session, String userName, String password) {
        UserInfo user = userInfoDao.queryUserByUserName(userName);
        String sha1Pwd = UserRegisterValidateUtil.encodePassword(password, "SHA");
        System.out.println(sha1Pwd);
        if(user!=null && sha1Pwd.equals(user.getPassword())){
            if(user.getUserType() == UserType.BASEADMIN.getCODE()){
                user.setBase(basesDao.queryById(user.getBaseInfoId()));
            }
            session.setAttribute("currentUser",user);
            return new CommonResult(1, "success", true);
        }else{
            return new CommonResult(2, "用户名或密码不正确", false);
        }
    }

    @Override
    public void addUserInfo(String userName, String passWord,Integer proviceId,Integer baseListId,Integer userType) {
        String sha1Pwd = UserRegisterValidateUtil.encodePassword(passWord, "SHA");
        UserInfo userInfo = new UserInfo();
        userInfo.setUserName(userName);
        userInfo.setPassword(sha1Pwd);
        userInfo.setUserType(userType);
        userInfo.setDeleted(0);
        userInfo.setBaseId(proviceId);
        if(null != baseListId){
            userInfo.setBaseInfoId(baseListId);
        }
        userInfoDao.add(userInfo);
    }

    @Override
    public List<PersonnelInfo> personnelList(Integer areaId, String searchUserName, Integer userType) {
        List<Object[]> personnelList = userInfoDao.personnelList(areaId,searchUserName,userType);
        List<PersonnelInfo> personnelInfos = new ArrayList<PersonnelInfo>();
        for (Object[] objects : personnelList) {
            PersonnelInfo personnelInfo = new PersonnelInfo();
            personnelInfo.setId(Integer.valueOf(objects[0].toString()));
            personnelInfo.setUserName(objects[1].toString());
            personnelInfo.setUserType(Integer.valueOf(objects[2].toString()));

            if(Integer.valueOf(objects[2].toString()) == UserType.ADMIN.CODE){
                personnelInfo.setUserTypeName(UserType.ADMIN.DESC);
            }
            if(Integer.valueOf(objects[2].toString()) == UserType.PROVINCEADMIN.CODE){
                personnelInfo.setUserTypeName(UserType.PROVINCEADMIN.DESC);
            }
            if(Integer.valueOf(objects[2].toString()) == UserType.BASEADMIN.CODE){
                personnelInfo.setUserTypeName(UserType.BASEADMIN.DESC);
            }
            personnelInfo.setAreaId(Integer.valueOf(objects[3].toString()));
            personnelInfo.setAreaName(objects[4].toString());
            personnelInfos.add(personnelInfo);
        }
        return personnelInfos;
    }

    @Override
    public UserInfo queryById(Integer userId) {
        return userInfoDao.queryById(userId);
    }

    @Override
    public void updateUserInfo(Integer userId,String userName, String passWord, Integer proviceId,Integer baseListId) {
        UserInfo userInfo = userInfoDao.queryById(userId);
//        userInfo.setUserName(userName);
        if(null!= passWord && !"undefined".equals(passWord) && !"".equals(passWord)){
            String sha1Pwd = UserRegisterValidateUtil.encodePassword(passWord, "SHA");
            userInfo.setPassword(sha1Pwd);
        }
        userInfo.setBaseId(proviceId);
        if(null != baseListId){
            userInfo.setBaseInfoId(baseListId);
        }
        userInfoDao.update(userInfo);
    }

    @Override
    public UserInfo queryUserByUserName(String userName) {
        return userInfoDao.queryUserByUserName(userName);
    }
}
