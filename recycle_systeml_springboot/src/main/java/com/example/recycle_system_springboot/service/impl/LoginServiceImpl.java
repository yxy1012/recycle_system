package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.AdminDao;
import com.example.recycle_system_springboot.dao.CollectorDao;
import com.example.recycle_system_springboot.dao.UserDao;
import com.example.recycle_system_springboot.pojo.dto.LoginMsg;
import com.example.recycle_system_springboot.pojo.entity.Admin;
import com.example.recycle_system_springboot.pojo.entity.Collector;
import com.example.recycle_system_springboot.pojo.entity.User;
import com.example.recycle_system_springboot.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

@Service
public class LoginServiceImpl implements LoginService {

    @Resource
    UserDao userDao;
    @Resource
    AdminDao adminDao;
    @Resource
    CollectorDao collectorDao;
    @Resource
    LoginMsg msg;
    //检查登录(业务逻辑：
    // 1.用户根据输入的用户名与密码去数据库中查询有无相关记录，若输入正确则返回login为true，并且返回userid和username给前端
    // 2.派送员根据输入的用户名与密码去数据库中进行查询，同时查询其启用信息，若是启用为F，意味着管理员还未审核，若输入正确并且启用为T，怎返回login为true
    // 3.管理员根据输入的用户名与密码去数据库中进行查询，若输入正确在返回true，返回adminid和adminname给前端)
    @Override
    public LoginMsg CheckLogin(int identity, String username, String password) {
        if(identity==1){
            User user=userDao.selectByUserName(username);
            if(user!=null&&user.getPassword().equals(password)){
                msg.setId(user.getUserId());
                msg.setIdentity(identity);
                msg.setName(user.getUserName());
                msg.setLogin(true);
            }
            else {
                msg.setLogin(false);
            }
        }
        else if(identity==2){
            Collector collector = collectorDao.selectByUserName(username);
            if (collector!=null&&collector.getPassword().equals(password)&&collector.getUserStatus().equals("T")) {
                msg.setId(collector.getCollectorPersonId());
                msg.setIdentity(identity);
                msg.setName(collector.getUserName());
                msg.setLogin(true);
            } else if((collector!=null&&collector.getPassword().equals(password)&&collector.getUserStatus().equals("F"))){
                msg.setName("审核中");
                msg.setLogin(true);
            }else {
                msg.setLogin(false);
            }
        }
        else if(identity==3) {
            Admin admin=adminDao.selectByUserName(username);
            if(admin!=null&&admin.getPassword().equals(password)){
                msg.setIdentity(identity);
                msg.setId(admin.getAdminId());
                msg.setName(admin.getUserName());
                msg.setLogin(true);
            }else {
                msg.setLogin(false);
            }
        }
        return msg;
    }
}
