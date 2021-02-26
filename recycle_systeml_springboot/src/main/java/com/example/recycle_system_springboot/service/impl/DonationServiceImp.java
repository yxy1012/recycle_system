package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.*;
import com.example.recycle_system_springboot.pojo.entity.Address;
import com.example.recycle_system_springboot.pojo.entity.Donation;
import com.example.recycle_system_springboot.pojo.entity.RecycleSite;
import com.example.recycle_system_springboot.pojo.vo.DonationVo;
import com.example.recycle_system_springboot.pojo.vo.EvaluationVo;
import com.example.recycle_system_springboot.service.DonationService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DonationServiceImp implements DonationService {
    @Resource
    DonationDao donationDao;
    @Resource
    CollectorDao collectorDao;
    @Resource
    EvaluationDao evaluationDao;
    @Resource
    Donation donation;
    @Resource
    AddressDao addressDao;
    @Resource
    RecycleSiteDao recycleSiteDao;
    @Override
    //增加一个捐赠订单
    public boolean placeAnDonation(Donation order) {
        boolean result=false;
        Address address=addressDao.selectByPrimaryKey(order.getAddressId());
        double j=address.getLongitude();
        double w=address.getLatitude();
        List<RecycleSite> recycleSites=recycleSiteDao.selectAll();
        RecycleSite recycleSite=recycleSites.get(0);
        double min=(recycleSite.getLongitude()-j)*(recycleSite.getLongitude()-j)+(recycleSite.getLatitude()-w)*(recycleSite.getLatitude()-w);
        int recyclesiteid=recycleSite.getSiteId();
        for(RecycleSite r:recycleSites){
            if(((r.getLongitude()-j)*(r.getLongitude()-j)+(r.getLatitude()-w)*(r.getLatitude()-w))<min){
                min=(r.getLongitude()-j)*(r.getLongitude()-j)+(r.getLatitude()-w)*(r.getLatitude()-w);
                recyclesiteid=r.getSiteId();
            }
        }
        int collectorid=collectorDao.selectBysiteid(recyclesiteid);
        order.setCollectorPersonId(collectorid);
        int i=donationDao.insert(order);
        if(i>0){
            result=true;
        }
        return result;
    }
    //用户查询所有的捐赠信息(业务逻辑：根据用户提供的相关信息对所有的相关信息进行查询，运用动态sql，动态的根据用户的需求进行查询)
    @Override
    public PageInfo<DonationVo> selectAllbyuser(int userid, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<DonationVo> list =donationDao.selectAllByuser(userid,collectorname,datebyday,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<DonationVo> list =donationDao.selectAllByuser(userid,collectorname,datebymonth,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }
    }
    //用户查询进行中捐赠信息(业务逻辑：根据用户提供的相关信息对所有的相关信息进行查询，运用动态sql，动态的根据用户的需求进行查询，正在进行中的订单是根据捐赠订单表中的finished_time为空的记录)
    @Override
    public PageInfo<DonationVo> selectDoingbyuser(int userid, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<DonationVo> list =donationDao.selectDoingByuser(userid,collectorname,datebyday,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<DonationVo> list =donationDao.selectDoingByuser(userid,collectorname,datebymonth,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }
    }
    //与用户类似
    @Override
    public PageInfo<DonationVo> selectAllbycollector(int collectorid, String username, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<DonationVo> list =donationDao.selectAllBycollector(collectorid,username,datebyday,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<DonationVo> list =donationDao.selectAllBycollector(collectorid,username,datebymonth,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<DonationVo> selectDoingbycollector(int collectorid, String username, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<DonationVo> list =donationDao.selectDoingBycollector(collectorid,username,datebyday,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<DonationVo> list =donationDao.selectDoingBycollector(collectorid,username,datebymonth,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<DonationVo> selectAllbyadmin(String username, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<DonationVo> list =donationDao.selectAllByadmin(username,collectorname,datebyday,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<DonationVo> list =donationDao.selectAllByadmin(username,collectorname,datebyday,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }
    }

    @Override
    public PageInfo<DonationVo> selectDoingbyadmin(String username, String collectorname, String datebyday, String datebymonth, String datepick, int start, int limit) {
        PageHelper.startPage(start,limit);
        if(datepick.equals("day")){
            Page<DonationVo> list =donationDao.selectDoingByadmin(username,collectorname,datebyday,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }else{
            Page<DonationVo> list =donationDao.selectDoingByadmin(username,collectorname,datebyday,datepick);
            PageInfo<DonationVo> result= new PageInfo<>(list);
            return result;
        }
    }
    //用于管理员给捐赠订单安排一个派送员(业务逻辑：根据前端返回的派送员名字，查找到派送员的主键，再将它更新到捐赠订单表中)
    @Override
    public int placecollector(int donateId, String collectorName) {
        int collectorid=collectorDao.selectBycollectorname(collectorName);
        boolean result=donationDao.updatecollector(donateId,collectorid);
        if(result){
            return donateId;
        }else {
            return 0;
        }
    }
    //用户对捐赠订单进行评价(业务逻辑：先将评价的相关信息添加到数据库表中，根据生成的主键号，更新捐赠订单表中的评价表外键信息)
    @Override
    public Boolean Evaluatedonation(EvaluationVo order) {
        evaluationDao.insert(order);
        donation.setDonateId(order.getDonateId());
        donation.setEvaluationId(order.getEvaluationId());
        donationDao.updateByPrimaryKeySelective(donation);
        return true;
    }
    //派送员对捐赠订单进行确认(业务逻辑：派送员确认捐赠订单时，自动生成当前的时间，并传到后端，更新捐赠订单表中的finished_time)
    @Override
    public Boolean confirmDonation(Donation order) {
        donationDao.updateByPrimaryKeySelective(order);
        return true;
    }
    //用于删除一个捐赠订单(业务逻辑：删除一个捐赠订单表中的记录)
    @Override
    public Boolean DeleteDonation(int donateId) {
        donationDao.deleteByPrimaryKey(donateId);
        return true;
    }

    @Override
    public boolean DeleteCollector(int donateId) {
        boolean result=donationDao.updateByDeleteCollertor(donateId);
        return result;
    }

}
