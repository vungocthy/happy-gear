package com.notimplement.happygear.controllers;

import com.notimplement.happygear.model.dto.*;
import com.notimplement.happygear.service.OrderDetailService;
import com.notimplement.happygear.service.OrderService;
import com.notimplement.happygear.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderApi {

    private final OrderService orderService;
    private final OrderDetailService orderDetailService;
    private final UserService userService;

    @PostMapping("")
    public ResponseEntity<?> createOrder(@Valid @RequestBody RequestOrderDto order) {
        log.info("Request " + order.toString());
        OrderDto orderDto = new OrderDto();
        List<CartItemDto> list = order.getCartItems();

        UserDto userDto = userService.getByUserName(order.getUserName());
        Double total = orderDetailService.getCartAmount(order.getCartItems());

        if (total == 0d) {
            return ResponseEntity.status(HttpStatus.INSUFFICIENT_STORAGE)
                    .body("The quantity is not enough");
        }
        if (userDto != null) {
            orderDto.setUserName(userDto.getUsername());
            orderDto.setDate(Date.valueOf(java.time.LocalDate.now()));
            orderDto.setTotal(total);
            orderDto.setStatus(1);
            OrderDto newOrderDto = orderService.create(orderDto);

            list.forEach(item -> {
                log.info("Item: " + item);
                OrderDetailDto orderDetailDto = new OrderDetailDto();
                orderDetailDto.setOrderId(newOrderDto.getOrderId());
                orderDetailDto.setPrice(item.getPrice());
                orderDetailDto.setQuantity(item.getQuantity());
                orderDetailDto.setStatus(true);
                orderDetailDto.setProductId(item.getProductId());
                orderDetailService.create(orderDetailDto);
            });
            log.info("User with username: " + userDto.getUsername());
        } else {
            log.info("User is not exist");
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("User is not exist");
        }
        log.info("order push........");
        return ResponseEntity.ok("success");
    }

    @GetMapping("/{id}/order-details")
    public ResponseEntity<?> getOrderDetailByOrderId(@PathVariable(name = "id") Integer id) {
        return ResponseEntity.ok(orderDetailService.getByOrderId(id));
    }
}
