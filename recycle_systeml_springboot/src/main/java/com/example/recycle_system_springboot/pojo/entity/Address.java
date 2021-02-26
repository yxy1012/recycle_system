package com.example.recycle_system_springboot.pojo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * address
 * @author
 */
@Data
@Component
public class Address implements Serializable {
    //地址实体类
    private Integer addressId;

    private Integer userId;

    private String addressDetails;

    private Double latitude;

    private Double longitude;

    private static final long serialVersionUID = 1L;

}
