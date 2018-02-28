package com.acsm.training.model.page;/**
 * Created by lq on 2018/2/26.
 */

import com.acsm.training.model.Course;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-02-26
 */
public class CourseModel implements Serializable{
    private static final long serialVersionUID = 6940158109057420189L;

    private Date date;
    private String showDate;
    private String week;
    private Course courseAM;//上午课
    private Course coursePM;//上午课

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Course getCourseAM() {
        return courseAM;
    }

    public void setCourseAM(Course courseAM) {
        this.courseAM = courseAM;
    }

    public Course getCoursePM() {
        return coursePM;
    }

    public void setCoursePM(Course coursePM) {
        this.coursePM = coursePM;
    }

    public String getShowDate() {
        return showDate;
    }

    public void setShowDate(String showDate) {
        this.showDate = showDate;
    }

    public String getWeek() {
        return week;
    }

    public void setWeek(String week) {
        this.week = week;
    }
}
