package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.entity.Donation;
import com.example.recycle_system_springboot.pojo.vo.DonationVo;
import com.example.recycle_system_springboot.pojo.vo.EvaluationVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

public interface DonationService {
    boolean placeAnDonation(Donation order);

    PageInfo<DonationVo> selectAllbyuser(int userid,String collectorname,String datebyday,String datebymonth,String datepick,int start,int limit);

    PageInfo<DonationVo> selectDoingbyuser(int userid,String collectorname,String datebyday,String datebymonth,String datepick,int start,int limit);

    PageInfo<DonationVo> selectAllbycollector(int collectorid,String username,String datebyday,String datebymonth,String datepick,int start,int limit);

    PageInfo<DonationVo> selectDoingbycollector(int collectorid,String username,String datebyday,String datebymonth,String datepick,int start,int limit);

    PageInfo<DonationVo> selectAllbyadmin(String username,String collectorname,String datebyday,String datebymonth,String datepick,int start,int limit);

    PageInfo<DonationVo> selectDoingbyadmin(String username,String collectorname,String datebyday,String datebymonth,String datepick,int start,int limit);

    int placecollector(int donateId,String collectorName);

    Boolean Evaluatedonation(EvaluationVo order);

    Boolean confirmDonation(Donation order);

    Boolean DeleteDonation(int donateId);

    boolean DeleteCollector(int donateId);
}
