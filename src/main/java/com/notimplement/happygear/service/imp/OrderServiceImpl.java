package com.notimplement.happygear.service.imp;

import com.notimplement.happygear.entities.Order;
import com.notimplement.happygear.model.dto.OrderDto;
import com.notimplement.happygear.model.enums.OrderStatus;
import com.notimplement.happygear.repositories.OrderRepository;
import com.notimplement.happygear.repositories.UserRepository;
import com.notimplement.happygear.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final ModelMapper mapper;

    @Override
    public List<OrderDto> getAllOrderDto() {
        return orderRepository.findAll()
                .stream().map(v -> mapper.map(v, OrderDto.class)).toList();
    }

    @Override
    public List<Order> getAllOrder() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderDto> getByUserName(String username) {
        return orderRepository.findByUserName(username)
                .stream().map(v -> mapper.map(v, OrderDto.class)).toList();
    }

    @Override
    public OrderDto getByOrderId(Integer id) {
        Order order = orderRepository.findByOrderId(id);
        return mapper.map(order, OrderDto.class);
    }

    @Override
    public OrderDto update(OrderDto orderDto) {
        if(orderDto!=null){
            Order order = toOrder(orderDto);
            orderRepository.save(order);
            return mapper.map(order, OrderDto.class);
        }
        return null;
    }

    @Override
    public OrderDto create(OrderDto orderDto) {
        if(orderDto!=null) {
            Order order = toOrder(orderDto);
            orderRepository.save(order);
            return mapper.map(order, OrderDto.class);
        }
        return null;
    }

    @Override
    public OrderDto delete(Integer id) {
        Order order = orderRepository.findByOrderId(id);
        order.setStatus(OrderStatus.CANCEL.getAction());
        return mapper.map(orderRepository.save(order), OrderDto.class);
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
