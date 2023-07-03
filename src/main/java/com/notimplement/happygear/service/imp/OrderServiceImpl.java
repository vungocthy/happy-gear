package com.notimplement.happygear.service.imp;

import com.notimplement.happygear.entities.Order;
import com.notimplement.happygear.model.dto.OrderDto;
import com.notimplement.happygear.model.mapper.Mapper;
import com.notimplement.happygear.repositories.OrderRepository;
import com.notimplement.happygear.repositories.UserRepository;
import com.notimplement.happygear.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Override
    public OrderDto create(OrderDto orderDto) {
        if(orderDto!=null) {
            Order order = toOrder(orderDto);
            Order res = orderRepository.save(order);
            return Mapper.toOrderDto(res);
        }
        return null;
    }

    private Order toOrder(OrderDto orderDto){
        Order order = new Order();
        order.setOrderId(orderDto.getOrderId());
        order.setDate(orderDto.getDate());
        order.setTotal(orderDto.getTotal());
        order.setStatus(orderDto.getStatus());
        order.setUser(userRepository.findByUsername(orderDto.getUserName()).orElseThrow());
        return order;
    }
}
