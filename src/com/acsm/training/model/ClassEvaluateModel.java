package com.acsm.training.model;

import org.apache.poi.ss.formula.functions.T;

import java.util.List;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 18:20 2018/2/28
 */
public class ClassEvaluateModel<T> {
    private CourseSchedule courseSchedule;
    private String title;
    private List<T> list;

    public CourseSchedule getCourseSchedule() {
        return courseSchedule;
    }

    public void setCourseSchedule(CourseSchedule courseSchedule) {
        this.courseSchedule = courseSchedule;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
