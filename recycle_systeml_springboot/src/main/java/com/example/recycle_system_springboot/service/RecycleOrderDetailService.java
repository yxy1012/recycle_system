package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.vo.EchartsItemVo;
import com.example.recycle_system_springboot.pojo.vo.RecycleOrdersDetailVo;

import java.util.List;

public interface RecycleOrderDetailService {
    List<RecycleOrdersDetailVo> findOrdersDetail(int id);

    List<EchartsItemVo> adminselectCountByitemid();

    List<EchartsItemVo> userselectCountByitemid(int id);

    List<EchartsItemVo> collectorselectCountByitemid(int id);
}
