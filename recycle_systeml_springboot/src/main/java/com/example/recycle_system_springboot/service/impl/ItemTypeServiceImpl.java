package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.ItemTypeDao;
import com.example.recycle_system_springboot.pojo.entity.ItemType;
import com.example.recycle_system_springboot.service.ItemTypeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
@Service
public class ItemTypeServiceImpl implements ItemTypeService {

    @Resource
    ItemTypeDao itemTypeDao;
    //查询所有的itemtype信息并返回给管理员（业务逻辑:查询所有）
    @Override
    public List<ItemType> selectAll() {
        List<ItemType> result= itemTypeDao.selectAll();
        return result;
    }
    //更新一条itemtype记录
    @Override
    public boolean update(ItemType itemType) {
        itemTypeDao.updateByPrimaryKey(itemType);
        return true;
    }
    //添加一条itemtype记录
    @Override
    public ItemType insert(ItemType itemType) {
        itemTypeDao.insert(itemType);
        ItemType result=itemTypeDao.selectByName(itemType.getItemTypeName());
        return result;
    }
}
