package com.example.recycle_system_springboot.service.impl;

import com.example.recycle_system_springboot.dao.AddressDao;
import com.example.recycle_system_springboot.pojo.entity.Address;
import com.example.recycle_system_springboot.service.AddressService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
@Service
public class AddressServiceImpl implements AddressService {
    @Resource
    AddressDao addressDao;

    //用户用于更新地址
    @Override
    public boolean updateAddress(Address address) {
        addressDao.updateByPrimaryKey(address);
        return true;
    }
    //用户用于删除地址(业务逻辑：将address表中的userid置空)
    @Override
    public boolean userdeleteAddress(int addressId) {
        addressDao.deleteAddressByUser(addressId);
        return true;
    }
    //用户用于增加地址
    @Override
    public boolean insert(Address address) {
        addressDao.insert(address);
        return true;
    }
}
