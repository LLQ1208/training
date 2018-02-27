package com.acsm.training.service;

import com.acsm.training.model.TecentAreaInfo;

import java.util.List;

/**
 * Created by lq on 2018/2/26.
 */
public interface TecentAreaInfoService {

    /**
     * 查询省列表
     * @return
     */
    List<TecentAreaInfo> queryProvinceList();

    /**
     * 根据省码查询市
     * @param proviceAreaId
     * @return
     */
    List<TecentAreaInfo> queryCityList(int proviceAreaId);

    /**
     * 根据市码查询区
     * @param cityAreaId
     * @return
     */
    List<TecentAreaInfo> queryAreaList(int cityAreaId);

    /**
     * 查询地点
     * @param areaId
     * @return
     */
    TecentAreaInfo queryByAreaId(int areaId);
}
