package com.example.recycle_system_springboot.pojo.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * user
 * @author
 */
@Data
public class User implements Serializable {
    //用户实体类
    private Integer userId;

    private String userName;

    private String userRealname;

    private String password;

    private String phone;

    private static final long serialVersionUID = 1L;

}
