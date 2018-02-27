package com.acsm.training.model;/**
 * Created by lq on 2018/2/26.
 */

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author lianglinqiang
 * @create 2018-02-26
 */

@Entity
@Table(name="tecent_area_info")
public class TecentAreaInfo implements Serializable{
    private static final long serialVersionUID = -6929699878321967875L;

    private Integer id;
    private Integer areaId; //地区id
    private String name; //名称
    private String fullName; //全名
    private String pinyin; //拼音
    private Double lat; //经度
    private Double lng; //纬度
    private Double north; //北部坐标
    private Double east; //东部坐标
    private Double south; //南部坐标
    private Double west; //西部坐标

    @Id
    @GeneratedValue
    @Column(name="id",length=11)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    @Column(name="area_id")
    public Integer getAreaId() {
        return areaId;
    }

    public void setAreaId(Integer areaId) {
        this.areaId = areaId;
    }
    @Column(name="name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    @Column(name="full_name")
    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }
    @Column(name="pinyin")
    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }
    @Column(name="lat")
    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }
    @Column(name="lng")
    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }
    @Column(name="north")
    public Double getNorth() {
        return north;
    }

    public void setNorth(Double north) {
        this.north = north;
    }
    @Column(name="east")
    public Double getEast() {
        return east;
    }

    public void setEast(Double east) {
        this.east = east;
    }
    @Column(name="south")
    public Double getSouth() {
        return south;
    }

    public void setSouth(Double south) {
        this.south = south;
    }
    @Column(name="west")
    public Double getWest() {
        return west;
    }

    public void setWest(Double west) {
        this.west = west;
    }
}
