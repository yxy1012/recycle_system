package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.entity.Evaluation;
import com.example.recycle_system_springboot.pojo.entity.HelpThePoor;
import com.example.recycle_system_springboot.service.EvaluationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Controller
public class EvaluationController {
    @Resource
    EvaluationService evaluationService;

    @GetMapping("/Evaluation/{id}")
    @ResponseBody
    public Evaluation findEvaluation(@PathVariable("id") int id){//查找
        Evaluation result = evaluationService.findEvaluation(id);
        return result;
    }
}
