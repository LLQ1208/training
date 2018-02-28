package com.acsm.training.dao;

import com.acsm.training.model.Evaluation;
import com.acsm.training.model.page.EvalLevel;
import org.aspectj.apache.bcel.classfile.annotation.ElementValue;

import java.util.List;

/**
 * Created by lq on 2018/2/28.
 */
public interface EvaluationDao extends BaseDao<ElementValue>{

    List<Evaluation> queryListByType(int type);
}
