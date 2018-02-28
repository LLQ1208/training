package com.acsm.training.service;

import com.acsm.training.model.page.CourseModel;

import java.util.List;

/**
 * Created by lq on 2018/2/28.
 */
public interface WetChatService {
    List<CourseModel> queryCourseDetail(int classScheduleId);
}
