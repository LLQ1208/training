package com.acsm.training.model;/**
 * Created by lq on 2018/2/28.
 */

import javax.persistence.*;
import java.io.Serializable;

/**
 * @Author lianglinqiang
 * @create 2018-02-28
 */
@Entity
@Table(name="evaluation")
public class Evaluation implements Serializable{
    private static final long serialVersionUID = 971982114909885116L;

    private Integer id;
    private Integer deleted;
    private String message;
    private Integer type;
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
    @Column(name="deleted")
    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
    @Column(name="message")
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    @Column(name="base_id")
    public Integer getBaseId() {
        return baseId;
    }

    public void setBaseId(Integer baseId) {
        this.baseId = baseId;
    }
    @Column(name="type")
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
