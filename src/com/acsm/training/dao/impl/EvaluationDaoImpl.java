package com.acsm.training.dao.impl;/**
 * Created by lq on 2018/2/28.
 */

import com.acsm.training.dao.EvaluationDao;
import com.acsm.training.model.Evaluation;
import org.aspectj.apache.bcel.classfile.annotation.ElementValue;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author lianglinqiang
 * @create 2018-02-28
 */
@Repository
public class EvaluationDaoImpl extends BaseDaoImpl<ElementValue> implements EvaluationDao {

    @Override
    public List<Evaluation> queryListByType(int type) {
        /*String hql = "from ElementValue where type=? and deleted=0";
        return this.list(hql,type);*/
        return null;
    }
}
