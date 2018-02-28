package com.acsm.training.dao;

import com.acsm.training.model.TeacherEval;

/**
 * Created by lq on 2018/2/28.
 */
public interface TeacherEvalDao extends BaseDao<TeacherEval>{

    TeacherEval queryByPhone(String phone);
}
