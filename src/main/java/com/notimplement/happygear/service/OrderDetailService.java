package com.notimplement.happygear.service;

import com.notimplement.happygear.entities.OrderDetail;
import com.notimplement.happygear.model.dto.CartItemDto;
import com.notimplement.happygear.model.dto.OrderDetailDto;

import java.util.List;

public interface OrderDetailService {
    OrderDetailDto create(OrderDetailDto orderDetailDto);
    Double getCartAmount(List<CartItemDto> list);
    List<OrderDetail> getByOrderId(Integer id);
}
