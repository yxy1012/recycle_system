package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.entity.Item;
import com.example.recycle_system_springboot.pojo.vo.ItemManageVo;
import com.github.pagehelper.PageInfo;
import org.springframework.web.multipart.MultipartFile;

public interface ItemService {
    PageInfo<ItemManageVo> selectAll(int start, int limit);

    Item insert(Item item);

    int delete(Item item);

    int update(Item item);

    String coversUpload(MultipartFile file);
}
