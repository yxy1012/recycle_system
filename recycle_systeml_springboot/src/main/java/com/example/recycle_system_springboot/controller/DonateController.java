package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.entity.Donation;
import com.example.recycle_system_springboot.pojo.query.AdminReqirueQuery;
import com.example.recycle_system_springboot.pojo.query.CollectorRequireQuery;
import com.example.recycle_system_springboot.pojo.query.UserRequireQuery;
import com.example.recycle_system_springboot.pojo.vo.DonationVo;
import com.example.recycle_system_springboot.pojo.vo.EvaluationVo;
import com.example.recycle_system_springboot.service.DonationService;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@Controller
public class DonateController {
    @Resource
    DonationService donationService;

    @PostMapping("/placeandonation")
    @ResponseBody
    public Boolean PlaceAnOrder(@RequestBody Donation order){
        Boolean result=donationService.placeAnDonation(order);
        return result;
    }

    @PostMapping("/userfindalldonate/{start}/{limit}")
    @ResponseBody
    public PageInfo<DonationVo> userfindAllDonate(@RequestBody UserRequireQuery u, @PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<DonationVo> result = donationService.selectAllbyuser(u.getId(),u.getCollectorname(),u.getDatebyday(),u.getDatebymonth(),u.getDatepick(),start,limit);
        return result;
    }

    @PostMapping("/userfinddoingdonate/{start}/{limit}")
    @ResponseBody
    public PageInfo<DonationVo> userfindDoingDonate(@RequestBody UserRequireQuery u, @PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<DonationVo> result = donationService.selectDoingbyuser(u.getId(),u.getCollectorname(),u.getDatebyday(),u.getDatebymonth(),u.getDatepick(),start,limit);
        return result;
    }

    @PostMapping("/collectorfindalldonate/{start}/{limit}")
    @ResponseBody
    public PageInfo<DonationVo> collectorfindAllDonate(@RequestBody CollectorRequireQuery c, @PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<DonationVo> result = donationService.selectAllbycollector(c.getId(),c.getUsername(),c.getDatebyday(),c.getDatebymonth(),c.getDatepick(),start,limit);
        return result;
    }

    @PostMapping("/collectorfinddoingdonate/{start}/{limit}")
    @ResponseBody
    public PageInfo<DonationVo> collectorfinddoingDonate(@RequestBody CollectorRequireQuery c, @PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<DonationVo> result = donationService.selectDoingbycollector(c.getId(),c.getUsername(),c.getDatebyday(),c.getDatebymonth(),c.getDatepick(),start,limit);
        return result;
    }

    @PostMapping("/adminfindalldonate/{start}/{limit}")
    @ResponseBody
    public PageInfo<DonationVo> adminfindAllDonate(@RequestBody AdminReqirueQuery a, @PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<DonationVo> result = donationService.selectAllbyadmin(a.getUsername(),a.getCollectorname(),a.getDatebyday(),a.getDatebymonth(),a.getDatepick(),start,limit);
        return result;
    }
    @PostMapping("/adminfinddoingdonate/{start}/{limit}")
    @ResponseBody
    public PageInfo<DonationVo> userfindAllDonate(@RequestBody AdminReqirueQuery a, @PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<DonationVo> result = donationService.selectDoingbyadmin(a.getUsername(),a.getCollectorname(),a.getDatebyday(),a.getDatebymonth(),a.getDatepick(),start,limit);
        return result;
    }

    @GetMapping("/donateplacecollector/{donateId}/{collectorName}")
    @ResponseBody
    public int placeCollector(@PathVariable("donateId") int donateId,@PathVariable("collectorName") String collectorName){
        int result=donationService.placecollector(donateId,collectorName);
        return result;
    }

    @PostMapping("/evaluationdonation")
    @ResponseBody
    public Boolean Evaluationdonation(@RequestBody EvaluationVo order){
        Boolean result=donationService.Evaluatedonation(order);
        return result;
    }

    @PostMapping("/confirmdonation")
    @ResponseBody
    public Boolean confirmDonation(@RequestBody Donation order){
        Boolean result=donationService.confirmDonation(order);
        return result;
    }

    //删除订单
    @DeleteMapping("/userdeletedonation/{donateId}")
    @ResponseBody
    public Boolean DeleteDonation(@PathVariable("donateId") int donateId){
        Boolean result=donationService.DeleteDonation(donateId);
        return result;
    }

    @GetMapping("/deleteDonateCollector/{donateId}")
    @ResponseBody
    public boolean DeleteCollector(@PathVariable("donateId") int donateId){
        boolean result=donationService.DeleteCollector(donateId);
        return result;
    }
}
