package com.acsm.training.model.page;/**
 * Created by lq on 2018/2/27.
 */

import java.io.Serializable;

/**
 * @Author lianglinqiang
 * @create 2018-02-27
 */
public class ClassModel implements Serializable{
    private static final long serialVersionUID = 526679493118204111L;

    private Integer classScheduleId;//课程表id
    private String area; //地区
    private String className; //班次
    private String baseAdmin;//基地管理员
    private Integer classPersonNum;//班次人数
    private Integer courseNum;//课节数

    public Integer getClassScheduleId() {
        return classScheduleId;
    }

    public void setClassScheduleId(Integer classScheduleId) {
        this.classScheduleId = classScheduleId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getBaseAdmin() {
        return baseAdmin;
    }

    public void setBaseAdmin(String baseAdmin) {
        this.baseAdmin = baseAdmin;
    }

    public Integer getClassPersonNum() {
        return classPersonNum;
    }

    public void setClassPersonNum(Integer classPersonNum) {
        this.classPersonNum = classPersonNum;
    }

    public Integer getCourseNum() {
        return courseNum;
    }

    public void setCourseNum(Integer courseNum) {
        this.courseNum = courseNum;
    }
}
