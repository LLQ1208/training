package com.acsm.training.service;

import com.acsm.training.model.Course;
import com.acsm.training.model.CourseSchedule;
import com.acsm.training.model.UserInfo;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.page.ClassModel;
import com.acsm.training.model.page.CourseModel;
import com.alibaba.fastjson.JSONArray;

import java.util.List;

/**
 * Created by lq on 2018/2/26.
 */
public interface CourseService {

    /**
     * 查询添加时候的list
     * @return
     */
    List<CourseModel> queryAddCourseList();


    void saveCourse(Integer provinceAreaId,Integer cityAreaId,Integer countyAreaId,Integer baseId,
                    String beginDate,String endDate,String className,JSONArray courseArr,
                    UserInfo userInfo);

    void saveEditCourse(Integer courseScheduleId,Integer provinceAreaId,Integer cityAreaId,Integer countyAreaId,Integer baseId,
                    String beginDate,String endDate,String className,JSONArray courseArr,
                    UserInfo userInfo);

    PageHelper<ClassModel> getClassList(String searchKey,Integer provinceAreaId,UserInfo userInfo,int pageSize,int pageIndex);


    List<CourseModel> queryCourseDetail(int classScheduleId);

    int getCourseNum(String searchKey,Integer provinceAreaId,UserInfo userInfo);

    CourseSchedule queryCourseScheduleById(int id);


    List<CourseModel> queryEditCourseList(int classScheduleId,String beginDate,String endDate);

    void deleteClass(int classScheduleId);

    Course queryCourseById(int id);
}
