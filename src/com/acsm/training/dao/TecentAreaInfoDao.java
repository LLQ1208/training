package com.acsm.training.dao;

import com.acsm.training.model.TecentAreaInfo;

import java.util.List;

/**
 * Created by lq on 2018/2/26.
 */
public interface TecentAreaInfoDao extends BaseDao<TecentAreaInfo>{

    List<TecentAreaInfo> queryProvinceList();

    List<TecentAreaInfo> queryCityList(int proviceAreaId);

    List<TecentAreaInfo> queryAreaList(int cityAreaId);

    TecentAreaInfo queryByAreaId(int areaId);
}
