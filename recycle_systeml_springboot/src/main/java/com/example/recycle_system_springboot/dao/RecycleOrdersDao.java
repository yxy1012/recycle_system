package com.example.recycle_system_springboot.dao;

import com.example.recycle_system_springboot.pojo.entity.RecycleOrders;
import com.example.recycle_system_springboot.pojo.vo.*;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecycleOrdersDao {
    int deleteByPrimaryKey(Integer recycleOrderId);

    int insert(RecycleOrders record);

    int insertSelective(RecycleOrders record);

    RecycleOrders selectByPrimaryKey(Integer recycleOrderId);

    int updateByPrimaryKeySelective(RecycleOrders record);

    int updateByPrimaryKey(RecycleOrders record);

    List<EchartsTimeVo> userselectCountbytime(Integer id);

    List<EchartsTimeVo> userselectCountbydate(Integer id);

    List<EchartsTimeVo> adminselectCountbytime();

    List<EchartsTimeVo> adminselectCountbydate();

    List<EchartsTimeVo> adminselectAmountbydate();

    List<EchartsTimeVo> collectorselectCountbytime(Integer collectorid);

    List<EchartsTimeVo> collectorselectCountbydate(Integer collectorid);

    List<EchartsTimeVo> collectorselectAmountbydate(Integer collectorid);

    Page<OrdersVo> selectAllByuser(Integer userid,String collectorname,String date,String datepick);

    Page<OrdersVo> selectDoingByuser(Integer userid,String collectorname,String date,String datepick);

    Page<OrdersVo> selectAllBycollector(Integer collectorid,String username,String date,String datepick);

    Page<OrdersVo> selectDoingBycollector(Integer collectorid,String username,String date,String datepick);

    Page<OrdersVo> selectAllByadmin(String username,String collectorname,String date,String datepick);

    Page<OrdersVo> selectDoingByadmin(String username,String collectorname,String date,String datepick);

    boolean updateCollector(@Param("recycleOrderId") int recycleOrderId, @Param("collectorId") int collectorId);

    Double selectsumamount();

    boolean updateByDeleteCollertor(Integer recycleOrderId);
}
