package com.example.recycle_system_springboot.pojo.dto;

import lombok.Data;

@Data
public class CollectorRegisterDto {
    private String collectorName;

    private String userName;

    private String password;

    private String repassword;

    private String phone;

    private String idcardNumber;

    private String siteName;

    private Double latitude;

    private Double longitude;
}
