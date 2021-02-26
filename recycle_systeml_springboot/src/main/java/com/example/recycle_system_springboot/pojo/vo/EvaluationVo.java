package com.example.recycle_system_springboot.pojo.vo;

import com.example.recycle_system_springboot.pojo.entity.Evaluation;
import lombok.Data;

@Data
public class EvaluationVo extends Evaluation {
    private Integer donateId;

    private Integer recycleOrderId;
}
