package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.entity.HelpThePoor;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface HelpThePoorService {
    PageInfo<HelpThePoor> selectAll(int start, int limit);

    boolean update(HelpThePoor helpThePoor);

    boolean delete(int id);

    boolean insert(HelpThePoor helpThePoor);

}
