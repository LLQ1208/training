package com.acsm.training.dao.impl;/**
 * Created by lq on 2018/2/26.
 */

import com.acsm.training.dao.TecentAreaInfoDao;
import com.acsm.training.model.TecentAreaInfo;
import com.acsm.training.model.UserInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-02-26
 */
@Repository("tecentAreaInfoDao")
public class TecentAreaInfoDaoImpl extends BaseDaoImpl<TecentAreaInfo> implements TecentAreaInfoDao{
    @Override
    public List<TecentAreaInfo> queryProvinceList() {
        String hql ="from TecentAreaInfo where areaId like '__0000'";
        return this.list(hql);
    }

    @Override
    public List<TecentAreaInfo> queryCityList(int proviceAreaId) {
        int proviceAreaIdBefore2 = Integer.valueOf((proviceAreaId+"").substring(0,2));
        String hql ="from TecentAreaInfo where areaId like '"+proviceAreaIdBefore2+"__00' and areaId != ?";
        return this.list(hql,proviceAreaId);
    }

    @Override
    public List<TecentAreaInfo> queryAreaList(int cityAreaId) {
        int cityAreaIdBefore4 = Integer.valueOf(new Integer(cityAreaId).toString().substring(0,4));
        String hql ="from TecentAreaInfo where areaId like '"+cityAreaIdBefore4+"__' and areaId != ?";
        return this.list(hql,cityAreaId);
    }

    @Override
    public TecentAreaInfo queryByAreaId(int areaId) {
        String hql ="from TecentAreaInfo where areaId = ?";
        return (TecentAreaInfo) this.Queryobject(hql, areaId);
    }
}
