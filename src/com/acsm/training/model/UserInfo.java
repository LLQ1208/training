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
@Table(name="user_info")
public class UserInfo implements Serializable{
    private static final long serialVersionUID = -944383816247441936L;
    private Integer id;
    private Integer deleted;
    private String password;
    private String userName;
    private Integer userType;
    private Integer baseId;
    private Integer baseInfoId;
    private Base base;
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
    @Column(name="password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @Column(name="user_name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @Column(name="user_type")
    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
    }
    @Column(name="base_id")
    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }

    @Column(name="base_info_id")
    public Integer getBaseInfoId() {
        return baseInfoId;
    }

    public void setBaseInfoId(Integer baseInfoId) {
        this.baseInfoId = baseInfoId;
    }

    @Transient
    public Base getBase() {
        return base;
    }

    public void setBase(Base base) {
        this.base = base;
    }
}

