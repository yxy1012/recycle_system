package com.example.recycle_system_springboot.pojo.vo;

import com.example.recycle_system_springboot.pojo.entity.Item;
import lombok.Data;

@Data
public class ItemRecycleVo extends Item {
    private Double quantity;
}
