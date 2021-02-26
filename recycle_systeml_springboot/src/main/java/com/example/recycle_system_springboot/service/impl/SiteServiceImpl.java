package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.RecycleSiteDao;
import com.example.recycle_system_springboot.pojo.entity.RecycleSite;
import com.example.recycle_system_springboot.service.SiteService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class SiteServiceImpl implements SiteService {
    @Resource
    RecycleSiteDao recycleSiteDao;
    @Resource
    RecycleSite recycleSite;
    @Override
    public boolean updatesite(RecycleSite site) {
        recycleSiteDao.updateByPrimaryKey(site);
        return true;
    }
    //用于派送员注册时的相关信息(业务逻辑：根据派送员注册时填写的站点位置信息，先在站点表中新增一条记录，之后返回出自动生成的主键，用于更新派送员记录中的站点id)
    @Override
    public int siteid(String siteName, Double longitude, Double latitude) {
        recycleSite.setSiteName(siteName);
        recycleSite.setLatitude(latitude);
        recycleSite.setLongitude(longitude);
        recycleSiteDao.insert(recycleSite);
        return recycleSite.getSiteId();
    }

}
