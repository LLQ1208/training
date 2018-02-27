package com.acsm.training.dao.impl;/**
 * Created by lq on 2018/2/26.
 */

import com.acsm.training.dao.UserInfoDao;
import com.acsm.training.model.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

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
        String hql = "from UserInfo where id=?";
        return (UserInfo) this.Queryobject(hql, id);
    }

    @Override
    public List<Object[]> personnelList(Integer areaId, String searchUserName,Integer userType) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select ui.id,ui.user_name,ui.user_type,ui.base_id,tai.full_name from user_info ui ");
        sql.append(" left join tecent_area_info tai on tai.area_id = ui.base_id where ui.deleted = 0");
        sql.append(" and  ui.user_type = ").append(userType);

        if(null != areaId){
            sql.append(" and ui.base_id = ").append(areaId);
        }
        if(null != searchUserName && !"".equals(searchUserName)){
            sql.append(" and ui.user_name like '%").append(searchUserName).append("%'");
        }
        return this.queryBySql(sql.toString());
    }


}
