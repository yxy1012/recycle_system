package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.vo.EchartsItemVo;
import com.example.recycle_system_springboot.pojo.vo.RecycleOrdersDetailVo;
import com.example.recycle_system_springboot.service.RecycleOrderDetailService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class RecycleOrderDetailController {
    //查询订单详情
    @Resource
    RecycleOrderDetailService recycleOrderDetailService;

    @GetMapping("/OrdersDetail/{id}")
    @ResponseBody
    public List<RecycleOrdersDetailVo> findOrdersDetail(@PathVariable("id") int id){
        List<RecycleOrdersDetailVo> result = recycleOrderDetailService.findOrdersDetail(id);
        return result;
    }

    @GetMapping("/adminselectCountByitemid")
    @ResponseBody
    public List<EchartsItemVo> adminselectCountByitemid(){
        List<EchartsItemVo> result = recycleOrderDetailService.adminselectCountByitemid();
        return result;
    }
    @GetMapping("/userselectCountByitemid/{id}")
    @ResponseBody
    public List<EchartsItemVo> userselectCountByitemid(@PathVariable("id") int id){
        List<EchartsItemVo> result = recycleOrderDetailService.userselectCountByitemid(id);
        return result;
    }
    @GetMapping("/collectorselectCountByitemid/{id}")
    @ResponseBody
    public List<EchartsItemVo> collectorselectCountByitemid(@PathVariable("id") int id){
        List<EchartsItemVo> result = recycleOrderDetailService.collectorselectCountByitemid(id);
        return result;
    }

}
