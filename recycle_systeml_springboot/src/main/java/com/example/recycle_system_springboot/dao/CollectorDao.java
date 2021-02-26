package com.example.recycle_system_springboot.dao;

import com.example.recycle_system_springboot.pojo.entity.Collector;
import com.example.recycle_system_springboot.pojo.vo.CollectorVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectorDao {
    int deleteByPrimaryKey(Integer collectorPersonId);

    int insert(Collector record);

    int insertSelective(Collector record);

    Collector selectByPrimaryKey(Integer collectorPersonId);

    int updateByPrimaryKeySelective(Collector record);

    int updateByPrimaryKey(Collector record);

    Collector selectByUserName(String username);

    CollectorVo selectRecyclesite(Integer collectorPersonId);

    int updatePhoneByPrimaykey(@Param("collectorPersonId") int collectorPersonId, @Param("phone") String phone);

    List<Collector> selectAll();//用于管理员与分配订单给派送员

    Integer selectBycollectorname(String collectorname);

    int updateStatusByPrimaykey(@Param("collectorPersonId") int collectorPersonId,@Param("userStatus") String userStatus);

    List<CollectorVo> selectAllCollector();//用于管理员管理所有的派送员

    int selectBysiteid(int siteid);
}
