package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.CollectorDao;
import com.example.recycle_system_springboot.pojo.dto.CollectorRegisterDto;
import com.example.recycle_system_springboot.pojo.entity.Collector;
import com.example.recycle_system_springboot.pojo.vo.CollectorVo;
import com.example.recycle_system_springboot.service.CollectorService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CollectorServiceImpl implements CollectorService {
    @Resource
    CollectorDao collectorDao;
    @Resource
    Collector collector;
    //查询派送员的所有信息，包括他的站点名字信息
    @Override
    public CollectorVo selctCollectorInfo(int collectorid) {
        CollectorVo collectorVo=collectorDao.selectRecyclesite(collectorid);
        return collectorVo;
    }
    //派送员修改手机号
    @Override
    public boolean updatePhone(int id, String phone) {
        collectorDao.updatePhoneByPrimaykey(id,phone);
        return true;
    }
    //查找所有的派送员,用于管理员给订单分配派送员时(业务逻辑:查询出所有已启用的派送员，提供给管理员供其分配订单)
    @Override
    public List<Collector> selectAll() {
        List<Collector> result=collectorDao.selectAll();
        return result;
    }
    //派送员的注册(业务逻辑：注册派送员时，设置派送员的状态为F，意义为未启用的状态，需要管理员审核之后将派送员设置为启用)
    @Override
    public boolean registerCollector(int siteid, CollectorRegisterDto c) {
        collector.setSiteId(siteid);
        collector.setUserName(c.getUserName());
        collector.setCollectorName(c.getCollectorName());
        collector.setPassword(c.getPassword());
        collector.setPhone(c.getPhone());
        collector.setIdcardNumber(c.getIdcardNumber());
        collector.setUserStatus("F");
        if(collectorDao.selectByUserName(collector.getUserName())==null)
        {
            collectorDao.insert(collector);
            return true;
        }
        else
            return false;
    }
    //用于管理员对派送员状态的更改(业务逻辑：管理员更改派送员的状态为T或者F，分别为启用和未启用的状态)
    @Override
    public boolean changeStatus(int id,String status) {
        collectorDao.updateStatusByPrimaykey(id,status);
        return true;
    }
    //查询所有的派送员，用于管理员对派送员进行管理(业务逻辑:返回所有的派送员信息给管理员)
    @Override
    public List<CollectorVo> selctAllCollector() {
        List<CollectorVo> result=collectorDao.selectAllCollector();
        return result;
    }
}
