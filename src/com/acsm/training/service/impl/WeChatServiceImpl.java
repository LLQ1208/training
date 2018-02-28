package com.acsm.training.service.impl;/**
 * Created by lq on 2018/2/28.
 */

import com.acsm.training.dao.CourseDao;
import com.acsm.training.dao.CourseScheduleDao;
import com.acsm.training.model.Course;
import com.acsm.training.model.CourseSchedule;
import com.acsm.training.model.page.CourseModel;
import com.acsm.training.service.WetChatService;
import com.acsm.training.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-02-28
 */
@Service("wetChatService")
public class WeChatServiceImpl implements WetChatService{
    @Autowired
    CourseScheduleDao courseScheduleDao;
    @Autowired
    CourseDao courseDao;
    @Override
    public List<CourseModel> queryCourseDetail(int classScheduleId) {
        CourseSchedule courseSchedule = courseScheduleDao.queryById(classScheduleId);
        List<CourseModel> courseListModelList = new ArrayList<>();
        String endDate = DateUtil.format(courseSchedule.getEndDate(), "yyyy/MM/dd");
        int i = 0;
        while(true){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(courseSchedule.getBeginDate());
            calendar.add(Calendar.DAY_OF_MONTH, i);
            String tempDate = DateUtil.format(calendar.getTime(),"yyyy/MM/dd");

            CourseModel courseListModel = new CourseModel();
            courseListModel.setWeek("星期"+DateUtil.getZHWeekOfDate(calendar.getTime()));
            courseListModel.setDate(calendar.getTime());
            courseListModel.setShowDate(DateUtil.format(calendar.getTime(),"MM/dd"));
            List<Course> courseList = courseDao.queryListByScheduleId(classScheduleId,calendar.getTime());
            if(null != courseList && courseList.size() > 0){
                for(Course course : courseList){
                    if(course.getDateType() == 1){//上午课程
                        courseListModel.setCourseAM(course);
                    }else if(course.getDateType() == 2){//下午课程
                        courseListModel.setCoursePM(course);
                    }
                }
            }
            courseListModelList.add(courseListModel);
            //如果日期到结束日期后，则跳出循环
            System.out.println("循环次数++++" + i);
            if(endDate.equals(tempDate)){
                break;
            }
            i++;
        }
        return courseListModelList;
    }
}
