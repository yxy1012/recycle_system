package com.example.recycle_system_springboot.pojo.dto;

import com.example.recycle_system_springboot.pojo.entity.RecycleOrders;
import com.example.recycle_system_springboot.pojo.vo.ItemVo;
import lombok.Data;

import java.util.List;

@Data
public class OrderDto extends RecycleOrders {
    private Integer userId;

    private Integer recycleOrderId;

    private List<ItemVo> tableData;
}
