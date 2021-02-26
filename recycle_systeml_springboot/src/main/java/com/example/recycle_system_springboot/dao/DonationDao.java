package com.example.recycle_system_springboot.dao;

import com.example.recycle_system_springboot.pojo.entity.Donation;
import com.example.recycle_system_springboot.pojo.vo.DonationVo;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;

public interface DonationDao {
    int deleteByPrimaryKey(Integer donateId);

    int insert(Donation record);

    int insertSelective(Donation record);

    Donation selectByPrimaryKey(Integer donateId);

    int updateByPrimaryKeySelective(Donation record);

    int updateByPrimaryKey(Donation record);

    Page<DonationVo> selectAllByuser(Integer userid,String collectorname,String date,String datepick);

    Page<DonationVo> selectDoingByuser(Integer userid,String collectorname,String date,String datepick);

    Page<DonationVo> selectAllBycollector(Integer collectorid,String username,String date,String datepick);

    Page<DonationVo> selectDoingBycollector(Integer collectorid,String username,String date,String datepick);

    Page<DonationVo> selectAllByadmin(String username,String collectorname,String date,String datepick);

    Page<DonationVo> selectDoingByadmin(String username,String collectorname,String date,String datepick);

    boolean updatecollector(Integer donateId,Integer collectorid);

    boolean updateByDeleteCollertor(Integer donateId);
}
