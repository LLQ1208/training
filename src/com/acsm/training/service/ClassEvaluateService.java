package com.acsm.training.service;

import java.util.List;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 19:23 2018/2/28
 */
public interface ClassEvaluateService {
    public void addClassEvaluate(Integer classScheduleId,String studentName,String studentPhone,Integer areaId,Integer satisfaction,Integer accommodations,Integer considerate,Integer rationality,Integer serviceAttitude,Integer gain);
    List<Object[]> queryByNameByPhoneById(Integer id, String name, String phone);
}
