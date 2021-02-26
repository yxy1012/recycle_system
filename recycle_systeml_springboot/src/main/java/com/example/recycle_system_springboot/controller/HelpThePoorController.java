package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.entity.HelpThePoor;
import com.example.recycle_system_springboot.service.HelpThePoorService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller

public class HelpThePoorController {

    @Resource
    HelpThePoorService helpThePoorService;

    @GetMapping("/allHelpThePoor/{start}/{limit}")
    @ResponseBody
    public PageInfo<HelpThePoor> selectAll(@PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<HelpThePoor> result=helpThePoorService.selectAll(start,limit);
        return result;
    }

    @PostMapping("/updateHelpThePoor")
    @ResponseBody
    public boolean updateHelpThePoor(@RequestBody HelpThePoor helpThePoor){
        boolean result=helpThePoorService.update(helpThePoor);
        return result;
    }

    @DeleteMapping("/deleteHelpThePoor/{id}")
    @ResponseBody
    public boolean deleteHelpThePoor(@PathVariable("id") int id){
        boolean result=helpThePoorService.delete(id);
        return result;
    }

    @PostMapping("/insertHelpThePoor")
    @ResponseBody
    public boolean insertHelpThePoor(@RequestBody HelpThePoor helpThePoor){
        boolean result=helpThePoorService.insert(helpThePoor);
        return result;
    }
}
