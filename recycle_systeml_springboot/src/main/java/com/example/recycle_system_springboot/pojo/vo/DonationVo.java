package com.example.recycle_system_springboot.pojo.vo;

import lombok.Data;

@Data
public class DonationVo {
    private Integer donateId;

    private String  userName;

    private Integer addressId;

    private String addressDetails;

    private String helpName;

    private String collectorName;

    private Integer evaluationId;

    private String scheduledTime;

    private String finishedTime;

    private String donateDetail;

    private String phone;

}
