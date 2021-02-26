package com.example.recycle_system_springboot.pojo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.Date;

/**
 * recycle_orders
 * @author
 */
@Data
@Component
public class RecycleOrders implements Serializable {
    //订单实体类
    private Integer recycleOrderId;

    private Integer addressId;

    private Integer collectorPersonId;

    private Integer evaluationId;

    private Integer userId;

    private String scheduledTime;

    private String finishedTime;

    private Double totalAmount;

    private static final long serialVersionUID = 1L;

}
