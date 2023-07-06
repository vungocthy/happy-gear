package com.notimplement.happygear.service.imp;

import com.notimplement.happygear.entities.OrderDetail;
import com.notimplement.happygear.entities.User;
import com.notimplement.happygear.model.dto.AccountDto;
import com.notimplement.happygear.model.dto.OrderDetailModel;
import com.notimplement.happygear.model.dto.OrderDto;
import com.notimplement.happygear.model.dto.UserDto;
import com.notimplement.happygear.model.mapper.Mapper;
import com.notimplement.happygear.repositories.ProductRepository;
import com.notimplement.happygear.repositories.RoleRepository;
import com.notimplement.happygear.repositories.UserRepository;
import com.notimplement.happygear.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ProductRepository productRepository;

    @Override
    public UserDto signup(UserDto userDto) {
        String fullName = userDto.getFullName();
        String userName = userDto.getUsername();
        String address = userDto.getAddress();
        String password = userDto.getPassword();
        String email = userDto.getEmail();
        String phoneNumber = userDto.getPhoneNumber();
        Boolean gender = userDto.getGender();
        Boolean status = true;
        Integer roleId = 2;
        UserDto newUser = new UserDto(userName,fullName,address,password,email,phoneNumber,status,gender,roleId);
        return createUser(newUser);
    }

    @Override
    public UserDto login(AccountDto accountDto) {
        String username = accountDto.getUsername();
        String password = accountDto.getPassword();
        User user = userRepository.findByUsernameAndPassword(username,password);
        if(user!=null){
            return Mapper.toUserDto(user);
        }
        return null;
    }

    @Override
    public UserDto getByUserName(String username) {
        User u = userRepository.findByUsername(username).orElse(null);
        if(u==null){
            return null;
        }
        return Mapper.toUserDto(u);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        User user = toUser(userDto);
        if(user!=null){
            User res = userRepository.save(user);
            return Mapper.toUserDto(res);
        }
        return null;
    }

    private User toUser(UserDto dto){
        if(dto!=null){
            User u = new User();
            u.setUsername(dto.getUsername());
            u.setPassword(dto.getPassword());
            u.setFullName(dto.getFullName());
            u.setAddress(dto.getAddress());
            u.setEmail(dto.getEmail());
            u.setPhoneNumber(dto.getPhoneNumber());
            u.setStatus(dto.getStatus());
            u.setGender(dto.getGender());
            u.setRole(roleRepository.findById(dto.getRoleId()).orElse(null));
            return u;
        }
        return null;
    }

    @Override
    public List<OrderDto> getOrdersByUsername(String username) {
        var res = userRepository.findOrdersByUsername(username)
                .stream()
                .map(Mapper::toOrderDto)
                .collect(Collectors.toList());
        return res;
    }

    @Override
    public UserDto getUserByEmail(String email) {
        Optional<User> u = userRepository.findUserByEmail(email);
        return u.map(Mapper::toUserDto).orElse(null);
    }

    @Override
    public List<OrderDetailModel> getOrderDetailsByUsername(String username) {
        List<OrderDetailModel> res = new ArrayList<>();
        List<OrderDetail> orderDetails = userRepository.findAllOrderDetailByUsername(username);

        orderDetails.stream().forEach(od -> {
            res.add(OrderDetailModel.builder()
                    .detailId(od.getDetailId())
                    .insuranceInfo(od.getProduct().getInsuranceInfo())
                    .picture(od.getProduct().getPicture())
                    .orderId(od.getOrder().getOrderId())
                    .price(od.getPrice())
                    .productId(od.getProduct().getProductId())
                    .productName(od.getProduct().getProductName())
                    .quantity(od.getQuantity())
                    .status(od.getStatus())
                    .build());
        });
        return res;
    }
}
