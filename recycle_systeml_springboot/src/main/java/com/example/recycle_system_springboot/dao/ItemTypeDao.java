package com.example.recycle_system_springboot.dao;

import com.example.recycle_system_springboot.pojo.entity.Item;
import com.example.recycle_system_springboot.pojo.entity.ItemType;

import java.util.List;

public interface ItemTypeDao {
    int deleteByPrimaryKey(Integer itemTypeId);

    int insert(ItemType record);

    int insertSelective(ItemType record);

    ItemType selectByPrimaryKey(Integer itemTypeId);

    int updateByPrimaryKeySelective(ItemType record);

    int updateByPrimaryKey(ItemType record);

    List<ItemType> selectAll();

    ItemType selectByName(String itemtypename);
}
