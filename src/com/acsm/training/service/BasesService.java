package com.acsm.training.service;

import com.acsm.training.model.Base;

import java.util.List;

/**
 * Created by lq on 2018/2/27.
 */
public interface BasesService {
    List<Base> queryBaseList(Integer provinceAreaId,Integer cityAreaId,Integer countyAreaId);

    Base queryById(Integer baseId);

    List<Base> queryListByProvinceArea(Integer provinceAreaId);
}
