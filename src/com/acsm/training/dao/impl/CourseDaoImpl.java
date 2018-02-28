package com.acsm.training.dao.impl;/**
 * Created by lq on 2018/2/26.
 */

import com.acsm.training.dao.CourseDao;
import com.acsm.training.model.Course;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-02-26
 */
@Repository("courseDao")
public class CourseDaoImpl extends BaseDaoImpl<Course> implements CourseDao {
    @Override
    public List<Course> queryListByScheduleId(int courseScheduleId,Date date) {
        String hql ="from Course where courseScheduleId=:courseScheduleId and deleted=0 and date=:date";
        Session session = this.getSession();
        Query q = session.createQuery(hql);
        q.setInteger("courseScheduleId", courseScheduleId);
        q.setDate("date", date);
        return q.list();
    }

    @Override
    public Course queryById(int id) {
        String hql ="from Course where id=?";
        return (Course) this.Queryobject(hql, id);
    }

    @Override
    public List<Course> queryList(int courseScheduleId) {
        String hql ="from Course where courseScheduleId=? and deleted=0";
        return this.list(hql,courseScheduleId);
    }
}
