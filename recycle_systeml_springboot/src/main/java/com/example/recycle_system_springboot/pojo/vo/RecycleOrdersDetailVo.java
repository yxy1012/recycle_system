package com.example.recycle_system_springboot.pojo.vo;

import lombok.Data;

@Data
public class RecycleOrdersDetailVo {
    //返回给前端订单详情信息类
    private String itemName;

    private Double itemPrice;

    private Double quantity;

    private Double sum;
}
