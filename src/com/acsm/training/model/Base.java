package com.acsm.training.model;/**
 * Created by lq on 2018/2/27.
 */

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author lianglinqiang
 * @create 2018-02-27
 */

@Entity
@Table(name="base")
public class Base implements Serializable{
    private static final long serialVersionUID = 2189098998277630944L;

    private Integer id;
    private Integer deleted;
    private String baseName;//基地名字
    private Integer provinceAreaId;//省码
    private Integer cityAreaId;//市码
    private Integer countyAreaId;//县区码

    @Id
    @GeneratedValue
    @Column(name="id",length=11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="deleted")
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    @Column(name="base_name")
    public String getBaseName() {
        return baseName;
    }

    public void setBaseName(String baseName) {
        this.baseName = baseName;
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
}
