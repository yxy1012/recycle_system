package com.example.recycle_system_springboot.pojo.entity;

import java.io.Serializable;

/**
 * evaluation
 * @author 
 */
public class Evaluation implements Serializable {
    private Integer evaluationId;

    private String evaluationDetails;

    private Integer evaluationScore;

    private static final long serialVersionUID = 1L;

    public Integer getEvaluationId() {
        return evaluationId;
    }

    public void setEvaluationId(Integer evaluationId) {
        this.evaluationId = evaluationId;
    }

    public String getEvaluationDetails() {
        return evaluationDetails;
    }

    public void setEvaluationDetails(String evaluationDetails) {
        this.evaluationDetails = evaluationDetails;
    }

    public Integer getEvaluationScore() {
        return evaluationScore;
    }

    public void setEvaluationScore(Integer evaluationScore) {
        this.evaluationScore = evaluationScore;
    }
}