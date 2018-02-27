package com.acsm.training.service.impl;/**
 * Created by lq on 2018/2/27.
 */

import com.acsm.training.dao.BasesDao;
import com.acsm.training.model.Base;
import com.acsm.training.service.BasesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-02-27
 */
@Service("basesService")
public class BasesServiceImpl implements BasesService{
    @Autowired
    BasesDao basesDao;
    @Override
    public List<Base> queryBaseList(Integer provinceAreaId, Integer cityAreaId, Integer countyAreaId) {
        return basesDao.queryListByArea(provinceAreaId,cityAreaId,countyAreaId);
    }
}