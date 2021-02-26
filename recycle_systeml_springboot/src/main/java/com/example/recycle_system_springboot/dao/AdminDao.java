package com.example.recycle_system_springboot.dao;

import com.example.recycle_system_springboot.pojo.entity.Admin;
import com.example.recycle_system_springboot.pojo.entity.Collector;

public interface AdminDao {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);

    Admin selectByUserName(String username);
}
