package com.acsm.training.dao;

import com.acsm.training.model.UserInfo;

import java.util.List;

/**
 * Created by lq on 2018/2/26.
 */
public interface UserInfoDao extends BaseDao<UserInfo> {
    /**
     * 根据用户名查找用户
     * @param userName
     * @return
     */
    public UserInfo queryUserByUserName(String userName);

    /**
     * @param id
     * @return
     */
    public UserInfo queryById(Integer id);

    public List<Object[]> personnelList(Integer areaId, String searchUserName,Integer userType);
}
