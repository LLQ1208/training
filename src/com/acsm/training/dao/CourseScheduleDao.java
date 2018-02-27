package com.acsm.training.dao;

import com.acsm.training.model.CourseSchedule;
import com.acsm.training.model.UserInfo;
import com.acsm.training.model.basic.PageHelper;

import java.util.List;

/**
 * Created by lq on 2018/2/26.
 */
public interface CourseScheduleDao extends BaseDao<CourseSchedule>{
    CourseSchedule queryById(int courseScheduleId);

    List<CourseSchedule> queryByArea(int provinceAreaId,int cityAreaId,int countyAreaId);

    PageHelper queryClassList(String searchKey,Integer provinceAreaId,UserInfo userInfo,int pageSize,int pageIndex);
}
