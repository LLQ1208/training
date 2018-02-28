package com.acsm.training.dao;

import com.acsm.training.model.ClassEvaluate;

import java.util.List;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 19:23 2018/2/28
 */

public interface ClassEvaluateDao extends BaseDao<ClassEvaluate>  {
    ClassEvaluate queryById(Integer id);
    List<Object[]> queryByNameByPhoneById(Integer id, String name, String phone);
}
