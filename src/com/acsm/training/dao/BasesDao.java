package com.acsm.training.dao;

import com.acsm.training.model.Base;

import java.util.List;

/**
 * Created by lq on 2018/2/27.
 */
public interface BasesDao extends BaseDao<Base>{

    Base queryById(int id);

    List<Base> queryListByArea(Integer provinceAreaId, Integer cityAreaId, Integer countyAreaId);

    List<Base> queryListByProvinceArea(Integer provinceAreaId);
}
