package com.acsm.training.service.impl;

import com.acsm.training.dao.ClassEvaluateDao;
import com.acsm.training.model.ClassEvaluate;
import com.acsm.training.service.ClassEvaluateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 19:23 2018/2/28
 */
@Service("ClassEvaluateService")
public class ClassEvaluateServiceImpl implements ClassEvaluateService {
    @Autowired
    ClassEvaluateDao classEvaluateDao;

    @Override
    public void addClassEvaluate(Integer classScheduleId,String studentName, String studentPhone, Integer areaId, Integer satisfaction, Integer accommodations, Integer considerate, Integer rationality, Integer serviceAttitude, Integer gain) {
        ClassEvaluate classEvaluate = new ClassEvaluate();
        classEvaluate.setCourseScheduleId(classScheduleId);
        classEvaluate.setStudentName(studentName);
        classEvaluate.setStudentPhone(studentPhone);
        classEvaluate.setAreaId(areaId);
        classEvaluate.setSatisfaction(satisfaction);
        classEvaluate.setAccommodations(accommodations);
        classEvaluate.setConsiderate(considerate);
        classEvaluate.setRationality(rationality);
        classEvaluate.setServiceAttitude(serviceAttitude);
        classEvaluate.setGain(gain);
        classEvaluateDao.add(classEvaluate);
    }

    @Override
    public List<Object[]> queryByNameByPhoneById(Integer id, String name, String phone) {
        return classEvaluateDao.queryByNameByPhoneById(id,name,phone);
    }
}
