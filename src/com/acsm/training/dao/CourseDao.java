package com.acsm.training.dao;

import com.acsm.training.model.Course;

import java.util.Date;
import java.util.List;

/**
 * Created by lq on 2018/2/26.
 */
public interface CourseDao extends BaseDao<Course>{

    List<Course> queryListByScheduleId(int courseScheduleId,Date date);

    Course queryById(int id);

    List<Course> queryList(int courseScheduleId);
}
