package com.example.recycle_system_springboot.pojo.vo;


import com.example.recycle_system_springboot.pojo.entity.User;
import lombok.Data;

import java.util.List;
@Data
public class UserVo extends User {
    //返回给前端的用户信息类
    private List<AddressVo> addressList;
}
