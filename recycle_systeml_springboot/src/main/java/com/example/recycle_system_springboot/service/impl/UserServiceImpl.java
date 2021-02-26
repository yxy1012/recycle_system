package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.HelpThePoorDao;
import com.example.recycle_system_springboot.dao.UserDao;
import com.example.recycle_system_springboot.pojo.entity.HelpThePoor;
import com.example.recycle_system_springboot.pojo.entity.User;
import com.example.recycle_system_springboot.pojo.vo.UserVo;
import com.example.recycle_system_springboot.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    UserDao userDao;
    @Resource
    HelpThePoorDao helpThePoorDao;
    //查询出当前用户的所有地址信息(业务逻辑：根据用户的userid查询其相应的地址信息)
    @Override
    public UserVo userfindAlladdress(int id) {
        UserVo userVo = userDao.selectAddress(id);
        return userVo;
    }
    //用于用户更新手机号
    @Override
    public boolean updatePhone(int id, String phone) {
        userDao.updatePhoneByPrimaryKey(id,phone);
        return true;
    }
    //用于在填写捐赠订单中用户选择捐赠的地点的信息(业务逻辑：返回所有的已启用的捐赠地点的信息)
    @Override
    public List<HelpThePoor> userfindAllHelp() {
        List<HelpThePoor> list=helpThePoorDao.selectAllHelp();
        return list;
    }
    //查询所有的用户数量
    @Override
    public int getsum() {
        int result=userDao.selectSum();
        return result;
    }

    @Override
    public PageInfo<User> selectAll(int start,int limit) {
        PageHelper.startPage(start,limit);
        Page<User> list= userDao.selectAll();
        PageInfo<User> result = new PageInfo<>(list);
        return result;
    }
}
