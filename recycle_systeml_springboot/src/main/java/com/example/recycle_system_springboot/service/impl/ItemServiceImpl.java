package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.ItemDao;
import com.example.recycle_system_springboot.pojo.entity.Item;
import com.example.recycle_system_springboot.pojo.vo.ItemManageVo;
import com.example.recycle_system_springboot.service.ItemService;
import com.example.recycle_system_springboot.utils.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;

@Service
public class ItemServiceImpl implements ItemService {

    @Resource
    ItemDao itemDao;
    //返回所有的Item信息给管理员(业务逻辑：查询所有的Item信息，同时包括Item属于的Item_Type中的信息)
    @Override
    public PageInfo<ItemManageVo> selectAll(int start, int limit) {
        PageHelper.startPage(start,limit);
        Page<ItemManageVo> list = itemDao.selectAll();
        PageInfo<ItemManageVo> result= new PageInfo<>(list);
        return result;
    }
    //用于添加一个item记录
    @Override
    public Item insert(Item item){
        itemDao.insert(item);
        Item result = itemDao.selectByPrimaryKey(item.getItemId());
        return result;
    }
    //用于删除一个item记录
    @Override
    public int delete(Item item){
        int result=itemDao.deleteByPrimaryKey(item.getItemId());
        return result;
    }
    //用于更新一个item记录
    @Override
    public int update(Item item){
        int result=itemDao.updateByPrimaryKey(item);
        return result;
    }

    @Override
    public String coversUpload(MultipartFile file) {
        String folder = "D:\\杨昕语资料\\javaee\\课程设计\\资源回收项目终版\\recycle_system_vue\\src\\assets\\imagebox";
        File imageFolder = new File(folder);
        File f = new File(imageFolder, StringUtils.getRandomString(6) + file.getOriginalFilename()
                .substring(file.getOriginalFilename().length() - 4));
        if (!f.getParentFile().exists())
            f.getParentFile().mkdirs();
        try {
            file.transferTo(f);
            String imgURL = "http://localhost:8181/api/file/" + f.getName();
            return imgURL;
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
}
