package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.entity.Item;
import com.example.recycle_system_springboot.pojo.entity.ItemType;
import com.example.recycle_system_springboot.service.ItemTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class ItemTypeController {

    @Resource
    ItemTypeService itemTypeService;

    @GetMapping("/allItemType")
    @ResponseBody
    public List<ItemType> selctAll(){
        List<ItemType> result=itemTypeService.selectAll();
        return result;
    }

    @PostMapping("/updateItemType")
    @ResponseBody
    public boolean updateItemType(@RequestBody ItemType itemType){
        itemTypeService.update(itemType);
        return true;
    }

    @PostMapping("/insertItemType")
    @ResponseBody
    public ItemType insert(@RequestBody ItemType itemType){
        ItemType result=itemTypeService.insert(itemType);
        return result;
    }
}
