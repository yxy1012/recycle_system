package com.example.recycle_system_springboot.pojo.vo;

import com.example.recycle_system_springboot.pojo.entity.Collector;
import lombok.Data;

@Data
public class CollectorVo extends Collector {
    private String siteName;
}
