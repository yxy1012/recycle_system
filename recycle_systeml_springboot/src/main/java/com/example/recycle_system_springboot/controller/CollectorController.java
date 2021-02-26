package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.dto.CollectorRegisterDto;
import com.example.recycle_system_springboot.pojo.entity.Collector;
import com.example.recycle_system_springboot.pojo.vo.CollectorVo;
import com.example.recycle_system_springboot.pojo.vo.UserVo;
import com.example.recycle_system_springboot.service.CollectorService;
import com.example.recycle_system_springboot.service.SiteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class CollectorController {
    @Resource
    CollectorService collectorService;
    @Resource
    SiteService siteService;
    @GetMapping("/collectorInfo/{id}")
    @ResponseBody
    public CollectorVo userfindAlladdress(@PathVariable("id") int id){//查找
        CollectorVo collectorVo = collectorService.selctCollectorInfo(id);
        return collectorVo;
    }

    @PostMapping("/collectorupdatePhone/{id}/{phone}")
    @ResponseBody
    public boolean updatePhone(@PathVariable("id") int id,@PathVariable("phone") String phone){
        boolean result=collectorService.updatePhone(id,phone);
        return result;
    }

    @GetMapping("/allCollector")
    @ResponseBody
    public List<Collector> selectAll(){
        List<Collector> result=collectorService.selectAll();
        return result;
    }

    //派送员注册
    @PostMapping("/register/collector")
    @ResponseBody
    public boolean collectorRegister(@RequestBody CollectorRegisterDto c){
        int siteid= siteService.siteid(c.getSiteName(),c.getLongitude(),c.getLatitude());
        boolean result=collectorService.registerCollector(siteid,c);
        return result;
    }

    @GetMapping("/allCollectorstatus")
    @ResponseBody
    public List<CollectorVo> selectAllcollector(){
        List<CollectorVo> result=collectorService.selctAllCollector();
        return result;
    }

    //管理员更改派送员状态
    @PostMapping("/changeStatus/{id}/{status}")
    @ResponseBody
    public boolean changeStatus(@PathVariable("id") int id,@PathVariable("status") String status){
        boolean result = collectorService.changeStatus(id,status);
        return result;
    }
}
