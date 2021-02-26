package com.example.recycle_system_springboot.pojo.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * recycle_site
 * @author
 */
@Data
@Component
public class RecycleSite implements Serializable {
    private Integer siteId;

    private String siteName;

    private Double latitude;

    private Double longitude;

    private static final long serialVersionUID = 1L;

}
