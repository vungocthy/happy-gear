package com.notimplement.happygear.service;

import com.notimplement.happygear.model.dto.AccountDto;
import com.notimplement.happygear.model.dto.OrderDetailModel;
import com.notimplement.happygear.model.dto.OrderDto;
import com.notimplement.happygear.model.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto signup(UserDto userDto);
    UserDto login(AccountDto accountDto);
    UserDto createUser(UserDto userDto);
    UserDto getByUserName(String name);
    List<OrderDto> getOrdersByUsername(String username);
    UserDto getUserByEmail(String email);
    List<OrderDetailModel> getOrderDetailsByUsername(String username);
}
