package com.acsm.training.service.impl;/**
 * Created by lq on 2018/2/26.
 */

import com.acsm.training.dao.TecentAreaInfoDao;
import com.acsm.training.model.TecentAreaInfo;
import com.acsm.training.service.TecentAreaInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-02-26
 */
@Service("tecentAreaInfoService")
public class TecentAreaInfoServiceImpl implements TecentAreaInfoService{
    @Autowired
    TecentAreaInfoDao tecentAreaInfoDao;

    @Override
    public List<TecentAreaInfo> queryProvinceList() {
        return tecentAreaInfoDao.queryProvinceList();
    }

    @Override
    public List<TecentAreaInfo> queryCityList(int proviceAreaId) {
        return tecentAreaInfoDao.queryCityList(proviceAreaId);
    }

    @Override
    public List<TecentAreaInfo> queryAreaList(int cityAreaId) {
        return tecentAreaInfoDao.queryAreaList(cityAreaId);
    }

    @Override
    public TecentAreaInfo queryByAreaId(int areaId) {
        return tecentAreaInfoDao.queryByAreaId(areaId);
    }


}
