package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.RecycleOrderDetailDao;
import com.example.recycle_system_springboot.pojo.vo.EchartsItemVo;
import com.example.recycle_system_springboot.pojo.vo.RecycleOrdersDetailVo;
import com.example.recycle_system_springboot.service.RecycleOrderDetailService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class RecycleOrderDetailServiceImpl implements RecycleOrderDetailService {
    @Resource
    RecycleOrderDetailDao recycleOrderDetailDao;
    //根据主键查询所有的订单详情信息
    @Override
    public List<RecycleOrdersDetailVo> findOrdersDetail(int id) {
        List<RecycleOrdersDetailVo> result = recycleOrderDetailDao.selectDetailByRecycleOrderId(id);
        for(RecycleOrdersDetailVo recycleOrdersDetailVo:result){
            recycleOrdersDetailVo.setSum(recycleOrdersDetailVo.getQuantity()*recycleOrdersDetailVo.getItemPrice());
        }
        return result;
    }
    //用于Echarts的数据统计(业务逻辑：管理员根据物品查询出其全部的数量信息)
    @Override
    public List<EchartsItemVo> adminselectCountByitemid(){
        List<EchartsItemVo> result = recycleOrderDetailDao.adminselectCountByitemid();
        return result;
    }
    //用于Echarts的数据统计(业务逻辑：用户根据物品查询出其下订单的的数量信息)
    @Override
    public List<EchartsItemVo> userselectCountByitemid(int id){
        List<EchartsItemVo> result = recycleOrderDetailDao.userselectCountByitemid(id);
        return result;
    }
    //用于Echarts的数据统计(业务逻辑：派送员根据物品查询出其全部的数量信息)
    @Override
    public List<EchartsItemVo> collectorselectCountByitemid(int id){
        List<EchartsItemVo> result = recycleOrderDetailDao.collectorselectCountByitemid(id);
        return result;
    }
}
