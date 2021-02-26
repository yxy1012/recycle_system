package com.example.recycle_system_springboot.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * item_type
 * @author
 */
@Data
public class ItemType implements Serializable {
    private Integer itemTypeId;

    private String itemTypeName;

    private static final long serialVersionUID = 1L;
}
