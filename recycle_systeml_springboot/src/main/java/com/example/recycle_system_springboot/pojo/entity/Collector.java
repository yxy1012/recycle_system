package com.example.recycle_system_springboot.pojo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * collector
 * @author
 */
@Data
@Component
public class Collector implements Serializable {
    private Integer collectorPersonId;

    private Integer siteId;

    private String collectorName;

    private String userName;

    private String password;

    private String phone;

    private String idcardNumber;

    private String photo;

    private String userStatus;
}
