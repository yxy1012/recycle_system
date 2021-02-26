package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.entity.RecycleSite;
import com.example.recycle_system_springboot.service.SiteService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Controller

public class SiteController {

    @Resource
    SiteService siteService;

    @PostMapping("/updateSite")
    @ResponseBody
    public boolean updateSite(@RequestBody RecycleSite site){
        boolean result =siteService.updatesite(site);
        return result;
    }
}
