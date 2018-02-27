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
@Table(name="course_schedule")
public class CourseSchedule implements Serializable{
    private static final long serialVersionUID = 2248959241749159525L;

    private Integer id;
    private Integer provinceAreaId;//省码
    private Integer cityAreaId;//市码
    private Integer countyAreaId;//县区码
    private Date beginDate; //开始日期
    private Date endDate; //结束日期
    private String className;//课程表名称
    private Integer deleted;
    private Date createTime;
    private Integer insertUser;
    private Integer baseId;

    @Id
    @GeneratedValue
    @Column(name="id",length=11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="procince_area_id")
    public Integer getProvinceAreaId() {
        return provinceAreaId;
    }

    public void setProvinceAreaId(Integer provinceAreaId) {
        this.provinceAreaId = provinceAreaId;
    }
    @Column(name="city_area_id")
    public Integer getCityAreaId() {
        return cityAreaId;
    }

    public void setCityAreaId(Integer cityAreaId) {
        this.cityAreaId = cityAreaId;
    }
    @Column(name="county_area_id")
    public Integer getCountyAreaId() {
        return countyAreaId;
    }

    public void setCountyAreaId(Integer countyAreaId) {
        this.countyAreaId = countyAreaId;
    }
    @Column(name="begin_date")
    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }
    @Column(name="end_date")
    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Column(name="class_name")
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
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

    @Column(name="insert_user")
    public Integer getInsertUser() {
        return insertUser;
    }

    public void setInsertUser(Integer insertUser) {
        this.insertUser = insertUser;
    }

    @Column(name="base_id")
    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }
}
