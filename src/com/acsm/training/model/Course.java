package com.acsm.training.model;/**
 * Created by lq on 2018/2/26.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author lianglinqiang
 * @create 2018-02-26
 */
@Entity
@Table(name="course")
public class Course implements Serializable{
    private static final long serialVersionUID = -3429982918917790667L;

    private Integer id;
    private Integer courseScheduleId;//课程表名称
    private Date date;//日期
    private String courseName;//课程名称
    private String teacher;//讲课人
    private Integer dateType;//1 上午  2下午
    private Integer deleted;
    private Date createTime;

    @Id
    @GeneratedValue
    @Column(name="id",length=11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="course_schedule_id")
    public Integer getCourseScheduleId() {
        return courseScheduleId;
    }

    public void setCourseScheduleId(Integer courseScheduleId) {
        this.courseScheduleId = courseScheduleId;
    }

    @Column(name="date")
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Column(name="course_name")
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    @Column(name="teacher")
    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    @Column(name="date_type")
    public Integer getDateType() {
        return dateType;
    }

    public void setDateType(Integer dateType) {
        this.dateType = dateType;
    }

    @Column(name="deleted")
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    @Column(name="create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
