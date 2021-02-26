package com.example.recycle_system_springboot.pojo.vo;

import lombok.Data;

@Data
public class ItemManageVo {
    private Integer itemId;

    private Integer itemTypeId;

    private String itemName;

    private Double itemPrice;

    private String itemPic;

    private String itemTypeName;
}
