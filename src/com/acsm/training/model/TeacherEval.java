package com.acsm.training.model;/**
 * Created by lq on 2018/2/28.
 */

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * @Author lianglinqiang
 * @create 2018-02-28
 */
@Entity
@Table(name="teacher_eval")
public class TeacherEval implements Serializable{
    private static final long serialVersionUID = -921801102851187751L;
    private Integer id;
    private Integer courseId;
    private Integer deleted;
    private String phone;
    private String studentName;//学生名字
    private Integer provinceAreaId;//省码
    private Integer evalId;//下拉框评价
    private Integer starEvalOne;//
    private Integer starEvalTwo;//
    private Integer starEvalThree;//
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

    @Column(name="course_id")
    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }
    @Column(name="deleted")
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    @Column(name="phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    @Column(name="student_name")
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }
    @Column(name="procince_area_id")
    public Integer getProvinceAreaId() {
        return provinceAreaId;
    }

    public void setProvinceAreaId(Integer provinceAreaId) {
        this.provinceAreaId = provinceAreaId;
    }
    @Column(name="eval_id")
    public Integer getEvalId() {
        return evalId;
    }

    public void setEvalId(Integer evalId) {
        this.evalId = evalId;
    }
    @Column(name="start_eval_one")
    public Integer getStarEvalOne() {
        return starEvalOne;
    }

    public void setStarEvalOne(Integer starEvalOne) {
        this.starEvalOne = starEvalOne;
    }
    @Column(name="start_eval_tow")
    public Integer getStarEvalTwo() {
        return starEvalTwo;
    }

    public void setStarEvalTwo(Integer starEvalTwo) {
        this.starEvalTwo = starEvalTwo;
    }
    @Column(name="start_eval_three")
    public Integer getStarEvalThree() {
        return starEvalThree;
    }

    public void setStarEvalThree(Integer starEvalThree) {
        this.starEvalThree = starEvalThree;
    }
    @Column(name="create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
