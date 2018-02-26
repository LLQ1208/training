package com.acsm.training.dao.impl;/**
 * Created by lq on 2018/2/26.
 */

import com.acsm.training.dao.UserInfoDao;
import com.acsm.training.model.UserInfo;
import org.springframework.stereotype.Repository;

/**
 * @Author lianglinqiang
 * @create 2018-02-26
 */
@Repository("userInfoDao")
public class UserInfoDaoImpl extends BaseDaoImpl<UserInfo> implements UserInfoDao{
    @Override
    public UserInfo queryUserByUserName(String userName) {
        String hql ="from UserInfo where userName=? and deleted=0";
        return (UserInfo)this.Queryobject(hql,userName);
    }

    @Override
    public UserInfo queryById(Integer id) {
        String hql = "from User where id=?";
        return (UserInfo) this.Queryobject(hql, id);
    }
}
