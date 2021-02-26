package com.example.recycle_system_springboot.controller;

import com.example.recycle_system_springboot.pojo.entity.Address;
import com.example.recycle_system_springboot.service.AddressService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@Controller
public class AddressController {

    @Resource
    AddressService addressService;
    @Resource
    Address address;
    @PostMapping("/updateAddress")
    @ResponseBody
    public boolean updateAddress(@RequestBody Address address){
        boolean result =addressService.updateAddress(address);
        return result;
    }

    @DeleteMapping("/userDeleteAddress/{id}")
    @ResponseBody
    public boolean deleteAddress(@PathVariable("id") int id){
        boolean result=addressService.userdeleteAddress(id);
        return result;
    }

    @PostMapping("/insertAddress")
    @ResponseBody
    public boolean insertAddress(@RequestBody Address address){
        boolean result=addressService.insert(address);
        return result;
    }
}
