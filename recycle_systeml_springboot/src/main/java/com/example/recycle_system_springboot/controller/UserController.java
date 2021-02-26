package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.dao.UserDao;
import com.example.recycle_system_springboot.pojo.entity.HelpThePoor;
import com.example.recycle_system_springboot.pojo.entity.User;
import com.example.recycle_system_springboot.pojo.vo.AddressVo;
import com.example.recycle_system_springboot.pojo.vo.UserVo;
import com.example.recycle_system_springboot.service.HelpThePoorService;
import com.example.recycle_system_springboot.service.UserService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Controller
public class UserController {
    //用于查询用户的信息、修改用户的手机号
    @Resource
    UserService userService;
    @GetMapping("/userAlladdress/{id}")
    @ResponseBody
    public UserVo userfindAlladdress(@PathVariable("id") int id){//查找
        UserVo userVo = userService.userfindAlladdress(id);
        return userVo;
    }

    @PostMapping("/userupdatePhone/{id}/{phone}")
    @ResponseBody
    public boolean updatePhone(@PathVariable("id") int id,@PathVariable("phone") String phone){
        boolean result=userService.updatePhone(id,phone);
        return result;
    }

    @GetMapping("/userAllHelp")
    @ResponseBody
    public List<HelpThePoor> userfindAllHelp(){//查找
        List<HelpThePoor> list = userService.userfindAllHelp();
        return list;
    }

    @GetMapping("/usercount")
    @ResponseBody
    public int usercount(){
        int result=userService.getsum();
        return result;
    }

    @GetMapping("/alluser/{start}/{limit}")
    @ResponseBody
    public PageInfo<User> allUser(@PathVariable("start") int start, @PathVariable("limit") int limit){
        PageInfo<User> result=userService.selectAll(start,limit);
        return result;
    }
}
