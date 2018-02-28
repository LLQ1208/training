package com.acsm.training.dao.impl;/**
 * Created by lq on 2018/2/28.
 */

import com.acsm.training.dao.BaseDao;
import com.acsm.training.dao.TeacherEvalDao;
import com.acsm.training.model.TeacherEval;
import org.springframework.stereotype.Repository;

/**
 * @Author lianglinqiang
 * @create 2018-02-28
 */
@Repository("teacherEvalDao")
public class TeacherEvalDaoImpl extends BaseDaoImpl<TeacherEval> implements TeacherEvalDao{
    @Override
    public TeacherEval queryByPhone(String phone) {
        String hql ="from TeacherEval where phone=?";
        return (TeacherEval) this.Queryobject(hql, phone);
    }
}
