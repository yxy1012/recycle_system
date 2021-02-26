package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.entity.ItemType;

import java.util.List;

public interface ItemTypeService {
    List<ItemType> selectAll();

    boolean update(ItemType itemType);

    ItemType insert(ItemType itemType);
}
