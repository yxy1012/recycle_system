package com.example.recycle_system_springboot.pojo.vo;

import lombok.Data;

@Data
public class OrdersVo {
    private Integer recycleOrderId;

    private int addressId;

    private String addressDetails;

    private String collectorName;

    private String username;

    private Integer evaluationId;

    private String scheduledTime;

    private String finishedTime;

    private String phone;

    private Double totalAmount;
}
