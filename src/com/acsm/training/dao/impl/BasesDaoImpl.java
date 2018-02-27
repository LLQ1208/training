package com.acsm.training.dao.impl;/**
 * Created by lq on 2018/2/27.
 */

import com.acsm.training.dao.BaseDao;
import com.acsm.training.dao.BasesDao;
import com.acsm.training.model.Base;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-02-27
 */
@Repository("basesDao")
public class BasesDaoImpl extends BaseDaoImpl<Base> implements BasesDao{
    @Override
    public Base queryById(int id) {
        String hql ="from Base where id=?";
        return (Base) this.Queryobject(hql, id);
    }

    @Override
    public List<Base> queryListByArea(Integer provinceAreaId, Integer cityAreaId, Integer countyAreaId) {
        String hql ="from Base where deleted=0 and provinceAreaId=:provinceAreaId " +
                " and cityAreaId=:cityAreaId and countyAreaId=:countyAreaId";
        Session session = this.getSession();
        Query q = session.createQuery(hql);
        q.setInteger("provinceAreaId", provinceAreaId);
        q.setInteger("cityAreaId", cityAreaId);
        q.setInteger("countyAreaId", countyAreaId);
        return q.list();
    }
}
