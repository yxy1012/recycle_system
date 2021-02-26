package com.example.recycle_system_springboot.dao;

import com.example.recycle_system_springboot.pojo.entity.Evaluation;

public interface EvaluationDao {
    int deleteByPrimaryKey(Integer evaluationId);

    int insert(Evaluation record);

    int insertSelective(Evaluation record);

    Evaluation selectByPrimaryKey(Integer evaluationId);

    int updateByPrimaryKeySelective(Evaluation record);

    int updateByPrimaryKey(Evaluation record);
}