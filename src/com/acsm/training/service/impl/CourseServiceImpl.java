package com.acsm.training.service.impl;/**
 * Created by lq on 2018/2/26.
 */

import com.acsm.training.dao.CourseDao;
import com.acsm.training.dao.CourseScheduleDao;
import com.acsm.training.model.Course;
import com.acsm.training.model.CourseSchedule;
import com.acsm.training.model.UserInfo;
import com.acsm.training.model.basic.PageHelper;
import com.acsm.training.model.page.ClassModel;
import com.acsm.training.model.page.CourseModel;
import com.acsm.training.service.CourseService;
import com.acsm.training.util.ClassScheduleUtil;
import com.acsm.training.util.DateUtil;
import com.acsm.training.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-02-26
 */
@Service("courseService")
public class CourseServiceImpl implements CourseService {

    @Autowired
    CourseScheduleDao courseScheduleDao;

    @Autowired
    CourseDao courseDao;

    @Override
    public List<CourseModel> queryAddCourseList() {
        List<CourseModel> courseListModelList = new ArrayList<>();
        for(int i=0; i < 7; i++){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.add(Calendar.DAY_OF_MONTH, i);
            CourseModel courseListModel = new CourseModel();
            courseListModel.setDate(calendar.getTime());
            courseListModelList.add(courseListModel);
        }
        return courseListModelList;
    }

    @Override
    public void saveCourse(Integer provinceAreaId, Integer cityAreaId, Integer countyAreaId,Integer baseId,
                           String beginDate, String endDate, String className, JSONArray courseArr,UserInfo userInfo) {
        CourseSchedule courseSchedule = new CourseSchedule();
        courseSchedule.setProvinceAreaId(provinceAreaId);
        courseSchedule.setCityAreaId(cityAreaId);
        courseSchedule.setCountyAreaId(countyAreaId);
        courseSchedule.setBeginDate(DateUtil.StringToDate(beginDate, "yyyy/MM/dd"));
        courseSchedule.setEndDate(DateUtil.StringToDate(endDate, "yyyy/MM/dd"));
        courseSchedule.setClassName(className);
        courseSchedule.setDeleted(0);
        courseSchedule.setCreateTime(new Date());
        courseSchedule.setInsertUser(userInfo.getId());
        courseSchedule.setBaseId(baseId);
        courseScheduleDao.add(courseSchedule);

        //保存课程
        for(int i=0; i<courseArr.size(); i++){
            JSONObject courseDay = courseArr.getJSONObject(i);
            String date = courseDay.getString("date");
            String amCourseName = courseDay.getString("amCourseName");
            String amTeacher = courseDay.getString("amTeacher");
            String pmCourseName = courseDay.getString("pmCourseName");
            String pmTeacher = courseDay.getString("pmTeacher");
            //上午的课
            if(StringUtils.isNotEmpty(amCourseName) && StringUtils.isNotEmpty(amTeacher)){
                Course course = new Course();
                course.setCourseScheduleId(courseSchedule.getId());
                course.setDate(DateUtil.StringToDate(date,"yyyy/MM/dd"));
                course.setCourseName(amCourseName);
                course.setTeacher(amTeacher);
                course.setDateType(1);
                course.setDeleted(0);
                course.setCreateTime(new Date());
                courseDao.add(course);
            }
            //下午的课
            if(StringUtils.isNotEmpty(pmCourseName) && StringUtils.isNotEmpty(pmTeacher)){
                Course course = new Course();
                course.setCourseScheduleId(courseSchedule.getId());
                course.setDate(DateUtil.StringToDate(date,"yyyy/MM/dd"));
                course.setCourseName(pmCourseName);
                course.setTeacher(pmTeacher);
                course.setDateType(2);
                course.setDeleted(0);
                course.setCreateTime(new Date());
                courseDao.add(course);
            }
        }
    }

    @Override
    public PageHelper<ClassModel> getClassList(String searchKey, Integer provinceAreaId, UserInfo userInfo, int pageSize, int pageIndex) {
        PageHelper pageHelper = courseScheduleDao.queryClassList(searchKey,provinceAreaId,userInfo,pageSize,pageIndex);
        List<Object[]> classList = pageHelper.getList();
        List<ClassModel> classModelList = new ArrayList<>();
        if(null != classList && classList.size() > 0){
            for(Object[] obj : classList){
                ClassModel classModel = new ClassModel();
                classModel.setClassScheduleId(Integer.valueOf(obj[0].toString()));
                classModel.setArea(obj[1].toString());
                classModel.setClassName(obj[4].toString());
                classModel.setBaseAdmin(obj[5].toString());
                classModel.setClassPersonNum(0);
                classModel.setCourseNum(Integer.valueOf(obj[6].toString()));
                classModelList.add(classModel);
            }
        }
        PageHelper<ClassModel> returnPage = new PageHelper<>();
        returnPage.setList(classModelList);
        returnPage.setTotalRow(pageHelper.getTotalRow());
        returnPage.setPageSize(pageSize);
        returnPage.setNowPageIndex(pageIndex);
        return returnPage;
    }

    @Override
    public List<CourseModel> queryCourseDetail(int classScheduleId) {
        CourseSchedule courseSchedule = courseScheduleDao.queryById(classScheduleId);
        List<CourseModel> courseListModelList = new ArrayList<>();
        String endDate = DateUtil.format(courseSchedule.getEndDate(),"yyyy/MM/dd");
        int i = 0;
        while(true){
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(courseSchedule.getBeginDate());
            calendar.add(Calendar.DAY_OF_MONTH, i);
            String tempDate = DateUtil.format(calendar.getTime(),"yyyy/MM/dd");

            CourseModel courseListModel = new CourseModel();
            courseListModel.setDate(calendar.getTime());
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
