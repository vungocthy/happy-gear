package com.notimplement.happygear.service.imp;

import com.notimplement.happygear.entities.User;
import com.notimplement.happygear.model.dto.AccountDto;
import com.notimplement.happygear.model.dto.OrderDto;
import com.notimplement.happygear.model.dto.UserDto;
import com.notimplement.happygear.model.mapper.Mapper;
import com.notimplement.happygear.repositories.RoleRepository;
import com.notimplement.happygear.repositories.UserRepository;
import com.notimplement.happygear.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    @Override
    public List<UserDto> getAllUserDto() {
        return userRepository.findAll()
                .stream()
                .map(Mapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    public UserDto signup(UserDto userDto) {
        String fullName = userDto.getFullName();
        String userName = userDto.getUsername();
        String address = userDto.getAddress();
        String password = userDto.getPassword();
        System.out.println(password);
        String email = userDto.getEmail();
        String phoneNumber = userDto.getPhoneNumber();
        Boolean gender = userDto.getGender();
        Boolean status = true;
        Integer roleId = 2;

        UserDto u = getByUserName(userName);
        if(u!=null){
            return null;
        }
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
    public List<UserDto> getAllActiveUser() {
        return userRepository.findAllUserWithActiveStatus()
                .stream()
                .map(Mapper::toUserDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        User user = toUser(userDto);
        User res = userRepository.save(user);
        return Mapper.toUserDto(res);
    }

    @Override
    public UserDto deleteUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow();
        if(user!=null){
            user.setStatus(false);
            User res = userRepository.save(user);
            return Mapper.toUserDto(res);
        }
        return null;
    }

    @Override
    public UserDto updateUser(UserDto userDto, String username) {
        User user = userRepository.findByUsername(username).orElse(null);
        if(user!=null){
            User updatedUser = toUser(userDto);
            User res = userRepository.save(updatedUser);
            return Mapper.toUserDto(res);
        }
        return null;
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

    @Override
    public List<UserDto> searchByFullName(String name) {
        return userRepository.findByFullNameContainingIgnoreCase(name)
                .stream()
                .map(Mapper::toUserDto)
                .collect(Collectors.toList());
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
	public Map<List<UserDto>, Long> listByPage(Pageable p) {
		Map<List<UserDto>, Long> pair = new HashMap<List<UserDto>, Long>();
		Page<User> pageList = userRepository.findAll(p);
		pair.put(
            pageList.stream().map(Mapper::toUserDto).collect(Collectors.toList()),
            pageList.getTotalElements());
		return pair;
	}

    @Override
    public List<OrderDto> getOrdersByUsername(String username) {
        return userRepository.findOrdersByUsername(username)
                .stream()
                .map(Mapper::toOrderDto)
                .collect(Collectors.toList());
    }
}
