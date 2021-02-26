package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.HelpThePoorDao;
import com.example.recycle_system_springboot.pojo.entity.HelpThePoor;
import com.example.recycle_system_springboot.service.HelpThePoorService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class HelpThePoorServiceImpl implements HelpThePoorService {
    @Resource
    HelpThePoorDao helpThePoorDao;
    //提供所有的扶贫信息给管理员，供管理员进行管理(业务逻辑：查询所有信息)
    @Override
    public PageInfo<HelpThePoor> selectAll(int start, int limit) {
        PageHelper.startPage(start,limit);
        Page<HelpThePoor> list= helpThePoorDao.selectAll();
        PageInfo<HelpThePoor> result =new PageInfo<>(list);
        return result;
    }
    //用于管理员更新扶贫信息表
    @Override
    public boolean update(HelpThePoor helpThePoor) {
        helpThePoorDao.updateByPrimaryKey(helpThePoor);
        return true;
    }
    //用于管理员删除扶贫信息表
    @Override
    public boolean delete(int id) {
        helpThePoorDao.deleteByPrimaryKey(id);
        return true;
    }
    //用于管理员添加扶贫信息表
    @Override
    public boolean insert(HelpThePoor helpThePoor) {
        helpThePoorDao.insert(helpThePoor);
        return true;
    }
}
