package com.acsm.training.dao.impl;

import com.acsm.training.dao.ClassEvaluateDao;
import com.acsm.training.model.ClassEvaluate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author : RedCometJ
 * @Description :
 * @Date : Create in 19:24 2018/2/28
 */
@Repository("ClassEvaluateDao")
public class ClassEvaluateDaoImpl extends BaseDaoImpl<ClassEvaluate> implements ClassEvaluateDao {

    @Override
    public ClassEvaluate queryById(Integer id) {
        return null;
    }

    @Override
    public List<Object[]> queryByNameByPhoneById(Integer id, String name, String phone) {
        StringBuffer sql = new StringBuffer();
        sql.append(" select count(*),id from class_evaluate where course_schedule_id = ").append(id);
        sql.append(" and student_name = '").append(name).append("'");
        sql.append(" and student_phone = '").append(phone).append("'");
        return this.queryBySql(sql.toString());
    }
}
