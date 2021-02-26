package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.entity.RecycleSite;

public interface SiteService {
    boolean updatesite(RecycleSite site);

    int siteid(String siteName,Double longitude,Double latitude);
}
