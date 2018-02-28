package com.acsm.training.service;

import com.acsm.training.model.CourseSchedule;
import com.acsm.training.model.page.CourseModel;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * Created by lq on 2018/2/28.
 */
public interface WetChatService {
    List<CourseModel> queryCourseDetail(int classScheduleId);

    CourseSchedule queryClassByCourseId(int courseId);

    JSONObject saveTeacherEval(Integer courseId,String studentName,String phone,Integer provinceAreaId,
                         Integer evalId,Integer starEvalOne,Integer starEvalTwo,Integer starEvalThree);

    CourseSchedule queryById(Integer id);
}
