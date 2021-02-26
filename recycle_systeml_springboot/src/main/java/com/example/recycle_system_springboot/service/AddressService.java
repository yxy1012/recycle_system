package com.example.recycle_system_springboot.service;

import com.example.recycle_system_springboot.pojo.entity.Address;

public interface AddressService {
    boolean updateAddress(Address address);

    boolean userdeleteAddress(int addressId);

    boolean insert(Address address);
}
