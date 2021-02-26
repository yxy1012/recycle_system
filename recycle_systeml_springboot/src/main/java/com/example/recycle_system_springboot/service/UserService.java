package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.entity.HelpThePoor;
import com.example.recycle_system_springboot.pojo.entity.User;
import com.example.recycle_system_springboot.pojo.vo.UserVo;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface UserService {
    UserVo userfindAlladdress(int id);

    boolean updatePhone(int id,String phone);

    List<HelpThePoor> userfindAllHelp();

    int getsum();

    PageInfo<User> selectAll(int start,int limit);
}
