package com.example.recycle_system_springboot.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * item
 * @author
 */
@Data
public class Item implements Serializable {
    private Integer itemId;

    private Integer itemTypeId;

    private String itemName;

    private Double itemPrice;

    private String itemPic;
}
