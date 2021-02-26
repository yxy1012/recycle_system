package com.example.recycle_system_springboot.dao;

import com.example.recycle_system_springboot.pojo.entity.HelpThePoor;
import com.github.pagehelper.Page;

import java.util.List;

public interface HelpThePoorDao {
    int deleteByPrimaryKey(Integer helpId);

    int insert(HelpThePoor record);

    int insertSelective(HelpThePoor record);

    HelpThePoor selectByPrimaryKey(Integer helpId);

    int updateByPrimaryKeySelective(HelpThePoor record);

    int updateByPrimaryKey(HelpThePoor record);

    Page<HelpThePoor> selectAll();

    List<HelpThePoor> selectAllHelp();
}
